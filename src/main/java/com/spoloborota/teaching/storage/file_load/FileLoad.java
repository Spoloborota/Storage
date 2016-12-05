package com.spoloborota.teaching.storage.file_load;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.spoloborota.teaching.storage.processor.Processor;

public class FileLoad {

	private static final String EXTENSION = ".storage";

	public static void load(String name) {

		try {

			final String DIR_NAME = name;

			File dir = new File(DIR_NAME);

			String[] filenames = dir.list(new ExtensionFilter(EXTENSION));

			if (filenames.length == 0) {
				System.out.println("В каталоге не найдено " + EXTENSION + " для загрузки, создайте свой :) ");
			} else {
				System.out.println("Загружены файлы с расширением " + EXTENSION + ":");

				for (String filename : filenames) {

					String[] nameWords = filename.trim().split("\\.");
					System.out.println(nameWords[0]);

					System.out.println(filename);

					try {
						BufferedReader reader = new BufferedReader(new FileReader(DIR_NAME + "\\" + filename));
						try {
							List<String> lines = new ArrayList<String>();
							int i = 0;
							String c;
							while ((c = reader.readLine()) != null) {

								lines.add(c);

								if (i == 1) {
									Processor.loadFile(nameWords[0], lines);
									lines.clear();
								}
								i++;
								if (i == 2) {
									i = 0;
								}
							}
						} finally {
							reader.close();
						}

					} catch (IOException ex) {
						System.out.println(ex.getMessage());

					}

				}

			}
		} catch (NullPointerException e) {

		}

	}

	private static class ExtensionFilter implements FilenameFilter {

		private final String extension;

		public ExtensionFilter(String ext) {
			extension = ext;
		}

		@Override
		public boolean accept(File dir, String name) {
			boolean rez = name.endsWith(extension);
			return rez;
		}
	}
}
