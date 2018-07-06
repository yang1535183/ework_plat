package com.diiwon.mis.plat.xmgl.htba.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.diiwon.mis.plat.base.pojo.TMisUser;
import com.diiwon.mis.plat.base.utils.Common;
import com.diiwon.mis.plat.base.utils.EWorkController;
import com.diiwon.mis.plat.base.vojo.PageVo;
import com.diiwon.mis.plat.xmgl.htba.service.HtbaService;
import com.diiwon.mis.plat.xmgl.pojo.TextImpdata;

import net.sf.json.JSONObject;

@Controller
public class HtbaController extends EWorkController {

	@Autowired
	private HtbaService htbaService;
	
	@RequestMapping("/xmgl/htba/htsc/htsclist.do")
	public String htsclist(Model model) {
		
//		this.add(model, "htlx", UserProperties.getLISRZD_CACHE().get(SysCode.SYS_HTLX));
		return "/xmgl/htba/htsc/htscList";
	}
	
	/**通过用户条件获取当前可上传合同数据**/
	@RequestMapping("/xmgl/htba/htsc/jsonPagehtbaList.do")
	public void jsonPagehtbaList(HttpSession session, HttpServletResponse response, 
			PageVo page, String cjsj, String gcmc) {
		
		TMisUser tMisUser = this.getframeService().goIn(session);
		JSONObject json = this.htbaService.jsonPagehtbaList(page, tMisUser, cjsj, gcmc);
		this.sendDirectToClient(response, json.toString());
	}
	
	/***合同上传校验****/
	@RequestMapping("/xmgl/htba/htsc/uploadcheck.do")
	public void uploadcheck(HttpSession session, HttpServletRequest request, 
			HttpServletResponse response, TextImpdata textImpdata, String pzmc) {
		TMisUser user = this.getframeService().goIn(session);
		JSONObject json = Common.getJsonResult(this.htbaService.uploadcheck(request, response, user, textImpdata, pzmc));
		this.sendDirectToClient(response, json.toString());
	}
	
	/***根据ID 加载合同数据**/
	@RequestMapping("/xmgl/gcgl/gcsc/htShow.do")
	public void htShow(HttpServletResponse response, TextImpdata textImpdata, String pzmc) {
		JSONObject json = Common.getDatagridJsonList(this.htbaService.htJsonList(textImpdata, pzmc));
		this.sendDirectToClient(response, json.toString());
	}
	
	/***根据id上报合同****/
	@RequestMapping("/xmgl/gcgl/gcsc/ReportHtsbGcbj.do")
	public void ReportHtsbGcbj(HttpSession session, HttpServletResponse response, TextImpdata textImpdata) {
		
		TMisUser user = this.getframeService().goIn(session);
		JSONObject json = Common.getJsonResult(this.htbaService.ReportHtsbGcbj(textImpdata, user));
		this.sendDirectToClient(response, json.toString());
	}
	
}
