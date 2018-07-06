package com.diiwon.mis.plat.xtgl.qxgl.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.diiwon.mis.plat.base.dao.JdbcTemplate;
import com.diiwon.mis.plat.base.dao.imp.BaseDaoImpl;
import com.diiwon.mis.plat.base.pojo.TMisQx;
import com.diiwon.mis.plat.base.pojo.TMisUser;
import com.diiwon.mis.plat.base.utils.Common;


@Repository
public class QxglDao extends BaseDaoImpl<Object>{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * 查询：MENU级权限数据信息
	 * @return
	 */
	public List<TMisQx> queryQxMenu() {
		StringBuilder hql = new StringBuilder();
		hql.append("from TMisQx t where t.authType='MENU' order by t.sortOrder desc");
		return super.find(hql.toString());
	}

	public List<TMisQx> querySecondMenu() {
		StringBuilder hql = new StringBuilder();
		hql.append("from TMisQx t where t.authType='URL' and t.status='0' order by t.sortOrder asc");
		return super.find(hql.toString());
	}
	
	@SuppressWarnings("unchecked")
	public List<?> queryQxglAll(TMisUser user) {
		String sql = "select * from t_mis_qx t order by sort_Order asc";
		
		return (List<TMisQx>)this.jdbcTemplate.queryForListEn(sql,new String[]{});
	}
	
	/**
	 * 按照对应人员查询相应的菜单
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<TMisQx> queryQxAll(TMisUser user) {
		StringBuilder sql = new StringBuilder();
		if(Common.BlenAdmin(user.getLoginname())) {
			sql.append(" select * from t_mis_qx t order by sort_Order asc");
			return this.jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(TMisQx.class));
		}
		else {
		
			sql.append("select a.* from t_mis_qx a ");
			sql.append("where exists ( select 1 from t_mis_jsqx b where exists ( ");
			sql.append("select 1 from t_mis_jsry c where ryid = ? and b.role_code=c.jsid ");
			sql.append(") and a.auth_code=b.auth_code ) order by a.sort_Order asc") ;
			return this.jdbcTemplate.query(sql.toString(), new Object[] {user.getUuid()}, new BeanPropertyRowMapper(TMisQx.class));
		}
	}

	public TMisQx getTQx(String propertyName, Object value) {
		return super.findUnique(TMisQx.class, propertyName, value);
	}

	/**
	 * 删除：依据传入的AUTH_CODE权限对象
	 * @param authCode
	 */
	@Transactional
	public void dropTMisQx(String idName, String idValue) throws Exception {
		try {
			super.remove(this.findUnique(TMisQx.class, idName, idValue));
			//this.pci.update();
		}
		catch (Exception e) {
			throw e;
		}
	}
	
	public void delAuthRole(String authcode) {
		String sql = "delete from t_mis_qx where auth_code=?";
		
		this.jdbcTemplate.update(sql, new Object[] {authcode});
	}
	
	public List<TMisQx> queryTQxList(String flag) {
		String hql = "from TMisQx t where flag= ? or flag=0 order by t.sortOrder desc";
		return this.find(hql, new Object[] {flag});
	}
}
