<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>board_info.jsp</title>
</head>
<body>
	<jsp:include page="_top.jsp" />
	<div align="center">
		<h2>Board</h2>
		<table border="1">
			<tr height="40">
				<td align="center" width="120">No.</td>
				<td align="center" width="180">${board.getBoard_num() }</td>
				<td align="center" width="120">Read</td>
				<td align="center" width="180">${  board.getBoard_rcount() }</td>
			</tr>
			<tr height="40">
				<td align="center" width="120">ID</td>
				<td align="center" width="180">${ user.getUser_id() }</td>
				<td align="center" width="120">Date</td>
				<td align="center" width="180">${ board.getBoard_regdate() }</td>
			</tr>
			<tr height="40">
				<td align="center" width="120">E-mail</td>
				<td align="center" colspan="3">${user.getUser_email()}</td>
			</tr>
			<tr height="40">
				<td align="center" width="120">Subject</td>
				<td align="center" colspan="3">${ board.getBoard_subject()}</td>
			</tr>
			<tr height="40">
				<td align="center" width="120">Content</td>
				<td align="center" colspan="3">${ board.getBoard_content()}</td>
			</tr>
			<tr height="40">
				<td align="center" colspan="4"><c:if
						test="${null ne sessionScope.log }">
						<input type="button" value="Rewrite"
							onclick="location.href='Board_rewrite.do?board_num=${board.getBoard_num() }'" />
					</c:if> <c:if test="${user.getUser_num() eq sessionScope.log }">
						<input type="button" value="Edit"
							onclick="location.href='Board_update.do?board_num=${board.getBoard_num() }'" />
						<input type="button" value="Delete"
							onclick="location.href='Board_delete.do?board_num=${board.getBoard_num() }'" />
					</c:if> 
					<input type="button" value="List"
					onclick="location.href='Board_list.do'" /></td>
			</tr>
		</table>
	</div>
</body>
</html>











