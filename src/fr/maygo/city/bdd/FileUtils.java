package fr.maygo.city.bdd;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtils {

	public static void createFile(File file) throws IOException {
		if (!file.exists()) {
			file.getParentFile().mkdirs();
			file.createNewFile();
		}
	}
	
	public static void deleteFile(File file) throws IOException {
		if (file.exists()) {
			file.delete();
		}
	}

	public static void saveFile(File file, String text) throws IOException {
		final FileWriter fw;
		createFile(file);

		fw = new FileWriter(file);
		fw.write(text);
		fw.flush();
		fw.close();
	}

	public static String loadFile(File file) throws IOException {
		if(file.exists()) {
			final BufferedReader reader = new BufferedReader(new FileReader(file));
			final StringBuilder text = new StringBuilder();
			
			String line;
			
			while ((line = reader.readLine()) != null) {
				text.append(line);
			}
			
			reader.close();
			
			return text.toString();
		}
		return "";
	}

}
