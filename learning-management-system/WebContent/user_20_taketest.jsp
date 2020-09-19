<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>user_20_taketest.jsp</title>
</head>
<body>
	<div align="center">
		<h4>${test.getTestTitle() }</h4>
		<form method="post" action="/jsp_lms_mvc2/user/taketestPro.do">
			<table border="1">

				<c:forEach var="question" items="${questions}">

					<tr>
						<td width="500"><b>${question.getQuestion() }</b>
							<hr> <c:if test="${question.getQueType() eq 'radio' }">
								<div align="left">
									<c:set var="option"
										value="${fn:split(question.getQueOpts(),'/')}" />
									<c:set var="size" value="${ option[0]}" />
									<c:forEach var="i" begin="1" end="${size }">

										<input type="radio" name="que${question.getQueNum()}"
											value="opt${i }" required /> 
							${option[i]}
							<br />
									</c:forEach>
								</div>


							</c:if> <c:if test="${question.getQueType() eq 'checkbox' }">
								<div align="left">
									<c:set var="option"
										value="${fn:split(question.getQueOpts(),'/')}" />
									<c:set var="size" value="${option[0]}" />
									<c:forEach var="i" begin="1" end="${size }">

										<input type="checkbox" name="que${question.getQueNum()}"
											value="opt${i}" /> 
						${option[i]}
							<br />

									</c:forEach>
								</div>
							</c:if> <c:if test="${question.getQueType() eq 'text' }">
								<input type="text" name="que${question.getQueNum()}" />
							</c:if></td>
					</tr>

				</c:forEach>
				<tr>
					<td><input type="hidden" name="testNum"
						value="${test.getTestNum()}" /> <input type="submit"
						value="Submit"></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>