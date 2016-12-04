package com.spoloborota.teaching.storage.view;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.spoloborota.teaching.storage.processor.Processor;
import com.spoloborota.teaching.storage.reader.SingletonReader;

/**
 * Commands received via system console
 * @author Spoloborota
 *
 */
public class Console {
	public static String url = "";
	public Processor processor;
	public SingletonReader rdr = SingletonReader.getInstance(); 

	public Console(Processor processor) {
		this.processor = processor;
	}

	public void startListen() {
		while(true) {
			try {
				String commandString = rdr.readLine();
				boolean flag;
				flag = commandString.contains("\\");
				if (flag == true){
					url = commandString;
					File f1 = new File(url);
					if (f1.isDirectory()) {
						System.out.println("Selected directory: " + url);
					} else {
						System.out.println("Directory does not exist. You must specify a directory.");
					}
					check(url);
					for(int i = 0; i < check(url).length; i++){
						String c = "create " + check(url)[i];
						String result = processor.process(c);
						System.out.println("File "+ check(url)[i] + ".storage exists " + "in directory: " + url);
						String u = "use " + check(url)[i];
						result = processor.process(u);
						for(int j = 0; j < readfile(url+"\\"+check(url)[i]+".storage").size(); j++){
							String ad = "add " + readfile(url+"\\"+check(url)[i]+".storage").get(j) + " " + readfile(url+"\\"+check(url)[i]+".storage").get(j+1);
							j++;
							result = processor.process(ad);
						}
						System.out.println("Data downloaded!");
					}
				} else { 
					String result = processor.process(commandString);
					System.out.println(result);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	//���������� �����, ���������� �������������
	public String get_url()
	{
		return url;
	}
	//������ ������ � �������� �� �����. �������,���������� � JAVA 7 � ����
	private List<String> readfile(String url) throws IOException {
		List<String> lines = Files.readAllLines(Paths.get(url), StandardCharsets.UTF_8);
		return lines;
	}
	// ��������� ����� � ����������� storage � ��������� �����
	private String[] check(String url2) {
		File folder = new File(url2);

		String[] files = folder.list(new FilenameFilter() {

			@Override public boolean accept(File folder, String name) {
				return name.endsWith(".storage");
			}

		});
		String[] files2 = new String[files.length];
		for (int i = 0; i < files.length; i++){
			files2[i] = files[i].substring(0, files[i].length()-8);
		}
		//		for ( String fileName : files ) {
		//			System.out.println("File in directory: " + fileName);
		//		}
		return files2;
	}
}
