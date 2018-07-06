package com.diiwon.mis.plat.xtgl.regist.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.diiwon.mis.plat.base.pojo.TMisUser;
import com.diiwon.mis.plat.base.utils.Common;
import com.diiwon.mis.plat.base.utils.EWorkController;
import com.diiwon.mis.plat.xtgl.regist.service.RegistService;

import net.sf.json.JSONObject;

@Controller
public class RegistController extends EWorkController{
	
	@Autowired
	private RegistService registService;
	
	@RequestMapping("/regist.do")
	public String regist(Model model) {
		this.add(model, "bcmger", this.getBasicCodeManager());
		
		return "/xtgl/regist/regist";
	}
	
	/***用户注册基本信息校验**/
	@RequestMapping("/cheakRegist.do")
	public void cheakRegist(HttpServletResponse response, HttpSession session, TMisUser user) {
		JSONObject json = Common.getJsonResult(this.registService.cheakRegist(user, session));
		
		this.sendDirectToClient(response, CONTENTTYPE_JSON, CHARSET_UTF8, json.toString());
	}
	
	@RequestMapping("/registerUser.do")
	public void registUser(HttpServletResponse response, TMisUser user, HttpSession session,
			String code, String agree, String identify) {
		JSONObject json = Common.getJsonResult(this.registService.registerUser(session, user, code, agree, identify));
		this.sendDirectToClient(response, CONTENTTYPE_JSON, CHARSET_UTF8, json.toString());
	}
}
