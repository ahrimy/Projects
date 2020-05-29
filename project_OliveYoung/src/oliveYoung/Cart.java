package oliveYoung;
import java.awt.Color;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import java.util.HashMap;



// 아이템 타이틀 이름 , 아이템 전체 이름 , 판매가  , 

public class Cart {
	
	String data = ""; // 파일에 저장할 데이타 
	
	public ArrayList<ItemInfo1> cartList = new ArrayList<ItemInfo1>(); // 장바구니 전체 목록
	public ArrayList<ItemInfo1> buyList = new ArrayList<ItemInfo1>(); // 체크된 실제 구매할 상품 목록
	public ArrayList<ItemInfo2> elseInfo = new ArrayList<>(); 
	
	public void add(String imageFile , String itemTitleName ,String itemFullName ,int price , int count){
			if(findItem(itemFullName) != -1) { // 중복 있을때
				int index = findItem(itemFullName);
				cartList.get(index).buyCount += count;
				
				/*채우기*/
				makeString(); // String data 추가
				
//				FileManager.instance.saveCart(data, UserManager.usermanager.userList.get(UserManager.logIdx).userId + ".txt");
				// 파일 저장 추가
				
				
			}else { // 중복 없을때
				
				ItemInfo1 temp = new ItemInfo1(imageFile ,itemTitleName , itemFullName, price , count );
				cartList.add(temp);
				
				ItemInfo2 temp2 = new ItemInfo2();
				
				elseInfo.add(temp2);
				
				makeString(); // String data 추가
				
//				FileManager.instance.saveCart(data, UserManager.usermanager.userList.get(UserManager.logIdx).userId + ".txt");
				// 파일 저장 추가
			}
	}
	
	public void makeString(){
		data = "";
		data += cartList.size();
		data += "\n";
		for(int i = 0; i < cartList.size(); i++) {
			data += cartList.get(i).imageFile;
			data += "%";
			data += cartList.get(i).itemTitleName;
			data += "%";
			data += cartList.get(i).itemFullName;
			data += "%";
			data += cartList.get(i).price;
			data += "%";
			data += cartList.get(i).buyCount;
			if(i != cartList.size() - 1) {
			data += "\n";
			}
		}
	}
	
	public int findItem(String itemFullName){ // 검색 빠르게 도와주는 메소드 - > HashMap과 ArrayList 연결
		int index = -1;
		for(int i = 0; i < cartList.size(); i++) {
			if(itemFullName.equals(cartList.get(i).itemFullName)) {
				index = i;
			}
		}
		return index;
	}
}
