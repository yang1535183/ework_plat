package com.diiwon.mis.plat.base.cache.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.RowCallbackHandler;

import com.diiwon.mis.plat.base.cache.SystemCacheService;
import com.diiwon.mis.plat.base.dao.JdbcTemplate;
import com.diiwon.mis.plat.base.utils.StringUtilsExt;
import com.diiwon.mis.plat.xmgl.pojo.TMisPz;
import com.diiwon.mis.plat.xmgl.pojo.TmisZd;


/**
 * 缓存数据
 * @author Sunshine
 */
public class UserProperties implements SystemCacheService {
	protected final static Log log = LogFactory.getLog(UserProperties.class);
	
	private JdbcTemplate jdbcTemplate;
	/*** 系统共用字典项缓存 */
	private static HashMap<String, List<TmisZd>> ZD_CACHE = new HashMap<String, List<TmisZd>>();
	
	private static Map<String, TMisPz> UPLOADPZ_CACHE = new HashMap<String, TMisPz>();
	
	private static Map<String, List<TmisZd>> LISRZD_CACHE = new HashMap<String, List<TmisZd>>();
	
	private UserProperties() {
		
	}
	
	public List<TMisPz> loadTMisPz() {
		String sql = " select id, sclx, lxfgf, scdx, flmc, mcjp, cclx, ljlx, cclj from t_mis_pz ";
		final List<TMisPz> data = new ArrayList<TMisPz>();
		this.jdbcTemplate.query(sql, new RowCallbackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				TMisPz tMisPz = new TMisPz();
				tMisPz.setId(rs.getLong("id"));
				tMisPz.setSclx(rs.getString("sclx"));
				tMisPz.setLxfgf(rs.getString("lxfgf"));
				tMisPz.setScdx(rs.getString("scdx"));
				tMisPz.setFlmc(rs.getString("flmc"));
				tMisPz.setMcjp(rs.getString("mcjp"));
				tMisPz.setCclx(rs.getString("cclx"));
				tMisPz.setCclj(rs.getString("cclj"));
				
				data.add(tMisPz);
			}
		});
		
		return data;
	}
	
	private List<TmisZd> loadTMisZd() {
		String sql = " select id, dm_code, dm_name, parent_id, is_del, status, memo, flag, dep_code, dep_name from t_mis_zd where is_del = 1 order by id asc ";
		final List<TmisZd> data = new ArrayList<TmisZd>();
		this.jdbcTemplate.query(sql, new RowCallbackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				TmisZd ad = new TmisZd();
				ad.setId(rs.getLong("id"));
				ad.setDmCode(rs.getString("dm_code"));
				ad.setDmName(rs.getString("dm_name"));
				ad.setParentId(rs.getString("parent_id"));
				ad.setIsDel(rs.getLong("is_del"));
				ad.setStatus(rs.getString("status"));
				ad.setMemo(rs.getString("memo"));
				ad.setFlag(rs.getString("flag"));
				ad.setDepCode(rs.getString("dep_code"));
				ad.setDepName(rs.getString("dep_name"));
				data.add(ad);
			}
		});
		
		return data;
	}
	
	public synchronized void run() {
		update();
	}
	
	public synchronized void updateTMisPz() {
		List<TMisPz> tMisPz = loadTMisPz();
		if (null != tMisPz && !tMisPz.isEmpty()) {
			UPLOADPZ_CACHE.clear();
			String key;
			for(TMisPz upload : tMisPz) {
				if(null != upload.getMcjp()) {
					key = upload.getMcjp();
					UPLOADPZ_CACHE.put(key, upload);
				}
			}
		}
	}
	
	/***
	 *Map<String, List<TmisZd>>
	 *返回：dm_code 作为KEY，每行作为值返回
	 * **/
	public synchronized void updateTmisZd() {
		List<TmisZd> tmiszd = loadTMisZd();
		if (null != tmiszd && !tmiszd.isEmpty()) {
			ZD_CACHE.clear(); //清除缓存
			String key;
			
			for(TmisZd zd : tmiszd) {
				
				key = zd.getDmCode();
				if (key != null && !"".equals(key)) {
					key = key.toUpperCase().trim();
					List<TmisZd> list;
					if (ZD_CACHE.containsKey(key)) {
						list = ZD_CACHE.get(key);
					}
					else {
						list = new ArrayList<TmisZd>();
					}
					list.add(zd);
					ZD_CACHE.put(key, list);
				}
			}
		}
	}
	
	/***
	 *Map<String, List<TmisZd>>
	 *返回：父类作为KEY  子类作为值
	 * **/
	public synchronized void updateListTmisZd() {
		List<TmisZd> tmiszd = loadTMisZd();
		if (null != tmiszd && !tmiszd.isEmpty()) {
			LISRZD_CACHE.clear(); //清除缓存
			String key;
			
			for(TmisZd zd : tmiszd) {
				if(!StringUtilsExt.equals(zd.getParentId(), "0")) {
					key = zd.getParentId();
					List<TmisZd> list;
					if (LISRZD_CACHE.containsKey(key)) {
						list = LISRZD_CACHE.get(key);
					}
					else {
						list = new ArrayList<TmisZd>();
					}
					list.add(zd);
					LISRZD_CACHE.put(key, list);
				}
			}
		}
	}
	
	@Override
	public void update() {
		Long start = System.currentTimeMillis();
		log.info("字典缓存开始");
		this.updateTmisZd();//字典表
		this.updateListTmisZd();
		log.info("字典缓存结束-->耗时："+(System.currentTimeMillis() - start) / 1000.0);
		log.info("上传接口缓存开始");
		this.updateTMisPz();//上传配置表
		log.info("上传接口缓存结束-->耗时："+(System.currentTimeMillis() - start) / 1000.0);
	}
	
	
	public String getCacheName() {
		return "加载公共缓存";
	}
	
	public static Map<String, List<TmisZd>> getLISRZD_CACHE() {
		return LISRZD_CACHE;
	}

	public static void setLISRZD_CACHE(Map<String, List<TmisZd>> lISRZD_CACHE) {
		LISRZD_CACHE = lISRZD_CACHE;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public static HashMap<String, List<TmisZd>> getZD_CACHE() {
		return ZD_CACHE;
	}

	public static void setZD_CACHE(HashMap<String, List<TmisZd>> zD_CACHE) {
		ZD_CACHE = zD_CACHE;
	}
	
	public static Map<String, TMisPz> getUPLOADPZ_CACHE() {
		return UPLOADPZ_CACHE;
	}

	public static void setUPLOADPZ_CACHE(Map<String, TMisPz> uPLOADPZ_CACHE) {
		UPLOADPZ_CACHE = uPLOADPZ_CACHE;
	}

	
}
