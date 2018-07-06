package com.diiwon.mis.plat.base.utils;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

/**
 * 常用字符串操作基类
 * 
 */
public class StringUtilsExt extends StringUtils {
	
	public static int Mathrandom() {
		return (int)(Math.random()*1000)+1000;
	}
	
	/**
	 * 使用MD5加密算法加密字符串
	 * @param STR 待加密的字符串
	 * @param MD5_LX MD5长度类型
	 * @return 加密后MD5值
	 */
	public static String EncryptMD5(String STR, String MD5_LX) {
		String result = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");	// 创建一个MD5消息文搞
			md.update(STR.getBytes("UTF8"));						// 更新被文搞描述的位元组
			byte s[] = md.digest();									// 最后更新使用位元组的被叙述的排列,然后完成文摘计算
			for(int i=0; i<s.length; i++) {
				result += Integer.toHexString((0x000000FF & s[i]) | 0xFFFFFF00).substring(6);
			}
			
			if (SysCode.MD5_LX_16.equals(MD5_LX)) {
				result = result.substring(8, 24);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 查询数组中长度最大的元素
	 * @param array
	 * @return
	 */
	public static int queryArrayMaxLen(String[] array) {
		int index = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i].length() > array[index].length()) {
				index = i;
			}
		}
		
		return array[index].length();
	}
	
	public static String subStrByAny(String rs) {
		return rs.substring(rs.indexOf("/", 2)+1, rs.indexOf("."));
	}
	
	 /**
     * 将一个字符串的首字母改为大写或者小写
     *
     * @param srcString
     *            源字符串
     * @param flag
     *            大小写标识，ture大写，false小写
     * @return 改写后的新字符串
     */
    public static String toUpperCaseInitial(String srcString, boolean flag) {
        StringBuilder sb = new StringBuilder();
        if (flag) {
            sb.append(Character.toUpperCase(srcString.charAt(0)));
        } else {
            sb.append(Character.toLowerCase(srcString.charAt(0)));
        }
        sb.append(srcString.substring(1));
        return sb.toString();
    }
    
    /****
     * 将1,2,3转换成 '1','2','3'格式
     * @param str
     * @return
     */
    public static String arrStr(String str) {
    	if(StringUtilsExt.isNotBlank(str)) {
    	//	return "'"+str.replaceAll(",", "','")+"'";
    		return str.replaceAll(",", "/',\'");
    	}
    	else {
    		return null;
    	}
    }
    
    //FormId生成
	public static long genItemId() {
		// 取当前时间的长整形值包含毫秒
		long millis = System.currentTimeMillis();
		// long millis = System.nanoTime();
		// 加上两位随机数
		Random random = new Random();
		int end2 = random.nextInt(99);
		// 如果不足两位前面补0
		String str = millis + String.format("%02d", end2);
		long id = new Long(str);
		return id;
	}
	
	// 注册码生成
	public static String genZcm(String maxnum) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		maxnum = String.valueOf(Integer.valueOf(substring(maxnum, maxnum.length()-4, maxnum.length()))+1);
		
		return sdf.format(new Date()) + maxnum;
	}
	
	/**获取字符串中的数值**/
	public static String getNumber(String str) {
		String regEx="[^0-9]";  
		Pattern p = Pattern.compile(regEx);  
		Matcher m = p.matcher(str);  
		
		return m.replaceAll("").trim();
	}
}
