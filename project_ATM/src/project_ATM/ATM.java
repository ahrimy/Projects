package project_ATM;

import java.util.Scanner;

public class ATM {
	FileManager file = new FileManager();
	UserManager manager = new UserManager();
	Scanner scan = new Scanner(System.in);
	int log = -1;

	void run() {
		manager.users = file.load();
		manager.cnt = manager.users.size();
		while (true) {
			System.out.println();
			System.out.println("[1]Sign in");
			System.out.println("[2]Sign up");
			System.out.println("[0]End");
			int sel = scan.nextInt();
			if (sel == 1) {
				log = manager.signIn();
				while (log != -1) {
					log = system(log);
					file.save(manager);
				}
			} else if (sel == 2) {
				manager.join();
			} else if (sel == 0) {
				break;
			}
			file.save(manager);
		}
	}
	int system(int log){
		System.out.println();
		System.out.println("[1]Deposit");
		System.out.println("[2]Withdrawal");
		System.out.println("[3]Transfer");
		System.out.println("[4]Check the Balance");
		System.out.println("[5]Make new Account");
		System.out.println("[6]Delete the account");
		System.out.println("[7]Delete the ID,PW");
		System.out.println("[8]Sign out");

		int choice = scan.nextInt();

		if (choice == 1) {
			manager.deposit(log);
		} else if (choice == 2) {
			manager.withdraw(log);
		} else if (choice == 3) {
			manager.transfer(log);
		} else if (choice == 4) {
			manager.check(log);
		} else if (choice == 5) {
			manager.makeAccount(log);
		} else if (choice == 6) {
			manager.deleteAccount(log);
		} else if (choice == 7) {
			log = manager.leave(log);
		} else if (choice == 8) {
			log = manager.signOut(log);
		}
		return log;
	}
}