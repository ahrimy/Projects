package user;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class UserDAO {
	private static UserDAO instance = new UserDAO();

	private UserDAO() {
	}

	public static UserDAO getInstance() {
		return instance;
	}

	public String filePath = "";
	String fileName = "user.txt";

	ArrayList<User> userList = new ArrayList<User>();

	public void loadData() {
		File file = new File(filePath + fileName);
		FileReader fr = null;
		BufferedReader br = null;

		try {
			if (file.exists()) {
				fr = new FileReader(file);
				br = new BufferedReader(fr);

				userList.clear();

				while (true) {
					String line = br.readLine();
					if (line == null)
						break;

					String[] info = line.split("/");
					User user = new User(info[0], info[1], info[2], info[3]);

					userList.add(user);
				}
				System.out.println("[메세지]유저 파일을 로드하였습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fr != null) {
				try {
					fr.close();
				} catch (IOException e) {
				}
			}
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
				}
			}
		}
	}

	public void saveData() {
		String data = "";

		for (int i = 0; i < userList.size(); i++) {
			User user = userList.get(i);
			data += user.getId();
			data += "/";
			data += user.getPw();
			data += "/";
			data += user.getName();
			data += "/";
			data += user.getEmail();

			if (i != userList.size() - 1) {
				data += "\n";
			}
		}

		FileWriter fw = null;
		try {
			fw = new FileWriter(filePath + fileName);
			fw.write(data);
			System.out.println("[메세지]유저 파일을 저장하였습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
				}
			}
		}
	}

	public int findUser(String ID) {
		int index = -1;
		for (int i = 0; i < userList.size(); i++) {
			if (userList.get(i).getId().equals(ID)) {
				index = i;
				break;
			}
		}
		return index;
	}

	public String getEmail(int index) {
		return userList.get(index).getEmail();
	}
	public String getPassword(int index) {
		return userList.get(index).getPw();
	}
	public String getName(int index) {
		return userList.get(index).getName();
	}
	public boolean checkPassword(int index, String password) {
		boolean check = false;

		if (userList.get(index).getPw().equals(password)) {
			check = true;
		}

		return check;
	}

	public String signIn(User user) {
		int log = findUser(user.getId());
		if (log != -1) {
			if (checkPassword(log, user.getPw())) {
				return userList.get(log).getId();
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
	public void update(User user) {
		int log = findUser(user.getId());
		userList.get(log).setEmail(user.getEmail());
		userList.get(log).setName(user.getName());
		userList.get(log).setPw(user.getPw());
		saveData();
	}
	public boolean join(User user) {
		int log = findUser(user.getId());
		if (log == -1) {
			userList.add(user);
			saveData();
			return false;
		} else {
			return true;
		}
	}

}
