<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>_index.jsp</title>
</head>
<body>
	<c:if test="${1 eq sessionScope.version}">

	</c:if>
	<c:if test="${2 eq sessionScope.version}">
		<jsp:include page="user_00_top.jsp" />
	</c:if>

	<div align="center">
		<table border="1">
			<tr>
				<td width="150"><jsp:include page="user_01_menu.jsp" /></td>
				<td width="900"><jsp:include page="${cont}" /></td>
			</tr>

		</table>
	</div>
</body>
</html>