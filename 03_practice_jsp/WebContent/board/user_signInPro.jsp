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
		String log = UserDAO.getInstance().signIn(user);
	if (log == null) {
	%>
	<script>
		alert("아이디 비밀번호를 확인하세요");
		history.go(-1);
	</script>
	<%
		} else {
		session.setAttribute("logID", log);
		response.sendRedirect("board_main.jsp");
	}
	%>
</body>
</html>