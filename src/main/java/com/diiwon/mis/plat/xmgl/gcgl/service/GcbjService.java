package com.diiwon.mis.plat.xmgl.gcgl.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.diiwon.mis.plat.base.cache.imp.UserProperties;
import com.diiwon.mis.plat.base.orm.Page;
import com.diiwon.mis.plat.base.pojo.TMisJs;
import com.diiwon.mis.plat.base.pojo.TMisUser;
import com.diiwon.mis.plat.base.utils.Common;
import com.diiwon.mis.plat.base.utils.DateUtils;
import com.diiwon.mis.plat.base.utils.StringUtilsExt;
import com.diiwon.mis.plat.base.utils.SysCode;
import com.diiwon.mis.plat.base.vojo.PageVo;
import com.diiwon.mis.plat.xmgl.gcgl.dao.GcbjDao;
import com.diiwon.mis.plat.xmgl.pojo.TBaeCczj;
import com.diiwon.mis.plat.xmgl.pojo.TBaeGczj;
import com.diiwon.mis.plat.xmgl.pojo.TBaeGczjdw;
import com.diiwon.mis.plat.xmgl.pojo.TBaeGczjsx;
import com.diiwon.mis.plat.xmgl.pojo.TbaeDbsx;
import com.diiwon.mis.plat.xmgl.pojo.TbaeDxsc;
import com.diiwon.mis.plat.xmgl.pojo.TextImpdata;
import com.diiwon.mis.plat.xmgl.pojo.TmisZd;

import net.sf.json.JSONObject;

/**
 * 工程管理：控制跳转类
 * 
 * @author (刘佳玲)
 */
@Service
public class GcbjService {

	@Autowired
	private GcbjDao gcbjDao;

	@Autowired
	private TransactionTemplate transactionTemplate;

	public JSONObject jsonPageList(PageVo page, TBaeGczj tBaeGczj, 
			TMisUser tMisUser, String dmco) {
		Page<Map<String, String>> dataList = new Page<Map<String, String>>();
		dataList.setCurrentPageNo(page.getPage());
		dataList.setPageSize(page.getRows());
		if (tMisUser.getRoles().size() != 0 && null != tMisUser) {
			dataList = this.gcbjDao.jsonPageList(dataList, tBaeGczj, tMisUser, dmco);
		}

		return Common.getDatagridJsonDate(dataList);
	}

	public List<?> jsonPagelcjlList(TBaeGczj tBaeGczj) {

		return this.gcbjDao.jsonPagelcjlList(tBaeGczj, SysCode.SJZT_DEFAULT);
	}

	// 执行删除事务(刘)
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, Object> deleGcbj(TMisUser tMisUser, final String id) {
		Map<String, Object> result = (Map<String, Object>) transactionTemplate.execute(new TransactionCallback() {
			public Map<String, Object> doInTransaction(TransactionStatus status) {
				Map<String, Object> res = new HashMap<String, Object>();
				res.put("success", false);
				res.put("data", "");
				try {
					// 2、删除主表
					int ct = gcbjDao.delGong2(id, tMisUser);
					if (ct == 0) {
						res.put("data", "当前用户没有删除权限！！！");
					} else {
						// 1、删除从表
						gcbjDao.delGong(id);
						gcbjDao.delGong1(id);
						res.put("success", true);
						res.put("data", "成功删除" + ct + "条数据！！！");
					}
				} catch (Exception e) {
					res.put("data", "删除失败！！！");
					e.printStackTrace();
				}
				return res;
			}
		});
		return result;
	}

	public Map<String, Object> saveXmbj(TMisUser tMisUser, TBaeGczj tBaeGczj, TBaeGczjdw tBaeGczjdw,
			TBaeGczjsx tBaeGczjsx) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", false);
		result.put("data", "");

