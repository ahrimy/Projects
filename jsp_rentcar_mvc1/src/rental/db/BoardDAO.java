package rental.db;

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
			String sql = "select * from board order by board_ref DESC, board_relevel limit ?,?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardDTO temp = new BoardDTO();
				temp.setBoard_num(rs.getInt("board_num"));
				temp.setBoard_subject(rs.getString("board_subject"));
				temp.setBoard_content(rs.getString("board_content"));
				temp.setBoard_regdate(rs.getTimestamp("board_regdate"));
				temp.setBoard_readcount(rs.getInt("board_readcount"));
				temp.setBoard_ref(rs.getInt("board_ref"));
				temp.setBoard_restep(rs.getInt("board_restep"));
				temp.setBoard_relevel(rs.getInt("board_relevel"));
				temp.setUser_num(rs.getInt("user_num"));

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

	public BoardDTO getOneBoard(int board_num) {
		BoardDTO temp = new BoardDTO();

		conn = getConnection();
		try {
			String readsql = "UPDATE board SET board_readcount=board_readcount+1 WHERE board_num=?";
			pstmt = conn.prepareStatement(readsql);
			pstmt.setInt(1, board_num);
			pstmt.executeUpdate();

			String sql = "SELECT * FROM board WHERE board_num=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_num);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				temp.setBoard_num(rs.getInt("board_num"));
				temp.setBoard_subject(rs.getString("board_subject"));
				temp.setBoard_content(rs.getString("board_content"));
				temp.setBoard_regdate(rs.getTimestamp("board_regdate"));
				temp.setBoard_readcount(rs.getInt("board_readcount"));
				temp.setBoard_ref(rs.getInt("board_ref"));
				temp.setBoard_restep(rs.getInt("board_restep"));
				temp.setBoard_relevel(rs.getInt("board_relevel"));
				temp.setUser_num(rs.getInt("user_num"));
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

	public BoardDTO getOneUpdateBoard(int board_num) {
		BoardDTO temp = new BoardDTO();

		conn = getConnection();
		try {
			String sql = "SELECT * FROM board WHERE board_num=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_num);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				temp.setBoard_num(rs.getInt("board_num"));
				temp.setBoard_subject(rs.getString("board_subject"));
				temp.setBoard_content(rs.getString("board_content"));
				temp.setBoard_regdate(rs.getTimestamp("board_regdate"));
				temp.setBoard_readcount(rs.getInt("board_readcount"));
				temp.setBoard_ref(rs.getInt("board_ref"));
				temp.setBoard_restep(rs.getInt("board_restep"));
				temp.setBoard_relevel(rs.getInt("board_relevel"));
				temp.setUser_num(rs.getInt("user_num"));
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
			String refsql = "select max(board_ref) from board";
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

			String sql = "insert into board (board_num,board_subject, board_content, board_regdate,board_readcount,board_ref,board_restep,board_relevel,user_num) values(?,?,?,now(),0,?,1,1,?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, num);
			pstmt.setString(2, board.getBoard_subject());
			pstmt.setString(3, board.getBoard_content());
			pstmt.setInt(4, ref);
			pstmt.setInt(5, board.getUser_num());
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

	public void updateBoard(int num, String subjct, String content) {
		// db 연동
		
		try {

	
				conn = getConnection();
				String sql = "update board set board_subject = ?, board_content = ? where board_num=?;";
				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, subjct);
				pstmt.setString(2, content);
				pstmt.setInt(3, num);
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

	public void deleteBoard(int num) {
		int ref=0;
		int restep=0;
		int relevel=0;
		
		// db 연동
		try {

				conn = getConnection();
				String boardsql = "select board_ref, board_restep,board_relevel from board where board_num=?";
				pstmt= conn.prepareStatement(boardsql);
				
				pstmt.setInt(1, num);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					ref = rs.getInt("board_ref");
					restep = rs.getInt("board_restep");
					relevel = rs.getInt("board_relevel");
				}
				String deletesql = "select * from board where board_ref=? and board_relevel>? order by board_relevel";
				pstmt = conn.prepareStatement(deletesql);
				pstmt.setInt(1, ref);
				pstmt.setInt(2, relevel);
				rs = pstmt.executeQuery();
				
				int deleteCnt = 0;
				while(rs.next()) {
					int temp = rs.getInt("board_restep");
					if(temp<=restep) {
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
				pstmt.setInt(1, num);
				pstmt.executeUpdate();
				deleteCnt++;
				
				String relevelsql = "update board set board_relevel = board_relevel-? where board_ref=? and board_relevel>?;";
				pstmt = conn.prepareStatement(relevelsql);
				pstmt.setInt(1, deleteCnt);
				pstmt.setInt(2, ref);
				pstmt.setInt(3, relevel);
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


	public void rewriteBoard(BoardDTO board) {
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
			String relevelsql = "update board set board_relevel = board_relevel+1 where board_ref=? and board_relevel>?;";
			pstmt = conn.prepareStatement(relevelsql);

			pstmt.setInt(1, board.getBoard_ref());
			pstmt.setInt(2, board.getBoard_relevel());
			pstmt.executeUpdate();

			String sql = "insert into board (board_num,board_subject, board_content, board_regdate,board_readcount,board_ref,board_restep,board_relevel,user_num)  values(?,?,?,now(),0,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, num);
			pstmt.setString(2, board.getBoard_subject());
			pstmt.setString(3, board.getBoard_content());
			pstmt.setInt(4, board.getBoard_ref());
			pstmt.setInt(5, board.getBoard_restep() + 1);
			pstmt.setInt(6, board.getBoard_relevel() + 1);
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
