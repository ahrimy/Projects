<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%--JSP 주석
	1) 404에러 : 페이지가 없을 때
	2) 500에러 : 자바 문법 에러
	 --%>
	<%
		//자바 주석
		String name = "홍길동";
	%>
	<%
		//자바 주석
		name = "길동";
	%>
	<%
		//자바 주석
		//전부다 하나의 메인함수	
	%>
	<%= name %>
</body>
</html>