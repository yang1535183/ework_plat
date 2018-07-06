package com.diiwon.mis.plat.xmgl.file.upload.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.diiwon.mis.plat.base.cache.imp.UserProperties;
import com.diiwon.mis.plat.base.pojo.TMisUser;
import com.diiwon.mis.plat.base.utils.DateUtils;
import com.diiwon.mis.plat.base.utils.StringUtilsExt;
import com.diiwon.mis.plat.base.utils.SysCode;
import com.diiwon.mis.plat.frame.dao.FrameDao;
import com.diiwon.mis.plat.xmgl.file.upload.dao.UploadDao;
import com.diiwon.mis.plat.xmgl.gcgl.dao.GcbjDao;
import com.diiwon.mis.plat.xmgl.pojo.TMisPz;
import com.diiwon.mis.plat.xmgl.pojo.TextImpdata;

/****
 * 文件上传业务处理类
 * @author Sunshine
 *
 */
@Service
public class UploadService {
	@Autowired
	private FrameDao frameDao;
	
	@Autowired
	private UploadDao uploadDao;
	
	@Autowired
	private GcbjDao gcbjDao;

	/***上传**/
	public Map<String, Object> upLoadH5(HttpServletRequest request, TMisUser user, 
			TextImpdata textImpdata, String pzmc) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("success", false);
		map.put("data", "");
		
		if(null != user) {
			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
	        if(multipartResolver.isMultipart(request)){
	            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
	            Iterator<String> iter = multiRequest.getFileNames();
	            while (iter.hasNext()) {
	            	try {
		                MultipartFile file = multiRequest.getFile(iter.next()); // 取得上传文件  
		                TMisPz tMisPz = UserProperties.getUPLOADPZ_CACHE().get(pzmc);
		                if(file.getSize() <= Integer.valueOf(tMisPz.getScdx())) {
		                	String fileName = file.getOriginalFilename(); // 上传文件名
		                	if(StringUtilsExt.isNotBlank(fileName)) {
		                		if(tMisPz.getSclx().indexOf(String.valueOf(fileName.substring(fileName.lastIndexOf('.')+1)).toLowerCase()) != -1) {
			                		String fileExtName = fileName.substring(fileName.lastIndexOf('.'));//文件扩展名
			                		String newFilename = DateUtils.getDataName("_"+SysCode.SYS_UPLOADH5, false)+fileExtName;
					                String folder = tMisPz.getCclj();
					                
					                File myfolder = new File(folder);
									myfolder.mkdir();
									
									File source = new File(folder+"/"+newFilename);
									if (!source.isDirectory()) {
										source.mkdirs();
									}
									file.transferTo(source);
									
									if(StringUtilsExt.isBlank(textImpdata.getGcid()) 
											|| "null" == textImpdata.getGcid()) {//当工程ID为空时给默认值
										textImpdata.setGcid(DateUtils.getDataName("", true));
									}
									String filename = fileName.substring(0, fileName.lastIndexOf('.'));
									textImpdata.setFilepath(tMisPz.getCclj()+"/"+newFilename);
									textImpdata.setFilename(filename);
									textImpdata.setScry(user.getLoginname());
									textImpdata.setScsj(DateUtils.formatDate(SysCode.RQGSH_HXGS_CRQ));
									textImpdata.setIsdel(SysCode.FJ_YXBS_YSX);
									textImpdata.setCclx(SysCode.FJ_CCLX_JDLJ_WJCC);
									textImpdata.setUpty(pzmc);
									
									/**将原先上传数据状态改为隐藏**/
									if(!StringUtilsExt.equals(String.valueOf(textImpdata.getId()), "null")
											|| StringUtilsExt.equals(textImpdata.getUpty(), SysCode.UPLOAD_HTWJ)) {
										this.gcbjDao.updateIsdel(textImpdata);
									}
									this.frameDao.save(textImpdata);
									
									map.put("gcid", textImpdata.getGcid());
									map.put("data", "文件："+filename+"，上传成功！！！");
									map.put("success", true);
			                	}
			                	else {map.put("data", "文件类型不匹配！！！");}
		                	}
		                	else {map.put("data", "请选择文件！！！");}
		                }
		                else {map.put("data", "文件超过大小！！！");}
						
					} catch (IOException e) {
						map.put("data", "文件上传失败");
						e.printStackTrace();
					}
	            }  
	  
	        } 
		}
		else {
			map.put("data", "系统拒绝访问：权限受限！！！");
		}
		
		return map;
	}

	/***下载**/
	public Map<String, Object> fileDownload(HttpServletRequest request, HttpServletResponse response, 
			TMisUser user, String fileid) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("success", false);
		map.put("data", "");
		
		if(null != user) {
			try {
				queryImg(request, response, user, fileid);
			}
			catch (Exception e) {
				map.put("data", "系统拒绝访问：下载失败！");
				e.printStackTrace();
			}
		}
		else {
			map.put("data", "系统拒绝访问：请重新登陆！");
		}
		
		return map;
		
	}
	
	/**查看**/
	public void queryImg(HttpServletRequest request, HttpServletResponse response, TMisUser user, String fileid) {
		if(null != user && StringUtilsExt.isNotBlank(fileid)) {
			try {
				String agent = (String) request.getHeader("USER-AGENT");
				Map<String, Object> wjxx = this.uploadDao.getFileForMap(fileid);
				String filurl = String.valueOf(wjxx.get("filepath"));
				
				String fileName = String.valueOf(wjxx.get("filename")) +
						filurl.substring(filurl.lastIndexOf('.'), filurl.length());
				if (agent != null && agent.indexOf("MSIE") == -1) {
					fileName = new String(fileName.toString().getBytes("UTF-8"), "ISO-8859-1");
				}
				else {
					fileName = URLEncoder.encode(fileName, "utf-8");
				}
				
				ServletOutputStream sos = response.getOutputStream();							// 获得输出流
				response.reset();																// 清空输出流
				response.setHeader("Content-disposition", "attachment; filename="+ fileName);	// 设定输出文件头
				response.setContentType("application/octet-stream");							// 定义输出类型
				if (SysCode.SCZY_CCLX_SJKCC.equals(wjxx.get("cclx"))) {
					if (null == wjxx.get("wjnr")) {
						sos.write(new byte[0]);
					}
					else {
						sos.write((byte[])wjxx.get("wjnr"));
					}
				}
				else if (SysCode.FJ_CCLX_XDLJ_WJCC.equals(wjxx.get("cclx"))) {
					String filePath = request.getSession().getServletContext().getResource((String)wjxx.get("filepath")).getPath();
					File file = new File(filePath);
					if(file.exists()) {
						FileInputStream fileInputStream = new FileInputStream(new File(filePath));
						byte[] fileByte = new byte[fileInputStream.available()];
						fileInputStream.read(fileByte);
						sos.write(fileByte);
						fileInputStream.close();
					}
				}
				else if (SysCode.FJ_CCLX_JDLJ_WJCC.equals(wjxx.get("cclx"))) {
					String filePath = (String)wjxx.get("filepath");
					File file = new File(filePath);
					if(file.exists()) {
						FileInputStream fileInputStream = new FileInputStream(file);
						byte[] fileByte = new byte[fileInputStream.available()];
						fileInputStream.read(fileByte);
						sos.write(fileByte);
						fileInputStream.close();
					}
				}
				sos.flush();
	            sos.close();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

}
