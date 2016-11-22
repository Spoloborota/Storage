package com.spoloborota.teaching.storage.processor.type;

import com.spoloborota.teaching.storage.model.RAM;

public class Delete {
	
	public static String process(RAM ram, String[] commandWords) {
		boolean isDeleted = ram.delete(commandWords[1]);
		if (isDeleted) {
			return "Storage " + commandWords[1] + " is deleted";
		} else {
			return "Storage " + commandWords[1] + " not exists";
		}
	}
}
