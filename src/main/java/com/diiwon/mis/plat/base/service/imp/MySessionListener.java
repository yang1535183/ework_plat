package com.diiwon.mis.plat.base.service.imp;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.diiwon.mis.plat.base.service.MySessionContext;

public class MySessionListener implements HttpSessionListener{

	@Override
	public void sessionCreated(HttpSessionEvent httpSessionEvent) {
		MySessionContext.AddSession(httpSessionEvent.getSession());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
		HttpSession session = httpSessionEvent.getSession();
        MySessionContext.DelSession(session);
	}

}
