package com.spoloborota.teaching.storage.processor;

import java.io.IOException;

import com.spoloborota.teaching.storage.commands.Commands;
import com.spoloborota.teaching.storage.file_load.AddLoad;
import com.spoloborota.teaching.storage.file_load.CreateLoad;
import com.spoloborota.teaching.storage.file_load.UseLoad;
import com.spoloborota.teaching.storage.model.RAM;
import com.spoloborota.teaching.storage.processor.type.Add;
import com.spoloborota.teaching.storage.processor.type.Create;
import com.spoloborota.teaching.storage.processor.type.Display;
import com.spoloborota.teaching.storage.processor.type.List;
import com.spoloborota.teaching.storage.processor.type.Save;
import com.spoloborota.teaching.storage.processor.type.Use;
import com.spoloborota.teaching.storage.reader.SingletonReader;
import com.spoloborota.teaching.storage.type.MapStorage;
import com.spoloborota.teaching.storage.view.Console;

/**
 * process commands
 * 
 * @author Spoloborota
 *
 */
public class Processor {
	private static RAM ram;

	public Processor(RAM ram) {
		Processor.ram = ram;
	}

	public static void loadFile(String nameWords, java.util.List<String> lines) {

		CreateLoad.process(ram, nameWords);
		UseLoad.process(ram, nameWords);
		AddLoad.process(ram, lines);

	}

	public String process(String commandString) {
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

			case Commands.LIST:
				if (commandWords.length > 1) {
					result = List.process(ram, commandWords);

				} else {
					result = "Storage name does not specified";
				}
				break;

			case Commands.SAVE:
				if (commandWords.length > 1) {
					result = Save.process(ram, commandWords);

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

			case Commands.SHUTDOWN:
				System.out.println("Сохранить текущее хранилище ? y/n");

				String commandWords2 = null;
				try {
					commandWords2 = SingletonReader.getRdr().readLine();
				} catch (IOException e) {

					e.printStackTrace();
				}

				switch (commandWords2) {

				case Commands.Y:
					try {
						ram.save(RAM.getCurrentStorage().name);
					} catch (NullPointerException e) {
						System.out.println("Нет или не выбрано хранилища для сохранения");
					}

					System.out.println("Good bye!");
					System.exit(0);
					break;

				case Commands.N:
					System.out.println("Good bye!");
					System.exit(0);
					break;

				}

			case Commands.EXIT:
				System.out.println("Good bye!");
				System.exit(0);
				break;

			default:
				System.out.println("Введите одну из команд " + Commands.EXIT + " " + Commands.CREATE + " "
						+ Commands.USE + " " + Commands.LIST + " " + Commands.ADD + " " + Commands.DISPLAY);

			}
			return result;
		} else {
			return "You do not entered any comand";
		}
	}

}
