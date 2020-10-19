<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>_top.jsp</title>
</head>
<body>


	<div>
		<span style="float: left"><a href="Main.do">Home</a></span> <span
			style="float: right"> <c:if
				test="${ null eq sessionScope.log }">
				<span style="float: right"><a href="User_join.do">Join</a>&nbsp;&nbsp;
					<a href="User_signin.do">Sign In</a></span>
			</c:if> <c:if test="${null ne sessionScope.log }">
				<span style="float: right">${sessionScope.id}&nbsp;is signed in&nbsp;
					<a href="User_mypage.do">MyPage</a>&nbsp;&nbsp;<a
					href="User_signoutPro.do">Sign Out</a>
				</span>
			</c:if>

		</span>
	</div>
	<br>
	<h1 align="Center">Board</h1>

</body>
</html>