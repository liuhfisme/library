package com.mine.library.demo.core.pictureCheckCode.struts.action;

import com.mine.library.demo.core.pictureCheckCode.util.CreatePictureCheckCode;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckCodeAction extends ActionSupport {

	public void codePicture() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request=ServletActionContext.getRequest();
		// 设置不缓存图片
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "No-cache");
		response.setDateHeader("Expirs", 0);

		response.setContentType("image/jpeg");
		CreatePictureCheckCode.getPictureCheckCode(request, response);
	}

}