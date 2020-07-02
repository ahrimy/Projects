<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>user_22_result.jsp</title>
</head>
<body>
	<div align="center">
		<table border="1">
			<tr>
				<td width="10">No.</td>
				<td width="500">Test</td>
				<td width="200">Result</td>
			</tr>
			<c:set var="num" value="1" />
			<c:forEach var="i" begin="0" end="${size-1}">

				<tr>
					<td width="10">${num }</td>
					<td width="500">${testList.get(i).getTestTitle() }</td>
					<td width="200"><c:if
							test="${scoreList.get(i).getStatus() eq 'no result' }">
						N/A
					</c:if> <c:if test="${scoreList.get(i).getStatus() eq 'grading' }">
						Grading
					</c:if> <c:if test="${scoreList.get(i).getStatus() eq 'graded' }">
						${scoreList.get(i).getStuScore() }/${testList.get(i).getTestScore() }
					</c:if></td>
				</tr>
				<c:set var="num" value="${num+1}" />
			</c:forEach>

			<tr>
				<td colspan="3" align="center"><input type="button"
					value="Class Main" onclick="location.href = '/jsp_lms_mvc2/user/classinfo.do?classNum=${sessionScope.classNum}'" />
				</td>
			</tr>

		</table>
	</div>
</body>
</html>