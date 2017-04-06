package com.mine.library.demo.plugins.uploadify.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class Uploadify extends HttpServlet {
	/**
	 * 文件上传
	 */
	private static final long serialVersionUID = 2384326745121073713L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("-------------------UpLoadify-doPost");
		System.out.println("-------------------QueryString::::"+ request.getQueryString());
		System.out.println("*******************getParameter('folder'):::"+request.getParameter("folder"));
		/*如果没有设置folder则return;方法结束*/
		if (request.getParameter("folder") == null|| request.getParameter("folder") == "") {
			System.out.println("-------------------request.getParameter('folder')::::"+ request.getParameter("folder") + " then return");
			return;
		}
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		/*获取项实际目根路径*/
		String path = this.getServletContext().getRealPath("/");
		System.out.println("*******************getRealPath:::"+path);
		/*获取文件存放文件夹名称*/
		String fileD = request.getParameter("folder");
		/*声明并初始化文件资源存放路径*/
		String sourcePath = path + "upload/source/";
		/*定义上传文件存放路径*/
		path = path + "upload/" + fileD + "/";
		System.out.println("-------------------UpLoadify-path::::" + path);
		File folder = new File(path);
		File sourceFolder = new File(sourcePath);
		/*判断文件夹不存在则新建文件夹*/
		if (!folder.exists()) {
			System.out.println("-------------------UpLoadify::::" + "创建文件夹"+ fileD);
			folder.mkdirs();
		}
		/*判断资源文件夹不存在则新建文件夹*/
		if (!sourceFolder.exists()) {
			System.out.println("-------------------UpLoadify::::"+ "创建文件夹source");
			sourceFolder.mkdirs();
		}
		ServletFileUpload sfu = new ServletFileUpload(new DiskFileItemFactory());
		sfu.setHeaderEncoding("UFT-8");
		try {
			List<?> fileList = sfu.parseRequest(request);
			String sourceName = "";
			String extName = "";
			String name = "";
			String sfileName = "";
			for (int i = 0; i < fileList.size(); i++) {
				System.out.println("-------------------UpLoadify fileList[" + i
						+ "]::::" + fileList.get(i));
				FileItem fi = (FileItem) fileList.get(i);
				if (!fi.isFormField()) {
					sourceName = fi.getName();
					System.out.println("-------------------UpLoadify name::::"
							+ sourceName);
					if (sourceName == null || "".equals(sourceName.trim())) {
						continue;
					}
					if (sourceName.lastIndexOf(".") >= 0) {
						// 扩展名
						name = sourceName.substring(0,
								sourceName.lastIndexOf("."));
						extName = sourceName.substring(sourceName
								.lastIndexOf("."));
						System.out
								.println("-------------------UpLoadify extName::::"
										+ extName);
					}
					// 文件名规则 前缀 + 时间 + 两位随机数 + 文件分类(标识图片尺寸) + 扩展名
					Calendar ca = Calendar.getInstance();
					DecimalFormat df = new DecimalFormat();
					df.setMinimumIntegerDigits(2);
					String st = "zxy";
					if (st != null && st.length() > 6) {
						st = st.substring(0, 6);
					}
					String dateTime = ca.get(Calendar.YEAR) + ""
							+ df.format(ca.get(Calendar.MONTH)) + ""
							+ df.format(ca.get(Calendar.DATE)) + ""
							+ df.format(ca.get(Calendar.HOUR)) + ""
							+ df.format(ca.get(Calendar.MINUTE)) + ""
							+ df.format(ca.get(Calendar.SECOND));
					Random rand = new Random();
					int rand_num = rand.nextInt(89) + 10;
					String fileName = st + "_" + dateTime + "_" + rand_num
							+ extName;
					sfileName = name + "_" + dateTime + "_" + rand_num
							+ extName;
					File saveSourceFile = new File(sourcePath + sfileName);
//					File saveFile = new File(path + fileName);
					System.out.println(sourcePath + sfileName);
					System.out.println(path + fileName);
					fi.write(saveSourceFile);
					// fi.write(saveFile);
					System.out
							.println("-------------------UpLoadify fileSourceName::::"
									+ sourceName);
					System.out
							.println("-------------------UpLoadify fileName::::"
									+ fileName);
				}
			}
			System.out.println("sfileName--" + sfileName);
			response.getWriter().println(sfileName);
		} catch (FileUploadException e) {
			response.getWriter().println("0");
			e.printStackTrace();
		} catch (Exception e) {
			response.getWriter().println("0");
			e.printStackTrace();
		}
	}
}
