package com.spoloborota.teaching.storage.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.spoloborota.teaching.storage.type.LoadStorage;
import com.spoloborota.teaching.storage.type.MapStorage;

/**
 * All data saved to RAM memory first
 * @author Spoloborota
 *
 */
public class RAM {
	public Map<String, MapStorage> map;
	public MapStorage currentStorage = null;
	public String source;
	public static File source_f;
	public LoadStorage ld; 
	
	public RAM(String source) {
		map = new HashMap<>();
		this.source = source;
		source_f = new File(source);
		ld = new LoadStorage(source_f);
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
	
	public String list() {
		if (currentStorage != null) {
			return currentStorage.hashMap.entrySet().toString();
		} else {
			return "Please select the Storage";
		}
	}
	
	public boolean save() throws IOException {
		if (currentStorage != null) {
			
			File f = new File(source, currentStorage.name + ".storage");
//	        FileOutputStream fileOutputStream = new FileOutputStream(f);
//	        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
//	        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(f));
	        PrintWriter out = new PrintWriter(f);
					
			for (Entry <String, String> entry: currentStorage.hashMap.entrySet()) { 
				String key = entry.getKey(); 
				String value = entry.getValue(); 
				out.println(key);
				out.println(value);
				
				}
			out.close();
			
			return true;			
			
		}
		return false;
	}
}
