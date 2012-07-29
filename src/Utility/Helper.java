package Utility;

import java.io.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Helper {
	private static final String CLASSNAME = "Helper";
	
	static public String getContents(File aFile) {
		StringBuilder contents = new StringBuilder();
		try {
			BufferedReader input =  new BufferedReader(new FileReader(aFile));
			try {
				String line = null;
				while((line = input.readLine()) != null) {
					contents.append(line);
					contents.append(System.getProperty("line.separator"));
				}
			} finally {
				input.close();
			}
		} catch(Exception e) {
			System.err.println(CLASSNAME + ": " + e.getMessage());
		}
		return contents.toString();
	}

	
	static public String getCurrentDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String currentDateTime = dateFormat.format(date);
		return currentDateTime;
	}
}
