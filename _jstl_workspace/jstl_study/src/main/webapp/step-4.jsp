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
과일1: ${paramValues.food[0] }<br>
과일2: ${paramValues.food[1] }<br>
과일3: ${paramValues.food[2] }<br>
과일4: ${paramValues.food[3] }<br>
과일5: ${paramValues.food[4] }<br>
<br>
<hr>

<h3>c:forEach를 사용한 출력</h3>
<!-- varStatus: index(0번지부터 시작), count(1부터 시작) -->
<c:forEach items="${paramValues.food }" var="foods" varStatus="st">
과일: ${st.index} / ${st.count} : ${foods }<br>
</c:forEach>

<%
String friends[] = {"삼겹살","소주","족발","맥주"};
//java Controller에서 jsp로 데이터를 보낼 때 사용
request.setAttribute("f", friends);
%>
<br>
<hr>
<!-- requestScope 생략 가능 -->
<c:forEach items="${requestScope.f }" var="fname" varStatus="st">
count: ${st.count };
index: ${st.index };
${fname }<br>
</c:forEach>
<br>
<hr>
<!-- begin="시작숫자" end="끝숫자" var="요소지정변수" -->
<c:forEach begin="1" end="10" var="i">
${i } /
</c:forEach >
<a href="step-3.jsp">step-3으로 이동</a>
</body>
</html>