package com.diiwon.mis.plat.base.bean;

import java.util.List;

import org.apache.poi.hssf.record.formula.functions.T;

public class AjaxResult extends Result {
	
	public static AjaxResult ERROR(Object data, String message) {
		AjaxResult result = new AjaxResult();
		result.data = data;
		result.state = "error";
		result.message = message;
		result.success = false;
		result.footer = "";
		return result;
	}

	public static AjaxResult ERROR(Object data) {
		AjaxResult result = new AjaxResult();
		result.data = data;
		result.state = "error";
		result.success = false;
		result.message = (String) data;
		result.footer = "";
		return result;
	}

	public static AjaxResult WARN(Object data, String message) {
		AjaxResult result = new AjaxResult();
		result.data = data;
		result.state = "warn";
		result.success = false;
		result.message = message;
		result.footer = "";
		return result;
	}

	public static AjaxResult WARN(Object data) {
		AjaxResult result = new AjaxResult();
		result.data = data;
		result.state = "warn";
		result.success = false;
		result.message = (String) data;
		result.footer = "";
		return result;
	}

	public static AjaxResult SUCCESS(Object data, String message) {
		AjaxResult result = new AjaxResult();
		result.data = data;
		result.state = "success";
		result.success = true;
		result.message = message;
		return result;
	}

	public static AjaxResult SUCCESS(Object data) {
		return SUCCESS(data, (String) data);
	}

	public static AjaxResult SUCCESS(List<T> _list, int _total, Object _footer) {
		AjaxResult result = new AjaxResult();
		result.data = _list;
		result.state = "success";
		result.rows = _list;
		result.footer = _footer;
		result.total = _total;
		return result;
	}
	
	public static AjaxResult SUCCESS() {
		return SUCCESS(null, null);
	}
	
	public static AjaxResult ERROR() {
		return ERROR(null, null);
	}
	
	public static AjaxResult WARN() {
		return WARN(null, null);
	}
}
