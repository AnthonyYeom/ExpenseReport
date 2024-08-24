package com.newlectrue.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calc1")
public class calc1 extends HttpServlet{

	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
		out.write("<form action=\"calc_server\"method=\"post\">");
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
}
