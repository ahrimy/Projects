<%@page import="board.db.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="_top.jsp" />

		
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