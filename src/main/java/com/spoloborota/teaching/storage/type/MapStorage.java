package com.spoloborota.teaching.storage.type;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map.Entry;;

public class MapStorage {

	public String name;
	public HashMap<String, String> hashMap;
	
	public MapStorage(String name) {
		this.name = name;
		hashMap = new HashMap<>();
	}
	
	public boolean add(String[] keyValue) {
		hashMap.put(keyValue[0], keyValue[1]);
		return true;
	}
	
	public boolean list() {
		
		for (Entry <String, String> entry: hashMap.entrySet()) { 
			String key = entry.getKey(); 
			String value = entry.getValue(); 
			System.out.println(key + " " + value);
			} 
		
		System.out.println("command list for storage: " + name);
		return true;
	}
	
	public boolean save(String source) throws IOException, ClassNotFoundException{
		
		File f = new File(source, name + ".storage");
        FileOutputStream fileOutputStream = new FileOutputStream(f);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(f));
        PrintWriter out = new PrintWriter(f);
				
		for (Entry <String, String> entry: hashMap.entrySet()) { 
			String key = entry.getKey(); 
			String value = entry.getValue(); 
			out.println(key);
			out.println(value);
			
			}
		out.close();
		
		return true;
	}
	
	
}
