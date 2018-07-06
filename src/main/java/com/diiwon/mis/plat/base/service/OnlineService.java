package com.diiwon.mis.plat.base.service;

import javax.servlet.http.HttpSession;

import com.diiwon.mis.plat.base.pojo.TMisUser;
import com.diiwon.mis.plat.base.security.BasicSecurityManager;

public class OnlineService implements BasicSecurityManager {
	public void setCurrentUser(HttpSession session, TMisUser user, String pmid) {
		try {
			BasicUser.put(session.getId(), user);
			BasicSession.put(user.getLoginname(), session.getId());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/***判断session是否存在**/
	public boolean isSession(HttpSession session) {
		if(null != BasicUser && null != BasicUser.get(session.getId())) 
			return true;
		return false;
	}
	
	/**根据用户登录名判断用户session是否存在***/
	public boolean isTMisUser(String loginname) {
		if(null != BasicSession && null != BasicSession.get(loginname)) 
			return true;
		return false;
	}

	public TMisUser getCurrentUser(HttpSession session) {
		TMisUser user = null;
		if(null != BasicUser && BasicUser.containsKey(session.getId())) {
			Object obj = BasicUser.get(session.getId());
			if (null != obj) {
				user = (TMisUser)obj;
			}
		}

		return user;
	}

	public void invalidateSession(HttpSession session) {
		TMisUser user = getCurrentUser(session);
		if(BasicSession.containsKey(user.getLoginname())) {
			BasicSession.remove(user.getLoginname());
			BasicUser.remove(session.getId());
		}
		
	}

	public void invalidateUser(TMisUser user) {
		if(BasicSession.containsKey(user.getLoginname())) {
			BasicUser.remove(BasicSession.get(user.getLoginname()));
			BasicSession.remove(user.getLoginname());
		}
	}

}
