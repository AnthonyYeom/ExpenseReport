package Input;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Input.DB_connection;
@WebServlet("/Input/popup_add")
public class popup_add extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String[] Add_Data = req.getParameterValues("pop_up");
		int ID_NUM = 0;
		if(Add_Data[0]!=""&&Add_Data[0]!=null) {
		System.out.println(Add_Data.length);
		String regdate = Add_Data[0]; String regname = Add_Data[1];
		String date = Add_Data[2]; String day = null;
		String time = Add_Data[3]; String spd_desc = Add_Data[4]; String money = Add_Data[5];
		String memo = Add_Data[6]; String cat_l1 = "-"; String cat_l2 = Add_Data[7];
		String payment = Add_Data[8]; 
		
//		date에대해 요일로 변환
		String[] Date_Array = date.split("-");
		LocalDate New_date= LocalDate.of(Integer.parseInt(Date_Array[0]), Integer.parseInt(Date_Array[1]), Integer.parseInt(Date_Array[2]));
		DayOfWeek dayOfWeek = New_date.getDayOfWeek();
		int weeknum = dayOfWeek.getValue();
		System.out.println(weeknum);
		switch (weeknum) { 
		case 1: day="(월)"; break;
		case 2 : day="(화)"; break;
		case 3 : day="(수)";break;
		case 4 : day="(목)";break;
		case 5 : day="(금)";break;
		case 6 : day="(토)";break;
		case 7 : day="(일)";break;
		}
		
		DB_connection con = new DB_connection();
		con.connect();
		
		try {
			ID_NUM = con.len_selected_data(regdate);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		con.Excel_Data_Insert("'"+regname+"'", "'"+regdate+"'", "'"+ID_NUM+"'","'"+date+"'", "'"+day+"'","'"+ time+":00'", "'"+spd_desc+"'","'"+money+"'"
				,"'"+memo+"'", "'"+cat_l1+"'", "'"+cat_l2+"'", "'"+payment+"'");
		con.disconnect();
		resp.sendRedirect("/AddData");
		}
		
	}

}
