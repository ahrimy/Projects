<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="user.UserDAO"%>
<!DOCTYPE html>
	<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>board_write.jsp</title>
	</head>
	<body>
	<%
		if(session.getAttribute("logID")==null){
			session.setAttribute("writable",false);
			%>
			<script>
			alert("로그인 후에 작성 가능합니다");
			history.go(-1);
		</script>	<%
			

		}
	%>
	<jsp:include page="board_top.jsp" />
		<div align="center">
			<h2>게시글 쓰기</h2>
			<form method="post" action="board_writePro.jsp">
				<table border="1">
					<tr height="40">
						<td align="center" width="150">작성자</td>
						<!--<td width="450"><input type="text" name="writer"  size="60" /></td>-->
						<td><%=session.getAttribute("logID") %></td>
					</tr>
					<tr height="40">
						<td align="center" width="150">제목</td>
						<td width="450"><input type="text" name="subject" value="[본문]" size="60" required/></td>
					</tr>
					<tr height="40">
						<td align="center" width="150">이메일</td>
						<%
						String email = "";
						if(session.getAttribute("logID")!=null){
							int index = UserDAO.getInstance().findUser((String)session.getAttribute("logID"));	
							 email = UserDAO.getInstance().getEmail(index);
						}
						%>
						<td width="450"><%= email%></td>
					</tr>
					<!--  <tr height="40">
						<td align="center" width="150">비밀번호</td>
						<td width="450"><input type="password" name="password" size="60" /></td>
					</tr> -->
					<tr height="40">
						<td align="center" width="150">글내용</td>
						<td width="450"><textarea name="content" rows="10" cols="50" required ></textarea></td>
					</tr>
					<tr height="40">
						<td align="center" width="150" colspan="2">
							<input type="hidden" name="writer" value="<%=session.getAttribute("logID") %>"/>
							<input type="submit" value="글쓰기" /> &nbsp;&nbsp;
							<input type="reset" value="다시작성" /> &nbsp;&nbsp;
							<input type="button" value="전체게시글보기" onclick="location.href='board_list.jsp'" />
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>