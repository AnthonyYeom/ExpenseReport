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
	var x = document.cookie.split("; ");
	 openWin = window.open("/AddData?"+x[0], "a", "width=400, height=300, left=100, top=50");
	
	 
}

</script>

<body> 
<form  action="ListView" method ="get" name="DateList">
<select name="date">
<option value="none">업로드 시점 선택</option>
<c:forEach var="n" items="${DATE_ary}">
  <option>
  <fmt:parseDate value="${n}" var="time" pattern="yyyy-MM-dd HH:mm:ss.S"/>
  <fmt:formatDate value="${time}" pattern="yyyy-MM-dd HH:mm:ss"/> <!-- 소수점인 datetime을 포맷 태그로 조정-->
  </option> 
</c:forEach>
</select>


<select name="year">
<option value="none">년도 선택</option>
<c:forEach var="n" items="${Master_Record}">
  <option>${n}</option> 
</c:forEach>
</select>


<br>
<input type="submit" name="post" value="데이터 불러오기">
</form>

<input type="button" value="데이터 추가하기" onclick="AddData()">


<form action="/Data_Delete" method="get">	
<input type="hidden" name="date_deleted" value="${ary[0][1]}">
<input type="submit" value="데이터 삭제하기">
</form>


<form  action="/Input/Data_Commit" method ="post">
<input type="hidden" name="regdate" value="${ary[0][1]}">
<input type="hidden" name="REG_NAME" value="${ary[0][11]}">
<!--input으로 값은 보내야 하는데 html 화면에선 숨겨야 함-->

<input type="submit" value="데이터 확정">
<div>
<label> 데이터 업로드 시점:</label>
<label for="regdate">
<fmt:parseDate value="${ary[0][1]}" var="time" pattern="yyyy-MM-dd HH:mm:ss.S"/>
<fmt:formatDate value="${time}" pattern="yyyy-MM-dd HH:mm:ss"/> <!-- 소수점인 datetime을 포맷 태그로 조정-->
</label>
<label for="REG_NAME">${ary[0][11]}</label>

</div>
<input type="month" name="Commit_Year_Mon">
<table class="type10">
  <thead>
  <tr>
    <th>ID</th>
    <th>결제일자</th>
    <th>요일</th>
    <th>시간</th>
    <th>결제항목</th>
    <th>금액</th>
    <th>메모</th>
    <th>카테고리(As-is)</th>
    <th>카테고리(To-be)</th>
    <th>지불수단</th>
     <th>조정여부</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach var="n" items="${ary}" varStatus="status">
  
  <c:if test="${status.index%2 == 0}">
  <tr>
  
<td > ${n[0]} <input type="hidden" name="modified_data" value="${n[0]}"></td>
<td scope="row"> ${n[2]}</td>
<td class="c_2">${n[3]}</td>
<td>${n[4]}</td>
<td>${n[5]}</td>
<td>${n[6]}</td>
<td>${n[7]}</td>
<td>${n[9]}</td>


<td>
<select name="modified_data">
<option value="none">-</option>

<c:forEach var="i" items="${MAS_ARY}" varStatus="status">
      <option >${i[2]}</option>
</c:forEach>


</select>
</td>
<td>${n[10]}</td>
<c:if test="${n[9] eq n[12]}">
<td>●</td>
</c:if>
<c:if test="${n[9] ne n[12]}">
<td>x</td>
</c:if>

</tr>
</c:if>
<c:if test="${status.index%2 == 1}">
<tr class="even">	
<td > ${n[0]} <input type="hidden" name="modified_data" value="${n[0]}"></td>
<td > ${n[2]}</td>
<td class="c_2">${n[3]}</td>
<td>${n[4]}</td>
<td>${n[5]}</td>
<td>${n[6]}</td>
<td>${n[7]}</td>
<td>${n[9]}</td>

<td>
<select name="modified_data">
<option value="none">-</option>

<c:forEach var="i" items="${MAS_ARY}" varStatus="status">
      <option >${i[2]}</option>
</c:forEach>


</select>
</td>
<td>${n[10]}</td>

<c:if test="${n[9] eq n[12]}">
<td>●</td>
</c:if>
<c:if test="${n[9] ne n[12]}">
<td>x</td>
</c:if>


</tr>


</c:if>
</c:forEach>
  </tbody>
  
</table>
</form>
</body>
</html>