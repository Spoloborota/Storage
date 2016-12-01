package com.spoloborota.teaching.storage.file_load;

import com.spoloborota.teaching.storage.model.RAM;

public class UseLoad {
	public static void process(RAM ram, String commandWords) {
		boolean isSelected = ram.use(commandWords);
	}
}
