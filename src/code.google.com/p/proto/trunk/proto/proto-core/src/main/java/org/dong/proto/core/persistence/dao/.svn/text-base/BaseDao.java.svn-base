package org.dong.proto.core.persistence.dao;


import java.util.List;

import org.dong.proto.core.persistence.query.QueryCondition;



public interface BaseDao<T> {

	/**显示二级缓存统计数据*/
	public void showStatistics();

	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	public T get(int id);
	/**
	 * 添加记录
	 * @param t
	 */
	public void add(T t);
	/**
	 * 批量添加
	 * @param list
	 */
	public void batchAdd(List<T> list);
	/**
	 * 更新记录
	 * @param t
	 */
	public void update(T t);
	/**
	 * 删除记录
	 * @param t
	 */
	public void delete(T t);
	/**
	 * 查询记录条数
	 * @param conditions 查询条件
	 * @return
	 */
	public long queryCount(QueryCondition conditions);
	/**
	 * 查询记录条数
	 * @param conditions 查询条件
	 * @return
	 */
	public long queryCount();
	/**
	 * 查询所有记录
	 * @return
	 */
	public List<T> queryAll();
	/**
	 * 查询所有记录
	 * @return
	 */
	public List<T> queryAll(QueryCondition conditions);
	/**
	 * 分页查询,page、pageSize为0时表示不分页
	 * @param conditions	查询条件
	 * @param page			页数下标
	 * @param pageSize		每页记录条数
	 * @return
	 */
	public List<T> queryPage(QueryCondition conditions,final int page, final int pageSize);
	/**
	 * 根据属性查询对象
	 * @param property
	 * @param value
	 * @return
	 */
	public T query(String property,Object value);

	/**
	 * 原生sql查询
	 * @param <T>
	 * @param sql
	 * @param clazz	现在对象的属性都是String类型的
	 * @return
	 */
	@SuppressWarnings("hiding")
	public <T> List<T> querySQL(String sql,Class<T> clazz);
	/**
	 * 原生sql查询 
	 * @param <T>
	 * @param sql
	 * @param clazz	这个传的是Entity实体对象
	 * @return
	 */
	@SuppressWarnings("hiding")
	public <T> List<T> querySQLByEntity(String sql,Class<T> clazz);
	
	/**
	 * 执行原生sql的update语句
	 * @param sql
	 */
	public void excuteSQL(String sql);
	
	/**
	 * 为了使用easyui中的datagrid插件方便
	 * 创建这个方法直接返回ajax需要的json数据
	 * 分页查询,page、pageSize为0时表示不分页
	 * @param conditions	查询条件
	 * @param page			页数下标
	 * @param pageSize		每页记录条数
	 * @return
	 */
	public String queryPage4Datagrid(QueryCondition conditions,final int page, final int pageSize);
	
	/**
	 * 为了使用easyui中的datagrid插件方便
	 * 创建这个方法直接返回ajax需要的json数据
	 * 分页查询,page、pageSize为0时表示不分页
	 * @param conditions	查询条件
	 * @param page			页数下标
	 * @param pageSize		每页记录条数
	 * @return
	 */
	public String queryPage4Datagrid(final int page, final int pageSize);
}
