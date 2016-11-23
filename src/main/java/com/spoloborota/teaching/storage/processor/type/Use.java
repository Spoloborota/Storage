package com.spoloborota.teaching.storage.processor.type;

import com.spoloborota.teaching.storage.model.RAM;

public class Use {
    public static String process(RAM ram, String[] commandWords) {
        boolean isSelected = ram.use(commandWords[1]);
        if (isSelected) {
            return "Selected storage: " + commandWords[1];
        } else {
            return "Storage with name " + commandWords[1] + " does not exist";
        }
    }
}
