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


		

		final String source;
		if (args.length == 0) {
			source = "src/main/java/com/spoloborota/teaching/storage/storage_files";
		} else {
			source = args[0];
		}

		RAM ram = new RAM(source);
		LoadStorage.load(ram, source);
		Processor processor = new Processor(ram);
		Console console = new Console(processor);
		console.startListen();
	}

}
