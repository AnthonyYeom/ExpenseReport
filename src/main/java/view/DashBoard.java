package view;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import Input.DB_connection;

@WebServlet("/DashBoard")
public class DashBoard extends HttpServlet{

@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	DB_connection con = new DB_connection();
	System.out.println("live???");
	con.connect();
	String [] YearList = con.YearList();
	
	Cookie[] c = req.getCookies();
	String c_cat_type = null;
	String c_year = null;
	
	if(req.getParameter("RollbackYear")!=null&&!req.getParameter("RollbackYear").equals("none")) {
		
		String RollbackYear = req.getParameter("RollbackYear");
		con.Delete_Year_Mon(RollbackYear);
		
	}
	
	
	if(c!=null) { 
		for(Cookie cookie : c) {
			
			if(cookie.getName().equals("cat_type")) {
				c_cat_type = cookie.getValue();
				System.out.println("c_cat_type");
				System.out.println(c_cat_type);
				
			}
			else if(cookie.getName().equals("year")) {
				c_year = cookie.getValue();
				System.out.println("c_year");
				System.out.println(c_year);
				
			}
			
		}
		if(c_year!=null&&c_year!="") {
		
		if(c_cat_type!=null&&c_cat_type!="") {
			String [][] Dashboard_MAS_LIST = con.Dashboard_MAS_LIST(c_cat_type,c_year);
			String [] YearMonList = con.Year_Mon_List(c_year);
			String [][] Income_Expense_List= con.Income_Expense_List(c_year);
			
			req.setAttribute("Items",Dashboard_MAS_LIST);
			req.setAttribute("cat_type", c_cat_type);
			req.setAttribute("YearMonList", YearMonList);
			req.setAttribute("Income_Expense_List", Income_Expense_List);
			
		}
		
		}
	}
	
	
	
	req.setAttribute("YearList",YearList);
	
	if(req.getParameter("catergory")!=null&&req.getParameter("SelectedYear")!=null&&!req.getParameter("SelectedYear").equals("none")&&!req.getParameter("catergory").equals("none")) {
		
	
	String cat_type = req.getParameter("catergory");
	String SelectedYear = req.getParameter("SelectedYear");
	
	
	
			String [][] Dashboard_MAS_LIST = con.Dashboard_MAS_LIST(cat_type,SelectedYear);
			String [] YearMonList = con.Year_Mon_List(SelectedYear);
			//String [][] Income_Expense_List= con.Income_Expense_List(SelectedYear);
			String [][] Income_Expense_List = con.Income_Expense_List(SelectedYear);
			
			req.setAttribute("Items",Dashboard_MAS_LIST);
			req.setAttribute("cat_type", cat_type);
			req.setAttribute("YearMonList", YearMonList);
			req.setAttribute("Income_Expense_List", Income_Expense_List);
			
			
			Cookie c1 = new Cookie("cat_type", cat_type);
			c1.setPath("/DashBoard");
			resp.addCookie(c1);
			
			Cookie c2 = new Cookie("year", SelectedYear);
			c2.setPath("/DashBoard");
			resp.addCookie(c2);
		
			
			
	}
	
	

	
	con.disconnect();
	req.getRequestDispatcher("/ListView/DashBoard.jsp").forward(req, resp);

}
}
