package com.spoloborota.teaching.storage.processor.type;

import java.util.ArrayList;
import java.io.*;
import com.spoloborota.teaching.storage.model.RAM;
import com.spoloborota.teaching.storage.processor.Processor;
import com.spoloborota.teaching.storage.view.Console;

public class Save {
	public static String process(RAM ram) {
		Processor proc = new Processor(ram);
		Console con = new Console(proc);
		String adr = con.get_url();
		ArrayList<String> str = ram.save();
		String name = ram.getName();
		Writer writer = null;
		try {
			writer = new FileWriter(adr + "\\" +name+".storage");
			for(int i = str.size()-1; i >= 0; i--)
			{
				writer.write(str.get(i)+"\r\n");
				writer.flush();
			}
		} catch (Exception e) {
			System.out.println("Select the directory to which you want to save the data!");
			con.startListen();
		}

		return name +".storage saved in " + adr;
	}
	//		так себе код (пусть пока что лежит здесь)
	//		String[] strArr = new String[str.size()];
	//		str.toArray(strArr);
	//		try
	//        {
	//            OutputStream f = new FileOutputStream(adr + "\\File.txt", true);
	//            OutputStreamWriter writer = new OutputStreamWriter(f);
	//            BufferedWriter out = new BufferedWriter(writer);
	//            for(int i = str.size()-1; i >= 0; i--)
	//            {
	//                out.write(str.get(i)+" \n ");
	//                out.flush();
	//            }
	//        }
	//        catch(IOException ex)
	//        {
	//            System.err.println(ex);
	//        }

	//	            можно использовать сепаратор
	//	            for (String line : str) {
	//	                writer.write(line);
	//
	//	                writer.write(System.getProperty("line.separator"));
	//	            }
	//	            writer.flush();
}
