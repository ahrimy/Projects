<%@page import="board.BoardDAO"%>
<%@page import="user.UserDAO"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
	<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>07_board_updatePro.jsp</title>
	</head>
	<body>
		<% request.setCharacterEncoding("UTF-8"); %>
		
		<%
			String subject = request.getParameter("subject");
			String content = request.getParameter("content");
		
			int num = Integer.parseInt(request.getParameter("num"));
			String password = request.getParameter("password");
			
			int index = UserDAO.getInstance().findUser((String)session.getAttribute("logID"));
			boolean check = UserDAO.getInstance().checkPassword(index, password);
			if(check){
				BoardDAO.getInstance().updateBoard(num, subject, content);
				response.sendRedirect("board_list.jsp");
			}else {
		%>
				<script>
					alert("비밀번호가 틀립니다. 다시 확인해주세요.");
					history.go(-1);
				</script>
		<%
			}
		%>
	</body>
</html>