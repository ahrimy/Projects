package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Random;

public class ProfessorDAO {
	private ProfessorDAO() {
	}

	private static ProfessorDAO instance = new ProfessorDAO();

	public static ProfessorDAO getInstance() {
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

	// random profCode
	public int randomCode() {
		Random rn = new Random();
		int profCode = 0;

		try {
			conn = getConn();

			Timestamp ts = new Timestamp(System.currentTimeMillis());

			while (true) {
				int r = rn.nextInt(8998) + 1001;
				String temp = (ts.getYear() + 1900) + "20" + String.valueOf(r);
				profCode = Integer.parseInt(temp);

				String sql = "SELECT * FROM professors ";
				sql += "WHERE profCode=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, profCode);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					continue;
				} else {
					break;
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
					conn.close();
				} catch (SQLException e) {
				}
			}
			if (rs != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}

		System.out.println("CODE : " + profCode);
		return profCode;
	}

	// check Duplication
	public int check(String dupl) {
		int check = 1;

		try {
			conn = getConn();
			String sql = "SELECT * FROM professors ";
			sql += "WHERE dupl=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dupl);
			rs = pstmt.executeQuery();
			if (rs.next()) {
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

	// Join
	public int joinPfr(Professor p) {
		int check = 1;

		try {
			check = check(p.getDupl());
			conn = getConn();

			if (check == 1) {
				String sql = "INSERT INTO professor ";
				sql += "(profCode,pw,name,tel,email,birth,adds,dupl) ";
				sql += "VALUES (?,?,?,?,?,?,?,?)";
				pstmt = conn.prepareStatement(sql);

				pstmt.setInt(1, p.getProfCode());
				pstmt.setString(2, p.getPw());
				pstmt.setString(3, p.getName());
				pstmt.setString(4, p.getTel());
				pstmt.setString(5, p.getEmail());
				pstmt.setString(6, p.getBirth());
				pstmt.setString(7, p.getAdds());
				pstmt.setString(8, p.getDupl());

				pstmt.executeUpdate();
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

	// Login
	public int loginCheck(String id, String pw) {
		int check = -1;
		try {
			conn = getConn();
			String sql = "SELECT pw FROM professors ";
			sql += "WHERE profCode=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(id));
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String dbPw = rs.getString("pw");
				if (dbPw.equals(pw)) {
					check = 1;
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

	// getStudent(bean)
	public Student getStd(String id) {
		Student bean = null;
		try {
			conn = getConn();
			String sql = "SELECT * FROM students ";
			sql += "WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int stuCode = Integer.parseInt(id);
				int grade = rs.getInt("grade");
				String pw = rs.getString("pw");
				String name = rs.getString("name");
				String tel = rs.getString("tel");
				String email = rs.getString("email");
				String birth = rs.getString("birth");
				String adds = rs.getString("adds");
				String major = rs.getString("major");
				String dupl = rs.getString("dupl");
				bean = new Student(stuCode, grade, pw, name, tel, email, birth, adds, major);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) {try{conn.close();}catch(SQLException e) {}}
			if(pstmt != null) {try{pstmt.close();}catch(SQLException e) {}}
			if(rs != null) {try{rs.close();}catch(SQLException e) {}}
		}
		return bean;
	}
	
	// getProfessor(bean)
	public Professor getPrf(String id) {
		Professor bean = null;
		try {
			conn = getConn();
			String sql = "SELECT * FROM professors ";
			sql += "WHERE profCode=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int profCode = Integer.parseInt(id);
				String pw = rs.getString("pw");
				String name = rs.getString("name");
				String tel = rs.getString("tel");
				String email = rs.getString("email");
				String birth = rs.getString("birth");
				String adds = rs.getString("adds");
				String dupl = rs.getString("dupl");
				bean = new Professor(profCode, pw, name, tel, email, birth, adds, dupl);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) {try{conn.close();}catch(SQLException e) {}}
			if(pstmt != null) {try{pstmt.close();}catch(SQLException e) {}}
			if(rs != null) {try{rs.close();}catch(SQLException e) {}}
		}
		return bean;
	}
}