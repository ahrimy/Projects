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



// ������ Ÿ��Ʋ �̸� , ������ ��ü �̸� , �ǸŰ�  , 

public class Cart {
	
	String data = ""; // ���Ͽ� ������ ����Ÿ 
	
	public ArrayList<ItemInfo1> cartList = new ArrayList<ItemInfo1>(); // ��ٱ��� ��ü ���
	public ArrayList<ItemInfo1> buyList = new ArrayList<ItemInfo1>(); // üũ�� ���� ������ ��ǰ ���
	public ArrayList<ItemInfo2> elseInfo = new ArrayList<>(); 
	
	public void add(String imageFile , String itemTitleName ,String itemFullName ,int price , int count){
			if(findItem(itemFullName) != -1) { // �ߺ� ������
				int index = findItem(itemFullName);
				cartList.get(index).buyCount += count;
				
				/*ä���*/
				makeString(); // String data �߰�
				
//				FileManager.instance.saveCart(data, UserManager.usermanager.userList.get(UserManager.logIdx).userId + ".txt");
				// ���� ���� �߰�
				
				
			}else { // �ߺ� ������
				
				ItemInfo1 temp = new ItemInfo1(imageFile ,itemTitleName , itemFullName, price , count );
				cartList.add(temp);
				
				ItemInfo2 temp2 = new ItemInfo2();
				
				elseInfo.add(temp2);
				
				makeString(); // String data �߰�
				
//				FileManager.instance.saveCart(data, UserManager.usermanager.userList.get(UserManager.logIdx).userId + ".txt");
				// ���� ���� �߰�
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
	
	public int findItem(String itemFullName){ // �˻� ������ �����ִ� �޼ҵ� - > HashMap�� ArrayList ����
		int index = -1;
		for(int i = 0; i < cartList.size(); i++) {
			if(itemFullName.equals(cartList.get(i).itemFullName)) {
				index = i;
			}
		}
		return index;
	}
}
