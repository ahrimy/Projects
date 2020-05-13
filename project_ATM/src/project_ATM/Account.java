package project_ATM;

public class Account {
	int accNum = 0;
	int balance = 0;
	Account(int accNum){
		this.accNum = accNum;
	}

	public Account(int accNum, int balance) {
		// TODO Auto-generated constructor stub
		this.accNum = accNum;
		this.balance = balance;
	}
}