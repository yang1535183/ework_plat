package com.diiwon.mis.plat.base.utils.ImgCaptcha;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReadFontByteProperties {
	protected final static Logger logger = LoggerFactory.getLogger(ReadFontByteProperties.class);
	
	static private String fontByteStr = null;
	static {//会在类被加载的时候执行且仅会被执行一次，一般用来初始化静态变量和调用静态方法。
	    loads();
	}
	
	synchronized static public void loads() {
	    if (fontByteStr == null) {
	        InputStream is = ReadFontByteProperties.class.getResourceAsStream("/fontByte.properties");
	        Properties dbproperties = new Properties();
	        try {
	            dbproperties.load(is);
	            fontByteStr = dbproperties.getProperty("fontByteStr").toString();
	        } catch (Exception e) {
	        	logger.info("不能读取属性文件. 请确保" + fontByteStr+"在CLASSPATH指定的路径中");
	        }
	    }
	}
	    
    public static String getFontByteStr() {
        if (fontByteStr == null)
            loads();
        return fontByteStr;
    }
}
