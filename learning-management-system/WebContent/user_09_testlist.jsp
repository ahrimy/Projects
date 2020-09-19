<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>user_18_testlist.jsp</title>
</head>
<body>
	<div align="center">
		<form>
			<c:set var="number" value="${1}" />

			<table border="1">
				<c:if test="${size eq 0 }">
			no test
			</c:if>
				<c:if test="${size gt 0 }">
					<tr>
						<td width="10" align="center">No.</td>
						<td width="500">My Test</td>
						<c:if test="${10 eq sessionScope.type}">
							<td width="200">Status</td>
						</c:if>
					</tr>
					<c:forEach var="i" begin="0" end="${size-1 }">
						<tr>
							<td width="10" align="center">${number}</td>
							<td width="500"><a
								href="/jsp_lms_mvc2/user/testinfo.do?testNum=${tests.get(i).getTestNum() }">${tests.get(i).getTestTitle() }</a></td>
							<c:if test="${10 eq sessionScope.type}">
								<td width="200">${statusList.get(i) }</td>
							</c:if>
							<c:set var="number" value="${number+1}" />
						</tr>
					</c:forEach>


				</c:if>
			</table>
		</form>

		<%--
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
				<a
					href="/jsp_lms_mvc2/user/testlist.do?pageNum=${startPage-10}">[◀]</a>
			</c:if>

			<!-- 페이징 처리 -->
			<c:forEach var="i" begin="${startPage}" end="${endPage}">
				<a href="/jsp_lms_mvc2/user/testlist.do?pageNum=${i}">[${i}]</a>
			</c:forEach>

			<!-- 다음 -->
			<c:if test="${endPage < pageCount }">
				<a
					href="/jsp_lms_mvc2/user/testlist.do?pageNum=${startPage+10 }">[▶]</a>
			</c:if>

		</c:if>--%>
	</div>
</body>
</html>