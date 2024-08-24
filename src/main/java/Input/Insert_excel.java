package Input;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.json.JSONParser;

import Input.DB_connection;

@WebServlet("/Input/Insert_excel")
public class Insert_excel extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		
		
		String[] testdata = req.getParameterValues("testdata1");
		if(testdata!=null) {
			String[] temp_data = null;
			String[] temp_date_day= null;
			
		String name = req.getParameter("name");
		
		DB_connection con = new DB_connection();
		con.connect();
		
		LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
		String Format_now = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

		for(int i=0 ; i<testdata.length; i++) {
			temp_data = testdata[i].split(",");
			temp_date_day = temp_data[0].split(" ");
			con.Excel_Data_Insert("'"+name+"'", "'"+Format_now+"'","'"+i+"'", "'"+temp_date_day[0]+"'", "'"+temp_date_day[1]+"'","'"+ temp_data[1]+":00'", "'"+temp_data[2]+"'","'"+temp_data[3]+"'", "'"+temp_data[4]+"'", "'"+temp_data[5]+"'", "'"+temp_data[6]+"'", "'"+temp_data[7]+"'");
			temp_data = null;
			temp_date_day = null;	
			};
			con.disconnect();
			};
	
	
	
	
	
	}
	
	
	}
