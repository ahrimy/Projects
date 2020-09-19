<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${ 1 eq check && 10 == type}">
		<script type="text/javascript">
			alert('학생 ${sessionScope.name}님, 환영합니다!');
			location.href='/jsp_lms_mvc2/user/main.do';
		</script>
	</c:if>
	<c:if test="${ 1 eq check && 20 == type}">
		<script type="text/javascript">
			alert('${sessionScope.name} 교수님, 환영합니다!');
			location.href='/jsp_lms_mvc2/user/main.do';
		</script>
	</c:if>
	<c:if test="${ -1 eq check }">
		<script type="text/javascript">
			alert('정확한 정보를 입력해주세요.');
			history.go(-1);
		</script>
	</c:if>

</body>
</html>