package oliveYoung;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;


public class UserManager{
		// 클래스 : 유저매니저
		// 유저리스트, 회원가입, 로그인, 로그아웃, 회원탈퇴, 회원정보 수정
		
		static UserManager usermanager = new UserManager(); 
		
		ArrayList<User> userList = new ArrayList<>();
		
		static int logIdx = -1;
		
		// 회원가입
		public void join(String joinName, String joinId, String joinPw) {	
			int check = -1;
			for(int i=0; i<userList.size(); i++) {
				if(userList.get(i).userId.equals(joinId)) {
					check = i;
				}
			}
			
			if(check != -1) {
				JOptionPane.showMessageDialog(null, "중복아이디 입니다.", "안내", JOptionPane.WARNING_MESSAGE);
				return;
			}else {
				User temp = new User();
				temp.userName = joinName;
				temp.userId = joinId;
				temp.userPw = joinPw;
				
				userList.add(temp);
				JOptionPane.showMessageDialog(null, "회원가입 완료!", "안내", JOptionPane.WARNING_MESSAGE);				
				
				// 회원가입 완료후 fileManager에 유저 정보 저장
				FileManager.instance.saveUser(usermanager.saveUser(), "user.txt");
				
				// 회원가입 완료후 메인화면으로 이동
				Main.frame.setContentPane(new MainPanel());
				Main.frame.revalidate();
			}
		}
		
		// 로그인
		public void logIn(String loginId, String loginPw) {
			int check = -1;
			
			for(int i=0; i<userList.size(); i++) {
				if(loginId.equals(userList.get(i).userId)) {
					check = i;
				}
			}
			
			if(check == -1) {
				JOptionPane.showMessageDialog(null, "아이디와 패스워드를 확인해주세요!", "안내", JOptionPane.WARNING_MESSAGE);	
				return;
			}else {
				int checkPw = -1;
				for(int i=0; i<userList.size(); i++) {
					if(loginPw.equals(userList.get(check).userPw)) {
						checkPw = i;
					}
				}
				
				if(checkPw == -1) {
					JOptionPane.showMessageDialog(null, "아이디와 패스워드를 확인해주세요!", "안내", JOptionPane.WARNING_MESSAGE);	
					return;					
				}else {
					JOptionPane.showMessageDialog(null, "로그인 완료!", "안내", JOptionPane.WARNING_MESSAGE);	
					// 로그인완료 => 멤버패널로 이동
					logIdx = check;
					
					Main.frame.setContentPane(new MainPanel());
					Main.frame.revalidate();
				}
				
			}
			
		}
		
		// 로그아웃
		public void logOut() {
			Main.frame.setContentPane(new MainPanel());
			Main.frame.revalidate();
			logIdx = -1;
		}
		
		
		// 회원정보수정
		public void updateMember(String updateName, String updateId, String updatePw) {
			userList.get(UserManager.usermanager.logIdx).userName = updateName;
			userList.get(UserManager.usermanager.logIdx).userId = updateId;
			userList.get(UserManager.usermanager.logIdx).userPw = updatePw;
			
			// 회원정보 수정 후 fileManager에 유저 정보 저장
			FileManager.instance.saveUser(usermanager.saveUser(), "user.txt");			
			
			JOptionPane.showMessageDialog(null, "정보가 저장되었습니다!", "안내", JOptionPane.WARNING_MESSAGE);	
		}
	
		// 회원탈퇴
		public void  removeMember() {
			if(logIdx != -1) {
				userList.remove(logIdx);
				JOptionPane.showMessageDialog(null, "회원탈퇴 완료!", "안내", JOptionPane.WARNING_MESSAGE);	
				logIdx = -1;
				
				Main.frame.setContentPane(new MainPanel());
				Main.frame.revalidate();
			}
		}
		
		// 회원정보 저장
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
		
		// 회원정보 로드후 어레이리스트에 저장
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
			
			// 로드확인 : 콘솔
			for(int i=0; i<userList.size(); i++) {
				System.out.println(userList.get(i).userName + ":" + userList.get(i).userId + ":" + userList.get(i).userPw);
			}
		}
		
		
		
		
}
