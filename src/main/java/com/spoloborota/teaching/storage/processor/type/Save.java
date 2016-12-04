package com.spoloborota.teaching.storage.processor.type;

import com.spoloborota.teaching.storage.model.RAM;

public class Save {
	public static String process(RAM ram, String directoryPath){
		return ram.save(directoryPath);
	}
	
}
