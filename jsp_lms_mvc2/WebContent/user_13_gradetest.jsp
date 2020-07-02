<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>grade test</title>
</head>
<body>
	<div align="center">
<form method="post" action="/jsp_lms_mvc2/user/gradetestPro.do">
		<table border="1">
		<c:if test="${size eq 0 }">
			no result
		</c:if>
		<c:if test="${size gt 0 }">
			<c:forEach var="i" begin = "0" end="${size-1}">
				<tr>
					<td width="500"><b>${queList.get(i).getQuestion() }</b>
						<hr>
						Answer:${queList.get(i).getQueAnswer()}
						<br/><br/>Student:${answerList.get(i).getAnswer() }
						<br/><br/>
	
							Score:<input type="text" name="answer${answerList.get(i).getAnswerNum() }" required/>&nbsp; /&nbsp;${queList.get(i).getQueScore() }
						</td>
				</tr>
			</c:forEach>
			<tr>
				<td><input type="hidden" name="scoreNum"
					value="${answerList.get(0).getScoreNum()}" /> <input type="submit"
					value="Submit"></td>
			</tr>
			</c:if>
		</table>
	</form>
	</div>
</body>
</html>