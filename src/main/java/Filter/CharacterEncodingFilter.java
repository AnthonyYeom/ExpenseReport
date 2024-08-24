package Filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;

//@WebServlet("/*")
public class CharacterEncodingFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
	
	resp.setCharacterEncoding("UTF-8"); //서버에서 보낼 때 utf 8로 보내기
	resp.setContentType("text/html; charset=UTF-8"); //브라우저에서 html파일, utf 8 방식으로 읽어들이기 
	chain.doFilter(req, resp); //요청 흐름을 받아서 넘기는것 
	System.out.println("filter");
	}

}
