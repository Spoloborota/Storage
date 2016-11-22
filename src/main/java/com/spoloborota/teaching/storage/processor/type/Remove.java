package com.spoloborota.teaching.storage.processor.type;

import com.spoloborota.teaching.storage.model.RAM;

public class Remove {
	
	public static String process(RAM ram, String[] commandWords) {
		String isRemoved = ram.remove(new String[]{commandWords[1]});
		if (isRemoved != null) {
			return "Data removed";
		} else {
			return "There is no selected key";
		}
	}
}
