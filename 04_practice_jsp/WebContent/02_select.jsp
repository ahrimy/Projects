<%@page import="java.sql.Timestamp"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border = "1">
		<tr>
			<td>아이디</td>
			<td>패스워드</td>
			<td>가입일자</td>
		</tr>
	
	<%
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String url = "jdbc:mysql://localhost:3306/memberJDBC?serverTimezone=UTC";
		String dbId = "root";
		String dbPw = "root";
		try{
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url,dbId,dbPw);
			
			String sql = "SELECT * FROM member";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
		
			while(rs.next()){
				String id = rs.getString(1);
				String pw = rs.getString("pw");
				Timestamp joindate = rs.getTimestamp(3);
				%>
				<tr>
					<td><%=id %></td>
					<td><%=pw %></td>
					<td><%=joindate %></td>
				</tr>
				<%
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		System.out.println("DB연동 성공");
	
	%>
	</table>
</body>
</html>