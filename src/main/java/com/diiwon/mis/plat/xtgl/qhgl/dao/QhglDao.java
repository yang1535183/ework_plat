package com.diiwon.mis.plat.xtgl.qhgl.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.diiwon.mis.plat.base.dao.HibernateEntityDao;
import com.diiwon.mis.plat.base.dao.JdbcTemplate;
import com.diiwon.mis.plat.base.pojo.Admdivision;
import com.diiwon.mis.plat.base.pojo.TMisUser;
import com.diiwon.mis.plat.base.utils.Common;

@Repository
public class QhglDao extends HibernateEntityDao<Object> {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Admdivision> queryQhlbTree(TMisUser user){
		List<String> param = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		sb.append("select a.adname, a.adcode from admdivision a where length(adcode)<= 6 ");
		if(!Common.BlenAdmin(user.getLoginname())) {
			sb.append(" and adcode = ? ");
			param.add(user.getQhdm());
		}
		sb.append("order by a.adcode ");
		
		return this.jdbcTemplate.query(sb.toString(), param.toArray(), new RowMapper(){
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				Admdivision adm = new Admdivision();
				adm.setAdcode(rs.getString("adcode"));
				adm.setAdname(rs.getString("adname"));
				
				return adm;
			}
		});
	}

}
