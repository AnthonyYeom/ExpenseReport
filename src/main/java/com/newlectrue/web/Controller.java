package com.newlectrue.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.security.auth.message.callback.SecretKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/notice/list")
public class Controller extends HttpServlet{
	
	public String userId = null;
	public String password = null;
	public String name = null;
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
			
			String driver = "org.mariadb.jdbc.Driver";
			String DB_IP = "localhost";
			String DB_PORT = "3306";
			String DB_NAME = "contents";
			String DB_URL = 
					"jdbc:mariadb://" + DB_IP + ":" + DB_PORT + "/" + DB_NAME;

			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				Class.forName(driver);
				conn = DriverManager.getConnection(DB_URL, "root", "xkrwo1202!");
				if (conn != null) {
					System.out.println("DB 접속 성공");
				}

			} catch (ClassNotFoundException e) {
				System.out.println("드라이버 로드 실패");
				e.printStackTrace();
			} catch (SQLException e) {
				System.out.println("DB 접속 실패");
				e.printStackTrace();
			}

			try {
				String sql = "select * from notice";

				pstmt = conn.prepareStatement(sql);

				rs = pstmt.executeQuery();
				
				rs.next();
				String ID = rs.getString("ID");
				String title = rs.getString("TITLE");
				String writerId = rs.getString("WRITER_ID");
				Date regdate = rs.getDate("REGDATE");
				String hit = rs.getString("HIT");
				
				req.setAttribute("ID", ID);
				req.setAttribute("title", title);
				req.setAttribute("writerId", writerId);
				req.setAttribute("regdate", regdate);
				req.setAttribute("hit", hit);
				
		

			} catch (SQLException e) {
				System.out.println("error: " + e);
			} finally {
				try {
					if (rs != null) {
						rs.close();
					}
					if (pstmt != null) {
						pstmt.close();
					}

					if (conn != null && !conn.isClosed()) {
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
	
			req.getRequestDispatcher("/notice/list.jsp").forward(req, resp);
		}
		
		
	}

	
	
