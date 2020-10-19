package board.db;

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

	public ArrayList<BoardDTO> getAllBoard(int startRow, int endRow) {
		ArrayList<BoardDTO> boardList = new ArrayList<>();
		// db 연동
		conn = getConnection();
		try {
			String sql = "select * from board order by ref DESC, relevel limit ?,?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardDTO temp = new BoardDTO();
				temp.setId(rs.getString("id"));
				temp.setNum(rs.getInt("num"));
				temp.setReadcount(rs.getInt("readcount"));
				temp.setRef(rs.getInt("ref"));
				temp.setRegdate(rs.getTimestamp("regdate"));
				temp.setRelevel(rs.getInt("relevel"));
				temp.setRestep(rs.getInt("restep"));
				temp.setContent(rs.getString("content"));
				temp.setSubject(rs.getString("subject"));

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
			// if(rs!=null) {try {rs.close();} catch (SQLException e) {}}
		}

		return boardList;
	}

	public BoardDTO getOneBoard(int num) {
		BoardDTO temp = new BoardDTO();

		conn = getConnection();
		try {
			String readsql = "UPDATE board SET readcount=readcount+1 WHERE NUM=?";
			pstmt = conn.prepareStatement(readsql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();

			String sql = "SELECT * FROM board WHERE NUM=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				temp.setId(rs.getString("id"));
				temp.setNum(rs.getInt("num"));
				temp.setReadcount(rs.getInt("readcount"));
				temp.setRef(rs.getInt("ref"));
				temp.setRegdate(rs.getTimestamp("regdate"));
				temp.setRelevel(rs.getInt("relevel"));
				temp.setRestep(rs.getInt("restep"));
				temp.setContent(rs.getString("content"));
				temp.setSubject(rs.getString("subject"));
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

	public BoardDTO getOneUpdateBoard(int num) {
		BoardDTO temp = new BoardDTO();

		conn = getConnection();
		try {
			String sql = "SELECT * FROM board WHERE NUM=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				temp.setId(rs.getString("id"));
				temp.setNum(rs.getInt("num"));
				temp.setReadcount(rs.getInt("readcount"));
				temp.setRef(rs.getInt("ref"));
				temp.setRegdate(rs.getTimestamp("regdate"));
				temp.setRelevel(rs.getInt("relevel"));
				temp.setRestep(rs.getInt("restep"));
				temp.setContent(rs.getString("content"));
				temp.setSubject(rs.getString("subject"));
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

	public void insertBoard(BoardDTO board) {
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
			String numsql = "select max(num) from board";
			pstmt = conn.prepareStatement(numsql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				num = rs.getInt(1) + 1;
			}

			String sql = "insert into board (num,subject, content, regdate,readcount,ref,restep,relevel,id) values(?,?,?,now(),0,?,1,1,?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, num);
			pstmt.setString(2, board.getSubject());
			pstmt.setString(3, board.getContent());
			pstmt.setInt(4, ref);
			pstmt.setString(5, board.getId());
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

	public boolean updateBoard(int num, String id, String pw, String subjct, String content) {
		// db 연동
		
		try {

			if (checkPW(id, pw)) {
				conn = getConnection();
				String sql = "update board set subject = ?, content = ? where num=?;";
				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, subjct);
				pstmt.setString(2, content);
				pstmt.setInt(3, num);
				pstmt.executeUpdate();
				return false; // 비밀번호 확인->업데이트
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
		return true;// 비밀번호 불일치
	}

	public boolean deleteBoard(String id, String pw, int num) {
		int ref=0;
		int restep=0;
		int relevel=0;
		
		// db 연동
		try {
			if (checkPW(id, pw)) {
				conn = getConnection();
				String boardsql = "select ref, restep,relevel from board where num=?";
				pstmt= conn.prepareStatement(boardsql);
				
				pstmt.setInt(1, num);
				rs = pstmt.executeQuery();
				if(rs.next()) {
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
				while(rs.next()) {
					int temp = rs.getInt("restep");
					if(temp<=restep) {
						break;
					}
					temp = rs.getInt("num");
					String sql = "delete from board where num=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, temp);
					pstmt.executeUpdate();
					deleteCnt++;
				}
				String sql = "delete from board where num=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, num);
				pstmt.executeUpdate();
				deleteCnt++;
				
				String relevelsql = "update board set relevel = relevel-? where ref=? and relevel>?;";
				pstmt = conn.prepareStatement(relevelsql);
				pstmt.setInt(1, deleteCnt);
				pstmt.setInt(2, ref);
				pstmt.setInt(3, relevel);
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
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}
		}
		return true;
	}

	public boolean checkPW(String id, String pw) {
		String dbPW;
		// db 연동
		conn = getConnection();
		try {
			String pwsql = "select pw from member where id=?";
			pstmt = conn.prepareStatement(pwsql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dbPW = rs.getString("pw");
				if (dbPW.contentEquals(pw)) {
					return true;
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
		return false;
	}

	public void rewriteBoard(BoardDTO board) {
		int num = 0;
		// db 연동
		conn = getConnection();
		try {

			String numsql = "select max(num) from board";
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

			String sql = "insert into board (num,subject, content, regdate,readcount,ref,restep,relevel,id) values(?,?,?,now(),0,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, num);
			pstmt.setString(2, board.getSubject());
			pstmt.setString(3, board.getContent());
			pstmt.setInt(4, board.getRef());
			pstmt.setInt(5, board.getRestep() + 1);
			pstmt.setInt(6, board.getRelevel() + 1);
			pstmt.setString(7, board.getId());

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
