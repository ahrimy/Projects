package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EnrollDAO {
	private EnrollDAO() {
	}

	private static EnrollDAO instance = new EnrollDAO();

	public static EnrollDAO getInstance() {
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

	public ArrayList<EnrollDTO> getEnrollsStu(int stuCode) {
		ArrayList<EnrollDTO> list = new ArrayList<>();
		// db 연동
		conn = getConnection();
		try {
			String sql = "select * from enroll where stuCode = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, stuCode);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				EnrollDTO temp = new EnrollDTO();
				temp.setEnrollNum(rs.getInt("enrollNum"));
				temp.setClassNum(rs.getInt("classNum"));
				temp.setStuCode(stuCode);
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
	public ArrayList<EnrollDTO> getEnrollsClass(int classNum) {
		ArrayList<EnrollDTO> list = new ArrayList<>();
		// db 연동
		conn = getConnection();
		try {
			String sql = "select * from enroll where classNum = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, classNum);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				EnrollDTO temp = new EnrollDTO();
				temp.setEnrollNum(rs.getInt("enrollNum"));
				temp.setClassNum(classNum);
				temp.setStuCode(rs.getInt("stuCode"));
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
