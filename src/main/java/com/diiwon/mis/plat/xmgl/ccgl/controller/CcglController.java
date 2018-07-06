/**   
 * 功能描述：
 * @Package: com.diiwon.mis.plat.xmgl.ccgl.controller 
 * @author: Mr.Yang   
 * @date: 2018年6月13日 上午10:34:13 
 */
package com.diiwon.mis.plat.xmgl.ccgl.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.diiwon.mis.plat.base.pojo.TMisLcjl;
import com.diiwon.mis.plat.base.pojo.TMisUser;
import com.diiwon.mis.plat.base.utils.Common;
import com.diiwon.mis.plat.base.utils.EWorkController;
import com.diiwon.mis.plat.base.utils.StringUtilsExt;
import com.diiwon.mis.plat.base.utils.SysCode;
import com.diiwon.mis.plat.base.vojo.PageVo;
import com.diiwon.mis.plat.xmgl.ccgl.service.CcglService;
import com.diiwon.mis.plat.xmgl.gcgl.service.GcbjService;
import com.diiwon.mis.plat.xmgl.lcjk.service.LcjkService;
import com.diiwon.mis.plat.xmgl.pojo.TBaeCczj;
import com.diiwon.mis.plat.xmgl.pojo.TBaeCczjdw;

import net.sf.json.JSONObject;

/**
 * @ClassName: CcglController.java
 * @Description: 拆除工程
 *
 * @version: v1.0.0
 * @author: Mr.Yang
 * @date: 2018年6月13日 上午10:34:13
 *
 *        Modification History: Date Author Version Description
 *        ---------------------------------------------------------* 2018年6月13日
 *        Mr.Yang v1.0.0 修改原因
 */
@Controller
public class CcglController extends EWorkController {
	@Autowired
	private CcglService ccglService;
	
	@Autowired
	private GcbjService gcbjService;
	
	@Autowired
	private LcjkService lcjkService;

	// --------------------------------------------拆除开始
	@RequestMapping("/xmgl/ccgl/cCPage.do")
	public String cCPage() {
		return "/xmgl/gcgl/gccc/getCcList";
	}
	//审查
	@RequestMapping("/xmgl/ccgl/cCPageSC.do")
	public String cCSCPage() {
		return "/xmgl/gcgl/gccc/getCcListSC";
	}
	//审批
	@RequestMapping("/xmgl/ccgl/cCPageSP.do")
	public String cCSPPage() {
		return "/xmgl/gcgl/gccc/getCcListSP";
	}

	/**
	 * 拆除工程对应的datagrid数据
	 */
	@RequestMapping("/xmgl/ccgl/cCListDataGrid.do")
	public void cCListDataGrid(HttpSession session, HttpServletResponse response, PageVo page, 
			TBaeCczj tBaeCczj, String dmco) {
		TMisUser tMisUser = this.getframeService().goIn(session);
		JSONObject json = this.ccglService.CcJsonPageList(page, tBaeCczj, tMisUser, dmco);
		this.sendDirectToClient(response, json.toString());
	}

	@RequestMapping("/xmgl/ccgl/addCc.do")
	public String addCc(HttpServletResponse response, Model model, 
			String id, String xmlx,String step) {
		TBaeCczj tBaecczj = new TBaeCczj();
		tBaecczj.setXmlx(xmlx);
		tBaecczj.setSjzt(SysCode.SJZT_DEFAULT);
	
		if (StringUtilsExt.isNotBlank(id)) {
			tBaecczj = this.ccglService.findTBaeCczj(id);
			String[] _str = new String[1];
			String[] xcaqyArr = (tBaecczj.gettBaeCczjdw().getCcxcaqy().replaceAll(",", "") == "") ? _str : 
				tBaecczj.gettBaeCczjdw().getCcxcaqy().split(",");
			String[] khzhArr = (tBaecczj.gettBaeCczjdw().getAqkhzh().replaceAll(",", "") == "") ? _str : 
				tBaecczj.gettBaeCczjdw().getAqkhzh().split(",");
			
			this.add(model, "xcaqyArr", xcaqyArr);
			this.add(model, "khzhArr", khzhArr);
			
			this.add(model, "gcid", tBaecczj.getId());
			this.add(model, "xmlx", tBaecczj.getXmlx());
			this.add(model, "sjzt", tBaecczj.getSjzt());
			this.add(model, "zcbm", tBaecczj.getZcbm());
			List<TMisLcjl> list=this.lcjkService.getAllLcjlByGcid(id, SysCode.SJZT_DEFAULT);//获取流程记录
			if(StringUtilsExt.equals(tBaecczj.getSjzt(), SysCode.SJZT_SCTH)) {//当状态为3 时，到后台取数据和前台进行对比
				this.add(model, "checkdxsc", Common.getJsonList(this.gcbjService.getCheckDxscsj(id)));
			}
			this.add(model, "ywLCList", list);
		}
			
		this.add(model, "step", step);
		this.add(model, "TBAECCZJ", tBaecczj);
		return "/xmgl/gcgl/addChc";
	}
	
	// 保存拆除From对象
	@RequestMapping("/xmgl/ccgl/saveCc.do")
	public void saveCc(HttpSession session, HttpServletResponse response, TBaeCczj tBaeCczj, TBaeCczjdw tBaeCczjdw,
			String ccmj) {
		TMisUser tMisUser = this.getframeService().goIn(session);
		JSONObject json = Common.getJsonResult(this.ccglService.saveCc(tMisUser, tBaeCczj, tBaeCczjdw));

		this.sendDirectToClient(response, json.toString());
	}
	
	//删除
	@RequestMapping("/xmgl/ccgl/delCc.do")
	public void delCc(HttpServletResponse response, String id, HttpSession session) {
		TMisUser tMisUser = this.getframeService().goIn(session);
		JSONObject json = Common.getJsonResult(this.ccglService.deleCcbj(tMisUser, id));
		
		this.sendDirectToClient(response, json.toString());
	}
	
	//上报
	@RequestMapping("/xmgl/ccgl/ccgcSb.do")
	public void reportGcbj(HttpServletResponse response, HttpSession session, TBaeCczj tBaeCczj) {
		TMisUser user = this.getframeService().goIn(session);
		JSONObject json = Common.getJsonResult(this.ccglService.ccgcSb(tBaeCczj, user));
		this.sendDirectToClient(response, json.toString());
	}
	// --------------------------------------------拆除结束
}
