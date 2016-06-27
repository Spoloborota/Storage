package com.spoloborota.teaching.storage.processor.type;

import java.io.IOException;

import com.spoloborota.teaching.storage.model.RAM;
import com.spoloborota.teaching.storage.model.ROM;

public class Save {
	
	public static String process(RAM ram, ROM rom) throws IOException{
		return ram.save(rom);
	}

}
