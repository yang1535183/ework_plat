package com.diiwon.mis.plat.frame.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.diiwon.mis.plat.base.pojo.TMisQx;
import com.diiwon.mis.plat.base.pojo.TMisUser;
import com.diiwon.mis.plat.base.utils.Common;
import com.diiwon.mis.plat.base.utils.ComparatorAuthority;
import com.diiwon.mis.plat.base.utils.EWorkController;

import net.sf.json.JSONObject;



/**
 * 基础类
 * @author sunshine
 *
 */
@Controller
public class FrameController extends EWorkController {
	private String moudleName = "系统首页";

	
	/**
	 * cookie验证页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/login.do")
	public String loginMe() {
		return "index";
	}
	
	/**
	 * 登录：用户登录页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/checklogin.do")
	public String login(Model model) {
		
		this.add(model, "TITLE", this.getBasicCodeManager().getPlatTitle());
		return "/login";
	}
	
	/**
	 * 登录校验
	 * @param model
	 * @return
	 */
	@RequestMapping("/checkoutLogin.do")
	public void login(HttpSession session, HttpServletResponse response, TMisUser user, 
			HttpServletRequest request, String flag) {
		Map<String, Object> map = this.getframeService().checkLogin(session, user, response, request, flag);
		
		JSONObject json = Common.getJsonResult(map);
		this.sendDirectToClient(response, json.toString());
	}
	
	/**
	 * 登出：用户注销退出
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("/logout.do")
	public String logout(HttpSession session) {
	//	session.invalidate();
		this.getframeService().invalidateMap(session);
		return "redirect:/checklogin.do";
	}

	/**
	 * 平台默认页
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("/default.do")
	public String _default(HttpSession session, Model model) {
		TMisUser sessionUser = this.getframeService().goIn(session);
		if (null == sessionUser) {//用户是否已经登陆
			return "redirect:/login.do";
		}
		if(sessionUser.gettMisQx().size() == 0) {//是否有权限
			return "/main/noAuthority";
		}
		else {
			Long start = System.currentTimeMillis();
			List<TMisQx> authoritys = sessionUser.gettMisQx();
			ComparatorAuthority comparator = new ComparatorAuthority();
			Collections.sort(authoritys, comparator);	// ASC方式排序
			Collections.reverse(authoritys);			// DESC方式重排
			
			this.add(model, "bcmger", this.getBasicCodeManager());
			this.add(model, "user", sessionUser);
			this.add(model, "AUTHORITYS", authoritys);
			
			logger.info("{}打开{},后台耗时:{}秒", sessionUser.getUsername(), moudleName, (System.currentTimeMillis() - start) / 1000.0);
			return "default";
		}
	}
	
	/**
	 * 系统会话过期检查
	 * @param session
	 * @param response
	 */
	@RequestMapping("/checkoutSession.do")
	public void checkoutSession(HttpSession session, HttpServletResponse response, HttpServletRequest request) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("success", false);
		
		TMisUser user = this.getframeService().goIn(session);
		if (null != user) {
			jsonMap.put("success", true);
		}
		
		JSONObject json = Common.getJsonResult(jsonMap);
		this.sendDirectToClient(response, json.toString());
	}
	
	/****
	 * 跳转视频监控页面
	 * @param flag
	 * @return 
	 */
	@RequestMapping("/main/video.do")
	public String video(String flag, Model model) {
		this.add(model, "FLAG", flag);
		return "/main/video";
	}
	
}
