<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri ="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>formPro.jsp</title>
</head>
<body>

	<%-- EL태그(Expression Language)태그 --%>
	<h2>${name}</h2>
	<h2>${grade}</h2>
	
	<%-- JSTL(JSP Standard Tag Library)태그 
		 (1)WEB_INF>lib>jstl.jar 추가해야 위 태그를 사용할 수 있다.
		 (2)<%@ taglib prefix = "c" uri ="http://java.sun.com/jsp/jstl/core" %>
	--%>
	<c:if test="${gender eq 'men'}">
	<h2>남성</h2>
	</c:if>
	<c:if test="${gender eq 'women' }">
	<h2>여성</h2>
	</c:if>
	<c:forEach var="hobby" items="${hobbies}">
	<h2>${hobby}</h2>
	</c:forEach>
</body>
</html>