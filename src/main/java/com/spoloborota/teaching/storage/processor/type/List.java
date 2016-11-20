package com.spoloborota.teaching.storage.processor.type;

import com.spoloborota.teaching.storage.model.RAM;

import java.util.HashMap;

public class List {
    public static String process(RAM ram) {
        return ram.list();
    }
}
