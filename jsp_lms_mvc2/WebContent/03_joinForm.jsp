<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Join</title>
</head>
<body>
<%-- 	<% String type = (String)request.getAttribute("type"); %> --%>
<%-- 	<h2>type : <%= type%></h2><br><br> --%>
	<c:choose>
		<c:when test="${ 'student' eq type }">
			<h2>학생등록</h2><br><br>
			<form action="/jsp_lms_mvc2/user/joinStuPro.do" method="post">
				<label for="grade">학년 :</label> <select id="grade" name="grade">
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
				</select> <br> 
				<label for="pw">비밀번호 : </label> 
				<input type="password" id="pw" name="pw" placeholder="6자리 이상 알파벳과 숫자 포함"> <br>
				<label for="name">이름 : </label> 
				<input type="text" id="name" name="name"> <br> <label for="birth">생년월일 : </label> 
				<input type="text" id="birth" name="birth" placeholder="YYMMDD 숫자만 입력"><br> 
				<label for="tel">연락처 : </label> 
				<input type="text" id="tel" name="tel" placeholder="'-' 제외한 숫자만 입력"> <br>
				<label for="email">이메일 : </label> 
				<input type="email" id="email" name="email"> <br> 
				<label for="adds">주소 : </label> 
				<input type="text" id="adds" name="adds"> <br> 
				<label for="major">전공 : </label> 
				<input type="text" id="major" name="major"> <br>
				<input type="submit" value="회원가입"> <br>
			</form>
		</c:when>
		<c:when test="${ 'professor' eq type }">
			<h2>교수등록</h2><br><br>
			<form action="/jsp_lms_mvc2/user/joinPrfPro.do" method="post">
				<label for="pw">비밀번호 : </label> 
				<input type="password" id="pw" name="pw" placeholder="6자리 이상 알파벳과 숫자 포함"> <br> 
				<label for="name">이름 : </label> 
				<input type="text" id="name" name="name"><br> 
				<label for="birth">생년월일 : </label> 
				<input type="text" id="birth" name="birth" placeholder="YYMMDD 숫자만 입력"> <br>
				<label for="tel">연락처 : </label> 
				<input type="text" id="tel" name="tel" placeholder="'-' 제외한 숫자만 입력"> <br> 
				<label for="email">이메일 : </label> 
				<input type="email" id="email" name="email"> <br> 
				<label for="adds">주소 : </label> 
				<input type="text" id="adds" name="adds"> <br>
				<input type="submit" value="회원가입"> <br>
			</form>
		</c:when>
		<c:otherwise>
		WARNNING : 잘못된 접근입니다.
	</c:otherwise>
	</c:choose>
</body>
</html>