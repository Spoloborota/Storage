package com.spoloborota.teaching.storage.type;

import java.util.HashMap;

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

	public String list() {
		return hashMap.entrySet().toString();
	}
}
