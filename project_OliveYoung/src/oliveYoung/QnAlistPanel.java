package oliveYoung;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class QnAlistPanel extends JPanel implements ActionListener {

	JButton pageBtn[] = new JButton[5];
	JButton leftBtn;
	JButton rightBtn;

	JTextField title_tf;
	JTextField text_tf;

	int qnaNum = 0;
	JButton tempTitleBtn[] = new JButton[qnaNum];
	JButton titleBtn[] = new JButton[qnaNum];

	int btnSize = 20;

	int pageNum = 1; // �ʱ� ��������ȣ

	int maxNum = 5;
	int minNum = 0;

	public QnAlistPanel() {
		setLayout(null);
		setButton();
		upDateBtn();
	}

	public void setButton() {
		// ������ ��ư
		for (int i = minNum; i < maxNum; i++) {
			pageBtn[i] = new JButton();
			pageBtn[i].setText("" + (i + 1));
			pageBtn[i].setBorder(null);
			pageBtn[i].setBackground(Color.WHITE);
			pageBtn[i].setSize(btnSize, btnSize);
			pageBtn[i].setLocation(220 + (i * btnSize), 400);
			pageBtn[i].addActionListener(this);
			add(pageBtn[i]);
		}

		// ������ �������� �ѱ��
		leftBtn = new JButton("��");
		leftBtn.setBounds(200, 400, btnSize, btnSize);
		leftBtn.setBorder(null);
		leftBtn.setBackground(Color.WHITE);
		leftBtn.addActionListener(this);
		add(leftBtn);

		// ������ ���������� �ѱ��
		rightBtn = new JButton("��");
		rightBtn.setBounds(320, 400, btnSize, btnSize);
		rightBtn.setBorder(null);
		rightBtn.setBackground(Color.WHITE);
		rightBtn.addActionListener(this);
		add(rightBtn);

		LineBorder lineBorder = new LineBorder(Color.LIGHT_GRAY, 3, true);

		// ��ȸ�� Ÿ��Ʋ �ؽ�Ʈ�ʵ�
		title_tf = new JTextField(30);
		title_tf.setBorder(lineBorder);
		title_tf.setBounds(300, 50, 300, 30);
		title_tf.addActionListener(this);
		add(title_tf);

		// ��ȸ�� �ؽ�Ʈ �ؽ�Ʈ�ʵ�
		text_tf = new JTextField(200);
		text_tf.setBorder(lineBorder);
		text_tf.setBounds(300, 100, 300, 200);
		text_tf.addActionListener(this);
		add(text_tf);
	}

	// �ε��ϱ�

	public void loadSet() {

		tempTitleBtn = new JButton[qnaNum];
		int n = 0;
		for (int i = 0; i < QnAManager.qnaManager.qnaList.size(); i++) {
			if (QnAManager.qnaManager.qnaList.get(i).logId.equals(UserManager.usermanager.getId())) {

				tempTitleBtn[n] = new JButton("" + QnAManager.qnaManager.qnaList.get(i).title);
				tempTitleBtn[n].setBorder(null);
				tempTitleBtn[n].setBackground(Color.WHITE);

				n += 1;
			}
		}

		System.out.println("== �ε��� tempTitleBtn[] ��� ==");
		for (int i = 0; i < tempTitleBtn.length; i++) {
			System.out.println(tempTitleBtn[i].getText());
		}

		upDateBtn();
	}

	// ��ư�߰��ϱ�(���̱�X)
	public void setTitleBtn() {

		tempTitleBtn = new JButton[qnaNum + 1];

		int n = 0;
		for (int i = 0; i < QnAManager.qnaManager.qnaList.size(); i++) {
			if (UserManager.usermanager.getId().equals(QnAManager.qnaManager.qnaList.get(i).logId)) {
				tempTitleBtn[n] = new JButton("" + QnAManager.qnaManager.qnaList.get(i).title);
				tempTitleBtn[n].setBackground(Color.WHITE);
				tempTitleBtn[n].setBorder(null);
				n += 1;
			}
		}

		qnaNum += 1;

	}

	// �߰��� ��ư �������� ���� ���̱�
	public void upDateBtn() {
		
		// �پ��ִ� ��ư �����ϱ�
		for (int i = 0; i < titleBtn.length; i++) {	// ���ι�ư����
			if (titleBtn[i] != null) {	// null�� �ƴϸ�
				this.remove(titleBtn[i]);
			}
		}

		int startIdx = (pageNum - 1) * maxNum;
		int lastIdx = startIdx + maxNum;

		if (lastIdx > qnaNum) {
			lastIdx = qnaNum;
		}

		System.out.println("startIdx = " + startIdx);
		System.out.println("lastIdx = " + lastIdx);

		// Ÿ��Ʋ ��ư �ٽ� ���̱�
		titleBtn = new JButton[maxNum];

		int n = 0;
		for (int i = startIdx; i < lastIdx; i++) {
			titleBtn[n] = tempTitleBtn[i];
			titleBtn[n].setBounds(50, 50 + (n * 50), 200, 30);
			titleBtn[n].addActionListener(this);
			add(titleBtn[n]);
			n += 1;
		}
	}

	public void rightBtn() {
		maxNum += 5;
		minNum += 5;
		int n = 0;
		for (int i = minNum; i < maxNum; i++) {
			pageBtn[n].setText("" + (i + 1));
			n += 1;
		}
	}

	public void leftBtn() {
		if (minNum == 0) {
			return;
		} else {
			maxNum -= 5;
			minNum -= 5;
			int n = 0;
			for (int i = minNum; i < maxNum; i++) {
				pageBtn[n].setText("" + (i + 1));
				n += 1;
			}
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		try {
			Thread.sleep(10);
			repaint();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		g.setColor(Color.lightGray);
		
		g.drawLine(0, 13, 600, 13);
		g.drawLine(0, 14, 600, 14);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == rightBtn) {
			rightBtn();
		}

		if (e.getSource() == leftBtn) {
			leftBtn();
		}

		for (int i = 0; i < 5; i++) {
			if (e.getSource() == pageBtn[i]) {
				System.out.println("���� ������ : " + pageBtn[i].getText());
				pageNum = Integer.parseInt(pageBtn[i].getText());

				// ���� ������Ʈ(���� �������� �ִ� ��ư���!)
				upDateBtn();
			}
		}

		for (int i = 0; i < maxNum; i++) {
			int loadIdx = -1;
			if (e.getSource() == titleBtn[i]) {
				// loadIdx�� ��ư�� ��ȣ
				loadIdx = i;

				System.out.println("loadIdx : " + loadIdx);
				for (int j = 0; j < QnAManager.qnaManager.qnaList.size(); j++) {
					if (UserManager.usermanager.getId().equals(QnAManager.qnaManager.qnaList.get(j).logId)
							&& titleBtn[loadIdx].getText().equals(QnAManager.qnaManager.qnaList.get(j).title)) {
						title_tf.setText("" + QnAManager.qnaManager.qnaList.get(j).title);
						text_tf.setText("" + QnAManager.qnaManager.qnaList.get(j).text);
					}

				}
			}
		}

	}

}
