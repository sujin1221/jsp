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
	<h1>detail page</h1>
	<form action="/memb/edit" method="post">
	<table border="1">
		<tr>
			<td>아이디: <input type="text" readonly="readonly" name="id" value=${mvo.id }></td>
		</tr>
		<tr>
			<td>비밀번호:  <input type="text" name="pwd" value=${mvo.pwd }></td>
		</tr>
		<tr>
			<td>이메일:  <input type="text" name="email"  value=${mvo.email }></td>
		</tr>
		<tr>
			<td>나이:  <input type="text" name="age" readonly="readonly" value=${mvo.age }></td>
		</tr>
		<tr>
			<td>가입일:  <input type="text" name="regdate" readonly="readonly" value=${mvo.regdate }></td>
		</tr>
		<tr>
			<td>마지막로그인:  <input type="text" name="lastlogin" readonly="readonly" value=${mvo.lastlogin }></td>
		</tr>
	</table>
	<button type="submit">submit</button>
	<a href="/memb/remove?id=${mvo.id}"><button type="button">회원탈퇴</button></a>
	</form>
	<br>
	
	
	<a href="/brd/list"><button type="button">리스트</button></a>
</body>
</html>