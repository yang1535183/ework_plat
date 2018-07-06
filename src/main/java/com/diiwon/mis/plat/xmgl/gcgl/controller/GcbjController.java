package com.diiwon.mis.plat.xmgl.gcgl.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.diiwon.mis.plat.base.pojo.TMisUser;
import com.diiwon.mis.plat.base.utils.Common;
import com.diiwon.mis.plat.base.utils.EWorkController;
import com.diiwon.mis.plat.base.utils.StringUtilsExt;
import com.diiwon.mis.plat.base.utils.SysCode;
import com.diiwon.mis.plat.base.vojo.PageVo;
import com.diiwon.mis.plat.xmgl.gcgl.service.GcbjService;
import com.diiwon.mis.plat.xmgl.lcjk.service.LcjkService;
import com.diiwon.mis.plat.xmgl.pojo.TBaeGczj;
import com.diiwon.mis.plat.xmgl.pojo.TBaeGczjdw;
import com.diiwon.mis.plat.xmgl.pojo.TBaeGczjsx;
import com.diiwon.mis.plat.xmgl.pojo.TextImpdata;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 工程管理：控制跳转类 质安报监控制类
 */
@Controller
public class GcbjController extends EWorkController {

	@Autowired
	private GcbjService gcbjService;

	@Autowired
	private LcjkService lcjkService;

	/** 进入工程报监信息展示页面 房建 **/
	@RequestMapping("/xmgl/gcgl/gclr/gcbjList.do")
	public String show() {
		return "/xmgl/gcgl/gclr/gcbjList";
	}

	/** 进入工程报监信息展示页面 市政建 **/
	@RequestMapping("/xmgl/gcgl/gclr/gcszList.do")
	public String gcszList() {
		return "/xmgl/gcgl/gclr/gcszList";
	}

	/** 进入工程报监信息展示页面 装饰建 **/
	@RequestMapping("/xmgl/gcgl/gclr/gczsList.do")
	public String gczsList() {
		return "/xmgl/gcgl/gclr/gczsList";
	}

	/***
	 * 查看市政，房建，二次装饰数据
	 * 
	 * @param session
	 * @param response
	 * @param page
	 * @param tBaeGczj
	 * @param flag://接口
	 */

	/** 展示企业已录入工程信息 **/
	@RequestMapping("/xmgl/gcgl/gclr/jsonPageList.do")
	public void jsonPageList(HttpSession session, HttpServletResponse response, PageVo page, 
			TBaeGczj tBaeGczj, String dmco) {
		TMisUser tMisUser = this.getframeService().goIn(session);
		JSONObject json  = this.gcbjService.jsonPageList(page, tBaeGczj, tMisUser, dmco);
		this.sendDirectToClient(response, json.toString());
	}

	/**
	 * 跳转录入工程信息页面
	 * 
	 * @return
	 */
	@RequestMapping("/xmgl/gcgl/addChc.do")
	public String queryChc(HttpServletResponse response, Model model, String flag) {
		boolean fg = false;
		if (StringUtilsExt.equals(flag, "2")) {
			fg = true;
		}
		this.add(model, "fg", fg);
		return "/xmgl/gcgl/addChc";
	}

	@RequestMapping("/xmgl/gcgl/gclr/jsonPagelcjlList.do")
	public void jsonPagelcjlList(HttpServletResponse response, TBaeGczj tBaeGczj) {

		JSONArray json = Common.getJsonList(this.gcbjService.jsonPagelcjlList(tBaeGczj));
		this.sendDirectToClient(response, json.toString());
	}

