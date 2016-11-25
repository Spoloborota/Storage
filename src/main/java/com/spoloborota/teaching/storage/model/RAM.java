package com.spoloborota.teaching.storage.model;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.spoloborota.teaching.storage.type.MapStorage;

/**
 * All data saved to RAM memory first
 *
 * @author Spoloborota
 */
public class RAM {
    public Map<String, MapStorage> map;
    public MapStorage currentStorage = null;
    private String path;
    public RAM(File file) {
        path = file.getPath();
        map = new HashMap<>();
        //считываем все файлы в директории
        File[] folderEntries = file.listFiles();

            //проверяем расширение файла
        if (folderEntries != null) {
            for (File entry : folderEntries) {
            if (entry.getName().split("\\.")[1].equals("storage")){
               // если совпало, вытаскиваем имя файла
            String storageName = entry.getName().split("\\.")[0];
                create(storageName);
                System.out.println(storageName + " is loaded");
                // считать из файла данные и закинуть их в мапу.
                List<String> lines = null;
                try {
                    lines = java.nio.file.Files.readAllLines(entry.toPath(), StandardCharsets.UTF_8);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                use(storageName);
                for(int i=0; i<lines.size()-1;i++){
                    String[] pair = {lines.get(i), lines.get(i+1)};
                 currentStorage.add(pair);
                    System.out.println("settings added to " + storageName);
                }
            }

            }
        }
        currentStorage=null;



    }

    /**
     * Show all storages
     *
     * @return string with all storage names
     */
    public String display() {
        return map.keySet().toString();
    }

    /**
     * Create new storage
     *
     * @param name - name of the creating storage
     * @return "true" if all is Ok and "false" if storage with specified name already exists
     */
    public boolean create(String name) {
        if (map.containsKey(name)) {
            return false;
        } else {
            map.put(name, new MapStorage(name));
            return true;
        }
    }

    /**
     * Delete existing storage by name
     *
     * @param name
     */
    public void delete(String name) {
        MapStorage deleted = map.remove(name);
        if (deleted.equals(currentStorage)) {
            currentStorage = null;
        }
    }

    /**
     * Select existing storage by name to operate with it
     *
     * @param name
     * @return - "true" if storage with such name exist and "false" otherwise
     */
    public boolean use(String name) {
        MapStorage mapStorage = map.get(name);
        if (mapStorage != null) {
            currentStorage = mapStorage;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Save selected storage in spetialized directory
     *
     * @return - "true" if storage with such name exist and "false" otherwise
     */
    public boolean save() {

        return path != null && currentStorage != null && currentStorage.save(path);

    }

    /**
     * Add data to storage
     *
     * @param data
     * @return - "true" if all is Ok and "false" if there is no selected storage
     */
    public boolean add(String[] data) {
        return currentStorage != null && currentStorage.add(data);
    }

    private static class Files {
    }
}
