<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>user_join.jsp</title>
</head>
<body>
<jsp:include page="_top.jsp" />
		<div align="center">
			<h2>Join</h2>
			<form method="post" action="User_joinPro.do">
				<table border="1">
					<tr height="40">
						<td align="center" width="150">ID</td>
						<td width="450"><input type="text" name="user_id"  size="60" required/></td>
					</tr>
					<tr height="40">
						<td align="center" width="150">PW</td>
						<td width="450"><input type="password" name="user_pw"  size="60" required/></td>
					</tr>
									<tr height="40">
						<td align="center" width="150">Name</td>
						<td width="450"><input type="text" name="user_name"  size="60" required/></td>
					</tr>
					<tr height="40">
						<td align="center" width="150">E-mail</td>
						<td width="450"><input type="email" name="user_email" size="60" required/></td>
					</tr>
					<tr height="40">
						<td align="center" width="150" colspan="2">
							<input type="submit" value="Join" /> &nbsp;&nbsp;
						</td>
					</tr>
				</table>
			</form>
		</div>
</body>
</html>