<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>user_mypage.jsp</title>
</head>
<body>
<jsp:include page="_top.jsp" />
		<div align="center">
			<h2>My Page</h2>
			<form method="post" action="User_mypagePro.do">
				<table border="1">
					<tr height="40">
						<td align="center" width="150">ID</td>
						<td>${user_id }</td>
					</tr>
					<tr height="40">
						<td align="center" width="150">Current PW</td>
						<td width="450"><input type="password" name="current_user_pw" value = "${user_pw}" size="60" required/></td>
					</tr>
										<tr height="40">
						<td align="center" width="150">New PW</td>
						<td width="450"><input type="password" name="new_user_pw" size="60"/></td>
					</tr>
									<tr height="40">
						<td align="center" width="150">Name</td>
						<td width="450"><input type="text" name="user_name"  value = "${user_name }"size="60" required/></td>
					</tr>
					<tr height="40">
						<td align="center" width="150">E-mail</td>
						<td width="450"><input type="email" name="user_email" value = "${user_email }"size="60" required/></td>
					</tr>
					<tr height="40">
						<td align="center" width="150" colspan="2">
							<input type="button" value="Withdraw" onclick = "location.href='User_withdraw.do'"/>&nbsp;&nbsp;
							<input type="submit" value="Save the Changes" /> 
						</td>
					</tr>
				</table>
			</form>
		</div>
</body>
</html>