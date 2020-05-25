package oliveYoung;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ItemDetailedPanel extends JPanel implements ActionListener{
	
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
	String[][] labelList = {{"판매가",""},{"카드할인혜택","The CJ카드 추가 10%"},{"CJ ONE 포인트 예상적립","2%적립"},{"배송비","2,500원(결제금액 20,000원 이상 무료)"},{"배송방법","올리브영 배송"},{"배송기간","평균 3일 이내 배송"},{"상품 금액 합계",""}};
	public ItemDetailedPanel(Item item){
		setLayout(null);
		
		this.item = new Item(item.getCategory(),item.getItemName(),item.getImageName(),item.getPrice(),1,item.isOnlyOnline());
		
		head_board = new HeadBoard();
		head_board.setSize(1900, 210);
		head_board.setBackground(Color.WHITE);
		head_board.setLocation(0, 0);
		add(head_board);
		
		mainPart = new JLabel();
		mainPart.setBounds(0,210,1900,800);
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
		setButton();
	}
	public void setButton(){
		cart.setBounds(1000, 600, 250, 100);
		cart.setBackground(Color.WHITE);
		cart.setText("장바구니");
		cart.setFont(new Font("",Font.BOLD,20));
		cart.setForeground(Color.PINK);
		cart.addActionListener(this);
		mainPart.add(cart);
		
		purchase.setBounds(1300, 600, 250, 100);
		purchase.setBackground(Color.PINK);
		purchase.setText("바로구매");
		purchase.setFont(new Font("",Font.BOLD,20));
		purchase.setForeground(Color.WHITE);
		purchase.addActionListener(this);
		mainPart.add(purchase);
		
		
	}

	public void setDetailedInfo(){
		itemImage.setBounds(350,50,600,600);
		try {
			Image img = new ImageIcon(item.getImageName()).getImage().getScaledInstance(600, 600,
					Image.SCALE_SMOOTH);
			itemImage.setIcon(new ImageIcon(img));

		} catch (Exception ex) {
			System.out.println(ex);
		}
		mainPart.add(itemImage);
		
		itemName.setText(item.getItemName());
		itemName.setVerticalAlignment(SwingConstants.TOP);
		itemName.setBounds(1000,50,600,100);
		itemName.setFont(new Font("",Font.BOLD,30));
		mainPart.add(itemName);
		
		for(int i=0;i<itemInfo.length;i++){
			itemInfo[i][0] = new JLabel(labelList[i][0],SwingConstants.LEFT);
			itemInfo[i][1] = new JLabel(labelList[i][1],SwingConstants.RIGHT);
			itemInfo[i][0].setBounds(1000,150+(i*50),300,50);
			itemInfo[i][1].setBounds(1300,150+(i*50),300,50);
			itemInfo[i][0].setForeground(Color.BLACK);
			itemInfo[i][1].setForeground(Color.black);
			mainPart.add(itemInfo[i][0]);
			mainPart.add(itemInfo[i][1]);
		}
		itemInfo[0][1].setText(item.getPrice()+" 원");
		itemInfo[0][1].setFont(new Font("",Font.BOLD,20));
		itemInfo[6][1].setText(item.getPrice()+" 원");
		itemInfo[6][0].setLocation(1000,520);
		itemInfo[6][1].setLocation(1300, 520);
		itemInfo[6][0].setFont(new Font("",Font.BOLD,20));
		itemInfo[6][0].setForeground(Color.red);
		itemInfo[6][1].setFont(new Font("",Font.BOLD,20));
		itemInfo[6][1].setForeground(Color.red);

	}
	public void setCount(){
		
		itemCount.setBounds(1000, 450, 600, 70);
		itemCount.setText("        구매수량");
		itemCount.setBackground(Color.LIGHT_GRAY);
		itemCount.setOpaque(true);
		itemCount.setForeground(Color.black);
		mainPart.add(itemCount);
		
		count.setBounds(490, 15, 40, 40);
		count.setText(item.getCount()+"");
		count.setVerticalAlignment(SwingConstants.CENTER);
		count.setHorizontalAlignment(SwingConstants.CENTER);
		count.setBackground(Color.white);
		count.setOpaque(true);
		count.setForeground(Color.BLACK);
		itemCount.add(count);
		
		itemPlus.setBounds(530,15,40,40);
		itemPlus.setText("+");
		itemPlus.setFont(new Font("",Font.BOLD,11));
		itemPlus.setBackground(Color.LIGHT_GRAY);
		itemPlus.addActionListener(this);
		itemCount.add(itemPlus);
		
		itemMinus.setBounds(450,15,40,40);
		itemMinus.setText("-");
		itemMinus.setFont(new Font("",Font.BOLD,10));
		itemMinus.setBackground(Color.LIGHT_GRAY);
		itemMinus.addActionListener(this);
		itemCount.add(itemMinus);
	
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==itemPlus){
			if(item.getCount()<10){
				item.updateCount(1);
			}
			count.setText(item.getCount()+"");
			itemInfo[6][1].setText(item.getCount()*item.getPrice()+" 원");
		}else if(e.getSource()==itemMinus){
			if(item.getCount()>1){
				item.updateCount(-1);
			}
			count.setText(item.getCount()+"");
			itemInfo[6][1].setText(item.getCount()*item.getPrice()+" 원");
		}
		if(e.getSource()==purchase){
			if(UserManager.usermanager.logIdx==-1){
				JOptionPane.showMessageDialog(null, "로그인이 필요한 서비스 입니다", "안내", JOptionPane.WARNING_MESSAGE);
			}else{
				
			}
		}else if(e.getSource()==cart){
			if(UserManager.usermanager.logIdx==-1){
				JOptionPane.showMessageDialog(null, "로그인이 필요한 서비스 입니다", "안내", JOptionPane.WARNING_MESSAGE);
			}else{
				
			}
		}
	}
}
