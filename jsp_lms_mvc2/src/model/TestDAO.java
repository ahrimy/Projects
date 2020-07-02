package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TestDAO {
	private TestDAO() {
	}

	private static TestDAO instance = new TestDAO();

	public static TestDAO getInstance() {
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

	public int getTestCount(int classNum) {
		int cnt = 0;

		conn = getConnection();
		try {

			String sql = "SELECT count(*) FROM test WHERE classNum=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, classNum);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				cnt = rs.getInt(1);
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
		return cnt;
	}

	public ArrayList<Integer> getTests(int classNum) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		conn = getConnection();
		try {

			String sql = "SELECT testNum FROM test WHERE classNum=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, classNum);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				int temp = rs.getInt("testNum");
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

	public int getMaxNum() {
		int max = 0;
		conn = getConnection();
		try {

			String sql = "SELECT max(testNum) FROM test";

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				max = rs.getInt(1);
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
		return max;
	}

	public int addTest(TestDTO test) {
		int testNum = getMaxNum() + 1;
		conn = getConnection();
		try {

			String sql = "Insert into test (testNum, testTitle, testDesc, testScore,classNum,profCode) values(?,?,?,0,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, testNum);
			pstmt.setString(2, test.getTestTitle());
			pstmt.setString(3, test.getTestDesc());
			pstmt.setInt(4, test.getClassNum());
			pstmt.setInt(5, test.getProfCode());
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
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}
		}
		return testNum;
	}

	public void deleteTest(int testNum) {

	}

	public void updateTest(TestDTO test) {

	}

	public ArrayList<TestDTO> getAllTest(int classNum) {
		ArrayList<TestDTO> list = new ArrayList<>();

		conn = getConnection();
		try {

			String sql = "select * from test where classNum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, classNum);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				TestDTO temp = new TestDTO();
				temp.setClassNum(classNum);
				temp.setProfCode(rs.getInt("profCode"));
				temp.setTestDesc(rs.getString("testDesc"));
				temp.setTestTitle(rs.getString("testTitle"));
				temp.setTestNum(rs.getInt("testNum"));
				temp.setTestScore(rs.getLong("testScore"));
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

	public TestDTO getTest(int testNum) {
		TestDTO temp = new TestDTO();
		conn = getConnection();
		try {

			String sql = "select * from test where testNum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, testNum);
			rs = pstmt.executeQuery();

			if (rs.next()) {

				temp.setClassNum(rs.getInt("classNum"));
				temp.setProfCode(rs.getInt("profCode"));
				temp.setTestDesc(rs.getString("testDesc"));
				temp.setTestTitle(rs.getString("testTitle"));
				temp.setTestNum(testNum);
				temp.setTestScore(rs.getLong("testScore"));
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
