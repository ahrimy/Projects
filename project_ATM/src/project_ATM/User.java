package project_ATM;

import java.util.ArrayList;

public class User {
	ArrayList<Account> accounts = new ArrayList<>();
	String id = "";
	String pw = "";
	int cnt = 0;// Number of accounts

	public User(String inputID, String inputPW) {
		this.id = inputID;
		this.pw = inputPW;
	}

	void printAll() {
		if (cnt > 0) {
			System.out.println("ID : " + id);
			for (int i = 0; i < cnt; i++) {
				System.out.println(accounts.get(i).accNum + " : " + accounts.get(i).balance);
			}
		} else {
			System.out.println("You dont have any account");
		}
	}

	void printPart(int num) {
		System.out.println("ID : " + id);
		System.out.println(accounts.get(num).accNum + " : " + accounts.get(num).balance);
	}

}