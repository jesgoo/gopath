package org.dong.proto.core.persistence.query;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class QueryCondition {
	//查询条件
	public List<Criterion> conditions;
	//排序
	public List<Order> orders;
	//多级查询的字段名称
	public List<String> alias;
	
	public static final String TYPE_ORDER_DESC = "desc";//降序
	
	public static final String TYPE_ORDER_ASC = "asc";//升序
	//私有构造函数，强迫使用init方式产生对象
	private QueryCondition(){
		conditions = new ArrayList<Criterion>();
		orders = new ArrayList<Order>();
		alias = new ArrayList<String>();
	}
	//初始化查询条件对象
	public static QueryCondition init(){
		return new QueryCondition();
	}
	/**
	 * 格式化多级字段
	 * @param p
	 * @return
	 */
	private String checkProperty(String p) {
        if (p.contains(".")) { 
        	//保存多级字段
        	alias.add(p);
        	
            //多层关联问题解决  
            String[] props=p.split("\\.");  
              
            StringBuffer perPropName=new StringBuffer(props[0]);  
            StringBuffer alienName=new StringBuffer(perPropName);  
              
            for (int i = 1; i < props.length-1;i++) {  
                perPropName=new StringBuffer(alienName).append(".").append(props[i]);  
                alienName=new StringBuffer(props[i]);  
            }  
            return alienName.append(".").append(props[props.length-1]).toString();
        }else{
        	return p;
        }
	}
//-----------------------------------------
//如果不需要添加别名的条件，则使用2个参数的方法 
//-----------------------------------------	
	/**等值条件*/
	public QueryCondition addEqual(String column,Object value){
		conditions.add(Restrictions.eq(checkProperty(column), value));
		return this;
	}
	/**模糊条件*/
	public QueryCondition addLike(String column,Object value){
		conditions.add(Restrictions.like(checkProperty(column), "%"+value+"%"));
		return this;
	}
	/**区间查询*/
	public QueryCondition addBetween(String column,Object value1,Object value2){
		conditions.add(Restrictions.between(checkProperty(column), value1,value2));
		return this;
	}
	/**IN区间查询*/
	public QueryCondition addIn(String column,Object[] values){
		conditions.add(Restrictions.in(checkProperty(column), values));
		return this;
	}
	/**不等于“<>”条件*/
	public QueryCondition addNot(String column,Object value){
		conditions.add(Restrictions.not(Restrictions.eq(checkProperty(column), value)));
		return this;
	}
	/**为空“is null”条件*/
	public QueryCondition addIsNull(String column){
		conditions.add(Restrictions.isNull(checkProperty(column)));
		return this;
	}
	
	/**OR区间查询*/
	public QueryCondition addOr(Criterion c1,Criterion c2){
		conditions.add(Restrictions.or(c1, c2));
		return this;
	}
	
	/**
	 * 排序，使用常量字段
	 * TYPE_ORDER_DESC = "desc";//降序
	 * TYPE_ORDER_ASC = "asc";//升序
	 * 
	 * */
	public QueryCondition addOrder(String column,String type){
		if (TYPE_ORDER_DESC.equalsIgnoreCase(type)) {
			orders.add(Order.desc(checkProperty(column)));
		}else if (TYPE_ORDER_ASC.equalsIgnoreCase(type)) {
			orders.add(Order.asc(checkProperty(column)));
		}else{
			orders.add(Order.asc(checkProperty(column)));			
		}
		return this;
	}
	
	//-----------------------------------------
	//为并联其他逻辑条件(即使用or时)，需要创建查询条件
	//-----------------------------------------	
		/**等值条件*/
		public Criterion createEqual(String column,Object value){
			return Restrictions.eq(checkProperty(column), value);
		}
		/**模糊条件*/
		public Criterion createLike(String column,Object value){
			return Restrictions.like(checkProperty(column), "%"+value+"%");
		}
		/**区间查询*/
		public Criterion createBetween(String column,Object value1,Object value2){
			return Restrictions.between(checkProperty(column), value1,value2);
		}
		/**IN区间查询*/
		public Criterion createIn(String column,Object[] values){
			return Restrictions.in(checkProperty(column), values);
		}
		/**OR区间查询*/
		public Criterion createOr(Criterion c1,Criterion c2){
			return Restrictions.or(c1, c2);
		}
		/**不等于“<>”条件*/
		public Criterion createNot(String column,Object value){
			return Restrictions.not(Restrictions.eq(checkProperty(column), value));
		}
		/**为空“is null”条件*/
		public Criterion createIsNull(String column){
			return Restrictions.isNull(checkProperty(column));
		}
	
}
