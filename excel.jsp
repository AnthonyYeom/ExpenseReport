<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>



<html>
<style>	

.desc{
	width:60px;
	height:30px;
	font-size : 75px;
   

}
.name_meta{
	width:60px;
	height:30px;
	font-size : 100px;
    padding : 30px;

}
.name{
	width:300px;
	height:80px;
	font-size : 100px;
 text-align: center;
 padding : 30px;
}

.file{
	width:1500px;
	height:150px;
	font-size : 100px;
 text-align: center;
  padding : 30px;
}
.post{
	width:1500px;
	height:350px;
	font-size : 150px;
 text-align: center;
  padding : 30px;
}
.group{
}
</style>

<head>
<meta charset="UTF-8">
<title>Excel Import Page</title>
</head>
<script src="//code.jquery.com/jquery-latest.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.15.5/xlsx.full.min.js"></script>
<script>

var testdata1=[];

function readExcel() {
	var dataset=[];

    let input = event.target;
    let reader = new FileReader();
    reader.onload = function () {
        let data = reader.result;
        let workBook = XLSX.read(data, { type: 'binary' });
        workBook.SheetNames.forEach(function (sheetName) {
            
            let rows = XLSX.utils.sheet_to_json(workBook.Sheets[sheetName]);	
            dataset.push(rows);
        })
        console.log(dataset[0]);
        for (i = 2; i< dataset[0].length; i++) {
        	
        	let dt = Object.values(dataset[0][i]);
        	const tempdata=[];
        	tempdata.push(dt[0].replace(/\./g,'-'));
        	tempdata.push(dt[1].replace(/,/g,''));
        	tempdata.push(dt[2].replace(/,/g,''));
        	tempdata.push(dt[3]);
        	tempdata.push(dt[4].replace(/,/g,''));
        	tempdata.push(dt[5].replace(/,/g,''));
        	tempdata.push(dt[6].replace(/,/g,''));
        	tempdata.push(dt[7].replace(/,/g,''));
        	testdata1.push(tempdata);
        }
        
        
        console.log(testdata1);
        name = document.getElementById("name");
        
   
      
    };
    
    reader.readAsBinaryString(input.files[0]);
}

function sendExcel() {
	
	var name = document.getElementById("name").value;
	console.log(name);
	
	if(name!=""){
$.ajax({
	type:'post',
	url:'/Input/Insert_excel',
	traditional: true,
	
	data : { 	
		name,
		'testdata1':testdata1
	},
	success : function(result) { // 결과 성공 콜백함수        
		console.log("성공")
		window.location.replace("http://210.179.155.131:3389/excel.jsp")
		alert( '아주 잘 했어요' );
		},    
	error : function(request, status, error) { // 결과 에러 콜백함수
	console.log("실패")
	alert( '파일을 첨부해주세요' );}
   });
	
	}
	else {
		alert( '이름을 입력해주세요' )	   
		}
	
}

</script>
<body> 

		<label Class="desc">부엉이네 가계부 Data Import Page 입니다. </label>
		<br>
		<label Class="desc">이름을 입력하시고 가계부 엑셀 파일을 첨부하신 후 서버로 전송하기 버튼을 눌러주세요</label>
		<br><br><br><br><br>
<div Class="group">
		<label Class="name_meta">이름 : </label>
		<input type="text" name="" id="name" class="name">
		<br><br><br>
		<input type="file" name="excel" onchange="readExcel()" class="file">
		
		<br><br><br>
		<button type="button"  onclick="sendExcel()" class="post">서버로 전송하기</button>
		
</div>
</body>
</html>