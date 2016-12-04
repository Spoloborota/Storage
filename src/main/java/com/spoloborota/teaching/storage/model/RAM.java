package com.spoloborota.teaching.storage.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.spoloborota.teaching.storage.type.MapStorage;
import com.spoloborota.teaching.storage.reader.SingletonReader;

/**
 * All data saved to RAM memory first
 * @author Spoloborota
 *
 */
public class RAM {
	public Map<String, MapStorage> map;
	public MapStorage currentStorage = null;
	File file;
	public SingletonReader rdr = SingletonReader.getInstance(); 
	
	public RAM() {
		map = new HashMap<>();
	}
	
	/**
	 * Show all storages
	 * @return string with all storage names
	 */
	public String display() {
		return map.keySet().toString();
	}
	
	/**
	 * Create new storage
	 * @param name - name of the creating storage
	 * @return "true" if all is Ok and "false" if storage with specified name already exists
	 */
	public boolean create(String name) {
		if (map.containsKey(name)) {
			return false;
		} else {
			map.put(name, new MapStorage(name));
			return true;
		}
	}
	
	/**
	 * Delete existing storage by name
	 * @param name
	 */
	public void delete(String name) {
		MapStorage deleted = map.remove(name);
		if (deleted.equals(currentStorage)) {
			currentStorage = null;
		}
	}
	
	/**
	 * Select existing storage by name to operate with it
	 * @param name
	 * @return - "true" if storage with such name exist and "false" otherwise
	 */
	public boolean use(String name) {
		MapStorage mapStorage = map.get(name);
		if (mapStorage != null) {
			currentStorage = mapStorage;
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Add data to storage
	 * @param data
	 * @return - "true" if all is Ok and "false" if there is no selected storage 
	 */
	public boolean add(String[] data) {
		if (currentStorage != null) {
			return currentStorage.add(data);
		} else {
			return false;
		}
	}
	
	// Отображаем данные хранилища
	public String list(){
		if (currentStorage != null){
		return currentStorage.hashMap.entrySet().toString();
		} else {
			return "Вы не выбрали хранилище, пожалуйста создайте его командой create ИМЯ_ХРАНИЛИЩА, или воспользуйтесь существующим, команда use ИМЯ_ХРАНИЛИЩА";
		}
	}
	
	//Запись перед выходом предлагается только если были изменения или в стартовом каталоге
	// были файлы с раширением storage. Используется SingeltonReader. Всё по феншую :)
	public String shutdown(){
		String choice = "";
		if(currentStorage != null){
			while(true){
				System.out.println("Вы хотите сохранить текущие изменения? y//n");
				try {
					String saveFlag = rdr.readLine();
					if (saveFlag.equals("y")){
						choice = "y";
						break;
					} else if (saveFlag.equals("n")){
						break;
					} else
						System.out.println("Введите y для сохранения, или n для отказа");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}	
		}
		return choice;
	}
	
	// Запись данных в хранилище.
	public String save(String dirPath){
		if(currentStorage != null && !dirPath.isEmpty()){
			String fileName = "";
			Set<Map.Entry<String, MapStorage>> entrySet = map.entrySet();
			for (Map.Entry<String, MapStorage> pair : entrySet) {
			    if (currentStorage.equals(pair.getValue())) {
			    	fileName = pair.getKey() + ".storage";
			    }
			}
			file = new File(dirPath, fileName);
			try {
				if (!file.exists())
					file.createNewFile();
				FileWriter writer = new FileWriter(file, false);
				for(Map.Entry<String, String> pair : currentStorage.hashMap.entrySet()){
					writer.write(pair.getKey() + "\n");
					writer.write(pair.getValue() + "\n");
				}
				writer.flush();
			} catch (IOException e) {
				System.out.println("Ошибка при создании файла");
				e.printStackTrace();
			}
			return "Хранилище успешно сохранено " + file;
		}else 
			return "Отсутствуют изменения для сохранения";
	}
}
