package oliveYoung;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;




public class CartAllPanel extends JPanel{
		
	HeadBoard head_board = null; // �ֻ�� 
	CartTopPanel topPanel = null; // ���
	CartMidPanel midPanel = null; // �ߴ�
	CartBotPanel botPanel = null; // �ϴ�
	
	
	public CartAllPanel(){
		setLayout(null);
		
		
		head_board = new HeadBoard();
		head_board.setSize(1900, 210);
		head_board.setBackground(Color.WHITE);
		head_board.setLocation(0, 0);
		add(head_board);
		revalidate();
		topPanel = new CartTopPanel();
		add(topPanel);
		revalidate();
		midPanel = new CartMidPanel();
		midPanel.setBounds(0, 340, 1920,500 + 250 * UserManager.usermanager.userList.get(UserManager.logIdx).cart.cartList.size());
		
		add(midPanel);
		revalidate();
		
		
	}
	
	
}
