package Input;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Input.DB_connection;
@WebServlet("/Add_Budget_Items")
public class Add_Budget_Items extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DB_connection con = new DB_connection();
		con.connect();
		
		String Mapping_Key = req.getParameter("Mapping_Key");
		
//		if(!req.getParameter("Mapping_Key").equals("")&&req.getParameter("Mapping_Key")!=null) {
//		Mapping_Key = req.getParameter("Mapping_Key");
//		}
//		
//		else{
//			Cookie[] cookies = req.getCookies();
//		for(Cookie c : cookies) {
//			if(c.getName().equals("Mapping_Key")) {
//				Mapping_Key=c.getValue();
//								
//			}
//		}
//		}
		
		String [] Add_Budget_Items = req.getParameterValues("Add_Budget_Items");
		String sql = "";
		if(Add_Budget_Items!=null&&!Add_Budget_Items[0].equals("")) {
			
			sql = "('"+Add_Budget_Items[0]+"','"+Add_Budget_Items[1]+"','"+Add_Budget_Items[2]+"','"+Add_Budget_Items[3]+"','"+Add_Budget_Items[4]+"','"+Mapping_Key+"')";
			System.out.println(sql);
			con.Add_Budget_Items(sql);
			
		}
		
		
		req.setAttribute("Mapping_Key",Mapping_Key);
		
		
		con.disconnect();
		req.getRequestDispatcher("/Budget/Add_Budget_Items.jsp").forward(req, resp);
	}

}
