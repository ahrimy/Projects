package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
	private UserDAO() {
	}

	private static UserDAO instance = new UserDAO();

	public static UserDAO getInstance() {
		return instance;
	}

	// db연동 클래스 선언
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	// db연동 메서드
	public Connection getConnection() {
		String url = "jdbc:mysql://localhost:3306/boardDB2?serverTimezone=UTC";
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

	public UserDTO getOneUser(int user_num) {
		UserDTO temp = new UserDTO();

		conn = getConnection();
		try {

			String sql = "SELECT * FROM user WHERE user_num=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, user_num);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				temp.setUser_num(user_num);
				temp.setUser_id(rs.getString("user_id"));
				temp.setUser_email(rs.getString("user_email"));
				temp.setUser_joindate(rs.getTimestamp("user_joindate"));
				temp.setUser_name(rs.getString("user_name"));
				temp.setUser_pw(rs.getString("user_pw"));
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

	public int joinUser(String user_id,String user_pw,String user_name,String user_email) {
		int num = 0;
		int check = -1;
		// db 연동
		conn = getConnection();
		try {
			String idsql = "select * from user where user_id = ?";
			pstmt = conn.prepareStatement(idsql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				check = 0;
			} else {
				String numsql = "select max(user_num) from user";
				pstmt = conn.prepareStatement(numsql);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					num = rs.getInt(1) + 1;
				}
				String sql = "INSERT INTO user (user_num,user_id,user_pw,user_name,user_email,user_joindate) VALUES(?,?,?,?,?,now())";
				pstmt = conn.prepareStatement(sql);

				pstmt.setInt(1, num);
				pstmt.setString(2,user_id );
				pstmt.setString(3, user_pw);
				pstmt.setString(4, user_name);
				pstmt.setString(5, user_email);
				pstmt.executeUpdate();
				check = 1;
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
		}
		return check;

	}

	public int withdrawUser(int user_num, String user_pw) {
		String dbPW = "";
		int check = -1;
		// db 연동
		conn = getConnection();
		try {
			String pwsql = "select user_pw from user where user_num = ?";
			pstmt = conn.prepareStatement(pwsql);
			pstmt.setInt(1, user_num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dbPW = rs.getString(1);
				if (dbPW.equals(user_pw)) {
					String sql = "delete from user where user_num=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, user_num);
					pstmt.executeUpdate();
					check = 1;
				} else {
					check = 0;
				}
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
		return check;
	}

	public int signInUser(String user_id, String user_pw) {
		String dbPW = "";
		int check = -1;
		// db 연동
		conn = getConnection();
		try {
			String sql = "select user_pw from user where user_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dbPW = rs.getString("user_pw");
				if (dbPW.equals(user_pw)) {
					String numsql = "select user_num from user where user_id=?";
					pstmt = conn.prepareStatement(numsql);
					pstmt.setString(1, user_id);
					rs = pstmt.executeQuery();
					if (rs.next()) {
						check = rs.getInt(1);
					}
				} else {
					check = 0;
				}
			} else {
				check = -1;
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
		return check;

	}

	public void signOutUser(UserDTO user) {
	}

	public void updateUser(int user_num,String user_pw,String user_name, String user_email) {
		// db 연동
		conn = getConnection();
		try {

			String sql = "UPDATE user SET user_pw=?,user_name=?,user_email=? WHERE user_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_pw);
			pstmt.setString(2, user_name);
			pstmt.setString(3, user_email);
			pstmt.setInt(4, user_num);
			pstmt.executeUpdate();
			
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
		}
	}

}
