package project_SHOP;

public class Customer extends User {

	public Customer(String name, String userID, String userPW, int userNum) {
		// TODO Auto-generated constructor stub
		super.setName(name);
		super.setUserID(userID);
		super.setUserPW(userPW);
		super.setUserNum(userNum);
		super.setCustomer(true);
		super.cart = new Cart();

	}

}
