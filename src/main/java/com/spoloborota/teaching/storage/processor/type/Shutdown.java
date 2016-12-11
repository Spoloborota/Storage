package com.spoloborota.teaching.storage.processor.type;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.spoloborota.teaching.storage.commands.Commands;
import com.spoloborota.teaching.storage.model.RAM;

public class Shutdown {
	public static String process(RAM ram) throws IOException {
		char c; 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		System.out.println("If you want to save you storage and exit - press 'y'. If you want exit without saving - press 'n' "); 
		//читать символы 

		switch(c = (char) br.read()){
		case Commands.y:
			String result2 = Save.process(ram);
			System.out.println("Storage succefully saved! Good bye!");
			System.exit(0);
			
			
		case Commands.n:
			System.out.println("Storage does not saved! Good bye!");
			System.exit(0);
			}
		
		
		return null;
	}

}
