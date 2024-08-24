package view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Input.DB_connection;

@WebServlet("/ListView")
public class ListView extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

		DB_connection con = new DB_connection();
		con.connect();
		
		//등록시점들 목록 조회 및 값 setting 
		String [] DATE_ary = con.Date_list(); 
		String [] Master_Record = con.Master_Record();
		System.out.println(Master_Record[0]);
		req.setAttribute("DATE_ary",DATE_ary); 
		req.setAttribute("Master_Record",Master_Record);
		
		
		//입력한 값에 대한 목록 조회 및 값 setting 
		String date = req.getParameter("date");
		String year = req.getParameter("year");
		if(date!=""&&date!=null) {
		String [][] ary = con.List_View(date);
		String [][] MAS_ARY = con.MAS_LIST(year);
		String [][] MAS_ARY_INCOME = con.MAS_LIST_INCOME(year);
		// 24.06.02 : 추후에 쓸 로직, String [][] MAS_LIST_INCOME = con.MAS_LIST_INCOME(year);
		req.setAttribute("ary",ary);
		req.setAttribute("MAS_ARY",MAS_ARY);
		req.setAttribute("MAS_ARY_INCOME",MAS_ARY_INCOME);
		
		/* 쿠키값 생성 */
//		Cookie c1 = new Cookie("date", date);
//		c1.setPath("/adddata");
//		resp.addCookie(c1);
//		
		Cookie c1 = new Cookie("year", year);
		c1.setPath("/ListView");
		resp.addCookie(c1);
		
		
		}
		
		
		
		con.disconnect();
		req.getRequestDispatcher("/ListView/List.jsp").forward(req, resp);
	}

}
