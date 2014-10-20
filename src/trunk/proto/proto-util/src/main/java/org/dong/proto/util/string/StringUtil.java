package org.dong.proto.util.string;


import java.util.Iterator;
import java.util.Map;

/**
 *作者：dongjibo
 *创建时间：2012-5-8
 */
public class StringUtil {

	/**
	 * 字符串是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return str == null || "".equals(str.trim());
	}

	/**
	 * 字符串数组是否为空
	 * 
	 * @param strs
	 * @return
	 */
	public static boolean isEmpty(String[] strs) {
		if (strs == null || strs.length == 0)
			return true;
		boolean isEmpty = true;
		for (String str : strs) {
			if (!isEmpty(str)) {
				isEmpty = false;
			}
		}
		return isEmpty;
	}

	/**
	 * 字符串Map是否为空
	 * 
	 * @param params
	 * @return
	 */
	public static boolean isEmpty(Map<String, String> params) {
		if (params == null || params.size() == 0) {
			return true;
		}
		Iterator<String> iter = params.keySet().iterator();
		int count = 0;
		while (iter.hasNext()) {
			String key = iter.next();
			String value = params.get(key);
			if (isEmpty(value)) {
				count++;
			}
		}
		if (count == params.size())
			return true;
		return false;
	}

	/**
	 * 字符串是否是Integer类型
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isInteger(String str) {
		try {
			Integer.valueOf(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/**
	 * 字符串是否是Long类型
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isLong(String str) {
		try {
			Long.valueOf(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/**
	 * 字符串是否是Double类型
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isDouble(String str) {
		try {
			Double.valueOf(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/**
	 * 字符串是否是Float类型
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isFloat(String str) {
		try {
			Float.valueOf(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/**
	 * 判断是否为中文
	 * @param content
	 * @return
	 */
	public static boolean isChinese(String content) {
		return content.matches("[\u4E00-\u9FA5]+");
	}
}



