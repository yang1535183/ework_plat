package com.diiwon.mis.plat.xmgl.pojo;

import javax.persistence.Column;

import org.hibernate.annotations.AccessType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.diiwon.mis.plat.xmgl.comm.annotation.FieldInfo;

@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@javax.persistence.Entity
@javax.persistence.Table(name = "t_bae_cczjdw")
@AccessType("field")
public class TBaeCczjdw {
	
	@Id	
	@Column(name = "gcid")
	@FieldInfo(desc = "工程编号", column = "gcid")
	private String gcid; 
		
	@Column(name = "jsdwmc")
	@FieldInfo(desc = "建设单位名称", column = "jsdwmc")
	private String jsdwmc; 
		
	@Column(name = "jssd")
	@FieldInfo(desc = "建设属地", column = "jssd")
	private String jssd; 
		
	@Column(name = "jsxmfzr")
	@FieldInfo(desc = "建设项目负责人", column = "jsxmfzr")
	private String jsxmfzr; 
		
	@Column(name = "jslxdh")
	@FieldInfo(desc = "建设联系电话", column = "jslxdh")
	private String jslxdh; 
		
	@Column(name = "jldwmc")
	@FieldInfo(desc = "监理单位名称", column = "jldwmc")
	private String jldwmc; 
		
	@Column(name = "jldjzs")
	@FieldInfo(desc = "监理资质等级及证书", column = "jldjzs")
	private String jldjzs; 
		
	@Column(name = "jlxmzj")
	@FieldInfo(desc = "监理项目总监", column = "jlxmzj")
	private String jlxmzj; 
		
	@Column(name = "jlzgzs")
	@FieldInfo(desc = "监理职业资格及证书", column = "jlzgzs")
	private String jlzgzs; 
		
	@Column(name = "jllxdh")
	@FieldInfo(desc = "监理联系电话", column = "jllxdh")
	private String jllxdh; 
		
	@Column(name = "ccdwmc")
	@FieldInfo(desc = "拆除单位名称", column = "ccdwmc")
	private String ccdwmc; 
		
	@Column(name = "cczzdj")
	@FieldInfo(desc = "拆除资质等级或备案", column = "cczzdj")
	private String cczzdj; 
		
	@Column(name = "ccaqxkzh")
	@FieldInfo(desc = "拆除安全生产许可证号", column = "ccaqxkzh")
	private String ccaqxkzh; 
		
	@Column(name = "ccxmfrz")
	@FieldInfo(desc = "项目负责人", column = "ccxmfrz")
	private String ccxmfrz; 
		
	@Column(name = "ccgwzs")
	@FieldInfo(desc = "拆除岗位证书", column = "ccgwzs")
	private String ccgwzs; 
		
	@Column(name = "ccaqkhzh")
	@FieldInfo(desc = "拆除安全考核证号", column = "ccaqkhzh")
	private String ccaqkhzh; 
		
	@Column(name = "cclxdh")
	@FieldInfo(desc = "拆除联系电话", column = "cclxdh")
	private String cclxdh; 
		
	@Column(name = "ccxcaqy")
	@FieldInfo(desc = "拆除现场专职安全员", column = "ccxcaqy")
	private String ccxcaqy; 
		
	@Column(name = "aqkhzh")
	@FieldInfo(desc = "安全员安全考核证号", column = "aqkhzh")
	private String aqkhzh; 
		
	@Column(name = "ccdwzzzs")
	@FieldInfo(desc = "拆除单位资质证书（或登记备案证书）及复印件", column = "ccdwzzzs")
	private String ccdwzzzs; 
		
	@Column(name = "fwccwtht")
	@FieldInfo(desc = "房屋拆除委托合同", column = "fwccwtht")
	private String fwccwtht; 
		
	@Column(name = "bzjjlpz")
	@FieldInfo(desc = "保证金缴纳凭证", column = "bzjjlpz")
	private String bzjjlpz; 
		
	@Column(name = "scyj")
	@FieldInfo(desc = "审查意见", column = "scyj")
	private String scyj; 
		
	@Column(name = "scr")
	@FieldInfo(desc = " 审查人", column = "scr")
	private String scr; 
		
	@Column(name = "scrq")
	@FieldInfo(desc = "审查日期", column = "scrq")
	private String scrq; 
		
	@Column(name = "jdzch")
	@FieldInfo(desc = "监督注册号", column = "jdzch")
	private String jdzch; 
		
	@Column(name = "bjyj")
	@FieldInfo(desc = "报监意见", column = "bjyj")
	private String bjyj; 
		
	@Column(name = "bjrq")
	@FieldInfo(desc = "报监日期", column = "bjrq")
	private String bjrq; 
		
