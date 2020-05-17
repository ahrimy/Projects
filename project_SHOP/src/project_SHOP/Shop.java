package project_SHOP;

import java.util.Scanner;

public class Shop {
	Scanner scan = new Scanner(System.in);
	UserManager userControl;

	private int log;

	Shop() {
		userControl = new UserManager();
		log = -1;
		userControl.loadItem();
		userControl.loadUser();
	}

	public void run() {
		while (true) {
			System.out.println("[1]Login");
			System.out.println("[2]Join");
			System.out.println("[0]End");
			int input = scan.nextInt();
			if (input == 1) {
				log = userControl.signIn();
				while (log != -1) {
					if (userControl.userList.get(log).isCustomer()) {
						System.out.println("---------------------------------------");
						System.out.println("[[Customer Page]]");
						log = shopping(log);
					} else {
						System.out.println("---------------------------------------");
						System.out.println("[[Manager Page]]");
						log = managing(log);
					}
					// file.save(manager);
				}
			} else if (input == 2) {

				System.out.println("Are you customer(true/false)? ");
				boolean answer = scan.nextBoolean();
				userControl.join(answer);
			} else if (input == 0) {
				userControl.saveItem();
				userControl.saveUser();
				break;
			}

		}
	}

	public int shopping(int log) {
		System.out.println();
		System.out.println("[1]Item list");
		System.out.println("[2]My Cart");
		System.out.println("[3]My Page");
		System.out.println("[0]Sign out");
		int choice = scan.nextInt();

		if (choice == 1) {
			userControl.shopping(log);
		} else if (choice == 2) {
			userControl.cartManaging(log);

		} else if (choice == 3) {
			while (true) {
				System.out.println("[1]Update personal information");
				System.out.println("[2]Cancel the account");
				System.out.println("[0]Back");
				int sel = scan.nextInt();
				if (sel == 1) {
					userControl.update(log);
				} else if (sel == 2) {
					log = userControl.leave(log);
					break;
				} else if (sel == 0) {
					break;
				}
			}
		} else if (choice == 0) {
			log = userControl.signOut(log);
		}
		return log;
	}

	public int managing(int log) {
		System.out.println();
		System.out.println("[1]Item");
		System.out.println("[2]Customer");
		System.out.println("[0]Sign out");
		int choice = scan.nextInt();

		if (choice == 1) {
			userControl.itemManaging();
		} else if (choice == 2) {
			userControl.customerManaging();

		} else if (choice == 0) {
			log = userControl.signOut(log);
		}
		return log;
	}

}
