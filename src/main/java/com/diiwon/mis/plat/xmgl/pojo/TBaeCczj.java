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
@javax.persistence.Table(name = "t_bae_cczj")
@AccessType("field")
public class TBaeCczj{
	@Id
	@Column(name = "id")
	@FieldInfo(desc = "工程编号", column = "id")
	private String id; 
		
	@Column(name = "jsdw")
	@FieldInfo(desc = "建设单位", column = "jsdw")
	private String jsdw; 
		
	@Column(name = "gcmc")
	@FieldInfo(desc = "工程名称", column = "gcmc")
	private String gcmc; 
		
	@Column(name = "gcdd")
	@FieldInfo(desc = "工程地点", column = "gcdd")
	private String gcdd; 
		
	@Column(name = "ccmj")
	@FieldInfo(desc = "拆除面积", column = "ccmj")
	private String ccmj; 
		
	@Column(name = "jgcc")
	@FieldInfo(desc = "结构层次", column = "jgcc")
	private String jgcc; 
		
	@Column(name = "wxxjd")
	@FieldInfo(desc = "危险性较大工程", column = "wxxjd")
	private String wxxjd; 
		
	@Column(name = "ljgyx")
	@FieldInfo(desc = "临近高压线", column = "ljgyx")
	private String ljgyx; 
		
	@Column(name = "ljjmq")
	@FieldInfo(desc = "临近居民、商业区", column = "ljjmq")
	private String ljjmq; 
		
	@Column(name = "zbqt")
	@FieldInfo(desc = "其他", column = "zbqt")
	private String zbqt; 
		
	@Column(name = "jx")
	@FieldInfo(desc = "机械", column = "jx")
	private String jx; 
		
	@Column(name = "bp")
	@FieldInfo(desc = "爆破", column = "bp")
	private String bp; 
		
	@Column(name = "jhkgrq")
	@FieldInfo(desc = "计划开工日期", column = "jhkgrq")
	private String jhkgrq; 
		
	@Column(name = "jhjgrq")
	@FieldInfo(desc = "计划竣工日期", column = "jhjgrq")
	private String jhjgrq; 
		
	@Column(name = "ccgczt")
	@FieldInfo(desc = "拆除工程状态", column = "ccgczt")
	private String ccgczt;
	
	@Column(name = "xmlx")
	@FieldInfo(desc = "项目类型", column = "xmlx")
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
	
	@Column(name = "cjr")
	@FieldInfo(desc = "创建人", column = "cjr")
	private String cjr; 
		
	@Column(name = "cjsj")
	@FieldInfo(desc = "创建时间", column = "cjsj")
	private String cjsj; 
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the jsdw
	 */
	public String getJsdw() {
		return jsdw;
	}
	/**
	 * @param jsdw the jsdw to set
	 */
	public void setJsdw(String jsdw) {
		this.jsdw = jsdw;
	}
	/**
	 * @return the gcmc
	 */
	public String getGcmc() {
		return gcmc;
	}
	/**
	 * @param gcmc the gcmc to set
	 */
	public void setGcmc(String gcmc) {
		this.gcmc = gcmc;
	}
	/**
	 * @return the gcdd
	 */
	public String getGcdd() {
		return gcdd;
	}
	/**
	 * @param gcdd the gcdd to set
	 */
	public void setGcdd(String gcdd) {
		this.gcdd = gcdd;
	}
	/**
	 * @return the ccmj
	 */
	public String getCcmj() {
		return ccmj;
	}
	/**
	 * @param ccmj the ccmj to set
	 */
	public void setCcmj(String ccmj) {
		this.ccmj = ccmj;
	}
	/**
	 * @return the jgcc
	 */
	public String getJgcc() {
		return jgcc;
	}
	/**
	 * @param jgcc the jgcc to set
	 */
	public void setJgcc(String jgcc) {
		this.jgcc = jgcc;
	}
	/**
	 * @return the wxxjd
	 */
	public String getWxxjd() {
		return wxxjd;
	}
	/**
	 * @param wxxjd the wxxjd to set
	 */
	public void setWxxjd(String wxxjd) {
		this.wxxjd = wxxjd;
	}
	/**
	 * @return the ljgyx
	 */
	public String getLjgyx() {
		return ljgyx;
	}
	/**
	 * @param ljgyx the ljgyx to set
	 */
	public void setLjgyx(String ljgyx) {
		this.ljgyx = ljgyx;
	}
	/**
	 * @return the ljjmq
	 */
	public String getLjjmq() {
		return ljjmq;
	}
	/**
	 * @param ljjmq the ljjmq to set
	 */
	public void setLjjmq(String ljjmq) {
		this.ljjmq = ljjmq;
	}
	/**
	 * @return the zbqt
	 */
	public String getZbqt() {
		return zbqt;
	}
	/**
	 * @param zbqt the zbqt to set
	 */
	public void setZbqt(String zbqt) {
		this.zbqt = zbqt;
	}
	/**
	 * @return the jx
	 */
	public String getJx() {
		return jx;
	}
	/**
	 * @param jx the jx to set
	 */
	public void setJx(String jx) {
		this.jx = jx;
	}
	/**
	 * @return the bp
	 */
	public String getBp() {
		return bp;
	}
	/**
	 * @param bp the bp to set
	 */
	public void setBp(String bp) {
		this.bp = bp;
	}
	/**
	 * @return the jhkgrq
	 */
	public String getJhkgrq() {
		return jhkgrq;
	}
	/**
	 * @param jhkgrq the jhkgrq to set
	 */
	public void setJhkgrq(String jhkgrq) {
		this.jhkgrq = jhkgrq;
	}
	/**
	 * @return the jhjgrq
	 */
	public String getJhjgrq() {
		return jhjgrq;
	}
	/**
	 * @param jhjgrq the jhjgrq to set
	 */
	public void setJhjgrq(String jhjgrq) {
		this.jhjgrq = jhjgrq;
	}
	/**
	 * @return the ccgczt
	 */
	public String getCcgczt() {
		return ccgczt;
	}
	/**
	 * @param ccgczt the ccgczt to set
	 */
	public void setCcgczt(String ccgczt) {
		this.ccgczt = ccgczt;
	}
	/**
	 * @return the xmlx
	 */
	public String getXmlx() {
		return xmlx;
	}
	/**
	 * @param xmlx the xmlx to set
	 */
	public void setXmlx(String xmlx) {
		this.xmlx = xmlx;
	}

	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
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

	public String getCjr() {
		return cjr;
	}
	public void setCjr(String cjr) {
		this.cjr = cjr;
	}
	public String getCjsj() {
		return cjsj;
	}
	public void setCjsj(String cjsj) {
		this.cjsj = cjsj;
	}


	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
	public TBaeCczjdw tBaeCczjdw;
	
	public TBaeCczjdw gettBaeCczjdw() {
		return tBaeCczjdw;
	}
	public void settBaeCczjdw(TBaeCczjdw tBaeCczjdw) {
		this.tBaeCczjdw = tBaeCczjdw;
	}
}