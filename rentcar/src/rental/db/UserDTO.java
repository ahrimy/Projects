package rental.db;

import java.sql.Timestamp;

public class UserDTO {    
	private int user_num;
    private String user_id;
    private String user_pw;
    private String user_name;
    private String user_email;
    private Timestamp user_joindate;
    private String user_tel;
	public int getUser_num() {
		return user_num;
	}
	public void setUser_num(int user_num) {
		this.user_num = user_num;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_pw() {
		return user_pw;
	}
	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public Timestamp getUser_joindate() {
		return user_joindate;
	}
	public void setUser_joindate(Timestamp user_joindate) {
		this.user_joindate = user_joindate;
	}
	public String getUser_tel() {
		return user_tel;
	}
	public void setUser_tel(String user_tel) {
		this.user_tel = user_tel;
	}
    
   
    
    
}
