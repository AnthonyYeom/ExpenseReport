<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<style>	
</style>

<head>
<meta charset="UTF-8">
<title>데이터 추가 입력</title>
</head>
<script src="//code.jquery.com/jquery-latest.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.15.5/xlsx.full.min.js"></script>
<script>

function end(){
	opener.location.reload();
    window.close();
}

</script>

<body> 
<Form action="/Input/popup_add" method ="post" accept-charset="utf-8">
		
		<label >등록일</label>
		<script>
		var regdate = opener.$("label[for='regdate']").text()
		document.write('<input name= "pop_up" value="'+regdate+'">');
		</script>
		
		<br>
		<label >등록이름</label>
			<script>
		var REG_NAME = opener.$("label[for='REG_NAME']").text()
		
		document.write('<input name="pop_up" value="'+REG_NAME+'">');
		</script>
		<br>
		<label >날짜</label>
		<input type="date" name="pop_up">
		<br>
	
		<label >시간</label>
		<input type="TIME" name="pop_up">
		<br>
		<label >지출내역</label>
		<input type="TEXT" name="pop_up">
		<br>
		<label >지출금액</label>
		<input type="NUMBER" name="pop_up">
		<br>
		<label >메모</label>
		<input type="CONTENT" name="pop_up">
		<br>

		<label >중분류</label>	
		<select name="pop_up">
		<option value="none">-</option>
		<c:forEach var="i" items="${MAS_ARY}" varStatus="status">
		<option >${i[2]}</option>
		</c:forEach>   
		</select>
		<br>
		<label >결제방식</label>
		<input type="CONTENT" name="pop_up">
		<br>
		<input type="submit" value="등록">
</Form>
	<input type="button" value="창닫기"onclick="end()">
</body>
</html>