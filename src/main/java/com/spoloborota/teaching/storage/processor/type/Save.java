package com.spoloborota.teaching.storage.processor.type;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.spoloborota.teaching.storage.model.RAM;
import com.spoloborota.teaching.storage.processor.Processor;
import com.spoloborota.teaching.storage.view.Console;
import com.spoloborota.teaching.storage.type.*;
//import com.spoloborota.teaching.storage.view.*;


public class Save {


	public static String process(RAM ram) {	
		ArrayList<String> isSaved = ram.Save();

		Processor p = new Processor(ram);
		Console c = new Console(p);
		String nameCs = ram.getName();




		try(FileWriter writer = new FileWriter(c.path + "\\" + ram.getName() + ".storage" , false))
		{


			for(int i = 0; i <= isSaved.size()-1; i++){
				writer.write(isSaved.get(i) + "\r\n");
				writer.flush();
			}








			//			String[] s = isSaved.split("\\s+");
			//			for(String o : s){
			//				 writer.write(o + "\n");
			//			}
			//			writer.close();
			//			


		}
		catch(IOException ex){

			System.out.println(ex.getMessage());
		} 
		return "Saved seccufully";

	}


}
