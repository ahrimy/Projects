<%@page import="board.db.MemberDAO"%>
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

	<jsp:useBean id="member" class="board.db.MemberDTO">
		<jsp:setProperty name="member" property="*" />
	</jsp:useBean>

	<%
		MemberDAO.getInstance().updateMember(member);
		response.sendRedirect("_main.jsp");
	
	%>
</body>
</html>