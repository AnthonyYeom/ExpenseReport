package com.newlectrue.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/calc_server")
public class calc_server extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String[] number = req.getParameterValues("number"); //text input에서 num인 이름을 배열로 받아온다.
		String operator = req.getParameter("operator");
		String clear = req.getParameter("clear");
	
		// ServletContext application = req.getServletContext();
		// HttpSession session = req.getSession();
		String exp = "";
		
		
		Cookie[] cookies = req.getCookies();
		
		if(cookies !=null) {
		for(Cookie c : cookies) {//쿠키 배열 for문 이니까 쿠키 객체를 생성하는듯
			if(c.getName().equals("number")) {
				exp=c.getValue();
				System.out.print("back");
				System.out.println(exp);
				break;
			}
			
		}}
		
		if(number!=null) {
			exp = exp + number[0];
			System.out.print("number");
			System.out.print(exp);
		}
		
		if(operator!=null && operator.equals("=")) {
			exp = "합계";
		
		}
		else if(operator!=null){
			exp = exp + operator;	
		}
		
		
		Cookie exp_cookie = new Cookie("number",exp);
		
		if(clear!=null) {
			exp_cookie.setMaxAge(0);
		
		}
		resp.addCookie(exp_cookie);
		resp.sendRedirect("/calc1");
	}
		
		}

		
	
