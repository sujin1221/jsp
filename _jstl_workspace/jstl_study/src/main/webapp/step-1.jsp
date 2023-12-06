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
<h1>cif 명령어</h1>
<c:if test="true">
<h2>true일때 실행되는 명령어입니다.</h2>
<hr>
<!-- query string 방식 주소?정보=13&정보=123 '파라미터를 보낸다'라고 함-->
</c:if>
<h2>index에서 보낸 파라미터 처리</h2>
나이: ${param.age }<br>
이름: ${param.name }
<br><br>
<c:if test="${param.age <15 }">
${param.name }은 어린이입니다.
</c:if>
<!-- query string 방식으로 name=맛있는귤 address=제주 step-2.jsp로 이동 -->
<!-- step-2.jsp 페이지를 생성 후 파라미터를 출력  상품명: / 원산지: -->
<a href="step-2.jsp?name=맛있는귤address=제주">step-2.jsp로 이동</a>
<br><br>
</body>
</html>