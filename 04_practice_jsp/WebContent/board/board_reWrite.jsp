<%@ page import="board.db.BoardDAO"%>
<%@ page import="board.db.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
	<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>10_board_reWrite.jsp</title>
	</head>
	<body>
	<jsp:include page="_top.jsp" />
		<%
			int num = Integer.parseInt(request.getParameter("num"));
		
			BoardDTO board = BoardDAO.getInstance().getOneUpdateBoard(num);
			
		%>
		<div align="center">
			<h2>답변글 입력하기</h2>
			<form method="post" action="board_reWritePro.jsp">
				<table border="1">
					<tr height="40">
						<td width="150" align="center">작성자</td>
						<td width="450">
							<!-- <input type="text" name="writer" size="60" /> -->
							<%=session.getAttribute("logID") %>
						</td>
					</tr>
					<tr height="40">
						<td width="150" align="center">제목</td>
						<td width="450">
							<input type="text" name="subject" value="[답변]" size="60"required>
						</td>
					</tr>
					<tr height="40">
						<td width="150" align="center">글내용</td>
						<td width="450"><textarea rows="10" cols="60" name="content"required></textarea></td>
					</tr>
					<tr height="40">
						<td align="center" colspan="2">
						<input type="hidden" name="id" value="<%=session.getAttribute("logID") %>"/>
							<input type="hidden" name="num" value="<%= num %>" />
							<input type="hidden" name="ref" value="<%= board.getRef() %>" />
							<input type="hidden" name="restep" value="<%= board.getRestep() %>" />
							<input type="hidden" name="relevel" value="<%= board.getRelevel() %>" />
							<input type="submit" value="답글쓰기완료" />&nbsp;&nbsp;
							<input type="reset" value="취소" />
							<input type="button" value="목록보기" onclick="location.href='board_list.jsp'" />
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>











