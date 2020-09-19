<%@page import="rental.db.UserDAO"%>
<%@page import="rental.db.UserDTO"%>
<%@ page import="rental.db.BoardDAO"%>
<%@ page import="rental.db.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
	<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>10_board_reWrite.jsp</title>
	</head>
	<body>
	<jsp:include page="_top.jsp" />
		<%
		if(session.getAttribute("log")==null){
			%>
			<script>
			alert("Only available when you are signed in");
			history.go(-1);
		</script>	<%
			

		}
	%>
		<%
			int num = Integer.parseInt(request.getParameter("num"));
		
			BoardDTO board = BoardDAO.getInstance().getOneUpdateBoard(num);
			
		%>
						<%
					String id = "";

				if (session.getAttribute("log") != null) {
					UserDTO user = UserDAO.getInstance().getOneUser((int) session.getAttribute("log"));
					id = user.getUser_id();

				}
				%>
		<div align="center">
			<h2>Rewirte a board</h2>
			<form method="post" action="board_reWritePro.jsp">
				<table border="1">
					<tr height="40">
						<td width="150" align="center">ID</td>
						<td width="450">
							<!-- <input type="text" name="writer" size="60" /> -->
							<%=id %>
						</td>
					</tr>
					<tr height="40">
						<td width="150" align="center">Subject</td>
						<td width="450">
							<input type="text" name="board_subject" value="[Re:]" size="60"required>
						</td>
					</tr>
					<tr height="40">
						<td width="150" align="center">Content</td>
						<td width="450"><textarea rows="10" cols="60" name="board_content"required></textarea></td>
					</tr>
					<tr height="40">
						<td align="center" colspan="2">
						<input type="hidden" name="board_num" value="<%=num %>"/>
							<input type="hidden" name="user_num" value="<%= session.getAttribute("log")%>" />
							<input type="hidden" name="board_ref" value="<%= board.getBoard_ref() %>" />
							<input type="hidden" name="board_restep" value="<%= board.getBoard_restep() %>" />
							<input type="hidden" name="board_relevel" value="<%= board.getBoard_relevel() %>" />
							<input type="submit" value="Post" />&nbsp;&nbsp;
							<input type="reset" value="Reset" />
							<input type="button" value="List" onclick="location.href='board_list.jsp'" />
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>











