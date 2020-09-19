<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>menu</title>
</head>
<body>
	<c:if test="${sessionScope.id ne null }">
		<br /> &nbsp;
	<b>My Page</b>
		<br />
		<br /> &nbsp;&nbsp;&nbsp;ㄴ&nbsp;
	<a href="">Dashboard</a>
		<br />
		<br /> &nbsp;&nbsp;&nbsp;ㄴ&nbsp;
	<a href="">Notice Board</a>
		<br />
		<br /> &nbsp;&nbsp;&nbsp;ㄴ&nbsp;
	<a href="">Update Profile</a>
		<br />
		<br />
		<br />
		<br /> &nbsp;
	<b>Course</b>
		<br />
		<br /> &nbsp;&nbsp;&nbsp;ㄴ&nbsp;
	<a href="/jsp_lms_mvc2/user/classlist.do">My class</a>
		<br />
		<br />
	</c:if>
	<br /> &nbsp;
	<b>Notice</b>
	<br />
	<br /> &nbsp;&nbsp;&nbsp;ㄴ&nbsp;
	<a href="/jsp_lms_mvc2/user/notice.do">Notice</a>
	<br />
	<br />

</body>
</html>