package com.diiwon.mis.plat.base.bean;

import java.util.List;

import org.apache.poi.hssf.record.formula.functions.T;

public class Result {

	public static final String SUCCESS = "success";
	public static final String ERROR = "error";
	public static final String WARN = "warn";
	public static final String FAILURE = "failure";
	public boolean success = true;
	public String state = null;
	public String message = null;
	public Object data = null;
	public List<T> rows;
	public int total;
	public Object footer;


	public static Result SUCCESS() {
		Result result = new Result();
		result.state = "success";
		result.success = true;
		return result;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public static String getSuccess() {
		return "success";
	}

	public static String getError() {
		return "error";
	}

	public static String getWarn() {
		return "warn";
	}

	public static String getFailure() {
		return "failure";
	}

}
