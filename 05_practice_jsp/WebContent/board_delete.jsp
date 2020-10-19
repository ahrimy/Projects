<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
	<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>board_delete.jsp</title>
	</head>
	<body>
	<jsp:include page="_top.jsp" />

		<div align="center">
			<h2>Delete the Board</h2>
			<form method="post" action="Board_deletePro.do">
				<table border="1">
					<tr height="40">
						<td width="120" align="center">ID</td>
						<td width="180" align="center">${user_id}</td>
						<td width="120" align="center">Date</td>
						<td width="180" align="center">${board.getBoard_regdate()}</td>
					</tr>
					<tr height="40">
						<td width="120" align="center">Subject</td>
						<td align="center" colspan="3">${board.getBoard_subject()}</td>
					</tr>
					<tr height="40">
						<td width="120" align="center">Password</td>
						<td align="center" colspan="3">
							<input type="password" name="board_pw" size="60" />
						</td>
					</tr>
					<tr height="40">
						<td align="center" colspan="4">
							<input type="hidden" name="board_num" value="${board.getBoard_num() }" />
							<input type="submit" value="Confirm" />&nbsp;&nbsp;
							<input type="button" value="Cancel" onclick="location.href='Board_list.do'" />
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>