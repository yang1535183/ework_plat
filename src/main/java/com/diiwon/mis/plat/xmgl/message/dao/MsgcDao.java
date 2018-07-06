package com.diiwon.mis.plat.xmgl.message.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.diiwon.mis.plat.base.dao.HibernateEntityDao;
import com.diiwon.mis.plat.base.dao.JdbcTemplate;
import com.diiwon.mis.plat.base.pojo.TMisUser;
import com.diiwon.mis.plat.base.utils.StringUtilsExt;
import com.diiwon.mis.plat.base.utils.SysCode;
import com.diiwon.mis.plat.xmgl.pojo.TbaeDbsx;

@Repository
public class MsgcDao extends HibernateEntityDao<Object> {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/***
	 * 
	 * 获取用户登陆信息
	 * **/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<TbaeDbsx> userQueryMsgc(TMisUser user) {
		StringBuilder sb = new StringBuilder();
		ArrayList<String> param = new ArrayList<>();
		sb.append(" select flag, gcid, id, memo, scsj, xmlx, qxjs from (select t1.*, case when t2.sjzt is not null then t2.sjzt ");
		sb.append(" else t3.sjzt end sjzt from t_bae_dbsx t1 left join t_bae_gczj t2 on t1.gcid=t2.id ");
		sb.append(" left join t_bae_cczj t3 on t1.gcid = t3.id) tt where 1=1 ");
		if(user.getQxsj() != null && user.getQxsj().size() != 0) {
			StringBuilder sb1 = new StringBuilder();
			sb.append(" and (");
			for(int i=0; i<user.getQxsj().size(); i++) {
				if(i != 0) {
					sb.append(" or");
				}
				sb.append(" qxjs like? ");
				if(StringUtilsExt.equals(user.getQxsj().get(i).toString(), SysCode.DBSX_QYYH))
					param.add("%"+user.getLoginname()+"%");
				else 
					param.add("%"+user.getQxsj().get(i).toString()+"%");
				
				sb1.append(" and (");
				if(i != 0) {sb1.append(" or");}
				if(StringUtilsExt.equals(user.getQxsj().get(i).toString(), SysCode.DBSX_BJSC)) {//审查人
					sb1.append(" sjzt=1");
				}
				else if(StringUtilsExt.equals(user.getQxsj().get(i).toString(), SysCode.DBSX_BJSH)) {//审批人
					sb1.append(" sjzt=2");
				}
				else {
					sb1.append(" 1=1 ");
				}
				sb1.append(" ) ");
			}
			sb.append(" ) ");
			sb.append(sb1.toString());
		}
		
		if(user.getXmlx() != null && user.getXmlx().size() != 0) {
			sb.append(" and (");
			for(int i=0; i<user.getXmlx().size(); i++) {
				if(i != 0) {
					sb.append(" or");
				}
				sb.append(" xmlx =? ");
				param.add(user.getXmlx().get(i).toString());
			}
			sb.append(" )  ");
		}
		
		sb.append(" order by scsj desc ");
		
		return this.jdbcTemplate.query(sb.toString(), param.toArray(), new BeanPropertyRowMapper(TbaeDbsx.class));
	}
}
