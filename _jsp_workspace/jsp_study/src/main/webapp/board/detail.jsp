<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<h1>Detail page</h1>
<table border="1">
<tr>
   		<th>글번호: <input type="text" disabled="disabled" value="${bvo.bno }"></th>
  	<tr/>
   <tr>
   		<th>제목: <input type="text" disabled="disabled" value="${bvo.title }"></th>
  	<tr/>
  	 <tr>
   		<th>작성자: <input type="text"  disabled="disabled" value="${bvo.writer }"></th>
  	<tr/>
  	 <tr>
   		<th>작성일: <input type="text" disabled="disabled" value="${bvo.regdate }"></th>
  	<tr/>
  	 <tr>
   		<th>수정일: <input type="text" disabled="disabled" value="${bvo.moddate }"></th>
  	<tr/>
  	 <tr>
   		<th>조회수: <input type="text" disabled="disabled" value="${bvo.readCount }"></th>
  	<tr/>
  	 <tr>
   		<th>내용: <textarea disabled="disabled">${bvo.content}</textarea></th>
  	<tr/>
   </table>
   <a href = "/brd/modify?bno=${bvo.bno}"><button>modify</button></a>
   <a href = "/brd/remove?bno=${bvo.bno}"><button>remove</button></a>
   <a href = "/brd/list"><button>list</button></a>
   <!-- comment line -->
   <hr>
   <div>
   comment line<br>
   <input type="text" id="cmtWriter" value="${ses.id}" readonly="readonly"><br>
   <input type="text" id="cmtText" placeholder="Add Comment...">
   <button type="button" id="cmtAddBtn">댓글등록</button>
   </div>
   
   <!-- 댓글 표시 라인 -->
   <div id="commentLine">
   <div>
   <div>cno, bno, writer, regdate</div>
   <div>
   <button>수정</button>
   <button>삭제</button><br>
   <input value="content">
   </div>
   </div>
   </div>
   <script type="text/javascript"  >
   const bnoVal = `<c:out value="${bvo.bno}" />`;
   console.log(bnoVal);
   const id = `<c:out value="${ses.id}" />`;
   </script>
   <script src="/resource/board_detail.js"></script>
   <script type="text/javascript">
   printCommentList(bnoVal);
   </script>
</body>
</html>