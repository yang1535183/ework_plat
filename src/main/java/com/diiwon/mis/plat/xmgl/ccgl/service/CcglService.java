/**   
 * 功能描述：
 * @Package: com.diiwon.mis.plat.xmgl.ccgl.service 
 * @author: Mr.Yang   
 * @date: 2018年6月13日 上午10:34:39 
 */
package com.diiwon.mis.plat.xmgl.ccgl.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.diiwon.mis.plat.base.orm.Page;
import com.diiwon.mis.plat.base.pojo.TMisJs;
import com.diiwon.mis.plat.base.pojo.TMisUser;
import com.diiwon.mis.plat.base.utils.Common;
import com.diiwon.mis.plat.base.utils.DateUtils;
import com.diiwon.mis.plat.base.utils.StringUtilsExt;
import com.diiwon.mis.plat.base.utils.SysCode;
import com.diiwon.mis.plat.base.vojo.PageVo;
import com.diiwon.mis.plat.xmgl.ccgl.dao.CcglDao;
import com.diiwon.mis.plat.xmgl.gcgl.dao.GcbjDao;
import com.diiwon.mis.plat.xmgl.pojo.TBaeCczj;
import com.diiwon.mis.plat.xmgl.pojo.TBaeCczjdw;
import com.diiwon.mis.plat.xmgl.pojo.TbaeDbsx;
import com.diiwon.mis.plat.xmgl.pojo.TextImpdata;

import net.sf.json.JSONObject;

/**
 * @ClassName: CcglService.java
 * @Description: 该类的功能描述
 *
 * @version: v1.0.0
 * @author: Mr.Yang
 * @date: 2018年6月13日 上午10:34:39
 *
 *        Modification History: Date Author Version Description
 *        ---------------------------------------------------------* 2018年6月13日
 *        Mr.Yang v1.0.0 修改原因
 */
@Service
public class CcglService {
	/**
	 * @Function: GcbjService.java
	 * @Description: 该函数的功能描述
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: Mr.Yang
	 * @date: 2018年6月12日 下午3:29:07
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2018年6月12日
	 *        Mr.Yang v1.0.0 修改原因
	 */
	@Autowired
	private CcglDao ccglDao;
	
	@Autowired
	private GcbjDao gcbjDao;

	@Autowired
	private TransactionTemplate transactionTemplate;

	public TBaeCczj findTBaeCczj(String id) {

		return this.ccglDao.findUnique(TBaeCczj.class, "id", id);
	}

	public JSONObject CcJsonPageList(PageVo page, TBaeCczj tBaeCczj, TMisUser tMisUser,
			String dmco) {
		Page<Map<String, String>> dataList = new Page<Map<String, String>>();
		dataList.setCurrentPageNo(page.getPage());
		dataList.setPageSize(page.getRows());

		if (tMisUser.getRoles().size() != 0 && null != tMisUser) {
			dataList = this.ccglDao.CcJsonPageList(dataList, tBaeCczj, tMisUser, dmco);
		}
		return Common.getDatagridJsonDate(dataList);
	}

