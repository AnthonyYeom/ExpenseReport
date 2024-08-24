package com.newlectrue.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/Nana2")
public class Nana2 extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		String temp_num = request.getParameter("num");
		int num=1;
		String result = "";
		
		
		if((temp_num!="") && (temp_num!=null)){
			num = Integer.parseInt(temp_num);
			if(num%2==1){
				result = "홀수";
			}
			else {
				result = "짝수";
			}
			
		}
		else{
			result ="request";
		}
		String[] names = {"김현영","염서윤","염탁재"};
		request.setAttribute("names", names);
		
		Map<String,Object> notice = new HashMap<String,Object>();
		notice.put("1","서윤이는 딸입니다");
		notice.put("2","현영이는 엄마입니다.");
		notice.put("3","탁재는 아빠입니다.");
		request.setAttribute("notice", notice);
		
	request.setAttribute("result", result); 
	RequestDispatcher dispatcher = request.getRequestDispatcher("Nana2.jsp");
	dispatcher.forward(request,response);
	
	}

}
