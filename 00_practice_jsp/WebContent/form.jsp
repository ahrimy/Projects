<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>form.jsp</title>
</head>
<body>
	<form method="post" action="formPro.do">
		이름 : <input type="text" name="name" /> <br /> 
		취미: 운동<input
			type="checkbox" name="hobby" value="sports" /> 
			독서 <input
			type="checkbox" name="hobby" value="reading" /> 
			공부 <input
			type="checkbox" name="hobby" value="study" /> <br /> 
		성별: 남성<input
			type="radio" name="gender" value="men" /> 
			여성<input 
			type="radio" name="gender" value="women" /> <br /> 
		학년 <select name="grade">
			<option value="1">1학년</option>
			<option value="2">2학년</option>
			<option value="3">3학년</option>
			</select> 
		<input type="submit" value="가입하기" />
	</form>
</body>
</html>