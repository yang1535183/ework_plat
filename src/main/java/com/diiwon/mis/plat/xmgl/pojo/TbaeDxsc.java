package com.diiwon.mis.plat.xmgl.pojo;

import javax.persistence.Column;
import javax.persistence.Id;

import org.hibernate.annotations.AccessType;

import com.diiwon.mis.plat.xmgl.comm.annotation.FieldInfo;

@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@javax.persistence.Entity
@javax.persistence.Table(name = "t_bae_dxsc")
@AccessType("field")
public class TbaeDxsc {

	@Id
	@Column(name = "id")
	@FieldInfo(desc = "id", column = "id")
	private Long id; 
		
	@Column(name = "gcid")
	@FieldInfo(desc = "项目编号", column = "gcid")
	private String gcid; 
		
	@Column(name = "sclm")
	@FieldInfo(desc = "获取数据值对应列【对应基础表数据列】", column = "sclm")
	private String sclm; 
		
	@Column(name = "scsj")
	@FieldInfo(desc = "具体的审查数据", column = "scsj")
	private String scsj; 
		
	@Column(name = "sccz")
	@FieldInfo(desc = "审查操作：1为通过；0为不通过", column = "sccz")
	private String sccz; 
		
	@Column(name = "scyj")
	@FieldInfo(desc = "审查不通过时填写的审查意见", column = "scyj")
	private String scyj; 
		
	@Column(name = "scrq")
	@FieldInfo(desc = "审查每一项的时间", column = "scrq")
	private String scrq; 
		
	@Column(name = "scr")
	@FieldInfo(desc = "审查人", column = "scr")
	private String scr; 
		
	@Column(name = "is_old")
	@FieldInfo(desc = "1:正常。0过期", column = "is_old")
	private java.lang.Integer is_old;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGcid() {
		return gcid;
	}

	public void setGcid(String gcid) {
		this.gcid = gcid;
	}

	public String getSclm() {
		return sclm;
	}

	public void setSclm(String sclm) {
		this.sclm = sclm;
	}

	public String getScsj() {
		return scsj;
	}

	public void setScsj(String scsj) {
		this.scsj = scsj;
	}

	public String getSccz() {
		return sccz;
	}

	public void setSccz(String sccz) {
		this.sccz = sccz;
	}

	public String getScyj() {
		return scyj;
	}

	public void setScyj(String scyj) {
		this.scyj = scyj;
	}

	public String getScrq() {
		return scrq;
	}

	public void setScrq(String scrq) {
		this.scrq = scrq;
	}

	public String getScr() {
		return scr;
	}

	public void setScr(String scr) {
		this.scr = scr;
	}

	public java.lang.Integer getIs_old() {
		return is_old;
	}

	public void setIs_old(java.lang.Integer is_old) {
		this.is_old = is_old;
	} 
	
}
