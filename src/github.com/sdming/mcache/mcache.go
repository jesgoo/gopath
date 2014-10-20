// Copyright 2013 by sdm. All rights reserved.
// license that can be found in the LICENSE file.

/*
mcache is a package to provide cache in memory.

* 1. thread safe
* 2. support expiration after absolute or sliding span of time
* 3. support CAS Update

*/
package mcache

import (
	"bytes"
	"fmt"
	"runtime"
	"sync"
	"time"
)

// ExpirationKind is the kind of cache entry expiration
type ExpirationKind int

const (
	// SlidingExpiration means cache entry should be evicted if it has not been accessed in a given span of time.
	SlidingExpiration ExpirationKind = 0

	// AbsoluteExpiration means cache entry should be evicted after a specified duration.
	AbsoluteExpiration ExpirationKind = 1
)

const (
	// _minTickInterval is the min interval duration to run expiration check process
	_minTickInterval time.Duration = time.Second

	// _minExpiration is the min duration of cache entry expiration
	_minExpiration time.Duration = time.Microsecond

	// _noExpiration means cache entry will not be expirated
	_noExpiration time.Duration = 1000 * 1000 * time.Hour
)

// TickInterval is the the interval duration of expiration check
var TickInterval time.Duration = time.Minute

// item is cache entry item
type item struct {
	Key        string
	Value      interface{}
	Version    int
	Kind       ExpirationKind
	Expiration time.Duration
	ExpAt      time.Time
}

// expired return cache entry expired or not
func (item *item) expired() bool {
	//return time.Now().UnixNano() > item.ExpAtN
	return time.Now().After(item.ExpAt)
}

// touch can refresh cache entry expiration time
func (item *item) touch() {
	if item.Kind != SlidingExpiration {
		return
	}

	if item.Expiration >= _minExpiration {
		item.ExpAt = time.Now().Add(item.Expiration)
	}
}

// MCache is cache in memory
type MCache struct {
	*mcache
}

// https://groups.google.com/forum/?fromgroups=#!topic/golang-nuts/1ItNOOj8yW8
type mcache struct {
	sync.RWMutex
	items map[string]*item
	stop  chan bool
	tick  <-chan time.Time
}

// get
func (mc *mcache) get(key string) (*item, bool) {
	mc.RLock()
	x, ok := mc.items[key]
	mc.RUnlock()

	if !ok {
		return nil, false
	}

	if x.Expiration < _minExpiration {
		return x, ok
	}
	if x.expired() {
		//mc.delete(key)
		return nil, false
	}

	return x, ok
}

// delete
func (mc *mcache) delete(key string) {
	mc.Lock()
	defer mc.Unlock()
	delete(mc.items, key)
}

// SetP set a cache entry with very long expiration time
func (mc *mcache) SetP(key string, value interface{}) {
	mc.Set(key, value, 0, AbsoluteExpiration)
}

// SetAbs set a cache entry with AbsoluteExpiration
func (mc *mcache) SetAbs(key string, value interface{}, expire time.Duration) {
	mc.Set(key, value, expire, AbsoluteExpiration)
}

// SetSlid set a cache entry with SlidingExpiration
func (mc *mcache) SetSlid(key string, value interface{}, expire time.Duration) {
	mc.Set(key, value, expire, SlidingExpiration)
}

// set
func (mc *mcache) set(key string, value interface{}, expire time.Duration, kind ExpirationKind) {
	var expAt time.Time
	if expire < _minExpiration {
		expire = 0
		expAt = time.Now().Add(_noExpiration)
	} else {
		expAt = time.Now().Add(expire)
	}

	mc.items[key] = &item{
		Key:        key,
		Value:      value,
		Version:    0,
		Kind:       kind,
		Expiration: expire,
		ExpAt:      expAt,
	}
	return
}

// Set set a cache entry with expire time span and kind
func (mc *mcache) Set(key string, value interface{}, expire time.Duration, kind ExpirationKind) {
	mc.Lock()
	defer mc.Unlock()

	mc.set(key, value, expire, kind)
}

// Get return a cached value, it return false if key doesn't exist
func (mc *mcache) Get(key string) (interface{}, bool) {
	x, ok := mc.get(key)
	if !ok {
		return nil, false
	}

	x.touch()
	return x.Value, true
}

