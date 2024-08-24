package com.newlectrue.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/main.do")
public class Nana extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	
	PrintWriter out = resp.getWriter();
	int cnt = 100;
	String temp_cnt = req.getParameter("cnt");
	
	if ((temp_cnt!=null) && (temp_cnt != "")) {
		
		try {
			cnt = Integer.parseInt(temp_cnt);
			for (int i=0; i<cnt; i++) {
				out.print("안녕!!"+i+"<br >");
				}
			PrintWriter out2 = resp.getWriter();
			String content=req.getParameter("content");
			out2.print(content);
			
		}
		catch (NumberFormatException e)
		{
			out.print("오류");
		}
	}
	
		
	}
}
