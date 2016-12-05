package com.spoloborota.teaching.storage.type;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import com.spoloborota.teaching.storage.file_save.FileSave;

public class MapStorage {
	private static String getMap;

	public static String getGetMap() {
		return getMap;
	}

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

	public boolean get(String name) {
		getMap = hashMap.entrySet().toString();
		return true;
	}

	public boolean save(String name) {

		getMap = hashMap.entrySet().toString();
		String file_name = name + ".storage";
		File file = new File(FileSave.path, file_name);
		try {

			if (!file.exists()) {
				file.createNewFile();
			}

			PrintWriter out = new PrintWriter(file.getAbsoluteFile());

			try {

				for (String key : hashMap.keySet()) {
					String value = hashMap.get(key);
					out.println(key);
					out.println(value);
				}

			} finally {
				out.close();

			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return true;
	}

}
