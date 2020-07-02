package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClassDAO {
	private ClassDAO() {
	}

	private static ClassDAO instance = new ClassDAO();

	public static ClassDAO getInstance() {
		return instance;
	}

	// db연동 클래스 선언
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	// db연동 메서드
	public Connection getConnection() {
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
	
	public ClassDTO getClass(int classNum) {
		ClassDTO temp = new ClassDTO();

		conn = getConnection();
		try {

			String sql = "SELECT * FROM class WHERE classNum=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, classNum);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				temp.setClassNum(classNum);
				temp.setClassCurri(rs.getString("classCurri"));
				temp.setClassInfo(rs.getString("classInfo"));
				temp.setCourseNum(rs.getInt("courseNum"));
				temp.setProfCode(rs.getInt("profCode"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}
		}
		return temp;
	}
	
	public ArrayList<ClassDTO> getProfclass(int profCode){
		ArrayList<ClassDTO> list = new ArrayList<>();
		// db 연동
		conn = getConnection();
		try {
			String sql = "select * from class where profCode = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, profCode);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ClassDTO temp = new ClassDTO();
				temp.setClassNum(rs.getInt("classNum"));
				temp.setClassCurri(rs.getString("classCurri"));
				temp.setClassInfo(rs.getString("classInfo"));
				temp.setCourseNum(rs.getInt("courseNum"));
				temp.setProfCode(profCode);
				list.add(temp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}
		}
		return list;
	}
}
