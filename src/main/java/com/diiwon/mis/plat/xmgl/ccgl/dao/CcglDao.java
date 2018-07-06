/**   
 * 功能描述：
 * @Package: com.diiwon.mis.plat.xmgl.ccgl.dao 
 * @author: Mr.Yang   
 * @date: 2018年6月13日 上午10:34:28 
 */
package com.diiwon.mis.plat.xmgl.ccgl.dao;

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
import com.diiwon.mis.plat.xmgl.pojo.TBaeCczj;

/**    
* @ClassName: CcglDao.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: Mr.Yang
* @date: 2018年6月13日 上午10:34:28 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2018年6月13日     Mr.Yang           v1.0.0               修改原因
*/
@Repository
public class CcglDao extends HibernateEntityDao<Object>{
	/**   
	* @Function: GcbjDao.java
	* @Description: 分页数据
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: Mr.Yang
	* @date: 2018年6月12日 下午3:32:35 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2018年6月12日     Mr.Yang           v1.0.0               修改原因
	*/
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public String getTbaeCczj() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT t1.*,t2.* FROM t_bae_cczj t1 LEFT JOIN t_bae_cczjdw t2 on t1.id=t2.gcid where 1=1");
		return sql.toString();
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getTbaeCczj(TBaeCczj tBaeCczj) {
		StringBuilder sb = new StringBuilder();
		List<String> param = new ArrayList<String>();
		sb.append(getTbaeCczj());
		if(StringUtilsExt.isNotBlank(tBaeCczj.getId())) {
			sb.append(" and t1.id = ? ");
			param.add(tBaeCczj.getId());
		}
		
		return this.jdbcTemplate.queryForList(sb.toString(), param.toArray());
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	public Page<Map<String, String>> CcJsonPageList(Page<Map<String, String>> page, TBaeCczj tBaeCczj, 
			TMisUser user, String dmco) {
		StringBuilder sql = new StringBuilder();
		List<String> param = new ArrayList<String>();
		
		sql.append(getTbaeCczj());
		if (StringUtilsExt.isNotBlank(tBaeCczj.getJhkgrq())) {
			sql.append(" and t1.jhkgrq like ?");
			param.add(tBaeCczj.getJhkgrq()+"%");
		}
		if (StringUtilsExt.isNotBlank(tBaeCczj.getGcmc())) {
			sql.append(" and t1.gcmc like ? ");
			param.add("%"+tBaeCczj.getGcmc()+"%");
		}
		if(StringUtilsExt.isNotBlank(tBaeCczj.getCjsj())) {
			sql.append(" and cjsj like ? ");
			param.add(tBaeCczj.getCjsj()+"%");
		}
		if(StringUtilsExt.isNotBlank(tBaeCczj.getSjzt())) {
			sql.append(" and sjzt like ? ");
			param.add(tBaeCczj.getSjzt()+"%");
		}
		if(0 == user.getRoles().size() &&
				null == UserProperties.getZD_CACHE().get((dmco))) {
			/***没有角色人员将不给查看数据*/
			sql.append(" 1=2 ");
		}
		
		if(0 != user.getRoles().size()
				&& null != UserProperties.getZD_CACHE().get(dmco)) {
			sql.append(" and t1.sjzt in (");
			sql.append(UserProperties.getZD_CACHE().get(dmco).get(0).getStatus());
			sql.append(") ");
		}
		
		if(StringUtilsExt.equals(dmco, SysCode.SYS_QYLR)) {/**企业提报**/
			sql.append(" and cjr = ?");
			param.add(user.getLoginname());
			sql.append(" order by field(sjzt, id, 4, 2, 1, 3, 5, 0) DESC, cjsj DESC ");
		}else if(StringUtilsExt.equals(dmco, SysCode.SYS_SCYSCTG)) {/**住建局审查**/
			sql.append(" order by field(sjzt, id, 4, 2, 3, 5, 1) DESC, cjsj DESC ");
		}else if(StringUtilsExt.equals(dmco, SysCode.SYS_SCZCM)) {/**住建局审批**/
			sql.append(" order by field(sjzt, id, 4,5,2) DESC, zcbm desc ");
		}
		
		String sqlCount = " select count(*) from (" + sql.toString() +") tCount" ;
		
		int firstResult = (page.getCurrentPageNo() - 1) * page.getPageSize();
		int maxResults = page.getPageSize();
		
		String pageSql = "select * from ("+ sql.toString()+ ") tPage limit " + firstResult + "," + maxResults;
		
		List<Map<String, String>> data = (List<Map<String, String>>)this.jdbcTemplate.queryForList(pageSql, param.toArray());
		int total = this.jdbcTemplate.queryForInt(sqlCount, param.toArray());
		
		page.setTotalCount(total);
		page.setResult(data);
		return page;
	}

	/**   
	* @Function: CcglDao.java
	* @Description: 拆除主表
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: Mr.Yang
	* @date: 2018年6月13日 下午5:27:07 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2018年6月13日     Mr.Yang           v1.0.0               修改原因
	*/
	public int deleCcbj(String id, TMisUser tMisUser) {
		String sql = "delete from t_bae_cczj where id in ("+id+")";
		return this.jdbcTemplate.update(sql);
	}

	/**   
	* @Function: CcglDao.java
	* @Description: 拆除从表
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: Mr.Yang
	* @date: 2018年6月13日 下午5:27:32 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2018年6月13日     Mr.Yang           v1.0.0               修改原因
	*/
	public int deleCcbj2(String id) {
		String sql = "delete from t_bae_cczjdw where gcid in ("+id+") ";
	    return this.jdbcTemplate.update(sql);
	}

	/**   
	* @Function: CcglDao.java
	* @Description: 拆除上报
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: Mr.Yang
	* @date: 2018年6月14日 上午9:20:01 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2018年6月14日     Mr.Yang           v1.0.0               修改原因
	*/
	public void ccgcSb(TMisUser user, TBaeCczj tBaeCczj, String flag) {
		String sql = "update t_bae_cczj set ccgczt="+flag+" where id in ("+tBaeCczj.getId()+")";
		 this.jdbcTemplate.update(sql);
	}
}
