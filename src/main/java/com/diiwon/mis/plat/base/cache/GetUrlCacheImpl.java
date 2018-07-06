package com.diiwon.mis.plat.base.cache;

import java.util.HashMap;
import org.apache.log4j.Logger;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;

public class GetUrlCacheImpl implements SystemCacheService {
	protected final Logger logger = Logger.getLogger(this.getClass());

	/**
	 * 配置URL
	 */
	private static HashMap<String, String> CORE_URL = new HashMap<String, String>();

	/**
	 * 取URL的实际值，若没有，则返回null
	 * 
	 * @param key this.getClass().getSimpleName()+".MethodName"。不区分大小写
	 * @return
	 */
	public static String getUrl(String key) {
		if (StringUtils.isNotBlank(key)) {
			key = key.trim().toLowerCase();
			if (CORE_URL.containsKey(key)) {
				return CORE_URL.get(key);
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 */
	public synchronized void run() {
		this.update();
	}

	/*
	 * (non-Javadoc)
	 * 
	 */
	public synchronized void update() {
		CORE_URL.clear();
		InputStream ips = null;
		Properties coreurl = new Properties();
		try {
			// 先取系统核心配置
			ips = GetUrlCacheImpl.class .getResourceAsStream("/core_resources/url.properties");
			if (ips != null) {
				coreurl.load(ips);
				Enumeration<Object> enums = coreurl.keys();
				while (enums.hasMoreElements()) {
					Object nkey = enums.nextElement();
					String key = obj2str(nkey);
					if (key != null) {
						CORE_URL.put(String.valueOf(key).toLowerCase(), String.valueOf(coreurl.get(nkey)));
					}
					if (logger.isDebugEnabled()) {
						logger.debug("Core：key =" + key + ",value =" + coreurl.get(nkey));
					}
				}
				coreurl.clear();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (ips != null) {
				try {
					ips.close();
				}
				catch (Exception e) {
				}
			}
		}
	}

	private static String obj2str(Object obj) {
		if (obj != null) {
			String key = String.valueOf(obj);
			return key.trim().toLowerCase();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 */
	public String getCacheName() {
		return "读取URL配置缓存";
	}
}
