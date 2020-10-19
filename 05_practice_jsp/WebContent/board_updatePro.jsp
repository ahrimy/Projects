<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
	<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>07_board_updatePro.jsp</title>
	</head>
	<body>
	<c:if test="${ 0 eq check }">
		<script type="text/javascript">
			alert('Check the Password!');
			history.go(-1);
		</script>
	</c:if>


	<c:if test="${check ge 1 }">
		<script type="text/javascript">
			window.location.href = "Board_list.do";
		</script>
	</c:if>
	</body>
</html>