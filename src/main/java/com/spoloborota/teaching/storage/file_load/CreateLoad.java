package com.spoloborota.teaching.storage.file_load;

import com.spoloborota.teaching.storage.model.RAM;

public class CreateLoad {
	public static void process(RAM ram, String commandWords) {
		boolean isCreated = ram.create(commandWords);
	}
}
