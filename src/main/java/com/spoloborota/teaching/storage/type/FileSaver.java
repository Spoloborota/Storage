package com.spoloborota.teaching.storage.type;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileSaver {
	String directoryPath;
	String fileToSavePath;
	String data;

	public FileSaver(String data, String directoryPath, String fileName) {
		this.data = data;
		this.directoryPath = directoryPath;
		fileToSavePath = directoryPath + "\\" + fileName + ".storage";
	}
	/**
	 * Proveryaet est' li directoriya peredannaya is komandnoi stroki.
	 * Proveryaet chto eto deistvitelno directoriya.
	 * Zapisivaet podgotovlennuyu stroky v fail <tek storage>.storage.
	 */
	public int save(){
		File f1 = new File(directoryPath);

		if (!f1.exists()){
			return 1;
		}	
		if (!f1.isDirectory()){
			return 2;
		}
		f1 = null; // bolshe ne nuzhen
		
		try{
			FileWriter f = new FileWriter(fileToSavePath);
			f.write(data);			
			f.close();
		}
		catch(IOException e){
		}
		return 0;
	}
}
