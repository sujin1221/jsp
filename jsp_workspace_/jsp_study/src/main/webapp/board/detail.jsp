<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</head>
<body>
	<h1>detail page</h1>
	<div>
		<img alt="" src="/_fileUpload/${bvo.imageFile }">
	</div>
	<table class="table table-hover">
		<tr>
			<td>제목: <input type="text" readonly="readonly" value=${bvo.bno }></td>
		</tr>
		<tr>
			<td>작성자:  <input type="text" readonly="readonly" value=${bvo.writer }></td>
		</tr>
		<tr>
			<td>작성일:  <input type="text" readonly="readonly" value=${bvo.regdate }></td>
		</tr>
		<tr>
			<td>수정일:  <input type="text" readonly="readonly" value=${bvo.moddate }></td>
		</tr>
		<tr>
			<td>조회수:  <input type="text" readonly="readonly" value=${bvo.readcount }></td>
		</tr>
		<tr>
			<td>내용: <br><textarea readonly="readonly">${bvo.content }</textarea></td>
		</tr>
	</table>
	<br>
	
	
	<c:if test="${ses.id eq bvo.writer }">
	<a href="/brd/modify?bno=${bvo.bno }"><button type="button">수정</button></a>
	<a href="/brd/remove?bno=${bvo.bno }&imagefile=${bvo.imageFile}"><button type="button">삭제</button></a>
	</c:if>
	<a href="/brd/list"><button type="button">리스트</button></a>
	
	<!-- comment line  -->
	<hr>
	<div>
	comment line<br>
	<input type="text" id="cmtWriter" value="${ses.id }" readonly><br>
	<input type="text" id="cmtText" placeholder="Add Comment...">
	<button type="button" id="cmtAddBtn">댓글등록</button>
	</div>
	
	<hr>
	<br>
	<!-- 댓글표시 라인 -->
	<div id="commentLine">
		<div>
			<div>cno,bno,writer,regdate</div>
			<div>
				<button>수정</button> <button>삭제</button><br>
				<input value="content">
			</div>
		</div>	
	</div>
	<script type="text/javascript">
		const bnoVal = `<c:out value="${bvo.bno}"/>`
		console.log(bnoVal);
	</script>
	
	<script type="text/javascript" src="/resources/board_detail.js"></script>
	<script type="text/javascript">
		printCommentList(bnoVal);
	</script>
	
</body>
</html>