package com.spoloborota.teaching.storage.processor.type;

import java.io.FileWriter;
import java.io.IOException;

import com.spoloborota.teaching.storage.model.RAM;
import com.spoloborota.teaching.storage.processor.Processor;
import com.spoloborota.teaching.storage.view.Console;
import com.spoloborota.teaching.storage.type.*;
//import com.spoloborota.teaching.storage.view.*;

public class Save {
	
		
	public static String process(RAM ram) {	
		String isSaved = ram.Save();
		
		Processor p = new Processor(ram);
		Console c = new Console(p);
		
		
		
		try(FileWriter writer = new FileWriter(c.path + "\\" + ram.getName() + ".storage" , false))
	    {
	       // запись всей строки
	        writer.write(isSaved);
	        writer.flush();
	    }
	    catch(IOException ex){
	         
	        System.out.println(ex.getMessage());
	    } 
		return isSaved;
		
	}

	
}
