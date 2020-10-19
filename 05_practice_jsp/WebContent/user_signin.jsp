<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>user_signin.jsp</title>
</head>
<body>
	<jsp:include page="_top.jsp" />
		<div align="center">
			<h2>Sign in</h2>
			<form method="post" action="User_signinPro.do">
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
						<td align="center" width="150" colspan="2">
							<input type="submit" value="Sign in" /> &nbsp;&nbsp;
						</td>
					</tr>
				</table>
			</form>
		</div>
</body>
</html>