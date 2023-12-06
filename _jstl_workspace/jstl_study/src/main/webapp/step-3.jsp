<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2> Form 태그로 전달한 파라미터 받기</h2>
<!-- 스크립틀릿 방식은 parameter 값이 문자열로 인식, 연산불가능 -->
<%= request.getParameter("name") %>
<%= request.getParameter("age")+1 %>
<br><br>
<!-- EL 방식은 내부적으로 형변환을 하여 연산이 가능함 -->
이름: ${param.name }<br>
나이: ${param.age+1 }<br>
<!-- c:if로 나이가 18세 미만이면 '미성년자' 출력 -->
<c:if test="${param.age<18}">
<h3>미성년자</h3>
</c:if>
<!-- choose를 사용하여 다중 조건처리 -->
<c:choose>
<c:when test="${param.age>=19 }">
<h3>${param.name }님은 ${param.age }세, 성인입니다.</h3>
</c:when>
<c:when test="${param.age>=15 }">
<h3>${param.name }님은 ${param.age }세, 청소년입니다.</h3>
</c:when>
<c:when test="${param.age>=5 }">
<h3>${param.name }님은 ${param.age }세, 어린이입니다.</h3>
</c:when>
<c:otherwise>
<h3>${param.name }님은 ${param.age }세, 유아입니다.</h3>
</c:otherwise>
</c:choose>
<form action="step-4.jsp">
<input type="checkbox" name="food" value="바나나">바나나<br>
<input type="checkbox" name="food" value="딸기">딸기<br>
<input type="checkbox" name="food" value="귤">귤<br>
<input type="checkbox" name="food" value="사과">사과<br>
<input type="checkbox" name="food" value="복숭아">복숭아<br>
<button type="submit">전송</button>
</form>
</body>
</html>