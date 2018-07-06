package com.diiwon.mis.plat.xmgl.pojo;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.AccessType;

import com.diiwon.mis.plat.xmgl.comm.annotation.FieldInfo;

@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@javax.persistence.Entity
@javax.persistence.Table(name = "t_ext_impdata")
@AccessType("field")
public class TextImpdata {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@FieldInfo(desc = "主键", column = "id", isPK=true)
	private Long id; 
		
	@Column(name = " xmlx")
	@FieldInfo(desc = "项目类型", column = " xmlx")
	private String  xmlx; 
		
	@Column(name = "gcid")
	@FieldInfo(desc = "项目ID", column = "gcid")
	private String gcid; 
		
	@Column(name = "filepath")
	@FieldInfo(desc = "文件路径", column = "filepath")
	private String filepath; 
		
	@Column(name = "filename")
	@FieldInfo(desc = "文件名称", column = "filename")
	private String filename; 
		
	@Column(name = "gclb")
	@FieldInfo(desc = "工程类别", column = "gclb")
	private String gclb; 
		
	@Column(name = "scry")
	@FieldInfo(desc = "上传人员", column = "scry")
	private String scry; 
		
	@Column(name = "scsj")
	@FieldInfo(desc = "上传时间", column = "scsj")
	private String scsj;
	
	@Column(name = "is_del")
	@FieldInfo(desc = "重复上传：覆盖，0隐藏，1显示", column = "sjc")
	private String isdel;

	@Column(name = "sccz")
	@FieldInfo(desc = "审查操作：1为通过；0为不通过", column = "czzt")
	private String sccz;
	
	@Column(name = "scyj")
	@FieldInfo(desc = "审查不通过时填写的审查意见", column = "scyj")
	private String scyj;
	
	@Column(name = "cclx")
	@FieldInfo(desc = "存储类型：数据库存储=0，相对路径文件存储=1，绝对路径文件存储=2", column = "cclx")
	private String  cclx;
	
	@Column(name = "wjnr")
	@FieldInfo(desc = "文件内容：用于数据库直接存储文件", column = "wjnr")
	private Blob wjnr;
	
	@Column(name = "upty")
	@FieldInfo(desc = "上传接口类型", column = "upty")
	private String upty;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getXmlx() {
		return xmlx;
	}

	public void setXmlx(String xmlx) {
		this.xmlx = xmlx;
	}

	public String getGcid() {
		return gcid;
	}

	public void setGcid(String gcid) {
		this.gcid = gcid;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getGclb() {
		return gclb;
	}

	public void setGclb(String gclb) {
		this.gclb = gclb;
	}

	public String getScry() {
		return scry;
	}

	public void setScry(String scry) {
		this.scry = scry;
	}

	public String getScsj() {
		return scsj;
	}

	public void setScsj(String scsj) {
		this.scsj = scsj;
	}

	public String getIsdel() {
		return isdel;
	}

	public void setIsdel(String isdel) {
		this.isdel = isdel;
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

	public Blob getWjnr() {
		return wjnr;
	}

	public void setWjnr(Blob wjnr) {
		this.wjnr = wjnr;
	}

	public String getCclx() {
		return cclx;
	}

	public void setCclx(String cclx) {
		this.cclx = cclx;
	}

	public String getUpty() {
		return upty;
	}

	public void setUpty(String upty) {
		this.upty = upty;
	}

}
