package oliveYoung;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class UserManager {
	// Ŭ���� : �����Ŵ���
	// ��������Ʈ, ȸ������, �α���, �α׾ƿ�, ȸ��Ż��, ȸ������ ����

	static UserManager usermanager = new UserManager();

	ArrayList<User> userList = new ArrayList<>();

	static int logIdx = -1;

	// ȸ������
	public void join(String joinName, String joinId, String joinPw, String joinCity, String joinStreet,
			String joinCode) {
		int check = -1;

		for (int i = 0; i < userList.size(); i++) {
			if (userList.get(i).userId.equals(joinId)) {
				check = i;
			}
		}

		// System.out.println("check : " + check);

		if (check == -1) {
			User temp = new User();
			temp.userName = joinName;
			temp.userId = joinId;
			temp.userPw = joinPw;
			temp.userCity = joinCity;
			temp.userStreet = joinStreet;

			int code = Integer.parseInt(joinCode);
			temp.userCode = code;

			userList.add(temp);
			JOptionPane.showMessageDialog(null, "ȸ������ �Ϸ�!", "�ȳ�", JOptionPane.WARNING_MESSAGE);

			// ȸ������ �Ϸ��� fileManager�� ���� ���� ����
			FileManager.instance.saveUser(usermanager.saveUser(), "user.txt");

			// ȸ������ �Ϸ��� ����ȭ������ �̵�
			Main.frame.setContentPane(new MainPanel());
			Main.frame.revalidate();
		} else {
			JOptionPane.showMessageDialog(null, "�ߺ����̵� �Դϴ�.", "�ȳ�", JOptionPane.WARNING_MESSAGE);
			return;
		}
	}

	// �α���
	public void logIn(String loginId, String loginPw) {
		int check = -1;

		for (int i = 0; i < userList.size(); i++) {
			if (loginId.equals(userList.get(i).userId)) {
				check = i;
			}
		}

		if (check == -1) {
			JOptionPane.showMessageDialog(null, "���̵�� �н����带 Ȯ�����ּ���!", "�ȳ�", JOptionPane.WARNING_MESSAGE);
			return;
		} else {
			int checkPw = -1;
			for (int i = 0; i < userList.size(); i++) {
				if (loginPw.equals(userList.get(check).userPw)) {
					checkPw = i;
				}
			}

			if (checkPw == -1) {
				JOptionPane.showMessageDialog(null, "���̵�� �н����带 Ȯ�����ּ���!", "�ȳ�", JOptionPane.WARNING_MESSAGE);
				return;
			} else {
				// �α��οϷ� => ����гη� �̵�
				logIdx = check;

				Main.frame.setContentPane(new MainPanel());
				Main.frame.revalidate();
			}

		}

	}

	// �α׾ƿ�
	public void logOut() {
		Main.frame.setContentPane(new MainPanel());
		Main.frame.revalidate();
		logIdx = -1;
	}

	// ȸ����������
	public void updateMember(String updateName, String updatePw, String updateCity, String updateStreet,
			String updateCode) {
		userList.get(UserManager.usermanager.logIdx).userName = updateName;
		// userList.get(UserManager.usermanager.logIdx).userId = updateId;
		userList.get(UserManager.usermanager.logIdx).userPw = updatePw;
		userList.get(UserManager.usermanager.logIdx).userCity = updateCity;
		userList.get(UserManager.usermanager.logIdx).userStreet = updateStreet;
		int code = Integer.parseInt(updateCode);
		userList.get(UserManager.usermanager.logIdx).userCode = code;

		// ȸ������ ���� �� fileManager�� ���� ���� ����
		FileManager.instance.saveUser(usermanager.saveUser(), "user.txt");

		JOptionPane.showMessageDialog(null, "������ ����Ǿ����ϴ�!", "�ȳ�", JOptionPane.WARNING_MESSAGE);
	}

	// ȸ��Ż��
	public void removeMember() {
		if (logIdx != -1) {

			int rs = JOptionPane.showConfirmDialog(null, "ȸ�� Ż�� �����Ͻðڽ��ϱ�?", "�ȳ�", JOptionPane.YES_NO_OPTION);
			if (rs == JOptionPane.YES_OPTION) {
				
				// qna����Ʈ���� �α����� ����(logIdx)�� ��ġ�ϴ� ID�� ���� ���� ==> ����� ������ �ֿܼ� ���
				QnAManager.qnaManager.removeQnA();
				
				// ��������Ʈ���� �α����� idx ����
				userList.remove(logIdx);
				JOptionPane.showMessageDialog(null, "ȸ��Ż�� �Ϸ�!", "�ȳ�", JOptionPane.WARNING_MESSAGE);
				
				logIdx = -1;

				// ȸ�� Ż���� fileManager�� ���� ���� ����
				FileManager.instance.saveUser(usermanager.saveUser(), "user.txt");
				
				// ȸ�� Ż���� fileManaget�� ���� qna���� ����
				FileManager.instance.saveQnA(QnAManager.qnaManager.saveQnA(), "qna.txt");
				
				Main.frame.setContentPane(new MainPanel());
				Main.frame.revalidate();
			}

		}
	}

	// ȸ������ ����
	public String saveUser() {
		String data = "";

		for (int i = 0; i < userList.size(); i++) {
			data += userList.get(i).userName;
			data += "/";
			data += userList.get(i).userId;
			data += "/";
			data += userList.get(i).userPw;
			data += "/";
			data += userList.get(i).userCity;
			data += "/";
			data += userList.get(i).userStreet;
			data += "/";
			data += userList.get(i).userCode;

			if (i < userList.size() - 1) {
				data += "\n";
			}
		}

		System.out.println(data);

		return data;
	}

	// ȸ������ �ε��� ��̸���Ʈ�� ����
	public void loadUser(String data) {
		String info[] = data.split("\n");

		for (int i = 0; i < info.length; i++) {
			User temp = new User();
			String userinfo[] = info[i].split("/");

			temp.userName = userinfo[0];
			temp.userId = userinfo[1];
			temp.userPw = userinfo[2];
			temp.userCity = userinfo[3];
			temp.userStreet = userinfo[4];
			temp.userCode = Integer.parseInt(userinfo[5]);

			userList.add(temp);
		}
	}

	// ��й�ȣ ã��
	public void findPw(String userName, String userId) {
		int check = -1;
		String pw = "";
		for (int i = 0; i < userList.size(); i++) {
			if (userId.equals(userList.get(i).userId) && userName.equals(userList.get(i).userName)) {
				check = i;
			}
		}

		if (check == -1) {
			JOptionPane.showMessageDialog(null, "ȸ�������� ã�� �� �����ϴ�.", "�ȳ�", JOptionPane.WARNING_MESSAGE);
		} else {
			pw = userList.get(check).userPw;
			JOptionPane.showMessageDialog(null, "��й�ȣ�� " + pw + " �Դϴ�.", "�ȳ�", JOptionPane.WARNING_MESSAGE);
		}
	}

	// ��������Ʈ ���
	public void printUserList() {
		for (int i = 0; i < userList.size(); i++) {
			System.out.println(userList.get(i).userName + ":" + userList.get(i).userId + ":" + userList.get(i).userPw
					+ ":" + userList.get(i).userCity + ":" + userList.get(i).userStreet + ":"
					+ userList.get(i).userCode);
		}
	}

	// ���ø� ����
	public String getCity() {
		String city = "";
		city += userList.get(logIdx).userCity;

		return city;
	}

	// ���θ� ����
	public String getStreet() {
		String street = "";
		street += userList.get(logIdx).userStreet;

		return street;
	}

	// �����ȣ ����
	public int getCode() {
		int code = 0;
		code += userList.get(logIdx).userCode;

		return code;
	}

	public String getId() {
		return userList.get(logIdx).userId;
	}
	
	public String getName() {
		return userList.get(logIdx).userName;
	}
	
	public int getCartSize() {
		return userList.get(logIdx).cart.cartList.size();
	}
	public int getBuyListSize() {
		return userList.get(logIdx).cart.buyList.size();
	}

}
