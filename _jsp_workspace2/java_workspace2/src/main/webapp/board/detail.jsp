<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Detail page</h1>
<table border="1">
<tr>
   		<th>글번호: <input type="text" readonly="readonly" value="${bvo.bno }"></th>
  	<tr/>
   <tr>
   		<th>제목: <input type="text" readonly="readonly" value="${bvo.title }"></th>
  	<tr/>
  	 <tr>
   		<th>작성자: <input type="text"  readonly="readonly" value="${bvo.writer }"></th>
  	<tr/>
  	 <tr>
   		<th>작성일: <input type="text" readonly="readonly" value="${bvo.regdate }"></th>
  	<tr/>
  	 <tr>
   		<th>수정일: <input type="text" readonly="readonly" value="${bvo.moddate }"></th>
  	<tr/>
  	 <tr>
   		<th>조회수: <input type="text" readonly="readonly" value="${bvo.readCount }"></th>
  	<tr/>
  	 <tr>
   		<th>내용: <textarea>${bvo.content }</textarea></th>
  	<tr/>
   </table>
   <a href = "/brd/modify?bno=${bvo.bno }"><button >수정</button></a>
   <a href = "/brd/remove?bno=${bvo.bno }"><button >삭제</button></a>
   <a href = "/brd/list"><button>목록</button></a>
</body>
</html>