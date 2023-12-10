<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>member list 관리자용</h1>
	<table border="1">
		<tr>
			<th>ID</th>
			<th>PW</th>
			<th>E-mail</th>
			<th>Age</th>
			<th>Reg-date</th>
			<th>Last-login</th>
		</tr>
			<c:forEach items="${list }" var="mvo">
			<tr>
				<td>${mvo.id }</td>
				<td>${mvo.pwd }</td>
				<td>${mvo.email }</td>
				<td>${mvo.age }</td>
				<td>${mvo.regdate }</td>
				<td>${mvo.lastlogin }</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>