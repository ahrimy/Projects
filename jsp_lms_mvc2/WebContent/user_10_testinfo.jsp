<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>user_19_testinfo.jsp</title>
</head>
<body>
	<div align="center">
		<h2>Test Info</h2>
		<br />
		<table border="1">
			<tr height="100">

				<td width="600" align="center"><b>${testTitle }</b>
	<br/><br/>
				${testDesc }</td>
			</tr>
			<tr height="50">
				<td width="450" align="center"><input
					type="button" value= "back" onclick="history.go(-1)" />
					<c:if test="${3 eq sessionScope.type}"><input type="button" value="Edit"onclick="location.href='/jsp_lms_mvc2/user/edittest.do?testNum=${testNum}'" /></c:if>
					<c:if test="${2 eq sessionScope.type}">
					<c:if test="${'no result' eq status }">
					<input type="button" value="start"onclick="location.href='/jsp_lms_mvc2/user/taketest.do?testNum=${testNum}'" />
					</c:if>
					</c:if>

				
					</td>
			</tr>
		</table>

	</div>
</body>
</html>