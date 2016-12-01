package com.spoloborota.teaching.storage.processor.type;

import com.spoloborota.teaching.storage.model.RAM;

public class Display {

	public static String process(RAM ram) {
		return ram.display(); // принимает ram и вызывает метод display из RAM
	}

}
