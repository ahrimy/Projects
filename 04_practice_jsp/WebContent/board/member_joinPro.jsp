<%@page import="java.sql.Timestamp"%>
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
	Timestamp joindate = new Timestamp(System.currentTimeMillis());
	member.setJoindate(joindate);
		boolean check = MemberDAO.getInstance().joinMemebr(member);
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