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
		RAM ram = new RAM();                         // RAM для работы с памятью и методов работы с данными , MapStorage создание хранилища 
		Processor processor = new Processor(ram);	 // получает ссылку на хранилище и вызывает нужные команды, получая ответ
		Console console = new Console(processor);	 // для работы через консоль и вывод информации 
		console.startListen();						 // слушатель вечный 
	}

}
