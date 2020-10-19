<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="_top.jsp" />
		<div align="center">
			<h2>로그인</h2>
			<form method="post" action="member_signInPro.jsp">
				<table border="1">
					<tr height="40">
						<td align="center" width="150">ID</td>
						<td width="450"><input type="text" name="id"  size="60" required/></td>
					</tr>
					<tr height="40">
						<td align="center" width="150">PW</td>
						<td width="450"><input type="password" name="pw"  size="60" required/></td>
					</tr>
					<tr height="40">
						<td align="center" width="150" colspan="2">
							<input type="submit" value="로그인" /> &nbsp;&nbsp;
							<input type="button" value="전체게시글보기" onclick="location.href='board_list.jsp'" />
						</td>
					</tr>
				</table>
			</form>
		</div>
</body>
</html>