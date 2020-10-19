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
	<%
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String url = "jdbc:mysql://localhost:3306/memberJDBC?serverTimezone=UTC";
		String dbId = "root";
		String dbPw = "root";	
		try{	
				
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn = DriverManager.getConnection(url,dbId,dbPw);
				
				//db명령어(insert) 입력하기
				String sql = "INSERT INTO member (id,pw,joindate) VALUES(?,?,now())";
				pstmt = conn.prepareStatement(sql);
				
				//물음표에 값 적용하기
				pstmt.setString(1, "qwer");
				pstmt.setString(2, "1111");
				
				//db명령어 실행하기 : ctrl + enter
				//1)executeUpdate()		:SELECT  이외 나머지
				//2)executeQuery() 		:SELECT
				pstmt.executeUpdate();
				
				
		}catch(Exception e){
			e.printStackTrace();
		}
		
		System.out.println("DB연동 성공");
	
	
	%>
</body>
</html>