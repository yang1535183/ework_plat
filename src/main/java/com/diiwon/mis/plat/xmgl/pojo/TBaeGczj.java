package com.diiwon.mis.plat.xmgl.pojo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.AccessType;

import com.diiwon.mis.plat.xmgl.comm.annotation.FieldInfo;


@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@javax.persistence.Entity
@javax.persistence.Table(name = "t_bae_gczj")
@AccessType("field")
public class TBaeGczj {
	
	@Id
	@Column(name = "id")
	@FieldInfo(desc = "序号", column = "id")
	private String id; 
		
	@Column(name = "gcmc")
	@FieldInfo(desc = "工程名称", column = "gcmc")
	private String gcmc; 
		
	@Column(name = "qu")
	@FieldInfo(desc = "区(工程地点)", column = "qu")
	private String qu; 
		
	@Column(name = "lu")
	@FieldInfo(desc = "路(工程地点)", column = "lu")
	private String lu; 
		
	@Column(name = "hao")
	@FieldInfo(desc = "号(工程地点)", column = "hao")
	private String hao; 
		
	@Column(name = "tzlb")
	@FieldInfo(desc = "投资类别(1.外资，2.合资，3.国有，4.集体，5.民营)", column = "tzlb")
	private String tzlb; 
		
	@Column(name = "yszj")
	@FieldInfo(desc = "预算造价", column = "yszj")
	private String yszj; 
		
	@Column(name = "jzmj")
	@FieldInfo(desc = "建筑面筋", column = "jzmj")
	private String jzmj; 
		
	@Column(name = "jgcc")
	@FieldInfo(desc = "结构类型/层次", column = "jgcc")
	private String jgcc; 
		
	@Column(name = "pmzj")
	@FieldInfo(desc = "每平米造价", column = "pmzj")
	private String pmzj; 
		
	@Column(name = "jclx")
	@FieldInfo(desc = "基础类型", column = "jclx")
	private String jclx; 
		
	@Column(name = "gclb")
	@FieldInfo(desc = "工程类别(1.住宅，2.公建，3.厂房，4.其他)", column = "gclb")
	private String gclb; 
		
	@Column(name = "jnbz")
	@FieldInfo(desc = "绿色节能执行标准(1.50%，2.65%,3.其他)", column = "jnbz")
	private String jnbz; 
		
	@Column(name = "lsjz")
	@FieldInfo(desc = "绿色建筑(平方米)", column = "lsjz")
	private String lsjz; 
		
	@Column(name = "xj")
	@FieldInfo(desc = "星级(1.一星 2.二星 3.三星)", column = "xj")
	private String xj; 
		
	@Column(name = "tyngr")
	@FieldInfo(desc = "太阳能光热(平方米)", column = "tyngr")
	private String tyngr; 
		
	@Column(name = "tyngf")
	@FieldInfo(desc = "太阳能光伏(平方米)", column = "tyngf")
	private String tyngf; 
		
	@Column(name = "dyrb")
	@FieldInfo(desc = "地源热泵(平方米)", column = "dyrb")
	private String dyrb; 
		
	@Column(name = "kgsj")
	@FieldInfo(desc = "计划开工日期", column = "kgsj")
	private String kgsj; 
		
	@Column(name = "jgsj")
	@FieldInfo(desc = "计划竣工日期", column = "jgsj")
	private String jgsj; 
		
	@Column(name = "cjr")
	@FieldInfo(desc = "创建人", column = "cjr")
	private String cjr; 
		
	@Column(name = "cjsj")
	@FieldInfo(desc = "创建时间", column = "cjsj")
	private String cjsj; 
		
	@Column(name = "xmlx")
	@FieldInfo(desc = "项目属性", column = "xmlx")
	private String xmlx; 
		
	@Column(name = "bz")
	@FieldInfo(desc = "备注", column = "bz")
	private String bz; 
	
	@Column(name = "sjzt")
	@FieldInfo(desc = "数据状态", column = "sjzt")
	private String sjzt;
	
