package com.diiwon.mis.plat.xtgl.jsgl.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.diiwon.mis.plat.base.orm.Page;
import com.diiwon.mis.plat.base.pojo.TMisJs;
import com.diiwon.mis.plat.base.pojo.TMisQx;
import com.diiwon.mis.plat.base.pojo.TMisUser;
import com.diiwon.mis.plat.base.utils.Common;
import com.diiwon.mis.plat.base.utils.DateUtils;
import com.diiwon.mis.plat.base.utils.SysCode;
import com.diiwon.mis.plat.xtgl.jsgl.dao.JsglDao;

import net.sf.json.JSONObject;

@Service
public class JsglService {

	@Autowired
	public JsglDao jsglDao;
	
	@Autowired
	private TransactionTemplate transactionTemplate;
	
	public JSONObject getTmisjsPage(int page, int rows) {
		Page<TMisJs> dataList = new Page<TMisJs>();
		dataList.setCurrentPageNo(page);
		dataList.setPageSize(rows);
		
		return Common.getDatagridJsonDate(this.jsglDao.queryTmisjsPage(dataList));
	}
	
	public Map<String, Object> doSaveTMisJs(TMisJs js, String authCodes, TMisUser user) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", false);
		result.put("data", "");
		
		try {
			if(StringUtils.isBlank(js.getRoleCode())){
				js.setRoleCode(DateUtils.getDataName("R", true));
				js.setRoleType(SysCode.ROLE_TYPE_CUSTOM); //定制角色
				js.setRoleEndow(SysCode.ROLE_TYPE_UNENDOW); //可授权
			}
			String[] codes = authCodes.split(",");
			List<TMisQx> qxs = new ArrayList<TMisQx>();
			for (String authCode : codes) {
				TMisQx qx = this.jsglDao.getTMisQxUniqueBy("authCode", authCode);
				qxs.add(qx);
			}
			js.setAuthoritys(qxs);
			this.jsglDao.saveOrUpdate(js);
			
			result.put("success", true);
			result.put("data", "角色录入成功！");
		}
		catch (Exception e) {
			e.printStackTrace();
			result.put("data", "角色录入失败！");
		}
		
		return result;
	}
	
	/**
	 * 执行删除：角色对象数据,依据传入的UUID
	 * @param ids
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> dropJs(final String roleCode) {
		// TODO 调用 transactionTemplate 的 execute 方法进行全局事务管理
		Map<String, Object> result = (Map<String, Object>)transactionTemplate.execute(new TransactionCallback() {
			public Map<String, Object> doInTransaction(TransactionStatus status) {
				Map<String, Object> result = new HashMap<String, Object>();
				result.put("success", false);
				result.put("data", "");
				
				try {
					jsglDao.delJsqx(roleCode);
					jsglDao.delJs(roleCode);
					
					result.put("success", true);
					result.put("data", "角色删除成功！");
				}
				catch (Exception e) {
					e.printStackTrace();
					result.put("data", "角色删除失败！");
				}
				
				return result;
			}
		});
			
		return result;
	}

	public JSONObject jsonRyByjs(int page, int rows, String role_code) {
		Page<Map<String, String>> dataList = new Page<Map<String, String>>();
		dataList.setCurrentPageNo(page);
		dataList.setPageSize(rows);
		
		dataList = this.jsglDao.queryRyByjs(dataList, role_code);
		return Common.getDatagridJsonDate(dataList);
	}


}
