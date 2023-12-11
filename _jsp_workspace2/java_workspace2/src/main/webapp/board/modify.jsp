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
	<h1>Modify page</h1>
	
	<div>
		<img alt="" src="/_fileUpload/${bvo.imageFile }">
	</div>
	
	<form action="/brd/edit" method="post" enctype="multipart/form-data">
	
	
	
	<table border="1">
		<!-- <input type="hidden" name="bno" value="${bvo.bno }"> 이방법도 가능 -->
		<tr>
			<td>글번호:</td><td> <input type="text" name="bno" readonly="readonly" value="${bvo.bno }"></td>
		</tr>
		<tr>
			<td>글제목:</td><td> <input type="text" name="title"  value="${bvo.title }"></td>
		</tr>
		<tr>
			<td>작성자:</td><td>  <input type="text" readonly="readonly" value="${bvo.writer }"></td>
		</tr>
		<tr>
			<td>작성일:</td><td>  <input type="text" readonly="readonly" value="${bvo.regdate }"></td>
		</tr>
		<tr>
			<td>수정일:</td><td>  <input type="text" readonly="readonly" value="${bvo.moddate }"></td>
		</tr>
		<tr>
			<td>조회수:</td><td>  <input type="text" readonly="readonly" value=${bvo.readcount }></td>
		</tr>
		<tr>
			<td>내용:</td><td> <br><textarea rows="10" cols="30" name="content">${bvo.content }</textarea></td>
		</tr>
		<tr>
			<th>image_file</th>
			<td>
				<input type="hidden" name="image_file" value="${bvo.imageFile }">
				<input type="file" name="new_file" accept="image/png, image/jpg, image/gif"">
			</td>
		</tr>

	</table>
	<button type="submit">수정하기</button>
	</form>
	<br>
	<a href="/brd/list"><button type="button">리스트</button></a>
</body>
</html>