package com.mine.library.demo.core.cookie;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class AddCookieServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2447722392785751495L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException { 
		Cookie[] cookies=req.getCookies();
		if(cookies!=null){
			for(Cookie cookie:cookies){
				System.out.println(cookie.getName());
			}
		}
		HttpSession session=req.getSession();
		session.setAttribute("sessionTest", "sessionTest");
		if(session!=null){
			System.out.println(session.getId());
		}
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out=resp.getWriter();
		Cookie cookie=new Cookie("username", "liuff2513");
		Cookie cookie2=new Cookie("password", "liuff6174");
		cookie.setMaxAge(10);
		cookie2.setMaxAge(15);
		resp.addCookie(cookie);
		resp.addCookie(cookie2);
		out.write("1111111111");
		out.close();
	}
	
}
