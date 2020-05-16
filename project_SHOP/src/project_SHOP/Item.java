package project_SHOP;

public class Item {
	private String name;
	private int price;
	private int itemNum;

	public Item(String name, int price, int itemNum) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.price = price;
		this.itemNum = itemNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getItemNum() {
		return itemNum;
	}

	public void setItemNum(int itemNum) {
		this.itemNum = itemNum;
	}

	public void print() {
		System.out.print(name + " : " + price);
	}

	public void printName() {
		System.out.print(name);
	}

}
