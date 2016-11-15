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
	public Map<String, MapStorage> map;
	public MapStorage currentStorage = null;
	
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
	public boolean delete(String name) {
		if (map.containsKey(name)) {
			map.remove(name);
			if (currentStorage != null){
				if (currentStorage.name.equals(name)){
					currentStorage = null;
				}
			}
		return true;
		} else {
			return false;
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
	public boolean remove(String[] data) {
		if (currentStorage != null) {
			return currentStorage.remove(data);
		} else {
			return false;
		}
	}
	public boolean show(String[] data) {
		if (currentStorage != null) {
			return currentStorage.show(data);
			}
		else {
			return false;
		}
	}
}
