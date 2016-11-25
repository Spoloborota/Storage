package com.spoloborota.teaching.storage.processor.type;

import com.spoloborota.teaching.storage.model.RAM;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.BufferPoolMXBean;

public class Shutdown {
    public static void process(RAM ram) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String answer;
        if (ram.currentStorage!=null) {
            while (true) {
                try {
                    System.out.println("Save current storage? (y/n)");
                    answer = reader.readLine();

                    if (answer.equals("y")) {
                        Save.process(ram);
                        System.out.println("Storage saved, good bye!");
                        System.exit(0);
                    } else if (answer.equals("n")) {
                        System.out.println("You choosed no, good bye!");
                        System.exit(0);
                    } else {
                        System.out.println("wrong command");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        System.out.println("No storages to save, good bye!");
        System.exit(0);
    }
}


