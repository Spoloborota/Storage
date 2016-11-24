package com.spoloborota.teaching.storage.processor.type;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Shutdown {

	/**
	 * Pri vihode zaprashivaetsya sohranit' tekushee hranilishe ili net.
	 * 
	 */
		
	public boolean askSaveOrNot() {
		boolean saveOrNot = false;
		String yesNo = "n";
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Sohranit' tekushee hranilishe? y/n");
		try{
			yesNo = reader.readLine();
		}
		catch(IOException e){
		}
		switch (yesNo){
		default:
		case ("n"):
			saveOrNot = false;
		break;
		case ("y"):
			saveOrNot = true;
		break;
		}
		return saveOrNot;
	}
}