	/**
	 * @return the gcid
	 */
	public String getGcid() {
		return gcid;
	}
	/**
	 * @param gcid the gcid to set
	 */
	public void setGcid(String gcid) {
		this.gcid = gcid;
	}
	/**
	 * @return the jsdwmc
	 */
	public String getJsdwmc() {
		return jsdwmc;
	}
	/**
	 * @param jsdwmc the jsdwmc to set
	 */
	public void setJsdwmc(String jsdwmc) {
		this.jsdwmc = jsdwmc;
	}
	/**
	 * @return the jssd
	 */
	public String getJssd() {
		return jssd;
	}
	/**
	 * @param jssd the jssd to set
	 */
	public void setJssd(String jssd) {
		this.jssd = jssd;
	}
	/**
	 * @return the jsxmfzr
	 */
	public String getJsxmfzr() {
		return jsxmfzr;
	}
	/**
	 * @param jsxmfzr the jsxmfzr to set
	 */
	public void setJsxmfzr(String jsxmfzr) {
		this.jsxmfzr = jsxmfzr;
	}
	/**
	 * @return the jslxdh
	 */
	public String getJslxdh() {
		return jslxdh;
	}
	/**
	 * @param jslxdh the jslxdh to set
	 */
	public void setJslxdh(String jslxdh) {
		this.jslxdh = jslxdh;
	}
	/**
	 * @return the jldwmc
	 */
	public String getJldwmc() {
		return jldwmc;
	}
	/**
	 * @param jldwmc the jldwmc to set
	 */
	public void setJldwmc(String jldwmc) {
		this.jldwmc = jldwmc;
	}
	/**
	 * @return the jldjzs
	 */
	public String getJldjzs() {
		return jldjzs;
	}
	/**
	 * @param jldjzs the jldjzs to set
	 */
	public void setJldjzs(String jldjzs) {
		this.jldjzs = jldjzs;
	}
	/**
	 * @return the jlxmzj
	 */
	public String getJlxmzj() {
		return jlxmzj;
	}
	/**
	 * @param jlxmzj the jlxmzj to set
	 */
	public void setJlxmzj(String jlxmzj) {
		this.jlxmzj = jlxmzj;
	}
	/**
	 * @return the jlzgzs
	 */
	public String getJlzgzs() {
		return jlzgzs;
	}
	/**
	 * @param jlzgzs the jlzgzs to set
	 */
	public void setJlzgzs(String jlzgzs) {
		this.jlzgzs = jlzgzs;
	}
	/**
	 * @return the jllxdh
	 */
	public String getJllxdh() {
		return jllxdh;
	}
	/**
	 * @param jllxdh the jllxdh to set
	 */
	public void setJllxdh(String jllxdh) {
		this.jllxdh = jllxdh;
	}
	/**
	 * @return the ccdwmc
	 */
	public String getCcdwmc() {
		return ccdwmc;
	}
	/**
	 * @param ccdwmc the ccdwmc to set
	 */
	public void setCcdwmc(String ccdwmc) {
		this.ccdwmc = ccdwmc;
	}
	/**
	 * @return the cczzdj
	 */
	public String getCczzdj() {
		return cczzdj;
	}
	/**
	 * @param cczzdj the cczzdj to set
	 */
	public void setCczzdj(String cczzdj) {
		this.cczzdj = cczzdj;
	}
	/**
	 * @return the ccaqxkzh
	 */
	public String getCcaqxkzh() {
		return ccaqxkzh;
	}
	/**
	 * @param ccaqxkzh the ccaqxkzh to set
	 */
	public void setCcaqxkzh(String ccaqxkzh) {
		this.ccaqxkzh = ccaqxkzh;
	}
	/**
	 * @return the ccxmfrz
	 */
	public String getCcxmfrz() {
		return ccxmfrz;
	}
	/**
	 * @param ccxmfrz the ccxmfrz to set
	 */
	public void setCcxmfrz(String ccxmfrz) {
		this.ccxmfrz = ccxmfrz;
	}
	/**
	 * @return the ccgwzs
	 */
	public String getCcgwzs() {
		return ccgwzs;
	}
	/**
	 * @param ccgwzs the ccgwzs to set
	 */
	public void setCcgwzs(String ccgwzs) {
		this.ccgwzs = ccgwzs;
	}
	/**
	 * @return the ccaqkhzh
	 */
	public String getCcaqkhzh() {
		return ccaqkhzh;
	}
	/**
	 * @param ccaqkhzh the ccaqkhzh to set
	 */
	public void setCcaqkhzh(String ccaqkhzh) {
		this.ccaqkhzh = ccaqkhzh;
	}
	/**
	 * @return the cclxdh
	 */
	public String getCclxdh() {
		return cclxdh;
	}
	/**
	 * @param cclxdh the cclxdh to set
	 */
	public void setCclxdh(String cclxdh) {
		this.cclxdh = cclxdh;
	}
	/**
	 * @return the ccxcaqy
	 */
	public String getCcxcaqy() {
		return ccxcaqy;
	}
	/**
	 * @param ccxcaqy the ccxcaqy to set
	 */
	public void setCcxcaqy(String ccxcaqy) {
		this.ccxcaqy = ccxcaqy;
	}
	/**
	 * @return the aqkhzh
	 */
	public String getAqkhzh() {
		return aqkhzh;
	}
	/**
	 * @param aqkhzh the aqkhzh to set
	 */
	public void setAqkhzh(String aqkhzh) {
		this.aqkhzh = aqkhzh;
	}
	/**
	 * @return the ccdwzzzs
	 */
	public String getCcdwzzzs() {
		return ccdwzzzs;
	}
	/**
	 * @param ccdwzzzs the ccdwzzzs to set
	 */
	public void setCcdwzzzs(String ccdwzzzs) {
		this.ccdwzzzs = ccdwzzzs;
	}
	/**
	 * @return the fwccwtht
	 */
	public String getFwccwtht() {
		return fwccwtht;
	}
	/**
	 * @param fwccwtht the fwccwtht to set
	 */
	public void setFwccwtht(String fwccwtht) {
		this.fwccwtht = fwccwtht;
	}
	/**
	 * @return the bzjjlpz
	 */
	public String getBzjjlpz() {
		return bzjjlpz;
	}
	/**
	 * @param bzjjlpz the bzjjlpz to set
	 */
	public void setBzjjlpz(String bzjjlpz) {
		this.bzjjlpz = bzjjlpz;
	}
	/**
	 * @return the scyj
	 */
	public String getScyj() {
		return scyj;
	}
	/**
	 * @param scyj the scyj to set
	 */
	public void setScyj(String scyj) {
		this.scyj = scyj;
	}
	/**
	 * @return the scr
	 */
	public String getScr() {
		return scr;
	}
	/**
	 * @param scr the scr to set
	 */
	public void setScr(String scr) {
		this.scr = scr;
	}
	/**
	 * @return the scrq
	 */
	public String getScrq() {
		return scrq;
	}
	/**
	 * @param scrq the scrq to set
	 */
	public void setScrq(String scrq) {
		this.scrq = scrq;
	}
	/**
	 * @return the jdzch
	 */
	public String getJdzch() {
		return jdzch;
	}
	/**
	 * @param jdzch the jdzch to set
	 */
	public void setJdzch(String jdzch) {
		this.jdzch = jdzch;
	}
	/**
	 * @return the bjyj
	 */
	public String getBjyj() {
		return bjyj;
	}
	/**
	 * @param bjyj the bjyj to set
	 */
	public void setBjyj(String bjyj) {
		this.bjyj = bjyj;
	}
	/**
	 * @return the bjrq
	 */
	public String getBjrq() {
		return bjrq;
	}
	/**
	 * @param bjrq the bjrq to set
	 */
	public void setBjrq(String bjrq) {
		this.bjrq = bjrq;
	}

