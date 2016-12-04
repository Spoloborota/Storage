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
				} else { 
					String result = processor.process(commandString);
					System.out.println(result);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	//возвращает адрес, переданный пользователем
	public String get_url()
	{
		return url;
	}
	//чтение ключей и значений из файла. Функция,работающая в JAVA 7 и выше
	private List<String> readfile(String url) throws IOException {
		List<String> lines = Files.readAllLines(Paths.get(url), StandardCharsets.UTF_8);
		return lines;
	}
	// проверяем файлы с расширением storage в указанной папке
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
