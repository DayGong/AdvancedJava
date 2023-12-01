<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" 
	src="<%= request.getContextPath()%>/js/jquery-3.7.1.min.js"></script>
	
<script type="text/javascript">
$(function() {
	$("#strBtn").on("click", function() {
		$.ajax({
			url : "<%= request.getContextPath() %>/json/jsonTest.do", // 요청을 처리할 문서명
			type : "post", 					// 전송방식(get 또는 post)
			data : "choice=string",			// 서버로 전송할 데이터(없으면 생략 가능)
			
			success : function(data) {
				let htmlCode = "<h3>문자열 응답 결과</h3>"
				htmlCode += data;
				
				$('#result').html(htmlCode);
			},
			error : function(xhr) {
				alert("오류: " + xhr.status);
			},
			dataType : "json"	// 응답 데이터의 Type 지정(text, xml, html, json...)
		})		
	});
	
	// 배열 처리
	$('#arrayBtn').on("click", function(){
		$.ajax({
			url : "<%= request.getContextPath() %>/json/jsonTest.do", // 요청을 처리할 문서명
			type : "post", 					// 전송방식(get 또는 post)
			data : "choice=array",			// 서버로 전송할 데이터(없으면 생략 가능)
			
			success : function(data) {
				// data = [10,20,30,40,50]
				let htmlCode = "<h3>문자열 응답 결과</h3>"
				$.each(data, function(i, v) {
					htmlCode += i + "번째 값: " + v + "<br>";
				});
				
				$("#result").html(htmlCode);
			},
			error : function(xhr) {
				alert("오류: " + xhr.status);
			},
			dataType : "json"	// 응답 데이터의 Type 지정(text, xml, html, json...)
		})		
	});
	
	// 객체 처리
	$("#objBtn").on("click", function() {
		$.ajax({
			url : "<%= request.getContextPath() %>/json/jsonTest.do", // 요청을 처리할 문서명
			type : "post", 					// 전송방식(get 또는 post)
			data : "choice=object",			// 서버로 전송할 데이터(없으면 생략 가능)
			
			success : function(data) {
				let htmlCode = "<h3>객체 응답 결과</h3>"
				htmlCode += "번호: " + data.num + "<br>";
				htmlCode += "이름: " + data.name + "<br>";
				htmlCode += "나이: " + data.age + "<br>";
				
				$("#result").html(htmlCode);
			},
			error : function(xhr) {
				alert("오류: " + xhr.status);
			},
			dataType : "json"	// 응답 데이터의 Type 지정(text, xml, html, json...)
		})		
	});
	
	// 리스트 처리
	$('#listBtn').on('click', function(){
		$.ajax({
			url : "<%= request.getContextPath() %>/json/jsonTest.do", // 요청을 처리할 문서명
			type : "post", 					// 전송방식(get 또는 post)
			data : "choice=list",			// 서버로 전송할 데이터(없으면 생략 가능)
			
			success : function(data) {
				// data = [{"num":100,"name":"이순신","age":40},{"num":200,"name":"강감찬","age":30},{"num":300,"name":"일지매","age":20},{"num":400,"name":"성춘향","age":10}]
				let htmlCode = "<h3>List객체 응답 결과</h3>"
				
				$.each(data, function(i, v) {
					htmlCode += i + "번째 자료<br>";
					htmlCode += "번호: " + v.num + "<br>";
					htmlCode += "이름: " + v.name + "<br>";
					htmlCode += "나이: " + v.age + "<hr>";
				});
				
				$("#result").html(htmlCode);
			},
			error : function(xhr) {
				alert("오류: " + xhr.status);
			},
			dataType : "json"	// 응답 데이터의 Type 지정(text, xml, html, json...)
		})		
	});
	
	// Map객체 처리
	$('#mapBtn').on('click', function(){
		$.ajax({
			url : "<%= request.getContextPath() %>/json/jsonTest.do", // 요청을 처리할 문서명
			type : "post", 					// 전송방식(get 또는 post)
			data : "choice=map",			// 서버로 전송할 데이터(없으면 생략 가능)
			
			success : function(data) {
				// data ==> {"name":"이순신","tel":"010-1234-5678","addr":"대전시 중구 오류동"}
				let htmlCode = "<h3>Map객체 응답 결과</h3>"
				htmlCode += "이름: " + data.name + "<br>";
				htmlCode += "전화: " + data.tel + "<br>";
				htmlCode += "주소: " + data.addr + "<br>";
				$("#result").html(htmlCode);
			},
			error : function(xhr) {
				alert("오류: " + xhr.status);
			},
			dataType : "json"	// 응답 데이터의 Type 지정(text, xml, html, json...)
		})
	});
});
</script>
</head>
<body>
<form>
	<input type="button" id="strBtn" value="문자열">
	<input type="button" id="arrayBtn" value="배열">
	<input type="button" id="objBtn" value="객체">
	<input type="button" id="listBtn" value="List객체">
	<input type="button" id="mapBtn" value="Map객체">
</form>
<hr>
<!-- <h3>JSON 응답 데이터 내용</h3> -->
<div id="result"></div>
</body>
</html>