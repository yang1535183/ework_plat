/**   
 * 功能描述：
 * @Package: com.diiwon.mis.plat.xtgl.jsgl.service 
 * @author: Mr.Yang   
 * @date: 2018年7月3日 上午9:45:15 
 */
package com.diiwon.mis.plat.xtgl.regist.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diiwon.mis.plat.base.pojo.TMisUser;
import com.diiwon.mis.plat.base.utils.StringUtilsExt;
import com.diiwon.mis.plat.base.utils.SysCode;
import com.diiwon.mis.plat.xtgl.regist.dao.RegistDao;

/**    
* @ClassName: RegistService.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: Mr.Yang
* @date: 2018年7月3日 上午9:45:15 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2018年7月3日     Mr.Yang           v1.0.0               修改原因
*/
@Service
public class RegistService {
	
	@Autowired
	private RegistDao registDao;
	
	public Map<String, Object> cheakRegist(TMisUser user, HttpSession session) {
		Map<String, Object> result = new  HashMap<String, Object>();
		result.put("success", true);
		result.put("data", "X");
		try {
			String verificode = session.getAttribute("verificode").toString();
			if(null == verificode) {
				result.put("success", false);
				result.put("data", "X-您不能使用该手机号。");
				return result;
			}
			if(StringUtilsExt.isNotBlank(user.getCellphone())) {
				if(null != this.registDao.findUnique(TMisUser.class, "cellphone", user.getCellphone())) {
					result.put("success", false);
					result.put("data", "X-该手机号已注册。");
				}
			}
			else if(StringUtilsExt.isNotBlank(user.getUsername())) {
				if(null != this.registDao.findUnique(TMisUser.class, "username", user.getUsername())) {
					result.put("success", false);
					result.put("data", "X-该企业用户已注册。");
				}
			}
		} catch (Exception e) {
			result.put("success", false);
			result.put("data", "X-您不能使用该手机号。");
		}
		
		return result;
	}
	
	/**
	 * 
	 * **/
	public Map<String, Object> registerUser(HttpSession session, TMisUser user, 
			String code, String agree, String identify) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", false);
		try {
			/***手机验证码***/
			
			
			/*******/
			
			user.setUuid(UUID.randomUUID().toString().replace("-", "").toLowerCase());
			user.setLoginname(String.valueOf(StringUtilsExt.genItemId()));
			user.setStatus(SysCode.USER_ISDISABLED_YES);
			user.setLoginpass(StringUtilsExt.EncryptMD5(user.getLoginpass(), SysCode.MD5_LX_32));
			this.registDao.save(user);
			result.put("phone", user.getCellphone());
			result.put("name", user.getUsername());
			result.put("success", true);
		} catch (Exception e) {
			result.put("data", "用户注册失败，请联系系统管理员！！！");
		}
		return result;
	}
	
}
