package com.spoloborota.teaching.storage.main;

import java.io.FileNotFoundException;

import com.spoloborota.teaching.storage.model.RAM;
import com.spoloborota.teaching.storage.processor.Processor;
import com.spoloborota.teaching.storage.type.LoadStorage;
import com.spoloborota.teaching.storage.view.Console;;

/**
 * Main class
 * @author Spoloborota
 *
 */
public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		final String source = "src/main/java/com/spoloborota/teaching/storage/storage_files";
		
		RAM ram = new RAM();
		LoadStorage.load(ram, source);
		Processor processor = new Processor(ram);
		Console console = new Console(processor);
		console.startListen();
	}

}
