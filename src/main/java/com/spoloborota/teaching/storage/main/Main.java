package com.spoloborota.teaching.storage.main;

import java.io.IOException;


import com.spoloborota.teaching.storage.model.ROM;
import com.spoloborota.teaching.storage.processor.Processor;
import com.spoloborota.teaching.storage.view.Console;;

/**
 * Main class
 * @author Spoloborota
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		
		ROM rom = new ROM(args);
		Processor processor = new Processor(rom.getRAM(), rom);
		Console console = new Console(processor);
		console.startListen();
	}

}
