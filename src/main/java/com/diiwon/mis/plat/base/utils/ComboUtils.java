package com.diiwon.mis.plat.base.utils;

import java.util.List;
import java.util.Map;

/**
 * 构建组件data
 * @author Sunshine
 *
 */
public class ComboUtils {

	/**
	 * 构建ComBo下拉列表框
	 * @param list
	 * @return
	 */
	public static String queryComBo(List<Map<String, Object>> list) {
		StringBuilder sb = new StringBuilder();
		sb.append("[{");
		sb.append("id:'").append("-1").append("',");
		sb.append("text:'").append("-- 请选择 --").append("',");
		sb.append("selected:").append("true");
		sb.append("}");
		if(list != null && list.size() != 0) {
			for(Map<String, Object> one : list) {
				sb.append("{");
				sb.append("id:'").append(String.valueOf(one.get("ID"))).append("',");
				sb.append("text:'").append(String.valueOf(one.get("TEXT"))).append("'");
				sb.append("}");
			}
		}
		sb.append("]");
		sb = new StringBuilder(sb.toString().replace("}{", "},{"));
		
		return sb.toString();
	}
	
	/**
	 * 构建ComBo下拉列表框默认选中第一列数据
	 * @param list
	 * @return
	 */
	public static String queryComBoOne(List<Map<String, Object>> list) {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		if(list != null && list.size() != 0) {
			int i=0;
			for(Map<String, Object> one : list) {
				sb.append("{");
				sb.append("id:'").append(String.valueOf(one.get("ID"))).append("',");
				sb.append("text:'").append(String.valueOf(one.get("TEXT"))).append("'");
				if(i==0) {
					sb.append(",").append("selected:").append("true");
					i++;
				}
				sb.append("}");
			}
		}
		sb.append("]");
		sb = new StringBuilder(sb.toString().replace("}{", "},{"));
		
		return sb.toString();
	}
}
