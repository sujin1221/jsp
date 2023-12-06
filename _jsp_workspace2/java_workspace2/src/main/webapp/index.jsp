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
<h1>Hello my First JSP World~!</h1>
<form action="/memb/login" method="post">
ID: <input type="text" name="id">
PWD: <input type="password" name="pwd">
<button type="submit">login</button>
</form>

<c:if test="${ses.id ne null }">
	${ses.id}님이 로그인하셨습니다.<br>
	계정생성일: ${ses.regdate }; <br>
	마지막접속: ${ses.lastlogin };<br>
	<a href="/memb/detail"><button>회원정보수정</button></a>
		<a href="/memb/list"><button>회원리스트</button></a>
		<a href="/memb/logout"><button>로그아웃</button></a>
		<a href="/brd/register"><button>글쓰기 페이지로 이동</button></a>
</c:if>
<hr>
<a href="/memb/join"><button>회원가입</button></a>
<a href="/brd/register"><button>글쓰기 페이지로 이동</button></a>
<a href="/brd/list"><button>게시판 리스트로 이동</button></a>
<a href="/memb/mylist"><button>작성 리스트</button></a>
<script type="text/javascript">
		const msg_login = `<c:out value="${msg_login}"/>`;
		console.log(msg_login);
		
		if(msg_login=="-1"){
			alert('로그인 정보가 일치하지 않습니다.');
		}
	</script>	
	
	<!-- 수정시 메세지가 뜨도록 설정 -->
	<c:if test="${not empty message}">
    <div class="message">${message}</div>
    </c:if>
    
</body>
</html>