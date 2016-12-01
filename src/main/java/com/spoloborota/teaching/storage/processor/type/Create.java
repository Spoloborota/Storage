package com.spoloborota.teaching.storage.processor.type;

import com.spoloborota.teaching.storage.model.RAM;

public class Create {

	public static String process(RAM ram, String[] commandWords) {
		boolean isCreated = ram.create(commandWords[1]);
		if (isCreated) {
			return "Storage " + commandWords[1] + " is created";
		} else {
			return "Storage " + commandWords[1] + " already exists";
		}
	}
}