	/***
	 * 
	 * @param response
	 * @param model
	 * @param flag
	 *            对应按钮 1 企业新增， 2企业修改
	 * @param id
	 * @return
	 */
	@RequestMapping("/xmgl/gcgl/infoForm.do")
	public String queryInfoForm(HttpServletResponse response, Model model, 
			String id, String xmlx,String step) {
		TBaeGczj tBaeGczj = new TBaeGczj();
		tBaeGczj.setXmlx(xmlx);
		tBaeGczj.setSjzt(SysCode.SJZT_DEFAULT);

		if (StringUtilsExt.isNotBlank(id)) {
			tBaeGczj = this.gcbjService.findTBaeGczj(id);
			String[] _str = new String[0];
			String[] str = (tBaeGczj.gettBaeGczjdw().getSgxcaqy().replaceAll(",", "") == "") ? _str
					: tBaeGczj.gettBaeGczjdw().getSgxcaqy().split(",");
			String[] str1 = (tBaeGczj.gettBaeGczjdw().getSgaqyzs().replaceAll(",", "") == "") ? _str
					: tBaeGczj.gettBaeGczjdw().getSgaqyzs().split(",");

			for (int i = 0; i < 3; i++) {
				if (i < str.length) {
					this.add(model, "SGXCAQY" + i, str[i]);
				} else {
					this.add(model, "SGXCAQY" + i, "");
				}
				if (i < str1.length) {
					this.add(model, "SGAQYZS" + i, str1[i]);
				} else {
					this.add(model, "SGAQYZS" + i, "");  
				}
			}
			if(StringUtilsExt.equals(tBaeGczj.getSjzt(), SysCode.SJZT_SCTH)) {//当状态为3 时，到后台取数据和前台进行对比
				this.add(model, "checkdxsc", Common.getJsonList(this.gcbjService.getCheckDxscsj(id)));
			}
			
			this.add(model, "gcid", tBaeGczj.getId());
			this.add(model, "xmlx", tBaeGczj.getXmlx());
			this.add(model, "sjzt", tBaeGczj.getSjzt());
			this.add(model, "zcbm", tBaeGczj.getZcbm());
			this.add(model, "ywLCList", this.lcjkService.getAllLcjlByGcid(id, SysCode.SJZT_DEFAULT));//获取流程记录
		}

		this.add(model, "step", step);//当前所处环节
		this.add(model, "TBAEGCZJ", tBaeGczj);
		return "/xmgl/gcgl/infoForm";
	}
	
	

	/**
	 * 删除数据质安报监数据(刘)
	 * 
	 * @param response
	 * @param id
	 */
	@RequestMapping("/xmgl/gcgl/delGong.do")
	public void delGong(HttpServletResponse response, String id, HttpSession session) {
		TMisUser tMisUser = this.getframeService().goIn(session);
		JSONObject json = Common.getJsonResult(this.gcbjService.deleGcbj(tMisUser, id));

		this.sendDirectToClient(response, json.toString());
	}

	/****
	 * 保存
	 * 
	 * @param session
	 * @param response
	 * @param tBaeGczj
	 * @param tBaeGczjdw
	 * @param tBaeGczjsx
	 */
	@RequestMapping("/xmgl/gcgl/saveXmbj.do")
	public void saveXmbj(HttpSession session, HttpServletResponse response, TBaeGczj tBaeGczj, TBaeGczjdw tBaeGczjdw,
			TBaeGczjsx tBaeGczjsx) {
		TMisUser tMisUser = this.getframeService().goIn(session);
		JSONObject json = Common.getJsonResult(this.gcbjService.saveXmbj(tMisUser, tBaeGczj, tBaeGczjdw, tBaeGczjsx));

		this.sendDirectToClient(response, json.toString());
	}

	/*** 查询单一工程表数据 **/
	@RequestMapping("/xmgl/gcgl/dygridJsondata.do")
	public void dygridJsondata(HttpServletResponse response) {

		JSONArray json = Common.getJsonList(this.gcbjService.dygridJsondata());
		this.sendDirectToClient(response, json.toString());
	}

	/****
	 * 企业提报接口
	 * 
	 * @param response
	 * @param ids
	 */
	@RequestMapping("/xmgl/gcgl/reportGcbj.do")
	public void reportGcbj(HttpServletResponse response, HttpSession session, TBaeGczj tBaeGczj, 
			String flag) {
		TMisUser user = this.getframeService().goIn(session);
		JSONObject json = Common.getJsonResult(this.gcbjService.reportGcbj(tBaeGczj, user, flag));
		this.sendDirectToClient(response, json.toString());
	}

	// --------------------------------------------审查开始
	/**
	 * YW 跳转审查工程列表
	 */
	@RequestMapping("/xmgl/gcgl/gcsc/gcscListFJ.do")
	public String getSCGCListJsonFJ(String type, Model model) {
		return "/xmgl/gcgl/gcsc/gcscListFJ";
	}

	@RequestMapping("/xmgl/gcgl/gcsc/gcscListSZ.do")
	public String getSCGCListJsonSZ(String type, Model model) {
		return "/xmgl/gcgl/gcsc/gcscListSZ";
	}

