package org.dong.proto.util.log;

import org.apache.log4j.Logger;



/**
 *作者：dongjibo
 *创建时间：2012-5-8
 */
public class LogUtil {
	
	private static Logger logger = Logger.getLogger("default");
	
	//获取本包下的log4j配置
//	public static void setDebug(boolean isDebug){
//		if (isDebug) {
////			PropertyConfigurator.configure( System.getProperty("user.dir")+"/src/main/java/org/proto/util/log/log4j.properties" );
////			PropertyConfigurator.configure( LogUtil.class.getResource("").getPath().substring(1)+"log4j.properties" );
//		}
//	}
	//初始化log对象
	public static void setLogName(String name){
		logger = Logger.getLogger(name);
	}
	
	//初始化
	static {
//		setDebug(true);
		setLogName("proto");
	}
	
	
	/**
	 * 需要传类名
	 * @param clazz
	 * @param msg
	 */
	@SuppressWarnings("unchecked")
	public static void info(Class clazz, Object msg) {
			Logger.getLogger(clazz).info(msg);
	}
	/**
	 * 只写日志信息
	 * @param msg
	 */
	public static void info(String msg) {
		logger.info(msg);
	}
	
	/**
	 * @param msg
	 * @param e
	 */
	public static void error(Object msg, Throwable e) {
		logger.error(msg, e);
	}
	
	public static void error(Throwable e) {
		logger.error("", e);
	}
}



