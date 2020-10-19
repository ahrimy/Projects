<%@page import="board.db.MemberDAO"%>
<%@page import="board.db.MemberDTO"%>
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
			<h2>회원가입</h2>
			<form method="post" action="member_myPagePro.jsp">
				<table border="1">
					<tr height="40">
						<td align="center" width="150">ID</td>
						<td><%=session.getAttribute("logID") %></td>
					</tr>
					<%
						String email = "";
						String pw = "";
						String name = "";
						if(session.getAttribute("logID")!=null){
							MemberDTO member = MemberDAO.getInstance().getOneMember((String)session.getAttribute("logID"));	
							email = member.getEmail();
							pw = member.getPw();
							name = member.getName();
						}
						%>
					<tr height="40">
						<td align="center" width="150">PW</td>
						<td width="450"><input type="password" name="pw" value = "<%=pw %>" size="60" required/></td>
					</tr>
									<tr height="40">
						<td align="center" width="150">Name</td>
						<td width="450"><input type="text" name="name"  value = "<%=name %>"size="60" required/></td>
					</tr>
					<tr height="40">
						<td align="center" width="150">E-mail</td>
						<td width="450"><input type="email" name="email" value = "<%=email %>"size="60" required/></td>
					</tr>
					<tr height="40">
						<td align="center" width="150" colspan="2">
							<input type="hidden" name="id" value="<%=session.getAttribute("logID") %>"/>
							<input type="reset" value="다시입력"/>&nbsp;&nbsp;
							<input type="submit" value="수정" /> 
						</td>
					</tr>
				</table>
			</form>
		</div>
</body>
</html>