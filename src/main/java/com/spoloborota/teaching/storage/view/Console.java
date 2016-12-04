package com.spoloborota.teaching.storage.view;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

import com.spoloborota.teaching.storage.processor.Processor;
import com.spoloborota.teaching.storage.reader.SingletonReader;

/**
 * Commands received via system console
 * @author Spoloborota
 *
 */
public class Console {
	public static String url = "";
	public Processor processor;
	public SingletonReader rdr = SingletonReader.getInstance(); 
	
	public Console(Processor processor) {
		this.processor = processor;
	}
	
	public void startListen() {
		while(true) {
			try {
				String commandString = rdr.readLine();
				boolean flag;
				flag = commandString.contains("\\");
				if (flag == true){
					url = commandString;
					File f1 = new File(url);
					if (f1.isDirectory()) {
						System.out.println("Selected directory: " + url);
					} else {
						System.out.println("Directory does not exist. You must specify a directory.");
					}
				} else { 
					String result = processor.process(commandString);
					System.out.println(result);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
