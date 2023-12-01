<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>forward방식과 redirect방식 연습</h3>
<hr>
<form action="<%= request.getContextPath() %>/forwardTest01.do" method='post'>
forward방식: <input type="text" name="username">
<input type="submit" value="확인">
</form>

<br><hr><br>

<form action="<%= request.getContextPath() %>/redirectTest01.do" method='post'>
redirect방식: <input type="text" name="username">
<input type="submit" value="확인">
</form>
</body>
</html>