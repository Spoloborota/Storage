package com.spoloborota.teaching.storage.processor.type;

import com.spoloborota.teaching.storage.model.RAM;

public class Shutdown {
	public static String process(RAM ram){
		return ram.shutdown();
	}
}