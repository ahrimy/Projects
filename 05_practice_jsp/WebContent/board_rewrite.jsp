<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>board_reWrite.jsp</title>
</head>
<body>
	<jsp:include page="_top.jsp" />
	<div align="center">
		<h2>Write a reply Board</h2>
		<form method="post" action="Board_rewritePro.do">
			<table border="1">
				<tr height="40">
					<td width="150" align="center">ID</td>
					<td width="450">${user_id }</td>
				</tr>
				<tr height="40">
					<td width="150" align="center">Subject</td>
					<td width="450"><input type="text" name="board_subject"
						value="[Re:]" size="60" required></td>
				</tr>
				<tr height="40">
					<td width="150" align="center">Content</td>
					<td width="450"><textarea rows="10" cols="60"
							name="board_content" required></textarea></td>
				</tr>
				<tr height="40">
					<td align="center" colspan="2"><input type="hidden" name="ref"
						value="${ref }" /> <input type="hidden"
						name="restep" value="${restep }" /> <input
						type="hidden" name="relevel" value="${relevel}" />
						<input type="submit" value="Upload" />&nbsp;&nbsp; <input
						type="button" value="Cancel" onclick="history.go(-1)"/> <input type="button" value="List"
						onclick="location.href='Board_list.do'" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>











