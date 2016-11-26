package com.spoloborota.teaching.storage.processor.type;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Shutdown {
	private boolean shutdownFlag;	
	private boolean shutdownSaveFlag;	
	
	public Shutdown(){
		shutdownFlag = false;
		shutdownSaveFlag = false;
	}
	
	public boolean getShutdownFlag(){
		return shutdownFlag;
	}
	
	public void setShutdownFlag(boolean shutdownFlag){
		this.shutdownFlag = shutdownFlag;
	}
	
	public void setShutdownSaveFlag(boolean shutdownSaveFlag){
		this.shutdownSaveFlag = shutdownSaveFlag;
	}
	
	public boolean getShutdownSaveFlag(){
		return shutdownSaveFlag;
	}
	
	public String getSaveYesNoMessage(){
		return "Sohranit' tekushee hranilishe? y/n";
	}
		
	
	public void exit(){
		if (shutdownFlag){
			System.out.println("Good bye!");
			System.exit(0);
		}
	}
}
