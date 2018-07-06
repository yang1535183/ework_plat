package com.diiwon.mis.plat.frame.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diiwon.mis.plat.base.cache.imp.UserProperties;
import com.diiwon.mis.plat.base.pojo.TMisUser;
import com.diiwon.mis.plat.base.pojo.TMisUserRecord;
import com.diiwon.mis.plat.base.security.BasicSecurityManager;
import com.diiwon.mis.plat.base.utils.CusAccessObjectUtil;
import com.diiwon.mis.plat.base.utils.DateUtils;
import com.diiwon.mis.plat.base.utils.StringUtilsExt;
import com.diiwon.mis.plat.base.utils.SysCode;
import com.diiwon.mis.plat.frame.dao.FrameDao;
import com.diiwon.mis.plat.xmgl.pojo.TmisZd;
import com.diiwon.mis.plat.xtgl.qxgl.dao.QxglDao;

@Service
public class FrameService {
	@Autowired
	private FrameDao frameDao;
	
	@Autowired
	private  QxglDao qxglDao;
	
	@Autowired
	private BasicSecurityManager basicSecurityManager;
	
	/**
	 * 通过用户名验证
	 * @param request
	 * @param user
	 * @param request 
	 * @param response 
	 * @return
	 */
	public Map<String, Object> checkLogin(HttpSession session, TMisUser user, 
			HttpServletResponse response, HttpServletRequest request, String flag) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", false);
		result.put("cherk", false);
		
		try {
			user.setLoginpass(StringUtilsExt.EncryptMD5(user.getLoginpass(), SysCode.MD5_LX_32));
			TMisUser tUser = this.frameDao.findUnique(TMisUser.class, "loginname", user.getLoginname());

			if (null == tUser) {
				result.put("data", "当前登录用户不存在！");
			}
			else if (!StringUtilsExt.equals(user.getLoginpass(), tUser.getLoginpass())) {
				result.put("data", "登录密码错误！");
			}
			else if (StringUtilsExt.equals(tUser.getStatus(), "0")) {
				result.put("data", "当前用户已禁用，如果疑问请联系管理员！");
			}
			else {
				if(!this.basicSecurityManager.isSession(session) &&
						this.basicSecurityManager.isTMisUser(user.getLoginname())
						&& !StringUtilsExt.equals(flag, "check")) {
					result.put("cherk", true);
					result.put("data", "该账号已登录，是否切换当前客户端登录！");
				}
				else {
					/**用户清除session**/
					this.basicSecurityManager.invalidateUser(tUser);
					/**用户登录信息信息**/
					TMisUserRecord record = new TMisUserRecord();
					record.setUid(tUser.getUuid());
					record.setUsername(tUser.getUsername());
					record.setLoginmac(CusAccessObjectUtil.getMACAddress(request));
					record.setLoginip(CusAccessObjectUtil.getIpAddress(request));
					record.setLoginclient(CusAccessObjectUtil.getOsAndBrowserInfo(request));
					record.setLogintime(DateUtils.thisFormatDate(SysCode.RQGSH_HXGS_CRQ));
					this.frameDao.save(record);
					
					/**user-session信息**/
					tUser.settMisQx(this.qxglDao.queryQxAll(tUser));
					this.setSession(session, tUser, "pid");
					result.put("user", tUser);
					result.put("success", true);
				}
				
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
			result.put("data", "登录信息校验失败，请检查网络！");
		}
		
		return result;
	}
	
	/**
	 * 获得：当前数据库时间
	 * @return
	 */
	public String getDatabaseDateTime() {
		return this.frameDao.findDbDate();
	}
	
	public void setSession(HttpSession session, TMisUser user, String pmid) {
		this.basicSecurityManager.setCurrentUser(session, user, pmid);
	}
	
	/**
	 * 执行设置：当前登录用户
	 * @param session
	 * @return
	 */
	public TMisUser goIn(HttpSession session) {
		return this.basicSecurityManager.getCurrentUser(session);
	}
	
	public void invalidateMap(HttpSession session) {
		this.basicSecurityManager.invalidateSession(session);
	}

	public String StrMapZd(String parentid, String flag) {
		List<TmisZd> list = UserProperties.getLISRZD_CACHE().get(parentid);
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		if(StringUtilsExt.equals(flag, "1")) {
			sb.append("{");
			sb.append("'id':'-1',");
			sb.append("'text':'--请选择--',");
			sb.append("'selected': true");
			sb.append("}");
		}
		for(TmisZd str : list) {
			sb.append("{");
			sb.append("'id':'").append(str.getDmCode()).append("',");
			sb.append("'text':'").append(str.getDmName()).append("'");
			sb.append("}");
		}
		sb.append("]");
		sb = new StringBuilder(sb.toString().replace("}{", "},{"));
		return sb.toString();
	}
}
