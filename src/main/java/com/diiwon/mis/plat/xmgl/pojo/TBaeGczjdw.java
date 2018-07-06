package com.diiwon.mis.plat.xmgl.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.AccessType;

import com.diiwon.mis.plat.xmgl.comm.annotation.FieldInfo;

@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@javax.persistence.Entity
@javax.persistence.Table(name = "t_bae_gczjdw")
@AccessType("field")
public class TBaeGczjdw {
	
	@Id
	@Column(name = "gcid")
	@FieldInfo(desc = "工程编号", column = "gcid")
	private String gcid; 
		
	@Column(name = "jsdwmc")
	@FieldInfo(desc = "建设单位名称", column = "jsdwmc")
	private String jsdwmc; 
		
	@Column(name = "jsxmfzr")
	@FieldInfo(desc = "建设项目负责人", column = "jsxmfzr")
	private String jsxmfzr; 
		
	@Column(name = "jsdjzs")
	@FieldInfo(desc = "建设资质等级及证书", column = "jsdjzs")
	private String jsdjzs; 
		
	@Column(name = "jslxdh")
	@FieldInfo(desc = "建设单位联系电话", column = "jslxdh")
	private String jslxdh; 
		
	@Column(name = "kcdwmc")
	@FieldInfo(desc = "勘察单位名称", column = "kcdwmc")
	private String kcdwmc; 
		
	@Column(name = "kcxmfzr")
	@FieldInfo(desc = "勘察项目名称", column = "kcxmfzr")
	private String kcxmfzr; 
		
	@Column(name = "kcdjzs")
	@FieldInfo(desc = "勘察资质等级及证书", column = "kcdjzs")
	private String kcdjzs; 
		
	@Column(name = "kczgzs")
	@FieldInfo(desc = "勘察执业资格及证书", column = "kczgzs")
	private String kczgzs; 
		
	@Column(name = "kclxdh")
	@FieldInfo(desc = "勘察单位联系电话", column = "kclxdh")
	private String kclxdh; 
		
	@Column(name = "sjdwmc")
	@FieldInfo(desc = "设计单位名称", column = "sjdwmc")
	private String sjdwmc; 
		
	@Column(name = "sjxmfrz")
	@FieldInfo(desc = "设计单位项目负责人", column = "sjxmfrz")
	private String sjxmfrz; 
		
	@Column(name = "sjdjzs")
	@FieldInfo(desc = "设计单位资质等级及证书", column = "sjdjzs")
	private String sjdjzs; 
		
	@Column(name = "sjzgzs")
	@FieldInfo(desc = "设计执业资格及证书", column = "sjzgzs")
	private String sjzgzs; 
		
	@Column(name = "sjlxdh")
	@FieldInfo(desc = "设计单位联系电话", column = "sjlxdh")
	private String sjlxdh; 
		
	@Column(name = "jldwmc")
	@FieldInfo(desc = "监理单位名称", column = "jldwmc")
	private String jldwmc; 
		
	@Column(name = "jlxmfzr")
	@FieldInfo(desc = "监理项目负责人", column = "jlxmfzr")
	private String jlxmfzr; 
		
	@Column(name = "jldjzs")
	@FieldInfo(desc = "监理资质等级及证书", column = "jldjzs")
	private String jldjzs; 
		
	@Column(name = "jlzgzs")
	@FieldInfo(desc = "监理执业资格及证书", column = "jlzgzs")
	private String jlzgzs; 
		
	@Column(name = "jllxdh")
	@FieldInfo(desc = "监理联系电话", column = "jllxdh")
	private String jllxdh; 
		
	@Column(name = "sgdwmc")
	@FieldInfo(desc = "施工单位名称", column = "sgdwmc")
	private String sgdwmc; 
		
	@Column(name = "sgxmjl")
	@FieldInfo(desc = "施工项目经理", column = "sgxmjl")
	private String sgxmjl; 
		
	@Column(name = "sgdjzs")
	@FieldInfo(desc = "施工资质等级及证书", column = "sgdjzs")
	private String sgdjzs; 
		
	@Column(name = "sgaqxkz")
	@FieldInfo(desc = "施工安全生产许可证", column = "sgaqxkz")
	private String sgaqxkz; 
		
	@Column(name = "sggwzs")
	@FieldInfo(desc = "施工岗位证书", column = "sggwzs")
	private String sggwzs; 
		
	@Column(name = "sgkhzh")
	@FieldInfo(desc = "施工安全考核证号", column = "sgkhzh")
	private String sgkhzh; 
		
	@Column(name = "sglxdh")
	@FieldInfo(desc = "施工联系电话", column = "sglxdh")
	private String sglxdh; 
		
