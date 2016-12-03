package com.spoloborota.teaching.storage.processor;

import com.spoloborota.teaching.storage.commands.Commands;
import com.spoloborota.teaching.storage.model.RAM;
import com.spoloborota.teaching.storage.processor.type.*;
import com.spoloborota.teaching.storage.processor.type.Shutdown;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

/**
 * process commands
 *
 * @author Spoloborota
 */
public class Processor {
    public RAM ram;

    public Processor(RAM ram) {
        this.ram = ram;
    }

    private static boolean saveCurrentStorageQuestion = false;

    public void setUp(File file) {
        ram.path = file.getPath();
        //считываем все файлы в директории
        File[] folderEntries = file.listFiles();

        //проверяем расширение файла
        if (folderEntries != null) {
            for (File entry : folderEntries) {
                if (entry.getName().split("\\.")[1].equals("storage")) {
                    // если совпало, вытаскиваем имя файла
                    String storageName = entry.getName().split("\\.")[0];
                    ram.create(storageName);
                    process(storageName + "_is_loaded");
                    // считать из файла данные и закинуть их в мапу.
                    List<String> lines = null;
                    try {
                        lines = Files.readAllLines(entry.toPath(), StandardCharsets.UTF_8);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    ram.use(storageName);
                    if (lines != null) {
                        for (int i = 0; i < lines.size() - 1; i++) {
                            String[] pair = {lines.get(i), lines.get(i + 1)};
                            ram.currentStorage.add(pair);
                            process("settings_added_to_" + storageName);
                        }
                    }
                }

            }
        }
        ram.currentStorage = null;
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

                case Commands.SAVE:
                    result = Save.process(ram);
                    break;

                case Commands.SHUTDOWN:
                    if (ram.currentStorage != null) {
                        //ставим флаг что был задан вопрос, ждем ответ "y" или "n"
                        saveCurrentStorageQuestion = true;
                        result = "Save_current_storage?(y/n)";
                        break;
                    } else {
                        Shutdown.process();
                    }

                case Commands.YES: {
                    if (saveCurrentStorageQuestion) {
                        Save.process(ram);
                        process("Storage_saved");
                        Shutdown.process();
                        break;
                    }
                }
                case Commands.NO: {
                    if (saveCurrentStorageQuestion) {
                        Shutdown.process();
                        break;
                    }
                }
            }
            return result;
        } else {
            return "You do not entered any comand";
        }
    }

}
