<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import = "java.util.Random" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>import 속성</title>
</head>
<body>
<h2>import 속성</h2>
<%
	Random ran = new Random();
	String[] str = {"JSP","JAVA","Android","HTML5"};
	int i = ran.nextInt(4);
%>
<br><br>
<%=str[i] %>가(이) 재미있다. 

</body>
</html>