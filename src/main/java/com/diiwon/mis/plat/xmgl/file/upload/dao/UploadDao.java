package com.diiwon.mis.plat.xmgl.file.upload.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.diiwon.mis.plat.base.dao.JdbcTemplate;

@Repository
public class UploadDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 载入：上传资源对象
	 * @param fileid 上传资源文件ID
	 * @return
	 */
	public Map<String, Object> getFileForMap(String fileid) {
		final Map<String, Object> resultMap = new Hashtable<String, Object>();
		
		Object[] args = {fileid};
		StringBuilder sb = new StringBuilder();
		sb.append("select id, filepath, filename, wjnr, cclx from t_ext_impdata where id=? ");
		this.jdbcTemplate.query(sb.toString(), args, new RowCallbackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				resultMap.put("filepath", (rs.getString("filepath")==null)?"":rs.getString("filepath"));
				resultMap.put("filename", rs.getString("filename").trim());
				resultMap.put("cclx", (rs.getString("cclx")==null)?"":rs.getString("cclx").trim());
				try {
					resultMap.put("wjnr", rs.getBytes("wjnr"));
				}
				catch (Exception e) {
					resultMap.put("wjnr", new byte[0]);
				}
			}
		});
		
		return resultMap;
	}
}
