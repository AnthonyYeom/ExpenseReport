<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<style>	

table.type10 thead {
position: sticky; top: 0;
}

.DIF {
color: Blue;
}
table.type10 {
  border-collapse: collapse;
  text-align: center;
  line-height: 2.5;
  border-top: 1px solid #ccc;
  border-bottom: 1px solid #ccc;
  margin: 50px 10px;
  font-size : 12px;
}
table.type10 thead th {
  width: 90px;
  padding: 10px;
  font-weight: bold;
  vertical-align: top;
  color: #fff;
  background: #e7708d;
  margin: 20px 10px;
}
table.type10 tbody th {
  width: 90px;
  padding: 10px;
}
table.type10 td {
  width: 50px;
  padding: 10px;
  vertical-align: center;
  line-hight : 50%;
}

table.type10 .even {
  background: #fdf3f5;
}


</style>

<head>
<meta charset="UTF-8">
<title>Excel Import Page</title>
</head>
<script src="//code.jquery.com/jquery-latest.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.15.5/xlsx.full.min.js"></script>
<script>



function AddData(){
	//localStorage.setItem('date',JSON.stringify(${ary[0][1]}))
	 openWin = window.open("/AddData", "a", "width=400, height=300, left=100, top=50");
	
	 
}

</script>

<body> 
<table class="type10">
  <thead>
  <tr>
    <th>NAME</th>
    <th>날짜</th>
    <th>요일</th>
    <th>시간</th>
    <th>결제항목</th>
    <th>금액</th>
    <th>메모</th>
    <th>카테고리(l1)</th>
    <th>카테고리(l2)</th>
    <th>지불</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach var="n" items="${Detail_List}" varStatus="status">
  
  <c:if test="${status.index%2 == 0}">
  <tr>
<td >${n[0]}</td>
<td> ${n[1]}</td>
<td>${n[2]}</td>
<td>${n[3]}</td>
<td>${n[4]}</td>
<td>${n[5]}</td>
<td>${n[6]}</td>
<td>${n[7]}</td>
<td>${n[8]}</td>
<td>${n[9]}</td>
</tr>
</c:if>

<c:if test="${status.index%2 == 1}">
<tr class="even">	
<td >${n[0]}</td>
<td> ${n[1]}</td>
<td>${n[2]}</td>
<td>${n[3]}</td>
<td>${n[4]}</td>
<td>${n[5]}</td>
<td>${n[6]}</td>
<td>${n[7]}</td>
<td>${n[8]}</td>
<td>${n[9]}</td>
</tr>
</c:if>

</c:forEach>
  </tbody>
  
</table>
</body>
</html>