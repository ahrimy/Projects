package project_SHOP;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class UserManager {
	ArrayList<User> userList = new ArrayList<>();
	Scanner scan = new Scanner(System.in);
	ItemManager items = new ItemManager();
	FileManager files = new FileManager();
	int customer = 0;
	int manager = 0;

	public void saveItem() {
		String data = "";
		data = items.saveItem();
		files.save(data, "item.txt");

	}

	public void loadItem() {
		String data = files.load("item.txt");
		items.loadItem(data);
	}

	public void saveUser() {
		String data = userList.size()+"\n";
		for (int i = 0; i < userList.size(); i++) {
			data += userList.get(i).getName();
			data += " ";
			data += userList.get(i).getUserID();
			data += " ";
			data += userList.get(i).getUserPW();
			data += " ";
			data += userList.get(i).getUserNum();
			data += " ";
			data += userList.get(i).isCustomer();
			data += "\n";
			if (userList.get(i).isCustomer()) {
				data += userList.get(i).cart.saveCart();
			}
		}
		data = data.substring(0, data.length() - 1);
		files.save(data, "user.txt");
	}

	public void loadUser() {
		String data = files.load("user.txt");
		customer=0;
		manager=0;
		if (data.length() > 0) {
			String[] info = data.split("\n");
			int size = Integer.parseInt(info[0]);
			int listReader = 1;
			for (int i = 0; i < size; i++) {
				String temp[] = info[listReader++].split(" ");
				System.out.println(temp[0]);
				String name = temp[0];
				String userID = temp[1];
				String userPW = temp[2];
				int userNum = Integer.parseInt(temp[3]);
				boolean isCustomer = Boolean.parseBoolean(temp[4]);
				if (isCustomer) {
					User tempCustomer = new Customer(name, userID, userPW, userNum);
					int cartSize = Integer.parseInt(info[listReader++]);
					for (int j = 0; j < cartSize; j++) {
						temp = info[listReader++].split(" ");
						String itemName = temp[0];
						int cnt = Integer.parseInt(temp[1]);
						Item check = items.search(itemName);
						tempCustomer.cart.list.put(check, cnt);
						tempCustomer.cart.price+=check.getPrice()*cnt;
					}
					userList.add(tempCustomer);
					customer++;
				} else {
					userList.add(new Manager(name, userID, userPW, userNum));
					manager++;
				}
			}
		}
	}

	public void join(boolean answer) {
		if (answer && (manager == 0)) {
			System.out.println("Shop must have at least one manager");
		} else if (!answer && (manager == 2)) {
			System.out.println("Shop allows only two managers. Shop already have two managers.");
		} else {
			System.out.println();
			System.out.println("[[Personal Information]]");
			System.out.print("Name:");
			String name = scan.next();
			String inputID = "";
			int check = -1;
			do {
				check = -1;
				System.out.print("ID:");
				inputID = scan.next();
				for (int i = 0; i < userList.size(); i++) {
					if (userList.get(i).getUserID().equals(inputID)) {
						check = i;
						System.out.println("ID " + inputID + " already exists");
						break;
					}
				}
			} while (check != -1);
			String inputPW = "";
			while (inputPW.length() < 10) {
				System.out.print("Password(Length has to be longer than 10 characters) : ");
				inputPW = scan.next();
			}

			Random ran = new Random();
			int num = 0;
			while (true) {
				check = 0;
				num = ran.nextInt(9) + 1;
				for (int i = 0; i < 5; i++) {
					num *= 10;
					num += ran.nextInt(10);
				}
				for (int i = 0; i < userList.size(); i++) {
					if (num == userList.get(i).getUserNum()) {
						check = 1;
					}
				}
				if (check == 0) {
					break;
				}
			}
			if (answer) {
				userList.add(new Customer(name, inputID, inputPW, num));
				customer++;
			} else {
				userList.add(new Manager(name, inputID, inputPW, num));
				manager++;
			}
		}
		System.out.println();
		saveUser();
	}

	public int leave(int log) {

		// TODO Auto-generated method stub
		if (userList.get(log).isCustomer()) {
			// 장바구니 개수 올라가야함
			userList.remove(log);
			customer--;
			log = -1;
		}
		System.out.println();
		System.out.println(userList.get(log).getName() + " has deleted from the shop system.");
		System.out.println();
		saveUser();
		return log;
	}

	public int signIn() {
		System.out.println();
		System.out.println("[[Sign In]]");
		int check = -1;
		if (userList.size() > 0) {
			while (true) {
				System.out.print("ID : ");
				String inputID = scan.next();
				for (int i = 0; i < userList.size(); i++) {
					if (userList.get(i).getUserID().equals(inputID)) {
						check = i;
						break;
					}
				}
				if (check == -1) {
					System.out.println(inputID + " is not existing");
				} else {
					System.out.print("Password : ");
					String inputPW = scan.next();
					if (userList.get(check).getUserPW().equals(inputPW)) {
						System.out.println(inputID + " is successfully signed in");
						break;
					} else {
						System.out.println("Password is not valid");
						check = -1;
					}
				}
			}
		} else {
			System.out.println("No account in the system");
		}
		return check;
	}

	public int signOut(int log) {
		System.out.println();
		System.out.println(userList.get(log).getUserID() + " has successfully signed out");
		log = -1;
		return log;
	}

	public void update(int log) {
		while (true) {
			System.out.println("[[My Page]]");
			userList.get(log).infoList();
			System.out.println();
			System.out.println("[1]Name update");
			System.out.println("[2]Password update");
			System.out.println("[0]Back");
			int input = scan.nextInt();
			if (input == 1) {
				System.out.print("Enter your new name");
				String name = scan.next();
				userList.get(log).setName(name);
			} else if (input == 2) {
				String inputPW = "";
				while (inputPW.length() < 10) {
					System.out.print("Enter your new Password(Length has to be longer than 10 characters) : ");
					inputPW = scan.next();
				}
				userList.get(log).setUserPW(inputPW);
			} else if (input == 0) {
				break;
			}
		}
		saveUser();
	}

	public void shopping(int log) {
		// TODO Auto-generated method stub
		System.out.println();
		System.out.println("[[Item List]]");
		HashMap<Item, Integer> tempList = new HashMap<>();
		while (true) {
			items.printItems();
			System.out.println("[1]Select");
			System.out.println("[2]Deselect");
			System.out.println("[3]Add to Cart");
			System.out.println("[0]Back");
			int sel = scan.nextInt();
			if (sel == 1) {
				System.out.println("Item name : ");
				String name = scan.next();
				Item check = items.search(name);
				int itemCnt = items.itemList.get(check);
				if (check == null) {
					System.out.println("This item is not on the list");
				} else {
					System.out.println("The number of items : ");
					int cnt = scan.nextInt();
					if (cnt <= itemCnt) {
						Item checkSub = null;
						for (Item key : tempList.keySet()) {
							if (check.getItemNum() == key.getItemNum()) {
								checkSub = key;
							}
						}
						if (checkSub != null) {
							int current = tempList.get(checkSub);
							cnt = current + cnt;
						}
						tempList.put(check, cnt);
					} else {
						System.out.println("Item stock is not enough");
					}
				}

			} else if (sel == 2) {
				Item check = null;
				System.out.println("Item name : ");
				String name = scan.next();
				for (Item key : tempList.keySet()) {
					if (key.getName().equals(name)) {
						check = key;
					}
				}
				if (check == null) {
					System.out.println("You haven't chose this item");
				} else {
					tempList.remove(check);
				}

			} else if (sel == 3) {
				for (Item key : tempList.keySet()) {
					int itemCnt = items.itemList.get(key);
					int cnt = tempList.get(key);
					if (itemCnt >= cnt) {
						userList.get(log).cart.addtoCart(key, tempList.get(key));
						items.updateItem(key, -tempList.get(key));
					} else {
						System.out.println("Item is not enough");
					}
				}
				tempList.clear();
			} else if (sel == 0) {

				break;
			}
			System.out.println("[Selected Item]");
			for (Item key : tempList.keySet()) {
				System.out.println(key.getName() + " * " + tempList.get(key));
			}
			System.out.println();
			saveUser();
			saveItem();
		}

	}

	public void cartManaging(int log) {
		// TODO Auto-generated method stub
		System.out.println();
		System.out.println("[[My Cart]]");
		while (true) {
			userList.get(log).cart.cartList();
			System.out.println("[1]Add amount");
			System.out.println("[2]Delete item");
			System.out.println("[3]Buy items");
			System.out.println("[0]Back");
			int sel = scan.nextInt();
			if (sel == 1) {
				userList.get(log).cart.updateAmount();
			} else if (sel == 2) {
				userList.get(log).cart.dropItem();
			} else if (sel == 3) {
				userList.get(log).cart.buyAll();
			} else if (sel == 0) {
				break;
			}
		}
		saveUser();
		saveItem();
	}

	public void itemManaging() {
		// TODO Auto-generated method stub
		System.out.println();
		System.out.println("[[Item Management]]");
		while (true) {
			items.printStoredItem();
			System.out.println("[1]Add item");
			System.out.println("[2]update item");
			System.out.println("[3]Delete item");
			System.out.println("[0]Back");
			int sel = scan.nextInt();
			if (sel == 1) {
				items.addItem();
			} else if (sel == 2) {
				items.changeItem();
			} else if (sel == 3) {
				items.deleteItem();
			} else if (sel == 0) {
				break;
			}
		}
		saveItem();
	}

	public void customerList() {

		for (int i = 0; i < userList.size(); i++) {
			userList.get(i).infoList();
		}

	}

	public void customerManaging() {
		// TODO Auto-generated method stub
		System.out.println();
		System.out.println("[[Customer Management]]");
		while (true) {
			customerList();
			System.out.println("[1]Add customer");
			System.out.println("[2]Update customer");
			System.out.println("[3]Delete customer");
			System.out.println("[0]Back");
			int sel = scan.nextInt();
			if (sel == 1) {
				join(true);
			} else if (sel == 2) {
				System.out.println("Enter the customer name");
				String name = scan.next();
				int num = -1;
				for (int i = 0; i < userList.size(); i++) {
					if (userList.get(i).getName().equals(name) && userList.get(i).isCustomer()) {
						num = i;
						break;
					}
				}
				if (num == -1) {
					System.out.println("This customer is not in the customer list");
				} else {
					update(num);
				}
			} else if (sel == 3) {
				System.out.println("Enter the customer name");
				String name = scan.next();
				int num = -1;
				for (int i = 0; i < userList.size(); i++) {
					if (userList.get(i).getName().equals(name) && userList.get(i).isCustomer()) {
						num = i;
						break;
					}
				}
				if (num == -1) {
					System.out.println("This customer is not in the customer list");
				} else {
					leave(num);
				}
			} else if (sel == 0) {
				break;
			}
		}
		saveUser();
	}

}
