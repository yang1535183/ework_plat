package com.diiwon.mis.plat.xmgl.lcjk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diiwon.mis.plat.base.pojo.TMisLcjl;
import com.diiwon.mis.plat.xmgl.lcjk.dao.LcjkDao;

/*****
 * 
 * 流程监控业务类
 * @author plp
 *
 */
@Service
public class LcjkService {
	
	@Autowired
	private LcjkDao lcjkDao;
	
	/****
	 * @param gcid：工程ID
	 * @param flag：0：报监流程，1：合同流程
	 * @return
	 */
	public List<TMisLcjl> getAllLcjlByGcid(String gcid, String flag){
		return lcjkDao.getAllLcjlByGcid(gcid, flag);
	}
}
