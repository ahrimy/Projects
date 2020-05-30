package oliveYoung;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Popup;
import javax.swing.PopupFactory;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class ItemDetailedPanel extends JPanel implements ActionListener {

	HeadBoard head_board = null;
	JLabel mainPart;
	JLabel itemImage;
	JLabel itemName;
	JLabel itemInfo[][];
	JLabel itemCount;
	JButton itemPlus;
	JButton itemMinus;
	JLabel count;
	JButton cart;
	JButton purchase;
	Item item;
	JLabel deliveryToday;
	JCheckBox checkDeliveryToday;
	JButton deliveryOption;
	JButton checkAvailableStore;
	JLabel deliveryStore;
	static Popup p;//
	JPanel storeList;//
	Store availableStore;
	JFrame f = new JFrame("pop");
	String[][] labelList = { { "�ǸŰ�", "" }, { "ī����������", "The CJī�� �߰� 10%" }, { "CJ ONE ����Ʈ ��������", "2%����" },
			{ "��ۺ�", "2,500��(�����ݾ� 20,000�� �̻� ����)" }, { "��۹��", "�ø��꿵 ���" }, { "��۱Ⱓ", "��� 3�� �̳� ���" },
			{ "��ǰ �ݾ� �հ�", "" } };

	public ItemDetailedPanel(Item item) {
		setLayout(null);
		this.item = new Item(item.getCategory(), item.getItemTitle(), item.getItemName(), item.getImageName(),
				item.getPrice(), 1, item.isOnlyOnline());
		availableStore = null;
		storeList = new StoreListPanel(item);//
		storeList.setSize(1000, 900);

		storeList.setBorder(new LineBorder(Color.LIGHT_GRAY, 2, true));

		head_board = new HeadBoard();
		head_board.setSize(1900, 210);
		head_board.setBackground(Color.WHITE);
		head_board.setLocation(0, 0);
		add(head_board);

		mainPart = new JLabel();
		mainPart.setBounds(0, 210, 1900, 900);
		mainPart.setBackground(Color.WHITE);
		mainPart.setOpaque(true);
		add(mainPart);
		itemImage = new JLabel();
		itemName = new JLabel();
		itemInfo = new JLabel[7][2];
		itemCount = new JLabel();
		count = new JLabel();
		itemPlus = new JButton();
		itemMinus = new JButton();
		setDetailedInfo();
		setCount();
		cart = new JButton();
		purchase = new JButton();
		checkAvailableStore = new JButton();
		setButton();

		deliveryToday = new JLabel();
		if (item.isOnlyOnline()) {
			deliveryToday.setBounds(1000, 560, 600, 40);
			deliveryToday.setFont(new Font("", Font.BOLD, 20));
			deliveryToday.setText("���õ帲 �Ұ� ��ǰ(�¶��� �Ǹ�)");
			deliveryToday.setHorizontalAlignment(SwingConstants.CENTER);
			mainPart.add(deliveryToday);
		} else {
			deliveryToday = new JLabel();
			checkDeliveryToday = new JCheckBox();
			deliveryOption = new JButton();
			deliveryStore = new JLabel();
			deliveryOption();
		}

	}

	public void deliveryOption() {
		deliveryToday.setBounds(1000, 560, 600, 40);
		deliveryToday.setFont(new Font("", Font.BOLD, 20));
		deliveryToday.setText("���õ帲���� �޾ƺ��ðھ��?");
		deliveryToday.setHorizontalAlignment(SwingConstants.CENTER);
		// deliveryToday.setOpaque(true);
		mainPart.add(deliveryToday);

		// deliveryStore.setOpaque(true);
		deliveryStore.setBounds(1000, 600, 600, 40);
		deliveryStore.setFont(new Font("", Font.BOLD, 20));
		deliveryStore.setHorizontalAlignment(SwingConstants.CENTER);

		mainPart.add(deliveryStore);
		checkDeliveryToday.setBounds(130, 10, 20, 20);
		checkDeliveryToday.addActionListener(this);
		checkDeliveryToday.setBackground(Color.white);
		checkDeliveryToday.setBorder(null);
		deliveryToday.add(checkDeliveryToday);

		deliveryOption.setBounds(480, 10, 100, 30);
		deliveryOption.setFont(new Font("", Font.BOLD, 15));
		deliveryOption.setText("��ۿɼǺ���");
		deliveryOption.setForeground(Color.LIGHT_GRAY);
		deliveryOption.setBackground(Color.white);
		deliveryOption.setBorder(null);
		deliveryOption.addActionListener(this);
		deliveryToday.add(deliveryOption);
	}

	public void setButton() {
		cart.setBounds(1000, 650, 250, 50);
		cart.setBackground(Color.WHITE);
		cart.setText("��ٱ���");
		cart.setFont(new Font("", Font.BOLD, 20));
		cart.setForeground(Color.PINK);
		cart.addActionListener(this);
		mainPart.add(cart);

		purchase.setBounds(1300, 650, 250, 50);
		purchase.setBackground(Color.PINK);
		purchase.setText("�ٷα���");
		purchase.setFont(new Font("", Font.BOLD, 20));
		purchase.setForeground(Color.WHITE);
		purchase.addActionListener(this);
		mainPart.add(purchase);
		if (ItemManager.instance.checkStock(item.getCategory(), item).equals("ǰ��")) {
			cart.setBackground(Color.GRAY);
			cart.setText("�Ͻ� ǰ��");
			cart.setForeground(Color.white);
			purchase.setBackground(Color.white);
			purchase.setText("���԰� �˸�");
			purchase.setForeground(Color.pink);
		}

		checkAvailableStore.setBounds(1100, 710, 500, 50);
		checkAvailableStore.setFont(new Font("", Font.BOLD, 20));
		checkAvailableStore.setText("���� ���� ������ Ȯ���ϼ���");
		checkAvailableStore.setBackground(Color.white);
		checkAvailableStore.setBorder(null);
		checkAvailableStore.addActionListener(this);

		try {
			Image img = new ImageIcon("./src/images/logo.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
			checkAvailableStore.setIcon(new ImageIcon(img));

		} catch (Exception ex) {
			System.out.println(ex);
		}
		mainPart.add(checkAvailableStore);

	}

	public void setDetailedInfo() {
		itemImage.setBounds(350, 50, 600, 600);
		try {
			Image img = new ImageIcon(item.getImageName()).getImage().getScaledInstance(600, 600, Image.SCALE_SMOOTH);
			itemImage.setIcon(new ImageIcon(img));

		} catch (Exception ex) {
			System.out.println(ex);
		}
		mainPart.add(itemImage);

		itemName.setText(item.getItemName());
		itemName.setVerticalAlignment(SwingConstants.TOP);
		itemName.setBounds(1000, 50, 600, 100);
		itemName.setFont(new Font("", Font.BOLD, 30));
		mainPart.add(itemName);

		for (int i = 0; i < itemInfo.length; i++) {
			itemInfo[i][0] = new JLabel(labelList[i][0], SwingConstants.LEFT);
			itemInfo[i][1] = new JLabel(labelList[i][1], SwingConstants.RIGHT);
			itemInfo[i][0].setBounds(1000, 150 + (i * 50), 300, 50);
			itemInfo[i][1].setBounds(1300, 150 + (i * 50), 300, 50);
			itemInfo[i][0].setForeground(Color.BLACK);
			itemInfo[i][1].setForeground(Color.black);
			mainPart.add(itemInfo[i][0]);
			mainPart.add(itemInfo[i][1]);
		}
		itemInfo[0][1].setText(item.getPrice() + " ��");
		itemInfo[0][1].setFont(new Font("", Font.BOLD, 20));
		itemInfo[6][1].setText(item.getPrice() + " ��");
		itemInfo[6][0].setLocation(1000, 520);
		itemInfo[6][1].setLocation(1300, 520);
		itemInfo[6][0].setFont(new Font("", Font.BOLD, 20));
		itemInfo[6][0].setForeground(Color.red);
		itemInfo[6][1].setFont(new Font("", Font.BOLD, 20));
		itemInfo[6][1].setForeground(Color.red);

	}

	public void setCount() {

		itemCount.setBounds(1000, 450, 600, 70);
		itemCount.setText("        ���ż���");
		itemCount.setBackground(Color.LIGHT_GRAY);
		itemCount.setOpaque(true);
		itemCount.setForeground(Color.black);
		mainPart.add(itemCount);

		count.setBounds(490, 15, 40, 40);
		count.setText(item.getCount() + "");
		count.setVerticalAlignment(SwingConstants.CENTER);
		count.setHorizontalAlignment(SwingConstants.CENTER);
		count.setBackground(Color.white);
		count.setOpaque(true);
		count.setForeground(Color.BLACK);
		itemCount.add(count);

		itemPlus.setBounds(530, 15, 40, 40);
		itemPlus.setText("+");
		itemPlus.setFont(new Font("", Font.BOLD, 11));
		itemPlus.setBackground(Color.LIGHT_GRAY);
		itemPlus.addActionListener(this);
		itemCount.add(itemPlus);

		itemMinus.setBounds(450, 15, 40, 40);
		itemMinus.setText("-");
		itemMinus.setFont(new Font("", Font.BOLD, 10));
		itemMinus.setBackground(Color.LIGHT_GRAY);
		itemMinus.addActionListener(this);
		itemCount.add(itemMinus);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == itemPlus) {
			if (item.getCount() < 10) {
				item.updateCount(1);
			}
			count.setText(item.getCount() + "");
			itemInfo[6][1].setText(item.getCount() * item.getPrice() + " ��");
		} else if (e.getSource() == itemMinus) {
			if (item.getCount() > 1) {
				item.updateCount(-1);
			}
			count.setText(item.getCount() + "");
			itemInfo[6][1].setText(item.getCount() * item.getPrice() + " ��");
		} else if (e.getSource() == purchase) {
			if (UserManager.usermanager.logIdx == -1) {
				JOptionPane.showMessageDialog(null, "�α����� �ʿ��� ���� �Դϴ�", "�ȳ�", JOptionPane.WARNING_MESSAGE);
			} else {
				if (purchase.getText().equals("�ٷα���")) {
					if (checkDeliveryToday.isSelected()) {
						int index = StoreManager.instance.storeIndex(availableStore.getCity(), availableStore);
						String data = StoreManager.instance.storeList.get(availableStore.getCity()).get(index)
								.findItem(item);
						if (data.equals("�Ǹ����� �ʴ� ��ǰ")) {
							JOptionPane.showMessageDialog(null, "���� �Ǹ����� �ʴ� ��ǰ�Դϴ�", "�ȳ�", JOptionPane.WARNING_MESSAGE);
						} else if (data.equals("���� �ش� ������ ��� ����")) {
							JOptionPane.showMessageDialog(null, "���� �ش� �������� �Ͻ� ǰ�� �Ǿ����ϴ�", "�ȳ�", JOptionPane.WARNING_MESSAGE);
						} else if (data.equals("��� ���� ����")) {
							JOptionPane.showMessageDialog(null, "���� �ش� ������ ��� �����մϴ�", "�ȳ�", JOptionPane.WARNING_MESSAGE);
						} else {
							PurchasePanel purchasePanel = null;
							JScrollPane scroll = new JScrollPane();
							if (checkDeliveryToday.isSelected()) {
								purchasePanel = new PurchasePanel(item, true);
							} else {
								purchasePanel = new PurchasePanel(item, false);
							}
							Dimension size = new Dimension();
							size.setSize(1920, 1110);

							purchasePanel.setPreferredSize(size);
							scroll.setViewportView(purchasePanel);
							Main.frame.setContentPane(scroll);
							Main.frame.revalidate();
						}
					}else{
						String data = ItemManager.instance.checkStock(item.getCategory(), item);
						if (data.equals("���� ����")) {
							PurchasePanel purchasePanel = null;
							JScrollPane scroll = new JScrollPane();
							if (checkDeliveryToday.isSelected()) {
								purchasePanel = new PurchasePanel(item, true);
							} else {
								purchasePanel = new PurchasePanel(item, false);
							}
							Dimension size = new Dimension();
							size.setSize(1920, 1110);

							purchasePanel.setPreferredSize(size);
							scroll.setViewportView(purchasePanel);
							Main.frame.setContentPane(scroll);
							Main.frame.revalidate();
						} else if (data.equals("��� ���� ����")) {
							JOptionPane.showMessageDialog(null, "��� ������ �����մϴ�.", "�ȳ�", JOptionPane.WARNING_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null, "���� �Ǹŵ��� �ʴ� ��ǰ�Դϴ�.", "�ȳ�", JOptionPane.WARNING_MESSAGE);
						}
					}

				}
			}
		} else if (e.getSource() == cart) {
			if (cart.getText().equals("��ٱ���")) {
				if (UserManager.usermanager.logIdx == -1) {
					JOptionPane.showMessageDialog(null, "�α����� �ʿ��� ���� �Դϴ�", "�ȳ�", JOptionPane.WARNING_MESSAGE);
				} else {

					String data = ItemManager.instance.checkStock(item.getCategory(), item);
					if (data.equals("���� ����")) {
						UserManager.usermanager.userList.get(UserManager.logIdx).cart.add(item.getImageName(),
								item.getItemTitle(), item.getItemName(), item.getPrice(), item.getCount());
						JOptionPane.showMessageDialog(null, "��ٱ��� ��� �Ϸ�", "�ȳ�", JOptionPane.WARNING_MESSAGE);
					} else if (data.equals("��� ���� ����")) {
						JOptionPane.showMessageDialog(null, "��� ������ �����մϴ�.", "�ȳ�", JOptionPane.WARNING_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "���� �Ǹŵ��� �ʴ� ��ǰ�Դϴ�.", "�ȳ�", JOptionPane.WARNING_MESSAGE);
					}
				}
			}

		} else if (e.getSource() == checkDeliveryToday) {
			if (checkDeliveryToday.isSelected()) {

				if (UserManager.logIdx == -1) {
					JOptionPane.showMessageDialog(null, "�α����� �ʿ��� ���� �Դϴ�", "�ȳ�", JOptionPane.WARNING_MESSAGE);
					checkDeliveryToday.setSelected(false);
				} else {
					String data = "";
					Store temp = StoreManager.instance.findStore(
							UserManager.usermanager.userList.get(UserManager.logIdx).userCity,
							UserManager.usermanager.userList.get(UserManager.logIdx).userStreet,
							UserManager.usermanager.userList.get(UserManager.logIdx).userCode);
					if (temp != null) {
						String status = temp.findItem(item);
						if (status.equals("���� �ش� ������ ��� ����")) {
							cart.setBackground(Color.GRAY);
							cart.setText("�Ͻ� ǰ��");
							cart.setForeground(Color.white);
							purchase.setBackground(Color.white);
							purchase.setText("���԰� �˸�");
							purchase.setForeground(Color.pink);
						} else if (status.equals("�Ǹ����� �ʴ� ��ǰ")) {
							JOptionPane.showMessageDialog(null, "���� �帲 ������ ������ �����ϴ�.", "�ȳ�",
									JOptionPane.WARNING_MESSAGE);
							checkDeliveryToday.setSelected(false);
						} else {
							if (cart.getText().equals("�Ͻ� ǰ��")) {
								// cart.setBackground(Color.WHITE);
								// cart.setText("��ٱ���");
								// cart.setForeground(Color.PINK);

								purchase.setBackground(Color.PINK);
								purchase.setText("�ٷα���");
								purchase.setForeground(Color.WHITE);
							}
							availableStore = temp;
						}
						data = temp.getStoreName() + " : ";
						data += status;
						deliveryStore.setText(data);
					} else {
						deliveryStore.setText("���� �帲 ������ ������ ����");
						JOptionPane.showMessageDialog(null, "���� �帲 ������ ������ �����ϴ�.", "�ȳ�", JOptionPane.WARNING_MESSAGE);
						checkDeliveryToday.setSelected(false);
					}
				}
			} else {
				deliveryStore.setText("");
				if (purchase.getText().equals("���԰� �˸�")) {
					if (ItemManager.instance.checkStock(item.getCategory(), item).equals("���� ����")) {

						cart.setBackground(Color.WHITE);
						cart.setText("��ٱ���");
						cart.setForeground(Color.PINK);

						purchase.setBackground(Color.PINK);
						purchase.setText("�ٷα���");
						purchase.setForeground(Color.WHITE);
					}
				} else if (purchase.getText().equals("�ٷα���")) {
					if (ItemManager.instance.checkStock(item.getCategory(), item).equals("ǰ��")) {

						purchase.setBackground(Color.white);
						purchase.setText("���԰� �˸�");
						purchase.setForeground(Color.pink);
					}
				}

			}
		} else if (e.getSource() == checkAvailableStore) {
			PopupFactory pf = new PopupFactory();
			p = pf.getPopup(Main.frame, storeList, 450, 100);
			p.show();

			// Main.frame.setContentPane(new StoreListPanel(item));
			// Main.frame.revalidate();
		}
	}
}
