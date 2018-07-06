package com.diiwon.mis.plat.base.dao;

public interface BaseDao<T> {

	void saveOrUpdate(T entity) throws Exception;

	
}
