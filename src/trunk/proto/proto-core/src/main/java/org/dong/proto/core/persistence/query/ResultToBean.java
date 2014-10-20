package org.dong.proto.core.persistence.query;


import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.property.ChainedPropertyAccessor;
import org.hibernate.property.PropertyAccessor;
import org.hibernate.property.PropertyAccessorFactory;
import org.hibernate.property.Setter;
import org.hibernate.transform.ResultTransformer;

public class ResultToBean implements ResultTransformer {

	private static final long serialVersionUID = -3179980728463321516L;

	@SuppressWarnings("unchecked")
	private final Class resultClass;
	private boolean isInitialized;
	private String[] aliases;
	private Setter[] setters;
	
	@SuppressWarnings("unchecked")
	public ResultToBean(Class resultClass) {
		if ( resultClass == null ) {
			throw new IllegalArgumentException( "resultClass cannot be null" );
		}
		isInitialized = false;
		this.resultClass = resultClass;
	}
	
	@SuppressWarnings("unchecked")
	public List transformList(List collection) {
		return collection;
	}

	public Object transformTuple(Object[] tuple, String[] aliases) {
		Object result;
		
		try {
			if ( ! isInitialized ) {
				initialize( aliases );
			}
			else {
				check( aliases );
			}
			
			result = resultClass.newInstance();

			for ( int i = 0; i < aliases.length; i++ ) {
				if ( setters[i] != null ) {
					//现在全部属性都为String类型的
					setters[i].set( result, tuple[i]+"", null );
				}
			}
		}
		catch ( InstantiationException e ) {
			throw new HibernateException( "Could not instantiate resultclass: " + resultClass.getName() );
		}
		catch ( IllegalAccessException e ) {
			throw new HibernateException( "Could not instantiate resultclass: " + resultClass.getName() );
		}

		return result;
	}

	private void initialize(String[] aliases) {
		PropertyAccessor propertyAccessor = new ChainedPropertyAccessor(
				new PropertyAccessor[] {
						PropertyAccessorFactory.getPropertyAccessor( resultClass, null ),
						PropertyAccessorFactory.getPropertyAccessor( "field" )
				}
		);
		this.aliases = new String[ aliases.length ];
		setters = new Setter[ aliases.length ];
		for ( int i = 0; i < aliases.length; i++ ) {
			String alias = aliases[ i ];
			if ( alias != null ) {
				this.aliases[ i ] = alias;
				setters[ i ] = getSetterByColumnName(propertyAccessor,alias);
			}
		}
		isInitialized = true;
	}

	private void check(String[] aliases) {
		if ( ! Arrays.equals( aliases, this.aliases ) ) {
			throw new IllegalStateException(
					"aliases are different from what is cached; aliases=" + Arrays.asList( aliases ) +
							" cached=" + Arrays.asList( this.aliases ) );
		}
	}
	//根据数据库字段名在POJO查找JAVA属性名，参数就是数据库字段名，如：USER_ID  
   private Setter getSetterByColumnName(PropertyAccessor propertyAccessor,String alias) {  
       //取得POJO所有属性名  
       Field[] fields = resultClass.getDeclaredFields();  
       if(fields==null || fields.length==0){  
           throw new RuntimeException("实体"+resultClass.getName()+"不含任何属性");  
       }  
       //把字段名中所有的下杠去除  
       String proName = alias.replaceAll("_", "").toLowerCase();  
       for (Field field : fields) {  
           if(field.getName().toLowerCase().equals(proName)){//去除下杠的字段名如果和属性名对得上，就取这个SETTER方法  
               return propertyAccessor.getSetter(resultClass, field.getName());  
           }  
       }  
       throw new RuntimeException("找不到数据库字段 ："+ alias + " 对应的POJO属性或其getter方法，比如数据库字段为USER_ID或USERID，那么JAVA属性应为userId");  
   } 
}
