package com.spoloborota.teaching.storage.processor;

import com.spoloborota.teaching.storage.commands.Commands;
import com.spoloborota.teaching.storage.model.RAM;
import com.spoloborota.teaching.storage.processor.type.Add;
import com.spoloborota.teaching.storage.processor.type.Create;
import com.spoloborota.teaching.storage.processor.type.Display;
import com.spoloborota.teaching.storage.processor.type.List;
import com.spoloborota.teaching.storage.processor.type.Save;
import com.spoloborota.teaching.storage.processor.type.Use;
import com.spoloborota.teaching.storage.type.MapStorage;
import com.spoloborota.teaching.storage.view.Console;

/**
 * process commands
 * @author Spoloborota
 *
 */
public class Processor {
	public RAM ram;
	
	public Processor(RAM ram) {
		this.ram = ram;
	}
	public String process(String commandString) {        // метод принимает  команды
		String[] commandWords = commandString.trim().split("\\s+"); // commandString делаем массив строк
		
		
		
		if (commandWords.length != 0) {
			for (String s : commandWords) {   // печатаем весь массив если он имеет != 0
				System.out.println(s);
			}
			
			String result = "";              // создаем пустую строку результат 
			
			
			switch (commandWords[0]) {		// т.к. первое слово[0] команда, по ней и и проверяем совпадения
			case Commands.DISPLAY:			// если display из класса Commands 
				result = Display.process(ram); // то вызываем метод process класса Display, передавая туда ram 
				break;
		
			case Commands.USE:
				if (commandWords.length > 1) {  // если сюда попали, значит команду use отлавили и проверяем есть ли ещё что после
					result = Use.process(ram, commandWords); // если есть то вызывем process класса Use передавая ссылку ram и массив введеных слов 
				} else {
					result = "Storage name does not specified"; // это если написали только use
				}
				break;
				
			case Commands.LIST:
				if (commandWords.length > 1){
					result = List.process(ram, commandWords);
					
				}else {
					result = "Storage name does not specified";
				}
				break;
				
			case Commands.SAVE:
				if (commandWords.length > 1){
					result = Save.process(ram, commandWords);
					
				}else {
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
				if (commandWords.length > 2) {                  // здесь нужно задать ключ значение которые засунем
					result = Add.process(ram, commandWords);					
				} else {
					result = "Data for storage does not specified correctly";
				}
				break;
				
			case Commands.SHUTDOWN:
				System.out.println("Сохранить текущее хранилище ? y/n");
				break;
				
				// доделать выход через y/n
				
			case Commands.Y:
				ram.save(RAM.getCurrentStorage().name);
				System.out.println("Good bye!");
				System.exit(0);
				break;
				
			case Commands.N:	
				System.out.println("Good bye!");
				System.exit(0);						// для выхода из RunTime 
				break;
				
			case Commands.EXIT:	
				System.out.println("Good bye!");
				System.exit(0);						
				break;
				
			default:
				System.out.println("Введите одну из команд " + Commands.EXIT + " " + Commands.CREATE + " " + Commands.USE + " " + Commands.LIST + " " + Commands.ADD + " " + Commands.DISPLAY);
				
			}
			return result;
		} else {
			return "You do not entered any comand";
		}
	}

}
