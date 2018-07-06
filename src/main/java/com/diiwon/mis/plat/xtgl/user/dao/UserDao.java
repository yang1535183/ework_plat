package com.diiwon.mis.plat.xtgl.user.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.diiwon.mis.plat.base.dao.HibernateEntityDao;
import com.diiwon.mis.plat.base.dao.JdbcTemplate;
import com.diiwon.mis.plat.base.orm.Page;
import com.diiwon.mis.plat.base.pojo.TMisJs;
import com.diiwon.mis.plat.base.pojo.TMisUser;

@Repository
public class UserDao extends HibernateEntityDao<Object> {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
    public void setSuperSessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }
	
	public TMisUser checkmactuser(String mac) {
		return this.findUnique(TMisUser.class, "mac", mac);
	}
	
	public TMisUser queryTuser(String loginname) {
		return this.findUnique(TMisUser.class, "loginname", loginname);
	}
	
	public TMisUser queryTuserByUuid(String uuid) {
		return this.findUnique(TMisUser.class, "uuid", uuid);
	}
	
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public Page<Map<String, String>> queryUserPageList(Page<Map<String, String>> page, TMisJs tMisJs, TMisUser user) {
		List<String> param = new ArrayList<String>();
		StringBuilder comSql = new StringBuilder();
		comSql.append("select t1.*, t2.adname, t2.adcode, t3.jsid, t4.role_name from ");
		comSql.append(" t_mis_user t1 left JOIN admdivision t2  ");
		comSql.append(" on t1.qhdm=t2.adcode LEFT JOIN t_mis_jsry t3 ON t1.uuid = t3.ryid ");
		comSql.append(" LEFT JOIN t_mis_js t4 on t3.jsid=t4.role_code ");
		comSql.append("  where loginname !='admin' order by t1.qhdm, loginname ");
		
		String sqlCount = "select count(1) from (" + comSql.toString() +") t1" ;
		
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
	 * 修改密码
	 */
	public void updateLoginpass(String loginpass, String uuid) {
		String sql = "update t_mis_user set loginpass = '"+loginpass+"' where uuid = '"+uuid+"'";
		
		this.jdbcTemplate.execute(sql);
	}

	public void deleUser(String ryid) {
		
		String sql = "delete from t_mis_user where uuid=? ";
		this.jdbcTemplate.update(sql, new Object[] {ryid});
	}

	/**   
	* @Function: UserDao.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: Mr.Yang
	* @date: 2018年6月11日 上午10:41:14 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2018年6月11日     Mr.Yang           v1.0.0               修改原因
	*/
	public void updatePwd(TMisUser tMisUser, String newPwd) {
		String uuid=tMisUser.getUuid();
		String sql = "update t_mis_user set loginpass = '"+newPwd+"' where uuid = '"+uuid+"'";
		this.jdbcTemplate.execute(sql);
	}
}
