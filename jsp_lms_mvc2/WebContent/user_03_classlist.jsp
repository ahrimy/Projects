<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>classlist.jsp</title>
</head>
<body>
	<div align="center">
		<h2>My class</h2>
		<br />

		<table border="1">
			<c:if test="${classes.size() eq 0 }">no class</c:if>
			<c:if test="${classes.size() gt 0 }">
				<tr>
					<td width="10">No.</td>
					<td width="500">Course name</td>
					<c:if test="${10 eq sessionScope.type}">
						<td width="200">Professor</td>
					</c:if>
					<td width="100">Participants</td>
					<td width="40">Part</td>
				</tr>
				<c:set var="number" value="${1}" />
				<c:forEach var="i" begin="0" end="${classes.size()-1}">
					<tr>
						<td width="10">${number}</td>
						<td width="500"><a
							href="/jsp_lms_mvc2/user/classinfo.do?classNum=${classes.get(i).getClassNum() }">${titles[i]}</a></td>
						<c:if test="${10 eq sessionScope.type}">
							<td width="200">${professors[i]}</td>
						</c:if>
						<td width="100">${participants[i]}</td>
						<td width="40"></td>
					</tr>
					<c:set var="number" value="${number+1}" />
				</c:forEach>
			</c:if>
		</table>
	</div>
</body>
</html>