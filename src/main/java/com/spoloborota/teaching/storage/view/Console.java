package com.spoloborota.teaching.storage.view;

import java.io.IOException;

import com.spoloborota.teaching.storage.processor.Processor;
import com.spoloborota.teaching.storage.reader.SingletonReader;

/**
 * Commands received via system console
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
			System.out.println("Первой командой укажите путь к существующей директории (Например C:\\java) ... хотя можете указать и несуществующую, всё равно мы заставим Вас сделать так, как это нужно нам, если только вы не будете чеерезчур настырны :)");
		while(true) {
			try {
				String commandString = rdr.readLine();
				String result = processor.process(commandString);
				System.out.println(result);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
