package com.diiwon.mis.plat.xmgl.pojo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.AccessType;

import com.diiwon.mis.plat.xmgl.comm.annotation.FieldInfo;

@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@javax.persistence.Entity
@javax.persistence.Table(name = "t_mis_pz")
@AccessType("field")
public class TMisPz {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@FieldInfo(desc = "主键", column = "id", isPK=true)
	private Long id; 
		
	@Column(name = "sclx")
	@FieldInfo(desc = "上传类型", column = "sclx")
	private String sclx; 
		
	@Column(name = "lxfgf")
	@FieldInfo(desc = "类型分割符", column = "lxfgf")
	private String lxfgf; 
		
	@Column(name = "scdx")
	@FieldInfo(desc = "上传大小限制", column = "scdx")
	private String scdx; 
		
	@Column(name = "flmc")
	@FieldInfo(desc = "分类名称", column = "flmc")
	private String flmc; 
		
	@Column(name = "mcjp")
	@FieldInfo(desc = "名称简拼", column = "mcjp")
	private String mcjp; 
		
	@Column(name = "cclx")
	@FieldInfo(desc = "存储类型[0-数据库存储,1-文件存储]", column = "cclx")
	private String cclx; 
		
	@Column(name = "ljlx")
	@FieldInfo(desc = "路径类型[0-相对路径,1-物理路径]", column = "ljlx")
	private String ljlx; 
		
	@Column(name = "cclj")
	@FieldInfo(desc = "存储路径", column = "cclj")
	private String cclj; 
		
	
	public void setId(Long id) {
		this.id = id;
	}
	public Long getId() {
		return this.id;
	}
	public void setSclx(String sclx) {
		this.sclx = sclx;
	}
	public String getSclx() {
		return this.sclx;
	}
	public void setLxfgf(String lxfgf) {
		this.lxfgf = lxfgf;
	}
	public String getLxfgf() {
		return this.lxfgf;
	}
	public void setScdx(String scdx) {
		this.scdx = scdx;
	}
	public String getScdx() {
		return this.scdx;
	}
	public void setFlmc(String flmc) {
		this.flmc = flmc;
	}
	public String getFlmc() {
		return this.flmc;
	}
	public void setMcjp(String mcjp) {
		this.mcjp = mcjp;
	}
	public String getMcjp() {
		return this.mcjp;
	}
	public void setCclx(String cclx) {
		this.cclx = cclx;
	}
	public String getCclx() {
		return this.cclx;
	}
	public void setLjlx(String ljlx) {
		this.ljlx = ljlx;
	}
	public String getLjlx() {
		return this.ljlx;
	}
	public void setCclj(String cclj) {
		this.cclj = cclj;
	}
	public String getCclj() {
		return this.cclj;
	}
	
}
