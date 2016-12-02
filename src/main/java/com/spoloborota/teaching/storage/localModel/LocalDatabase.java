package com.spoloborota.teaching.storage.localModel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map.Entry;

import com.spoloborota.teaching.storage.model.RAM;
import com.spoloborota.teaching.storage.type.MapStorage;

public class LocalDatabase {

	private static File file = null;
	
	public static void unloadingDatabase(MapStorage currentStorage) throws Exception{
		if (file != null){
			String path = file.getAbsolutePath();
			path += "\\" + currentStorage.name + ".storage";
			System.out.println(path);
			File currentFile = new File(path);
			if (currentFile.exists())
				currentFile.delete();
			PrintWriter out = new PrintWriter(currentFile);
			Iterator<Entry<String, String>> it = currentStorage.hashMap.entrySet().iterator();
			while (it.hasNext()){
				String key = it.next().getKey();
				out.println("key = " + key);
				out.println("Value = " + currentStorage.hashMap.get(key));
			}
			out.close();
		}
	}
	
	public static void loadingDatabase(File fileArgs, RAM ram){
		file = fileArgs;
		if (file.isDirectory()){
			String[] fileList = file.list();
			for (int i = 0; i < fileList.length; i++){
				int lastIndex = fileList[i].lastIndexOf(".storage");
				if (lastIndex != -1){
					BufferedReader in = null;
					try {
						in = new BufferedReader(new FileReader(file.getAbsolutePath() + "\\" + fileList[i]));
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
					String nameStorage = fileList[i].substring(0, lastIndex);
					ram.create(nameStorage);
					ram.use(nameStorage);
					String strLine = new String();
					try {
						while ((strLine = in.readLine()) != null){
							String nextLine = in.readLine();
							String[] currentStr = new String[]{strLine.substring(strLine.indexOf('=') + 2, strLine.length()), 
									nextLine.substring(nextLine.indexOf('=') + 2, nextLine.length())};
							ram.add(currentStr);
						}
						in.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	private LocalDatabase(){};
}
