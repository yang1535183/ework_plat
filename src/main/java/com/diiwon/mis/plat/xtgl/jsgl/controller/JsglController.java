package com.diiwon.mis.plat.xtgl.jsgl.controller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.diiwon.mis.plat.base.pojo.TMisJs;
import com.diiwon.mis.plat.base.pojo.TMisUser;
import com.diiwon.mis.plat.base.utils.Common;
import com.diiwon.mis.plat.base.utils.EWorkController;
import com.diiwon.mis.plat.xtgl.jsgl.service.JsglService;

import net.sf.json.JSONObject;

/**
 * 角色基础类
 * @author alps880@foxmail.com
 *
 */
@Controller
public class JsglController extends EWorkController {

	@Autowired
	private JsglService jsglService;
	
	@RequestMapping("/xtgl/jsgl/jsglList.do")
	public String jsgllist(Model model) {
		return "/xtgl/jsgl/jsglList";
	}
	
	@RequestMapping("/xtgl/jsgl/jsonTmisjsPageList.do")
	public void jsonBtxmwhPageList(HttpSession session, HttpServletResponse response, int page, int rows) {
		JSONObject json = this.jsglService.getTmisjsPage(page, rows);
		if(logger.isDebugEnabled()){
			logger.debug(json.toString());
		}
		this.sendDirectToClient(response, json.toString());
	}
	
	/**
	 * 保存操作：AJAX执行角色数据保存
	 * @param response
	 * @param role
	 * @param authCodes
	 */
	@RequestMapping("/xtgl/jsgl/doJsSave.do")
	public void doJsSave(HttpSession session, HttpServletResponse response, TMisJs js, String authCodes) {
		TMisUser user = this.getframeService().goIn(session);
		Map<String, Object> result = this.jsglService.doSaveTMisJs(js, authCodes, user);
		JSONObject json = Common.getJsonResult(result);
		this.sendDirectToClient(response, json.toString());
	}
	
	/**
	 * 删除操作：AJAX执行角色删除
	 * @param response
	 * @param ids
	 */
	@RequestMapping("/xtgl/jsgl/doDropJs.do")
	public void doDropJs(HttpServletResponse response, TMisJs js) {
		Map<String, Object> result = this.jsglService.dropJs(js.getRoleCode());
		JSONObject json = Common.getJsonResult(result);
		this.sendDirectToClient(response, json.toString());
	}
	
	@RequestMapping("/xtgl/jsgl/jsonRyByjs.do")
	public void jsonRyByjs(HttpSession session, HttpServletResponse response, int page, int rows, String role_code){
		JSONObject json = this.jsglService.jsonRyByjs(page, rows, role_code);
		if (logger.isDebugEnabled()) {
			logger.debug(json.toString());
		}
		
		this.sendDirectToClient(response, json.toString());
	}
}
