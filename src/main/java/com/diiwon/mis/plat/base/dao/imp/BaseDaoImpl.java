package com.diiwon.mis.plat.base.dao.imp;

import org.springframework.transaction.annotation.Transactional;

import com.diiwon.mis.plat.base.dao.BaseDao;
import com.diiwon.mis.plat.base.dao.HibernateEntityDao;

public class BaseDaoImpl<T> extends HibernateEntityDao<Object> implements BaseDao<T> {
	
	
	/**
	 * 保存对象
	 * @param entity
	 */
	@Transactional
	public void saveOrUpdate(Object entity) {
		try {
			this.getHibernateTemplate().saveOrUpdate(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
