package Input;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Input.DB_connection;
@WebServlet("/Budget_Commit")
public class Budget_Commit extends HttpServlet 
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		DB_connection con = new DB_connection();
		con.connect();
		req.setCharacterEncoding("UTF-8");
		String[] Commited_Budget_Array = req.getParameterValues("Commited_Budget_Array");
		String Commited_Budget_Year = req.getParameter("Commited_Budget_Year");
		
		LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
		String Format_now = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		Format_now=Format_now.replace("-","");
		Format_now=Format_now.replace(" ","");
		Format_now=Format_now.replace(":","");
		
		String Mapping_Key = Format_now;
		
		String data = "";
		int n = 0;
		String insert = "";
		
	for (int i=5; i<Commited_Budget_Array.length;i+=6) {
		System.out.println(Commited_Budget_Array[i]);
		if(Commited_Budget_Array[i].equals("none")) {
		for(int j=0; j<5;j++) {
			if(insert=="") {
				insert= "('"+Commited_Budget_Array[i-5+j];	
			}
			else
			{
				insert = insert + "','" +Commited_Budget_Array[i-5+j]; 
			}
			
			}
		insert = insert+"','"+Mapping_Key+"')";
		
		if(data!="") {
			data = data +","+ insert;
		}
		else {
			data = insert;
		}
		
		}
		insert="";
		
		//con.Insert_New_Budget(insert, Commited_Budget_Year);
	
		System.out.println(data);
	
		}
	
		con.Insert_New_Budget(data,Mapping_Key,Commited_Budget_Year);
	
	
		
		con.disconnect();
		resp.sendRedirect("/Budget_Management");
	}
}


