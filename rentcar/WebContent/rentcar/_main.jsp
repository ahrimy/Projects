<%@page import="rental.db.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>_main</title>
</head>
<body>
	<jsp:include page="_top.jsp" />


	<div align="center">
		<h2>[Main Menu]</h2>
		<p>
			<a href="reserve_selectCategory.jsp">Reserve Car</a>
		</p>		
		<p>
			<a href="board_list.jsp">Board</a>
		</p>

	</div>
</body>
</html>