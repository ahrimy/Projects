<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${ 1 eq check }">
		<script type="text/javascript">
			alert('${ name }님 학번은 ${ stuCode} 입니다. 환영합니다!');
			location.href = '/jsp_lms_mvc2/user/main.do';
		</script>
	</c:if>
	<c:if test="${ -1 eq check }">
		<script type="text/javascript">	
			alert('이미 등록된 학생입니다.');
			history.go(-1);
		</script>
	</c:if>
</body>
</html>