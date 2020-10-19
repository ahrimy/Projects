package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BoardDAO {
	private BoardDAO() {
	}

	private static BoardDAO instance = new BoardDAO();

	public static BoardDAO getInstance() {
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

	public int getAllCount() {
		int count = 0;
		// db 연동
		conn = getConnection();
		try {
			String sql = "select count(*) from board";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
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
		return count;
	}

	public ArrayList<BoardDTO> getAllBoard(int startRow, int size) {
		ArrayList<BoardDTO> boardList = new ArrayList<>();
		// db 연동
		conn = getConnection();
		try {
			String sql = "select * from board order by ref DESC, relevel limit ?,?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, size);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardDTO temp = new BoardDTO();
				temp.setUser_num(rs.getInt("user_num"));
				temp.setBoard_num(rs.getInt("board_num"));
				temp.setBoard_subject(rs.getString("board_subject"));
				temp.setBoard_content(rs.getString("board_content"));
				temp.setBoard_rcount(rs.getInt("board_rcount"));
				temp.setBoard_regdate(rs.getTimestamp("board_regdate"));
				temp.setRef(rs.getInt("ref"));
				temp.setRelevel(rs.getInt("relevel"));
				temp.setRestep(rs.getInt("restep"));

				boardList.add(temp);
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

		return boardList;
	}

	public BoardDTO getOneBoard(int board_num) {
		BoardDTO temp = new BoardDTO();

		conn = getConnection();
		try {
			String readsql = "UPDATE board SET board_rcount=board_rcount+1 WHERE board_num=?";
			pstmt = conn.prepareStatement(readsql);
			pstmt.setInt(1, board_num);
			pstmt.executeUpdate();

			String sql = "SELECT * FROM board WHERE board_num=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_num);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				temp.setUser_num(rs.getInt("user_num"));
				temp.setBoard_num(rs.getInt("board_num"));
				temp.setBoard_subject(rs.getString("board_subject"));
				temp.setBoard_content(rs.getString("board_content"));
				temp.setBoard_rcount(rs.getInt("board_rcount"));
				temp.setBoard_regdate(rs.getTimestamp("board_regdate"));
				temp.setRef(rs.getInt("ref"));
				temp.setRelevel(rs.getInt("relevel"));
				temp.setRestep(rs.getInt("restep"));
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

	public BoardDTO getUpdateBoard(int board_num) {
		BoardDTO temp = new BoardDTO();

		conn = getConnection();
		try {
			String sql = "SELECT * FROM board WHERE board_num=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_num);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				temp.setUser_num(rs.getInt("user_num"));
				temp.setBoard_num(rs.getInt("board_num"));
				temp.setBoard_subject(rs.getString("board_subject"));
				temp.setBoard_content(rs.getString("board_content"));
				temp.setBoard_rcount(rs.getInt("board_rcount"));
				temp.setBoard_regdate(rs.getTimestamp("board_regdate"));
				temp.setRef(rs.getInt("ref"));
				temp.setRelevel(rs.getInt("relevel"));
				temp.setRestep(rs.getInt("restep"));
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

	public void addBoard(String board_subject,String board_content,int user_num) {
		int ref = 0;
		int num = 0;
		// db 연동
		conn = getConnection();
		try {
			String refsql = "select max(ref) from board";
			pstmt = conn.prepareStatement(refsql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				ref = rs.getInt(1) + 1;
			}
			String numsql = "select max(board_num) from board";
			pstmt = conn.prepareStatement(numsql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				num = rs.getInt(1) + 1;
			}

			String sql = "insert into board (board_num,board_subject, board_content, board_regdate,board_rcount,ref,restep,relevel,user_num) values(?,?,?,now(),0,?,1,1,?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, num);
			pstmt.setString(2, board_subject);
			pstmt.setString(3, board_content);
			pstmt.setInt(4, ref);
			pstmt.setInt(5, user_num);
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

	public int updateBoard(int board_num, int user_num, String user_pw, String board_subject, String board_content) {
		int check = -1;
		// db 연동

		try {
			if (checkPW(user_num, user_pw)) {
				conn = getConnection();
				String sql = "update board set board_subject = ?, board_content = ? where board_num=?;";
				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, board_subject);
				pstmt.setString(2, board_content);
				pstmt.setInt(3, board_num);
				pstmt.executeUpdate();
				check = 1; // 비밀번호 확인->업데이트
			}else {
				check = 0;
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

	public int deleteBoard(int user_num, String user_pw, int board_num) {
		int ref = 0;
		int restep = 0;
		int relevel = 0;
		int check = -1;
		// db 연동
		try {
			if (checkPW(user_num, user_pw)) {
				conn = getConnection();
				String boardsql = "select ref, restep,relevel from board where board_num=?";
				pstmt = conn.prepareStatement(boardsql);

				pstmt.setInt(1, board_num);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					ref = rs.getInt("ref");
					restep = rs.getInt("restep");
					relevel = rs.getInt("relevel");
				}
				String deletesql = "select * from board where ref=? and relevel>? order by relevel";
				pstmt = conn.prepareStatement(deletesql);
				pstmt.setInt(1, ref);
				pstmt.setInt(2, relevel);
				rs = pstmt.executeQuery();

				int deleteCnt = 0;
				while (rs.next()) {
					int temp = rs.getInt("restep");
					if (temp <= restep) {
						break;
					}
					temp = rs.getInt("board_num");
					String sql = "delete from board where board_num=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, temp);
					pstmt.executeUpdate();
					deleteCnt++;
				}
				String sql = "delete from board where board_num=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, board_num);
				pstmt.executeUpdate();
				deleteCnt++;

				String relevelsql = "update board set relevel = relevel-? where ref=? and relevel>?;";
				pstmt = conn.prepareStatement(relevelsql);
				pstmt.setInt(1, deleteCnt);
				pstmt.setInt(2, ref);
				pstmt.setInt(3, relevel);
				pstmt.executeUpdate();

				check = 1;
			}else {
				check=0;
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

	public boolean checkPW(int user_num, String user_pw) {
		String dbPW;
		boolean check = false;
		// db 연동
		conn = getConnection();
		try {
			String pwsql = "select user_pw from user where user_num=?";
			pstmt = conn.prepareStatement(pwsql);
			pstmt.setInt(1, user_num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dbPW = rs.getString(1);
				if (dbPW.equals(user_pw)) {
					check = true;
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

	public void addRewriteBoard(BoardDTO board) {
		int num = 0;
		// db 연동
		conn = getConnection();
		try {

			String numsql = "select max(board_num) from board";
			pstmt = conn.prepareStatement(numsql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				num = rs.getInt(1) + 1;
			}
			String relevelsql = "update board set relevel = relevel+1 where ref=? and relevel>?;";
			pstmt = conn.prepareStatement(relevelsql);

			pstmt.setInt(1, board.getRef());
			pstmt.setInt(2, board.getRelevel());
			pstmt.executeUpdate();
			
			String sql = "insert into board (board_num,board_subject, board_content, board_regdate,board_rcount,ref,restep,relevel,user_num) values(?,?,?,now(),0,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, num);
			pstmt.setString(2, board.getBoard_subject());
			pstmt.setString(3, board.getBoard_content());
			pstmt.setInt(4, board.getRef());
			pstmt.setInt(5, board.getRestep() + 1);
			pstmt.setInt(6, board.getRelevel() + 1);
			pstmt.setInt(7, board.getUser_num());

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

}
