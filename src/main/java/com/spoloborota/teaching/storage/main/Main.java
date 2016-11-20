package com.spoloborota.teaching.storage.main;

import com.spoloborota.teaching.storage.model.RAM;
import com.spoloborota.teaching.storage.processor.Processor;
import com.spoloborota.teaching.storage.view.Console;

/*
 TO DO
 Задача по доработке проекта Storage:
 Реализовать вывод на экран всех элементов выбранного ранее (с использованием команды USE) хранилища. Использовать для этого команду "list".
 Выполнить задачу в отдельной ветке и прислать мне pull-request. Как делать pull-requst и что это, вообще, такое разобраться самостоятельно.
 Форкать отсюда:
 https://github.com/Spoloborota/Storage
 Выполнить до 28го ноября.
 */
public class Main {

	public static void main(String[] args) {
		RAM ram = new RAM();
		Processor processor = new Processor(ram);
		Console console = new Console(processor);
        System.out.println("At your service, master.");
        console.startListen();
	}

}
