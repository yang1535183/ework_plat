package com.diiwon.mis.plat.base.vojo;

public class PageVo {

	private int page;
	
	private int rows;
	
	private int startIndex;
	
	private String sort; 
	
	private String order;
	
	public int getPage() {
		if (0 != page) {
			return page;
		}
		else {
			return 1;
		}
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		if (0 != rows) {
			return rows;
		}
		else {
			return 20;
		}
	}
	
	public void setRows(int rows) {
		this.rows = rows;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public int getStartIndex() {
		return (getPage()-1)*getRows();
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

}
