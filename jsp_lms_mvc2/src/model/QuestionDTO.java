package model;

public class QuestionDTO {
	private int queNum;
	private int testNum;
	private String question;
	private String queType;
	private String queOpts;
	private String queAnswer;
	private int queScore;
	public QuestionDTO() {}
	public QuestionDTO(int testNum, String question, String queType, String queOpts,  String queAnswer, int  queScore) {
		
		this.testNum = testNum;
		this.question = question;
		this.queType = queType;
		this.queOpts = queOpts;
		this.queAnswer = queAnswer;
		this.queScore = queScore;
	}
	public int getQueNum() {
		return queNum;
	}
	public void setQueNum(int queNum) {
		this.queNum = queNum;
	}
	public int getTestNum() {
		return testNum;
	}
	public void setTestNum(int testNum) {
		this.testNum = testNum;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getQueType() {
		return queType;
	}
	public void setQueType(String queType) {
		this.queType = queType;
	}
	public String getQueOpts() {
		return queOpts;
	}
	public void setQueOpts(String queOpts) {
		this.queOpts = queOpts;
	}
	public String getQueAnswer() {
		return queAnswer;
	}
	public void setQueAnswer(String queAnswer) {
		this.queAnswer = queAnswer;
	}
	public int getQueScore() {
		return queScore;
	}
	public void setQueScore(int queScore) {
		this.queScore = queScore;
	}

	
	
}
