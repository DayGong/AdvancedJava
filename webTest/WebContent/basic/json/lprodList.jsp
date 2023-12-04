<%@page import="kr.or.ddit.vo.LprodVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
	List<LprodVO> lprodList = (List<LprodVO>)request.getAttribute("lprodList");
%>

<body>
<h2>Lprod 자료 목록(Non Ajax)</h2>
<table border="1">
<tr>
	<td>lprod_id</td><td>lprod_gu</td><td>lprod_nm</td>
</tr>
<%
for(LprodVO lvo : lprodList) {
%>
	<tr>
		<td><%= lvo.getLprod_id() %></td>
		<td><%= lvo.getLprod_gu() %></td>
		<td><%= lvo.getLprod_nm() %></td>
	</tr>
<%
}
%>
</table>
</body>
</html>