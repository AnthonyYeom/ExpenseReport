package Input;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SelectDetail_2")
public class SelectDetail_2 extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		DB_connection con = new DB_connection();
		con.connect();
		String row = req.getParameter("row");
		String month = req.getParameter("month");
		String year = req.getParameter("year");
		
		String [][] Detail_List = con.Select_Detail_2(row,month,year);
		
		
		req.setAttribute("Detail_List",Detail_List);
		
		con.disconnect();
		req.getRequestDispatcher("/Detail_List.jsp").forward(req, resp);
		
		
	}
}
