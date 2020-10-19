<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@page import="user.UserDAO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
		request.setCharacterEncoding("UTF-8");
	%>

	<jsp:useBean id="user" class="user.User">
		<jsp:setProperty name="user" property="*" />
	</jsp:useBean>

	<%
		UserDAO.getInstance().update(user);
		response.sendRedirect("board_main.jsp");
	
	%>
</body>
</html>