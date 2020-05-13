package project_ATM;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileManager {
	String fileName = "userList.txt";
	void save(UserManager manager){
		String data = manager.cnt + "\n";
		for(int i=0;i<manager.cnt;i++){
			data+=(manager.users.get(i).cnt+"/");
			data+=(manager.users.get(i).id+"/");
			data+=(manager.users.get(i).pw+"\n");
			for(int j=0;j<manager.users.get(i).cnt;j++){
				data+=(manager.users.get(i).accounts.get(j).accNum+"/");
				data+=(manager.users.get(i).accounts.get(j).balance+"\n");
			}
		}
		data = data.substring(0,data.length()-1);
		FileWriter fw = null;
		try {
			fw = new FileWriter(fileName);
			fw.write(data);
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
	ArrayList<User> load(){
		String data = "";
		File file = new File(fileName);
		FileReader fr = null;
		BufferedReader br = null;
		ArrayList<User> users = new ArrayList<>();
		
		if(file.exists()) {
			
			try {
				fr = new FileReader(file);
				br = new BufferedReader(fr);
				while (true) {
					String line = br.readLine();
					if (line == null) {
						break;
					}

					data += line;
					data += "\n";
				}
				data = data.substring(0, data.length()-1);
				String[] list = data.split("\n");
				int listReader = 0;
				int userCount = Integer.parseInt(list[listReader]);//number of user
				
				for(int i=0;i<userCount;i++){
					String temp[] = list[++listReader].split("/");
					int accCount = Integer.parseInt(temp[0]);
					String id = temp[1];
					String pw = temp[2];
					users.add(new User(id,pw));
					for(int j=0;j<accCount;j++){
						String info[] = list[++listReader].split("/");
						int num = Integer.parseInt(info[0]);
						int balance = Integer.parseInt(info[1]);
						users.get(i).accounts.add(new Account(num,balance));
						users.get(i).cnt = accCount;
					}
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				if(fr != null) {try {fr.close();} catch (IOException e) {}}
				if(br != null) {try {br.close();} catch (IOException e) {}}
			}
		}

		return users;
	}
	
}