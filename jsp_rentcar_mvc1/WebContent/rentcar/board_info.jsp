<%@ page import="rental.db.BoardDTO"%>
<%@ page import="rental.db.BoardDAO"%>
<%@ page import="rental.db.UserDTO"%>
<%@ page import="rental.db.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>board_info.jsp</title>
</head>
<body>
	<jsp:include page="_top.jsp" />
	<%
		int num = Integer.parseInt(request.getParameter("num"));
	BoardDTO board = BoardDAO.getInstance().getOneBoard(num);
	%>
	<div align="center">
		<h2>게시글 보기</h2>
		<table border="1">
			<tr height="40">
				<%
					UserDTO user = UserDAO.getInstance().getOneUser(board.getUser_num());
				String id = user.getUser_id();
				String email = user.getUser_email();
				String pw = user.getUser_pw();
				String name = user.getUser_name();
				String tel = user.getUser_tel();
				%>
				<td align="center" width="120">No.</td>
				<td align="center" width="180"><%=board.getBoard_num()%></td>
				<td align="center" width="120">Count</td>
				<td align="center" width="180"><%=board.getBoard_readcount()%></td>
			</tr>
			<tr height="40">
				<td align="center" width="120">ID</td>
				<td align="center" width="180"><%=id%></td>
				<td align="center" width="120">Date</td>
				<td align="center" width="180"><%=board.getBoard_regdate()%></td>
			</tr>
			<tr height="40">
				<td align="center" width="120">E-mail</td>

				<td align="center" colspan="3"><%=email%></td>
			</tr>
			<tr height="40">
				<td align="center" width="120">Subject</td>
				<td align="center" colspan="3"><%=board.getBoard_subject()%></td>
			</tr>
			<tr height="40">
				<td align="center" width="120">Content</td>
				<td align="center" colspan="3"><%=board.getBoard_content()%></td>
			</tr>
			<tr height="40">
				<td align="center" colspan="4">
					<%
						if (session.getAttribute("log") != null) {
					%> <input type="button" value="Rewirte"
					onclick="location.href='board_reWrite.jsp?num=<%=num%>'" /> <%
 	if ((int) session.getAttribute("log") == board.getUser_num()) {
 %> <input type="button" value="Edit"
					onclick="location.href='board_update.jsp?num=<%=num%>'" /> <input
					type="button" value="Delete"
					onclick="location.href='board_delete.jsp?num=<%=num%>'" /> <%
 	}
 }
 %> <input type="button" value="목록보기"
					onclick="location.href='board_list.jsp'" />
				</td>
			</tr>
		</table>
	</div>
</body>
</html>











