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
	<h1>Join Page</h1>
	<br>
	<form action="/memb/register" method="post">
		ID : <input type="text" name="id"><br>
		PassWord : <input type="password" name="pwd"><br>
		E-Mail : <input type="text" name="email"><br>
		Age : <input type="text" name="age"><br>
		<button type="submit">join</button>
	</form>
</body>
</html>