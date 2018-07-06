package com.diiwon.mis.plat.xtgl.qhgl.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.diiwon.mis.plat.base.pojo.TMisUser;
import com.diiwon.mis.plat.base.utils.EWorkController;
import com.diiwon.mis.plat.xtgl.qhgl.service.QhglService;

@Controller
public class QhglController extends EWorkController {

	@Autowired
	private QhglService qhglService;
	
	@RequestMapping("/xtgl/qhgl/qhtree.do")
	public String qhtree(HttpSession session, Model model, String flag) {
		TMisUser user = this.getframeService().goIn(session);
		this.add(model, "QHLIST", this.qhglService.findQhTree(user, flag));
		this.add(model, "FLAG", flag);
		return "/xtgl/rygl/qhtree";
	}
}
