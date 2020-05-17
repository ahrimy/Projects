package project_SHOP;

import java.util.HashMap;
import java.util.Scanner;

public class Cart {
	Scanner scan = new Scanner(System.in);
	HashMap<Item, Integer> list = new HashMap<>();
	int price;
	boolean isChanged = false;

	public void cartList() {
		if (list.isEmpty()) {
			System.out.println();
			System.out.println("Cart is empty.");
		} else {
			for (Item key : list.keySet()) {
				System.out.println(key.getName() + " : " + key.getPrice() + " * " + list.get(key));
			}
			System.out.println();
			System.out.println("Total price : " + price);
		}
	}
	public String saveCart(){
		String data = list.size()+"\n";
		for(Item key : list.keySet()){
			data+=key.getName();
			data+=" ";
			data+=list.get(key);
			data+="\n";
		}
		return data;
	}
	public Item search(String name) {
		Item check = null;
		for (Item key : list.keySet()) {
			if (key.getName().equals(name)) {
				check = key;
				break;
			}
		}
		return check;
	}

	public void addtoCart(Item item, int cnt) {
		Item check = null;
		for (Item key : list.keySet()) {
			if (key.getItemNum() == item.getItemNum()) {
				check = key;
				break;
			}
		}
		if (check == null) {
			list.put(item, cnt);
		} else {
			int current = list.get(check);
			list.replace(check, current + cnt);
		}
		price += item.getPrice() * cnt;
	}

	public void dropItem() {
		System.out.println("Enter the item name you want to drop :");
		String name = scan.next();
		Item check = search(name);
		if (check == null) {
			System.out.println("This item is not in the cart");
		} else {
			list.remove(check);
		}
		price -= check.getPrice() * list.get(check);

	}

	public void updateAmount() {
		System.out.println("Enter the item name you want to change amount :");
		String name = scan.next();
		Item check = search(name);
		if (check == null) {
			System.out.println("This item is not in the cart");
		} else {
			System.out.println("Amount :");
			int change = scan.nextInt();
			int current = list.get(check);
			list.replace(check, current + change);
		}
	}

	public void buyAll() {
		for (Item key : list.keySet()) {
			System.out.println(key.getName() + " - " + key.getPrice() + " " + key);
		}
		System.out.println("Total prices : " + price);
	}
}
