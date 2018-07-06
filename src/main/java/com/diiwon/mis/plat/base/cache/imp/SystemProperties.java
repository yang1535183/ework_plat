package com.diiwon.mis.plat.base.cache.imp;

import java.io.InputStream;
import java.util.Properties;

/**
 * 系统文件属性
*      
* 类名称：SystemProperties   
* 类描述：     
* 创建时间：2013-5-24 下午02:08:53    
* 备注：   
* @version
 */
public class SystemProperties {

	private Properties prop = null;
	private static SystemProperties instance = null;

	private SystemProperties() {
		prop = new Properties();
		try {
			String filename = "/eprep.properties"; 
			InputStream ins = SystemProperties.class.getResourceAsStream(filename);
			prop.load(ins);
			ins.close();
			
		} catch (Exception e) {
			throw new RuntimeException("加载system.properties文件时发生异常", e);
		}
	}

	public synchronized static SystemProperties getInstance(){
		if (instance == null) {
			instance = new SystemProperties();
		}
		return instance;
	}

	public String getProperty(String key) {
		return prop.getProperty(key);
	}
	
}
