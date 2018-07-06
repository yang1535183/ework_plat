package com.diiwon.mis.plat.xmgl.lcjk.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.diiwon.mis.plat.base.dao.HibernateEntityDao;
import com.diiwon.mis.plat.base.dao.JdbcTemplate;
import com.diiwon.mis.plat.base.pojo.TMisLcjl;

/***
 * 流程监控类
 * 
 * @author plp
 *
 */
@Repository
public class LcjkDao extends HibernateEntityDao<Object> {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/****
	 * @param gcid：工程ID
	 * @param flag：0：报监流程，1：合同流程
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<TMisLcjl> getAllLcjlByGcid(String gcid, String flag) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from (select tt1.*, cast((@rownum := @rownum + 1) AS UNSIGNED INT ) AS pxh ");
		sql.append(" from ( SELECT t1.*, t2.username FROM t_tlp_lcjl t1 LEFT JOIN t_mis_user t2 ON t1. USER = t2.loginname ");
		sql.append(" WHERE t1.gcid = ? and t1.flag=? ORDER BY t1.time ASC) tt1, ");
		sql.append(" (SELECT @rownum := 0) b ) ttt1 order by time desc");
		return this.jdbcTemplate.queryForList(sql.toString(), new Object[] {gcid, flag});
	}
}
