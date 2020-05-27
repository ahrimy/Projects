package oliveYoung;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class StoreListPanel extends JPanel implements ActionListener {
	JButton back;
	Item item;
	JLabel title;
	JLabel searchTitle;
	JButton searchBtn;
	JTextField searchArea;
	JLabel[] storeNameList;
	JLabel[] storeStatus;
	ArrayList<Store> storeList = null;

	public StoreListPanel(Item item) {
		setLayout(null);
		setBackground(Color.WHITE);
		this.item = item;
		back = new JButton("X");
		back.setBounds(1300, 50, 50, 50);
		back.setBackground(Color.BLACK);
		back.setForeground(Color.white);
		back.setFont(new Font("",Font.BOLD,20));
		back.addActionListener(this);
		add(back);

		title = new JLabel("구매 가능 매장 확인");
		title.setBounds(600, 50, 700, 50);
		title.setFont(new Font("", Font.BOLD, 30));
		title.setForeground(Color.black);
		add(title);

		searchTitle = new JLabel("확인하고 싶은 도시명 입력");
		searchTitle.setBounds(600, 100, 700, 50);
		searchTitle.setForeground(Color.DARK_GRAY);
		add(searchTitle);

		searchBtn = new JButton();
		searchArea = new JTextField();

		LineBorder lineBorder = new LineBorder(new Color(153, 255, 51), 3, true);
		searchArea.setBorder(lineBorder);
		searchArea.setBounds(600, 150, 700, 50);
		searchArea.setBackground(null);
		add(searchArea);
		searchBtn.setBounds(650, 8, 35, 35);
		searchBtn.setBackground(Color.white);
		searchBtn.setBorder(null);
		searchBtn.addActionListener(this);
		searchArea.add(searchBtn);
		try {
			Image img = new ImageIcon("./src/images/ico_search21x212.png").getImage().getScaledInstance(35, 35,
					Image.SCALE_SMOOTH);
			searchBtn.setIcon(new ImageIcon(img));

		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		try {
			Thread.sleep(10);
			repaint();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (storeList != null) {
			int storeCount = storeList.size();
			storeNameList = new JLabel[storeCount];
			storeStatus = new JLabel[storeCount];
			LineBorder lineBorder = new LineBorder(Color.LIGHT_GRAY, 1, false);
			Font font = new Font("",Font.PLAIN,15);
			for (int i = 0; i < storeCount; i++) {
				
				storeNameList[i]=new JLabel();
				storeNameList[i].setFont(font);
				storeNameList[i].setBounds(600, 300 + (i * 50), 600, 20);
				storeNameList[i].setText(storeList.get(i).getStoreName());
				storeNameList[i].setBorder(lineBorder);
				
				storeStatus[i] = new JLabel();
				storeStatus[i].setBounds(600, 320 + (i * 50), 600, 20);
				storeStatus[i].setFont(font);
				storeStatus[i].setText(storeList.get(i).findItem(item));
				
				add(storeNameList[i]);
				add(storeStatus[i]);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == back) {
			Main.frame.setContentPane(new ItemDetailedPanel(item));
			Main.frame.revalidate();
		} else if (e.getSource() == searchBtn) {
			String data = searchArea.getText();
			storeList = StoreManager.instance.storeList.get(data);

		}
	}

}
