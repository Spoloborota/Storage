package com.spoloborota.teaching.storage.file_save;

import java.io.File;

public class FileSave {

	private static int catalog_exist_1_no_exist_0 = 0;
	public static String path;

	public static void setCATALOG_EXIST_NO_EXIST(int cATALOG_EXIST) {
		catalog_exist_1_no_exist_0 = cATALOG_EXIST;
	}

	public static int getCATALOG_EXIST_NO_EXIST() {
		return catalog_exist_1_no_exist_0;
	}

	public static void show_console() {
		System.out.println("Введите существующий католог куда сохранять storage : ");
		System.out.println(
				"Пример : " + "C://Users/Артур/workspace/Storage/src/main/java/com/spoloborota/teaching/storage");
	}

	public static void catalog(String commandString1) {

		path = commandString1;

		if (FileSave.getCATALOG_EXIST_NO_EXIST() == 0) {
			FileSave file = new FileSave();
			File catalog = new File(path);

			if (catalog.exists()) {
				file.setCATALOG_EXIST_NO_EXIST(1);
				System.out.println("Выбран " + path + " каталог");
			} else {
				System.out.println("Каталога не существует");
			}

		}
	}
}
