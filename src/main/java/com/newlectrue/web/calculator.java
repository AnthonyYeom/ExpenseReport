package com.newlectrue.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/calculator.do")
public class calculator extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String[] num = req.getParameterValues("num"); //text input에서 num인 이름을 배열로 받아온다.
		String operator = req.getParameter("operator");
		System.out.print(num[0]);
		// ServletContext application = req.getServletContext();
		// HttpSession session = req.getSession();
		
		Cookie[] cookies = req.getCookies();
		int int_num = Integer.parseInt(num[0]);
		int result;
		
		PrintWriter out = resp.getWriter();
		try {
			if(operator.equals("=")){// '=='표시는 값이 할당된 메모리 주소를 비교한다...
				
				// int int_num_stored = (Integer) application.getAttribute("value");
				// String operator_stored = (String) application.getAttribute("op");
				
				//int int_num_stored = (Integer) session.getAttribute("value");
				//String operator_stored = (String) session.getAttribute("op");
				int int_num_stored = 0;
				String operator_stored ="";
				
				
				for(Cookie c : cookies) {//쿠키 배열 for문 이니까 쿠키 객체를 생성하는듯
					if(c.getName().equals("value")) {
						int_num_stored = Integer.parseInt(c.getValue());
						break;
					}
					
				}
				
				
				for(Cookie c : cookies) {
					if(c.getName().equals("op")) {
						operator_stored = c.getValue();
						break;
					}
					
				}
				
				if(operator_stored.equals("+")) {
					result = int_num_stored+int_num;
					
				}
				
				else {
					result = int_num_stored-int_num;
				}
				out.print(result);
			}
			else {
				//application.setAttribute("value",Integer.parseInt(num[0])); //
				//application.setAttribute("op",operator);
				//session.setAttribute("value",Integer.parseInt(num[0])); //
				//session.setAttribute("op",operator);
				Cookie val_cookie = new Cookie("value",num[0]);
				Cookie op_cookie = new Cookie("op",operator);
				val_cookie.setPath("/calculator.do");
				op_cookie.setPath("/calculator.do");
				resp.addCookie(val_cookie);
				resp.addCookie(op_cookie);
				
			}
	
		}
		catch (NumberFormatException e)
		{
			out.print("오류");
		}
	
	
		
		
		
	}
	}
