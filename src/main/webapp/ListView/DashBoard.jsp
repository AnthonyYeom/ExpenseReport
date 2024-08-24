<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<style>	

table.type10 thead {
position: sticky; top: 0;
}

table.type10 {
  border-collapse: collapse;
  text-align: center;
  line-height: 2.5;
  border-top: 1px solid #ccc;
  border-bottom: 1px solid #ccc;
  margin: 50px 10px;
}

table.type10 thead {
position: sticky; top: 0;
}

.other_column{
  width: 80px;
  font-weight: bold;
  vertical-align: top;
  color: #fff;
  background: #e7708d;
  display: sticky;
}
.other_column_v2{
  width: 80px;
  font-weight: bold;
  vertical-align: top;
  color: #fff;
  background: #003458;
  display: sticky;
}
.c1 {
  width: 200px;
  padding: 1px;
  font-weight: bold;
  vertical-align: top;
  color: #fff;
  background: #e7708d;
  margin: 1px 1px;
}

.c1_v2 {
  width: 200px;
  padding: 1px;
  font-weight: bold;
  vertical-align: top;
  color: #fff;
  background: #003458;
  margin: 1px 1px;
}

.gap{
	padding-left:0.5px;
	color: #A8A8A8;
	font-size: 10px;
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



function ViewDetail(a,b){
	//localStorage.setItem('date',JSON.stringify(${ary[0][1]}))
	console.log(a)
	console.log(b)
	var x = document.cookie.split("; ");
	openWin = window.open("/SelectDetail?row="+a+"&month="+b+"&"+x[0]+"&"+x[1], "a", "width=1300, height=500, left=100, top=50");
}

function ViewDetail_2(a,b){
	//localStorage.setItem('date',JSON.stringify(${ary[0][1]}))
	console.log(a)
	console.log(b)
	var x = document.cookie.split("; ");
	openWin = window.open("/SelectDetail_2?row="+a+"&month="+b+"&"+x[0]+"&"+x[1], "a", "width=1300, height=500, left=100, top=50");
}


</script>

<body> 





<br>

<form action="/DashBoard" method="get">	
<select name="catergory">
<option value="none">관리 구분을 선택하세요</option>
<option value="month">월별 관리 항목
</option>
<option value="year">연간 관리 항목
</option>
</select>

<select name="SelectedYear">
<option value="none">년도를 선택하세요</option>
<c:forEach var="i" items="${YearList}" varStatus="status">
      <option >${i}</option>
</c:forEach>
</select>
<input type="submit" value="조회하기">
</form>

<c:if test="${not empty cat_type}">
<form action="/DashBoard" method="get">
<select name="RollbackYear">
<option value="none">RollBack년도</option>
<c:forEach var="i" items="${YearMonList}" varStatus="status">
      <option >${i}</option>
</c:forEach>
</select>
<input type="submit" value="Rollback">
</form>
</c:if>
<!--input으로 값은 보내야 하는데 html 화면에선 숨겨야 함-->
 
 
 <table class="type10">
  <thead>
  <tr>
    <th class="c1_v2">구분</th>
    <th class="other_column_v2"></th>
    <th class="other_column_v2">1M</th>
    <th class="other_column_v2">2M</th>
    <th class="other_column_v2">3M</th>
    <th class="other_column_v2">4M</th>
    <th class="other_column_v2">5M</th>
    <th class="other_column_v2">6M</th>
    <th class="other_column_v2">7M</th>
    <th class="other_column_v2">8M</th>
    <th class="other_column_v2">9M</th>
    <th class="other_column_v2">10M</th>
    <th class="other_column_v2">11M</th>
    <th class="other_column_v2">12M</th>
    <th class="other_column_v2">연평균</th>
    <th class="other_column_v2">연누계</th>

  </tr>
  </thead>
 <tbody>
<c:forEach var="n" items="${Income_Expense_List}" varStatus="status">
<c:if test="${n[0] eq '고정'||n[0] eq '변동'||n[0] eq '고정'||n[0] eq '특별'}">
<tr class='even'>
<td>${n[0]} </td>
<td>-</td>
<td><A onclick="ViewDetail_2('${n[0]}','1')">${n[1]}</A></td>
<td><A onclick="ViewDetail_2('${n[0]}','2')">${n[2]}</A></td>
<td><A onclick="ViewDetail_2('${n[0]}','3')">${n[3]}</A></td>
<td><A onclick="ViewDetail_2('${n[0]}','4')">${n[4]}</A></td>
<td><A onclick="ViewDetail_2('${n[0]}','5')">${n[5]}</A></td>
<td><A onclick="ViewDetail_2('${n[0]}','6')">${n[6]}</A></td>
<td><A onclick="ViewDetail_2('${n[0]}','7')">${n[7]}</A></td>
<td><A onclick="ViewDetail_2('${n[0]}','8')">${n[8]}</A></td>
<td><A onclick="ViewDetail_2('${n[0]}','9')">${n[9]}</A></td>
<td><A onclick="ViewDetail_2('${n[0]}','10')">${n[10]}</A></td>
<td><A onclick="ViewDetail_2('${n[0]}','11')">${n[11]}</A></td>
<td><A onclick="ViewDetail_2('${n[0]}','12')">${n[12]}</A></td>
<td>${n[13]}</td>
<td>${n[14]}</td>
</tr>
</c:if>	
<c:if test="${n[0] eq '소득'||n[0] eq '합계'}">
<tr>
<td>${n[0]} </td>
<td>-</td>

<td><A onclick="ViewDetail_2('${n[0]}','1')">${n[1]}</A></td>
<td><A onclick="ViewDetail_2('${n[0]}','2')">${n[2]}</A></td>
<td><A onclick="ViewDetail_2('${n[0]}','3')">${n[3]}</A></td>
<td><A onclick="ViewDetail_2('${n[0]}','4')">${n[4]}</A></td>
<td><A onclick="ViewDetail_2('${n[0]}','5')">${n[5]}</A></td>
<td><A onclick="ViewDetail_2('${n[0]}','6')">${n[6]}</A></td>
<td><A onclick="ViewDetail_2('${n[0]}','7')">${n[7]}</A></td>
<td><A onclick="ViewDetail_2('${n[0]}','8')">${n[8]}</A></td>
<td><A onclick="ViewDetail_2('${n[0]}','9')">${n[9]}</A></td>
<td><A onclick="ViewDetail_2('${n[0]}','10')">${n[10]}</A></td>
<td><A onclick="ViewDetail_2('${n[0]}','11')">${n[11]}</A></td>
<td><A onclick="ViewDetail_2('${n[0]}','12')">${n[12]}</A></td>
<td>${n[13]}</td>
<td>${n[14]}</td>

</tr>
</c:if>	


  </c:forEach>
  </tbody>
 </table>
 
 
<c:if test="${cat_type eq 'month'}">
<table class="type10">
  <thead>
  <tr class="r1">
    <th class="c1">지출항목</th>
    <th class="other_column">예산</th>
    <th class="other_column">1M</th>
    <th class="other_column">2M</th>
    <th class="other_column">3M</th>
    <th class="other_column">4M</th>
    <th class="other_column">5M</th>
    <th class="other_column">6M</th>
    <th class="other_column">7M</th>
    <th class="other_column">8M</th>
    <th class="other_column">9M</th>
    <th class="other_column">10M</th>
    <th class="other_column">11M</th>
    <th class="other_column">12M</th>
    <th class="other_column">연평균</th>
    <th class="other_column">연누계</th>

  </tr>
  </thead>
  <tbody>
  <c:forEach var="n" items="${Items}" varStatus="status">
  <c:if test="${status.index%2 == 0}">
  <tr>

<td>${n[1]} </td>
<td>${n[2]}</td>
<td> <A onclick="ViewDetail('${n[1]}','1')">${n[3]}</A> <A class="gap"> ${n[4]} </A></td>
<td> <A onclick="ViewDetail('${n[1]}','2')">${n[5]}</A><A class="gap"> ${n[6]} </A></td>
<td> <A onclick="ViewDetail('${n[1]}','3')">${n[7]}</A><A class="gap"> ${n[8]} </A></td>
<td> <A onclick="ViewDetail('${n[1]}','4')">${n[9]}</A><A class="gap"> ${n[10]} </A></td>
<td> <A onclick="ViewDetail('${n[1]}','5')">${n[11]}</A><A class="gap"> ${n[12]} </A></td>
<td> <A onclick="ViewDetail('${n[1]}','6')">${n[13]}</A><A class="gap"> ${n[14]} </A></td>
<td> <A onclick="ViewDetail('${n[1]}','7')">${n[15]}</A><A class="gap"> ${n[16]} </A></td>
<td> <A onclick="ViewDetail('${n[1]}','8')">${n[17]}</A><A class="gap"> ${n[18]} </A></td>
<td> <A onclick="ViewDetail('${n[1]}','9')">${n[19]}</A><A class="gap"> ${n[20]} </A></td>
<td> <A onclick="ViewDetail('${n[1]}','10')">${n[21]}</A><A class="gap"> ${n[22]} </A></td>
<td> <A onclick="ViewDetail('${n[1]}','11')">${n[23]}</A><A class="gap"> ${n[24]} </A></td>
<td> <A onclick="ViewDetail('${n[1]}','12')">${n[25]}</A><A class="gap"> ${n[26]} </A></td>
<td> ${n[27]}</td>
<td> ${n[28]}</td>


</tr>
</c:if>
<c:if test="${status.index%2 == 1}">
<tr class="even">	
<td>${n[1]} </td>
<td>${n[2]}</td>
<td> <A onclick="ViewDetail('${n[1]}','1')">${n[3]}</A> <A class="gap"> ${n[4]} </A></td>
<td> <A onclick="ViewDetail('${n[1]}','2')">${n[5]}</A><A class="gap"> ${n[6]} </A></td>
<td> <A onclick="ViewDetail('${n[1]}','3')">${n[7]}</A><A class="gap"> ${n[8]} </A></td>
<td> <A onclick="ViewDetail('${n[1]}','4')">${n[9]}</A><A class="gap"> ${n[10]} </A></td>
<td> <A onclick="ViewDetail('${n[1]}','5')">${n[11]}</A><A class="gap"> ${n[12]} </A></td>
<td> <A onclick="ViewDetail('${n[1]}','6')">${n[13]}</A><A class="gap"> ${n[14]} </A></td>
<td> <A onclick="ViewDetail('${n[1]}','7')">${n[15]}</A><A class="gap"> ${n[16]} </A></td>
<td> <A onclick="ViewDetail('${n[1]}','8')">${n[17]}</A><A class="gap"> ${n[18]} </A></td>
<td> <A onclick="ViewDetail('${n[1]}','9')">${n[19]}</A><A class="gap"> ${n[20]} </A></td>
<td> <A onclick="ViewDetail('${n[1]}','10')">${n[21]}</A><A class="gap"> ${n[22]} </A></td>
<td> <A onclick="ViewDetail('${n[1]}','11')">${n[23]}</A><A class="gap"> ${n[24]} </A></td>
<td> <A onclick="ViewDetail('${n[1]}','12')">${n[25]}</A><A class="gap"> ${n[26]} </A></td>
<td> ${n[27]}</td>
<td> ${n[28]}</td>
</tr>
</c:if>

</c:forEach>
  </tbody>
</table>

</c:if>


<c:if test="${cat_type eq 'year'}">
<table class="type10">
  <thead>
  <tr class="r1">
    <th class="c1">지출항목</th>
    <th class="other_column">연간 예산</th>
    <th class="other_column">1M</th>
    <th class="other_column">2M</th>
    <th class="other_column">3M</th>
    <th class="other_column">4M</th>
    <th class="other_column">5M</th>
    <th class="other_column">6M</th>
    <th class="other_column">7M</th>
    <th class="other_column">8M</th>
    <th class="other_column">9M</th>
    <th class="other_column">10M</th>
    <th class="other_column">11M</th>
    <th class="other_column">12M</th>
    <th class="other_column">연평균</th>
    <th class="other_column">연누계</th>

  </tr>
  </thead>
  <tbody>
  <c:forEach var="n" items="${Items}" varStatus="status">
  <c:if test="${status.index%2 == 0}">
  <tr>

<td>${n[1]} </td>
<td>${n[2]}</td>
<td><A onclick="ViewDetail('${n[1]}','1')">${n[3]}</A></td>
<td><A onclick="ViewDetail('${n[1]}','2')">${n[4]}</A></td>
<td><A onclick="ViewDetail('${n[1]}','3')">${n[5]}</A></td>
<td><A onclick="ViewDetail('${n[1]}','4')">${n[6]}</A></td>
<td><A onclick="ViewDetail('${n[1]}','5')">${n[7]}</A></td>
<td><A onclick="ViewDetail('${n[1]}','6')">${n[8]}</A></td>
<td><A onclick="ViewDetail('${n[1]}','7')">${n[9]}</A></td>
<td><A onclick="ViewDetail('${n[1]}','8')">${n[10]}</A></td>
<td><A onclick="ViewDetail('${n[1]}','9')">${n[11]}</A></td>
<td><A onclick="ViewDetail('${n[1]}','10')">${n[12]}</A></td>
<td><A onclick="ViewDetail('${n[1]}','11')">${n[13]}</A></td>
<td><A onclick="ViewDetail('${n[1]}','12')">${n[14]}</A></td>

<td>${n[15]}</td>
<td>${n[16]}</td>




</tr>
</c:if>
<c:if test="${status.index%2 == 1}">
<tr class="even">	
<td>${n[1]} </td>
<td>${n[2]}</td>
<td><A onclick="ViewDetail('${n[1]}','1')">${n[3]}</A></td>
<td><A onclick="ViewDetail('${n[1]}','2')">${n[4]}</A></td>
<td><A onclick="ViewDetail('${n[1]}','3')">${n[5]}</A></td>
<td><A onclick="ViewDetail('${n[1]}','4')">${n[6]}</A></td>
<td><A onclick="ViewDetail('${n[1]}','5')">${n[7]}</A></td>
<td><A onclick="ViewDetail('${n[1]}','6')">${n[8]}</A></td>
<td><A onclick="ViewDetail('${n[1]}','7')">${n[9]}</A></td>
<td><A onclick="ViewDetail('${n[1]}','8')">${n[10]}</A></td>
<td><A onclick="ViewDetail('${n[1]}','9')">${n[11]}</A></td>
<td><A onclick="ViewDetail('${n[1]}','10')">${n[12]}</A></td>
<td><A onclick="ViewDetail('${n[1]}','11')">${n[13]}</A></td>
<td><A onclick="ViewDetail('${n[1]}','12')">${n[14]}</A></td>
<td>${n[15]}</td>
<td>${n[16]}</td>
</tr>
</c:if>

</c:forEach>
  </tbody>
</table>

</c:if>






</body>
</html>