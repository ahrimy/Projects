package project_ATM;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class UserManager {
	ArrayList<User> users = new ArrayList<>();
	int cnt = 0;// number of users

	void join() {

		Scanner scan = new Scanner(System.in);
		System.out.print("ID : ");
		String inputID = scan.next();
		int check = -1;
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).id.equals(inputID)) {
				check = i;
				break;
			}
		}
		if (check == -1) {
			System.out.print("Password : ");
			String inputPW = scan.next();
			users.add(new User(inputID, inputPW));
			System.out.println("ID " + users.get(cnt).id + " has signed up");
			cnt++;

		} else {
			System.out.println("ID " + inputID + " already exists");
		}

	}

	int leave(int log) {
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter your password to delete your account in the system :");
		String inputPW = scan.next();
		if (users.get(log).pw.equals(inputPW)) {
			users.remove(log);
			cnt--;
			log = -1;
			System.out.println("Have deleted your account");
		}
		return log;
	}

	int signIn() {
		int check = -1;
		if (cnt > 0) {
			Scanner scan = new Scanner(System.in);
			System.out.print("ID : ");
			String inputID = scan.next();
			System.out.print("Password : ");
			String inputPW = scan.next();
			for (int i = 0; i < users.size(); i++) {
				if (users.get(i).id.equals(inputID)) {
					check = i;
					break;
				}
			}
			if (check == -1) {
				System.out.println(inputID + " is not existing");
			} else {
				if (users.get(check).pw.equals(inputPW)) {
					System.out.println(inputID + " is successfully signed in");
					users.get(check).printAll();
				} else {
					System.out.println("Password is not valid");
					check = -1;
				}
			}
		} else {
			System.out.println("No account in the system");
		}
		return check;

	}

	int signOut(int log) {
		System.out.println(users.get(log).id + " has successfully signed out");
		log = -1;
		return log;
	}

	void deposit(int log) {
		if (users.get(log).cnt > 0) {
			Scanner scan = new Scanner(System.in);
			System.out.print("Account number :");
			int input = scan.nextInt();
			int check = -1;
			for (int i = 0; i < users.get(log).cnt; i++) {
				if (users.get(log).accounts.get(i).accNum == input) {
					check = i;
				}
			}
			if (check == -1) {
				System.out.println(input + " is not valid");
			} else {
				System.out.print("Deposit amount :");
				input = scan.nextInt();
				users.get(log).accounts.get(check).balance += input;
				System.out.println("Complete the process");
			}
		} else {
			System.out.println("You don't have any account");
		}
	}

	void withdraw(int log) {
		if (users.get(log).cnt > 0) {
			Scanner scan = new Scanner(System.in);
			System.out.print("Account number :");
			int input = scan.nextInt();
			int check = -1;
			for (int i = 0; i < users.get(log).cnt; i++) {
				if (users.get(log).accounts.get(i).accNum == input) {
					check = i;
				}
			}
			if (check == -1) {
				System.out.println(input + " is not valid");
			} else {
				System.out.print("Withdraw amount :");
				input = scan.nextInt();
				if (input <= users.get(log).accounts.get(check).balance) {
					users.get(log).accounts.get(check).balance -= input;
					System.out.println("Complete the process");
				} else {
					System.out.println("Blanace is not enough");
				}
			}
		} else {
			System.out.println("You don't have any account");
		}
	}

	void transfer(int log) {
		if (users.get(log).cnt > 0) {
			Scanner scan = new Scanner(System.in);
			System.out.print("Account number(send) :");
			int input = scan.nextInt();
			int check = -1;
			int transID = -1;
			int transAcc = -1;
			for (int i = 0; i < users.get(log).cnt; i++) {
				if (users.get(log).accounts.get(i).accNum == input) {
					check = i;
				}
			}
			if (check == -1) {
				System.out.println(input + " is not existing account number");
			} else {
				System.out.print("Account number(receive) :");
				input = scan.nextInt();
				if (input == users.get(log).accounts.get(check).accNum) {
					System.out.println("You can't transfer to same account");
					transID = log;
					transAcc = check;
				} else {
					for (int i = 0; i < cnt; i++) {
						for (int j = 0; j < users.get(i).cnt; j++) {
							if (input == users.get(i).accounts.get(j).accNum) {
								transID = i;
								transAcc = j;
								break;
							}
						}
					}
				}
				if (transID == -1 || transAcc == -1) {
					System.out.println("Cannot find the receiving account");
				} else {
					System.out.print("Transfer amount: ");
					input = scan.nextInt();
					if (input <= users.get(log).accounts.get(check).balance) {
						users.get(log).accounts.get(check).balance -= input;
						users.get(transID).accounts.get(transAcc).balance += input;
						System.out.println("Complete the process");
					} else {
						System.out.println("Blanace is not enough");
					}
				}
			}
		} else {
			System.out.println("You don't have any account");
		}
	}

	void check(int log) {
		users.get(log).printAll();
	}

	void makeAccount(int log) {
		Random ran = new Random();

		while (true) {
			int check = 0;
			int r = ran.nextInt(9) + 1;
			for (int i = 0; i < 5; i++) {
				r *= 10;
				r += ran.nextInt(10);
			}
			for (int i = 0; i < cnt; i++) {
				for (int j = 0; j < users.get(i).cnt; j++) {
					if (r == users.get(i).accounts.get(j).accNum) {
						check = 1;
					}
				}
			}
			if (check == 0) {
				users.get(log).accounts.add(new Account(r));
				users.get(log).printPart(users.get(log).cnt);
				users.get(log).cnt++;
				break;
			}
		}
	}

	void deleteAccount(int log) {
		if (users.get(log).cnt > 0) {
			Scanner scan = new Scanner(System.in);
			System.out.print("Account number :");
			int input = scan.nextInt();
			int check = -1;
			for (int i = 0; i < users.get(log).cnt; i++) {
				if (users.get(log).accounts.get(i).accNum == input) {
					check = i;
				}
			}
			if (check == -1) {
				System.out.println(input + " is not valid");
			} else {
				users.get(log).accounts.remove(check);
				users.get(log).cnt--;
				System.out.println("Complete the process");
			}
		} else {
			System.out.println("You don't have any account");
		}
	}

}