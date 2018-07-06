package com.diiwon.mis.plat.base.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.GenericGenerator;

@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@javax.persistence.Entity
@javax.persistence.Table(name = "t_ext_sczy_pz")
@AccessType("field")
public class TExtSczyPz implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public TExtSczyPz() {

	}

	/**
	 *Id[UUID/GUID]
	 */
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "guid")
	private Long id;
	/**
	 *上传类型
	 */
	@Column(name = "SCLX", length = 500)
	private String sclx;
	/**
	 *类型分隔符
	 */
	@Column(name = "LXFGF", length = 50)
	private String lxfgf;
	/**
	 *上传大小限制
	 */
	@Column(name = "SCDXXZ", length = 22)
	private Long scdxxz;
	/**
	 *分类名称
	 */
	@Column(name = "FLMC", length = 50)
	private String flmc;
	/**
	 *名称简拼
	 */
	@Column(name = "MCJP", length = 50)
	private String mcjp;
	/**
	 *存储类型[0-数据库存储,1-文件存储]
	 */
	@Column(name = "CCLX", length = 50)
	private String cclx;
	/**
	 *路径类型[0-相对路径,1-物理路径]
	 */
	@Column(name = "LJLX", length = 50)
	private String ljlx;
	/**
	 *存储路径
	 */
	@Column(name = "CCLJ", length = 100)
	private String cclj;

	/**
	 *序号
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 *序号
	 * 
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 *上传类型
	 */
	public String getSclx() {
		return this.sclx;
	}

	/**
	 *上传类型
	 * 
	 * @param sclx
	 */
	public void setSclx(String sclx) {
		this.sclx = sclx;
	}

	/**
	 *类型分隔符
	 */
	public String getLxfgf() {
		return this.lxfgf;
	}

	/**
	 *类型分隔符
	 * 
	 * @param lxfgf
	 */
	public void setLxfgf(String lxfgf) {
		this.lxfgf = lxfgf;
	}

	/**
	 *上传大小限制
	 */
	public Long getScdxxz() {
		return this.scdxxz;
	}

	/**
	 *上传大小限制
	 * 
	 * @param scdxxz
	 */
	public void setScdxxz(Long scdxxz) {
		this.scdxxz = scdxxz;
	}

	/**
	 *分类名称
	 */
	public String getFlmc() {
		return this.flmc;
	}

	/**
	 *分类名称
	 * 
	 * @param flmc
	 */
	public void setFlmc(String flmc) {
		this.flmc = flmc;
	}

	/**
	 *名称简拼
	 */
	public String getMcjp() {
		return this.mcjp;
	}

	/**
	 *名称简拼
	 * 
	 * @param mcjp
	 */
	public void setMcjp(String mcjp) {
		this.mcjp = mcjp;
	}

	/**
	 *存储类型[0-数据库存储,1-文件存储]
	 */
	public String getCclx() {
		return this.cclx;
	}

	/**
	 *存储类型[0-数据库存储,1-文件存储]
	 * 
	 * @param cclx
	 */
	public void setCclx(String cclx) {
		this.cclx = cclx;
	}

	/**
	 *路径类型[0-相对路径,1-物理路径]
	 */
	public String getLjlx() {
		return this.ljlx;
	}

	/**
	 *路径类型[0-相对路径,1-物理路径]
	 * 
	 * @param ljlx
	 */
	public void setLjlx(String ljlx) {
		this.ljlx = ljlx;
	}

	/**
	 *存储路径
	 */
	public String getCclj() {
		return this.cclj;
	}

	/**
	 *存储路径
	 * 
	 * @param cclj
	 */
	public void setCclj(String cclj) {
		this.cclj = cclj;
	}

}