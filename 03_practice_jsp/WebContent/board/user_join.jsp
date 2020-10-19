<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="board_top.jsp" />
		<div align="center">
			<h2>회원가입</h2>
			<form method="post" action="user_joinPro.jsp">
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
						<td align="center" width="150">Name</td>
						<td width="450"><input type="text" name="name"  size="60" required/></td>
					</tr>
					<tr height="40">
						<td align="center" width="150">E-mail</td>
						<td width="450"><input type="email" name="email" size="60" required/></td>
					</tr>
					<tr height="40">
						<td align="center" width="150" colspan="2">
							<input type="submit" value="회원가입" /> &nbsp;&nbsp;
						</td>
					</tr>
				</table>
			</form>
		</div>
</body>
</html>