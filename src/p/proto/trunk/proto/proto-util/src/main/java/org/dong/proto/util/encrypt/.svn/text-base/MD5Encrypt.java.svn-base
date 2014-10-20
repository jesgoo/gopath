package org.dong.proto.util.encrypt;

import java.security.MessageDigest;

import org.dong.proto.util.log.LogUtil;

/**
 *作者：dongjibo
 *创建时间：2012-5-8
 */
public class MD5Encrypt {

	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D",
			"E", "F" };

	/**
	 * 转换字节数组为16进制字串
	 * 
	 * @param b
	 * 
	 * @return
	 */
	private static String byteArrayToHexString(byte[] b) {
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			stringBuffer.append(byteToHexString(b[i]));
		}
		return stringBuffer.toString();
	}

	/**
	 * 若使用本函数转换则可得到加密结果的16进制表示，即数字字母混合的形式
	 * 
	 * @param b
	 * 
	 * @return
	 */
	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	/**
	 * 密码加密
	 * 
	 * @param password
	 * @return resultString
	 */
	public static String encryptPassword(String password) {
		String resultString = "";
		try {
			resultString = new String(password);
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byteArrayToHexString(md.digest(resultString.getBytes("UTF-8")));
			return resultString.toLowerCase();
		} catch (Exception e) {
			LogUtil.error(MD5Encrypt.class, e);
			return resultString = "";
		}
	}

	/**
	 * 判断密码是否一致
	 * 
	 * @param password
	 * @param encryptPassword
	 * @return boolean false;
	 * @throws Exception 
	 */
	public static boolean checkEncryptPassword(String password, String encryptPassword) {
		String resultString = "";
		try {
			resultString = new String(password);
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
		} catch (Exception e) {
			LogUtil.error(MD5Encrypt.class, e);
		}
		return resultString.equalsIgnoreCase(encryptPassword);
	}

}



