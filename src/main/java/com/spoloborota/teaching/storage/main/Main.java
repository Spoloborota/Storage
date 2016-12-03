package com.spoloborota.teaching.storage.main;

import com.spoloborota.teaching.storage.model.RAM;
import com.spoloborota.teaching.storage.processor.Processor;
import com.spoloborota.teaching.storage.view.Console;

import java.io.File;

/**
 * Main class
 *
 * @author Spoloborota
 */
public class Main {

    public static void main(String[] args) {

        File file = new File(args[0]);
        if (file.isDirectory()) {
            RAM ram = new RAM();
            Processor processor = new Processor(ram);
            Console console = new Console(processor);
            processor.setUp(file);
            console.startListen();
        } else {
            throw new IllegalArgumentException("Illegal Argument");
        }
    }
}
