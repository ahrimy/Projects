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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class QnAPanel extends JPanel implements ActionListener, MouseListener {

	HeadBoard head_board = null;

	JLabel qnaLabel;
	JLabel textLabel;
	JLabel listLabel;

	JTextField title_tf;
	JTextField text_tf;

	JButton addBtn;
	JButton mypageBtn[] = new JButton[4]; // ��ٱ��� ��ȸ, ȸ��Ż��, ����ȭ��, ����������

	QnAlistPanel QnA_list_panel = null;

	public QnAPanel() {
		setLayout(null);
		setBackground(Color.WHITE);
		addMouseListener(this);

		/* ���ȭ�� */
		head_board = new HeadBoard();
		head_board.setSize(1900, 210);
		head_board.setBackground(Color.WHITE);
		head_board.setLocation(0, 0);
		add(head_board);

		setButton();
		setImage();

		// QnAPanel()�� �����Ǹ� QnAPanel()�г� �ȿ� QnAlistPanel()�� ����
		QnA_list_panel = new QnAlistPanel();
		QnA_list_panel.setBackground(Color.WHITE);
		QnA_list_panel.setBounds(1250, 400, 600, 600);
		add(QnA_list_panel);
		
		// �α������� ȸ���� qna����
		checkData();
		System.out.println("QnA_list_panel.qnaNum = " + QnA_list_panel.qnaNum);
		QnA_list_panel.loadSet();
	}
	
	public void checkData() {
		int cnt = 0;
		if(QnAManager.qnaManager.qnaList != null) {
			for(int i=0; i<QnAManager.qnaManager.qnaList.size(); i++) {
				if(UserManager.usermanager.getId().equals(QnAManager.qnaManager.qnaList.get(i).logId)) {
					cnt += 1;
				}
			}
		}
		QnA_list_panel.qnaNum = cnt;
	}

	public void setButton() {

		mypageBtn[0] = new JButton("��ٱ���");
		mypageBtn[1] = new JButton("ȸ��Ż��");
		mypageBtn[2] = new JButton("����ȭ��");
		mypageBtn[3] = new JButton("����������");

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
		textLabel.setBounds(340, 400, 900, 500);
		textLabel.setOpaque(true);
		add(textLabel);

		addBtn = new JButton();
		addBtn.setBounds(665, 935, 200, 60);
		addBtn.setBorder(null);
		addBtn.setBackground(Color.WHITE);
		addBtn.addActionListener(this);
		add(addBtn);

		title_tf = new JTextField(30);
		title_tf.setBorder(null);
		title_tf.setBounds(575, 440, 150, 30);
		title_tf.addActionListener(this);
		add(title_tf);

		text_tf = new JTextField(200);
		text_tf.setBorder(null);
		text_tf.setBounds(575, 525, 625, 275);
		;
		text_tf.addActionListener(this);
		add(text_tf);

	}

	public void setImage() {
		Image img = new ImageIcon("./src/images/qnapanel.JPG").getImage().getScaledInstance(1900, 200,
				Image.SCALE_SMOOTH);
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
		// g.drawLine(100, 690, 210, 690);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// ��ٱ���
		if (e.getSource() == mypageBtn[0]) {
		}

		// ȸ��Ż��
		if (e.getSource() == mypageBtn[1]) {
			UserManager.usermanager.removeMember();
		}

		// ����ȭ��
		if (e.getSource() == mypageBtn[2]) {
			Main.frame.setContentPane(new MainPanel());
			Main.frame.revalidate();
		}

		// ����������
		if (e.getSource() == mypageBtn[3]) {
			Main.frame.setContentPane(new MypagePanel());
			Main.frame.revalidate();
		}

		// �����ϱ�(����)
		if (e.getSource() == addBtn) {
			System.out.println("���ȴ�.");

			if (title_tf.getText() == "" || text_tf.getText() == "") {
				JOptionPane.showMessageDialog(null, "����� ������ �Է����ּ���!", "�ȳ�", JOptionPane.WARNING_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "�����ϱⰡ ��ϵǾ����ϴ�.", "�ȳ�", JOptionPane.WARNING_MESSAGE);
				QnAManager.qnaManager.addQnA(title_tf.getText(), text_tf.getText(), UserManager.usermanager.getId()); // arrayList ����
				QnAManager.qnaManager.getList();	// ����� arrayList ���
				QnAManager.qnaManager.saveQnA();	// data�� ����� ����
				QnA_list_panel.setTitleBtn();
				QnA_list_panel.upDateBtn();
				FileManager.instance.saveQnA(QnAManager.qnaManager.saveQnA(), "qna.txt");	// data ���Ϸ� ����

			}
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
