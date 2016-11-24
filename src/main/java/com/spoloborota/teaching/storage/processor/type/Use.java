package com.spoloborota.teaching.storage.processor.type;

import com.spoloborota.teaching.storage.model.RAM;

public class Use {
	public static String process(RAM ram, String[] commandWords) {  // принимаем ссылку на ram и массив строк
		boolean isSelected = ram.use(commandWords[1]);				// создаем переменную boolean и вызываем метод use класса RAM передавая туда второе слово после команды use
		if (isSelected) {
			return "Selected storage: " + commandWords[1];    	// если есть такой ключ в map, то true  
		} else {
			return "Storage with name " + commandWords[1] + " does not exist"; // если нет ключа в map то use вернет false
		}
	}
}
