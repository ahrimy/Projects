<%@ page import="board.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board</title>
</head>
<body>
	<%
		request.setCharacterEncoding("utf-8");
	%>

	<div>
		<span style="float: left"><a href="board_main.jsp">Home</a></span> 
		<%
			if(session.getAttribute("logID")==null){
		%>
			<span style="float: right"><a href="user_join.jsp">Join</a>&nbsp;&nbsp; <a href="user_signIn.jsp">Sign In</a></span>
			<%}else{ 

			%>
			<span style="float: right"><%=session.getAttribute("logID")%>&nbsp;is signed in&nbsp; <a href="user_myPage.jsp">MyPage</a>&nbsp;&nbsp;<a href="user_logOutPro.jsp">LogOut</a></span>
			<%}			%>
			
	</div>
	<br>
	<h1 align="Center">Board</h1>

</body>
</html>