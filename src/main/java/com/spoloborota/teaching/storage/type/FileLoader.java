package com.spoloborota.teaching.storage.type;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileLoader {
	private String directoryPath;
	private String rezultContains="";
	private String[] rez ={};
	
	public FileLoader(String directoryPath) {
		this.directoryPath = directoryPath;
	}
	
	public String[] getRez() {
		return rez;
	}
	/**
	 * 
	 * Chitaet faili iz directorii peredannoi is komandnoi stroki.
	 * Formiruet flag (0 - vse horosho; v pole rez - spisok failov *.storage
	 * 					1 - ukazan put' na fail.
	 * 					2 - takoi directorii/faila net.) 
	 */
	public int searchAndFilter(){
		String [] catalogContains = {};
		String tek = ".storage";
		File f1 = new File(directoryPath);
		int errorFlag = 0;

		if (f1.exists()){
			if (f1.isDirectory()){
				catalogContains = f1.list();

				for (int i = 0; i<catalogContains.length;i++){
					if (catalogContains[i].contains(tek)){
						rezultContains = rezultContains + catalogContains[i] + "\n";
					}
				}
				rez = (rezultContains.trim().split("\n"));
			}
			else{
				errorFlag  = 1;
			}
		}
		else{
			errorFlag  = 2;
		}
		return errorFlag;		
	}
	
	/**
	 * Poluchaet dlinu bufera dlya sozdaniya bufera v kotorii budet chitat'sya fail: char [] dataFromFile.
	 * Chitaet dannie iz faila *.storage v bufer: char [] dataFromFile.
	 * Formatiruet dannie dlya zapisi v HashMap. 
	 */	
	public String [] load(String from, long bufSize){
		char [] dataFromFile = new char [(int)bufSize];
		String stringOfFile = "";
		String [] formattedData = {};

		try{
			FileReader f = new FileReader(from);
			f.read(dataFromFile);
			stringOfFile = new String(dataFromFile);
			formattedData = stringOfFile.trim().split("\r\n");
			f.close();
		}
		catch(IOException e){
		}
		return formattedData;
	}		
}