	@RequestMapping("/xmgl/gcgl/gcsc/gcscListZS.do")
	public String getSCGCListJsonZS(String type, Model model) {
		return "/xmgl/gcgl/gcsc/gcscListZS";
	}

	// --------------------------------------------审查结束

	// --------------------------------------------审批开始
	@RequestMapping("/xmgl/gcgl/gcsp/gcspList.do")
	public String getSPList() {
		return "/xmgl/gcgl/gcsp/gcspList";
	}

	@RequestMapping("/xmgl/gcgl/gcsp/gcspListFJ.do")
	public String getSPListFJ() {
		return "/xmgl/gcgl/gcsp/gcspListFJ";
	}

	@RequestMapping("/xmgl/gcgl/gcsp/gcspListSZ.do")
	public String getSPListSZ() {
		return "/xmgl/gcgl/gcsp/gcspListSZ";
	}

	@RequestMapping("/xmgl/gcgl/gcsp/gcspListZS.do")
	public String getSPListZS() {
		return "/xmgl/gcgl/gcsp/gcspListZS";
	}
	// --------------------------------------------审批结束

	// ----------------------逐项审核 开始 -----
	/****
	 * datagrid 展示数据
	 * 
	 * @param response
	 * @param tBaeGczj
	 */
	@RequestMapping("/xmgl/gcgl/gcsc/zxshJsonList.do")
	public void zxshJsonList(HttpServletResponse response, TBaeGczj tBaeGczj) {
		JSONObject json = Common.getDatagridJsonList(this.gcbjService.zxshJsonList(tBaeGczj));
		this.sendDirectToClient(response, json.toString());
	}

	/** 根据单选按钮选中状态变化数据库状态值 **/
	@RequestMapping("/xmgl/gcgl/gcsc/zxscSave.do")
	public void zxscSave(HttpServletResponse response, String id, String vl, String tname) {

		JSONObject json = Common.getJsonResult(this.gcbjService.zxscSave(id, vl, tname));
		this.sendDirectToClient(response, json.toString());
	}

	@RequestMapping("/xmgl/gcgl/jsonUploadfile.do")
	public void jsonUploadfile(HttpServletResponse response, String gcid) {

		JSONArray json = Common.getJsonList(this.gcbjService.jsonUploadfile(gcid));
		this.sendDirectToClient(response, json.toString());
	}

	@RequestMapping("/xmgl/gcgl/zxscSaveSctj.do")
	public void zxscSaveSctj(HttpServletResponse response, String id, HttpSession session, String dmco) {
		TMisUser user = this.getframeService().goIn(session);
		JSONObject json = Common.getJsonResult(this.gcbjService.zxscSaveSctj(id, user, dmco));

		this.sendDirectToClient(response, json.toString());
	}

	@RequestMapping("/xmgl/datagrid/saveOnAfterEdit.do")
	public void saveOnAfterEdit(HttpServletResponse response, String column, String val, String id, String tname) {
		JSONObject json = Common.getJsonResult(this.gcbjService.saveOnAfterEdit(column, val, id, tname));

		this.sendDirectToClient(response, json.toString());
	}
	
	/**
	 * id:审批数据id
	 * dmco工程类型
	 * flag操作结果
	 * **/
	@RequestMapping("/xmgl/gcgl/reportGcxm.do")
	public void reportGcxm(HttpServletResponse response, String id, String dmco, 
			HttpSession session, String flag, String memo) {
		TMisUser user = this.getframeService().goIn(session);
		JSONObject json = Common.getJsonResult(this.gcbjService.reportGcxm(id, dmco, user, flag, memo));

		this.sendDirectToClient(response, json.toString());
	}
	
	@RequestMapping("/xmgl/gcgl/form/fjpage.do")
	public String ywfjPage(Model model, HttpSession session, String id, String gcmc) {
		TMisUser user = this.getframeService().goIn(session);
		List<TextImpdata> fjList=this.gcbjService.fjList(id);
		if (fjList.size()>0 && null != user) {
			this.add(model, "fjList", fjList);
			this.add(model, "gcmc", gcmc);
			this.add(model, "user", user.getUsername());
		}
		
		return "/xmgl/staticPage/imgShow/fileShow";
	}
}
