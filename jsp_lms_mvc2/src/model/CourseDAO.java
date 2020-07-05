package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseDAO {
	private CourseDAO() {
	}

	private static CourseDAO instance = new CourseDAO();

	public static CourseDAO getInstance() {
		return instance;
	}


	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;


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
	public CourseDTO getCourse(int courseNum) {
		CourseDTO temp = new CourseDTO();

		conn = getConnection();
		try {

			String sql = "SELECT * FROM course WHERE courseNum=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, courseNum);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				temp.setCourseNum(courseNum);
				temp.setCourseTitle(rs.getString("courseTitle"));
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


}
