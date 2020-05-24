package oliveYoung;

public class Item {
	private String category;
	private String itemName; // 상품명
	private String imageName;
	private int price; // 가격
	// static int systemCount; // 상품재고
	private int count; // 유저 구매수량
	private boolean onlyOnline;

	public Item() {
	}

	public Item(String category, String itemName, String imageName, int price, int count, boolean onlyOnline) {
		this.category = category;
		this.itemName = itemName;
		this.imageName = imageName;
		this.price = price;
		this.count = count;
		this.onlyOnline = onlyOnline;
	}

	public boolean isOnlyOnline() {
		return onlyOnline;
	}

	public void setOnlyOnline(boolean onlyOnline) {
		this.onlyOnline = onlyOnline;
	}

	public String getItemName() {
		return itemName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void updateCount(int count) {
		this.count += count;
	}

}