		if (0 == tMisUser.getXmlx().size() || tMisUser.getRoles().size() == 0) {
			result.put("data", "系统拒绝访问：没有录入权限！");
		} else {
			try {
				if (!StringUtilsExt.equals(tBaeGczj.getSjzt(), SysCode.SJZT_DSP)
						|| !StringUtilsExt.equals(tBaeGczj.getSjzt(), SysCode.SJZT_SCZCM)) {
					/***数据状态为 0， 3， 5 【即数据状态不为 2， 4】时才能修改***/
					if (StringUtilsExt.isBlank(tBaeGczj.getId())) {
						tBaeGczj.setId(DateUtils.getDataName("", true));
					}
					tBaeGczjdw.setGcid(tBaeGczj.getId());
					tBaeGczjsx.setGcid(tBaeGczj.getId());
					tBaeGczj.settBaeGczjdw(tBaeGczjdw);
					tBaeGczj.settBaeGczjsx(tBaeGczjsx);

					tBaeGczj.setCjr(tMisUser.getLoginname());
					tBaeGczj.setCjsj(DateUtils.formatDate(SysCode.RQGSH_HXGS_CRQ));
					tBaeGczj.setSjzt(SysCode.SJZT_DEFAULT);
					this.gcbjDao.saveOrUpdate(tBaeGczj);

					result.put("gcid", tBaeGczj.getId());
					result.put("success", true);
					result.put("data", "数据保存成功!");
				} else {
					result.put("data", "系统拒绝请求:数据状态错误!");
				}

			} catch (Exception e) {
				e.printStackTrace();
				result.put("data", "数据录入失败！");
			}
		}

