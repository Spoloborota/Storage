package com.spoloborota.teaching.storage.main;

import com.spoloborota.teaching.storage.model.RAM;
import com.spoloborota.teaching.storage.processor.Processor;
import com.spoloborota.teaching.storage.view.Console;;

/**
 * Main class
 * @author Spoloborota
 *
 */
public class Main {

	public static void main(String[] args) {
		if (args.length == 0){
			System.out.println("Vvedite put' k directorii dlya hranilish!!!");
			System.exit(0);
		}
		
		RAM ram = new RAM(args);
		Processor processor = new Processor(ram);
		Console console = new Console(processor);
		console.startListen();
	}

}
