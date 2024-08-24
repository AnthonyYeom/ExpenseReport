package Input;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Input.DB_connection;

@WebServlet("/AddData")
public class AddData extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DB_connection con = new DB_connection();
		con.connect();
		String year = req.getParameter("year");
		String [][] MAS_ARY = con.MAS_LIST(year);
		
		req.setAttribute("MAS_ARY",MAS_ARY);
		
		con.disconnect();
		req.getRequestDispatcher("/adddata.jsp").forward(req, resp);
	
	}

}
