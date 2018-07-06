package com.diiwon.mis.plat.xmgl.gcgl.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.diiwon.mis.plat.base.cache.imp.UserProperties;
import com.diiwon.mis.plat.base.dao.HibernateEntityDao;
import com.diiwon.mis.plat.base.dao.JdbcTemplate;
import com.diiwon.mis.plat.base.orm.Page;
import com.diiwon.mis.plat.base.pojo.TMisUser;
import com.diiwon.mis.plat.base.utils.DateUtils;
import com.diiwon.mis.plat.base.utils.StringUtilsExt;
import com.diiwon.mis.plat.base.utils.SysCode;
import com.diiwon.mis.plat.xmgl.pojo.TBaeGczj;
import com.diiwon.mis.plat.xmgl.pojo.TbaeDxsc;
import com.diiwon.mis.plat.xmgl.pojo.TextImpdata;
import com.diiwon.mis.plat.xmgl.pojo.TmisZd;

@Repository
public class GcbjDao extends HibernateEntityDao<Object> {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
    public void setSuperSessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }
	

	/*******************************		查看字典表开始		********************/
	public List<TmisZd> getTmisZd(String dmcode, String depcode) {
		String hql = " from TmisZd t where 1=1 and t.dmCode= ? and t.depCode= ? and isDel = 1 ";
		
		return this.find(hql, dmcode, depcode);
	}
	
	public List<?> jsonUploadfile(String gcid) {
		StringBuilder sb = new StringBuilder();
		List<String> param = new ArrayList<String>();
		sb.append(" select dm_code, dm_name, t2.* from ");
		sb.append(" (select * from t_mis_zd where parent_id = ? and is_del = 1) t1 ");
		param.add(SysCode.SYS_SCFJ);
		sb.append(" left join ");
		sb.append(" (select * from t_ext_impdata where 1=1 and is_del = 1 ");
		if(StringUtilsExt.isNotBlank(gcid)) {
			sb.append(" and gcid = ? ");
			param.add(gcid);
		}
		else {
			sb.append(" and 1=2 ");
		}
		sb.append(" ) t2 ON dm_code = xmlx order by t1.pxh");
		
		return this.jdbcTemplate.queryForList(sb.toString(), param.toArray());
	}
	/*******************************		查看字典表结束		********************/

	/***
	 * 根据条件查询主表记录
	 * **/
	public List<?> querylistTBaeGczj(TBaeGczj tBaeGczj, String fg) {
		StringBuilder sb = new StringBuilder();
		sb.append(" select * from t_bae_gczj where 1=1 ");
		if(StringUtilsExt.isNotBlank(tBaeGczj.getId())) {
			sb.append(" and id in (").append(tBaeGczj.getId()).append(")");
		}
		if(StringUtilsExt.isNotBlank(tBaeGczj.getSjzt()) && StringUtilsExt.isNotBlank(fg)) {
			sb.append(" and  sjzt not in (").append(tBaeGczj.getSjzt()).append(") ");
		}
		
		return this.jdbcTemplate.queryForList(sb.toString());
	}
	
	
	public String getTbaeGczjSql() {
		StringBuilder comSql = new StringBuilder();
		comSql.append(" select t1.id,t1.gcmc,t1.qu,t1.lu,t1.hao,t1.tzlb,t1.yszj,t1.jzmj,t1.jgcc,t1.pmzj,t1.jclx,t1.sjzt,");
		comSql.append(" t1.gclb,t1.jnbz,t1.lsjz,t1.xj,t1.tyngr,t1.tyngf,t1.dyrb,t1.kgsj,t1.jgsj,t1.cjr,t1.cjsj,t1.xmlx,t1.bz,t1.zcbm,");
		comSql.append(" t3.sjc,t3.gdmb,t3.gkzy,t3.pj,t3.rhdt,t3.td,t3.wxqt,t3.ljgyx,t3.ljjmq,t3.zbqt, ");
		comSql.append(" t2.jsdwmc,t2.jsxmfzr,t2.jsdjzs,t2.jslxdh,t2.kcdwmc,t2.kcxmfzr,t2.kcdjzs,t2.");
		comSql.append(" kczgzs,t2.kclxdh,t2.sjdwmc,t2.sjxmfrz,t2.sjdjzs,t2.sjzgzs,t2.sjlxdh,t2.jldwmc,");
		comSql.append(" t2.jlxmfzr,t2.jldjzs,t2.jlzgzs,t2.jllxdh,t2.sgdwmc,t2.sgxmjl,t2.sgdjzs,t2.sgaqxkz,t2.sggwzs,t2.sgkhzh,");
		comSql.append(" t2.sglxdh,t2.sgxcaqy,t2.sgaqyzs,t2.sgtscdw,t2.gsbxbm,t2.sbbdh,t2.bfje,t2.jsdwdb,t2.jdjg,t2.jdlxdh,");
		comSql.append(" t2.jdzch,t2.scyj,t2.scr,t2.scrq,t2.bjyj,t2.bjrq ");
		comSql.append(" from t_bae_gczj t1 left join t_bae_gczjdw t2 on t1.id=t2.gcid left join t_bae_gczjsx t3 on t1.id=t3.gcid ");
		comSql.append(" where 1=1 ");
		
		return comSql.toString();
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> lisTbaeGczj(TBaeGczj tBaeGczj) {
		StringBuilder sb = new StringBuilder();
		List<String> param = new ArrayList<String>();
		sb.append(getTbaeGczjSql());
		if(StringUtilsExt.isNotBlank(tBaeGczj.getId())) {
			sb.append(" and t1.id = ? ");
			param.add(tBaeGczj.getId());
		}
		
		return this.jdbcTemplate.queryForList(sb.toString(), param.toArray());
	}
	
	/**
	 * 查询datagrid 表格记录
	 * @param tMisUser 
	 * flag:0 为企业查询
	 * ***/
	@SuppressWarnings({ "unchecked", "deprecation" })
	public Page<Map<String, String>> jsonPageList(Page<Map<String, String>> page, 
			TBaeGczj tBaeGczj, TMisUser user, String dmco) {
		StringBuilder comSql = new StringBuilder();
		List<String> param = new ArrayList<String>();
		comSql.append(getTbaeGczjSql());
		
		if(StringUtilsExt.isNotBlank(tBaeGczj.getXmlx())) {
			comSql.append(" and xmlx = ? ");
			param.add(tBaeGczj.getXmlx());
		}
		
		if(StringUtilsExt.isNotBlank(tBaeGczj.getKgsj())) {
			comSql.append(" and kgsj like ? ");
			param.add(tBaeGczj.getKgsj()+"%");
		}
		if(StringUtilsExt.isNotBlank(tBaeGczj.getKgsj())) {
			comSql.append(" and cjsj like ? ");
			param.add(tBaeGczj.getCjsj()+"%");
		}
		if(StringUtilsExt.isNotBlank(tBaeGczj.getGcmc())) {
			comSql.append(" and gcmc like ? ");
			param.add("%"+tBaeGczj.getGcmc()+"%");
		}
		if(StringUtilsExt.isNotBlank(tBaeGczj.getSjzt())) {
			comSql.append(" and sjzt like ? ");
			param.add("%"+tBaeGczj.getSjzt()+"%");
		}
		if(0 == user.getRoles().size() &&
				null == UserProperties.getZD_CACHE().get((dmco))) {
			/***没有角色人员将不给查看数据*/
			comSql.append(" 1=2 ");
		}
		
		if(0 != user.getRoles().size()
				&& null != UserProperties.getZD_CACHE().get(dmco)) {
			comSql.append(" and t1.sjzt in (");
			comSql.append(UserProperties.getZD_CACHE().get(dmco).get(0).getStatus());
			comSql.append(") ");
		}
		
		if(StringUtilsExt.equals(dmco, SysCode.SYS_QYLR)) {/**企业提报**/
			comSql.append(" and cjr = ?");
			param.add(user.getLoginname());
			comSql.append(" order by field(sjzt, id, 4, 2, 1, 3, 5, 0) DESC, cjsj DESC");
		}else if(StringUtilsExt.equals(dmco, SysCode.SYS_SCYSCTG)) {/**住建局审查**/
			comSql.append(" order by field(sjzt, id, 3, 5, 4, 2, 1) DESC, cjsj DESC");
		}else if(StringUtilsExt.equals(dmco, SysCode.SYS_SCZCM)) {/**住建局审批**/
			comSql.append(" order by field(sjzt, id, 4,5,2) DESC, zcbm desc ");
		}
		
		
		String sqlCount = " select count(1) from (" + comSql.toString() +") tt" ;
		
		int firstResult = (page.getCurrentPageNo() - 1) * page.getPageSize();
		int maxResults = page.getPageSize();
		
		String pageSql = "select * from ("+ comSql.toString()+ ") t2 limit " + firstResult + "," + maxResults;
		
		List<Map<String, String>> data = (List<Map<String, String>>)this.jdbcTemplate.queryForList(pageSql, param.toArray());
		int total = this.jdbcTemplate.queryForInt(sqlCount, param.toArray());
		
		page.setTotalCount(total);
		page.setResult(data);
		return page;
	}

	
	/**
	 * ------------------------质安报监删除 开始
	 * @param id
	 * @param tMisUser 
	 * @return
	 */
	public void delGong(String id) {
		String sql = "delete from t_bae_gczjdw where gcid in ("+id+") ";
	    this.jdbcTemplate.update(sql);
	}
		
	public void delGong1(String id) {
		String sql = "delete from t_bae_gczjsx where gcid in ("+id+") ";
	    this.jdbcTemplate.update(sql);
	}
		
	public int delGong2(String id, TMisUser tMisUser) {
		String sql = "delete from t_bae_gczj where id in ("+id+") and cjr = '"+tMisUser.getLoginname()+"'";
		return this.jdbcTemplate.update(sql);
	}
	
	/**
	 * ------------------------质安报监删除结束
	 */
	
	public List<?> jsonPagelcjlList(TBaeGczj tBaeGczj, String flag) {
		StringBuilder sb = new StringBuilder();
		sb.append(" select t1.*, t2.username name from t_tlp_lcjl t1 left join t_mis_user t2 on t1.user=t2.loginname ");
		sb.append(" where 1=1 and t1.gcid = ? and t1.flag= ?");
		return this.jdbcTemplate.queryForList(sb.toString(), new Object[] {tBaeGczj.getId(), flag});
	}
	
	public List<?> dygridJsondata() {
		StringBuilder sb = new StringBuilder();
		sb.append(" select t1.xh, t2.gcmc, t2.mj, t2.jglx, t2.gclb, t2.gcsj, ");
		sb.append(" t2.jdzch from t_bae_dtgc_dual t1 left join t_bae_dtgc t2 on t1.xh=t2.xh ");
		return this.jdbcTemplate.queryForList(sb.toString());
	}

	/****
	 * 流程管理记录
	 * @param sql
	 */
	public void batchUpdateSql(String[] sql) {
		this.jdbcTemplate.batchUpdate(sql);
	} 


	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> zxshJsonList(TBaeGczj tBaeGczj) {
		String sql = " select * from t_mis_scpz where xmlx=? order by px ";
		
		return (List<Map<String, Object>>)this.jdbcTemplate.queryForList(sql, new Object[]{tBaeGczj.getXmlx()});
	}
	
	public List<?> dxscJsonList(TBaeGczj tBaeGczj) {
		StringBuilder sb = new StringBuilder();
		sb.append(" select t1.xmlx, t1.scmc, t1.sclm, t1.table_bg, t1.yjml,  ");
		sb.append(" t2.id, t2.gcid,t2.scsj, t2.sccz, t2.scyj, t2.scrq, t2.scr, t2.is_old  ");
		sb.append(" from t_mis_scpz t1 left join t_bae_dxsc t2 on t1.sclm = t2.sclm where 1=1 ");
		sb.append(" and t2.is_old = 1 and t2.gcid=? and xmlx=? ");
		
		return this.jdbcTemplate.queryForList(sb.toString(), new Object[] {tBaeGczj.getId(), tBaeGczj.getXmlx()});
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> scpzJsonList(String xmlx) {
		String sql = " select * from t_mis_scpz where xmlx = ? ";
		
		return this.jdbcTemplate.queryForList(sql, new Object[] {xmlx});
	}

	
	/*******************************		更改逐项审查表开始		********************/
	/***查看是否存在不通过逐项审查项目**/
	@SuppressWarnings("deprecation")
	public int queryDxsc(String id, String sccz) {
		String sql = " select count(1) from t_bae_dxsc where gcid = ? and is_old=1 and sccz=? ";
		
		return this.jdbcTemplate.queryForInt(sql, new Object[] {id, sccz});
	}
	
	/*** 查看是否存在不通过附件****/
	@SuppressWarnings("deprecation")
	public int queryImpdata(String id, String sccz) {
		String sql = " select count(1) from t_ext_impdata where gcid = ? and is_del=1 and sccz=? ";
		
		return this.jdbcTemplate.queryForInt(sql, new Object[] {id, sccz});
	}
	
	/***根据ID 将逐项审查表数据改为，old*/
	public void updateDxsc(String id) {
		String sql = "update t_bae_dxsc set is_old=0 where gcid= ? ";
		this.jdbcTemplate.update(sql, new Object[] {id});
		
	}
	
	/**
	 * 通过Id更改tname中sccz的值且值为vl
	 * **/
	public void zxscSave(String id, String vl, String sccz, String tname) {
		StringBuffer sb = new StringBuffer();
		sb.append(" update ").append(tname).append(" set ").append(sccz);
		sb.append(" = '").append(vl).append("' where id='").append(id).append("'");
		this.jdbcTemplate.update(sb.toString());
	}
	
	/***修改主表状态
	 * @param dmco *****/
	public int updateGczj(String id, String vl, String dmco, String scyj, String userName) {
		StringBuilder sb = new StringBuilder();
		if(StringUtilsExt.isNotBlank(dmco)) {
			sb.append("update ").append(UserProperties.getZD_CACHE().get(dmco).get(0).getDepCode());
		}
		else {
			sb.append("update t_bae_gczj ");
		}
		sb.append(" set sjzt = ? where id = ? ");
		
		//向工程中加入审查信息
		StringBuilder sb1 = new StringBuilder();
		sb1.append(" update ").append(UserProperties.getZD_CACHE().get(dmco).get(0).getDepCode()).append("dw");
		sb1.append(" set scyj = ?, scr = ?, scrq = ? where gcid = ? ");
		
		String date = DateUtils.thisFormatDate(SysCode.RQGSH_HXGS_DRQ);
		this.jdbcTemplate.update(sb1.toString(),new Object[] {scyj, userName, date, id});
		return this.jdbcTemplate.update(sb.toString(), new Object[] {vl, id});
	}
	
	
	public void insertLcjl(String gcid, String depcode, String depname, TMisUser user, String flag) {
		StringBuilder sb = new StringBuilder();
		sb.append(" insert into t_tlp_lcjl(gcid, action, memo, user, time, flag) VALUES('");
		sb.append(gcid).append("','");
		sb.append(depcode).append("','");
		sb.append(depname).append("','");
		sb.append(user.getLoginname()).append("','");
		sb.append(DateUtils.formatDate(SysCode.RQGSH_HXGS_CRQ)).append("','");
		sb.append(flag).append("')");
		this.jdbcTemplate.execute(sb.toString());
	}


	/***更改上传附件状态***/
	public void updateIsdel(TextImpdata textImpdata) {
		String sql;
		if(StringUtilsExt.equals(textImpdata.getUpty(), SysCode.UPLOAD_HTWJ)) {
			sql = " update t_ext_impdata set is_del = ? where gcid= ? and upty=? and xmlx= ? ";
			this.jdbcTemplate.update(sql, new Object[] {SysCode.SJZT_DEFAULT, textImpdata.getGcid(),
					textImpdata.getUpty(), textImpdata.getXmlx()});
		}
		else {
			sql = " update t_ext_impdata set is_del = ? where id = ? ";
			this.jdbcTemplate.update(sql, new Object[] {SysCode.SJZT_DEFAULT, textImpdata.getId()});
		}
	}

	public void saveOnAfterEdit(String sql) {
		this.jdbcTemplate.update(sql);
	}
	
	public int delefjxx(String id) {
		String sql = "delete from t_ext_impdata where gcid in ("+id+") ";
	    return this.jdbcTemplate.update(sql);
	}


	/**   
	* @Function: GcbjDao.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: Mr.Yang
	* @date: 2018年6月19日 下午5:57:54 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2018年6月19日     Mr.Yang           v1.0.0               修改原因
	*/
	@SuppressWarnings("deprecation")
	public String selMaxNum() {
		String sql="SELECT MAX(zcbm) from t_bae_gczj";
		 int num=this.jdbcTemplate.queryForInt(sql);
		return num+"";
	}

	public String queryMaxnum(String dmco, String year) {
		StringBuilder sb = new StringBuilder();
		sb.append(" select max(zcbm) from ").append(UserProperties.getZD_CACHE().get(dmco).get(0).getDepCode());
		sb.append(" where zcbm like ? and xmlx = ? ");
		
		return (String)this.jdbcTemplate.queryForObject(sb.toString(), new Object[] {"%"+year+"%", dmco}, String.class);
	}


	/**   
	* @Function: GcbjDao.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: Mr.Yang
	* @date: 2018年6月22日 下午2:10:08 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2018年6月22日     Mr.Yang           v1.0.0               修改原因
	*/
	@SuppressWarnings("unchecked")
	public List<TextImpdata> fjList(String id) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select t1.*, t2.dm_name filename from t_ext_impdata t1 left join t_mis_zd t2 on dm_code = xmlx ");
		sb.append(" where t2.parent_id = ? and t1.is_del = 1 and t1.gcid= ? ");
		return this.jdbcTemplate.queryForList(sb.toString(), new Object[] {SysCode.SYS_SCFJ, id});
	}


	@SuppressWarnings("unchecked")
	public List<TbaeDxsc> getCheckDxscsj(String id) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select sclm, scsj, scyj, scrq from t_bae_dxsc where gcid = ?");
		sb.append(" and sccz =0 and is_old=1 ");
		return this.jdbcTemplate.queryForList(sb.toString(), new Object[] {id});
	}
}