	@Column(name = "sgxcaqy")
	@FieldInfo(desc = "工地现场专职安全员", column = "sgxcaqy")
	private String sgxcaqy; 
		
	@Column(name = "sgaqyzs")
	@FieldInfo(desc = "安全员安全考核证号", column = "sgaqyzs")
	private String sgaqyzs; 
		
	@Column(name = "sgtscdw")
	@FieldInfo(desc = "施工图审查单位", column = "sgtscdw")
	private String sgtscdw; 
		
	@Column(name = "gsbxbm")
	@FieldInfo(desc = "工伤保险征收部门", column = "gsbxbm")
	private String gsbxbm; 
		
	@Column(name = "sbbdh")
	@FieldInfo(desc = "社保保单号", column = "sbbdh")
	private String sbbdh; 
		
	@Column(name = "bfje")
	@FieldInfo(desc = "保费金额", column = "bfje")
	private String bfje; 
		
	@Column(name = "jsdwdb")
	@FieldInfo(desc = "建设单位代表", column = "jsdwdb")
	private String jsdwdb; 
		
	@Column(name = "jdjg")
	@FieldInfo(desc = "监督机构", column = "jdjg")
	private String jdjg; 
		
	@Column(name = "jdlxdh")
	@FieldInfo(desc = "联系电话", column = "jdlxdh")
	private String jdlxdh; 
		
	@Column(name = "jdzch")
	@FieldInfo(desc = "监督注册号", column = "jdzch")
	private String jdzch; 
		
	@Column(name = "scyj")
	@FieldInfo(desc = "审查意见", column = "scyj")
	private String scyj; 
		
	@Column(name = "scr")
	@FieldInfo(desc = "审查人", column = "scr")
	private String scr; 
		
	@Column(name = "scrq")
	@FieldInfo(desc = "审查日期", column = "scrq")
	private String scrq; 
		
	@Column(name = "bjyj")
	@FieldInfo(desc = "报监意见", column = "bjyj")
	private String bjyj; 
		
