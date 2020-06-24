<%@page import="rental.db.UserDAO"%>
<%@page import="rental.db.BoardDAO"%>
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
		
	
			int user_num = Integer.parseInt(request.getParameter("user_num"));
			String user_pw = request.getParameter("user_pw");
			boolean retry = UserDAO.getInstance().checkPW(user_num, user_pw);
			if(retry){
				%>
				<script>
					alert("비밀번호가 틀립니다. 다시 확인해주세요.");
					history.go(-1);
				</script>		
		<%



			}else{
				int board_num = Integer.parseInt(request.getParameter("board_num"));
				BoardDAO.getInstance().updateBoard(board_num,subject,content);
				response.sendRedirect("board_list.jsp");
			}
		%>	
	</body>
</html>