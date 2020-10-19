<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>board_update.jsp</title>
</head>
<body>
	<jsp:include page="_top.jsp" />

	<div align="center">
		<h2>Edit the board</h2>
		<form method="post" action="Board_updatePro.do">
			<table border="1">
				<tr height="40">
					<td width="120" align="center">ID</td>
					<td width="180" align="center">${user_id }</td>
					<td width="120" align="center">Date</td>
					<td width="180" align="center">${board.getBoard_regdate() }</td>
				</tr>
				<tr height="40">
					<td width="120" align="center">Subject</td>
					<td width="480" colspan="3"><input type="text"
						name="board_subject" value="${board.getBoard_subject()}" size="60"
						required /></td>
				</tr>
				<tr height="40">
					<td width="120" align="center">Password</td>
					<td width="480" colspan="3"><input type="password" name="board_pw"
						size="60" required /></td>
				</tr>
				<tr height="40">
					<td width="120" align="center">Content</td>
					<td width="480" colspan="3"><textarea name="board_content"
							rows="10" cols="60" required>${board.getBoard_content() }</textarea></td>
				</tr>
				<tr height="40">
					<td colspan="4" align="center"><input type="hidden" name="board_num"
						value="${board.getBoard_num() }" /> <input type="submit"
						value="Confirm" /> &nbsp;&nbsp; <input type="button" value="Cancel"
						onclick="location.href='Board_list.do'" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>