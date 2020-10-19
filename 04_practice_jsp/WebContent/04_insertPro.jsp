<%@page import="member.db.MemberDAO"%>
<%@page import="java.sql.Timestamp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>04_insertPro.jsp</title>
</head>
<body>
	<%-- post 방식에 관한 한글 인코딩 처리 --%>
	<% request.setCharacterEncoding("UTF-8"); %>
	<jsp:useBean id="member" class="member.db.MemberDTO">
		<jsp:setProperty name="member" property="*"/>
	</jsp:useBean>
	
	<%-- 가입일자는 입력받은 데이터가 아니므로 직접 값을 초기화 해줘야 한다 --%>
	<%
		Timestamp joindate = new Timestamp(System.currentTimeMillis());
		member.setJoindate(joindate);
		
		//MemberDAO 가입 메서드 호출
		MemberDAO.getInstance().joinMember(member);
	%>
</body>
</html>