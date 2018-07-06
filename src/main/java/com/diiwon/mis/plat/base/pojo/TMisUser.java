package com.diiwon.mis.plat.base.pojo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import com.diiwon.mis.plat.base.utils.StringUtilsExt;

@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@javax.persistence.Entity
@javax.persistence.Table(name = "t_mis_user")
@AccessType("field")
public class TMisUser{
	public TMisUser(){
	
	}
	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	private String uuid;
	/**
	 *
	 */
	@Column(name = "USERNAME", length = 20)
	private String username;
	/**
	 *
	 */
	@Column(name = "LOGINNAME", length = 20)
	private String loginname;
	/**
	 *
	 */
	@Column(name = "LOGINPASS", length = 64)
	private String loginpass;
	/**
	 *
	 */
	@Column(name = "SEX", length = 1)
	private String sex;
	/**
	 *
	 */
	@Column(name = "CELLPHONE", length = 11)
	private String cellphone;
	/**
	 *
	 */
	@Column(name = "QQ", length = 20)
	private String qq;
	
	@Column(name = "QHDM", length = 12)
	private String qhdm;
	
	@Column(name = "STATUS", length= 1)
	private String status;
	
	@Column(name = "EMAIL", length = 100)
	private String email;
	
	/**
	 *
	 */
	public String getUuid(){
		return this.uuid;
	}
	/**
	 *
	 *@param uuid
	 */
	public void setUuid(String uuid){
		this.uuid=uuid;
	}
	
	/**
	 *
	 */
	public String getUsername(){
		return this.username;
	}
	/**
	 *
	 *@param username
	 */
	public void setUsername(String username){
		this.username=username;
	}
	
	/**
	 *
	 */
	public String getLoginname(){
		return this.loginname;
	}
	/**
	 *
	 *@param loginname
	 */
	public void setLoginname(String loginname){
		this.loginname=loginname;
	}
	
	/**
	 *
	 */
	public String getLoginpass(){
		return this.loginpass;
	}
	/**
	 *
	 *@param loginpass
	 */
	public void setLoginpass(String loginpass){
		this.loginpass=loginpass;
	}
	
	/**
	 *
	 */
	public String getSex(){
		return this.sex;
	}
	/**
	 *
	 *@param sex
	 */
	public void setSex(String sex){
		this.sex=sex;
	}
	
	/**
	 *
	 */
	public String getCellphone(){
		return this.cellphone;
	}
	/**
	 *
	 *@param cellphone
	 */
	public void setCellphone(String cellphone){
		this.cellphone=cellphone;
	}
	
	/**
	 *
	 */
	public String getQq(){
		return this.qq;
	}
	/**
	 *
	 *@param qq
	 */
	public void setQq(String qq){
		this.qq=qq;
	}
	
	public String getQhdm() {
		return qhdm;
	}
	
	public void setQhdm(String qhdm) {
		this.qhdm = qhdm;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	@ManyToMany(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	@JoinTable(name="t_mis_jsry",joinColumns={@JoinColumn(name="ryid")},inverseJoinColumns={@JoinColumn(name="jsid")})
	@Fetch(FetchMode.SUBSELECT)
	private List<TMisJs> roles;
	
	public List<TMisJs> getRoles() {
		return roles;
	}
	public void setRoles(List<TMisJs> roles) {
		this.roles = roles;
	}
	
	@Transient
	private List<TMisQx> tMisQx;
	
	public List<TMisQx> gettMisQx() {
		return tMisQx;
	}
	
	public void settMisQx(List<TMisQx> tMisQx) {
		List<String> tQxList = new ArrayList<>();
		List<String> lxList = new ArrayList<>();
		String qxsj = null;
		String xmlx = null;
		for(TMisQx TQx : tMisQx) {
			if(StringUtilsExt.isNotBlank(TQx.getQxjs()) && !StringUtilsExt.equals(TQx.getQxjs(), qxsj)) {
				tQxList.add(TQx.getQxjs());
				qxsj = TQx.getQxjs();
			}
			if(StringUtilsExt.isNotBlank(TQx.getXmlx()) && !StringUtilsExt.equals(TQx.getQxjs(), xmlx)) {
				lxList.add(TQx.getXmlx());
				xmlx = TQx.getXmlx();
			}
		}
		
		setQxsj(tQxList);
		setXmlx(lxList);
		this.tMisQx = tMisQx;
	}
	
	@Transient
	private List<String> qxsj ;

	public List<String> getQxsj() {
		return qxsj;
	}
	public void setQxsj(List<String> qxsj) {
		this.qxsj = qxsj;
	}
	
	@Transient
	private List<String> xmlx ;
	
	public List<String> getXmlx() {
		return xmlx;
	}
	public void setXmlx(List<String> xmlx) {
		this.xmlx = xmlx;
	}
}