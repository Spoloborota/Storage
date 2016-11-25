package com.spoloborota.teaching.storage.processor.type;

import com.spoloborota.teaching.storage.model.RAM;
import com.spoloborota.teaching.storage.type.MapStorage;

public class List {
	public static String process(RAM ram, String[] commandWords) {
		boolean isSelected = ram.list(commandWords[1]);
		if (isSelected) {
			return "Show storage: " + MapStorage.getGetMap();
		} else {
			return "Must use " + commandWords[1] + "  Storage";
		}
	}
}
