package com.diiwon.mis.plat.base.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import com.diiwon.mis.plat.base.orm.Page;


public class Common {
	
	/**
	 * 把字符串按指定分隔符分割并转换成List
	 * @param ids
	 * @param split
	 * @return
	 */
	public static List<Object> idsTosList(String ids, String split) {
		List<Object> list = new ArrayList<Object>();
		String[] _IDS = ids.split(split);
		for (String id : _IDS) {
			list.add(id);
		}
		
		return list;
	}
	
	/**
	 * 将分页列表数据转换成easyui风格datagrid的json数据
	 * @param dataList
	 * @return
	 */
	public static JSONObject getDatagridJsonDate(Page<?> dataList) {
		Map<String, Object> jsonMap = new Hashtable<String, Object>();
		jsonMap.put("total", dataList.getTotalCount());
		jsonMap.put("rows", dataList.getResult());
		JSONObject json = JSONObject.fromObject(jsonMap);
		return json;
	}
	
	public static JSONObject getDatagridJsonSumDate(Page<?> dataList, List<?> sumlist) {
		Map<String, Object> jsonMap = new Hashtable<String, Object>();
		jsonMap.put("total", dataList.getTotalCount());
		jsonMap.put("rows", dataList.getResult());
		jsonMap.put("footer", sumlist);
		JSONObject json = JSONObject.fromObject(jsonMap);
		return json;
	}
	
	/**
	 * 将分页列表数据转换成easyui风格datagrid的json数据
	 * @param dataList
	 * @param config
	 * @return
	 */
	public static JSONObject getDatagridJsonDate(Page<?> dataList, JsonConfig config) {
		Map<String, Object> jsonMap = new Hashtable<String, Object>();
		jsonMap.put("total", dataList.getTotalCount());
		jsonMap.put("rows", dataList.getResult());
		JSONObject json = JSONObject.fromObject(jsonMap, config);
		return json;
	}
	
	public static JSONObject getDatagridJsonList(List<?> dataList) {
		Map<String, Object> jsonMap = new Hashtable<String, Object>();
		jsonMap.put("total", dataList.size());
		jsonMap.put("rows", dataList);
		JSONObject json = JSONObject.fromObject(jsonMap);
		return json;
	}
	
	/**
	 * 将结果转换成json数据
	 * @param success
	 * @param data
	 * @return
	 */
	public static JSONObject getJsonResult(boolean success, Object data) {
		Map<String, Object> jsonMap = new Hashtable<String, Object>();
		jsonMap.put("success", success);
		jsonMap.put("data", data);
		JSONObject json = JSONObject.fromObject(jsonMap);
		return json;
	}
	/**
	 * 将结果List转换成json数据
	 * @param list
	 * @return
	 */
	public static JSONArray getJsonList(List<?> list) {
			return JSONArray.fromObject(list);
	}
	/**
	 * 将结果Map转换成json数据
	 * @param jsonMap
	 * @return
	 */
	public static JSONObject getJsonResult(Map<String, Object> jsonMap) {
		return JSONObject.fromObject(jsonMap);
	}
	
	/**
	 * 将结果Object转换成json数据
	 * @param jsonMap
	 * @return
	 */
	public static JSONObject getJson(Object ojbect, JsonConfig config) {
		if (null != config) {
			return JSONObject.fromObject(ojbect, config);
		}
		else {
			return JSONObject.fromObject(ojbect);
		}
	}
	
	/**
	 * 将结果Object转换成json数据
	 * @param jsonMap
	 * @return
	 */
	public static JSONObject getJson(Object ojbect) {
		return JSONObject.fromObject(ojbect);
	}
	
	/**
	 * 返回传入日期的年月日数组
	 * @param date
	 * @return
	 */
	public static String[] getNYR(Date date) {
		if (null != date) {
			String datestr = DateUtils.doFormatDate(date, "yyyy-MM-dd");
			return datestr.split("-");
		}
		else {
			return null;
		}
	}
	
	/**
	 * 返回传入日期的年月日数组
	 * @param datestr
	 * @return
	 */
	public static String[] getNYR(String datestr) {
		if (StringUtilsExt.isNotBlank(datestr)) {
			Date date = DateUtils.stringToDate(datestr);
			return getNYR(date);
		}
		else {
			return null;
		}
	}
	
	/**  
	* 使用java正则表达式去掉多余的.与0  
	* @param s  
	* @return   
	*/  
	public static String subZeroAndDot(String s){   
	    if(s.indexOf(".") > 0){   
	    	s = s.replaceAll("0+?$", "");//去掉多余的0   
	    	s = s.replaceAll("[.]$", "");//如最后一位是.则去掉   
	    }   
	    return s;   
	}
	
	public static void main(String[] args) {
		String[] NYR = Common.getNYR(new Date());
		if (null != NYR) {
			for (String tmp : NYR) {
				System.out.println(tmp);
			}
		}
	}
	
	public static String getUUID(){
		return "{"+UUID.randomUUID().toString().toUpperCase()+"}";
	}
	
	
	/**
	 * 执行系统中的命令
	 * @param CMD 命令行命令
	 * @param OUT 输出对象
	 * @return
	 * @throws IOException
	 */
	public String CallExec(String CMD, Writer OUT) throws IOException {
		StringBuffer sb = new StringBuffer();
		
		int len = 0;
		byte by[] = new byte[CMD.length() * 10];
		Process p = Runtime.getRuntime().exec(CMD);
		InputStream is = p.getInputStream();
		while ((len = is.read(by)) != -1) {
			String str = new String(by, 0, len);
			if (OUT != null) {
				OUT.write(str);
				OUT.flush();
			}
			sb.append(str);
		}
		is.close();
		
		return sb.toString();
	}
	
	public static String chkNull(String rs) {
		String str = new String();
		if (null == rs || StringUtilsExt.equals(rs, "null") || StringUtilsExt.isBlank(rs)) {
			str = "";
		}
		else {
			str = rs;
		}
		
		return str;
	}
	
	public static String getNjbbmc(String njbbxls) {
		return njbbxls.substring(njbbxls.indexOf("/", 2)+1, njbbxls.indexOf("."));
	}

	/**
	 * 判断是否是系统内部默认用户			SysCode.USER_ADMIN[] 
	 * @param loginUser
	 * @return
	 */
	public static boolean BlenAdmin(String loginUser) {
		for(int i=0; i<SysCode.USER_ADMIN.length; i++) {
			if(StringUtilsExt.equals(loginUser, SysCode.USER_ADMIN[i])) {
				return true;
			}
		}
		
		return false;
	}
}
