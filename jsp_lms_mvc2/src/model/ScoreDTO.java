package model;

public class ScoreDTO {
	private int scoreNum;
	private long stuScore;
	private String status;
	private int testNum;
	private int stuCode;
	ScoreDTO(){}
	public int getScoreNum() {
		return scoreNum;
	}
	public void setScoreNum(int scoreNum) {
		this.scoreNum = scoreNum;
	}
	public long getStuScore() {
		return stuScore;
	}
	public void setStuScore(long stuScore) {
		this.stuScore = stuScore;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getTestNum() {
		return testNum;
	}
	public void setTestNum(int testNum) {
		this.testNum = testNum;
	}
	public int getstuCode() {
		return stuCode;
	}
	public void setstuCode(int stuCode) {
		this.stuCode = stuCode;
	}
	
	
	
}
