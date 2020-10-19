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

	<%
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		int log = MemberDAO.getInstance().signInMember(id,pw);
		if (log == -1) {
	%>
	<script>
		alert("존재하지 않는 아이디 입니다.");
		history.go(-1);
	</script>
	<%
		}else if(log == 0){
	%>
	<script>
		alert("비밀번호를 확인하세요");
		history.go(-1);
	</script>		
	<%
		}else {
		session.setAttribute("logID", id);
		response.sendRedirect("_main.jsp");
	}
	%>
</body>
</html>