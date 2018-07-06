package com.diiwon.mis.plat.base.cache.imp;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.diiwon.mis.plat.base.cache.SystemCacheService;


/**
 * Web容器启动时加载的方法
 * 
 * @author Sunshine
 */
public class SystemStartLoad implements ServletContextListener{

	private Log log = LogFactory.getLog(this.getClass());
	
	private SystemCache systemCache;

	public SystemCache getSystemCache() {
		return systemCache;
	}

	public void setSystemCache(SystemCache systemCache) {
		this.systemCache = systemCache;
	}
	
	public void contextDestroyed(ServletContextEvent sce) {
		if (log.isDebugEnabled()) {
			log.debug("--> contextDestroyed(ServletContextEvent sce)");
		}
	}

	public synchronized void contextInitialized(ServletContextEvent sce) {
		
		Long start = System.currentTimeMillis();
		log.info("公共缓存加载开始");
		
		if (log.isDebugEnabled()) {
			log.debug("启动--> contextInitialized(ServletContextEvent sce)");
		}
		systemCache = (SystemCache)WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext()).getBean("systemCache");
		if (systemCache.getLoadClass() != null && !systemCache.getLoadClass().isEmpty()) {
			for (int i = 0; i < systemCache.getLoadClass().size(); i++) {
				SystemCacheService scs = (SystemCacheService)WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext()).getBean(systemCache.getLoadClass().get(i));
				scs.run();
			}
		}
		
		log.debug("公共缓存加载结束-—>耗时： "+(System.currentTimeMillis() - start) / 1000.0+"；缓存数量："+systemCache.getLoadClass().size());
	}

	
}
