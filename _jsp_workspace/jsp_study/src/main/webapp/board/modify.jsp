<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Detail page-1</h1>
<br><img alt="" src="/_fileUpload/${bvo.imageFile}">
<form action="/brd/edit" method="post" enctype="multipart/form-data">
<table border="1">
<tr>
<!-- <input type = "hidden" name="bno" value = "${bvo.bno }">  -->
   		<th>글번호: <input type="text" value="${bvo.bno }" name="bno" readonly="readonly" input type="hidden" ></th>
  	<tr/>
   <tr>
   		<th>제목: <input type="text" value="${bvo.title }" readonly="readonly" name="title"></th>
  	<tr/>
  	 <tr>
   		<th>작성자: <input type="text" name="writer" readonly="readonly" value="${bvo.writer }"></th>
  	<tr/>
  	 <tr>
   		<th>작성일: <input type="text" name="regdate" readonly="readonly" value="${bvo.regdate }"></th>
  	<tr/>
  	 <tr>
   		<th>수정일: <input type="text" name="moddate" readonly="readonly" value="${bvo.moddate }"></th>
  	<tr/>
  	 <tr>
   		<th>조회수: <input type="text" name="readCount" readonly="readonly" value="${bvo.readCount }"></th>
  	<tr/>
  	 <tr>
   		<th>내용: <textarea rows="10" cols="30" name = "content">${bvo.content }</textarea></th>
  	<tr/>
  	<tr>
  	<th>image_file</th>
  	<td>
  	<input type="hidden" name="image_file" value="${bvo.imageFile}">
  	<!-- accept="image/png, image/jpg, image/gif, image/jpeg" => 안해도 됨 -->
  	<input type="file" name="new_file" accept="image/png, image/jpg, image/gif, image/jpeg">
  	</td>
   </table>
   <button type="submit">수정</button>
   <a href = "/brd/list"><button>목록</button></a>
   </form> 
</body>
</html>