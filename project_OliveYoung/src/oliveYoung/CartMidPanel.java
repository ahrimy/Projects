package oliveYoung;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class CartMidPanel extends JPanel implements ActionListener , ItemListener{
			// ���� 11 �̻�� ComboBox �����ϰ� TextField �ֱ� , Revalidate();
			// ���� 10 ���Ͻ� TextField �����ϱ� JComboBox �ֱ� , Revalidate();
			User user = UserManager.usermanager.userList.get(UserManager.logIdx);
			
			int logIdx = UserManager.usermanager.logIdx; 
			Cart cart = user.cart;		
			
			JButton payBtn; // �����ϱ� ��ư
			
			JCheckBox allCheck; // ��ü üũ
			JLabel label; // �� �����ݾ�
			
			int totalPrice;
			
			public CartMidPanel() {
				
				this.setLayout(null);
				allCheck = new JCheckBox("��üüũ" , true);
				
				allCheck.setBounds(100, 50, 100, 50);
				allCheck.addItemListener(this);
				add(allCheck);
				
				payBtn = new JButton("�����ϱ�");
				payBtn.setBackground(Color.PINK);
				payBtn.setBounds(1500, 220 + 220 * cart.cartList.size(), 300, 50);
				payBtn.addActionListener(this);
				add(payBtn);
				
				for(int i = 0; i < cart.cartList.size(); i++) {
					cart.buyList.add(cart.cartList.get(i));
					/*üũ�ڽ�*/
					cart.elseInfo.get(i).check.setBounds(100, 100 + 220 * i, 50, 50);
					cart.elseInfo.get(i).check.setSelected(true);
					cart.elseInfo.get(i).check.addItemListener(this);
					add(cart.elseInfo.get(i).check);//üũ�ڽ� �߰�
					
					/*�̹���*/
					JLabel imageLabel = new JLabel(makeImage(cart.cartList.get(i).imageFile));
					imageLabel.setBounds(300, 100 + 220 * i, 200, 200);
					add(imageLabel);
					
					/*ȸ�� �̸�*/
					JLabel titleLabel = new JLabel(cart.cartList.get(i).itemTitleName);
					titleLabel.setBounds(550, 100 + 220 * i, 200, 50);
					add(titleLabel);
					/*��ü �̸�*/
					JLabel fullLabel = new JLabel(cart.cartList.get(i).itemFullName);
					fullLabel.setBounds(550, 180 + 220 * i, 200, 50);
					add(fullLabel);
					
					/*�ǸŰ�*/
					JLabel priceLabel = new JLabel((cart.cartList.get(i).price + "��"));
					priceLabel.setBounds(800, 100 + 220 * i, 200, 50);
					add(priceLabel);
					
					/*����*/
					if(cart.cartList.get(i).buyCount > 10) {//���� 11�̻��ϰ��
						cart.elseInfo.get(i).putCount.setBounds(1000, 100 + 220 * i, 100, 50);
						cart.elseInfo.get(i).putCount.setText(cart.cartList.get(i).buyCount+"");
						cart.elseInfo.get(i).putCount.setHorizontalAlignment(JTextField.CENTER);
						cart.elseInfo.get(i).putCount.addActionListener(this);
						add(cart.elseInfo.get(i).putCount);
						cart.elseInfo.get(i).change.setBounds(1000, 150 + 220 * i, 100, 50);
						cart.elseInfo.get(i).change.setText("����");
						cart.elseInfo.get(i).change.addActionListener(this);
						add(cart.elseInfo.get(i).change);
					}else if(cart.cartList.get(i).buyCount <= 10) {// ���� 10�����ϰ��
						for(int j = 0; j < 10; j++) {
							cart.elseInfo.get(i).count.addItem(j+1+"");
						}
						cart.elseInfo.get(i).count.addItem("10+");
						cart.elseInfo.get(i).count.setSelectedIndex(cart.cartList.get(i).buyCount-1);
						cart.elseInfo.get(i).count.setBounds(1000, 100 + + 220 * i, 100, 50);
						((JLabel)cart.elseInfo.get(i).count.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);//�������
						cart.elseInfo.get(i).count.addActionListener(this);
						add(cart.elseInfo.get(i).count);
						
					}
					
					/*���Ű�*/
					cart.elseInfo.get(i).sum.setText((cart.cartList.get(i).buyCount * cart.cartList.get(i).price)+"��");
					cart.elseInfo.get(i).sum.setBounds(1200, 100  + 220 * i, 200, 50);
					add(cart.elseInfo.get(i).sum);
					
					/*�������*/
					JLabel fedex = new JLabel("������");
					fedex.setBounds(1300, 100  + 220 * i, 200, 50);
					add(fedex);
					
					Font font = new Font("" , Font.BOLD , 10);
					JLabel fedex2 = new JLabel("�������갣 ����");
					fedex2.setBounds(1300, 130 + 220 * i, 200, 70);
					fedex2.setFont(font);
					add(fedex2);
					
					/*����*/
					cart.elseInfo.get(i).buy.setText("�ٷα���");
					cart.elseInfo.get(i).buy.setBounds(1600, 80 + 220 * i, 200, 60);
					cart.elseInfo.get(i).buy.addActionListener(this);
					add(cart.elseInfo.get(i).buy);
					
					cart.elseInfo.get(i).delete.setText("����");
					cart.elseInfo.get(i).delete.setBounds(1600, 150 + 220 * i, 200, 60);
					cart.elseInfo.get(i).delete.addActionListener(this);
					add(cart.elseInfo.get(i).delete);
				}
				// UserManager.userList.get[logIdx] , , , ,
				JButton deleteButton = new JButton("���û�ǰ ����");
				deleteButton.setBounds(100, 220 + 220 * cart.cartList.size(), 200, 50);
				deleteButton.addActionListener(this);
				add(deleteButton);
				
				// totalPrice 
				Font font = new Font("" , Font.BOLD , 20);
				
				totalPrice = totalPrice();
				label = new JLabel("�� ���� �ݾ� : " + totalPrice + "��");
				label.setFont(font);
				label.setBounds(1000, 220 + 220 * cart.cartList.size(), 400, 100);
				add(label);	
			}
			
			public ImageIcon makeImage(String imageFile) { // �̹��� ������ ����� �޼ҵ�
				Image image = new ImageIcon(imageFile).getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
				return new ImageIcon(image);
			}

			public int totalPrice() {
				int price = 0;
				for(int i = 0; i < cart.buyList.size(); i++) {
					price += cart.buyList.get(i).price * cart.buyList.get(i).buyCount;
				}
				
				return price;
			}
			
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				
				// ���� ���� 
				for(int i = 0; i < cart.cartList.size(); i++) {
					if(e.getSource() == cart.elseInfo.get(i).count) {
						if(cart.elseInfo.get(i).count.getSelectedIndex() >=0 && cart.elseInfo.get(i).count.getSelectedIndex() < 10) {
						String temp = (String)cart.elseInfo.get(i).count.getSelectedItem();
						cart.cartList.get(i).buyCount = Integer.parseInt(temp);
						cart.elseInfo.get(i).sum.setText(cart.cartList.get(i).buyCount * cart.cartList.get(i).price+"��");
						
						for(int j = 0; j < cart.cartList.size(); j++) {
							for(int k = 0; k < cart.buyList.size(); k++) {
								if(cart.cartList.get(j).itemFullName.equals(cart.buyList.get(k).itemFullName)) {
									cart.buyList.get(k).buyCount = cart.cartList.get(j).buyCount;
								}
							}
						}
						
						totalPrice = totalPrice();
						label.setText("�� ���� �ݾ� : " + totalPrice + "��");
						}else if(cart.elseInfo.get(i).count.getSelectedIndex() == 10) {
							
						}
					}
				}
				
				if(e.getSource() == payBtn) {
					PurchasePanel purchasePanel = new PurchasePanel(cart.buyList);
					JScrollPane scroll = new JScrollPane();
					Dimension size = new Dimension();
					size.setSize(1920, 1060
							+ (100 * UserManager.usermanager.getBuyListSize()));
					purchasePanel.setPreferredSize(size);
					scroll.setViewportView(purchasePanel);
					Main.frame.setContentPane(scroll);
					Main.frame.revalidate();
				}
				
			}

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				
				for(int i = 0; i < cart.cartList.size(); i++) {
					if(e.getItem() == cart.elseInfo.get(i).check) {
					if(e.getStateChange() == ItemEvent.SELECTED) {
							cart.buyList.add(cart.cartList.get(i));
							
						}else if(e.getStateChange() == ItemEvent.DESELECTED) {
							cart.buyList.remove(cart.cartList.get(i));
							
						}
						totalPrice = totalPrice();
						label.setText("�� ���� �ݾ� : " + totalPrice + "��");
						
					}
				}
				
				if(e.getItem() == allCheck) {
					if(e.getStateChange() == ItemEvent.SELECTED) {
						for(int i = 0; i < cart.cartList.size(); i++) {
							cart.elseInfo.get(i).check.setSelected(true);
						}
					}else if(e.getStateChange() == ItemEvent.DESELECTED) {
						for(int i = 0; i < cart.cartList.size(); i++) {
							cart.elseInfo.get(i).check.setSelected(false);
						}
					}
				}
			}
			
			
			
			
}
