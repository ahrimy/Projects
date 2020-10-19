<%@ page import="board.BoardDAO"%>
<%@ page import="board.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
	<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>board_delete.jsp</title>
	</head>
	<body>
	<jsp:include page="board_top.jsp" />
		<%
			int num = Integer.parseInt(request.getParameter("num"));
			Board board = BoardDAO.getInstance().getOneUpdateBoard(num);
		%>
		<div align="center">
			<h2>게시글 삭제</h2>
			<form method="post" action="board_deletePro.jsp">
				<table border="1">
					<tr height="40">
						<td width="120" align="center">작성자</td>
						<td width="180" align="center"><%= board.getWriter() %></td>
						<td width="120" align="center">작성일</td>
						<td width="180" align="center"><%= board.getRegDate() %></td>
					</tr>
					<tr height="40">
						<td width="120" align="center">제목</td>
						<td align="center" colspan="3"><%= board.getSubject() %></td>
					</tr>
					<tr height="40">
						<td width="120" align="center">패스워드</td>
						<td align="center" colspan="3">
							<input type="password" name="password" size="60" />
						</td>
					</tr>
					<tr height="40">
						<td align="center" colspan="4">
							<input type="hidden" name="num" value="<%= num %>" />
							<input type="submit" value="글삭제" />&nbsp;&nbsp;
							<input type="button" value="목록보기" onclick="location.href='board_list.jsp'" />
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>