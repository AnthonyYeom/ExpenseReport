package view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Input.DB_connection;

@WebServlet("/Budget_Management")
public class Budget_Management extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		DB_connection con = new DB_connection();
		con.connect();
		String [] Budget_Year_List = con.Budget_Year_List();
		req.setAttribute("Budget_Year_List", Budget_Year_List);
		
		if(req.getCookies()!=null) {
		Cookie [] cookies = req.getCookies();
		for(Cookie c : cookies) {
			if(c.getName().equals("Budget_Year")){
				String Budget_Year = c.getValue();
				String [][] Budget_Detail_List = con.Budget_Detail_List(Budget_Year);
				req.setAttribute("Budget_Detail_List", Budget_Detail_List);
				req.setAttribute("year", Budget_Year);
			}
			
			
		}
		}

		
		
		if(req.getParameter("Budget_Year")!=null&&!req.getParameter("Budget_Year").equals("none")) {
			String Budget_Year = req.getParameter("Budget_Year");
			String [][] Budget_Detail_List = con.Budget_Detail_List(Budget_Year);
			req.setAttribute("Budget_Detail_List", Budget_Detail_List);
			req.setAttribute("year", Budget_Year);
			
			
			Cookie c1 = new Cookie("Mapping_Key",Budget_Detail_List[0][5]);
			c1.setPath("/Budget_Management");
			resp.addCookie(c1);
			
			Cookie c2 = new Cookie("Budget_Year",Budget_Year);
			c2.setPath("/Budget_Management");
			resp.addCookie(c2);
			
				}
		
		
		con.disconnect();

		
		req.getRequestDispatcher("/Budget/Budget_Management.jsp").forward(req, resp);
	
	}

}
