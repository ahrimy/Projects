
<%@ page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>board_list.jsp</title>
</head>

<body>
	<jsp:include page="_top.jsp" />
	<hr>
	<div align="right">
		<form method="post" action="Board_pagecountPro.do">
			<select name="pageSize">
			<option value=""disabled selected>--select--</option>
				<option value="5">5</option>
				<option value="10">10</option>
				<option value="15">15</option>
				<option value="20">20</option>
				<option value="30">30</option>
				<option value="40">40</option>
				<option value="50">50</option>
			</select> <input type="submit" value="Page Unit" />
		</form>
	</div>
	<div align="center">
		<h2>Board List[Total:${count }]</h2>
		<form>
			<table border="1">
				<tr height="40">
					<td align="right" colspan="5"><input type="button" value="Write"
						onclick="location.href='Board_write.do'" /></td>
				</tr>
				<tr height="40">
					<td width="50" align="center">No.</td>
					<td width="320" align="center">Subject</td>
					<td width="150" align="center">ID</td>	
					<td width="150" align="center">Date</td>
					<td width="50" align="center">Read</td>
				</tr>
				<c:set var="number" value="${number}" />
				<c:set var="cnt" value="${0}"/>
				<c:forEach var="board" items="${boardList}">
					<tr height="40">
						<td width="50" align="center">${number}</td>
						<td width="100"><c:if test="${board.getRestep() gt 1}">
								<c:set var="restep" value="${board.getRestep()}"/>
								<c:forEach var="j" begin="1" end="${( restep-1) * 5}">
								&nbsp;
								</c:forEach>
							</c:if> <a href="Board_info.do?board_num=${board.getBoard_num()}">${board.getBoard_subject()}</a>
						</td>
						<td width="150" align="center">${IDs.get(cnt)}</td>
						<td width="150" align="center">${board.getBoard_regdate()}</td>
						<td width="50" align="center">${board.getBoard_rcount()}</td>
					</tr>
					<c:set var="cnt" value="${cnt+1 }"/>
					<c:set var="number" value="${number - 1}" />
				</c:forEach>
			</table>
		</form>


		<c:if test="${ count> 0}">
			<c:set var="pageCount"
				value="${ count / pageSize + (count % pageSize == 0 ? 0 : 1)}" />
			<fmt:parseNumber var="pageCount" value="${pageCount} "
				integerOnly="true"></fmt:parseNumber>
			<fmt:parseNumber var="result" value="${curPage/10} "
				integerOnly="true"></fmt:parseNumber>



			<c:if test="${ curPage % 10  ne 0 }">
				<c:set var="startPage" value="${(result) * 10 + 1 }"></c:set>
			</c:if>

			<c:if test="${curPage % 10 eq 0}">
				<c:set var="startPage" value="${(result-1) * 10 + 1 }"></c:set>
			</c:if>

			<!-- 화면에 보여질 페이지 숫자를 표현 -->
			<c:set var="endPage" value="${ startPage + 10 - 1 }"></c:set>
			<c:if test="${ endPage >  pageCount}">
				<c:set var="endPage" value="${ pageCount }"></c:set>
			</c:if>

			<!--  이전 링크를 갈지 파악 -->
			<c:if test="${startPage >10 }">
				<a href="Board_list.do?pageNum=${startPage-10}">[◀]</a>
			</c:if>

			<!-- 페이징 처리 -->
			<c:forEach var="i" begin="${startPage}" end="${endPage}">
				<a href="Board_list.do?pageNum=${i}">[${i}]</a>
			</c:forEach>

			<!-- 다음 -->
			<c:if test="${endPage < pageCount }">
				<a href="Board_list.do?pageNum=${startPage+10 }">[▶]</a>
			</c:if>

		</c:if>
	</div>
</body>
</html>






