	@OneToOne(mappedBy = "tBaeCczjdw")
	public TBaeCczj tBaeCczj;
	
	public TBaeCczj gettBaeCczj() {
		return tBaeCczj;
	}
	public void settBaeCczj(TBaeCczj tBaeCczj) {
		this.tBaeCczj = tBaeCczj;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TBaeCczjdw [gcid=" + gcid + ", jsdwmc=" + jsdwmc + ", jssd=" + jssd + ", jsxmfzr=" + jsxmfzr
				+ ", jslxdh=" + jslxdh + ", jldwmc=" + jldwmc + ", jldjzs=" + jldjzs + ", jlxmzj=" + jlxmzj
				+ ", jlzgzs=" + jlzgzs + ", jllxdh=" + jllxdh + ", ccdwmc=" + ccdwmc + ", cczzdj=" + cczzdj
				+ ", ccaqxkzh=" + ccaqxkzh + ", ccxmfrz=" + ccxmfrz + ", ccgwzs=" + ccgwzs + ", ccaqkhzh=" + ccaqkhzh
				+ ", cclxdh=" + cclxdh + ", ccxcaqy=" + ccxcaqy + ", aqkhzh=" + aqkhzh + ", ccdwzzzs=" + ccdwzzzs
				+ ", fwccwtht=" + fwccwtht + ", bzjjlpz=" + bzjjlpz + ", scyj=" + scyj + ", scr=" + scr + ", scrq="
				+ scrq + ", jdzch=" + jdzch + ", bjyj=" + bjyj + ", bjrq=" + bjrq + ", tBaeCczj=" + tBaeCczj + "]";
	}
}