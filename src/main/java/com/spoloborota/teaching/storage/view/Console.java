package com.spoloborota.teaching.storage.view;

import java.io.BufferedReader;
import java.io.IOException;

import com.spoloborota.teaching.storage.processor.Processor;
import com.spoloborota.teaching.storage.reader.SingletonReader;

/**
 * Commands received via system console
 * @author Spoloborota
 *
 */
public class Console {
	public Processor processor;
	public SingletonReader rdr = SingletonReader.getInstance(); 
	public static String path = "";

	public Console(Processor processor) {
		this.processor = processor;
	}
	
	

	public void startListen() {
		while(true) {
			try {
				
				String commandString = rdr.readLine();
				if(commandString.contains("\\")){
					path = commandString + "\\save.txt";
					System.out.println("Your path is " + path);
				}
				else{
					String result = processor.process(commandString);// вот тут если что уберешь
					System.out.println(result);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public String getPath(){
		return path;
	}
}
