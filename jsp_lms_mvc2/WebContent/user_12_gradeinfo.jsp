<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>gradetest</title>
</head>
<body>
	<div align="center">


		<table border="1">
			<tr>
				<td width="10" align="center">Student ID</td>
				<td width="500">Student Name</td>

				<td width="200">Status</td>

			</tr>
			<c:set var="number" value="${1}" />

			<c:forEach var="i" begin="0" end="${size-1 }">
				<tr>
					<td width="10" align="center">${number }</td>
					<td width="500"><a
						href="/jsp_lms_mvc2/user/gradetest.do?scoreNum=${scoreList.get(i).getScoreNum() }">student${number }</a></td>

					<td width="200">${scoreList.get(i).getStatus() }</td>

					<c:set var="number" value="${number+1}" />
				</tr>
			</c:forEach>



		</table>

	</div>

</body>
</html>