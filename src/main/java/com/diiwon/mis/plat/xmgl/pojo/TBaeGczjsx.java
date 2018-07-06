package com.diiwon.mis.plat.xmgl.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.AccessType;

import com.diiwon.mis.plat.xmgl.comm.annotation.FieldInfo;

@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@javax.persistence.Entity
@javax.persistence.Table(name = "t_bae_gczjsx")
@AccessType("field")
public class TBaeGczjsx {
	
		
	@Id
	@Column(name = "gcid")
	@FieldInfo(desc = "工程编号", column = "gcid")
	private String gcid; 
		
	@Column(name = "sjc")
	@FieldInfo(desc = "深基坑", column = "sjc")
	private String sjc; 
		
	@Column(name = "gdmb")
	@FieldInfo(desc = "高大模板", column = "gdmb")
	private String gdmb; 
		
	@Column(name = "gkzy")
	@FieldInfo(desc = "30M以上高空作业", column = "gkzy")
	private String gkzy; 
		
	@Column(name = "pj")
	@FieldInfo(desc = "爬架", column = "pj")
	private String pj; 
		
	@Column(name = "rhdt")
	@FieldInfo(desc = "人货电梯", column = "rhdt")
	private String rhdt; 
		
	@Column(name = "td")
	@FieldInfo(desc = "塔吊", column = "td")
	private String td; 
		
	@Column(name = "wxqt")
	@FieldInfo(desc = "危险性较大工程其他", column = "wxqt")
	private String wxqt; 
		
	@Column(name = "ljgyx")
	@FieldInfo(desc = "临近高压线", column = "ljgyx")
	private String ljgyx; 
		
	@Column(name = "ljjmq")
	@FieldInfo(desc = "临近居民区、商业区", column = "ljjmq")
	private String ljjmq; 
		
	@Column(name = "zbqt")
	@FieldInfo(desc = "周边环境其他", column = "zbqt")
	private String zbqt; 
		
	
	public void setGcid(String gcid) {
		this.gcid = gcid;
	}
	public String getGcid() {
		return this.gcid;
	}
	public void setSjc(String sjc) {
		this.sjc = sjc;
	}
	public String getSjc() {
		return this.sjc;
	}
	public void setGdmb(String gdmb) {
		this.gdmb = gdmb;
	}
	public String getGdmb() {
		return this.gdmb;
	}
	public void setGkzy(String gkzy) {
		this.gkzy = gkzy;
	}
	public String getGkzy() {
		return this.gkzy;
	}
	public void setPj(String pj) {
		this.pj = pj;
	}
	public String getPj() {
		return this.pj;
	}
	public void setRhdt(String rhdt) {
		this.rhdt = rhdt;
	}
	public String getRhdt() {
		return this.rhdt;
	}
	public void setTd(String td) {
		this.td = td;
	}
	public String getTd() {
		return this.td;
	}
	public void setWxqt(String wxqt) {
		this.wxqt = wxqt;
	}
	public String getWxqt() {
		return this.wxqt;
	}
	public void setLjgyx(String ljgyx) {
		this.ljgyx = ljgyx;
	}
	public String getLjgyx() {
		return this.ljgyx;
	}
	public void setLjjmq(String ljjmq) {
		this.ljjmq = ljjmq;
	}
	public String getLjjmq() {
		return this.ljjmq;
	}
	public void setZbqt(String zbqt) {
		this.zbqt = zbqt;
	}
	public String getZbqt() {
		return this.zbqt;
	}
    
	
	@OneToOne(mappedBy = "tBaeGczjsx")
	public TBaeGczj TBaeGczj;

	
	public TBaeGczj getTBaeGczj() {
		return TBaeGczj;
	}
	public void setTBaeGczj(TBaeGczj tBaeGczj) {
		TBaeGczj = tBaeGczj;
	}
	
}