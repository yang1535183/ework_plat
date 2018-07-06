package com.diiwon.mis.plat.base.cache.imp;

import java.util.List;

/**
 * 系统缓存加载类
 * @author Sunshine
 *
 */
public class SystemCache {

	/**
	 * 需要在系统启动时运行的SystemCacheService实现类
	 */
	private List<String> loadClass;

	public SystemCache() {

	}

	/**
	 * 要加载的BEAN ID名称
	 * 
	 * @param loadClass
	 */
	public void setLoadClass(List<String> loadClass) {
		this.loadClass = loadClass;
	}

	public List<String> getLoadClass() {
		return this.loadClass;
	}
}
