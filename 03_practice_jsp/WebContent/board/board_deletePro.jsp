<%@ page import="board.BoardDAO"%>
<%@ page import="user.UserDAO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
	<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>board_deletePro.jsp</title>
	</head>
	<body>
		<%
			int num = Integer.parseInt(request.getParameter("num"));
			String password = request.getParameter("password");
			
			int index = UserDAO.getInstance().findUser((String)session.getAttribute("logID"));
			boolean check = UserDAO.getInstance().checkPassword(index, password);
			if(check){
				BoardDAO.getInstance().deleteBoard(num);
				response.sendRedirect("board_list.jsp");
			}else{
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