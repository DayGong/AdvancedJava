<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Session 로그인</title>
</head>
<%
	MemberVO memVo = (MemberVO)session.getAttribute("loginMember");
%>

<body>

<%
	if(memVo == null) {
%>
	<form action="<%= request.getContextPath() %>/member/sessionLoginDb.do" method="post">
		<table border="1">
			<tr>
				<td>ID:	</td>
				<td>
					<input type="text" name="userid" placeholder="ID를 입력하세요.">
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
 		<h2><%= memVo.getMem_name() %>님 반갑습니다.</h2>
		
		<a href="<%= request.getContextPath() %>/member/sessionLogoutDb.do">로그아웃</a>
<%
	}
%>
</body>
</html>