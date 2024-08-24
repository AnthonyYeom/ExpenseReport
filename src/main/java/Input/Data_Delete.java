package Input;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Input.DB_connection;
@WebServlet("/Data_Delete")
public class Data_Delete extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		String date_deleted = req.getParameter("date_deleted");
		
		DB_connection con = new DB_connection();
		con.connect();
		
		con.Data_Delete(date_deleted);
		
		con.disconnect();
		resp.sendRedirect("/ListView");
		
		
	
	}

}
