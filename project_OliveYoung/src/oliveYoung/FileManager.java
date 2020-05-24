package oliveYoung;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileManager {
	public static FileManager instance = new FileManager();
	void save(String inputData, String fileName) {
		String data = inputData;
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

	public void load(String fileName) {
		String data = "";
		File file = new File(fileName);
		FileReader fr = null;
		BufferedReader br = null;

		if (file.exists()) {

			try {
				fr = new FileReader(file);
				br = new BufferedReader(fr);
				while (true) {
					String line = br.readLine();
					if (line == null) {
						break;
					}
					if (line != null) {
						data = line;
						ItemManager.instance.loadItem(data);
					}
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

	}
}
