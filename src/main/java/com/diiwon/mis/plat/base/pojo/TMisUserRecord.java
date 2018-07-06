/**   
 * 功能描述：
 * @Package: com.diiwon.mis.plat.base.pojo 
 * @author: Mr.Yang   
 * @date: 2018年7月5日 上午9:39:05 
 */
package com.diiwon.mis.plat.base.pojo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.AccessType;

/**
 * @ClassName: TMisUserRecord.java
 * @Description: 该类的功能描述
 *
 * @version: v1.0.0
 * @author: Mr.Yang
 * @date: 2018年7月5日 上午9:39:05
 *
 *        Modification History: Date Author Version Description
 *        ---------------------------------------------------------* 2018年7月5日
 *        Mr.Yang v1.0.0 修改原因
 */
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@javax.persistence.Entity
@AccessType("field")
@javax.persistence.Table(name = "t_mis_userrecord")
public class TMisUserRecord {

	@Id
	@GeneratedValue
	private Integer id;

	@Column(name = "uid")
	private String uid;

	@Column(name = "username")
	private String username;

	@Column(name = "loginmac")
	private String loginmac;

	@Column(name = "loginip")
	private String loginip;

	@Column(name = "loginclient")
	private String loginclient;

	@Column(name = "logintime")
	private String logintime;

	@Column(name = "loginouttime")
	private String loginouttime;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the uid
	 */
	public String getUid() {
		return uid;
	}

	/**
	 * @param uid
	 *            the uid to set
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the loginmac
	 */
	public String getLoginmac() {
		return loginmac;
	}

	/**
	 * @param loginmac
	 *            the loginmac to set
	 */
	public void setLoginmac(String loginmac) {
		this.loginmac = loginmac;
	}

	/**
	 * @return the loginip
	 */
	public String getLoginip() {
		return loginip;
	}

	/**
	 * @param loginip
	 *            the loginip to set
	 */
	public void setLoginip(String loginip) {
		this.loginip = loginip;
	}

	/**
	 * @return the loginclient
	 */
	public String getLoginclient() {
		return loginclient;
	}

	/**
	 * @param loginclient
	 *            the loginclient to set
	 */
	public void setLoginclient(String loginclient) {
		this.loginclient = loginclient;
	}

	/**
	 * @return the logintime
	 */
	public String getLogintime() {
		return logintime;
	}

	/**
	 * @param logintime
	 *            the logintime to set
	 */
	public void setLogintime(String logintime) {
		this.logintime = logintime;
	}

	/**
	 * @return the loginouttime
	 */
	public String getLoginouttime() {
		return loginouttime;
	}

	/**
	 * @param loginouttime
	 *            the loginouttime to set
	 */
	public void setLoginouttime(String loginouttime) {
		this.loginouttime = loginouttime;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TMisUserRecord [id=" + id + ", uid=" + uid + ", username=" + username + ", loginmac=" + loginmac
				+ ", loginip=" + loginip + ", loginclient=" + loginclient + ", logintime=" + logintime
				+ ", loginouttime=" + loginouttime + "]";
	}

}
