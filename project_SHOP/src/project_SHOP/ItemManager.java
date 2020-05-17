package project_SHOP;

import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class ItemManager {
	HashMap<Item, Integer> itemList = new HashMap<>();
	Scanner scan = new Scanner(System.in);

	public Item search(String name) {
		Item check = null;
		for (Item key : itemList.keySet()) {
			if (key.getName().equals(name)) {
				check = key;
				break;
			}
		}
		return check;
	}

	public void loadItem(String fileData) {
		String data = fileData;
		if (data.length() > 0) {
			String item[] = data.split("\n");
			for (int i = 0; i < item.length; i++) {
				String[] temp = item[i].split(" ");
				String name = temp[0];
				int price = Integer.parseInt(temp[1]);
				int itemNum = Integer.parseInt(temp[2]);
				int cnt = Integer.parseInt(temp[3]);
				itemList.put(new Item(name, price, itemNum), cnt);
			}
		}
	}

	public String saveItem() {
		String data = "";
		for (Item key : itemList.keySet()) {
			data += key.getName();
			data += " ";
			data += key.getPrice();
			data += " ";
			data += key.getItemNum();
			data += " ";
			data += itemList.get(key);
			data += "\n";
		}
		if (data.length() > 0) {
			data = data.substring(0, data.length() - 1);
		}
		return data;
	}

	public void addItem() {
		System.out.print("Enter the item name : ");
		String name = scan.next();
		Item check = search(name);
		if (check == null) {
			System.out.print("Enter the item price : ");
			int price = scan.nextInt();
			System.out.print("Enter the number of items: ");
			int cnt = scan.nextInt();

			Random ran = new Random();
			int c = 0;
			int num = 0;
			while (true) {
				c = 0;
				num = ran.nextInt(9) + 1;
				for (int i = 0; i < 5; i++) {
					num *= 10;
					num += ran.nextInt(10);
				}
				for (Item key : itemList.keySet()) {
					if (key.getItemNum() == num) {
						c = 1;
						break;
					}
				}
				if (c == 0) {
					break;
				}
			}
			itemList.put(new Item(name, price, num), cnt);
		} else {
			System.out.println(name + " is already on the Item List");
		}
	}

	public void changeItem() {
		System.out.print("Enter the item name : ");
		String name = scan.next();
		Item check = search(name);
		if (check != null) {
			while (true) {
				printStoredItem();
				System.out.println();
				System.out.println("[1]change amount");
				System.out.println("[2]update name");
				System.out.println("[3]update price");
				System.out.println("[0]Back");
				int sel = scan.nextInt();
				if (sel == 1) {
					System.out.println();
					System.out.println("Current : " + check.getName() + " - " + itemList.get(check));
					System.out.print("Change Amount(+/-): ");
					int cnt = scan.nextInt();
					updateItem(check, cnt);

				} else if (sel == 2) {
					System.out.println("Enter the new name : ");
					String inputName = scan.next();
					check.setName(inputName);
				} else if (sel == 3) {
					System.out.println("Enter the new price : ");
					int inputPrice = scan.nextInt();
					check.setPrice(inputPrice);

				} else if (sel == 0) {
					printStoredItem();
					break;
				}

			}
		} else {
			System.out.println("This item is not on the list");
		}
	}

	public void updateItem(Item item, int cnt) {
		int current = itemList.get(item);
		itemList.replace(item, current + cnt);
	}

	public void deleteItem() {
		System.out.print("Enter the item name : ");
		String name = scan.next();
		Item check = search(name);
		if (check != null) {
			itemList.remove(check);
		} else {
			System.out.println(name + " is not on the Item List");
		}
	}

	public void printItems() {
		if (itemList.isEmpty()) {
			System.out.println();
			System.out.println("Item Storage is empty.");
		} else {
			for (Item key : itemList.keySet()) {
				key.print();
				System.out.println(" ");
			}
			System.out.println();
		}

	}

	public void printStoredItem() {
		if (itemList.isEmpty()) {
			System.out.println();
			System.out.println("Item Storage is empty.");
		} else {
			for (Item key : itemList.keySet()) {
				key.print();
				System.out.println(" : " + itemList.get(key));
			}
			System.out.println();
		}
	}

	public void printCnts() {
		if (itemList.isEmpty()) {
			System.out.println();
			System.out.println("Item Storage is empty.");
		} else {
			for (Item key : itemList.keySet()) {
				key.printName();
				System.out.println(" : " + itemList.get(key));

			}
			System.out.println();
		}
	}
}
