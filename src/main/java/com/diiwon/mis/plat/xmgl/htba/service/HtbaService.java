package com.diiwon.mis.plat.xmgl.htba.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diiwon.mis.plat.base.orm.Page;
import com.diiwon.mis.plat.base.pojo.TMisUser;
import com.diiwon.mis.plat.base.utils.Common;
import com.diiwon.mis.plat.base.utils.SysCode;
import com.diiwon.mis.plat.base.vojo.PageVo;
import com.diiwon.mis.plat.xmgl.htba.dao.HtbaDao;
import com.diiwon.mis.plat.xmgl.pojo.TextImpdata;

import net.sf.json.JSONObject;

@Service
public class HtbaService {

	@Autowired
	private HtbaDao htbaDao;

	public JSONObject jsonPagehtbaList(PageVo page, TMisUser user, String cjsj, String gcmc) {
		Page<Map<String, String>> dataList = new Page<Map<String, String>>();
		dataList.setCurrentPageNo(page.getPage());
		dataList.setPageSize(page.getRows());
		if (user.getRoles().size() != 0 && null != user) {
			dataList = this.htbaDao.jsonPagehtbaList(dataList, user, cjsj, gcmc);
		}

		return Common.getDatagridJsonDate(dataList);
	}

	public Map<String, Object> uploadcheck(HttpServletRequest request, HttpServletResponse response, 
			TMisUser user,  TextImpdata textImpdata, String pzmc) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("success", false);
		map.put("data", "");
		textImpdata.setScry(user.getLoginname());
		textImpdata.setUpty(pzmc);
		int tn = this.htbaDao.queryHtscwj(textImpdata);
		if(tn != 0) {
			map.put("data", "文件已存在，是否覆盖上传！！！");
		}
		else {
			map.put("success", true);
		}
		return map;
	}
	
	public List<?> htJsonList(TextImpdata textImpdata,String pzmc) {

		return this.htbaDao.htList(textImpdata, pzmc);
	}

	public Map<String, Object> ReportHtsbGcbj(TextImpdata textImpdata, TMisUser user) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("success", false);
		map.put("data", "");
		try {
			//查找是否存在合同附件
			textImpdata.setUpty(SysCode.UPLOAD_HTWJ);
			int ct = this.htbaDao.queryHtscwj(textImpdata);
			if(ct != 0) {
				this.htbaDao.updateImpSjzt(textImpdata);
				map.put("success", true);
				map.put("data", "请求成功！！！");
			}
			else {
				map.put("data", "请上传附件再操作！！！");
			}
		} catch (Exception e) {
			map.put("data", "系统拒绝请求：请求失败！！！");
			e.printStackTrace();
		}
		
		return map;
	}
}
