<%@page import="rental.db.UserDAO"%>
<%@page import="rental.db.UserDTO"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
	<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>board_write.jsp</title>
	</head>
	<body>
	<%
		if(session.getAttribute("log")==null){
			%>
			<script>
			alert("로그인 후에 작성 가능합니다");
			history.go(-1);
		</script>	<%
			

		}
	%>
	<jsp:include page="_top.jsp" />
		<div align="center">
			<h2>Write a board</h2>
			<form method="post" action="board_writePro.jsp">
				<table border="1">
					<tr height="40">
					<%					
					String id = "";
					if (session.getAttribute("log") != null) {
						UserDTO user = UserDAO.getInstance().getOneUser((int) session.getAttribute("log"));
						id = user.getUser_id();
					} %>
						<td align="center" width="150">ID</td>
						<!--<td width="450"><input type="text" name="writer"  size="60" /></td>-->
						<td><%=id %></td>
					</tr>
					<tr height="40">
						<td align="center" width="150">Subject</td>
						<td width="450"><input type="text" name="board_subject" value="[Main]" size="60" required/></td>
					</tr>
					<tr height="40">
						<td align="center" width="150">Content</td>
						<td width="450"><textarea name="board_content" rows="10" cols="50" required ></textarea></td>
					</tr>
					<tr height="40">
						<td align="center" width="150" colspan="2">
							<input type="hidden" name="user_num" value="<%=session.getAttribute("log") %>"/>
							<input type="submit" value="Post" /> &nbsp;&nbsp;
							<input type="reset" value="Reset" /> &nbsp;&nbsp;
							<input type="button" value="List" onclick="location.href='board_list.jsp'" />
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>