package com.spoloborota.teaching.storage.view;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

import com.spoloborota.teaching.storage.processor.Processor;
import com.spoloborota.teaching.storage.reader.SingletonReader;
import com.spoloborota.teaching.storage.model.*;
import com.spoloborota.teaching.storage.type.*;


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
					path = commandString + "\\";
//					System.out.println("Your path is " + path);	
					File f = new File(path);
					if(f.isDirectory()){
						System.out.println("Now your directory is" + path);
					}
					
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
