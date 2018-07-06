package com.diiwon.mis.plat.base.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.diiwon.mis.plat.base.vojo.PageVo;
public class ParamBean extends PageVo{
	
	private String sql;
	
	private String pageSql;
	
	private String count;
	
	
	 /**查询条件语句    A='修改值',B=12  */
    private String setSql;

    /**查询条件语句   A=A AND B=B */
    private String whereSql;

    /**排序语句   A DESC ,B ASC */
    private String orderSql;
	
	private List<String> param = new ArrayList<String>();
	/**
	 * 常用参数如：1,2,3
	 */
	private String ids;
	/**
	 * 常用参数如：张三，李四
	 */
	private String names;
	private Map<String,Object> map = new HashMap<String,Object>();

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}
	
	public String getPageSql() {
		pageSql = "SELECT * FROM (SELECT ROW_.*, ROWNUM ROWNUM_ FROM ( "+sql+" ) ROW_ " +
				"WHERE ROWNUM <= "+ super.getRows() +" ) WHERE ROWNUM_ > "+super.getPage();
		return pageSql;
	}

	public String getCount() {
		count = "SELECT COUNT(1) FROM (" + this.sql + ")";
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public void setPageSql() {
		this.pageSql = sql;
	}

	public void setPageSql(String pageSql) {
		this.pageSql = pageSql;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public List<String> getParam() {
		return param;
	}

	public void setParam(List<String> param) {
		this.param = param;
	}

	public String getSetSql() {
		return setSql;
	}

	public void setSetSql(String setSql) {
		this.setSql = setSql;
	}

	public String getWhereSql() {
		return whereSql;
	}

	public void setWhereSql(String whereSql) {
		this.whereSql = whereSql;
	}

	public String getOrderSql() {
		return orderSql;
	}

	public void setOrderSql(String orderSql) {
		this.orderSql = orderSql;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}
	
}
