package com.spoloborota.teaching.storage.processor.type;

import com.spoloborota.teaching.storage.model.RAM;

public class Save {
	
	public static String process(RAM ram){
		boolean isSaved = ram.saveStorage();
		if (isSaved)
			return "Storage is saved";
		else
			return "Storage not saved";
	}
}
