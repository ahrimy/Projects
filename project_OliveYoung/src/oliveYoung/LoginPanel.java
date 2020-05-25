package oliveYoung;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginPanel extends JPanel implements ActionListener{
	/*���ȭ��*/
	HeadBoard head_board = null;
	// �α��� �г�
	// �α��ΰ���
	
	Font font;
	
	JTextField id_tf;
	JTextField pw_tf;
	
	JButton logInBtn;
	JButton backBtn;
	
	public LoginPanel() {
		setLayout(null);
		
		/*���ȭ��*/
		head_board = new HeadBoard();
		head_board.setSize(1900, 210);
		head_board.setBackground(Color.WHITE);
		head_board.setLocation(0, 0);
		add(head_board);
		
		font = new Font("", font.BOLD, 50);
		JLabel id = new JLabel("ID ");
		id.setBackground(Color.PINK);
		id.setOpaque(true);
		id.setBounds(800, 550, 80, 30);
		add(id);
		
		JLabel pw = new JLabel("PW : ");
		pw.setBackground(Color.PINK);
		pw.setOpaque(true);
		pw.setBounds(800, 600, 80, 30);
		add(pw);
		
		id_tf = new JTextField(10);
		id_tf.setBounds(900, 550, 200, 30);
		id_tf.addActionListener(this);
		add(id_tf);
		
		pw_tf = new JPasswordField(10);
		pw_tf.setBounds(900, 600, 200, 30);
		pw_tf.addActionListener(this);
		add(pw_tf);
		
		logInBtn = new JButton("�α���");
		logInBtn.setBackground(Color.GREEN);
		logInBtn.setBounds(850, 700, 100, 70);
		logInBtn.addActionListener(this);
		add(logInBtn);
		
		backBtn = new JButton("����ȭ��");
		backBtn.setBackground(Color.RED);
		backBtn.setBounds(1000, 700, 100, 70);
		backBtn.addActionListener(this);
		add(backBtn);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == backBtn) {
			Main.frame.setContentPane(new MainPanel());
			Main.frame.revalidate();
		}
		
		if(e.getSource() == logInBtn) {
			if(id_tf.getText().equals("") || pw_tf.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "������ ��� �Է����ּ���!", "�ȳ�", JOptionPane.WARNING_MESSAGE);
			}else {
				UserManager.usermanager.logIn(id_tf.getText(), pw_tf.getText());
			}
		}
		
	}

}
