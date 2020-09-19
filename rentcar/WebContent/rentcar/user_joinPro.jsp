<%@page import="java.sql.Timestamp"%>
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
		Timestamp joindate = new Timestamp(System.currentTimeMillis());
		user.setUser_joindate(joindate);
			boolean check = UserDAO.getInstance().joinUser(user);
		if (check) {
	%>
	<script>
		alert("이미 존재하는 아이디 입니다.");
		history.go(-1);
	</script>
	<%
		} else {
		response.sendRedirect("_main.jsp");
	}
	%>
</body>
</html>