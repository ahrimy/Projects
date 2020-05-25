package oliveYoung;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JoinPanel extends JPanel implements ActionListener{
	/*상단화면*/
	HeadBoard head_board = null;
	// 조인패널(회원가입 패널)
	// 회원가입 가능(아이디, 패스워드, 회원명)
	
	Font font;

	JTextField id_tf;
	JTextField pw_tf;
	JTextField name_tf;

	
	JButton joinBtn;
	JButton backBtn;
	
	JoinPanel(){
		setLayout(null);
		
		/*상단화면*/
		head_board = new HeadBoard();
		head_board.setSize(1900, 210);
		head_board.setBackground(Color.WHITE);
		head_board.setLocation(0, 0);
		add(head_board);
		
		font = new Font("", font.BOLD, 50);
		JLabel name = new JLabel("이름 : ");
		name.setBackground(Color.pink);
		name.setOpaque(true);
		name.setBounds(800, 500, 80, 30);
		add(name);
		
		JLabel id = new JLabel("ID : ");
		id.setBackground(Color.PINK);
		id.setOpaque(true);
		id.setBounds(800, 550, 80, 30);
		add(id);

		JLabel pw = new JLabel("PW : ");
		pw.setBackground(Color.PINK);
		pw.setOpaque(true);
		pw.setBounds(800, 600, 80, 30);
		add(pw);
		
		name_tf = new JTextField(10);
		name_tf.setBounds(900, 500, 200, 30);
		name_tf.addActionListener(this);
		add(name_tf);
		
		id_tf = new JTextField(10);
		id_tf.setBounds(900, 550, 200, 30);
		id_tf.addActionListener(this);
		add(id_tf);
		
		pw_tf = new JTextField(10);
		pw_tf.setBounds(900, 600, 200, 30);
		pw_tf.addActionListener(this);
		add(pw_tf);
		
		joinBtn = new JButton("회원가입");
		joinBtn.setBackground(Color.GREEN);
		joinBtn.setBounds(850, 700, 100, 70);
		joinBtn.addActionListener(this);
		add(joinBtn);
		
		backBtn = new JButton("메인화면");
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
		
		if(e.getSource() == joinBtn) {
			if(name_tf.getText().equals("") || id_tf.getText().equals("") || pw_tf.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "정보를 모두 입력해주세요!", "안내", JOptionPane.WARNING_MESSAGE);
			}else {
				UserManager.usermanager.join(name_tf.getText(), id_tf.getText(), pw_tf.getText());
			}
		}
	}

}
