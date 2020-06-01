package oliveYoung;

import java.util.ArrayList;




// ������ Ÿ��Ʋ �̸� , ������ ��ü �̸� , �ǸŰ�  , 

public class Cart {
	
	String data = ""; // ���Ͽ� ������ ����Ÿ 
	
	public ArrayList<ItemInfo1> cartList = new ArrayList<ItemInfo1>(); // ��ٱ��� ��ü ���
	public ArrayList<ItemInfo1> buyList = new ArrayList<ItemInfo1>(); // üũ�� ���� ������ ��ǰ ���
	public ArrayList<ItemInfo2> elseInfo = new ArrayList<>(); 
	//ī�װ� �κ� ����
	public void add(String imageFile , String itemTitleName ,String itemFullName ,int price , int count , boolean today,String category){
			if(findItem(itemFullName) != -1) { // �ߺ� ������
				int index = findItem(itemFullName);
				cartList.get(index).today = today;
				cartList.get(index).buyCount += count;
				
				/*ä���*/
			
				
				
			}else { // �ߺ� ������
				
				ItemInfo1 temp = new ItemInfo1(imageFile ,itemTitleName , itemFullName, price , count , today,category);
				cartList.add(temp);
				
				ItemInfo2 temp2 = new ItemInfo2();
				
				elseInfo.add(temp2);
				
				
				
//				FileManager.instance.saveCart(data, UserManager.usermanager.userList.get(UserManager.logIdx).userId + ".txt");
				// ���� ���� �߰�
			}
	}
	
	
	public int findItem(String itemFullName){ // �˻� ������ �����ִ� �޼ҵ� - > HashMap�� ArrayList ����
		int index = -1;
		for(int i = 0; i < cartList.size(); i++) {
			if(itemFullName.equals(cartList.get(i).itemFullName)) {
				index = i;
			}
		}
		return index;
	}
	
	
	public void loadCart(String imageFile , String itemTitleName ,String itemFullName ,int price , int buyCount , boolean today,String category) {
		if(findItem(itemFullName) == -1) {
		ItemInfo1 temp = new ItemInfo1();
		temp.initLoad(imageFile, itemTitleName, itemFullName, price, buyCount, today);
		temp.setCategory(category);
		cartList.add(temp);
		
		ItemInfo2 temp2 = new ItemInfo2();
		
		elseInfo.add(temp2);
		}
	}
	
}
