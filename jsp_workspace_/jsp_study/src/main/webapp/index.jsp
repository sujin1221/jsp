<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Welcome to my jsp world~!</h2>
	<!-- get:페이지이동시 사용 / post: 많은데이터를 가지고 등록 할 때 사용-->
	<form action="/memb/login" method="post">
		ID : <input type="text" name="id">
		Pwd : <input type="password" name="pwd" placeholder="Password">
		<button type="submit">login</button>
	</form>
	
	<div>
		<c:if test="${ses.id ne null }">
		${ses.id }님이 login 하셨습니다. <br>
		계정생성일 : ${ses.regdate }<br>
		마지막접속 : ${ses.lastlogin }<br>
		<a href="/memb/detail"><button>회원정보수정</button></a>
		<a href="/memb/list"><button>회원리스트</button></a>
		<a href="/memb/logout"><button>로그아웃</button></a>
		<a href="/brd/register"><button>글쓰기 페이지로 이동</button></a>
		</c:if>
		
		<c:if test="${updateCom > 0}">
			<p>회원정보가 수정되었습니다!</p>
		</c:if>
	</div>
	<br>
	<hr>
	<br>
	
	<a href="/memb/join"><button>회원가입</button></a>
	<a href="/brd/list"><button>게시판 리스트로 이동</button></a>
</body>
	<script type="text/javascript">
		const msg_login = `<c:out value="${msg_login}"/>`;
		console.log(msg_login);
		
		if(msg_login=="-1"){
			alert('로그인 정보가 일치하지 않습니다.');
		}
		
		const updateCom = `<c:out value="${updateCom}"/>`;
		console.log(updateCom);
		if(updateCom=="1"){
			alert("회원정보가 수정되었습니다.");
		}else if(updateCom=="0"){
			alert("회원정보 수정이 실패했습니다.");	
		}
		
		const deleteUser = `<c:out value="${deleteUser}"/>`;
		console.log(updateCom);
		if(deleteUser=="1"){
			alert("회원이 탈퇴되었습니다.");
		}
		
	</script>
</html>