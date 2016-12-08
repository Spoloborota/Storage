package com.spoloborota.teaching.storage.type;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.spoloborota.teaching.storage.model.RAM;
import com.spoloborota.teaching.storage.processor.type.Create;


public class LoadStorage {

	public RAM ram;
	static public String source;
	static public File source_f;
	public boolean isDir;


	public static void load(RAM ram, String source) throws FileNotFoundException {

		source_f = new File(source);
		if (source_f.isDirectory()) {
			String[] list=source_f.list();
			for(String fileName: list){ 
				String [] result = fileName.split("\\.+");
				if (result[1].equals("storage")) {
					ram.create(result[0]);
					ram.use(result[0]);
					String fName = source + "/" + result[0] + "." + result[1];
					read(fName, ram);

				}

			}

		} else {
			System.out.println("The " + source_f.getAbsolutePath() + " is not Directory");

		}

	}

	public static String read(String fName, RAM ram) throws FileNotFoundException {

		source_f = new File(fName);
		StringBuilder sb = new StringBuilder();
		String [] toAdd = null;

		try {

			BufferedReader in = new BufferedReader(new FileReader(source_f.getAbsoluteFile()));
			try {

				String s;
				int i = 0;

				while ((s = in.readLine()) != null) {
					
					sb.append(s);
					sb.append("\n");
					i++;
					if (i%2 == 0 ) {
						String str = sb.toString();
						toAdd = str.trim().split("\\s+");
						ram.add(toAdd);
						sb.delete(0,sb.length());
					}
					
				}

			} finally {

				in.close();
				
			}
		} catch(IOException e) {
			throw new RuntimeException(e);
		}

		
		return "";
	}

}
