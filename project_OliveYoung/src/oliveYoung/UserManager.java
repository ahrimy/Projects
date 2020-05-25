package oliveYoung;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;


public class UserManager{
		// Ŭ���� : �����Ŵ���
		// ��������Ʈ, ȸ������, �α���, �α׾ƿ�, ȸ��Ż��, ȸ������ ����
		
		static UserManager usermanager = new UserManager(); 
		
		ArrayList<User> userList = new ArrayList<>();
		
		static int logIdx = -1;
		
		// ȸ������
		public void join(String joinName, String joinId, String joinPw) {	
			int check = -1;
			for(int i=0; i<userList.size(); i++) {
				if(userList.get(i).userId.equals(joinId)) {
					check = i;
				}
			}
			
			if(check != -1) {
				JOptionPane.showMessageDialog(null, "�ߺ����̵� �Դϴ�.", "�ȳ�", JOptionPane.WARNING_MESSAGE);
				return;
			}else {
				User temp = new User();
				temp.userName = joinName;
				temp.userId = joinId;
				temp.userPw = joinPw;
				
				userList.add(temp);
				JOptionPane.showMessageDialog(null, "ȸ������ �Ϸ�!", "�ȳ�", JOptionPane.WARNING_MESSAGE);				
				
				// ȸ������ �Ϸ��� fileManager�� ���� ���� ����
				FileManager.instance.saveUser(usermanager.saveUser(), "user.txt");
				
				// ȸ������ �Ϸ��� ����ȭ������ �̵�
				Main.frame.setContentPane(new MainPanel());
				Main.frame.revalidate();
			}
		}
		
		// �α���
		public void logIn(String loginId, String loginPw) {
			int check = -1;
			
			for(int i=0; i<userList.size(); i++) {
				if(loginId.equals(userList.get(i).userId)) {
					check = i;
				}
			}
			
			if(check == -1) {
				JOptionPane.showMessageDialog(null, "���̵�� �н����带 Ȯ�����ּ���!", "�ȳ�", JOptionPane.WARNING_MESSAGE);	
				return;
			}else {
				int checkPw = -1;
				for(int i=0; i<userList.size(); i++) {
					if(loginPw.equals(userList.get(check).userPw)) {
						checkPw = i;
					}
				}
				
				if(checkPw == -1) {
					JOptionPane.showMessageDialog(null, "���̵�� �н����带 Ȯ�����ּ���!", "�ȳ�", JOptionPane.WARNING_MESSAGE);	
					return;					
				}else {
					JOptionPane.showMessageDialog(null, "�α��� �Ϸ�!", "�ȳ�", JOptionPane.WARNING_MESSAGE);	
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
		public void updateMember(String updateName, String updateId, String updatePw) {
			userList.get(UserManager.usermanager.logIdx).userName = updateName;
			userList.get(UserManager.usermanager.logIdx).userId = updateId;
			userList.get(UserManager.usermanager.logIdx).userPw = updatePw;
			
			// ȸ������ ���� �� fileManager�� ���� ���� ����
			FileManager.instance.saveUser(usermanager.saveUser(), "user.txt");			
			
			JOptionPane.showMessageDialog(null, "������ ����Ǿ����ϴ�!", "�ȳ�", JOptionPane.WARNING_MESSAGE);	
		}
	
		// ȸ��Ż��
		public void  removeMember() {
			if(logIdx != -1) {
				userList.remove(logIdx);
				JOptionPane.showMessageDialog(null, "ȸ��Ż�� �Ϸ�!", "�ȳ�", JOptionPane.WARNING_MESSAGE);	
				logIdx = -1;
				
				Main.frame.setContentPane(new MainPanel());
				Main.frame.revalidate();
			}
		}
		
		// ȸ������ ����
		public String saveUser() {
			String data = "";
		
			for(int i=0; i<userList.size(); i++) {
					data += userList.get(i).userName; data += "/";
					data += userList.get(i).userId; data += "/";
					data += userList.get(i).userPw; 	
				if(i < userList.size() - 1) {
					data += "\n";
				}
			}
			
			System.out.println(data);
				
			return data;
		}
		
		// ȸ������ �ε��� ��̸���Ʈ�� ����
		public void loadUser(String data) {
			String info[] = data.split("\n");
			
			for(int i=0; i<info.length; i++) {
				User temp = new User();
				String userinfo[] = info[i].split("/");
				
				temp.userName = userinfo[0];
				temp.userId = userinfo[1];
				temp.userPw = userinfo[2];
				
				userList.add(temp);
			}
			
			// �ε�Ȯ�� : �ܼ�
			for(int i=0; i<userList.size(); i++) {
				System.out.println(userList.get(i).userName + ":" + userList.get(i).userId + ":" + userList.get(i).userPw);
			}
		}
		
		
		
		
}
