package com.spoloborota.teaching.storage.type;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import com.spoloborota.teaching.storage.file_save.FileSave;

public class MapStorage { //
	private static String getMap;

	public static String getGetMap() {
		return getMap;
	}

	public String name; // поле имени
	public HashMap<String, String> hashMap; // создание hashmap <строка ,
	// строка>

	public MapStorage(String name) { // конструктор принимает имя и присваивает
		// его полю класса, инициализация
		// hashmap
		this.name = name;
		hashMap = new HashMap<>();
	}

	public boolean add(String[] keyValue) { // метод add принимает строки,должно быть 2 строки мин.
		hashMap.put(keyValue[0], keyValue[1]); // в hashmap помещаются ключ первое слово, значение второе
		return true; // вернули true
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
