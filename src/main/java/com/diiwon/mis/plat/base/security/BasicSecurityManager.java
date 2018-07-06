package com.diiwon.mis.plat.base.security;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import com.diiwon.mis.plat.base.pojo.TMisUser;

public interface BasicSecurityManager {
	/****sessionid-user **/
	HashMap<String, Object> BasicUser = new HashMap<String, Object>();
	/**存放userloginname-session信息指允许单个用户登录**/
	HashMap<String, Object> BasicSession = new HashMap<String, Object>();
	
	public void setCurrentUser(HttpSession session, TMisUser user, String pmid);

	public TMisUser getCurrentUser(HttpSession session);
	
	public boolean isTMisUser(String loginname);
	
	public boolean isSession(HttpSession session);

	public void invalidateUser(TMisUser user);
	
	public void invalidateSession(HttpSession session);

}
