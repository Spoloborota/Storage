package com.spoloborota.teaching.storage.processor.type;

import com.spoloborota.teaching.storage.model.RAM;
import com.spoloborota.teaching.storage.type.MapStorage;

public class Save {

	public static String process(RAM ram, String[] commandWords) {
		boolean isSelected = ram.save(commandWords[1]);
		if (isSelected) {
			return "Storage " + commandWords[1] + " saved";
		} else {
			return "Must use " + commandWords[1] + "  Storage";
		}
	}

}
