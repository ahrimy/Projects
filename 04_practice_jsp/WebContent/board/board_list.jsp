<%@ page import="board.db.BoardDTO"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="board.db.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
	<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>board_list.jsp</title>
	</head>
	<%
		String strPageSize = (String)session.getAttribute("pageSize");
		if(strPageSize == null){
			strPageSize = "5";
		}
		int pageSize = Integer.parseInt(strPageSize);
		
		int count = BoardDAO.getInstance().getAllCount();
	
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null){
			pageNum = "1";
		}
		int curPage = Integer.parseInt(pageNum);
		
		int startRow = (curPage - 1) * pageSize;
		int endRow = startRow + pageSize;
		if(endRow > count) endRow = count;
		
		int number = count - (curPage - 1) * pageSize;
		
		ArrayList<BoardDTO> boardList = BoardDAO.getInstance().getAllBoard(startRow, endRow); 
	%>
	<body>
	<jsp:include page="_top.jsp" />
		<hr>
				<div align="right">
			<form method="post" action="board_pageCountPro.jsp">
				<select name="pageSize">
					<option value="5">5개씩</option>
					<option value="10">10개씩</option>
					<option value="15">15개씩</option>
					<option value="20">20개씩</option>
					<option value="30">30개씩</option>
					<option value="40">40개씩</option>
					<option value="50">50개씩</option>
				</select>
				<input type="submit" value="페이지 게시글 수 수정하기" />
			</form>
		</div>
		<div align="center">
			<h2>전체 게시글 보기[<%= count %>개]</h2>
			<form>
				<table border="1">
					<tr height="40">
						<td align="right" colspan="5">
							<input type="button" value="글쓰기" onclick="location.href='board_write.jsp'" />
						</td>
					</tr>
					<tr height="40">
						<td width="50" align="center">번호</td>
						<td width="320" align="center">제목</td>
						<td width="100" align="center">작성자</td>
						<td width="150" align="center">작성일</td>
						<td width="50" align="center">조회수</td>
					</tr>
					<%
					for(int i=0; i<boardList.size(); i++){
						BoardDTO board = boardList.get(i);
					%>
					<tr height="40">
						<td width="50" align="center"><%= number-- %></td>
						<td width="100">
						<% 
							if(board.getRestep() > 1){
								for(int j=0; j<(board.getRestep()-1)*5; j++){
						%>
								&nbsp;
						<%			
								}
							} 
						%>
							<a href="board_info.jsp?num=<%= board.getNum() %>"><%= board.getSubject() %></a>
						</td>
						<td width="320" align="center"><%= board.getId() %></td>
						<td width="150" align="center"><%= board.getRegdate() %></td>
						<td width="50" align="center"><%= board.getReadcount() %></td>
					</tr>
					<%
					}
					%>
				</table>
				
				<%-- 이하 페이징 소스 코드 --%>
				<p>
					<%
						if(count > 0){
							int size = 3;
							int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
							
							int startPageNum = 1;
							if(curPage % size != 0){
								startPageNum = (curPage / size) * size + 1;
							}else{
								startPageNum = ((curPage / size) - 1) * size + 1;
							}
							int endPageNum = startPageNum + size -1;
							if(endPageNum > pageCount) endPageNum = pageCount;
							
							if(startPageNum > size){
					%>
								<a href="board_list.jsp?pageNum=<%= startPageNum - size %>">[이전]</a>
					<%		
						}
						
							for(int i=startPageNum; i<=endPageNum; i++){
					%>
								<a href="board_list.jsp?pageNum=<%= i %>">[<%= i %>]</a>
					<%
							}
						
							if(endPageNum < pageCount){
					%>
								<a href="board_list.jsp?pageNum=<%= startPageNum + size %>">[이후]</a>
					<%
							}
						}
					%>
				</p>
			</form>
		</div>
	</body>
</html>






























