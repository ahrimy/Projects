<%@ page import="board.BoardDAO"%>
<%@ page import="user.UserDAO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head> 
<body>
	<jsp:include page="board_top.jsp" />
	
	<%
		// 웹 어플리케이션 루트에 대한 로컬상의 실제 경로를 얻는다.
		String path = application.getRealPath("/");
		BoardDAO.getInstance().filePath = path;
		System.out.println("메모장의 실제 위치 = " + path);

		BoardDAO.getInstance().loadData();
		BoardDAO.getInstance().sortBoard();
		
		UserDAO.getInstance().filePath = path;
		UserDAO.getInstance().loadData();
		%>
		
	<div align = "center">
	<h2>메인 페이지</h2>
	<p>
		<a href="board_write.jsp">게시판 글쓰기</a>
	</p>
	<p>
		<a href="board_list.jsp">전체 게시판 보기</a>
	</p>
</div>
</body>
</html>