package com.diiwon.mis.plat.xmgl.message.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.diiwon.mis.plat.base.pojo.TMisUser;
import com.diiwon.mis.plat.base.utils.Common;
import com.diiwon.mis.plat.base.utils.EWorkController;
import com.diiwon.mis.plat.xmgl.message.service.MsgcService;

/**系统信息**/
@Controller
public class MsgeController extends EWorkController{
	
	@Autowired
	private MsgcService msgeService;
	
	@RequestMapping("/xmgl/gcgl/msgc/userQueryMsgc.do")
	public void userQueryMsgc(HttpSession session, HttpServletResponse response) {
		TMisUser user = this.getframeService().goIn(session);
		
		this.sendDirectToClient(response, Common.getJsonList(this.msgeService.userQueryMsgc(user)).toString());
	}
}
