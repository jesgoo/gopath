package org.dong.proto.core.factory;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanCreateFactory {
	private static String[] locations = new String[] {
			"spring/spring-context.xml"
	};

	private static ApplicationContext ctx;
	static {
		ctx = new ClassPathXmlApplicationContext(locations);
	}

	/**
	 * 
	 * @param beanName
	 * @return
	 */
	public static Object getBean(String beanName) {
		if (beanName == null || beanName.trim() == "") {
			throw new RuntimeException("beanName cannot be none");
		}
		return ctx.getBean(beanName);
	}

}
