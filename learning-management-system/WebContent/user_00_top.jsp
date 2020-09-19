<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>top</title>
</head>
<body>
	<div>
		<span style="float: left"><a href="/jsp_lms_mvc2/user/main.do"><b>LMS
					Main</b></a></span> <span style="float: right"> <c:if
				test="${ null eq sessionScope.id }">

				<a href="/jsp_lms_mvc2/user/join.do">Join</a>&nbsp;&nbsp;
					<a href="/jsp_lms_mvc2/user/login.do">Sign In</a>
			</c:if> <c:if test="${null ne sessionScope.id }">
				"${ id }"님 반갑습니다.
					<a href="/jsp_lms_mvc2/user/mypage.do">MyPage</a>&nbsp;&nbsp;<a
					href="/jsp_lms_mvc2/user/signoutPro.do">Sign Out</a>
			</c:if>
		</span>
	</div>
	<br>

	<div align="center">
		<h2>LMS</h2>
	</div>
	<br>
	<div align="center">
		<a href="/jsp_lms_mvc2/user/.do">Notice</a>
	</div>
	<hr>
</body>
</html>