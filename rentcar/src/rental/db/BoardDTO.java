package rental.db;

import java.sql.Timestamp;

public class BoardDTO {  
	private int board_num;
    private String board_subject ;
    private String board_content;
    private Timestamp board_regdate;
    private int board_readcount;
    private int board_ref;
    private int board_restep; 
    private int board_relevel;
    private int user_num;
	public int getBoard_num() {
		return board_num;
	}
	public void setBoard_num(int board_num) {
		this.board_num = board_num;
	}
	public String getBoard_subject() {
		return board_subject;
	}
	public void setBoard_subject(String board_subject) {
		this.board_subject = board_subject;
	}
	public String getBoard_content() {
		return board_content;
	}
	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}
	public Timestamp getBoard_regdate() {
		return board_regdate;
	}
	public void setBoard_regdate(Timestamp board_regdate) {
		this.board_regdate = board_regdate;
	}
	public int getBoard_readcount() {
		return board_readcount;
	}
	public void setBoard_readcount(int board_readcount) {
		this.board_readcount = board_readcount;
	}
	public int getBoard_ref() {
		return board_ref;
	}
	public void setBoard_ref(int board_ref) {
		this.board_ref = board_ref;
	}
	public int getBoard_restep() {
		return board_restep;
	}
	public void setBoard_restep(int board_restep) {
		this.board_restep = board_restep;
	}
	public int getBoard_relevel() {
		return board_relevel;
	}
	public void setBoard_relevel(int board_relevel) {
		this.board_relevel = board_relevel;
	}
	public int getUser_num() {
		return user_num;
	}
	public void setUser_num(int user_num) {
		this.user_num = user_num;
	}
    
    

    
    
    
}
