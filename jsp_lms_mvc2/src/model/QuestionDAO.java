package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QuestionDAO {
	private QuestionDAO() {
	}

	private static QuestionDAO instance = new QuestionDAO();

	public static QuestionDAO getInstance() {
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

			String sql = "SELECT max(queNum) FROM question";

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
	public QuestionDTO getQuestion(int queNum) {
		QuestionDTO question = new QuestionDTO();
		conn = getConnection();
		try {

			String sql = "SELECT * FROM question where queNum = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, queNum);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				question.setQueAnswer(rs.getString("queAnswer"));
				question.setQueNum(queNum);
				question.setQueOpts(rs.getString("queOpts"));
				question.setQueScore(rs.getInt("queScore"));
				question.setQuestion(rs.getString("question"));
				question.setQueType(rs.getString("queType"));
				question.setTestNum(rs.getInt("testNum"));
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
		return question;
	}
	public void addQuestion(QuestionDTO question) {
		int queNum = getMaxNum() + 1;
		conn = getConnection();
		try {
			String scoresql = "UPDATE test set testScore = testScore+? where testNum=?";
			pstmt = conn.prepareStatement(scoresql);
			pstmt.setInt(1, question.getQueScore());
			pstmt.setInt(2, question.getTestNum());
			pstmt.executeUpdate();
			
			String sql = "Insert into question (queNum, testNum, question, queType, queOpts,queAnswer,queScore) values(?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, queNum);
			pstmt.setInt(2, question.getTestNum());
			pstmt.setString(3, question.getQuestion());
			pstmt.setString(4, question.getQueType());
			pstmt.setString(5, question.getQueOpts());
			pstmt.setString(6, question.getQueAnswer());
			pstmt.setInt(7, question.getQueScore());
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
	
	public void deleteQuestion(int queNum) {

	}
	
	public void updateQuestion(QuestionDTO question) {

	}
	
	public ArrayList<QuestionDTO> getQuestions(int testNum){
		ArrayList<QuestionDTO> list= new ArrayList<>();
		conn = getConnection();
		try {

			String sql = "select * from question where testNum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, testNum);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				QuestionDTO temp = new QuestionDTO();
				temp.setQueAnswer(rs.getString("queAnswer"));
				temp.setQueNum(rs.getInt("queNum"));
				temp.setQueOpts(rs.getString("queOpts"));
				temp.setQueScore(rs.getInt("queScore"));
				temp.setQuestion(rs.getString("question"));
				temp.setQueType(rs.getString("queType"));
				temp.setTestNum(testNum);
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
