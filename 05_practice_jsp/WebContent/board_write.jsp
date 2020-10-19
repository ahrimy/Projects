<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>board_write.jsp</title>
</head>
<body>
	<c:if test="${ null eq sessionScope.log }">
		<script type="text/javascript">
			alert('You should sign in first to write a board!');
			location.href = 'Main.do';
		</script>
	</c:if>
	<jsp:include page="_top.jsp" />
	<div align="center">
		<h2>Write a new board</h2>
		<form method="post" action="Board_writePro.do">
			<table border="1">
				<tr height="40">
					<td align="center" width="150">ID</td>
					<!--<td width="450"><input type="text" name="writer"  size="60" /></td>-->
					<td>${user_id}</td>
				</tr>
				<tr height="40">
					<td align="center" width="150">Subject</td>
					<td width="450"><input type="text" name="board_subject" value="[Main]"
						size="60" required /></td>
				</tr>
				<tr height="40">
					<td align="center" width="150">Content</td>
					<td width="450"><textarea name="board_content" rows="10" cols="50"
							required></textarea></td>
				</tr>
				<tr height="40">
					<td align="center" width="150" colspan="2">
						<input type="submit" value="Upload" /> &nbsp;&nbsp; <input
						type="reset" value="Reset" /> &nbsp;&nbsp; <input type="button"
						value="List" onclick="location.href='Board_list.do'" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>