	@Column(name = "bjrq")
	@FieldInfo(desc = "报监日期", column = "bjrq")
	private String bjrq; 
		
	
	public void setGcid(String gcid) {
		this.gcid = gcid;
	}
	public String getGcid() {
		return this.gcid;
	}
	public void setJsdwmc(String jsdwmc) {
		this.jsdwmc = jsdwmc;
	}
	public String getJsdwmc() {
		return this.jsdwmc;
	}
	public void setJsxmfzr(String jsxmfzr) {
		this.jsxmfzr = jsxmfzr;
	}
	public String getJsxmfzr() {
		return this.jsxmfzr;
	}
	public void setJsdjzs(String jsdjzs) {
		this.jsdjzs = jsdjzs;
	}
	public String getJsdjzs() {
		return this.jsdjzs;
	}
	public void setJslxdh(String jslxdh) {
		this.jslxdh = jslxdh;
	}
	public String getJslxdh() {
		return this.jslxdh;
	}
	public void setKcdwmc(String kcdwmc) {
		this.kcdwmc = kcdwmc;
	}
	public String getKcdwmc() {
		return this.kcdwmc;
	}
	public void setKcxmfzr(String kcxmfzr) {
		this.kcxmfzr = kcxmfzr;
	}
	public String getKcxmfzr() {
		return this.kcxmfzr;
	}
	public void setKcdjzs(String kcdjzs) {
		this.kcdjzs = kcdjzs;
	}
	public String getKcdjzs() {
		return this.kcdjzs;
	}
	public void setKczgzs(String kczgzs) {
		this.kczgzs = kczgzs;
	}
	public String getKczgzs() {
		return this.kczgzs;
	}
	public void setKclxdh(String kclxdh) {
		this.kclxdh = kclxdh;
	}
	public String getKclxdh() {
		return this.kclxdh;
	}
	public void setSjdwmc(String sjdwmc) {
		this.sjdwmc = sjdwmc;
	}
	public String getSjdwmc() {
		return this.sjdwmc;
	}
	public void setSjxmfrz(String sjxmfrz) {
		this.sjxmfrz = sjxmfrz;
	}
	public String getSjxmfrz() {
		return this.sjxmfrz;
	}
	public void setSjdjzs(String sjdjzs) {
		this.sjdjzs = sjdjzs;
	}
	public String getSjdjzs() {
		return this.sjdjzs;
	}
	public void setSjzgzs(String sjzgzs) {
		this.sjzgzs = sjzgzs;
	}
	public String getSjzgzs() {
		return this.sjzgzs;
	}
	public void setSjlxdh(String sjlxdh) {
		this.sjlxdh = sjlxdh;
	}
	public String getSjlxdh() {
		return this.sjlxdh;
	}
	public void setJldwmc(String jldwmc) {
		this.jldwmc = jldwmc;
	}
	public String getJldwmc() {
		return this.jldwmc;
	}
	public void setJlxmfzr(String jlxmfzr) {
		this.jlxmfzr = jlxmfzr;
	}
	public String getJlxmfzr() {
		return this.jlxmfzr;
	}
	public void setJldjzs(String jldjzs) {
		this.jldjzs = jldjzs;
	}
	public String getJldjzs() {
		return this.jldjzs;
	}
	public void setJlzgzs(String jlzgzs) {
		this.jlzgzs = jlzgzs;
	}
	public String getJlzgzs() {
		return this.jlzgzs;
	}
	public void setJllxdh(String jllxdh) {
		this.jllxdh = jllxdh;
	}
	public String getJllxdh() {
		return this.jllxdh;
	}
	public void setSgdwmc(String sgdwmc) {
		this.sgdwmc = sgdwmc;
	}
	public String getSgdwmc() {
		return this.sgdwmc;
	}
	public void setSgxmjl(String sgxmjl) {
		this.sgxmjl = sgxmjl;
	}
	public String getSgxmjl() {
		return this.sgxmjl;
	}
	public void setSgdjzs(String sgdjzs) {
		this.sgdjzs = sgdjzs;
	}
	public String getSgdjzs() {
		return this.sgdjzs;
	}
	public void setSgaqxkz(String sgaqxkz) {
		this.sgaqxkz = sgaqxkz;
	}
	public String getSgaqxkz() {
		return this.sgaqxkz;
	}
	public void setSggwzs(String sggwzs) {
		this.sggwzs = sggwzs;
	}
	public String getSggwzs() {
		return this.sggwzs;
	}
	public void setSgkhzh(String sgkhzh) {
		this.sgkhzh = sgkhzh;
	}
	public String getSgkhzh() {
		return this.sgkhzh;
	}
	public void setSglxdh(String sglxdh) {
		this.sglxdh = sglxdh;
	}
	public String getSglxdh() {
		return this.sglxdh;
	}
	public void setSgxcaqy(String sgxcaqy) {
		this.sgxcaqy = sgxcaqy;
	}
	public String getSgxcaqy() {
		return this.sgxcaqy;
	}
	public void setSgaqyzs(String sgaqyzs) {
		this.sgaqyzs = sgaqyzs;
	}
	public String getSgaqyzs() {
		return this.sgaqyzs;
	}
	public void setSgtscdw(String sgtscdw) {
		this.sgtscdw = sgtscdw;
	}
	public String getSgtscdw() {
		return this.sgtscdw;
	}
	public void setGsbxbm(String gsbxbm) {
		this.gsbxbm = gsbxbm;
	}
	public String getGsbxbm() {
		return this.gsbxbm;
	}
	public void setSbbdh(String sbbdh) {
		this.sbbdh = sbbdh;
	}
	public String getSbbdh() {
		return this.sbbdh;
	}
	public void setBfje(String bfje) {
		this.bfje = bfje;
	}
	public String getBfje() {
		return this.bfje;
	}
	public void setJsdwdb(String jsdwdb) {
		this.jsdwdb = jsdwdb;
	}
	public String getJsdwdb() {
		return this.jsdwdb;
	}
	public void setJdjg(String jdjg) {
		this.jdjg = jdjg;
	}
	public String getJdjg() {
		return this.jdjg;
	}
	public void setJdlxdh(String jdlxdh) {
		this.jdlxdh = jdlxdh;
	}
	public String getJdlxdh() {
		return this.jdlxdh;
	}
	public void setJdzch(String jdzch) {
		this.jdzch = jdzch;
	}
	public String getJdzch() {
		return this.jdzch;
	}
	public void setScyj(String scyj) {
		this.scyj = scyj;
	}
	public String getScyj() {
		return this.scyj;
	}
	public void setScr(String scr) {
		this.scr = scr;
	}
	public String getScr() {
		return this.scr;
	}
	public void setScrq(String scrq) {
		this.scrq = scrq;
	}
	public String getScrq() {
		return this.scrq;
	}
	public void setBjyj(String bjyj) {
		this.bjyj = bjyj;
	}
	public String getBjyj() {
		return this.bjyj;
	}
	public void setBjrq(String bjrq) {
		this.bjrq = bjrq;
	}
	public String getBjrq() {
		return this.bjrq;
	}
	
	@OneToOne(mappedBy = "tBaeGczjdw")
	public TBaeGczj TBaeGczj;

	
	public TBaeGczj getTBaeGczj() {
		return TBaeGczj;
	}
	public void setTBaeGczj(TBaeGczj tBaeGczj) {
		TBaeGczj = tBaeGczj;
	}
	
}