<%@page import="rental.db.UserDAO"%>
<%@page import="rental.db.UserDTO"%>
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
		request.setCharacterEncoding("utf-8");
	%>

	<div>
		<span style="float: left"><a href="_main.jsp">Home</a></span> 
		<%
			if(session.getAttribute("log")==null){
		%>
			<span style="float: right"><a href="user_join.jsp">Join</a>&nbsp;&nbsp; <a href="user_signIn.jsp">Sign In</a></span>
			<%}else{ 
				String id = "";
			if (session.getAttribute("log") != null) {
				UserDTO user = UserDAO.getInstance().getOneUser((int) session.getAttribute("log"));
				id = user.getUser_id();

			}
			%>
			<span style="float: right"><%=id%>&nbsp;is signed in&nbsp; <a href="user_myPage.jsp">MyPage</a>&nbsp;&nbsp;<a href="user_signOutPro.jsp">LogOut</a></span>
			<%}			%>
			
	</div>
	<br>
	<h1 align="Center">Car Rental Service</h1>

</body>
</html>