package com.diiwon.mis.plat.base.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 匹配正则表达式
 * @author Sunshine
 *
 */
public class MatcherUtils {
	
	public static Matcher matcher(String regEx, String str) {
		Pattern pattern = Pattern.compile(regEx);
		Matcher matcher = pattern.matcher(str);
		return matcher;
	}

	/**
	 * 匹配正则表达式方法
	 * @param regEx	正则
	 * @param str	字符串
	 * @return
	 */
	public static boolean mtcRgEps(String regEx, String str) {
		Matcher m = matcher(regEx, str);
		boolean found = false;
		while (m.find()) {
			found = true;
		}
		return found;
	}
	
	/**
	 * 去掉String中特殊字符处理方式
	 * @param regEx		正则表达式
	 * @param str		字符串
	 * @param entiy		去掉字符
	 * @return
	 */
	public static String delspecialsign(String regEx, String str, String entiy) { //去掉特殊字符
		
		Matcher m = matcher(regEx, str);
		return m.replaceAll(entiy);
	}
}
