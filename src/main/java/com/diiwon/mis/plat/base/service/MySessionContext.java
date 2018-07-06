package com.diiwon.mis.plat.base.service;

import javax.servlet.http.HttpSession;

import com.diiwon.mis.plat.base.pojo.TMisUser;
import com.diiwon.mis.plat.base.security.BasicSecurityManager;

/***监控用户登录**/
public class MySessionContext {

    public synchronized static void AddSession(HttpSession session) {
        
    }

    public synchronized static void DelSession(HttpSession session) {
    	TMisUser user = null;
    	Object obj = BasicSecurityManager.BasicUser.get(session.getId());
		if (null != obj) {
			user = (TMisUser)obj;
			BasicSecurityManager.BasicSession.remove(user.getLoginname());
	    	BasicSecurityManager.BasicUser.remove(session.getId());
		}
    }


}
