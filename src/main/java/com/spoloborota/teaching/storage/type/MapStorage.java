package com.spoloborota.teaching.storage.type;

import java.util.HashMap;
import java.util.Iterator;

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
	
	public String getDataToSave(){
		StringBuilder preparedBuffer = new StringBuilder();
		Iterator<HashMap.Entry<String, String>> iterator = hashMap.entrySet().iterator();

		while (iterator.hasNext())
		{
			HashMap.Entry<String, String> pair = iterator.next();
			String key = pair.getKey();
			String value = pair.getValue();
			preparedBuffer.append(key);
			preparedBuffer.append("\r\n");
			preparedBuffer.append(value);
			preparedBuffer.append("\r\n");
		}
		return preparedBuffer.toString();
	}
}
