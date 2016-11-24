package com.spoloborota.teaching.storage.model;

import java.util.HashMap;
import java.util.Map;

import com.spoloborota.teaching.storage.type.MapStorage;

/**
 * All data saved to RAM memory first
 * @author Spoloborota
 *
 */
public class RAM {
	public Map<String, MapStorage> map;			// создается map 
	public MapStorage currentStorage = null;	// ссылка currentStorage объекта MapStoraga = null. Текущее хранилище
	
	public RAM() {								// конструктор RAM, инициализация map hashmap'ом  
		map = new HashMap<>();
	}
	
	/**
	 * Show all storages
	 * @return string with all storage names
	 */
	public String display() {					// метод дисплей 
		if (map.keySet().isEmpty()){			
			return "No Storages";				//возвращает строку "No Storages"  если нет ключей map 
		}else{
		return map.keySet().toString();			// возвращает имена всех существующих ключей map преобразуя их к строке
		}
		
	}
	
	/**
	 * Create new storage
	 * @param name - name of the creating storage
	 * @return "true" if all is Ok and "false" if storage with specified name already exists
	 */
	public boolean create(String name) {		// создание ключа map с названием name 
		if (map.containsKey(name)) {			// проверка есть ли такой ключ 
			return false;						// если есть false
		} else {
			map.put(name, new MapStorage(name));	// если нет, то добавляем ключ name, а значение создание hashmap <string, string>
			return true;							// добавили и true тогда
		}
	}
	
	/**
	 * Delete existing storage by name
	 * @param name
	 */
	public void delete(String name) {			// метод delete принимает имя и не возвращает значение  
		MapStorage deleted = map.remove(name);	// создаем ссылку MapStorage deleted = удаляем ключ по name. 
												//Remove вернет значение предыдущего ключа или null если нет ключа по имени name  
		if (deleted.equals(currentStorage)) {	// проверка 
			currentStorage = null;
		}
	}
	
	/**
	 * Select existing storage by name to operate with it
	 * @param name
	 * @return - "true" if storage with such name exist and "false" otherwise
	 */
	public boolean use(String name) {			
		MapStorage mapStorage = map.get(name); // получаем значение, на которое указывает ключ или null 
		if (mapStorage != null) {
			currentStorage = mapStorage;		// текщее значение = значению map (hashmap)
			return true;
		} else {
			return false;
		}
	}
	
	public boolean list(String name){
		MapStorage mapStorage = map.get(name);
		if (currentStorage == mapStorage & currentStorage != null){
			return currentStorage.get(name);
		}else {
			return false;
		}
	}
	
	/**
	 * Add data to storage
	 * @param data
	 * @return - "true" if all is Ok and "false" if there is no selected storage 
	 */
	public boolean add(String[] data) {  // add принимает массив строк
		if (currentStorage != null) {	 // если текущее состояние выбрано  
			return currentStorage.add(data); // то в значение map добавляем инфу 
		} else {
			return false;
		}
	}
}
