<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mylist page</title>
</head>
<body>
   <h1>list page</h1>
   <table border="1">
   <tr>
   <th>bno</th>
   <th><a href="/brd/">title</th>
   <th>writer</th>
   <th>regdate</th>
   <th>readcount</th>
   </tr>
   <!-- db에서 가져온 list를 c:foreach를 통해서 반복해서 출력 -->
   <c:forEach items="${list }" var="bvo">
   <tr>
   <td><a href="/brd/detail?bno=${bvo.bno}">${bvo.bno }</a></td>
   <td><a href="/brd/detail?bno=${bvo.bno}">${bvo.title }</a></td>
   <td>${bvo.writer }</td>
   <td>${bvo.regdate }</td>
   <td>${bvo.readCount }</td>
   </tr>
   </c:forEach>
   </table>
  
   <a href ="/brd/register"><button>register</button></a>
   <a href="/index.jsp"><button>index</button></a>
</body>
</html>