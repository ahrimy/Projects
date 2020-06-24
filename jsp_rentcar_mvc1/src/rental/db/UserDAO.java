package rental.db;

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
		String url = "jdbc:mysql://localhost:3306/rentalDB?serverTimezone=UTC";
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
	public UserDTO getOneUser(int num) {
		UserDTO temp = new UserDTO();

		conn = getConnection();
		try {

			String sql = "SELECT * FROM user WHERE user_num=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				temp.setUser_num(rs.getInt("user_num"));
				temp.setUser_id(rs.getString("user_id"));
				temp.setUser_pw(rs.getString("user_pw"));
				temp.setUser_name(rs.getString("user_name"));
				temp.setUser_joindate(rs.getTimestamp("user_joindate"));
				temp.setUser_email(rs.getString("user_email"));
				temp.setUser_tel(rs.getString("user_tel"));
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
	public boolean checkPW(int user_num,String user_pw) {
		String dbPW = "";
		boolean check = true;
		// db 연동
		conn = getConnection();
		try {
			String sql = "select user_pw from user where user_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, user_num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dbPW = rs.getString("user_pw");
				if (dbPW.equals(user_pw)) {
					check = false;
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
	public boolean joinUser(UserDTO user) {
		int num=0;
		// db 연동
		conn = getConnection();
		try {
			String idsql = "select user_id from user where user_id = ?";
			pstmt = conn.prepareStatement(idsql);
			pstmt.setString(1, user.getUser_id());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return true;
			} else {
				String numsql = "select max(user_num) from user";
				pstmt = conn.prepareStatement(numsql);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					num = rs.getInt(1) + 1;
				}
				String sql = "INSERT INTO user(user_num,user_id,user_pw,user_name,user_email,user_joindate,user_tel) VALUES(?,?,?,?,?,?,?)";
				pstmt = conn.prepareStatement(sql);

				pstmt.setInt(1, num);	
				pstmt.setString(2, user.getUser_id());
				pstmt.setString(3,user.getUser_pw());
				pstmt.setString(4,user.getUser_name());
				pstmt.setString(5,user.getUser_email());
				pstmt.setTimestamp(6, user.getUser_joindate());
				pstmt.setString(7, user.getUser_tel());
				
				pstmt.executeUpdate();
				return false;
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
		return true;

	}

	public int signInUser(String id, String pw) {
		String dbPW = "";
		int check = -1;
		// db 연동
		conn = getConnection();
		try {
			String sql = "select user_pw,user_num from user where user_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dbPW = rs.getString("user_pw");
				if (dbPW.equals(pw)) {
					check = rs.getInt("user_num");
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

	public void updateUser(UserDTO user) {
		// db 연동
		conn = getConnection();
		try {
			
			String sql = "UPDATE user SET user_pw=?,user_name=?,user_email=?,user_tel=? WHERE user_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUser_pw());
			pstmt.setString(2, user.getUser_name());
			pstmt.setString(3, user.getUser_email());
			pstmt.setString(4, user.getUser_tel());
			pstmt.setInt(5, user.getUser_num());
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
