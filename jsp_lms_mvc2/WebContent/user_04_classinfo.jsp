<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>class info</title>
</head>
<body>
	<div align="center">
		<p>
			<a href="/jsp_lms_mvc2/user/testlist.do">Test
				List</a>
		</p>
		<c:if test="${3 eq sessionScope.type}">
			<p>
				<a href="/jsp_lms_mvc2/user/maketest.do">Make
					Test</a>
			</p>
				<p>
				<a href="/jsp_lms_mvc2/user/gradelist.do">Grade Test</a>
			</p>
		</c:if>

		<c:if test="${2 eq sessionScope.type}">
			<p>
				<a href="/jsp_lms_mvc2/user/testresult.do">Test
					Result</a>
			</p>
		</c:if>
	</div>
</body>
</html>