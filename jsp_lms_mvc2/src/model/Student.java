package model;

import java.util.ArrayList;

public class Student {
	
	private int stuCode;
	private int grade;	
	private String pw;
	private String name;
	private String tel;
	private String email;
	private String birth;
	private String adds;
	private String major;
	private String dupl;
	
	private ArrayList<EnrollDTO> myClassList;
	private ArrayList<EnrollDTO> myClassCart;
	
	
	public Student() {}
	public Student(int stuCode, int grade, String pw, String name, String tel, String email, String birth, String adds, String major) {
		setStuCode(stuCode);
		setGrade(grade);
		setPw(pw);
		setName(name);
		setTel(tel);
		setEmail(email);
		setBirth(birth);
		setAdds(adds);
		setMajor(major);
		setDupl(name + tel.substring(tel.length() -4));
	}
	
	public String getDupl() {
		return dupl;
	}
	public void setDupl(String dupl) {
		this.dupl = dupl;
	}
	public int getStuCode() {
		return stuCode;
	}
	public void setStuCode(int stuCode) {
		this.stuCode = stuCode;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
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
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public ArrayList<EnrollDTO> getMyClassList() {
		return myClassList;
	}
	public void setMyClassList(ArrayList<EnrollDTO> myClassList) {
		this.myClassList = myClassList;
	}
	public ArrayList<EnrollDTO> getMyClassCart() {
		return myClassCart;
	}
	public void setMyClassCart(ArrayList<EnrollDTO> myClassCart) {
		this.myClassCart = myClassCart;
	}

}
