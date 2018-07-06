package com.diiwon.mis.plat.xtgl.qxgl.controller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.diiwon.mis.plat.base.pojo.TMisQx;
import com.diiwon.mis.plat.base.pojo.TMisUser;
import com.diiwon.mis.plat.base.utils.Common;
import com.diiwon.mis.plat.base.utils.EWorkController;
import com.diiwon.mis.plat.frame.service.FrameService;
import com.diiwon.mis.plat.xtgl.qxgl.service.QxglService;

import net.sf.json.JSONObject;

@Controller
public class QxglController extends EWorkController {
	
	@Autowired
	private QxglService qxglService;
	
	@Autowired
	private FrameService frameService;

	/**
	 * 权限管理：权限管理主页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/xtgl/qxgl/qxglList.do")
	public String qxglList(Model model) {
		
		return "/xtgl/qxgl/qxglList";
	}
	
	/**
	 * 下拉列表数据：AJAX调用MENU级权限数据列表
	 * @param response
	 */
	@RequestMapping("/xtgl/qxgl/jsonQx.do")
	public void jsonQx(HttpServletResponse response, String flag) {
		String json = this.qxglService.getCacheQxMenuJson(flag);
		if (logger.isDebugEnabled()) {
			logger.debug(json);
		}
		this.sendDirectToClient(response, CONTENTTYPE_JSON, CHARSET_UTF8, json);
	}
	
	/**
	 * 全部数据：AJAX调用全部权限数据列表
	 * @param response
	 */
	@RequestMapping("/xtgl/qxgl/qxJsonAll.do")
	public void qxJsonAll(HttpSession session,HttpServletResponse response) {
		TMisUser user = this.frameService.goIn(session);
		JSONObject json = this.qxglService.getTQxJsonAll(user);
		if (logger.isDebugEnabled()) {
			logger.debug(json.toString());
		}
		this.sendDirectToClient(response, CONTENTTYPE_JSON, CHARSET_UTF8, json.toString());
	}
	
	/**
	 * 单个数据：AJAX加载单个权限数据,依据AUTH_CODE
	 * @param response
	 * @param authCode
	 */
	@RequestMapping("/xtgl/qxgl/getQxJson.do")
	public void getAuthorityJson(HttpServletResponse response, String authCode) {
		String json = Common.getJsonResult(true, this.qxglService.getTQx(authCode)).toString();
		if (logger.isDebugEnabled()) {
			logger.debug(json);
		}
		this.sendDirectToClient(response, json);
	}
	
	/**
	 * 删除操作：AJAX执行权限数据删除
	 * @param response
	 * @param authCode
	 */
	@RequestMapping("/xtgl/qxgl/doQxDrop.do")
	public void doAuthorityDrop(HttpServletResponse response, String authCode) {
		Map<String, Object> result = this.qxglService.doDropTQx(authCode);
		JSONObject json = Common.getJsonResult(result);
		this.sendDirectToClient(response, CONTENTTYPE_JSON, CHARSET_UTF8, json.toString());
	}
	
	/**
	 * 保存操作：AJAX执行权限数据保存
	 * @param response
	 * @param authority
	 */
	@RequestMapping("/xtgl/qxgl/doQxSave.do")
	public void doAuthoritySave(HttpServletResponse response, TMisQx qx) {
		Map<String, Object> result = this.qxglService.doSaveTQx(qx);
		JSONObject json = Common.getJsonResult(result);
		this.sendDirectToClient(response, CONTENTTYPE_JSON, CHARSET_UTF8, json.toString());
	}
}
 