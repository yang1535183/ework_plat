package com.diiwon.mis.plat.base.pojo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@javax.persistence.Entity
@javax.persistence.Table(name = "t_mis_js")
@AccessType("field")
public class TMisJs{
	public TMisJs(){
	
	}
	
	@Id
	@Column(name = "role_code")
	private String roleCode;
	/**
	 *
	 */
	@Column(name = "role_name", length = 500)
	private String roleName;
	/**
	 *
	 */
	@Column(name = "role_type", length = 50)
	private String roleType;
	/**
	 *
	 */
	@Column(name = "role_level", length = 50)
	private String roleLevel;
	/**
	 *
	 */
	@Column(name = "role_endow", length = 50)
	private String roleEndow;
	
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	/**
	 *
	 */
	public String getRoleName(){
		return this.roleName;
	}
	/**
	 *
	 *@param roleName
	 */
	public void setRoleName(String roleName){
		this.roleName=roleName;
	}
	
	/**
	 *
	 */
	public String getRoleType(){
		return this.roleType;
	}
	/**
	 *
	 *@param roleType
	 */
	public void setRoleType(String roleType){
		this.roleType=roleType;
	}
	
	/**
	 *
	 */
	public String getRoleLevel(){
		return this.roleLevel;
	}
	/**
	 *
	 *@param roleLevel
	 */
	public void setRoleLevel(String roleLevel){
		this.roleLevel=roleLevel;
	}
	
	/**
	 *
	 */
	public String getRoleEndow(){
		return this.roleEndow;
	}
	/**
	 *
	 *@param roleEndow
	 */
	public void setRoleEndow(String roleEndow){
		this.roleEndow=roleEndow;
	}
	
	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(name="t_mis_jsqx",joinColumns={@JoinColumn(name="role_code")},inverseJoinColumns={@JoinColumn(name="auth_code")})
	@Fetch(FetchMode.SUBSELECT)
	private List<TMisQx> authoritys;
	
	public List<TMisQx> getAuthoritys() {
		return authoritys;
	}
	
	public void setAuthoritys(List<TMisQx> authoritys) {
		this.authoritys = authoritys;
	}

}