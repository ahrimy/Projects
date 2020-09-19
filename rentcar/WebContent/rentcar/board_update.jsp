<%@page import="rental.db.BoardDTO"%>
<%@page import="rental.db.BoardDAO"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
	<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>06_board_update.jsp</title>
	</head>
	<body>
	<jsp:include page="_top.jsp" />
		<%
			int num = Integer.parseInt(request.getParameter("num"));
			BoardDTO board = BoardDAO.getInstance().getOneUpdateBoard(num);
		%>
		<div align="center">
			<h2>Edit the board</h2>
			<form method="post" action="board_updatePro.jsp" >
				<table border="1">
					<tr height="40">
						<td width="120" align="center">ID</td>
						<td width="180" align="center"><%= board.getUser_num() %></td>
						<td width="120" align="center">Date</td>
						<td width="180" align="center"><%= board.getBoard_regdate() %></td>
					</tr>
					<tr height="40">
						<td width="120" align="center">Subject</td>
						<td width="480" colspan="3">
							<input type="text" name="subject" value="<%= board.getBoard_subject() %>" size="60" required/>
						</td>
					</tr>
					<tr height="40">
						<td width="120" align="center">Password</td>
						<td width="480" colspan="3">
							<input type="password" name="user_pw" size="60" required/>
						</td>
					</tr>
					<tr height="40">
						<td width="120" align="center">Content</td>
						<td width="480" colspan="3"><textarea name="content" rows="10" cols="60" required><%= board.getBoard_content() %></textarea></td>
					</tr>
					<tr height="40">
						<td colspan="4" align="center">
	<input type="hidden" name="board_num" value="<%= num %>" />
	<input type="hidden" name="user_num" value="<%= board.getUser_num() %>"/>
							<input type="submit" value="Done" /> &nbsp;&nbsp;
							<input type="button" value="List" onclick="location.href='board_list.jsp'" />
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>