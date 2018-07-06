package com.diiwon.mis.plat.xmgl.file.upload.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.diiwon.mis.plat.base.pojo.TMisUser;
import com.diiwon.mis.plat.base.utils.Common;
import com.diiwon.mis.plat.base.utils.EWorkController;
import com.diiwon.mis.plat.base.utils.ImgCaptcha.ImgCaptcha;
import com.diiwon.mis.plat.xmgl.file.upload.service.UploadService;
import com.diiwon.mis.plat.xmgl.pojo.TextImpdata;

import net.sf.json.JSONObject;

/****
 * 文件上传接口类
 * 
 * @author Sunshine
 *
 */
@Controller
public class UploadController extends EWorkController {
	@Autowired
	private UploadService uploadService;

	/****
	 * H5文件上传接口
	 */
	@RequestMapping("/file/batchUpLoad/uploadH5.do")
	public void upLoadH5(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			TextImpdata textImpdata, String pzmc) {
		TMisUser user = this.getframeService().goIn(session);
		JSONObject json = Common.getJsonResult(this.uploadService.upLoadH5(request, user, textImpdata, pzmc));
		this.sendDirectToClient(response, json.toString());
	}

	/** 下载 **/
	@RequestMapping("/file/download/fileDownload.do")
	public void fileDownload(HttpServletRequest request, HttpServletResponse response, 
			HttpSession session, String fileid) {
		TMisUser user = this.getframeService().goIn(session);

		JSONObject json = Common.getJsonResult(this.uploadService.fileDownload(request, response, user, fileid));
		this.sendDirectToClient(response, json.toString());
	}

	/** 查看图片 **/
	@RequestMapping("/file/download/queryImg.do")
	public void queryImg(HttpServletRequest request, HttpServletResponse response, 
			HttpSession session, String fileid) {
		TMisUser user = this.getframeService().goIn(session);
		this.uploadService.queryImg(request, response, user, fileid);
	}

	/** 生成图片验证码 **/
	@RequestMapping("/file/verification.do")
	public void verification(HttpServletRequest request, HttpServletResponse response, 
			HttpSession session, Model model)
			throws IOException {
		response.setContentType("image/jpeg");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		ImgCaptcha instance = new ImgCaptcha();
		this.add(model, "verific", instance.getCode());
		session.setAttribute("verificode", instance.getCode());
		session.setAttribute("verification", instance.getCode());
		// 向页面输出验证码图片
		instance.write(response.getOutputStream());
	}

	/***验证码校验**/
	@RequestMapping("/checkVerificode.do")
	public void checkVerificode(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			String code) {
		try {
			Boolean flag = false;
			String verificode = session.getAttribute("verificode").toString();
			verificode = verificode.toUpperCase();
			code = code.toUpperCase();
			
			flag=verificode.equals(code);
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("success", flag);

			JSONObject json = Common.getJsonResult(result);
			this.sendDirectToClient(response, json.toString());
		} catch (Exception e) {
			
		}
	}
}
