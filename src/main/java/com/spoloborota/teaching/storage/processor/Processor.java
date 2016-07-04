package com.spoloborota.teaching.storage.processor;

import java.io.IOException;

import com.spoloborota.teaching.storage.commands.Commands;
import com.spoloborota.teaching.storage.model.RAM;
import com.spoloborota.teaching.storage.model.ROM;
import com.spoloborota.teaching.storage.processor.type.Add;
import com.spoloborota.teaching.storage.processor.type.Create;
import com.spoloborota.teaching.storage.processor.type.Display;
import com.spoloborota.teaching.storage.processor.type.List;
import com.spoloborota.teaching.storage.processor.type.Save;
import com.spoloborota.teaching.storage.processor.type.Use;
import com.spoloborota.teaching.storage.view.Console;

/**
 * process commands
 * @author Spoloborota
 *
 */
public class Processor {
	public RAM ram;
	public ROM rom;
	boolean shutdown = false;
	
	public Processor(RAM ram, ROM rom) {
		this.ram = ram;
		this.rom = rom;
	}
	public String process(String commandString) throws IOException {
		String[] commandWords = commandString.trim().split("\\s+");
		if (commandWords.length != 0) {
			for (String s : commandWords) {
				System.out.println(s);
			}
			
			String result = "";
			switch (commandWords[0]) {
			case Commands.DISPLAY:
				result = Display.process(ram);
				break;
		
			case Commands.USE:
				if (commandWords.length > 1) {
					result = Use.process(ram, commandWords);
				} else {
					result = "Storage name does not specified";
				}
				break;
				
			case Commands.CREATE:
				if (commandWords.length > 1) {
					result = Create.process(ram, commandWords);
				} else {
					result = "Storage name does not specified";
				}
				break;
				
			case Commands.ADD:
				if (commandWords.length > 2) {
					result = Add.process(ram, commandWords);					
				} else {
					result = "Data for storage does not specified correctly";
				}
				break;
				
			case Commands.LIST:
				result = List.process(ram);	
				break;
				
			case Commands.SAVE:
				result = Save.process(ram, rom);
				break;
			
				
			case Commands.SHUTDOWN:
				
				System.out.println("Сохранить текущее хранилище?(y - да,n - нет)");
				shutdown = true;
				break;
			
			case Commands.YES:
				if(shutdown){
					Save.process(ram, rom);
					System.out.println("Current storage saved");
					System.out.println("Good bye!");
					System.exit(0);
				}
				break;
				
			case Commands.NO:
				if(shutdown){
					System.out.println("Current storage is not saved");
					System.out.println("Good bye!");
					System.exit(0);
				}
				break;
//				System.out.println("Good bye!");
//				System.exit(0);
			}
			return result;
		} else {
			return "You do not entered any comand";
		}
	}

}
