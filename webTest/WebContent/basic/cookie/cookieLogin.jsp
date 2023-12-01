<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
String cookieLoginId = "";
String chk = "";

Cookie[] cookieArr = request.getCookies();

if(cookieArr != null) {
	for(Cookie cookie : cookieArr) {
		if("USERID".equals(cookie.getName())) { // 쿠키 이름이 있는지 확인
			// 쿠키 이름이 있으면 해당 쿠키 값 가져오기
			cookieLoginId = cookie.getValue();
			chk = "checked";
		}
	}
}
%>
<body>

<form action="<%= request.getContextPath()%>/cookieLoginServlet.do" method="post">
	<table>
		<tr>
			<td>ID: </td>
			<td><input type="text" name="userid" value="<%= cookieLoginId %>" placeholder="ID 입력"></td>
		</tr>
		<tr>
			<td>PW: </td>
			<td><input type="password" name="pass" placeholder="Password 입력"></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="checkbox" name="idchk" <%= chk %> value="check">ID 기억하기
			</td>
		</tr>
		<tr>
			<td colspan="2" style="text-align:center;">
				<input type="submit" value="Login">
			</td>
		</tr>
	</table>
</form>

</body>
</html>