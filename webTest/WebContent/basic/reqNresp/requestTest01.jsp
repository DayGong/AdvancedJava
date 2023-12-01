<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<%-- 이것은 JSP 주석을 나타낸다. --%>

<%
	// 이 영역은 JSP문서에서 JAVA명령을 사용할  수 있는 영역
	// '스크립트릿'이라고 한다.
	String name = "홍길동";
%>
<%--
<%= 변수 또는 수식 %>
--%>

<!-- 
<form>태그의 속성
1) action
2) method
3) target
4) enctype
 -->
<body>
<h2><%= name %>Request연습용 Form<%= 300-20*2 %></h2>
<%--
<form action="http://localhost:80/webTest/requestTest01.do">
--%>
<form action="/webTest/requestTest01.do" method="GET">
<table border="1">
<tr>
	<td>이름</td>
	<td><input type="text" size="10" name="username"></td>
</tr>

<tr>
	<td>직업</td>
	<td>
		<select name="job">
			<option value="회사원">회사원</option>
			<option value="전문직">전문직</option>
			<option value="학생">학생</option>
			<option value="무직">무직</option>
		</select>
	</td>
</tr>

<tr>
	<td>취미</td>
	<td>
		<input type="checkbox" name="hobby" value="여행">여행
		<input type="checkbox" name="hobby" value="독서">독서
		<input type="checkbox" name="hobby" value="게임">게임
		<input type="checkbox" name="hobby" value="배드민터">배드민턴
	</td>
</tr>

<tr>
	<td colspan="2" style="text-align: center;">
		<input type="submit" value="전송">
		<input type="reset" value="초기화">
	</td>
</tr>
</table>
</form>
</body>
</html>