package com.diiwon.mis.plat.base.pojo;

import javax.persistence.Column;
import javax.persistence.Id;

import org.hibernate.annotations.AccessType;

@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@javax.persistence.Entity
@javax.persistence.Table(name = "t_mis_qx")
@AccessType("field")
public class TMisQx{
	public TMisQx(){
	
	}
	/**
	 *
	 */
	@Id
	@Column(name = "AUTH_CODE", length = 50)
	private String authCode;
	/**
	 *
	 */
	@Column(name = "AUTH_TYPE", length = 50)
	private String authType;
	/**
	 *
	 */
	@Column(name = "DISPLAY_NAME", length = 100)
	private String displayName;
	/**
	 *
	 */
	@Column(name = "AUTH_DESCRIBE", length = 500)
	private String authDescribe;
	/**
	 *
	 */
	@Column(name = "DIRECT_URL", length = 200)
	private String directUrl;
	/**
	 *
	 */
	@Column(name = "SORT_ORDER", length = 22)
	private Long sortOrder;
	/**
	 *
	 */
	@Column(name = "UP_AUTH_CODE", length = 64)
	private String upAuthCode;
	/**
	 *
	 */
	@Column(name = "ICON", length = 50)
	private String icon;
	
	@Column(name = "TAB_ICON", length = 50)
	private String tabIcon;
	/**
	 *0：显示，1：不显示
	 */
	@Column(name = "SFXS", length = 1)
	private String sfxs;
	
	/**
	 *0：显示，1：不显示
	 */
	@Column(name = "FLAG")
	private String flag;
	
	/**
	 *0：显示，1：不显示
	 */
	@Column(name = "COLOR_BK")
	private String color_bk;
	
	/**
	 *0：显示，1：不显示
	 */
	@Column(name = "TEXT_ICON")
	private String text_icon;
	
	/**
	 *0：显示，1：不显示
	 */
	@Column(name = "QXJS")
	private String qxjs;
	
	@Column(name = "XMLX")
	private String xmlx;
	
	/**
	 *
	 */
	public String getAuthCode(){
		return this.authCode;
	}
	/**
	 *
	 *@param authCode
	 */
	public void setAuthCode(String authCode){
		this.authCode=authCode;
	}
	
	/**
	 *
	 */
	public String getAuthType(){
		return this.authType;
	}
	/**
	 *
	 *@param authType
	 */
	public void setAuthType(String authType){
		this.authType=authType;
	}
	
	/**
	 *
	 */
	public String getDisplayName(){
		return this.displayName;
	}
	/**
	 *
	 *@param displayName
	 */
	public void setDisplayName(String displayName){
		this.displayName=displayName;
	}
	
	/**
	 *
	 */
	public String getAuthDescribe(){
		return this.authDescribe;
	}
	/**
	 *
	 *@param authDescribe
	 */
	public void setAuthDescribe(String authDescribe){
		this.authDescribe=authDescribe;
	}
	
	/**
	 *
	 */
	public String getDirectUrl(){
		return this.directUrl;
	}
	/**
	 *
	 *@param directUrl
	 */
	public void setDirectUrl(String directUrl){
		this.directUrl=directUrl;
	}
	
	/**
	 *
	 */
	public Long getSortOrder(){
		return this.sortOrder;
	}
	/**
	 *
	 *@param sortOrder
	 */
	public void setSortOrder(Long sortOrder){
		this.sortOrder=sortOrder;
	}
	
	/**
	 *
	 */
	public String getUpAuthCode(){
		return this.upAuthCode;
	}
	/**
	 *
	 *@param upAuthCode
	 */
	public void setUpAuthCode(String upAuthCode){
		this.upAuthCode=upAuthCode;
	}
	
	/**
	 *
	 */
	public String getIcon(){
		return this.icon;
	}
	/**
	 *
	 *@param icon
	 */
	public void setIcon(String icon){
		this.icon=icon;
	}
	
	/**
	 *0：显示，1：不显示
	 */
	public String getSfxs(){
		return this.sfxs;
	}
	/**
	 *0：显示，1：不显示
	 *@param sfxs
	 */
	public void setSfxs(String sfxs){
		this.sfxs=sfxs;
	}
	
	public String getTabIcon() {
		return tabIcon;
	}
	public void setTabIcon(String tabIcon) {
		this.tabIcon = tabIcon;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getColor_bk() {
		return color_bk;
	}
	public void setColor_bk(String color_bk) {
		this.color_bk = color_bk;
	}
	public String getText_icon() {
		return text_icon;
	}
	public void setText_icon(String text_icon) {
		this.text_icon = text_icon;
	}
	public String getQxjs() {
		return qxjs;
	}
	public void setQxjs(String qxjs) {
		this.qxjs = qxjs;
	}
	public String getXmlx() {
		return xmlx;
	}
	public void setXmlx(String xmlx) {
		this.xmlx = xmlx;
	}
	
}