package com.spoloborota.teaching.storage.model;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


import com.spoloborota.teaching.storage.type.*;

public class ROM {
	
	
	private File storageDir;
	public String getStorageDir(){
		return storageDir.getAbsolutePath();
	}
	
	private boolean storageDirContainsStorage = false;
	private ArrayList<File> storageFileList = new ArrayList<>();
	private Map<String, MapStorage> map = new HashMap<String, MapStorage>();
	private RAM ram;
	
	public RAM getRAM(){ 
		return ram;
	}
	
	public ROM(String[] args) throws IOException {
		
		String dir = "";
		for (int i = 0; i < args.length; i++) {
			dir = dir + args[i];
		}
		File storageDir = new File(dir);
		
		if (storageDir.isDirectory()) 
			this.storageDir = storageDir;
		else 
			this.storageDir = new File(".storage");
		
		storageDirCheck(this.storageDir); 
		if (storageDirContainsStorage){
			for(int i = 0; i<storageFileList.size(); i++){
				File file =  storageFileList.get(i);
				
				String name =file.getName().substring(0, file.getName().length()-8);
				
				
				
				BufferedReader input = new BufferedReader(new FileReader(file));
				
				ArrayList<String> keyValue = new ArrayList<>();
				HashMap<String, String> hashMap = new HashMap<>();
				String kv;
				while((kv = input.readLine())!=null){
					keyValue.add(kv);
				}
				
				Iterator<String> it = keyValue.iterator();
				while(it.hasNext()){
					hashMap.put(it.next(), it.next());
				}
				
				MapStorage mapStorage = new MapStorage(name, hashMap);
				
				
				map.put(name, mapStorage);
				
			}
			
			ram = new RAM(map);
		}
		else {
			ram = new RAM();
		}
		
		
	}
	
	public String addToStorageDirCurrentStorage(MapStorage mapStorage) throws IOException{
				
		String name = mapStorage.getName();
		HashMap<String, String> map = mapStorage.getHashMap();
		
		File file = new File(storageDir.getAbsolutePath()+ "\\" + name + ".storage");
		
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		
		Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
		
		while(it.hasNext()){
			
			Map.Entry<String, String> pair = it.next();
			String key = pair.getKey();
			String value = pair.getValue();
			
			writer.write(key+"\n"+value+"\n");
		}
		writer.flush();	
		return "Storage saved";
	}
	
	public void storageDirCheck(File storageDir){
		
		String[] list = storageDir.list();
				
		for (int i = 0; i < list.length; i++) {
			File file = new File(storageDir,list[i]);
			if (!file.isDirectory()){
				if (file.getName().endsWith(".storage")){
					storageFileList.add(file);
				}
			}
		}
		
		if (!storageFileList.isEmpty()){
			storageDirContainsStorage = true;
		}
		
		
	}
	
	
	
	
	
}
