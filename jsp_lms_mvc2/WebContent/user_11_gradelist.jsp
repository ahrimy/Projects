<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>user_11_gradetestlist.jsp</title>
</head>
<body>
	<div align="center">

		<c:set var="number" value="${1}" />

		<table border="1">
			<c:if test="${tests.size() eq 0 }">no result</c:if>
			<c:if test="${tests.size() gt 0 }">
				<tr>
					<td width="10" align="center">No.</td>
					<td width="500">Test</td>
				</tr>
				<c:forEach var="test" items="${tests }">
					<tr>
						<td width="10" align="center">${number}</td>
						<td width="500"><a
							href="/jsp_lms_mvc2/user/gradeinfo.do?testNum=${test.getTestNum() }">${test.getTestTitle() }</a></td>

						<c:set var="number" value="${number+1}" />
					</tr>
				</c:forEach>
			</c:if>
		</table>

	</div>
</body>
</html>