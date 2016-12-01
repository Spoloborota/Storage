package com.spoloborota.teaching.storage.file_save;

import java.io.File;

public class FileSave {

	private static int CATALOG_EXIST_1_NO_EXIST_0 = 0;
	public static String path;

	public static void setCATALOG_EXIST_NO_EXIST(int cATALOG_EXIST) {
		CATALOG_EXIST_1_NO_EXIST_0 = cATALOG_EXIST;
	}

	public static int getCATALOG_EXIST_NO_EXIST() {
		return CATALOG_EXIST_1_NO_EXIST_0;
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
