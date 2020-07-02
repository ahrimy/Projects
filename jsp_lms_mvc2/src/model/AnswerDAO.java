package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AnswerDAO {
	private AnswerDAO() {
	}

	private static AnswerDAO instance = new AnswerDAO();

	public static AnswerDAO getInstance() {
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

			String sql = "SELECT max(answerNum) FROM answer";
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

	public void addAnswer(AnswerDTO answer) {
		int answerNum = getMaxNum() + 1;
		conn = getConnection();
		try {
			
			String sql = "Insert into answer (answerNum, answer, result, scoreNum, queNum) values(?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, answerNum);
			pstmt.setString(2, answer.getAnswer());
			pstmt.setString(3, answer.getResult());
			pstmt.setInt(4, answer.getScoreNum());
			pstmt.setInt(5, answer.getQueNum());
			
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
	}
	public void updateResult(int answerNum, String result) {
		conn = getConnection();
		try {
			
			String sql = "Update answer set result = ? where answerNum = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, result);
			pstmt.setInt(2, answerNum);
			
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
	}

	public ArrayList<AnswerDTO> getAnswerList(int scoreNum){
		ArrayList<AnswerDTO> list = new ArrayList<>();
		conn = getConnection();
		try {

			String sql = "select * from answer where scoreNum=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, scoreNum);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				AnswerDTO temp = new AnswerDTO();
				temp.setAnswer(rs.getString("answer"));
				temp.setAnswerNum(rs.getInt("answerNum"));
				temp.setQueNum(rs.getInt("queNum"));
				temp.setResult(rs.getString("result"));
				temp.setScoreNum(scoreNum);
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

	public ArrayList<String> getResultList(int scoreNum){
		ArrayList<String> list = new ArrayList<>();
		conn = getConnection();
		try {

			String sql = "select result from answer where scoreNum=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, scoreNum);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String result = rs.getString("result");
				list.add(result);
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
