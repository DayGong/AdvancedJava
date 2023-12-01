<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
window.onload = function() {
	document.getElementById("getBtn").addEventListener("click", function() {
		location.href = "http://localhost/webTest/servletTest03.do";
	})
}
</script>
</head>
<body>
	<h2>Servlet 요청 연습</h2>
	<br><hr><br>
	
	<h3>Get방식 요청1 ==> 링크 방식</h3>
	<a href="http://localhost/webTest/servletTest03.do">Get방식 요청1</a>
	<hr>
	
	<h3>Get방식 요청2 ==> form태그 방식</h3>
	<!--  <form>태그의 method속성값을 생략하거나 'Get'으로 설정하여 GET방식으로 submit하면
		  Get방식으로 요청하게 된다. -->
	<form action="http://localhost/webTest/servletTest03.do">
		<input type="submit" value="Get방식 요청2">
	</form>
	
	<h3>Get방식 요청3 ==> javascript를 이용하는 방식</h3>
	<!-- javascript의 location.href속성에 주소를 지정하면 Get방식으로 요청하게 된다. -->
	<form>
		<input type="button" value="Get방식 요청3" id="getBtn">
	</form>
	<hr><br>
	
	<h3>Post방식 요청 ==> form태그 방식</h3>
	<!-- <form>태그의 method속성값을 'Post'로 설정하여 submit하면
		 POST방식으로 요청하게 된다. -->
	<form action="http://localhost/webTest/servletTest03.do" method="post">
		<input type="submit" value="Post방식 요청2">
	</form>
</body>
</html>