package com.diiwon.mis.plat.frame.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.diiwon.mis.plat.base.dao.HibernateEntityDao;
import com.diiwon.mis.plat.base.dao.JdbcTemplate;

@Repository
public class FrameDao extends HibernateEntityDao<Object> {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * JdbcTemplate使用例子(查询数据库时间)
	 * @return
	 */
	public String findDbDate() {
		StringBuilder sb = new StringBuilder();
		sb.append(" select now() as systemtime ");
		
		if (logger.isDebugEnabled()) {
			logger.debug("UserDaoImpl.findDbDate():" + sb.toString());
		}
		
		return (String)this.jdbcTemplate.queryForObject(sb.toString(), String.class);
	}
	
	
}
