<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
    <%--pageEncoding UTF-8로 출력을 하고 --%>
      <%--charset UTF-8로 브라우저에서 읽게 한다.--%>
      
<%
String temp_cnt = request.getParameter("cnt");
int cnt = 100;
if ((temp_cnt!=null) && (temp_cnt != "")) {
	
	try {
		cnt = Integer.parseInt(temp_cnt);
	}
	catch (NumberFormatException e)
	{
		
	}
}

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <% for(int i=0; i<cnt;i++){ %> 
 안녕 Servlet!!<br> 
 <%	 }%>
<%-- 10번 반복하는 코드블럭을 만듦 --%>
</body>
</html>