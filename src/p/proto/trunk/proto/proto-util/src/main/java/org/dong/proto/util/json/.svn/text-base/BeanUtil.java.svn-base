package org.dong.proto.util.json;



import java.util.Map;

/**
 *作者：dongjibo
 *创建时间：2012-5-8
 */
public class BeanUtil {
	/**
	 * 将对象转化为Map
	 * @param object
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> toMap(Object object) {
		try {
			Map map = org.apache.commons.beanutils.BeanUtils.describe(object);
			map.remove("class");
			return map;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 将对象转化为Map<String,Object>
	 * @param object
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> toMapObject(Object object) {
		try {
			Map map = org.apache.commons.beanutils.BeanUtils.describe(object);
			map.remove("class");
			return map;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}



