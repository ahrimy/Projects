package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Random;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class StudentDAO {
	private StudentDAO() {}
	private static StudentDAO instance = new StudentDAO();
	public static StudentDAO getInstance() {
		return instance;
	}
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public Connection getConn() throws Exception {
		String url = "jdbc:mysql://localhost:3306/lmsdb?serverTimezone=UTC";
		String dbId = "root";
		String dbPw = "root";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, dbId, dbPw);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	// random stuCdoe
	public int randomCode() {
		Random rn = new Random();
		int stuCode = 0;
		
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		
		try {
			conn = getConn();
			
			while(true) {
				int r = rn.nextInt(8998) + 1001;
				stuCode = ts.getYear() + r;
				
				String sql = "SELECT * FROM students ";
				sql += "WHERE stuCode=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, stuCode);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					continue;
				} else {
					break;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) { try{conn.close();} catch(SQLException e) {}}
			if(pstmt != null) { try{conn.close();} catch(SQLException e) {}}
			if(rs != null) { try{conn.close();} catch(SQLException e) {}}
		}
		
		System.out.println("CODE : " + stuCode);
		return stuCode;
	}
	
	// check Duplication
	public int check(String dupl) {
		int check = 1;
		
		try {
			conn = getConn();
			String sql = "SELECT * FROM students ";
			sql += "WHERE dupl=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dupl);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				check = -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) {try {conn.close();} catch (SQLException e) {}}
			if(pstmt != null) {try {pstmt.close();} catch (SQLException e) {}}
			if(rs != null) {try {rs.close();} catch (SQLException e) {}}
		}
		
		return check;
	}
	
	// Join
	public int joinStd(Student s) {
		int check = 1;
		
		try {
			check = check(s.getDupl());
			conn = getConn();
			
			if(check == 1) {
				String sql = "INSERT INTO students ";
				sql += "(stuCode,grade,pw,name,tel,email,birth,adds,major,dupl) ";
				sql += "VALUES (?,?,?,?,?,?,?,?,?,?)";
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, s.getStuCode());
				pstmt.setInt(2, s.getGrade());
				pstmt.setString(3, s.getPw());
				pstmt.setString(4, s.getName());
				pstmt.setString(5, s.getTel());
				pstmt.setString(6, s.getEmail());
				pstmt.setString(7, s.getBirth());
				pstmt.setString(8, s.getAdds());
				pstmt.setString(9, s.getMajor());
				pstmt.setString(10, s.getDupl());
				
				pstmt.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) { try{ conn.close();} catch(SQLException e) {}}
			if(pstmt != null) { try{ pstmt.close();} catch(SQLException e) {}}
		}
		
		return check;
	}
	
	// Login
	public int loginCheck(String id, String pw) {
		int check = -1;
		try {
			conn = getConn();
			String sql = "SELECT pw FROM students ";
			sql += "WHERE stuCode=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(id));
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String dbPw = rs.getString("pw");
				if(dbPw.equals(pw)) {
					check = 1;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) {try{conn.close();}catch(SQLException e) {}}
			if(pstmt != null) {try{pstmt.close();}catch(SQLException e) {}}
			if(rs != null) {try{rs.close();}catch(SQLException e) {}}
		}
		
		return check;
	}
}
