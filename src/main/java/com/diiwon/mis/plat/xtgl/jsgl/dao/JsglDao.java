package com.diiwon.mis.plat.xtgl.jsgl.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.diiwon.mis.plat.base.dao.HibernateEntityDao;
import com.diiwon.mis.plat.base.dao.JdbcTemplate;
import com.diiwon.mis.plat.base.orm.Page;
import com.diiwon.mis.plat.base.pojo.TMisJs;
import com.diiwon.mis.plat.base.pojo.TMisQx;
import com.diiwon.mis.plat.base.pojo.TMisUser;
import com.diiwon.mis.plat.base.utils.StringUtilsExt;
import com.diiwon.mis.plat.base.utils.SysCode;

@Repository
public class JsglDao extends HibernateEntityDao<Object> {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 载入：依据传入的属性名和属性值返回角色对象
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public TMisJs getTMisJsUniqueBy(String propertyName, Object value) {
		return this.findUnique(TMisJs.class, propertyName, value);
	}
	
	/**
	 * 删除：依据传入的UUID角色数据信息
	 * @param ids
	 */
	@Transactional(readOnly = false)
	public void dropJs(TMisJs tMisJs){
		try {
			this.getHibernateTemplate().delete(tMisJs);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 载入：依据传入的属性名和属性值返回权限对象
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public TMisQx getTMisQxUniqueBy(String propertyName, Object value) {
		return super.findUnique(TMisQx.class, propertyName, value);
	}
	
	public List<?> queryTMisJs() {
		StringBuilder sb = new StringBuilder();
		sb.append(" select uuid id, role_name text, role_type from t_mis_js where 1=1 ");
		
		return this.jdbcTemplate.queryForList(sb.toString());
	}
	
	public Page<TMisJs> queryTmisjsPage(Page<TMisJs> dataList) {
		StringBuilder hql = new StringBuilder();
		hql.append(" from TMisJs t where 1=1 and role_type != ? ");	//屏蔽内置管理员admin
		if (logger.isDebugEnabled()) {
			logger.debug("JsglDao.queryTmisjsPage.hql:"+hql.toString());
		}
		
		return this.findPage(dataList, hql.toString(), new String[]{SysCode.ROLE_TYPE_SYSTEM});
	}
	
	@Transactional
	public void saveJsqx(final String jsid, final String[] qxidlist) {
		String sql = "insert into t_mis_jsqx(uuid,jsid,authcode) values(sys_guid(),?,?)";
		
		BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter() {
			public void setValues(PreparedStatement ps, int i)
					throws SQLException {
				ps.setString(1, jsid);
				ps.setString(2, qxidlist[i]);
			}

			public int getBatchSize() {
				return qxidlist.length;
			}
		};
		
		this.jdbcTemplate.batchUpdate(sql, setter);
	}
	
	public void delJs(String roleCode) {
		String sql = "delete from t_mis_js where role_code = ?";
		
		this.jdbcTemplate.update(sql, new Object[] {roleCode});
	}
	
	public void delJsqx(String roleCode) {
		String sql = "delete from t_mis_jsqx where role_code = ?";
		
		this.jdbcTemplate.update(sql, new Object[] {roleCode});
	}

	@SuppressWarnings("unchecked")
	public Page<Map<String, String>> queryRyByjs(Page<Map<String, String>> page, String rolecode) {
		List<String> param = new ArrayList<String>();
		StringBuilder comSql = new StringBuilder();
		comSql.append("from t_mis_user a, admdivision b ");
		comSql.append("where a.qhdm = b.adcode and a.uuid in ");
		comSql.append("(select ryid from t_mis_jsry where 1=1 ");
		if(StringUtilsExt.isBlank(rolecode)) {
			comSql.append("and 1=2  )");
		}
		else {
			comSql.append("and jsid=? )");
			param.add(rolecode);
		}
		
		String orderby = " order by a.qhdm";
		
		String sql = "select a.uuid,a.username,b.adname " + comSql.toString() + orderby;
		
		String sqlCount = "select count(1) " + comSql.toString();
		
		int firstResult = (page.getCurrentPageNo() - 1) * page.getPageSize();
		int maxResults = page.getPageSize();
		
		String pageSql = "select * from ("+ sql+ ") t2 limit " + firstResult + "," + maxResults;
		
		List<Map<String, String>> data = (List<Map<String, String>>) this.jdbcTemplate.queryForList(pageSql, param.toArray());
		int total = this.jdbcTemplate.queryForInt(sqlCount, param.toArray());
		
		page.setTotalCount(total);
		page.setResult(data);
		
		return page;
	}
}
