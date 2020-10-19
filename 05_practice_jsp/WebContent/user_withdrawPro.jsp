<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>user_withdrawPro.jsp</title>
</head>
<body>

	<c:if test="${ 0 eq check }">
		<script type="text/javascript">
			alert('Check the password!');
			history.go(-1);
		</script>
	</c:if>
	<c:if test="${ -1 eq check }">
		<script type="text/javascript">
			alert('Error!');
			history.go(-1);
		</script>
	</c:if>
	
<c:if test="${check ge 1 }">
		<script type="text/javascript">
		window.location.href = "User_signoutPro.do";
	</script></c:if>
</body>
</html>