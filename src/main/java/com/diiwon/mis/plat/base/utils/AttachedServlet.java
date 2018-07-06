package com.diiwon.mis.plat.base.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.diiwon.mis.plat.base.cache.imp.SystemProperties;

public class AttachedServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	protected final Logger logger = Logger.getLogger(KeUploadServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		process(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		process(req, resp);
	}

	private void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
		InputStream is = null;  
        OutputStream os = null;
        try {  
//            File file = new File("d:/attached/" + fileType + "/" + uploadDate + "/" + fileName + "." + suffix);
        	//实际文件存储目录
			String dirPath = SystemProperties.getInstance().getProperty("uploadFileSavePath");
        	File file = new File(dirPath+request.getPathInfo());
            is = new FileInputStream(file);
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
              
            os = new BufferedOutputStream(response.getOutputStream());
            os.write(buffer);
            os.flush();
        } catch (Exception e) {  
            //判断suffix  
            //图片请求可以在此显示一个默认图片  
            //file显示文件已损坏等错误提示...  
            System.out.println("读取文件失败"+e);
        } finally {  
            if (is != null) {  
                try {  
                    is.close();  
                } catch (IOException e) {  
                	System.out.println("读取文件失败"+e);
                }  
                  
                if (os != null) {  
                    try {  
                        os.close();  
                    } catch (IOException e) {  
                    	System.out.println("读取文件失败"+e);
                    }  
                }  
            }  
        }  
	}
}