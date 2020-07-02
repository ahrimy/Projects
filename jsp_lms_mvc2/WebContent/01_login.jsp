<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="UTF-8">
<title>Name College</title>
</head>
<body>
	<div align="center">

		<c:if test="${ null eq id }">
			<form action="/jsp_lms_mvc2/user/loginPro.do" method="post">
				<input type="text" id="id" name="id" autofocus placeholder="학번입력"><br><br> 
				<input type="password" id="pw" name="pw"><br><br> 
				<input type="submit" value="로그인"><br><br>
			</form>
			<a href="/jsp_lms_mvc2/user/join.do">회원가입</a>
			<a href="find.college">학번찾기</a>
		</c:if>
		
	</div>

</body>
</html>