package com.diiwon.mis.plat.xmgl.pojo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.AccessType;

import com.diiwon.mis.plat.xmgl.comm.annotation.FieldInfo;


@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@javax.persistence.Entity
@javax.persistence.Table(name = "t_mis_zd")
@AccessType("field")
public class TmisZd {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@FieldInfo(desc = "主键", column = "id", isPK=true)
	private Long id; 
		
	@Column(name = "dm_code")
	@FieldInfo(desc = "编码", column = "dm_code")
	private String dmCode; 
		
	@Column(name = "dm_name")
	@FieldInfo(desc = "名称", column = "dm_name")
	private String dmName; 
		
	@Column(name = "parent_id")
	@FieldInfo(desc = "上级ID", column = "parent_id")
	private String parentId; 
		
	@Column(name = "is_del")
	@FieldInfo(desc = "是否可用 1：可用2：不可用", column = "is_del")
	private Long isDel; 
		
	@Column(name = "status")
	@FieldInfo(desc = "状态", column = "status")
	private String status; 
		
	@Column(name = "memo")
	@FieldInfo(desc = "备注", column = "memo")
	private String memo; 
		
	@Column(name = "flag")
	@FieldInfo(desc = "其它用途：", column = "flag")
	private String flag; 
		
	@Column(name = "dep_code")
	@FieldInfo(desc = "保留字段代码", column = "dep_code")
	private String depCode; 
		
	@Column(name = "dep_name")
	@FieldInfo(desc = "保留字段名称", column = "dep_name")
	private String depName;
	
	@Column(name = "pxh")
	@FieldInfo(desc = "排序号", column = "pxh")
	private String pxh;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDmCode() {
		return dmCode;
	}

	public void setDmCode(String dmCode) {
		this.dmCode = dmCode;
	}

	public String getDmName() {
		return dmName;
	}

	public void setDmName(String dmName) {
		this.dmName = dmName;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Long getIsDel() {
		return isDel;
	}

	public void setIsDel(Long isDel) {
		this.isDel = isDel;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getDepCode() {
		return depCode;
	}

	public void setDepCode(String depCode) {
		this.depCode = depCode;
	}

	public String getDepName() {
		return depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}

	public String getPxh() {
		return pxh;
	}

	public void setPxh(String pxh) {
		this.pxh = pxh;
	} 
		
	
}
