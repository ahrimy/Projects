<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>maketest.jsp</title>
</head>
<body>
	<form method="post" action="/jsp_lms_mvc2/user/maketestPro.do">
		<div align="center">
			<h2>Make new Test</h2>
			<br />
			<table border="1">
				<tr class="testTitle" height = "100">
					<%--시험 정보 --%>
					<td width="600" align="center">Test Title : <input type="text"
						name="testTitle" value="Untitled form" size="60" required /><br /><br/>
						Description : <input type="text" name="testDesc" value="Form description"
						size="60" />
				<tr height = "50">
					<td width="450" align="center">
					<input type="submit" value="Next" /></td>
				</tr>
			</table>
			<br>
			<br>

		</div>

	</form>
</body>
</html>