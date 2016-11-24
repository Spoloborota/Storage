package com.spoloborota.teaching.storage.type;

import java.util.HashMap;

public class MapStorage {   //
	public static String getMap;
	

	public String name;  						// поле имени 
	public HashMap<String, String> hashMap;		// создание hashmap <строка , строка>
	
	public MapStorage(String name) {			// конструктор принимает имя и присваивает его полю класса, инициализация hashmap
		this.name = name;
		hashMap = new HashMap<>();
	}
	
	public boolean add(String[] keyValue) {    	// метод add принимает строки ,должно быть 2 строки мин.
		hashMap.put(keyValue[0], keyValue[1]);	// в hashmap помещаются ключ первое слово, значение второе
		return true;							// вернули true 
	}
	
	public boolean get(String name){
		getMap = hashMap.entrySet().toString();
		return true;
	}
	
}
