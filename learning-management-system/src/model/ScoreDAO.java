package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ScoreDAO {
	private ScoreDAO() {
	}

	private static ScoreDAO instance = new ScoreDAO();

	public static ScoreDAO getInstance() {
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

	public int getMaxNum() {
		int max = 0;
		conn = getConnection();
		try {

			String sql = "SELECT max(scoreNum) FROM score";

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

	public void addScore(int testNum, int classNum) {
		int num = getMaxNum() + 1;

		conn = getConnection();
		try {

			String stusql = "select stuCode from enroll where classNum=?";
			pstmt = conn.prepareStatement(stusql);
			pstmt.setInt(1, classNum);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				int stuCode = rs.getInt("stuCode");

				String sql = "insert into score (scoreNum,stuScore,status,testNum,stuCode) value(?,0,'no result',?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, num++);
				pstmt.setInt(2, testNum);
				pstmt.setInt(3, stuCode);

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
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}
		}

	}
	public ScoreDTO getScore(int stuCode,int testNum) {
		ScoreDTO temp = new ScoreDTO();
		conn = getConnection();
		try {

			String sql = "select * from score where stuCode=? and testNum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, stuCode);
			pstmt.setInt(2, testNum);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				temp.setScoreNum(rs.getInt("scoreNum"));
				temp.setStatus(rs.getString("status"));
				temp.setstuCode(stuCode);
				temp.setStuScore(rs.getLong("stuScore"));
				temp.setTestNum(testNum);
				
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
	public int getScoreNum(int stuCode,int testNum) {
		int num = 0;
		
		conn = getConnection();
		try {

			String sql = "select scoreNum from score where stuCode=? and testNum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, stuCode);
			pstmt.setInt(2, testNum);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				num = rs.getInt("scoreNum");
				
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
		return num;
	}
	public int updateScore(int scoreNum, int score) {
		int num = 0;
		
		conn = getConnection();
		try {

			String sql = "update score set stuScore = stuScore+?,status='grading' where scoreNum=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, score);
			pstmt.setInt(2, scoreNum);
			pstmt.executeUpdate();
			
			checkStatus(scoreNum);

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
		return num;
	}
	public void checkStatus(int scoreNum) {
		
		conn = getConnection();
		try {

			String resql = "select * from answer where scoreNum=? and result is null";
			pstmt = conn.prepareStatement(resql);
			pstmt.setInt(1, scoreNum);
			rs = pstmt.executeQuery();
			if(!rs.next()) {

				String sql = "update score set status='graded'where scoreNum=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, scoreNum);
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
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}
		}

	}
}
