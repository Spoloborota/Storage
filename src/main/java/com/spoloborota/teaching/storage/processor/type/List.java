package com.spoloborota.teaching.storage.processor.type;

import com.spoloborota.teaching.storage.model.RAM;

public class List {
	
	public static String process(RAM ram, String[] commandWords){
		return ram.list(commandWords[1]);
	}
}
