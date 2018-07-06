package com.diiwon.mis.plat.base.comm;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.support.WebApplicationContextUtils;

@Service
public class AppContext implements ApplicationContextAware {
	static Logger log = LoggerFactory.getLogger(AppContext.class);

	private static ApplicationContext applicationContext = null;

	public static ApplicationContext getContext() {
		if (applicationContext == null) {
			synchronized (AppContext.class) {
				if (applicationContext == null) {
					log.debug("use ContextLoader.getCurrentWebApplicationContext().");
					applicationContext = ContextLoader.getCurrentWebApplicationContext();
					if (applicationContext == null) {
						log.debug(
								"ContextLoader.getCurrentWebApplicationContext() return null,use new ClassPathXmlApplicationContext(classpath:applicationContext.xml).");
						applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
					}
				}
			}
		}
		return applicationContext;
	}

	public void setApplicationContext(ApplicationContext ac) throws BeansException {
		applicationContext = ac;
	}

	public static void setServlet(ServletContext servletContext) {
		applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
	}

	public static <T> T getBean(Class<T> clazz) {
		return applicationContext.getBean(clazz);
	}
	
	public static <T> T getBean(String beanName) {
		return (T) applicationContext.getBean(beanName);
	}
}