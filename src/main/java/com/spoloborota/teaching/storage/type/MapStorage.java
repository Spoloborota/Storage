package com.spoloborota.teaching.storage.type;

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
}
