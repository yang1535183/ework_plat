package com.diiwon.mis.plat.xtgl.qxgl.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.diiwon.mis.plat.base.pojo.TMisQx;
import com.diiwon.mis.plat.base.pojo.TMisUser;
import com.diiwon.mis.plat.base.utils.Common;
import com.diiwon.mis.plat.base.utils.DateUtils;
import com.diiwon.mis.plat.base.utils.StringUtilsExt;
import com.diiwon.mis.plat.xtgl.qxgl.dao.QxglDao;

import net.sf.json.JSONObject;

@Service
public class QxglService{
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private QxglDao qxglDao;
	
	@Autowired
	private TransactionTemplate transactionTemplate;
	
	/**
	 * 获取缓存数据：缓存中的MENU级权限数据
	 * @return
	 */
	public String getCacheQxMenuJson(String flag) {
		List<TMisQx> qxMenu = null;
		if (StringUtilsExt.equals(flag, "1")) {
			qxMenu = this.qxglDao.queryQxMenu();
		}
		else {
			qxMenu = this.qxglDao.querySecondMenu();
		}
		StringBuilder sb = new StringBuilder();
		if (null != qxMenu && !qxMenu.isEmpty()) {
			for (TMisQx qx : qxMenu) {
				if (sb != null && sb.length() > 0) {
					sb.append(",");
				}
				sb.append("{");
				sb.append("\"").append("authCode").append("\"");
				sb.append(":");
				sb.append("\"").append(qx.getAuthCode()).append("\",");
				sb.append("\"").append("displayName").append("\"");
				sb.append(":");
				sb.append("\"").append(qx.getDisplayName()).append("\"");
				sb.append("}");
			}
		}
		sb.insert(0, "{\"auth\":[");
		sb.append("]}");
		return sb.toString();
	}
	
	/**
	 * 获取全部数据：权限数据信息
	 * @return
	 */
	public JSONObject getTQxJsonAll(TMisUser user) {
		
		return Common.getJsonResult(true, this.qxglDao.queryQxglAll(user));
	}
	
	public List<TMisQx> queryQxAll(TMisUser user) {
		
		return this.qxglDao.queryQxAll(user);
	}

	public TMisQx getTQx(String authCode) {
		
		return this.qxglDao.getTQx("authCode", authCode);
	}
	
	/**
	 * 获取全部数据：权限数据信息
	 * @return
	 */
	public List<TMisQx> getTQxAll(String flag) {
		List<TMisQx> dataList = this.qxglDao.queryTQxList(flag);
		return dataList;
	}

	/**
	 * 执行删除：依据传入的AUTH_CODE删除权限对象
	 * @param authCode
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> doDropTQx(final String authCode) {
		// TODO 调用 transactionTemplate 的 execute 方法进行全局事务管理
		@SuppressWarnings("rawtypes")
		Map<String, Object> result = (Map<String, Object>)transactionTemplate.execute(new TransactionCallback() {
			public Map<String, Object> doInTransaction(TransactionStatus status) {
				Map<String, Object> result = new HashMap<String, Object>();
				result.put("success", false);
				result.put("data", "");
				
				try {
			//		qxglDao.dropTMisQx("authCode", authCode);
					qxglDao.delAuthRole(authCode);
					
					result.put("success", true);
					result.put("data", "权限删除成功！");
				}
				catch (Exception e) {
					if (logger.isDebugEnabled()) {
						logger.debug(e.getMessage());
					}
			
					status.setRollbackOnly();
					
					result.put("data", "权限删除失败！<br/>详细："+e.getMessage());
				}
				return result;
			}
		});
			
		return result;
	}

	public Map<String, Object> doSaveTQx(TMisQx entity) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", false);
		result.put("data", "");
		
		try {
			if (StringUtilsExt.isBlank(entity.getAuthCode())) {
				entity.setAuthCode(DateUtils.getDataName("A", true));
			}
			this.qxglDao.saveOrUpdate(entity);
	
			result.put("success", true);
			result.put("data", "权限保存成功！");
		}
		catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug(e.getMessage());
			}
			
			result.put("data", "权限保存失败！<br/>详细："+e.getMessage());
		}
		
		return result;
	}
}
