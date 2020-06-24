<%@page import="rental.db.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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

	<jsp:useBean id="user" class="rental.db.UserDTO">
		<jsp:setProperty name="user" property="*" />
	</jsp:useBean>

	<%
		UserDAO.getInstance().updateUser(user);
			response.sendRedirect("_main.jsp");
	%>
</body>
</html>