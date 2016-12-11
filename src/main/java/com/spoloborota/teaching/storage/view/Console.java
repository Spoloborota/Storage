package com.spoloborota.teaching.storage.view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.spoloborota.teaching.storage.processor.Processor;
import com.spoloborota.teaching.storage.reader.SingletonReader;
import com.spoloborota.teaching.storage.model.*;
import com.spoloborota.teaching.storage.type.*;


/**
 * Commands received via system console
 * @author Spoloborota
 *
 */
public class Console {
	public Processor processor;
	public SingletonReader rdr = SingletonReader.getInstance(); 
	public static String path = "";

	public Console(Processor processor) {
		this.processor = processor;
	}



	public void startListen() {
		while(true) {
			try {
				String commandString = rdr.readLine();
				if(commandString.contains("\\")){
					path = commandString + "\\";
					//					System.out.println("Your path is " + path);	
					File f = new File(path);
					if(f.isDirectory()){
						System.out.println("Now your directory is" + path);
					}
					else{
						System.out.println("Type correct path of your directory");
					}

					proverka(path);
					for(int i = 0; i<proverka(path).length; i++){
						String c = "create " + proverka(path)[i];
						String r = processor.process(c);
						System.out.println("File " + proverka(path)[i] + " .storage exists " + "in directory: " + path);
						String u = "use " + proverka(path)[i];
						r = processor.process(u);
						for(int j = 0; j < readfile(path+"\\"+proverka(path)[i]+ ".storage").size();j++){
							String d = "add " + readfile(path+"\\"+proverka(path)[i]+".storage").get(j) + " " + readfile(path + "\\" + proverka(path)[i]+".storage").get(j+1);
							j++;
							r = processor.process(d);
						}
						System.out.println("Your data downloaded!");
					}

				}
				else{
					String result = processor.process(commandString);// вот тут если что уберешь
					System.out.println(result);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public String getPath(){
		return path;
	}

	private List<String> readfile(String pp) throws IOException{
		List<String> lines = Files.readAllLines(Paths.get(pp), StandardCharsets.UTF_8);
		return lines;

	}


	public String[] proverka(String url){
		File f1 = new File(url);
		String[] files = f1.list(new FilenameFilter(){

			@Override
			public boolean accept(File f1, String name) {
				return name.endsWith(".storage");
			}


		});
		String[] files2 = new String[files.length];
		for (int i = 0; i < files.length; i++){
			files2[i] = files[i].substring(0, files[i].length()-8);

		}
		return files2;
	}
}
