package model;

public class ClassDTO {
	private int classNum;
	private String classCurri;
	private String classInfo;
	private int courseNum;
	private int profCode;
	ClassDTO(){}
	public int getClassNum() {
		return classNum;
	}
	public void setClassNum(int classNum) {
		this.classNum = classNum;
	}
	public String getClassCurri() {
		return classCurri;
	}
	public void setClassCurri(String classCurri) {
		this.classCurri = classCurri;
	}
	public String getClassInfo() {
		return classInfo;
	}
	public void setClassInfo(String classInfo) {
		this.classInfo = classInfo;
	}
	public int getCourseNum() {
		return courseNum;
	}
	public void setCourseNum(int courseNum) {
		this.courseNum = courseNum;
	}
	public int getProfCode() {
		return profCode;
	}
	public void setProfCode(int profCode) {
		this.profCode = profCode;
	}
	
}
