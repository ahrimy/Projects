package model;

public class EnrollDTO {
	private int enrollNum;
	private int classNum;
	private int stuCode;
	EnrollDTO(){}
	public int getEnrollNum() {
		return enrollNum;
	}
	public void setEnrollNum(int enrollNum) {
		this.enrollNum = enrollNum;
	}
	public int getClassNum() {
		return classNum;
	}
	public void setClassNum(int classNum) {
		this.classNum = classNum;
	}
	public int getStuCode() {
		return stuCode;
	}
	public void setStuCode(int stuCode) {
		this.stuCode = stuCode;
	}
	
}