// GetV return cached value and it's version
func (mc *mcache) GetV(key string) (interface{}, int, bool) {
	x, ok := mc.get(key)
	if !ok {
		return nil, 0, false
	}

	x.touch()
	return x.Value, x.Version, true
}

// Add insert a cache entry, it return false if key exist
func (mc *mcache) Add(key string, value interface{}, expire time.Duration, kind ExpirationKind) bool {
	mc.Lock()
	defer mc.Unlock()

	x, ok := mc.items[key]
	if !ok {
		mc.set(key, value, expire, kind)
		return true
	}

	if x.Expiration >= _minExpiration && x.expired() {
		mc.set(key, value, expire, kind)
		return true
	}

	return false
}

// update
func (mc *mcache) update(key string, version int, value interface{}) bool {
	x, ok := mc.get(key)
	if !ok {
		return false
	}

	mc.Lock()
	defer mc.Unlock()

	if version >= 0 && x.Version != version {
		return false
	}

	x.Value = value
	x.Version++
	x.touch()

	return true
}

// Update update cache entry, it return false if key doesn't exist
func (mc *mcache) Update(key string, value interface{}) bool {
	return mc.update(key, -1, value)
}

// UpdateV update cache entry when version match
func (mc *mcache) UpdateV(key string, version int, value interface{}) bool {
	return mc.update(key, version, value)
}

// Delete delete cache entry from the cache
func (mc *mcache) Delete(key string) {
	mc.delete(key)
}

// DeleteMany delete some keys from cache
func (mc *mcache) DeleteMany(keys []string) {
	if keys == nil || len(keys) == 0 {
		return
	}

	mc.Lock()
	defer mc.Unlock()

	for _, k := range keys {
		delete(mc.items, k)
	}
}

// Count return number of cache entry, maybe include expired
func (mc *mcache) Count() int {
	mc.Lock()
	defer mc.Unlock()

	n := len(mc.items)
	return n
}

// Exists return whether the key exist
func (mc *mcache) Exists(key string) bool {
	_, ok := mc.get(key)
	return ok
}

// Keys return all cache keys
func (mc *mcache) Keys() []string {
	mc.RLock()
	defer mc.RUnlock()

	keys := make([]string, 0, 255)

	for k, v := range mc.items {
		if !v.expired() {
			keys = append(keys, k)
		}
	}

	return keys
}

// Stat return MCache stat information
func (mc *mcache) Stat() string {
	mc.RLock()
	defer mc.RUnlock()

	var buf bytes.Buffer
	buf.WriteString("start stat \n")
	buf.WriteString(fmt.Sprintf("Len=%d \n", len(mc.items)))
	for k, v := range mc.items {
		buf.WriteString(fmt.Sprintf("key=%s; value=%v; ExpAt=%v; \n", k, v.Value, v.ExpAt))
	}
	buf.WriteString("end stat \n")
	return buf.String()
}

// expKeys
func (mc *mcache) expKeys() (keys []string) {
	mc.RLock()
	defer mc.RUnlock()

	for k, v := range mc.items {
		if v.expired() {
			if keys == nil {
				keys = make([]string, 0, 255)
			}
			keys = append(keys, k)
		}
	}

	return
}

// recycle
func (mc *mcache) recycle() {
	keys := mc.expKeys()
	mc.DeleteMany(keys)
}

// startTick start a goroutine to check expire checking
func (mc *mcache) startTick() {
	if mc == nil {
		return
	}

	interval := TickInterval
	if interval < _minTickInterval {
		interval = _minTickInterval
	}

	mc.tick = time.Tick(interval)
	for {
		select {
		case <-mc.tick:
			mc.recycle()
		case <-mc.stop:
			return
		}
	}
}

// stopTick can stop goroutine of expire
func stopTick(mc *MCache) {
	mc.stop <- true
}

// NewMCache return *MCache with default options
func NewMCache() *MCache {
	cache := &mcache{
		items: map[string]*item{},
		stop:  make(chan bool),
	}
	c := &MCache{cache}

	go cache.startTick()
	runtime.SetFinalizer(c, stopTick)
	return c
}
