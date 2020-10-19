<%@page import="user.UserDAO"%>
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

	<jsp:useBean id="user" class="user.User">
		<jsp:setProperty name="user" property="*" />
	</jsp:useBean>

	<%
		boolean check = UserDAO.getInstance().join(user);
	if (check) {
	%>
	<script>
		alert("이미 존재하는 아이디 입니다.");
		history.go(-1);
	</script>
	<%
		} else {
		response.sendRedirect("board_main.jsp");
	}
	%>
</body>
</html>