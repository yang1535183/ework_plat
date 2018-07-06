package com.diiwon.mis.plat.base.vojo;

import org.apache.commons.lang.StringUtils;

/**
 * 用户登录后获取用户按钮权限
 * @author Sunshine
 *
 */
public class UserData {

	
	/**
	 * 页面按钮ID
	 */
	public String id;
	
	/**
	 * 图标 EASYUI:中iconCls的值
	 */
	public String cls;
	
	/**
	 * 图标名称
	 */
	public String tpmc;
	
	/**
	 * 状态 [1:启用；0:禁用]
	 */
	public String state;
	
	/**
	 * 是否显示icon图片[0:启用； 1:禁用]
	 */
	public String icon;
	
	/**
	 * 是否隐藏 1：启用；0：隐藏
	 */
	public String hidden;

	/**
	 * 角色ID
	 * @return
	 */
	public String jsid;
	
	/**
	 * 权限ID
	 */
	public String authcode;
	
	public boolean getState() {
		if(StringUtils.equals(state, "1")) {//图标启用 false
			return false;
		}
		return true; //禁用true
	}

	public void setState(String state) {
		this.state = state;
	}

	public boolean getIcon() {
		if(StringUtils.equals(icon, "1")) {//是否显示icon图片[1:启用；]
			return true;
		}
		return false;	// 0:不显示图片
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public boolean getHidden() {
		if(StringUtils.equals(hidden, "1")) {//图标启用 true
			return true;
		}

		return false;	// false 禁用
	}

	public void setHidden(String hidden) {
		this.hidden = hidden;
	}

	public String getJsid() {
		return jsid;
	}

	public void setJsid(String jsid) {
		this.jsid = jsid;
	}

	public String getAuthcode() {
		return authcode;
	}

	public void setAuthcode(String authcode) {
		this.authcode = authcode;
	}

	public String getCls() {
		return cls;
	}

	public void setCls(String cls) {
		this.cls = cls;
	}

	public String getTpmc() {
		return tpmc;
	}

	public void setTpmc(String tpmc) {
		this.tpmc = tpmc;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
