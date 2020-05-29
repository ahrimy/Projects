package oliveYoung;

public class ItemInfo1 {
	String imageFile;	
	String itemTitleName;
	String itemFullName;
	int price;
	int buyCount;
	
	// sumPrice(수량 X 가격) 는 MidPanel에서 구현
	
	ItemInfo1(String imageFile , String itemTitleName , String itemFullName , int price , int count ) {
		this.imageFile = imageFile;
		this.itemTitleName = itemTitleName;
		this.itemFullName = itemFullName;
		this.price = price;
		this.buyCount += count;
	
		// sumPrice 는 MidPanel에서 구현
	}
}
