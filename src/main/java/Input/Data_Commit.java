package Input;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Input.DB_connection;

@WebServlet("/Input/Data_Commit")
public class Data_Commit extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		DB_connection con = new DB_connection();
		con.connect();
		
		
		req.setCharacterEncoding("UTF-8");
		String[] Modified_Data = req.getParameterValues("modified_data");
		
		String regdate = req.getParameter("regdate");
	
		
		
		for (int i=1; i<Modified_Data.length;i+=2 ) {
			
			System.out.println(Modified_Data[i]);
			
			if(!Modified_Data[i].equals("none")) {
				con.Change_Values("'"+regdate+"'","'"+Modified_Data[i-1]+"'","'"+Modified_Data[i]+"'");
				
			}
		}

		String Commit_Year_Mon = req.getParameter("Commit_Year_Mon");
		String REG_NAME = req.getParameter("REG_NAME");
		System.out.println(Commit_Year_Mon);
		String[] year_mon = Commit_Year_Mon.split("-");
		con.Insert_Year_Mon(year_mon[0],year_mon[1],regdate,REG_NAME);
		
		
//		if(regdate!=""&&regdate!=null) {
//			String [][] ary = con.List_View(regdate);
//			String [][] MAS_ARY = con.MAS_LIST();
//			req.setAttribute("ary",ary);
//			req.setAttribute("MAS_ARY",MAS_ARY);
//			}
		
		
		
		con.disconnect();
		
		
		resp.sendRedirect("/ListView");
	}

}