		return result;
	}

	public List<?> dygridJsondata() {
		return this.gcbjDao.dygridJsondata();
	}

	/****
	 * 企业提报，支持批量提报
	 * 
	 * @param flag
	 * @param tMisUser
	 * @param response
	 * @param ids
	 */
	public Map<String, Object> reportGcbj(TBaeGczj tBaeGczj, TMisUser user, String flag) {
		Map<String, Object> res = new HashMap<String, Object>();
		res.put("success", false);
		res.put("data", "非法请求！！！");
		try {
			if (StringUtilsExt.isNotBlank(tBaeGczj.getId())) {
				String[] _ids = tBaeGczj.getId().split(",");
				if (_ids.length != 0) {
					
					
					for (String id : _ids) {
						List<TextImpdata> list=new ArrayList<TextImpdata>();
						list=this.gcbjDao.fjList(id);
						if (list.size()==0) {
							res.put("data", "请确保您已上传附件！！！");
							return res;
						}
					}
					
					List<TMisJs> tMisJs = user.getRoles();
					if (null == tMisJs || 0 == tMisJs.size()) {
						res.put("data", "当前用户请求失败，该用户角色无法发送该请求！！！");
					} else {
						String[] sql = new String[_ids.length+1];
						List<TbaeDbsx> dbsxList = new ArrayList<TbaeDbsx>();
						//更改主表状态
						sql[0] = "update t_bae_gczj set sjzt = '"+SysCode.SJZT_DSC+"' where id in("+tBaeGczj.getId()+")";
						//添加流程记录
						for(int i=0; i<_ids.length; i++) {
							StringBuilder sb = new StringBuilder();
							sb.append(" insert into t_tlp_lcjl(gcid, action, memo, user, time) VALUES('");
							sb.append(_ids[i]).append("','").append(SysCode.SJZT_DSC).append("','");
							sb.append(SysCode.DFNAME_QYLR).append("','").append(user.getLoginname()).append("','");
							sb.append(DateUtils.formatDate(SysCode.RQGSH_HXGS_CRQ)).append("')");
							sql[i+1] = sb.toString();
							
							TBaeGczj gczj = this.gcbjDao.findUnique(TBaeGczj.class, "id", _ids[i]);
							TbaeDbsx tbaeDbsx = new TbaeDbsx();
							tbaeDbsx.setGcid(_ids[i]);
							tbaeDbsx.setScry(user.getLoginname());
							tbaeDbsx.setScsj(DateUtils.formatDate(SysCode.RQGSH_HXGS_CRQ));
							tbaeDbsx.setXmlx(gczj.getXmlx());
							tbaeDbsx.setFlag(SysCode.SJZT_DEFAULT);
							tbaeDbsx.setQxjs(SysCode.DBSX_BJSC);
							tbaeDbsx.setMemo("企业用户："+user.getUsername()+"-上报："+gczj.getGcmc()+"数据。");
							dbsxList.add(tbaeDbsx);
						}
						
						if(this.dxscGcbj(tBaeGczj)) {
							gcbjDao.batchUpdateSql(sql);
							for(TbaeDbsx tbae : dbsxList) {
								gcbjDao.save(tbae);
							}
							res.put("success", true);
							res.put("data", "完成："+SysCode.DFNAME_QYLR);
						}else {
							res.put("data", "无法与审核表对应，请联系管理员");
						}
					}
				}
			}
		} catch (Exception e) {
			res.put("data", "上报失败！");
			e.printStackTrace();
		}
		return res;
	}

	/****
	 * 保存逐项审查数据{上报过程中生成数据}
	 */
	public boolean dxscGcbj(TBaeGczj tBaeGczj) {
		String[] ids = tBaeGczj.getId().split(",");
		Boolean rel = false;
		if (ids.length != 0) {
			for (int i = 0; i < ids.length; i++) {
				// 审查需要审查的数据
				tBaeGczj.setId(ids[i]);
				List<Map<String, Object>> gczj = (List<Map<String, Object>>) this.gcbjDao.lisTbaeGczj(tBaeGczj);
				// 审查配置表
				List<Map<String, Object>> list = (List<Map<String, Object>>) this.gcbjDao
						.scpzJsonList(String.valueOf(String.valueOf(gczj.get(0).get("xmlx"))));

				String[] sql = new String[list.size()];
				for (int j = 0; j < list.size(); j++) {
					StringBuilder sb = new StringBuilder();
					sb.append(" insert into t_bae_dxsc(gcid, sclm, is_old, sccz, scsj)");
					sb.append(" VALUES(");
					sb.append("'").append(tBaeGczj.getId()).append("',");// gcid
					sb.append("'").append(list.get(j).get("sclm").toString()).append("',");// sclm
					sb.append("'1',");// is_old
					sb.append("'1',");// sccz
					sb.append("'").append(String.valueOf(gczj.get(0).get(list.get(j).get("sclm")))).append("')");// scsj
					sql[j] = sb.toString();
				}

				if (sql.length != 0) {
					try {
						this.gcbjDao.updateDxsc(tBaeGczj.getId());// 更改原有数据
						this.gcbjDao.batchUpdateSql(sql);
						rel = true;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return rel;
	}

	public TBaeGczj findTBaeGczj(String id) {

		return this.gcbjDao.findUnique(TBaeGczj.class, "id", id);
	}

	public List<?> zxshJsonList(TBaeGczj tBaeGczj) {

		return this.gcbjDao.dxscJsonList(tBaeGczj);
	}

	/** 根据单选按钮选中状态变化数据库状态值 **/
	public Map<String, Object> zxscSave(String id, String vl, String tname) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("success", false);
		try {
			List<TmisZd> tmisZd = UserProperties.getZD_CACHE().get(tname);
			this.gcbjDao.zxscSave(id, vl, tmisZd.get(0).getStatus(), tmisZd.get(0).getDepCode());
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return map;
	}

	public List<?> jsonUploadfile(String gcid) {

		return this.gcbjDao.jsonUploadfile(gcid);
	}

	/**逐项审查***/
	public Map<String, Object> zxscSaveSctj(String id, TMisUser user, String dmco) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("success", false);
		/*** 查看逐项审查是否存在不通过数据 */
		long cot = this.gcbjDao.queryDxsc(id, SysCode.SJZT_DEFAULT);// 逐项审查表
		long cvt = this.gcbjDao.queryImpdata(id, SysCode.SJZT_DEFAULT);//附件上传表
		String _xmlx;
		String _gcmc;
		String _cjr;
		if(StringUtilsExt.equals(dmco, SysCode.SYS_GCCHC)) {
			TBaeCczj gczj = this.gcbjDao.findUnique(TBaeCczj.class, "id", id);
			_xmlx = gczj.getXmlx();
			_gcmc = gczj.getGcmc();
			_cjr = gczj.getCjr();
			
		}else {
			TBaeGczj gczj = this.gcbjDao.findUnique(TBaeGczj.class, "id", id);
			_xmlx = gczj.getXmlx();
			_gcmc = gczj.getGcmc();
			_cjr = gczj.getCjr();
		}
		
		TbaeDbsx tbaeDbsx = new TbaeDbsx();
		tbaeDbsx.setGcid(id);
		tbaeDbsx.setScry(user.getLoginname());
		tbaeDbsx.setScsj(DateUtils.formatDate(SysCode.RQGSH_HXGS_CRQ));
		tbaeDbsx.setXmlx(_xmlx);
		tbaeDbsx.setFlag(SysCode.SJZT_DEFAULT);
		
		if (cot == 0 && cvt ==0) { /*** 如果不存在。则通过审批通过 */
			/*** 修改主表状态 为通过：1 */
			long ct = this.gcbjDao.updateGczj(id, SysCode.SJZT_DSP, dmco, SysCode.SJZT_DSC, user.getUsername());
			if (ct != 0) {
				/** 添加流程记录 **/
				this.gcbjDao.insertLcjl(id, SysCode.SJZT_DSP, SysCode.DFNAME_SCYTG, user, SysCode.SJZT_DEFAULT);
			} else {
				map.put("data", "审查失败，无法修改主表数据！！！");
			}
			
			tbaeDbsx.setQxjs(SysCode.DBSX_BJSH);
			tbaeDbsx.setMemo("审查员："+user.getUsername()+"-审查通过："+_gcmc+"项目数据。");
			gcbjDao.save(tbaeDbsx);
			
			map.put("success", true);
			map.put("data", "审查通过！！！");
		} else {/*** 退回 **/
			/*** 修改主表状态 为不通过：0 */
			this.gcbjDao.updateGczj(id, SysCode.SJZT_SCTH, dmco, SysCode.SJZT_DEFAULT, user.getUsername());
			/** 添加流程记录 **/
			this.gcbjDao.insertLcjl(id, SysCode.SJZT_SCTH, SysCode.DFNAME_SCYBTG, user, SysCode.SJZT_DEFAULT);
			
			tbaeDbsx.setQxjs(_cjr);
			tbaeDbsx.setMemo("审查员："+user.getUsername()+"-审查不通过："+_gcmc+"项目数据。");
			gcbjDao.save(tbaeDbsx);
			
			map.put("success", true);
			map.put("data", "审查不通过！！！");
		}

		return map;
	}

	public Map<String, Object> saveOnAfterEdit(String column, String val, String id, String tname) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("success", false);
		try {
			List<TmisZd> tmisZd = UserProperties.getZD_CACHE().get(tname);
			StringBuilder sb = new StringBuilder();
			sb.append(" update ").append(tmisZd.get(0).getDepCode()).append(" set ");
			sb.append(column).append(" = '").append(val);
			sb.append("' where id = '").append(id).append("'");
			this.gcbjDao.saveOnAfterEdit(sb.toString());
			map.put("success", true);
			map.put("data", "数据保存成功！！！");
		} catch (Exception e) {
			map.put("data", "数据保存失败！！！");
		}

		return map;
	}
	
	/**审批**/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, Object> reportGcxm(String id, String dmco, TMisUser user, String flag, String memo) {
		Map<String, Object> result = (Map<String, Object>) transactionTemplate.execute(new TransactionCallback() {
			public Map<String, Object> doInTransaction(TransactionStatus status) {
				HashMap<String, Object> map = new HashMap<>();
				map.put("success", false);
				map.put("data", "");
				try {
					String[] ids = id.split(",");
					if (ids.length != 0) {
						String[]  str = new String[ids.length];
						String[] _str = new String[ids.length];
						
						String year = DateUtils.thisFormatDate("yyyy");//年度
						String maxnumber = gcbjDao.queryMaxnum(dmco, year);//数据库最大值
						int maxZcm = Integer.valueOf(year +"001");//最大的数值串
						if(!StringUtilsExt.isBlank(maxnumber)) {
							maxZcm = Integer.valueOf(StringUtilsExt.getNumber(maxnumber))+1;
						}
						
						ArrayList<TbaeDbsx> dbsxList = new ArrayList<>();
						for(int i=0; i<ids.length; i++) {
							StringBuilder sql = new StringBuilder();
							/**获取注册码**/
							String zcm = (StringUtilsExt.isBlank(UserProperties.getZD_CACHE().get(dmco).get(0).getStatus())?"":
										UserProperties.getZD_CACHE().get(dmco).get(0).getStatus()) + 
									String.valueOf(maxZcm);
							//更改主表数据状态
							sql.append("update ").append(UserProperties.getZD_CACHE().get(dmco).get(0).getDepCode());
							sql.append(" set sjzt = '").append(UserProperties.getZD_CACHE().get(flag).get(0).getDepCode()).append("'");
							if(StringUtilsExt.equals(flag, SysCode.SYS_SCZCM)) {//审批通过
								sql.append(" , zcbm='").append(zcm).append("' ");
							}
							sql.append(" where id = '").append(ids[i]).append("'");
							str[i]=sql.toString();
							
							maxZcm++;
							//添加流程记录
							StringBuilder sb = new StringBuilder();
							sb.append(" insert into t_tlp_lcjl(gcid, action, memo, user, time) VALUES('");
							sb.append(ids[i]).append("','").append(UserProperties.getZD_CACHE().get(flag).get(0).getDepCode()).append("','");
							if(StringUtilsExt.isNotBlank(memo)) {
								sb.append(memo.trim()).append("','");
							}
							else {
								sb.append(UserProperties.getZD_CACHE().get(flag).get(0).getDepName()).append("','");
							}
							sb.append(user.getLoginname()).append("','");
							sb.append(DateUtils.formatDate(SysCode.RQGSH_HXGS_CRQ)).append("')");
							
							_str[i]=sb.toString();
							
							String _xmlx;
							String _gcmc;
							String _cjr;
							if(StringUtilsExt.equals(dmco, SysCode.SYS_GCCHC)) {
								TBaeCczj gczj = gcbjDao.findUnique(TBaeCczj.class, "id", id);
								_xmlx = gczj.getXmlx();
								_gcmc = gczj.getGcmc();
								_cjr = gczj.getCjr();
								
							}else {
								TBaeGczj gczj = gcbjDao.findUnique(TBaeGczj.class, "id", id);
								_xmlx = gczj.getXmlx();
								_gcmc = gczj.getGcmc();
								_cjr = gczj.getCjr();
							}
							
							TbaeDbsx tbaeDbsx = new TbaeDbsx();
							tbaeDbsx.setGcid(ids[i]);
							tbaeDbsx.setScry(user.getLoginname());
							tbaeDbsx.setScsj(DateUtils.formatDate(SysCode.RQGSH_HXGS_CRQ));
							tbaeDbsx.setXmlx(_xmlx);
							tbaeDbsx.setFlag(SysCode.SJZT_DEFAULT);
							tbaeDbsx.setQxjs(SysCode.DBSX_QYYH);
							tbaeDbsx.setMemo("审批人："+user.getUsername()+"-通过："+_gcmc+"数据。");
							if(StringUtilsExt.equals(flag, SysCode.SYS_SPLDBTG)) {
								tbaeDbsx.setQxjs(_cjr);
								tbaeDbsx.setMemo("审批人："+user.getUsername()+"-没有通过："+_gcmc+"数据。");
							}
							dbsxList.add(tbaeDbsx);
						}
						
						for(TbaeDbsx tbae : dbsxList) {
							gcbjDao.save(tbae);
						}
						gcbjDao.batchUpdateSql(str);
						gcbjDao.batchUpdateSql(_str);
						map.put("success", true);
						map.put("data", UserProperties.getZD_CACHE().get(flag).get(0).getDepName());
					}
					else {
						map.put("data", "系统拒绝访问：选择数据类型错误！！！");
					}
				} catch (Exception e) {
					map.put("data", "数据保存失败！！！");
					e.printStackTrace();
				}

				return map;
			}
		});
		return result;
	}

	public List<TextImpdata> fjList(String fjList) {
		
		return this.gcbjDao.fjList(fjList);
	}

	public List<TbaeDxsc> getCheckDxscsj(String id) {
		
		return this.gcbjDao.getCheckDxscsj(id);
	}
}
