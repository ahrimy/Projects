<%@page import="board.db.BoardDTO"%>
<%@page import="board.db.BoardDAO"%>
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
			<h2>게시글 수정</h2>
			<form method="post" action="board_updatePro.jsp" >
				<table border="1">
					<tr height="40">
						<td width="120" align="center">작성자</td>
						<td width="180" align="center"><%= board.getId() %></td>
						<td width="120" align="center">작성일</td>
						<td width="180" align="center"><%= board.getRegdate() %></td>
					</tr>
					<tr height="40">
						<td width="120" align="center">제목</td>
						<td width="480" colspan="3">
							<input type="text" name="subject" value="<%= board.getSubject() %>" size="60" required/>
						</td>
					</tr>
					<tr height="40">
						<td width="120" align="center">패스워드</td>
						<td width="480" colspan="3">
							<input type="text" name="pw" size="60" required/>
						</td>
					</tr>
					<tr height="40">
						<td width="120" align="center">글내용</td>
						<td width="480" colspan="3"><textarea name="content" rows="10" cols="60" required><%= board.getContent() %></textarea></td>
					</tr>
					<tr height="40">
						<td colspan="4" align="center">
	<input type="hidden" name="num" value="<%= num %>" />
							<input type="submit" value="글수정" /> &nbsp;&nbsp;
							<input type="button" value="목록보기" onclick="location.href='board_list.jsp'" />
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>