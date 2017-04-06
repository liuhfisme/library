package com.mine.library.demo.core.cookie;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class SessionServlet extends HttpServlet{

	/**
	 *
	 */
	private static final long serialVersionUID = 5676311017159028583L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		HttpSession session=req.getSession();
		Integer count=(Integer) session.getAttribute("count");
		if(count==null){
			count=1;
		}else{
			count++;
		}
		session.setAttribute("count", count);
		session.setMaxInactiveInterval(30);
		PrintWriter out=resp.getWriter();
		out.write("您访问了服务器 "+count+" 次");
		out.close();
	}

}
