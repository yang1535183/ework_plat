package com.diiwon.mis.plat.xmgl.pojo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.AccessType;

import com.diiwon.mis.plat.xmgl.comm.annotation.FieldInfo;


@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@javax.persistence.Entity
@javax.persistence.Table(name = "t_bae_dbsx")
@AccessType("field")
public class TbaeDbsx {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@FieldInfo(desc = "主键", column = "id", isPK=true)
	private Long id; 
		
	@Column(name = "gcid")
	@FieldInfo(desc = "工程编号", column = "gcid")
	private String gcid; 
		
	@Column(name = "scry")
	@FieldInfo(desc = "上传人员", column = "scry")
	private String scry; 
		
	@Column(name = "scsj")
	@FieldInfo(desc = "上传时间", column = "scsj")
	private String scsj; 
		
	@Column(name = "flag")
	@FieldInfo(desc = "状态（前台可读取：0）", column = "flag")
	private String flag; 
		
	@Column(name = "memo")
	@FieldInfo(desc = "信息", column = "memo")
	private String memo; 
		
	@Column(name = "xmlx")
	@FieldInfo(desc = "消息类型：四大工程，或者是合同备案", column = "xmlx")
	private String xmlx; 
		
	@Column(name = "qxjs")
	@FieldInfo(desc = "接收人员", column = "jsuser")
	private String qxjs;
	

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

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getXmlx() {
		return xmlx;
	}

	public void setXmlx(String xmlx) {
		this.xmlx = xmlx;
	}

	public String getQxjs() {
		return qxjs;
	}

	public void setQxjs(String qxjs) {
		this.qxjs = qxjs;
	}

	
}
