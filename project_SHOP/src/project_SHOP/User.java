package project_SHOP;

public class User {
	private String name;
	private String userID;
	private String userPW;
	private int userNum;
	private boolean isCustomer;
	public Cart cart;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUserPW() {
		return userPW;
	}

	public void setUserPW(String userPW) {
		this.userPW = userPW;
	}

	public int getUserNum() {
		return userNum;
	}

	public void setUserNum(int userNum) {
		this.userNum = userNum;
	}

	public boolean isCustomer() {
		return isCustomer;
	}

	public void setCustomer(boolean isCustomer) {
		this.isCustomer = isCustomer;
	}

	public void infoList() {
		if (isCustomer) {
			System.out.println(name + " " + userID + " " + userPW + " " + userNum);
		}
	}

}
