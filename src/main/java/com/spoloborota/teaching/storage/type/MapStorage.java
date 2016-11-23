package com.spoloborota.teaching.storage.type;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class MapStorage {

    public String name;
    public HashMap<String, String> hashMap;

    public MapStorage(String name) {
        this.name = name;
        hashMap = new HashMap<>();
    }

    public boolean add(String[] keyValue) {
        hashMap.put(keyValue[0], keyValue[1]);
        return true;
    }

    public boolean save(String path) {
        //Определяем файл
        try {
            File fileToSave = new File(path, this.name + ".storage");

            //проверяем, что если файл не существует то создаем его
            if (!fileToSave.exists()) {
                fileToSave.createNewFile();
            }

            //PrintWriter обеспечит возможности записи в файл
            PrintWriter out = new PrintWriter(fileToSave);

            try {
                //Записываем текст в файл
                for (Map.Entry<String, String> pair : hashMap.entrySet()) {
                    out.println(pair.getKey());
                    out.println(pair.getValue());
                }
                return true;
            } finally {
                //После чего мы должны закрыть файл
                //Иначе файл не запишется
                out.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
