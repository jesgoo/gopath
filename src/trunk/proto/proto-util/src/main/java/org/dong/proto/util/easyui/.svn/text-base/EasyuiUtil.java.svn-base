package org.dong.proto.util.easyui;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dong.proto.util.json.BeanUtil;
import org.dong.proto.util.json.JsonUtil;

/**
 *作者：dongjibo
 *创建时间：2012-9-6
 */
public class EasyuiUtil {

	/**
	 * 获取combox的json数据
	 * @param items 
	 * @return 
	 * [{"id":"000","selected":true,"text":"item0"},{"id":"001","text":"item1"},{"id":"002","text":"item2"}]
	 */
	public static String getCombox(List<ComboxInfo> items){
		return JsonUtil.arryToJSON(items).toString();
	}

	/**
	 * 分页的json数据
	 * @param items
	 * @param cout
	 * @return
	 * 
	 * {                                                      
			"total":239,                                                      
			"rows":[                                                          
				{"code":"001","name":"Name 1","addr":"Address 11","col4":"col4 data"},         
				{"code":"002","name":"Name 2","addr":"Address 13","col4":"col4 data"},         
				{"code":"003","name":"Name 3","addr":"Address 87","col4":"col4 data"},         
				{"code":"004","name":"Name 4","addr":"Address 63","col4":"col4 data"},         
				{"code":"005","name":"Name 5","addr":"Address 45","col4":"col4 data"},         
				{"code":"006","name":"Name 6","addr":"Address 16","col4":"col4 data"},          
				{"code":"007","name":"Name 7","addr":"Address 27","col4":"col4 data"},          
				{"code":"008","name":"Name 8","addr":"Address 81","col4":"col4 data"},          
				{"code":"009","name":"Name 9","addr":"Address 69","col4":"col4 data"},          
				{"code":"010","name":"Name 10","addr":"Address 78","col4":"col4 data"}     
			]                                                          
		} 
	 * 
	 */
	@SuppressWarnings("unchecked")
	public static String getDataGrid(List items, long cout) {
		Map<String, Object> result = new HashMap<String, Object>();
		List<Map<String, String> > objects = new ArrayList<Map<String, String> >(); 
		/**
		 * 对象中如果有set等集合对象，json工具会出现循环引用，
		 * 所以稳妥的方式还是先将对象转换为map
		 */
		for(Object o:items){
			objects.add(BeanUtil.toMap(o));
		}
		result.put("total", cout);
		result.put("rows", objects);
		return JsonUtil.mapToJSON(result).toString();
	}
	
	
	public static String getDataBean(Object item) {
		return JsonUtil.beanToJSON(item).toString();
	}
	
	/**
	 * 树结构
	 * @param tree
	 * [{  
		    "id":1,  
		    "text":"Folder1",  
		    "iconCls":"icon-save",  
		    "children":[{  
		        "text":"File1",  
		        "checked":true  
		    },{  
		        "text":"Books",  
		        "state":"open",  
		        "attributes":{  
		            "url":"/demo/book/abc",  
		            "price":100  
		        },  
		        "children":[{  
		            "text":"PhotoShop",  
		            "checked":true  
		        },{  
		            "id": 8,  
		            "text":"Sub Bookds",  
		            "state":"closed"  
		        }]  
		    }]  
		},{  
		    "text":"Languages",  
		    "state":"closed",  
		    "children":[{  
		        "text":"Java"  
		    },{  
		        "text":"C#"  
		    }]  
		}]  
	 * @return 
	 * 
	 */
	public static String getTreeInfo(List<TreeNodeInfo> tree) {
		return JsonUtil.arryToJSON(tree).toString();
	}

	@SuppressWarnings("unchecked")
	public static String getTreeGrid(List tree) {
		return JsonUtil.arryToJSON(tree).toString();
	}
	
}



