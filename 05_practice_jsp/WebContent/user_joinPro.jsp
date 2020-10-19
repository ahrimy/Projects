
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>user_joinPro.jsp</title>
</head>
<body>
	<c:if test="${check eq 0 }">
	<script>
		alert("This ID is existing");
		history.go(-1);
	</script>
	</c:if><c:if test="${check eq 1 }">
		<script type="text/javascript">
		window.location.href = "Main.do";
	</script></c:if>


</body>
</html>