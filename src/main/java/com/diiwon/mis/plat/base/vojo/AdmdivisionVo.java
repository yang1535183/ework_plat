package com.diiwon.mis.plat.base.vojo;

import javax.persistence.Column;
import javax.persistence.Id;

public class AdmdivisionVo {

	/**
	 * 主键
	 */
	@Id
	@Column(name = "UUID")
	private String uuid;
	/**
	 *区划编码
	 */
	@Column(name = "ADCODE", length = 20)
	private String adcode;
	/**
	 *区划名称
	 */
	@Column(name = "ADNAME", length = 20)
	private String adname;
	/**
	 *备注
	 */
	@Column(name = "MEMO", length = 100)
	private String memo;
	/**
	 *顺序
	 */
	@Column(name = "SEQUENCE", length = 10)
	private Long sequence;
	
	/**
	 *日期
	 */
	@Column(name = "MSDATE", length = 19)
	private String msdate;
	
	/**
	 *标注1：当前版本所属地区
	 */
	@Column(name = "TAG", length = 1)
	private String tag;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getAdcode() {
		return adcode;
	}

	public void setAdcode(String adcode) {
		this.adcode = adcode;
	}

	public String getAdname() {
		return adname;
	}

	public void setAdname(String adname) {
		this.adname = adname;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Long getSequence() {
		return sequence;
	}

	public void setSequence(Long sequence) {
		this.sequence = sequence;
	}

	public String getMsdate() {
		return msdate;
	}

	public void setMsdate(String msdate) {
		this.msdate = msdate;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
	
	
}
