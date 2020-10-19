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
			if(session.getAttribute("logID")==null){
		%>
			<span style="float: right"><a href="member_join.jsp">Join</a>&nbsp;&nbsp; <a href="member_signIn.jsp">Sign In</a></span>
			<%}else{ 

			%>
			<span style="float: right"><%=session.getAttribute("logID")%>&nbsp;is signed in&nbsp; <a href="member_myPage.jsp">MyPage</a>&nbsp;&nbsp;<a href="member_signOutPro.jsp">LogOut</a></span>
			<%}			%>
			
	</div>
	<br>
	<h1 align="Center">Board</h1>

</body>
</html>