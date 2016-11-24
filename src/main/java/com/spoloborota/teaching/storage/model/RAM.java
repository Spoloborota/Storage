package com.spoloborota.teaching.storage.model;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.spoloborota.teaching.storage.type.FileLoader;
import com.spoloborota.teaching.storage.type.FileSaver;
import com.spoloborota.teaching.storage.type.MapStorage;

/**
 * All data saved to RAM memory first
 * @author Spoloborota
 *
 */
public class RAM {
	String path;
	String [] filesInDirectory;
	String [] fileBuffer;
	
	public Map<String, MapStorage> map;
	public MapStorage currentStorage = null;
	public FileSaver fileSaver;
	public FileLoader fileLoader;	

	public RAM() {
		map = new HashMap<>();
	}

	/**
	 * Konstructor sozdaet hranilishe hranilish.
	 * Proveryaet est' li faili (*.storage) v kataloge.
	 * Sozdaet i zapolnyaet xranilisha iz failov (*.storage).
	 */
	public RAM(String[] path) {
		
		map = new HashMap<>();
		this.path = path[0];

		fileLoader = new FileLoader(this.path);
		int loadFlag = fileLoader.searchAndFilter();
				
		switch(loadFlag){
		default:
		case (0):
			filesInDirectory = fileLoader.getRez();
			fillStorages();
			break;
		case (1):
			System.out.println("Ukazan put' na fail.");
			System.out.println("Good bye!");
			System.exit(0);
			break;
		case (2):
			System.out.println("Ukazannaya directoriya ne sushestvuet.");
			System.out.println("Good bye!");
			System.exit(0);
			break;
		}
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

	/**
	 * Zaprashivaet dannie is MapStorage v vide podgotovlennoi stroki.
	 *  
	 */	
	private String prepareDataToSave(){
		return currentStorage.prepareDataToSave();		
	}
	
	/**
	 * Sohranyaet dannie is bufera: (buffer) v fail: <tek storage>.storage.
	 * Proveryaet vibrano li tekushee hranilishe. 
	 * 
	 */	
	public String save(){
		String buffer = "";
		String outString = "";
		int savedFlag = 0;
		if (currentStorage != null) {
			buffer = prepareDataToSave();
			fileSaver = new FileSaver(buffer, path, currentStorage.name);
			savedFlag = fileSaver.save();
			switch(savedFlag){
			default:
			case (0):
				outString = "Dannie iz tekushego hranilisha " + currentStorage.name 
				+ " sohraneni v fail " + path + "\\" 
				+ currentStorage.name +  ".storage";
			break;
			case (1):
				outString = "Ukazannaya directoriya ne sushestvuet.";						
			break;
			case (2):
				outString = "Ukazannii put' ne yavlyaetsya direktoriei.";
			break;
			}
			return outString;
		} 
		else {
			return null;
		}
	}
	
	/**
	 * Meryaet dlinu faila chitaemogo hranilisha(dlya sozdaniya ).
	 * Vizivaetsya is:   public void fillStorages()
	 * Chitaet otformatirovannie dannie v pole: (fileBuffer). 
	 */	
	private void load(String pathToFile){
		long lng = 0;
		File f1 = new File(pathToFile + ".storage");
		lng = f1.length();

		fileBuffer = fileLoader.load(pathToFile + ".storage", lng);
	}
	
	/**
	 * Vizivaetsya is Konstructora: public RAM(String[] path). 
	 * Zapolnuaet hranilisha dannimi iz failov.
	 */
	public void fillStorages(){
		int indexOfDot=0;
		String temp="";

		for (int i = 0; i< filesInDirectory.length; i++){
			indexOfDot = filesInDirectory[i].indexOf(".");
			if (indexOfDot != -1){
				temp = filesInDirectory[i].substring(0, indexOfDot);
				filesInDirectory[i] = temp;
				create(temp);
				load(this.path + "\\" + temp);
				use(temp);
				int mytemp = fileBuffer.length;
				for (int j = 0; j< mytemp; j+=2){
					String [] toPut = new String[2];
					toPut[0] = fileBuffer[j];
					toPut[1] = fileBuffer[j+1];				
					add(toPut);
				}
			}
		}
		fileLoader = null;// bolshe ne nuzhen
		currentStorage = null;//initial value (polzovatel ne vvodil komandu "use")
	}
}
