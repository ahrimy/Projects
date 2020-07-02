package model;

public class TestDTO {
	private int testNum;
	private String testTitle;
	private String testDesc;
	private long testScore;
	private int classNum;
	private int profCode;
	public TestDTO() {}
	public TestDTO(String testTitle, String testDesc,int classNum, int profCode){
		
		this.testTitle = testTitle;
		this.testDesc = testDesc;
		this.profCode = profCode;
		this.classNum = classNum;
	}
	public int getTestNum() {
		return testNum;
	}
	public void setTestNum(int testNum) {
		this.testNum = testNum;
	}
	public String getTestTitle() {
		return testTitle;
	}
	public void setTestTitle(String testTitle) {
		this.testTitle = testTitle;
	}
	public String getTestDesc() {
		return testDesc;
	}
	public void setTestDesc(String testDesc) {
		this.testDesc = testDesc;
	}
	public long getTestScore() {
		return testScore;
	}
	public void setTestScore(long testScore) {
		this.testScore = testScore;
	}
	public int getClassNum() {
		return classNum;
	}
	public void setClassNum(int classNum) {
		this.classNum = classNum;
	}
	public int getProfCode() {
		return profCode;
	}
	public void setProfCode(int profCode) {
		this.profCode = profCode;
	}

	
	
	
}