	/**
	 * @Function: GcbjService.java
	 * @Description: 保存拆除表数据
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: Mr.Yang
	 * @date: 2018年6月12日 下午8:04:22
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2018年6月12日
	 *        Mr.Yang v1.0.0 修改原因
	 */
	public Map<String, Object> saveCc(TMisUser tMisUser, TBaeCczj tBaeCczj, TBaeCczjdw tBaeCczjdw) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", false);
		result.put("data", "");
		try {
			if(!StringUtilsExt.equals(tBaeCczj.getSjzt(), SysCode.SJZT_DSP)
					|| !StringUtilsExt.equals(tBaeCczj.getSjzt(), SysCode.SJZT_SCZCM)) {
				/***数据状态为 0， 3， 5 【即数据状态不为 2， 4】时才能修改***/
				if(StringUtilsExt.isBlank(tBaeCczj.getId())) {
					tBaeCczj.setId(DateUtils.getDataName("", true));
				}
				
				tBaeCczjdw.setGcid(tBaeCczj.getId());
				tBaeCczj.setCjr(tMisUser.getLoginname());
				tBaeCczj.setCjsj(DateUtils.formatDate(SysCode.RQGSH_HXGS_CRQ));
				tBaeCczj.setSjzt(SysCode.SJZT_DEFAULT);

				tBaeCczj.settBaeCczjdw(tBaeCczjdw);
				this.ccglDao.saveOrUpdate(tBaeCczj);
				
				result.put("gcid", tBaeCczj.getId());
				result.put("success", true);
				result.put("data", "数据保存成功!");
				
			}
			else {
				result.put("data", "系统拒绝请求:数据状态错误!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("data", "数据录入失败！");
		}
		return result;
	}

	/**
	 * @Function: CcglService.java
	 * @Description: 该函数的功能描述
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: Mr.Yang
	 * @date: 2018年6月13日 下午5:20:31
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2018年6月13日
	 *        Mr.Yang v1.0.0 修改原因
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, Object> deleCcbj(TMisUser tMisUser, String id) {
		Map<String, Object> result = (Map<String, Object>) transactionTemplate.execute(new TransactionCallback() {
			public Map<String, Object> doInTransaction(TransactionStatus status) {
				Map<String, Object> res = new HashMap<String, Object>();
				res.put("success", false);
				res.put("data", "");
				try {
					// 删除主表
					int zhu = ccglDao.deleCcbj(id, tMisUser);/**删除主表**/
					if (zhu != 0) {
						ccglDao.deleCcbj2(id);/***删除从表数据*/
						gcbjDao.delefjxx(id);  /***删除附件*/
					}
					res.put("success", true);
					res.put("data", "成功删除" + zhu + "条数据！！！");
				} catch (Exception e) {
					res.put("data", "删除失败！！！");
					e.printStackTrace();
				}
				return res;
			}
		});
		return result;
	}

	/**
	 * @Function: CcglService.java
	 * @Description: 该函数的功能描述
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: Mr.Yang
	 * @date: 2018年6月13日 下午6:06:40
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2018年6月13日
	 *        Mr.Yang v1.0.0 修改原因
	 */
	public Map<String, Object> ccgcSb(TBaeCczj tBaeCczj, TMisUser user) {
		Map<String, Object> res = new HashMap<String, Object>();
		res.put("success", false);
		res.put("data", "非法请求！！！");
		try {
			if(StringUtilsExt.isNotBlank(tBaeCczj.getId())) {
				String[] _ids = tBaeCczj.getId().split(",");
				if(_ids.length != 0) {
					
					for (String id : _ids) {
						List<TextImpdata> list=new ArrayList<TextImpdata>();
						list=this.gcbjDao.fjList(id);
						if (list.size()==0) {
							res.put("data", "请确保您已上传附件！！！");
							return res;
						}
					}
					
					List<TMisJs> tMisJs = user.getRoles();
					if(null == tMisJs  || 0 == tMisJs.size()) {
						res.put("data", "当前用户请求失败，该用户角色无法发送该请求！！！");
					}
					else {
						String[] sql = new String[_ids.length+1];
						//更改主表状态
						sql[0] = "update t_bae_cczj set sjzt = '"+SysCode.SJZT_DSC+"' where id in("+tBaeCczj.getId()+")";
						//添加流程记录
						List<TbaeDbsx> dbsxList = new ArrayList<TbaeDbsx>();
						for(int i=0; i<_ids.length; i++) {
							StringBuilder sb = new StringBuilder();
							sb.append(" insert into t_tlp_lcjl(gcid, action, memo, user, time) VALUES('");
							sb.append(_ids[i]).append("','").append(SysCode.SJZT_DSC).append("','");
							sb.append(SysCode.DFNAME_QYLR).append("','").append(user.getLoginname()).append("','");
							sb.append(DateUtils.formatDate(SysCode.RQGSH_HXGS_CRQ)).append("')");
							sql[i+1] = sb.toString();
							
							TBaeCczj chcTbae = this.gcbjDao.findUnique(TBaeCczj.class, "id", _ids[i]);
							TbaeDbsx tbaeDbsx = new TbaeDbsx();
							tbaeDbsx.setGcid(_ids[i]);
							tbaeDbsx.setScry(user.getLoginname());
							tbaeDbsx.setScsj(DateUtils.formatDate(SysCode.RQGSH_HXGS_CRQ));
							tbaeDbsx.setXmlx(chcTbae.getXmlx());
							tbaeDbsx.setFlag(SysCode.SJZT_DEFAULT);
							tbaeDbsx.setQxjs(SysCode.DBSX_BJSC);
							tbaeDbsx.setMemo("企业用户："+user.getUsername()+"-上报："+chcTbae.getGcmc()+"数据。");
							dbsxList.add(tbaeDbsx);
						}
						
						if(this.dxscGcbj(tBaeCczj)) {
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
		}  
		catch (Exception e) {
			res.put("data", "上报失败！");
			e.printStackTrace();
		}
		return res;
	}
	
	/****
	 * 保存逐项审查数据
	 */
	public boolean dxscGcbj (TBaeCczj tBaeCczj) {
		String[] ids = tBaeCczj.getId().split(",");
		Boolean rel = false;
		if(ids.length !=0) {
			for(int i=0; i<ids.length; i++) {
				//审查需要审查的数据
				tBaeCczj.setId(ids[i]);
				List<Map<String, Object>> gczj = (List<Map<String, Object>>)this.ccglDao.getTbaeCczj(tBaeCczj);
				//审查配置表
				List<Map<String, Object>> list = (List<Map<String, Object>>)this.gcbjDao
						.scpzJsonList(String.valueOf(String.valueOf(gczj.get(0).get("xmlx"))));
				
				String[] sql = new String[list.size()];
				for(int j=0; j<list.size(); j++) {
					StringBuilder sb = new StringBuilder();
					sb.append(" insert into t_bae_dxsc(gcid, sclm, is_old, sccz, scsj)");
					sb.append(" VALUES(");
					sb.append("'").append(tBaeCczj.getId()).append("',");//gcid
					sb.append("'").append(list.get(j).get("sclm").toString()).append("',");//sclm
					sb.append("'1',");//is_old
					sb.append("'1',");//sccz
					sb.append("'").append(String.valueOf(gczj.get(0).get(list.get(j).get("sclm")))).append("')");//scsj
					sql[j]=sb.toString();
				}
				
				if(sql.length != 0) {
					try {
						this.gcbjDao.updateDxsc(tBaeCczj.getId());//更改原有数据
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
}