package org.dong.proto.core.exception;


public class OSExceptionFactory {
	
	/**
	 * 抛出异常,直接填写异常信息
	 * @param msg
	 * 
	 * 使用方法	OSExceptionFactory.throwException("登陆失败，直接抛的异常信息");
	 */
	public static void throwException(String msg) throws Exception{
		throw new BusinessException(msg);
	}
	
}
