package oliveYoung;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class QnAPanel extends JPanel implements ActionListener, MouseListener {

	HeadBoard head_board = null;

	JLabel qnaLabel;
	JLabel textLabel;

	JTextField q_tf;

	JButton addBtn;

	JButton mypageBtn[] = new JButton[4]; // 장바구니 조회, 회원탈퇴, 메인화면, 마이페이지

	public QnAPanel() {
		setLayout(null);
		setBackground(Color.WHITE);
		addMouseListener(this);

		/* 상단화면 */
		head_board = new HeadBoard();
		head_board.setSize(1900, 210);
		head_board.setBackground(Color.WHITE);
		head_board.setLocation(0, 0);
		add(head_board);
		
		setButton();
		setImage();
	}

	public void setButton() {

		mypageBtn[0] = new JButton("장바구니");
		mypageBtn[1] = new JButton("회원탈퇴");
		mypageBtn[2] = new JButton("메인화면");
		mypageBtn[3] = new JButton("마이페이지");

		for (int i = 0; i < mypageBtn.length; i++) {
			mypageBtn[i].setFont(new Font("", Font.PLAIN, 15));
			mypageBtn[i].setBounds(110, 500 + (i * 50), 100, 30);
			mypageBtn[i].setBorder(null);
			mypageBtn[i].setBackground(Color.white);
			mypageBtn[i].addActionListener(this);
			add(mypageBtn[i]);
		}
		
		qnaLabel = new JLabel();
		qnaLabel.setBackground(Color.BLUE);
		qnaLabel.setBounds(0, 210, 1900, 200);
		qnaLabel.setOpaque(true);
		add(qnaLabel);

		textLabel = new JLabel();
		textLabel.setBackground(Color.BLUE);
		textLabel.setBounds(440, 400, 900, 500);
		textLabel.setOpaque(true);
		add(textLabel);
		
		addBtn = new JButton();
		addBtn.setBounds(850, 900, 200, 60);
		addBtn.setBorder(null);
		addBtn.setBackground(Color.WHITE);
		addBtn.addActionListener(this);
		add(addBtn);
		
	}
	
	public void setImage() {
		Image img = new ImageIcon("./src/images/qnapanel.JPG").getImage().getScaledInstance(1900, 200, Image.SCALE_SMOOTH);
		qnaLabel.setIcon(new ImageIcon(img));
		
		img = new ImageIcon("./src/images/addBtn.PNG").getImage().getScaledInstance(200, 60, Image.SCALE_SMOOTH);
		addBtn.setIcon(new ImageIcon(img));
		
		img = new ImageIcon("./src/images/qnaTextLabel.JPG").getImage().getScaledInstance(900, 500, Image.SCALE_SMOOTH);
		textLabel.setIcon(new ImageIcon(img));

	}
	


	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		g.setColor(Color.lightGray);
		g.drawLine(100, 540, 210, 540);
		g.drawLine(100, 590, 210, 590);
		g.drawLine(100, 640, 210, 640);
		//g.drawLine(100, 690, 210, 690);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// 장바구니
		if(e.getSource() == mypageBtn[0]) {}
		
		// 회원탈퇴
		if(e.getSource() == mypageBtn[1]) {
			UserManager.usermanager.removeMember();
		}
		
		// 메인화면
		if(e.getSource() == mypageBtn[2]) {
			Main.frame.setContentPane(new MainPanel());
			Main.frame.revalidate();
		}
		
		// 마이페이지
		if(e.getSource() == mypageBtn[3]) {
			Main.frame.setContentPane(new MypagePanel());
			Main.frame.revalidate();			
		}
		
		// 문의하기
		if(e.getSource() == addBtn) {
			System.out.println("눌렸다.");
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();

		System.out.println(x + " : " + y);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


}
