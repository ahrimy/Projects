package member.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberDAO {
	private MemberDAO() {}
	private static MemberDAO instance = new MemberDAO();
	public static MemberDAO getInstance() {return instance;}
	
	//db연동 클래스 선언
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	//db연동 메서드
	public Connection getConnection() {
		String url = "jdbc:mysql://localhost:3306/memberJDBC?serverTimezone=UTC";
		String dbId = "root";
		String dbPw = "root";	
		try{	
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url,dbId,dbPw);				
		}catch(Exception e){
			e.printStackTrace();
		}
		return conn;
	}
	
	//회원가입 메서드
	public void joinMember(MemberDTO member) {
		//db 연동
		conn = getConnection();
		try {
			String sql = "INSERT INTO member(id,pw,joindate) VALUES(?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPw());
			pstmt.setTimestamp(3, member.getJoindate());
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(conn!=null) {try {conn.close();} catch (SQLException e) {}}
			if(pstmt!=null) {try {pstmt.close();} catch (SQLException e) {}}
		}
		
	}
	
	
	
}
