<%@ page import="rental.db.BoardDAO"%>
<%@ page import="rental.db.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
	<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>board_delete.jsp</title>
	</head>
	<body>
	<jsp:include page="_top.jsp" />
		<%
			int num = Integer.parseInt(request.getParameter("num"));
			BoardDTO board = BoardDAO.getInstance().getOneUpdateBoard(num);
		%>
		<div align="center">
			<h2>Delete the board</h2>
			<form method="post" action="board_deletePro.jsp">
				<table border="1">
				<tr>
						<td width="120" align="center">Password</td>
						<td align="center" colspan="3">
							<input type="password" name="user_pw" size="60" />
						</td>
					</tr>
					<tr height="40">
						<td align="center" colspan="4">
							<input type="hidden" name="user_num" value="<%=board.getUser_num() %>" />
							<input type="hidden" name="board_num" value="<%=board.getBoard_num() %>"/>
							<input type="submit" value="글삭제" />&nbsp;&nbsp;
							<input type="button" value="목록보기" onclick="location.href='board_list.jsp'" />
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>