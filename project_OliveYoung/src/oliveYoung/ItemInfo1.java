package oliveYoung;

public class ItemInfo1 {
	String imageFile;	
	String itemTitleName;
	String itemFullName;
	int price;
	int buyCount;
	
	// sumPrice(���� X ����) �� MidPanel���� ����
	
	ItemInfo1(String imageFile , String itemTitleName , String itemFullName , int price , int count ) {
		this.imageFile = imageFile;
		this.itemTitleName = itemTitleName;
		this.itemFullName = itemFullName;
		this.price = price;
		this.buyCount += count;
	
		// sumPrice �� MidPanel���� ����
	}
}
