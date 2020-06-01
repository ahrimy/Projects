package oliveYoung;

public class ItemInfo1 extends Item{
	//item.getImageName(), item.getItemTitle(), item.getItemName(), item.getPrice(), item.getCount() , today
	String imageFile;	
	String itemTitleName;
	String itemFullName;
	int price;
	int buyCount;
	boolean today; // ���� ��� ����
	// ī�װ��� �����ۿ��� ��ӹ޾���.
	
	// sumPrice(���� X ����) �� MidPanel���� ����
	public ItemInfo1() {
		
	}
	ItemInfo1(String imageFile , String itemTitleName , String itemFullName , int price , int count , boolean today ) {
		this.imageFile = imageFile;
		this.itemTitleName = itemTitleName;
		this.itemFullName = itemFullName;
		this.price = price;
		this.buyCount += count;
		this.today = today;
		// sumPrice �� MidPanel���� ����
	}
	//ī�װ� �κ� ����
	ItemInfo1(String imageFile , String itemTitleName , String itemFullName , int price , int count , boolean today ,String category) {
		this.imageFile = imageFile;
		this.itemTitleName = itemTitleName;
		this.itemFullName = itemFullName;
		this.price = price;
		this.buyCount += count;
		this.today = today;
		// sumPrice �� MidPanel���� ����
		super.setCategory(category);
	}
	
	
	//���Ϸε��
	public void initLoad(String imageFile , String itemTitleName , String itemFullName , int price , int buyCount , boolean today ) {
		this.imageFile = imageFile;
		this.itemTitleName = itemTitleName;
		this.itemFullName = itemFullName;
		this.price = price;
		this.buyCount = buyCount;
		this.today = today;
		// sumPrice �� MidPanel���� ����
	}
	
	@Override
	public String getImageName() {
		return imageFile;
	}
	
	@Override
	public String getItemName() {
		return itemFullName;
	}
	@Override
	public String getItemTitle() {
		return itemTitleName;
	}
	@Override
	public int getPrice() {
		return price;
	}
	
	@Override
	public int getCount() {
		return buyCount;
	}
}
