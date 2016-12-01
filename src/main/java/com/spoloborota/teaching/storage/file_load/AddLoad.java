package com.spoloborota.teaching.storage.file_load;

import com.spoloborota.teaching.storage.model.RAM;

public class AddLoad {
	public static void process(RAM ram, java.util.List<String> lines) {
		boolean isAdded = ram.add(new String[] { lines.get(0), lines.get(1) });
	}

}
