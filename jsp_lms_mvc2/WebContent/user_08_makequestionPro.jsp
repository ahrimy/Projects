<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>makequestionPro</title>
</head>
<body>
	<c:if test="${check eq 0 }">

		<script type="text/javascript">
			window.location.href = "/jsp_lms_mvc2/user/makequestion.do?testNum=${testNum}";
		</script>

	</c:if>
	<c:if test="${check eq 1 }">
		<script type="text/javascript">
			window.location.href = "/jsp_lms_mvc2/user/classinfo.do?classNum=${sessionScope.classNum}";
		</script>
	</c:if>
</body>
</html>