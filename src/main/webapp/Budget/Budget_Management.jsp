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
  width: 200px;
  font-weight: bold;
  vertical-align: top;
  color: #fff;
  background: #e7708d;
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
	var x = document.cookie.split(";");
	openWin = window.open("/SelectDetail?row="+a+"&month="+b+"&"+x[0], "a", "width=1300, height=500, left=100, top=50");
}


function Add_Budget_Data(){
	//localStorage.setItem('date',JSON.stringify(${ary[0][1]}))
	var x = document.cookie.split("; ");
	 openWin = window.open("/Add_Budget_Items?"+x[0]+"&"+x[1], "a", "width=400, height=300, left=100, top=50");
}


</script>

<body> 

<form action="/Budget_Management" method="get">	
<select name="Budget_Year">
<option value="none">년도를 선택하세요</option>
<c:forEach var="i" items="${Budget_Year_List}" varStatus="status">
      <option >${i}</option>
</c:forEach>
</select>
<input type="submit" value="불러오기">
</form>


<input type="button" value="데이터 추가하기" onclick="Add_Budget_Data()">


<form action="/Budget_Commit" method="post">
<select name="Commited_Budget_Year">
<option value="none">년도를 선택하세요</option>
<c:forEach var="i" items="${Budget_Year_List}" varStatus="status">
      <option >${i}</option>
</c:forEach>
</select>
<input type="submit" value="복사하기">

<table class="type10">
  <thead>
  <tr class="r1">
   <th class="c1">L1</th>
   <th class="other_column">L2</th>
   <th class="other_column">CAT_L1</th>
   <th class="other_column">CAT_L2</th>

   <th class="other_column">BUD</th>
   <th class="other_column">TO_BE</th>
   <th class="other_column">DISABLE?</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach var="n" items="${Budget_Detail_List}" varStatus="status">
  <c:if test="${status.index%2 == 0}">
  <tr>
  <td>${n[0]} <input type="hidden" name="Commited_Budget_Array" value="${n[0]}"> </td>
<td>${n[1]} <input type="hidden" name="Commited_Budget_Array" value="${n[1]}"></td>
<td>${n[2]} <input type="hidden" name="Commited_Budget_Array" value="${n[2]}"></td>
<td>${n[3]} <input type="hidden" name="Commited_Budget_Array" value="${n[3]}"></td>
<td>${n[4]}</td>
<td><input type="number" name="Commited_Budget_Array" value="${n[4]}"></td>
<td>
<select name="Commited_Budget_Array" name="Usage">
<option value="none">-</option>
<option value="delete">미사용</option>
</select> </td>

</tr>

</c:if>

<c:if test="${status.index%2 == 1}">
<tr class="even">	
<td>${n[0]} <input type="hidden" name="Commited_Budget_Array" value="${n[0]}"></td>
<td>${n[1]} <input type="hidden" name="Commited_Budget_Array" value="${n[1]}"></td>
<td>${n[2]} <input type="hidden" name="Commited_Budget_Array" value="${n[2]}"></td>
<td>${n[3]} <input type="hidden" name="Commited_Budget_Array" value="${n[3]}"></td>
<td>${n[4]}</td>
<td><input type="number" name="Commited_Budget_Array" value="${n[4]}"></td>
<td>
<select name="Commited_Budget_Array" name="Usage">
<option value="none">-</option>
<option value="delete">미사용</option>
</select> </td>
</tr>
</c:if>

</c:forEach>
  </tbody>
</table>
</form>
</body>
</html>