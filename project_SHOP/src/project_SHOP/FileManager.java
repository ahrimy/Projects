package project_SHOP;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileManager {
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

	String load(String fileName) {
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

					data += line;
					data += "\n";
				}
				if (data.length() > 0) {
					data = data.substring(0, data.length() - 1);
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

		return data;
	}
}
