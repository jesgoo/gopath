package org.dong.proto.core.persistence.dao;


import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.dong.proto.core.persistence.query.QueryCondition;
import org.dong.proto.core.persistence.query.ResultToBean;
import org.dong.proto.util.easyui.EasyuiUtil;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.stat.Statistics;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public abstract class DaoSupport<T> implements BaseDao<T>{

	@Resource
	private SessionFactory sessionFactory;
	//实体类对象
	private final Class<T> entityClass;
	
	@SuppressWarnings("unchecked")
	public DaoSupport() {
		//获取实体类
		this.entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	/**获取session*/
    public Session getSession() {
        //事务必须是开启的(Required)，否则获取不到
        return sessionFactory.getCurrentSession();
    }
     
    /**显示二级缓存统计数据*/
	public void showStatistics() {
		Statistics st = this.sessionFactory.getStatistics();
		System.out.println("hit:"+st.getSecondLevelCacheHitCount());
		System.out.println("mis:"+st.getSecondLevelCacheMissCount());
		System.out.println("put:"+st.getSecondLevelCachePutCount());
	}
    
	@SuppressWarnings("unchecked")
	public T get(int id) {
		return (T) this.getSession().get(entityClass, id);
	}
	
    
    /**添加*/
	public void add(T t) {
		this.getSession().save(t);
	}

	/**批量添加*/
	public void batchAdd(List<T> list) {
		Session session = this.getSession();
		for (int i = 0; i < list.size(); i++) {
			session.save(list.get(i));
			if (i % 30 == 0) {
				session.flush();
				session.clear();
			}
		}
		
	}

	/**删除*/
	public void delete(T t) {
		this.getSession().delete(t);		
	}

	/**更改*/
	public void update(T t) {
		this.getSession().update(t);
	}
	
	/**执行本地sql*/
	public void excuteSQL(String sql) {
		this.getSession().createSQLQuery(sql).executeUpdate();
	}

	/**查询所有记录*/
	public List<T> queryAll() {
		return this.queryPage(QueryCondition.init(), 0, 0);
	}
	
	/**查询所有记录*/
	public List<T> queryAll(QueryCondition queryCondition) {
		return this.queryPage(queryCondition, 0, 0);
	}
	
	/**根据单列查询*/
	public T query(String property, Object value) {
		List<T> list = this.queryPage(QueryCondition.init().addEqual(property, value), 0, 0);
		return (list != null && list.size() > 0) ? list.get(0) : null;
	}

	/**查询记录条数*/
	public long queryCount(QueryCondition conditions) {
		Criteria c = this.getSession().createCriteria(entityClass);
		//为了防止fetch="join"出现重复记录
		c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY); 
		//设置二级缓存
		c.setCacheable(true);
		
		//多级查询
		List<String> aliasStr = new ArrayList<String>();
		for(String s : conditions.alias){
			this.addAlias(s, c,aliasStr);
		}
		//设置查询条件
		for(Criterion cn : conditions.conditions){
			c.add(cn);
		}
		c.setProjection(Projections.rowCount());
	
		return (Long) c.uniqueResult();
	}
	
	/**查询记录条数*/
	public long queryCount() {
		return this.queryCount(QueryCondition.init());
	}

	@SuppressWarnings("unchecked")
	/**分页查询*/
	public List<T> queryPage(QueryCondition conditions, int page,
			int pageSize) {
		Criteria c = this.getSession().createCriteria(entityClass);
		//为了防止fetch="join"出现重复记录
		c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY); 
		//设置二级缓存
		c.setCacheable(true);
		
		//多级查询
		List<String> aliasStr = new ArrayList<String>();//为了防止重复申明别名
		for(String s : conditions.alias){
			this.addAlias(s, c,aliasStr);
		}
		
		//设置查询条件
		for(Criterion cn : conditions.conditions){
			c.add(cn);
		}
		//设置排序条件
		for(Order o : conditions.orders){
			c.addOrder(o);
		}
		//分页设置
		if (pageSize > 0) {
			c.setFirstResult((page - 1) * pageSize);
			c.setMaxResults(pageSize);
		}
		
		return c.list();
	}
	
	/**
	 * 添加别名
	 * hibernate中多层级和集合查询时，只能却别名，并且一次只能取一级
	 * 例如task.product.productLine.agreementType.id
	 *  task-task
		task.product-product
		product.productLine-productLine
		productLine.agreementType-agreementType
	 * @param p			多级字段
	 * @param c			Criteria
	 * @param aliasStr	已有的alias
	 */
	private void addAlias(String p,Criteria c, List<String> aliasStr) {
		  if (p.contains(".")) {  
			  
	            //多层关联问题解决  
	            String[] props=p.split("\\.");  
	             
	            StringBuffer perPropName=new StringBuffer(props[0]);  
	            String alienName= props[0];  
	            if (!hasAlias(aliasStr,alienName)) {
	            	c.createAlias(perPropName.toString(), alienName.toString(),Criteria.LEFT_JOIN); 
//	            	System.out.println(perPropName.toString()+"-"+ alienName.toString());
				}
	            
	            for (int i = 1; i < props.length-1;i++) {  
	            	perPropName.delete(0, perPropName.length());
	                perPropName=perPropName.append(alienName).append(".").append(props[i]);  
	                alienName= props[i];  
	                if (!hasAlias(aliasStr,alienName)) {
	                	 c.createAlias(perPropName.toString(), alienName.toString(),Criteria.LEFT_JOIN); 
//	                	 System.out.println(perPropName.toString()+"-"+ alienName.toString());
	                }
	               
	            }  
	        }
	}
	
	/**
	 * 是否已经存在alias
	 * @param aliasStr		已有alias
	 * @param alienName		别名
	 * @return
	 */
	private boolean hasAlias(List<String> aliasStr, String alienName) {
		for(String a : aliasStr){
			if (a.equals(alienName)) {
				return true;
			}
		}
		aliasStr.add(alienName);
		return false;
	}
	/**本地sql查询*/
	@SuppressWarnings({ "unchecked", "hiding" })
	public <T> List<T> querySQL(String sql,Class<T> clazz) {
		SQLQuery query = this.getSession().createSQLQuery(sql);  
		//hibernate自带的转换器
//		query.setResultTransformer(Transformers.aliasToBean(clazz));  
//		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);  
//		query.setResultTransformer(Transformers.TO_LIST);  
		query.setResultTransformer(new ResultToBean(clazz));  
        //返回查询结果  
		return query.list(); 
	}
	/**本地sql查询*/
	@SuppressWarnings({ "unchecked", "hiding" })
	public <T> List<T> querySQLByEntity(String sql,Class<T> clazz) {
		return this.getSession().createSQLQuery(sql).addEntity(clazz).list(); 
	}
	
	/**
	 * 为了使用easyui中的datagrid插件方便
	 * 创建这个方法直接返回ajax需要的json数据
	 * 分页查询,page、pageSize为0时表示不分页
	 * @param conditions	查询条件
	 * @param page			页数下标
	 * @param pageSize		每页记录条数
	 * @return
	 */
	public String queryPage4Datagrid(QueryCondition conditions, int page,int pageSize) {
		return EasyuiUtil.getDataGrid(queryPage(conditions, page, pageSize), queryCount(conditions));
	}
	public String queryPage4Datagrid( int page,int pageSize) {
		return EasyuiUtil.getDataGrid(queryPage(QueryCondition.init(), page, pageSize), queryCount(QueryCondition.init()));
	}
	
}
