<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Session 로그인</title>
</head>
<body>
<%
	// JSP문서에서의 세션은 'session'이라는 변수에 이미 저장되어 있다.
	
	// 세션에 저장한 데이터 가져오기
	String userId = (String)session.getAttribute("id");
	
	if (userId == null) {
%>
	<form action="<%= request.getContextPath() %>/sessionLogin.do" method="post">
		<table border="1">
			<tr>
				<td>ID:	</td>
				<td>
					<input type="text" name="id" placeholder="ID를 입력하세요.">
				</td>
			</tr>
			<tr>
				<td>PW: </td>
				<td>
					<input type="text" name="pass" placeholder="PASSWORD를 입력하세요.">
				</td>
			</tr>
			<tr>
				<td colspan="2" style="text-align:center;">
					<input type="submit" value="Login">
				</td>
			</tr>
		</table>
	</form>
<%
	} else {
%>
		<h2><%= userId %>님 반갑습니다.</h2>
		
		<a href="<%= request.getContextPath() %>/sessionLogout.do">로그아웃</a>
<%
	}
%>
</body>
</html>