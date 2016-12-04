package com.spoloborota.teaching.storage.type;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
	
	public ArrayList<String> print() {
		String a = "";
		ArrayList<String> arr = new ArrayList<String>();
		for (Map.Entry<String, String> pair : hashMap.entrySet()){
		 String key = pair.getKey();
		 String value = pair.getValue();
		 a = key + " " + value;
		 arr.add(0, a);
		}
		return arr;
	}
	public String save() {
		String a = "";
		ArrayList<String> arr = new ArrayList<String>();
		for (Map.Entry<String, String> pair : hashMap.entrySet()){
		 String key = pair.getKey();
		 String value = pair.getValue();
		 a = key + "\n" + value + "\n";
		 arr.add(a);
		}
		return arr.toString();
	}
}
