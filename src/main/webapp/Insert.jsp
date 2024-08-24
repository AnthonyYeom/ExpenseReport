<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>



<html>
<style>	
input{
	width:200px;
	height:50px;
	font-size : 12px;

}

.love{
	width:1000px;
	height:50px;
	font-size : 12px;
}
.META{
	height: 50px;
	background: #e9e9e9;
	font-size : 24px;
	font-weight : bold;
	text-align: right;
	padding: 15px;
}
	
</style>

<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body> 
<Form action="Insert" method ="post">		
		<label Class="META">	ID : </label>
		<input type="text" name="ID" >
		
		<br>
		<label Class="META">	CONTENT : </label>
		<textarea name="CONTENT" Class="love">	</textarea>
		
		<br>
		<label Class="META">	HIT : </label>
		<input type="number" name="HIT" >
		
		<br>
		<label Class="META">	FILES : </label>
		
		<input type="text" name="FILES" >
		
		<br>
		<label Class="META">	TITLE: </label>
		<input type="text" name="TITLE" >
		
		<br>
		<label Class="META">	WRITER_ID: </label>
		<input type="text" name="WRITER_ID" >
		<br>
		<input type="submit" name="post" value="입력">
		
</Form>

</body>
</html>