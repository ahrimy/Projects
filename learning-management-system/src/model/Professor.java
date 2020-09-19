package model;

public class Professor {
	private int profCode;
	private String pw;
    private String name;
    private String tel;
    private String email;
    private String birth;
    private String adds;
    private String dupl;
    public Professor() {}
    public Professor(int profCode,String  pw,String name,String tel,String email,String birth,String adds,String dupl){
    	this.profCode = profCode;
    	this.pw = pw;
    	this.name = name;
    	this.tel = tel;
    	this.email = email;
    	this.birth = birth;
    	this.adds = adds;
    	this.dupl = dupl;
    }
	public int getProfCode() {
		return profCode;
	}
	public void setProfCode(int profCode) {
		this.profCode = profCode;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getAdds() {
		return adds;
	}
	public void setAdds(String adds) {
		this.adds = adds;
	}
	public String getDupl() {
		return dupl;
	}
	public void setDupl(String dupl) {
		this.dupl = dupl;
	}
    
    
}
