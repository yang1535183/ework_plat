package com.diiwon.mis.plat.xmgl.message.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diiwon.mis.plat.base.pojo.TMisUser;
import com.diiwon.mis.plat.base.utils.Common;
import com.diiwon.mis.plat.xmgl.message.dao.MsgcDao;
import com.diiwon.mis.plat.xmgl.pojo.TbaeDbsx;

@Service
public class MsgcService {

	@Autowired
	private MsgcDao msgcDao;

	public List<TbaeDbsx> userQueryMsgc(TMisUser user) {
		if (null != user && !Common.BlenAdmin(user.getLoginname()) && user.getXmlx().size() != 0)
			return this.msgcDao.userQueryMsgc(user);

		return null;
	}

}
