package com.diiwon.mis.plat.xmgl.lcjk.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.diiwon.mis.plat.base.pojo.TMisLcjl;
import com.diiwon.mis.plat.base.utils.EWorkController;
import com.diiwon.mis.plat.base.utils.SysCode;
import com.diiwon.mis.plat.xmgl.lcjk.service.LcjkService;

/*****
 * 流程监控控制类
 * @author plp
 *
 */
@Controller
public class LcjkController extends EWorkController{
	
	@Autowired
	private LcjkService lcjkService;
	
	@RequestMapping(value="xmgl/lcgl/lcList.do")
	public String lcList(String gcid, HttpServletResponse response,Model model) {
		List<TMisLcjl> list=new ArrayList<TMisLcjl>();
		list=this.lcjkService.getAllLcjlByGcid(gcid, SysCode.SJZT_DEFAULT);
		this.add(model, "list", list);
		return "xmgl/gcgl/gclc/gclc";
	}
}
