<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%
    //post방식의 한글처리
    request.setCharacterEncoding("utf-8");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>주문자명: ${param.customer }</h3>
주문하신메뉴: <br>
<c:forEach items="${paramValues.menu }" var="food" varStatus="st">
${st.count }.${food }<br>
</c:forEach>
주문 완료!<br>
<hr>
<a href="step-7.jsp">step-7으로 이동</a>
</body>
</html>