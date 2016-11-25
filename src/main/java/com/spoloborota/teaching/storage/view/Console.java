package com.spoloborota.teaching.storage.view;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

import com.spoloborota.teaching.storage.file_save.FileSave;
import com.spoloborota.teaching.storage.processor.Processor;
import com.spoloborota.teaching.storage.reader.SingletonReader;

/**
 * Commands received via system console
 * 
 * @author Spoloborota
 *
 */
public class Console {
	public Processor processor;
	public SingletonReader rdr = SingletonReader.getInstance();

	public Console(Processor processor) {
		this.processor = processor;
	}

	public void startListen() {

		while (true) {
			try {

				if (FileSave.getCATALOG_EXIST_NO_EXIST() == 0) {
					FileSave.show_console();
				}

				while (FileSave.getCATALOG_EXIST_NO_EXIST() == 0) {
					String commandString1 = rdr.readLine();
					FileSave.catalog(commandString1);
				}
				
				String commandString = rdr.readLine();
				String result = processor.process(commandString);
				System.out.println(result);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
