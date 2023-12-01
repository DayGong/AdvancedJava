<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Request연습 Form(숫자 입력은 정수형으로 입력하세요.)</h2>
	<hr>
	<form action="/webTest/requestTest02.do" method="GET">
		<input type="text" name="firstNum">
		<select name="oper">
			<option value="+">+</option>
			<option value="-">-</option>
			<option value="*">*</option>
			<option value="/">/</option>
			<option value="%">%</option>
		</select>
		<input type="text" name="secondNum">
		<input type="submit" value="확인">
	</form>
</body>
</html>