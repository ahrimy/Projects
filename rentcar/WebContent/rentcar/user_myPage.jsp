<%@page import="rental.db.UserDAO"%>
<%@page import="rental.db.UserDTO"%>
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
		<h2>[My Page]</h2>
		<form method="post" action="user_myPagePro.jsp">
			<table border="1">
				<tr height="40">
					<td align="center" width="150">Number</td>
					<td><%=session.getAttribute("log")%></td>
				</tr>
				<%
					String id = "";
				String email = "";
				String pw = "";
				String name = "";
				String tel = "";
				if (session.getAttribute("log") != null) {
					UserDTO user = UserDAO.getInstance().getOneUser((int) session.getAttribute("log"));
					id = user.getUser_id();
					email = user.getUser_email();
					pw = user.getUser_pw();
					name = user.getUser_name();
					tel = user.getUser_tel();
				}
				%>
				<tr height="40">
					<td align="center" width="150">ID</td>
					<td><%=id%></td>
				</tr>
				<tr height="40">
					<td align="center" width="150">PW</td>
					<td width="450"><input type="password" name="user_pw"
						value="<%=pw%>" size="60" required /></td>
				</tr>
				<tr height="40">
					<td align="center" width="150">Name</td>
					<td width="450"><input type="text" name="user_name"
						value="<%=name%>" size="60" required /></td>
				</tr>
				<tr height="40">
					<td align="center" width="150">E-mail</td>
					<td width="450"><input type="email" name="user_email"
						value="<%=email%>" size="60" required /></td>
				</tr>
				<tr height="40">
					<td align="center" width="150">TEL</td>
					<td width="450"><input type="tel" name="user_tel"
						value="<%=tel%>" size="60" required /></td>
				</tr>
				<tr height="40">
					<td align="center" width="150" colspan="2"><input
						type="hidden" name="user_id" value="<%=id%>" /> <input
						type="hidden" name="user_num"
						value="<%=session.getAttribute("log")%>" /> 
						<input type="button"
						value="Rental Reservation"onclick="location.href='user_rentalInfo.jsp'"/>&nbsp;&nbsp;
						<input type="submit" value="Edit" />
						 </td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>