<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Join</title>
</head>
<body>
	<div align="center">
		<h3>Join up</h3><br>
		회원가입 유형을 선택해주세요.<br><br>
		
		<form action="/jsp_lms_mvc2/user/joinForm.do" method="post">
			<input type="radio" value="student" name="memType">
			<label for="student">학생</label>
			<input type="radio" value="professor" name="memType">
			<label for="professor">교수</label><br><br>
			<input type="submit" value="회원가입">
		</form>
		
	</div>
</body>
</html>