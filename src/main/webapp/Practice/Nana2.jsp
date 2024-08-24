<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<%
pageContext.setAttribute("result", "pagecontext");
%>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body> 
	${requestScope.result}
	<br>
	${names[1]}
	<br>
	${notice['1']}
	<br>
	${param.num gt 1} <%-- 요청하는 파라메터 값을 호출 --%>
	<br>
	${empty param.num} <%--널 혹은 빈문자인경우--%>
	<br>
	${header.accept} <%-- 헤더의 어셉트 정보 호출--%>
</body>
</html>