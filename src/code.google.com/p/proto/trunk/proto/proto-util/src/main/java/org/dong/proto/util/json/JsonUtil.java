package org.dong.proto.util.json;


import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *作者：dongjibo
 *创建时间：2012-5-8
 */
public class JsonUtil {
	
	/**
	 * map是不带[]的，只有{}
	 * @param map
	 * @return
	 * {"id":"000","selected":true,"text":"item0"}
	 */
	public static JSONObject mapToJSON(Map<String, Object> map){
		return JSONObject.fromObject(map);  
	}
	
	/**
	 * array是带[]的
	 * @param items
	 * @return
	 * [{"id":"000","selected":true,"text":"item0"},{"id":"001","text":"item1"},{"id":"002","text":"item2"}]
	 */
	public static JSONArray arryToJSON(Object items){
		return JSONArray.fromObject(items);
	}
	
	public static JSONObject beanToJSON(Object object){
		return JSONObject.fromObject(object);  
	}
	
	
}



