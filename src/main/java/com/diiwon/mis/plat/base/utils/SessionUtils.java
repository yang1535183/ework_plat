package com.diiwon.mis.plat.base.utils;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.diiwon.mis.plat.base.pojo.TMisUser;


/**
 * 
 * Cookie 工具类
 *
 */
public final class SessionUtils {
	
	protected static final Logger logger = Logger.getLogger(SessionUtils.class);
	
	private static final String SESSION_USER = "session_user";
	
	private static final String SESSION_PERMISSION = "session_permission";

	private static final String SESSION_VALIDATECODE = "session_validatecode";//验证码
	
	/**
	  * 设置session的值
	  * @param request
	  * @param key
	  * @param value
	  */
	 public static void setAttr(HttpServletRequest request,String key,Object value){
		 request.getSession(true).setAttribute(key, value);
	 }
	 
	 
	 /**
	  * 获取session的值
	  * @param request
	  * @param key
	  * @param value
	  */
	 public static Object getAttr(HttpServletRequest request,String key){
		 return request.getSession(true).getAttribute(key);
	 }
	 
	 /**
	  * 删除Session值
	  * @param request
	  * @param key
	  */
	 public static void removeAttr(HttpServletRequest request,String key){
		 request.getSession(true).removeAttribute(key);
	 }
	 
	 /**
	  * 设置用户信息 到session
	  * @param request
	  * @param user
	  */
	 public static void setUser(HttpServletRequest request, TMisUser user){
		 request.getSession(true).setAttribute(SESSION_USER, user);
	 }
	 
	 
	 /**
	  * 从session中获取用户信息
	  * @param request
	  * @return SysUser
	  */
	 public static TMisUser getUser(HttpServletRequest request){
		return (TMisUser)request.getSession(true).getAttribute(SESSION_USER);
	 }
	 
	 /**
	  * 从session中获取用户信息
	  * @param request
	  * @return TmisUser
	  */
	 public static void removeUser(HttpServletRequest request){
		removeAttr(request, SESSION_USER);
	 }
	 
	 /**
	  * 设置验证码 到session
	  * @param request
	  * @param user
	  */
	 public static void setValidateCode(HttpServletRequest request,String validateCode){
		 request.getSession(true).setAttribute(SESSION_VALIDATECODE, validateCode);
	 }
	 
	 /**
	  * 从session中获取验证码
	  * @param request
	  * @return TmisUser
	  */
	 public static String getValidateCode(HttpServletRequest request){
		return (String)request.getSession(true).getAttribute(SESSION_VALIDATECODE);
	 }
	 
	 /**
	  * 从session中获删除验证码
	  * @param request
	  * @return TmisUser
	  */
	 public static void removeValidateCode(HttpServletRequest request){
		removeAttr(request, SESSION_VALIDATECODE);
	 }
	
	 /**
	  * 设置用户信息 到session
	  * @param request
	  * @param user
	  */
	 public static void setPermission(HttpServletRequest request, List<Map<String, Object>> permissionlist){
		 request.getSession(true).setAttribute(SESSION_PERMISSION, permissionlist);
	 }
	 
	 /**
	  * 从session中获取用户信息
	  * @param request
	  * @return SysUser
	  */
	 @SuppressWarnings("unchecked")
	public static List<Map<String, Object>> getPermission(HttpServletRequest request){
		return (List<Map<String, Object>>)request.getSession(true).getAttribute(SESSION_PERMISSION);
	 }
}