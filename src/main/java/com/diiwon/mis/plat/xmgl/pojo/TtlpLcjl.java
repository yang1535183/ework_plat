package com.diiwon.mis.plat.xmgl.pojo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.AccessType;

import com.diiwon.mis.plat.xmgl.comm.annotation.FieldInfo;

@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@javax.persistence.Entity
@javax.persistence.Table(name = "t_tlp_lcjl")
@AccessType("field")
public class TtlpLcjl {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@FieldInfo(desc = "主键", column = "id", isPK=true)
	private Long id; 
		
	@Column(name = "gcid")
	@FieldInfo(desc = "工程编号", column = "gcid")
	private String gcid; 
	
	@Column(name = "action")
	@FieldInfo(desc = "行为动作，结果 0：企业初始创建。 1：企业提交 2：审查员通过 3：审查员不通过 4：审批领导通过 5：审批领导不通过", column = "action")
	private String action; 
		
	@Column(name = "memo")
	@FieldInfo(desc = "意见", column = "memo")
	private String memo; 
		
	@Column(name = "user")
	@FieldInfo(desc = "操作人", column = "user")
	private String user; 
		
	@Column(name = "time")
	@FieldInfo(desc = "时间", column = "time")
	private String time; 
	
	@Column(name = "flag")
	@FieldInfo(desc = "0:报监流程。1：合同流程", column = "flag")
	private String flag;
		
	public void setId(Long id) {
		this.id = id;
	}
	public Long getId() {
		return this.id;
	}
	public void setGcid(String gcid) {
		this.gcid = gcid;
	}
	public String getGcid() {
		return this.gcid;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getAction() {
		return this.action;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getMemo() {
		return this.memo;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getUser() {
		return this.user;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getTime() {
		return this.time;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	
}
