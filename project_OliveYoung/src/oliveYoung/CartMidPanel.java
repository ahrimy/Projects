package oliveYoung;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import oliveYoung.ItemManager;
import oliveYoung.UserManager;
import oliveYoung.User;

public class CartMidPanel extends JPanel implements ActionListener, ItemListener {
	// ���� 11 �̻�� ComboBox �����ϰ� TextField �ֱ� , Revalidate();
	// ���� 10 ���Ͻ� TextField �����ϱ� JComboBox �ֱ� , Revalidate();
	int n = 0;
	User user = UserManager.usermanager.userList.get(UserManager.logIdx);

	int logIdx = UserManager.usermanager.logIdx;
	Cart cart = user.cart;

	JCheckBox allCheck; // ��ü üũ
	JLabel label; // �� �����ݾ�
	JButton deleteButton; // ���û�ǰ ����
	int totalPrice; // ��ü����
	JButton payBtn; // ���� ��ư
	JLabel todaySend; // ���� �帲 ��ǰ

	public CartMidPanel() {

		set();

	}

	public void set() {
		this.setLayout(null);
		allCheck = new JCheckBox("��üüũ", true);

		allCheck.setBounds(100, 50, 100, 50);
		allCheck.addItemListener(this);
		add(allCheck);

		for (int i = 0; i < cart.cartList.size(); i++) {

			/* üũ�ڽ� */
			cart.elseInfo.get(i).check.setBounds(100, 100 + 220 * i, 50, 50);
			cart.elseInfo.get(i).check.setSelected(true);
			cart.elseInfo.get(i).check.addItemListener(this);
			add(cart.elseInfo.get(i).check);// üũ�ڽ� �߰�
			System.out.println(
					"buyList ����3 : " + UserManager.usermanager.userList.get(UserManager.logIdx).cart.buyList.size());
			/* �̹��� */
			JLabel imageLabel = new JLabel(makeImage(cart.cartList.get(i).imageFile));
			imageLabel.setBounds(300, 100 + 220 * i, 200, 200);
			add(imageLabel);
			System.out.println(
					"buyList ����4 : " + UserManager.usermanager.userList.get(UserManager.logIdx).cart.buyList.size());
			/* ȸ�� �̸� */
			JLabel titleLabel = new JLabel(cart.cartList.get(i).itemTitleName);
			titleLabel.setBounds(550, 100 + 220 * i, 200, 50);
			add(titleLabel);
			/* ��ü �̸� */
			JLabel fullLabel = new JLabel(cart.cartList.get(i).itemFullName);
			fullLabel.setBounds(550, 180 + 220 * i, 200, 50);
			add(fullLabel);
			System.out.println(
					"buyList ����5 : " + UserManager.usermanager.userList.get(UserManager.logIdx).cart.buyList.size());
			/* �ǸŰ� */
			JLabel priceLabel = new JLabel((cart.cartList.get(i).price + "��"));
			priceLabel.setBounds(800, 100 + 220 * i, 200, 50);
			add(priceLabel);
			System.out.println(
					"buyList ����6 : " + UserManager.usermanager.userList.get(UserManager.logIdx).cart.buyList.size());
			/* ���õ帲 */
			if (cart.cartList.get(i).today) {
				Font font = new Font("", Font.BOLD, 40);
				todaySend = new JLabel();
				todaySend.setText("���õ帲");
				todaySend.setForeground(Color.PINK);
				todaySend.setBounds(800, 130 + 220 * i, 100, 50);
				add(todaySend);
			} else {

			}
			/* ���� */
			if (cart.cartList.get(i).buyCount > 10) {// ���� 11�̻��ϰ��
				cart.elseInfo.get(i).putCount.setBounds(1000, 100 + 220 * i, 100, 50);
				cart.elseInfo.get(i).putCount.setText(cart.cartList.get(i).buyCount + "");
				cart.elseInfo.get(i).putCount.setHorizontalAlignment(JTextField.CENTER);
				cart.elseInfo.get(i).putCount.addActionListener(this);
				add(cart.elseInfo.get(i).putCount);
				cart.elseInfo.get(i).change.setBounds(1000, 150 + 220 * i, 100, 50);
				cart.elseInfo.get(i).change.setText("����");
				cart.elseInfo.get(i).change.addActionListener(this);
				add(cart.elseInfo.get(i).change);
			} else if (cart.cartList.get(i).buyCount <= 10) {// ���� 10�����ϰ��
				for (int j = 0; j < 10; j++) {
					cart.elseInfo.get(i).count.addItem(j + 1 + "");
				}
				cart.elseInfo.get(i).count.addItem("10+");
				cart.elseInfo.get(i).count.setSelectedIndex(cart.cartList.get(i).buyCount - 1);
				cart.elseInfo.get(i).count.setBounds(1000, 100 + +220 * i, 100, 50);
				((JLabel) cart.elseInfo.get(i).count.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);// �������
				cart.elseInfo.get(i).count.addActionListener(this);
				add(cart.elseInfo.get(i).count);
				System.out.println("buyList ����7 : "
						+ UserManager.usermanager.userList.get(UserManager.logIdx).cart.buyList.size());
			}

			/* ���Ű� */
			cart.elseInfo.get(i).sum.setText((cart.cartList.get(i).buyCount * cart.cartList.get(i).price) + "��");
			cart.elseInfo.get(i).sum.setBounds(1200, 100 + 220 * i, 200, 50);
			add(cart.elseInfo.get(i).sum);

			/* ������� */
			JLabel fedex = new JLabel("������");
			fedex.setBounds(1300, 100 + 220 * i, 200, 50);
			add(fedex);

			Font font = new Font("", Font.BOLD, 10);
			JLabel fedex2 = new JLabel("�������갣 ����");
			fedex2.setBounds(1300, 130 + 220 * i, 200, 70);
			fedex2.setFont(font);
			add(fedex2);

			/* ���� */
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
		deleteButton = new JButton("���û�ǰ ����");
		deleteButton.setBounds(100, 220 + 220 * cart.cartList.size(), 200, 50);
		deleteButton.addActionListener(this);
		add(deleteButton);

		Font font = new Font("", Font.BOLD, 40);
		payBtn = new JButton("�����ϱ�");
		payBtn.setBackground(Color.PINK);
		payBtn.setBounds(1500, 220 + 220 * cart.cartList.size(), 300, 50);
		payBtn.addActionListener(this);
		add(payBtn);
		// totalPrice
		font = new Font("", Font.BOLD, 20);

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
		for (int i = 0; i < cart.cartList.size(); i++) {
			if (cart.elseInfo.get(i).check.isSelected()) {
				price += cart.cartList.get(i).buyCount * cart.cartList.get(i).price;
			}
		}

		return price;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		/* [���� ����] */
		for (int i = 0; i < cart.cartList.size(); i++) {
			if (e.getSource() == cart.elseInfo.get(i).count) {
				if (cart.elseInfo.get(i).count.getSelectedIndex() >= 0
						&& cart.elseInfo.get(i).count.getSelectedIndex() < 10) {
					String temp = (String) cart.elseInfo.get(i).count.getSelectedItem();
					cart.cartList.get(i).buyCount = Integer.parseInt(temp);
					cart.elseInfo.get(i).sum.setText(cart.cartList.get(i).buyCount * cart.cartList.get(i).price + "��");
					FileManager.instance.saveUser(UserManager.usermanager.saveUser(), "user.txt");

					label.setText("�� ���� �ݾ� : " + totalPrice() + "��");
				} else if (cart.elseInfo.get(i).count.getSelectedIndex() == 10) {

				}
			}
		}

		/* [���û�ǰ ����] */
		if (e.getSource() == deleteButton) {
			Component[] componentList = this.getComponents();

			for (Component c : componentList) {
				remove(c);
			}

			ArrayList<ItemInfo1> temp1 = cart.cartList;
			ArrayList<ItemInfo2> temp2 = cart.elseInfo;

			cart.cartList = new ArrayList<ItemInfo1>();
			cart.elseInfo = new ArrayList<ItemInfo2>();

			for (int i = 0; i < temp1.size(); i++) {
				if (!temp2.get(i).check.isSelected()) {
					cart.cartList.add(temp1.get(i));
					cart.elseInfo.add(temp2.get(i));
				}

			}
			FileManager.instance.saveUser(UserManager.usermanager.saveUser(), "user.txt");
			set();
			this.revalidate();
			this.repaint();

		}
		/* [�ֹ� �ϱ�] */
		if (e.getSource() == payBtn) {
			if (cart.cartList.size() > 0) {
				ArrayList<ItemInfo1> temp1 = new ArrayList<>();
				for (int i = 0; i < cart.cartList.size(); i++) {
					if (cart.elseInfo.get(i).check.isSelected()) {
						temp1.add(cart.cartList.get(i));
					}
				}
				boolean today = temp1.get(0).today;
				for (int i = 1; i < temp1.size(); i++) {
					System.out.println(cart.cartList.get(i).today);
				
						if (today != temp1.get(i).today) {
							JOptionPane.showMessageDialog(null, "���õ帲 ��ǰ�� �Ϲݻ�ǰ�� ���� ���Ű� �Ұ��� �մϴ�", "�ȳ�",
									JOptionPane.WARNING_MESSAGE);
							return;
						}
					
				}
				cart.buyList = new ArrayList<>();
				cart.buyList = temp1;

				PurchasePanel purchasePanel = new PurchasePanel(cart.buyList);
				JScrollPane scroll = new JScrollPane();
				Dimension size = new Dimension();
				size.setSize(1920, 1060 + (100 * UserManager.usermanager.getBuyListSize()));
				purchasePanel.setPreferredSize(size);
				scroll.setViewportView(purchasePanel);
				Main.frame.setContentPane(scroll);
				Main.frame.revalidate();
			}else{
				JOptionPane.showMessageDialog(null, "���õ� ��ǰ�� �����ϴ�", "�ȳ�",
						JOptionPane.WARNING_MESSAGE);
			}

		}

		/* [�ش��ǰ ����] */
		for (int i = 0; i < cart.cartList.size(); i++) {
			if (e.getSource() == cart.elseInfo.get(i).buy) {
				PurchasePanel purchasePanel = null;
				JScrollPane scroll = new JScrollPane();
				cart.buyList = new ArrayList<>();
				cart.buyList.add(cart.cartList.get(i));

				purchasePanel = new PurchasePanel(cart.cartList.get(i));

				Dimension size = new Dimension();
				size.setSize(1920, 1110);

				purchasePanel.setPreferredSize(size);
				scroll.setViewportView(purchasePanel);
				Main.frame.setContentPane(scroll);
				Main.frame.revalidate();
				break;
			}

			if (e.getSource() == cart.elseInfo.get(i).delete) {
				Component[] componentList = this.getComponents();

				for (Component c : componentList) {
					remove(c);
				}

				cart.cartList.remove(i);
				cart.elseInfo.remove(i);
				set();
				this.revalidate();
				this.repaint();

			}
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub

		label.setText("�� ���� �ݾ� : " + totalPrice() + "��");

		if (e.getItem() == allCheck) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				for (int i = 0; i < cart.elseInfo.size(); i++) {
					cart.elseInfo.get(i).check.setSelected(true);
				}
			}

			if (e.getStateChange() == ItemEvent.DESELECTED) {
				for (int i = 0; i < cart.elseInfo.size(); i++) {
					cart.elseInfo.get(i).check.setSelected(false);
				}
			}
		}

	}

}
