package com.spoloborota.teaching.storage.processor.type;

import java.util.Iterator;
import java.util.Map.Entry;

import com.spoloborota.teaching.storage.model.RAM;

public class ListPrint{
	public static String process(RAM ram){
		if ( ram.listDisplay() != null)
			return ram.currentStorage.name + "\n" + ram.listDisplay();
		else
			return "Not selected storage";
	}
}
