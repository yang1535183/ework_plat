package com.diiwon.mis.plat.base.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.Assert;

import com.diiwon.mis.plat.base.cache.GetUrlCacheImpl;
import com.diiwon.mis.plat.base.vojo.BasicCodeManager;
import com.diiwon.mis.plat.frame.service.FrameService;

import net.sf.json.JSONObject;


/**
 * Controller基类
 * 
 * @author sunshine
 *
 */
public abstract class EWorkController {
	
	@Autowired
	private FrameService frameService;
	
	@Autowired
	private BasicCodeManager basicCodeManager;
	
	protected final Logger logger = LoggerFactory.getLogger(EWorkController.class);
	
	public static final String CONTENTTYPE_HTML = "text/html";
	
	public static final String CONTENTTYPE_JSON = "text/json";
	
	public static final String CONTENTTYPE_XML = "text/xml";
	
	public static final String CHARSET_GBK = "GBK";
	
	public static final String CHARSET_GB2312 = "GB2312";
	
	public static final String CHARSET_UTF8 = "UTF-8";
	
	public FrameService getframeService() {
		return frameService;
	}
	
	public BasicCodeManager getBasicCodeManager() {
		return basicCodeManager;
	}
	
	/**
	 * 向Model中设置参数
	 * @param model
	 * @param key
	 * @param value
	 */
	protected void add(Model model, String key, Object value) {
		model.addAttribute(key, value);
	}
	
	/**
	 * 获取页面的地址,以[当前类名.方法名]命名获取值，如：XxxxControll.XXMethod
	 * @param key
	 * @return
	 */
	protected String getUrl(String key) {
		return GetUrlCacheImpl.getUrl(key);
	}

	/*
	 * 将页面传递的一组ID转换成Long
	 */
	protected List<Long> toId(String ids) {
		List<Long> id = new ArrayList<Long>();
		String[] sItem = ids.split(",");
		for (String s : sItem) {
			id.add(Long.valueOf(s));
		}
		return id;
	}
	
	/**
	 * 将结果Map转换成json数据
	 * @param jsonMap
	 * @return
	 */
	@SuppressWarnings("unused")
	private JSONObject getResult(Map<String, Object> jsonMap) {
		return JSONObject.fromObject(jsonMap);
	}
	
//	/**
//	 * 获取页面的地址,以[当前类名.方法名]命名获取值，如：XxxxControll.XXMethod
//	 * @param key
//	 * @return
//	 */
//	protected String getUrl(String key) {
//		return GetUrlCacheImpl.getUrl(key);
//	}
	
	/**
	 * 直接通过<b>{@link HttpServletResponse}写数据到客户端</b>,可以指定编码,文件类型
	 * @param response {@link HttpServletResponse}
	 * @param contentType 文件类型
	 * @param charset 编码集
	 * @param s 数据
	 */
	public void sendDirectToClient(HttpServletResponse response, String contentType, String charset, String s) {
		Assert.notNull(response);
		
		String charsetPrefix = org.springframework.web.util.WebUtils.CONTENT_TYPE_CHARSET_PREFIX;
		String contentHead = contentType + charsetPrefix + charset;
		response.setContentType(contentHead);
		try {
			PrintWriter writer = response.getWriter();
			writer.write(s);
			writer.flush();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 直接通过<b>{@link HttpServletResponse}写数据到客户端</b>,可以指定编码,文件类型
	 * @param response {@link HttpServletResponse}
	 * @param contentType 文件类型
	 * @param charset 编码集
	 * @param s 数据
	 */
	public void sendDirectToClient(HttpServletResponse response, String s) {
		Assert.notNull(response);
		
		String charsetPrefix = org.springframework.web.util.WebUtils.CONTENT_TYPE_CHARSET_PREFIX;
		String contentHead = CONTENTTYPE_JSON + charsetPrefix + CHARSET_UTF8;
		response.setContentType(contentHead);
		try {
			PrintWriter writer = response.getWriter();
			writer.write(s);
			writer.flush();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
