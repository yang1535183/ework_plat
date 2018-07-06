package com.diiwon.mis.plat.xtgl.user.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diiwon.mis.plat.base.orm.Page;
import com.diiwon.mis.plat.base.pojo.TMisJs;
import com.diiwon.mis.plat.base.pojo.TMisUser;
import com.diiwon.mis.plat.base.utils.Common;
import com.diiwon.mis.plat.base.utils.StringUtilsExt;
import com.diiwon.mis.plat.base.utils.SysCode;
import com.diiwon.mis.plat.xtgl.jsgl.dao.JsglDao;
import com.diiwon.mis.plat.xtgl.user.dao.UserDao;

import net.sf.json.JSONObject;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private JsglDao jsglDao;	
	
	
	public JSONObject queryUserPageList(int page, int rows, TMisJs tMisJs, TMisUser user){
		Page<Map<String, String>> dataList = new Page<Map<String, String>>();
		dataList.setCurrentPageNo(page);
		dataList.setPageSize(rows);
		
		dataList = this.userDao.queryUserPageList(dataList, tMisJs, user);
		return Common.getDatagridJsonDate(dataList);
	}
	
	public Map<String, Object> saveUser(TMisUser user, String jsid) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", false);
		result.put("data", "");
		
		try {
			List<TMisJs> roles = new ArrayList<TMisJs>();
			TMisJs role = this.jsglDao.getTMisJsUniqueBy("roleCode", jsid);
			roles.add(role);
			user.setRoles(roles);
			
			if(StringUtils.isBlank(user.getUuid())) {
				if(!this.getuser(user)) {
					result.put("data", "用户已存在，请选择其他用户!");
				}
				else {
					if(StringUtils.isBlank(user.getUuid())){
						user.setUuid(null);
						user.setLoginpass(StringUtilsExt.EncryptMD5(user.getLoginpass(), SysCode.MD5_LX_32));
					}
					
					user.setLoginpass(SysCode.USER_PWD_DEFAULT_ENCRYPT); 
					this.jsglDao.saveOrUpdate(user);
					
					result.put("success", true);
					result.put("data", "用户录入成功！");
				}
			}
			else {
				
				this.jsglDao.saveOrUpdate(user);
				result.put("success", true);
				result.put("data", "用户修改成功！");
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
			result.put("data", "用户录入失败！");
		}
		
		return result;
	}
	
	/**
	 * 判断t_mis_user中用户是否存在
	 * @param user
	 * @return
	 */
	public boolean getuser(TMisUser user) {
		TMisUser tUser = this.userDao.queryTuser(user.getLoginname());
		if(null == tUser) {
			return true;
		}
		return false;
	}
	
	/**
	 * 修改密码
	 * @param personuuid
	 * @return
	 */
	public Map<String, Object> updatePass(TMisUser user, String oldpass){
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", false);
		result.put("data", "");
		
		try {
			TMisUser personaccount = this.userDao.queryTuserByUuid(user.getUuid());
			 if (null != personaccount) {
				 String pass = personaccount.getLoginpass();
				 String md5OldPass = StringUtilsExt.EncryptMD5(oldpass, SysCode.MD5_LX_32);
				 if (pass.equals(md5OldPass)) {
					 personaccount.setLoginpass(StringUtilsExt.EncryptMD5(user.getLoginpass(), SysCode.MD5_LX_32));
					 this.userDao.updateLoginpass(personaccount.getLoginpass(), personaccount.getUuid());
					 
					 result.put("success", true);
					 result.put("data", "修改密码成功！");
				 }
				 else {
					 result.put("data", "原始密码错误，请重新输入！");
				 }
			 }
		}
		catch (Exception e) {
			e.printStackTrace();
			result.put("data", "修改密码失败！");
		}
		
		return result;
	}
	
	public Map<String, Object> delUser(String ryid) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", false);
		result.put("data", "");
		
		try {
			this.userDao.deleUser(ryid);
			result.put("success", true);
			result.put("data", "用户删除成功！");
		} 
		catch (Exception e) {
			e.printStackTrace();
			result.put("data", "出现异常，删除用户失败！");
		}
		
		return result;
	}
	
	public Map<String, Object> resetPass(String ryid) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", false);
		result.put("data", "");
		
		try {
			TMisUser user = this.userDao.queryTuserByUuid(ryid);
			user.setLoginpass(SysCode.USER_PWD_DEFAULT_ENCRYPT);
			
			this.jsglDao.saveOrUpdate(user);
			
			result.put("success", true);
			result.put("data", "密码重置成功！");
		} 
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.put("data", "出现异常，密码重置失败！");
		}
		
		return result;
	}

	/**   
	* @Function: UserService.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: Mr.Yang
	* @date: 2018年6月11日 上午10:33:47 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2018年6月11日     Mr.Yang           v1.0.0               修改原因
	*/
	public Map<String, Object> updatePwd(TMisUser tMisUser, String oldPwd, String newPwd) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", false);
		result.put("data", "");
		oldPwd=StringUtilsExt.EncryptMD5(oldPwd, SysCode.MD5_LX_32);
		try {
			if (tMisUser.getLoginpass().equals(oldPwd)) {
				newPwd=StringUtilsExt.EncryptMD5(newPwd, SysCode.MD5_LX_32);
				this.userDao.updatePwd(tMisUser,newPwd);
				result.put("success", true);
				result.put("data", "密码修改成功!");
			}else {
				result.put("success", false);
				result.put("data", "原始密码错误!");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			result.put("data", "操作失败！");
		}
		return result;
	}
}
