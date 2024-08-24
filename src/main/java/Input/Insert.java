package Input;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Input.DB_connection;

@WebServlet("/Insert")
public class Insert extends HttpServlet{

	String ID = null;
	String CONTENT = null;
	String HIT = null;
	String FILES = null;
	String TITLE = null;
	String WRITER_ID = null;
	String REGDATE = null;
	
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	// TODO Auto-generated method stub
	
	ID = req.getParameter("ID");
	CONTENT = req.getParameter("CONTENT");
	HIT = req.getParameter("HIT");
	FILES = req.getParameter("FILES");
	TITLE = req.getParameter("TITLE");
	WRITER_ID = req.getParameter("WRITER_ID");
	REGDATE = req.getParameter("REGDATE");
	
	
	DB_connection con = new DB_connection();
	con.connect();
	con.exceute_sql();
	if(ID!=""&&CONTENT!=""&&HIT!=""&&FILES!=""&&TITLE!=""&&WRITER_ID!=""&&REGDATE!="") {
	con.writter(ID, CONTENT, HIT, FILES, TITLE, WRITER_ID,REGDATE);
	}
	
	con.disconnect();
	
	resp.sendRedirect("/Insert.jsp");
	
}
}
