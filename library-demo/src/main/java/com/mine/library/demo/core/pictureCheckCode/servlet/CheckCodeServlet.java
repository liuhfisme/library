package com.mine.library.demo.core.pictureCheckCode.servlet;

import com.mine.library.demo.core.pictureCheckCode.util.CreatePictureCheckCode;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class CheckCodeServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,
					  HttpServletResponse response)throws ServletException,IOException{
		System.out.println("1111111111");
		//设置不缓存图片
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "No-cache");
		response.setDateHeader("Expirs", 0);

		response.setContentType("image/jpeg");
		CreatePictureCheckCode.getPictureCheckCode(request, response);
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//设置不缓存图片
		resp.setHeader("Pragma", "No-cache");
		resp.setHeader("Cache-Control", "No-cache");
		resp.setDateHeader("Expirs", 0);

		resp.setContentType("image/jpeg");
		CreatePictureCheckCode.getPictureCheckCode(req, resp);
	}

}
