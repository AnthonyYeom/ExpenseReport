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
<Form action="/Add_Budget_Items" method ="get" accept-charset="utf-8">
		
		
		<input type=hidden value="${Mapping_Key}" name="Mapping_Key">
		<br>
		<label >L1</label>
		<input type=text name="Add_Budget_Items">
		<br>
		<label >L2</label>
		<input type=text name="Add_Budget_Items">
		<br>
		<label >CAT_L1</label>
		<input type=text name="Add_Budget_Items">
		<br>
		<label >CAT_L2</label>
		<input type=text name="Add_Budget_Items">
		<br>
		<label >BUD</label>
		<input type=text name="Add_Budget_Items">
		<input type="submit" value="등록">
</Form>
	<input type="button" value="창닫기"onclick="end()">
</body>
</html>