	@Column(name = "zcbm")
	@FieldInfo(desc = "注册编码", column = "sjzt")
	private String zcbm;
	
		
	public void setId(String id) {
		this.id = id;
	}
	public String getId() {
		return this.id;
	}
	public void setGcmc(String gcmc) {
		this.gcmc = gcmc;
	}
	public String getGcmc() {
		return this.gcmc;
	}
	public void setQu(String qu) {
		this.qu = qu;
	}
	public String getQu() {
		return this.qu;
	}
	public void setLu(String lu) {
		this.lu = lu;
	}
	public String getLu() {
		return this.lu;
	}
	public void setHao(String hao) {
		this.hao = hao;
	}
	public String getHao() {
		return this.hao;
	}
	public void setTzlb(String tzlb) {
		this.tzlb = tzlb;
	}
	public String getTzlb() {
		return this.tzlb;
	}
	public void setYszj(String yszj) {
		this.yszj = yszj;
	}
	public String getYszj() {
		return this.yszj;
	}
	public void setJzmj(String jzmj) {
		this.jzmj = jzmj;
	}
	public String getJzmj() {
		return this.jzmj;
	}
	public void setJgcc(String jgcc) {
		this.jgcc = jgcc;
	}
	public String getJgcc() {
		return this.jgcc;
	}
	public void setPmzj(String pmzj) {
		this.pmzj = pmzj;
	}
	public String getPmzj() {
		return this.pmzj;
	}
	public void setJclx(String jclx) {
		this.jclx = jclx;
	}
	public String getJclx() {
		return this.jclx;
	}
	public void setGclb(String gclb) {
		this.gclb = gclb;
	}
	public String getGclb() {
		return this.gclb;
	}
	public void setJnbz(String jnbz) {
		this.jnbz = jnbz;
	}
	public String getJnbz() {
		return this.jnbz;
	}
	public void setLsjz(String lsjz) {
		this.lsjz = lsjz;
	}
	public String getLsjz() {
		return this.lsjz;
	}
	public void setXj(String xj) {
		this.xj = xj;
	}
	public String getXj() {
		return this.xj;
	}
	public void setTyngr(String tyngr) {
		this.tyngr = tyngr;
	}
	public String getTyngr() {
		return this.tyngr;
	}
	public void setTyngf(String tyngf) {
		this.tyngf = tyngf;
	}
	public String getTyngf() {
		return this.tyngf;
	}
	public void setDyrb(String dyrb) {
		this.dyrb = dyrb;
	}
	public String getDyrb() {
		return this.dyrb;
	}
	public void setKgsj(String kgsj) {
		this.kgsj = kgsj;
	}
	public String getKgsj() {
		return this.kgsj;
	}
	public void setJgsj(String jgsj) {
		this.jgsj = jgsj;
	}
	public String getJgsj() {
		return this.jgsj;
	}
	public void setCjr(String cjr) {
		this.cjr = cjr;
	}
	public String getCjr() {
		return this.cjr;
	}
	public void setCjsj(String cjsj) {
		this.cjsj = cjsj;
	}
	public String getCjsj() {
		return this.cjsj;
	}
	public void setXmlx(String xmlx) {
		this.xmlx = xmlx;
	}
	public String getXmlx() {
		return this.xmlx;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getBz() {
		return this.bz;
	}
    
	public String getSjzt() {
		return sjzt;
	}
	public void setSjzt(String sjzt) {
		this.sjzt = sjzt;
	}
	
	public String getZcbm() {
		return zcbm;
	}
	public void setZcbm(String zcbm) {
		this.zcbm = zcbm;
	}
	
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private TBaeGczjsx tBaeGczjsx;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private TBaeGczjdw tBaeGczjdw;
    
	public TBaeGczjsx gettBaeGczjsx() {
		return tBaeGczjsx;
	}

	public void settBaeGczjsx(TBaeGczjsx tBaeGczjsx) {
		this.tBaeGczjsx = tBaeGczjsx;
	}

	public TBaeGczjdw gettBaeGczjdw() {
		return tBaeGczjdw;
	}

	public void settBaeGczjdw(TBaeGczjdw tBaeGczjdw) {
		this.tBaeGczjdw = tBaeGczjdw;
	}
    
	
}