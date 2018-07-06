package com.diiwon.mis.plat.base.service.imp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.diiwon.mis.plat.base.dao.BaseDao;
import com.diiwon.mis.plat.base.service.BaseService;

public abstract class BaseServiceImpl<T> implements BaseService<T> {	
	protected final Logger logger = LoggerFactory.getLogger(BaseServiceImpl.class);
	public abstract BaseDao<T> getDao();
	
}
