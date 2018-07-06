package com.diiwon.mis.plat.base.pojo;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.GenericGenerator;

@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@javax.persistence.Entity
@javax.persistence.Table(name = "t_ext_sczy")
@AccessType("field")
@SequenceGenerator(name = "t_ext_sczy_id", sequenceName = "t_ext_sczy_seq", allocationSize = 1)
public class TExtSczy{
public TExtSczy(){

}
/**
 *Id[UUID/GUID]
 */
@Id
@GeneratedValue(generator = "paymentableGenerator")
@GenericGenerator(name = "paymentableGenerator", strategy = "guid")
private String uuid;
/**
 *关联表名称
 */
@Column(name = "GLBMC", length = 50)
private String glbmc;
/**
 *关联表信息代码
 */
@Column(name = "GLBXXDM", length = 64)
private String glbxxdm;
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
 *存储全路径
 */
@Column(name = "CCQLJ", length = 1000)
private String ccqlj;
/**
 *文件名
 */
@Column(name = "WJM", length = 500)
private String wjm;
/**
 *文件类型
 */
@Column(name = "WJLX", length = 50)
private String wjlx;
/**
 *文件内容
 */
@Column(name = "WJNR", length = 4000)
private Blob wjnr;
/**
 *文件大小
 */
@Column(name = "WJDX", length = 22)
private Long wjdx;
/**
 *上传时间
 */
@Column(name = "SCSJ", length = 50)
private String scsj;
/**
 *有效标识
 */
@Column(name = "YXBS", length = 50)
private String yxbs;
/**
 *图片说明
 */
@Column(name = "WJSM", length = 200)
private String wjsm;
/**
 *上传人
 */
@Column(name = "SCR", length = 20)
private String scr;
/**
 *序号
 */
public String getUuid(){
	return this.uuid;
}
/**
 *序号
 *@param uuid
 */
public void setUuid(String uuid){
	this.uuid=uuid;
}

/**
 *关联表名称
 */
public String getGlbmc(){
	return this.glbmc;
}
/**
 *关联表名称
 *@param glbmc
 */
public void setGlbmc(String glbmc){
	this.glbmc=glbmc;
}

/**
 *关联表信息代码
 */
public String getGlbxxdm(){
	return this.glbxxdm;
}
/**
 *关联表信息代码
 *@param glbxxdm
 */
public void setGlbxxdm(String glbxxdm){
	this.glbxxdm=glbxxdm;
}

/**
 *存储类型[0-数据库存储,1-文件存储]
 */
public String getCclx(){
	return this.cclx;
}
/**
 *存储类型[0-数据库存储,1-文件存储]
 *@param cclx
 */
public void setCclx(String cclx){
	this.cclx=cclx;
}

/**
 *路径类型[0-相对路径,1-物理路径]
 */
public String getLjlx(){
	return this.ljlx;
}
/**
 *路径类型[0-相对路径,1-物理路径]
 *@param ljlx
 */
public void setLjlx(String ljlx){
	this.ljlx=ljlx;
}

/**
 *存储全路径
 */
public String getCcqlj(){
	return this.ccqlj;
}
/**
 *存储全路径
 *@param ccqlj
 */
public void setCcqlj(String ccqlj){
	this.ccqlj=ccqlj;
}

/**
 *文件名
 */
public String getWjm(){
	return this.wjm;
}
/**
 *文件名
 *@param wjm
 */
public void setWjm(String wjm){
	this.wjm=wjm;
}

/**
 *文件类型
 */
public String getWjlx(){
	return this.wjlx;
}
/**
 *文件类型
 *@param wjlx
 */
public void setWjlx(String wjlx){
	this.wjlx=wjlx;
}

/**
 *文件内容
 */
public Blob getWjnr(){
	return this.wjnr;
}
/**
 *文件内容
 *@param wjnr
 */
public void setWjnr(Blob wjnr){
	this.wjnr=wjnr;
}

/**
 *文件大小
 */
public Long getWjdx(){
	return this.wjdx;
}
/**
 *文件大小
 *@param wjdx
 */
public void setWjdx(Long wjdx){
	this.wjdx=wjdx;
}

/**
 *上传时间
 */
public String getScsj(){
	return this.scsj;
}
/**
 *上传时间
 *@param scsj
 */
public void setScsj(String scsj){
	this.scsj=scsj;
}

/**
 *有效标识
 */
public String getYxbs(){
	return this.yxbs;
}
/**
 *有效标识
 *@param yxbs
 */
public void setYxbs(String yxbs){
	this.yxbs=yxbs;
}

/**
 *图片说明
 */
public String getWjsm(){
	return this.wjsm;
}
/**
 *图片说明
 *@param wjsm
 */
public void setWjsm(String wjsm){
	this.wjsm=wjsm;
}

/**
 *上传人
 */
public String getScr(){
	return this.scr;
}
/**
 *上传人
 *@param scr
 */
public void setScr(String scr){
	this.scr=scr;
}

}