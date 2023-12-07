<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
			<td>아이디: <input type="text" readonly="readonly" name="id" value=${ses.id }></td>
		</tr>
		<tr>
			<td>비밀번호:  <input type="password" name="pwd" value=${ses.pwd }></td>
		</tr>
		<tr>
			<td>이메일:  <input type="text" name="email"  value=${ses.email }></td>
		</tr>
		<tr>
			<td>나이:  <input type="text" name="age" readonly="readonly" value=${ses.age }></td>
		</tr>
		<tr>
			<td>가입일:  <input type="text" name="regdate" readonly="readonly" value=${ses.regdate }></td>
		</tr>
		<tr>
			<td>마지막로그인:  <input type="text" name="lastlogin" readonly="readonly" value=${ses.lastlogin }></td>
		</tr>
	</table>
	<button type="submit">submit</button>
	<button type="reset">reset</button>
	<a href="/memb/remove"><button type="button">탈퇴</button></a>
	</form>
	<br>
	
	
	<a href="/index.jsp"><button type="button">돌아가기</button></a>

</body>
</html>