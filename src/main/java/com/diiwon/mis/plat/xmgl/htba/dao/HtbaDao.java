package com.diiwon.mis.plat.xmgl.htba.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.diiwon.mis.plat.base.cache.imp.UserProperties;
import com.diiwon.mis.plat.base.dao.HibernateEntityDao;
import com.diiwon.mis.plat.base.dao.JdbcTemplate;
import com.diiwon.mis.plat.base.orm.Page;
import com.diiwon.mis.plat.base.pojo.TMisUser;
import com.diiwon.mis.plat.base.utils.StringUtilsExt;
import com.diiwon.mis.plat.base.utils.SysCode;
import com.diiwon.mis.plat.xmgl.pojo.TextImpdata;

@Repository
public class HtbaDao extends HibernateEntityDao<Object>{

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@SuppressWarnings({ "unchecked"})
	public Page<Map<String, String>> jsonPagehtbaList(Page<Map<String, String>> page, 
			TMisUser user, String cjsj, String gcmc) {
		StringBuilder sb = new StringBuilder();
		List<String> param = new ArrayList<String>();
		sb.append(" select id, gcmc, group_concat(qu, '区', lu, '路', hao, '号') as gcdd, ");
		sb.append(" kgsj, xmlx, sjzt, cjr, 't_bae_gczj' tname, zcbm ");
		sb.append(" from t_bae_gczj where cjr=? and sjzt=4 ");
		param.add(user.getLoginname());
		if(StringUtilsExt.isNotBlank(cjsj)) {
			sb.append(" and cjsj like ? ");
			param.add(cjsj+"%");
		}
		if(StringUtilsExt.isNotBlank(gcmc)) {
			sb.append(" and gcmc like ? ");
			param.add("%"+gcmc+"%");
		}
		sb.append(" group by id ");
		sb.append(" UNION ");
		sb.append(" select id, gcmc, gcdd, jhkgrq as kgsj, xmlx, sjzt, cjr,  ");
		sb.append(" 't_bae_cczj' tname, zcbm from t_bae_cczj where cjr=? and sjzt=4 ");
		if(StringUtilsExt.isNotBlank(cjsj)) {
			sb.append(" and cjsj like ? ");
			param.add(cjsj+"%");
		}
		if(StringUtilsExt.isNotBlank(gcmc)) {
			sb.append(" and gcmc like ? ");
			param.add("%"+gcmc+"%");
		}
		sb.append(" order by zcbm desc ");
		param.add(user.getLoginname());
		
		String sqlCount = " select count(1) from (" + sb.toString() +") tt" ;
		
		int firstResult = (page.getCurrentPageNo() - 1) * page.getPageSize();
		int maxResults = page.getPageSize();
		
		String pageSql = "select * from ("+ sb.toString()+ ") t2 limit " + firstResult + "," + maxResults;
		
		List<Map<String, String>> data = (List<Map<String, String>>)this.jdbcTemplate.queryForList(pageSql, param.toArray());
		int total = this.jdbcTemplate.queryForObject(sqlCount, param.toArray(), Integer.class);
		
		page.setTotalCount(total);
		page.setResult(data);
		return page;
	}

	public int queryHtscwj(TextImpdata textImpdata) {
		StringBuilder sb = new StringBuilder();
		List<String> param = new ArrayList<String>(); 
		sb.append("select count(1) from t_ext_impdata where gcid=? and upty=? and is_del=1 ");
		param.add(textImpdata.getGcid());
		param.add(textImpdata.getUpty());
		if(StringUtilsExt.isNotBlank(textImpdata.getXmlx())) {
			sb.append("and xmlx=? ");
			param.add(textImpdata.getXmlx());
		}
		if(StringUtilsExt.isNotBlank(textImpdata.getScry())) {
			sb.append("and scry=? ");
			param.add(textImpdata.getScry());
		}
		
		return this.jdbcTemplate.queryForObject(sb.toString(), param.toArray(), Integer.class);
	}
	
	public List<?> htList(TextImpdata textImpdata, String pzmc) {
		StringBuilder sb = new StringBuilder();
		List<String> param = new ArrayList<String>(); 
		sb.append(" select t1.*, t2.dm_name dmname, t3.username from t_ext_impdata t1 left JOIN  ");
		sb.append(" t_mis_zd t2 on t1.xmlx=t2.dm_code ");
		sb.append(" left join t_mis_user t3 on t1.scry = t3.loginname ");
		sb.append(" where t1.is_del='1' and t1.upty=? and t1.gcid=? order by t2.pxh");
		param.add(pzmc);
		param.add(textImpdata.getGcid());
		return this.jdbcTemplate.queryForList(sb.toString(), param.toArray());
	}

	public void updateImpSjzt(TextImpdata textImpdata) {
		StringBuilder sb = new StringBuilder();
		sb.append(" update t_ext_impdata set sccz = '");
		sb.append(UserProperties.getZD_CACHE().get(SysCode.SYS_HTQYSB).get(0).getDepCode()).append("' ");
		sb.append(" where 1=1 upty=? and gcid=? ");
		
		this.jdbcTemplate.update(sb.toString(), new Object[] {textImpdata.getUpty(), textImpdata.getGcid()});
	}
}
