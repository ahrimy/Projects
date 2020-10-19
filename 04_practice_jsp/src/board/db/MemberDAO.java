package board.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberDAO {
	private MemberDAO() {
	}

	private static MemberDAO instance = new MemberDAO();

	public static MemberDAO getInstance() {
		return instance;
	}

	// db연동 클래스 선언
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	// db연동 메서드
	public Connection getConnection() {
		String url = "jdbc:mysql://localhost:3306/boardDB?serverTimezone=UTC";
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
	public MemberDTO getOneMember(String id) {
		MemberDTO temp = new MemberDTO();

		conn = getConnection();
		try {

			String sql = "SELECT * FROM member WHERE id=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				temp.setId(rs.getString("id"));
				temp.setEmail(rs.getString("email"));
				temp.setJoindate(rs.getTimestamp("joindate"));
				temp.setName(rs.getString("name"));
				temp.setPw(rs.getString("pw"));
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
	public boolean joinMemebr(MemberDTO member) {
		// db 연동
		conn = getConnection();
		try {
			String idsql = "select id from member where id = ?";
			pstmt = conn.prepareStatement(idsql);
			pstmt.setString(1, member.getId());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return true;
			} else {
				String sql = "INSERT INTO member(id,pw,name,email,joindate) VALUES(?,?,?,?,?)";
				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, member.getId());
				pstmt.setString(2, member.getPw());
				pstmt.setString(3, member.getName());
				pstmt.setString(4, member.getEmail());
				pstmt.setTimestamp(5, member.getJoindate());
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
		return false;

	}

	public int signInMember(String id, String pw) {
		String dbPW = "";
		int check = -1;
		// db 연동
		conn = getConnection();
		try {
			String sql = "select pw from member where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dbPW = rs.getString("pw");
				if (dbPW.equals(pw)) {
					check = 1;
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

	public void signOutMember(MemberDTO member) {
	}

	public void updateMember(MemberDTO member) {
		// db 연동
		conn = getConnection();
		try {
			
			String sql = "UPDATE board SET pw=?,name=?,email=? WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getPw());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getEmail());
			pstmt.setString(4, member.getId());
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
