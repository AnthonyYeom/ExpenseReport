package com.newlectrue.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calc3")
public class calc3 extends HttpServlet{
//	@Override
//	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		if(req.getMethod().equals("GET")) {
//			System.out.println("GET");
//		}
//		else if(req.getMethod().equals("POST")) {
//			System.out.println("POST");
//		}
//		
//		super.service(req, resp); // 부모가 갖고 있는 서비스 함수 호출, get 요청이 오면 부모 클래스에서 Doget메소드를 찾아옴, 서비스 함수를 쓸때만 필요함, Doget, Dopost를 사용하면 이 서비스 함수는 필요가 없음
//		
//		
//	}
	//↓ cal1의 get과, cal_server에 구현한 post 로직을 doget과 dopost로 나눠서 한 파일에 작성하는 로직 
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("GET");
		
		PrintWriter out = resp.getWriter();
		Cookie[] cookies = req.getCookies();
		String exp = "0";
		if(cookies!=null) {
		for(Cookie c : cookies) {//쿠키 배열 for문 이니까 쿠키 객체를 생성하는듯
			if(c.getName().equals("number")) {
				exp = c.getValue();
				System.out.print("front");
				System.out.println(exp);
				break;
			}
			
		}}
		
			out.write("<!DOCTYPE html>");
			out.write("<html>");
			out.write("<head>");
			out.write("<meta charset=\"UTF-8\"");
			out.write("<title>Insert title here</title>");
			out.write("<style>");
			out.write("input{");
			out.write("width:50px;");
			out.write("height:50px;");
			out.write("}");
			out.write(".result{");
			out.write("height: 50px;");
			out.write("background: #e9e9e9;");
			out.write("font-size : 24px;");
			out.write("font-weight : bold;");
			out.write("text-align: right;");
			out.write("padding:0px 5px;");
			out.write("}");
			out.write("</style>");
			out.write("</head>");
			out.write("<body>");
			out.write("<form method=\"post\">");
			out.write("<table>");
			out.write("<tr>");
			out.printf("<td class=\"result\"colspan=\"4\">%s</td>", exp);
			out.write("</tr>");
			out.write("<tr>");
			out.write("<td><input type=\"submit\"name=\"operator\"value=\"+\"></td>");
			out.write("<td><input type=\"submit\"name=\"operator\"value=\"-\"></td>");
			out.write("<td><input type=\"submit\"name=\"operator\"value=\"/\"></td>");
			out.write("<td><input type=\"submit\"name=\"operator\"value=\"*\"></td>");
			out.write("</tr>");
			out.write("<tr> ");
			out.write("<td><input type=\"submit\"name=\"number\"value=\"1\"></td>");
			out.write("<td><input type=\"submit\"name=\"number\"value=\"2\"></td>");
			out.write("<td><input type=\"submit\"name=\"number\"value=\"3\"></td>");
			out.write("<td><input type=\"submit\"name=\"number\"value=\"4\"></td>");
			out.write("</tr>");
			out.write("<tr> ");
			out.write("<td><input type=\"submit\"name=\"number\"value=\"5\"></td>");
			out.write("<td><input type=\"submit\"name=\"number\"value=\"6\"></td>");
			out.write("<td><input type=\"submit\"name=\"number\"value=\"7\"></td>");
			out.write("<td><input type=\"submit\"name=\"number\"value=\"8\"></td>");
			out.write("</tr>");
			out.write("<tr>");
			out.write("<td><input type=\"submit\"name=\"number\"value=\"9\"></td>");
			out.write("<td><input type=\"submit\"name=\"number\"value=\"0\"></td>");
			out.write("<td><input type=\"submit\"name=\"clear\"value=\"C\"></td>");
			out.write("<td><input type=\"submit\"name=\"operator\"value=\"=\"></td>");
			out.write("</tr>");
			out.write("</table> ");
			out.write("</form>");
			out.write("</body>");
			out.write("</html>");
		
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("POST");
		

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
		
		exp_cookie.setPath("/calc3"); //쿠키가 전달되는 패스를 셋팅함
		resp.addCookie(exp_cookie);
		resp.sendRedirect("calc3"); //자기가 자기를 호출하기 때문에 get 요청을 의미함
	}
}
