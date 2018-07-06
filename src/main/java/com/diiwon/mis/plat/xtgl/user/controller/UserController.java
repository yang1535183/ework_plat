package com.diiwon.mis.plat.xtgl.user.controller;

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
import com.diiwon.mis.plat.base.utils.StringUtilsExt;
import com.diiwon.mis.plat.xtgl.user.dao.UserDao;
import com.diiwon.mis.plat.xtgl.user.service.UserService;

import net.sf.json.JSONObject;

/**
 * 人员管理：控制跳转类
 * 
 * @author alps880@foxmail.com
 */
@Controller
public class UserController extends EWorkController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserDao userDao;

	@RequestMapping("/xtgl/rygl/userList.do")
	public String userlist() {
		return "/xtgl/rygl/userList";
	}

	@RequestMapping("/xtgl/rygl/jsonTMisUserPageList.do")
	public void jsonBtxmwhPageList(HttpSession session, HttpServletResponse response, int page, int rows,
			TMisJs tMisJs) {
		TMisUser user = getframeService().goIn(session);
		JSONObject json = this.userService.queryUserPageList(page, rows, tMisJs, user);
		if (logger.isDebugEnabled()) {
			logger.debug(json.toString());
		}
		this.sendDirectToClient(response, json.toString());
	}

	@RequestMapping("/xtgl/rygl/operUser.do")
	public String operUser(Model model, String ryid, String adname, String adcode) {
		this.add(model, "FLAG", "0");
		if (StringUtilsExt.isNotBlank(ryid)) {
			TMisUser tMisUser = this.userDao.queryTuserByUuid(ryid);
			this.add(model, "ADNAME", (adname == null || StringUtilsExt.equals(adname, "null")) ? "" : adname);
			this.add(model, "ADCODE", (adcode == null || StringUtilsExt.equals(adcode, "null")) ? "" : adcode);
			this.add(model, "ROLE", (tMisUser.getRoles().size() == 0 || tMisUser.getRoles() == null) ? ""
					: tMisUser.getRoles().get(0).getRoleCode());
			this.add(model, "USER", this.userDao.queryTuserByUuid(ryid));
			this.add(model, "FLAG", "1");
		} else {
			this.add(model, "ADNAME", this.getBasicCodeManager().getPlatAdname());
			this.add(model, "ADCODE", this.getBasicCodeManager().getPlatAdcode());
		}
		return "/xtgl/rygl/operUser";
	}

	/**
	 * 新增或修改人员
	 * 
	 * @param response
	 * @param user
	 */
	@RequestMapping("/xtgl/rygl/doSaveUser.do")
	public void doSaveUser(HttpServletResponse response, TMisUser user, String js) {
		JSONObject json = Common.getJsonResult(this.userService.saveUser(user, js));
		this.sendDirectToClient(response, CONTENTTYPE_JSON, CHARSET_UTF8, json.toString());
	}

	/**
	 * 修改密码
	 * 
	 * @param response
	 * @param personuuid
	 */
	@RequestMapping("/xtgl/rygl/updatePsd.do")
	public void updatePass(HttpServletResponse response, HttpSession session, TMisUser user, String oldpass) {
		Map<String, Object> result = this.userService.updatePass(user, oldpass);
		JSONObject json = Common.getJsonResult(result);
		this.sendDirectToClient(response, CONTENTTYPE_JSON, CHARSET_UTF8, json.toString());
	}

	/**
	 * 删除用户
	 * 
	 * @param response
	 * @param ryid
	 */
	@RequestMapping("/xtgl/rygl/delUser.do")
	public void delUser(HttpServletResponse response, String ryid) {
		JSONObject json = Common.getJsonResult(this.userService.delUser(ryid));
		this.sendDirectToClient(response, CONTENTTYPE_JSON, CHARSET_UTF8, json.toString());
	}

	/**
	 * 重置密码
	 * 
	 * @param response
	 * @param ryid
	 */
	@RequestMapping("/xtgl/rygl/resetPass.do")
	public void resetPass(HttpServletResponse response, String ryid) {
		JSONObject json = Common.getJsonResult(this.userService.resetPass(ryid));
		this.sendDirectToClient(response, CONTENTTYPE_JSON, CHARSET_UTF8, json.toString());
	}

	// --------------------------------修改密码开始
	@RequestMapping("/xtgl/rygl/updatePwdPage.do")
	public String updatePwdPage(HttpSession session, Model model) {
		TMisUser user = getframeService().goIn(session);
		this.add(model, "user", user);
		return "/xtgl/rygl/updatePwd";
	}

	@RequestMapping("/xtgl/rygl/updatePwd.do")
	public void updatePwd(HttpSession session, HttpServletResponse response, String oldPwd, String newPwd) {
		TMisUser tMisUser = this.getframeService().goIn(session);
		Map<String, Object> map = this.userService.updatePwd(tMisUser, oldPwd, newPwd);
		JSONObject json = Common.getJsonResult(map);
		this.sendDirectToClient(response, json.toString());
	}
	// --------------------------------修改密码结束

}
