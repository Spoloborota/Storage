package com.spoloborota.teaching.storage.processor;

import java.io.File;

import com.spoloborota.teaching.storage.commands.Commands;
import com.spoloborota.teaching.storage.model.RAM;
import com.spoloborota.teaching.storage.processor.type.Add;
import com.spoloborota.teaching.storage.processor.type.Create;
import com.spoloborota.teaching.storage.processor.type.Display;
import com.spoloborota.teaching.storage.processor.type.List;
import com.spoloborota.teaching.storage.processor.type.Save;
import com.spoloborota.teaching.storage.processor.type.Use;
import com.spoloborota.teaching.storage.processor.type.Shutdown;
import com.spoloborota.teaching.storage.fileloader.FileLoader;

/**
 * process commands
 * @author Spoloborota
 *
 */
public class Processor {
	public RAM ram;
	String directoryPath = "";
	String expansion = ".storage";
	int counter = 0;
	
	public Processor(RAM ram) {
		this.ram = ram;
	}
	public String process(String commandString) {
		// Заставляем пользователя указать каталог или создаём его за него после 5 попыток
		// Данный блок будет выполняться только пока не задан каталог.
		// После того как каталог задан происходит разовый вызов загрузчика файлов
		if (directoryPath.isEmpty()){
			File isdir = new File(commandString);
			if (counter >= 5){
				File newFolder = new File("IamVeryStubbornPerson").getAbsoluteFile();
				if (!newFolder.exists())
					newFolder.mkdir();
				System.out.println("Вы нас убедили, вы действительно очень настырный человек :) В качестве каталога для хранилищ выбран " + newFolder);
				directoryPath = newFolder.getAbsolutePath();
				System.out.println(FileLoader.findFiles(ram, directoryPath, expansion));
			} else if (!isdir.isDirectory()){
				System.out.println("Указанного каталога не существует, пожалуйста попробуйте ещё раз (перезапускать программу не обязательно) :)");
				counter++;
			}else{
				directoryPath = commandString;
				System.out.println(FileLoader.findFiles(ram, directoryPath, expansion));
			}
		}
		
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
				result = Save.process(ram, directoryPath);
				break;
				
			case Commands.SHUTDOWN:
				result = Shutdown.process(ram);
				if (result.equals("y")){
					System.out.println(Save.process(ram, directoryPath));
				}
				System.out.println("Good bye!");
				System.exit(0);
			}
			return result;
		} else {
			return "You do not entered any comand";
		}
	}

}
