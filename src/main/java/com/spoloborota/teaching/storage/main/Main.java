package com.spoloborota.teaching.storage.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.filechooser.FileNameExtensionFilter;

import com.spoloborota.teaching.storage.localModel.LocalDatabase;
import com.spoloborota.teaching.storage.model.RAM;
import com.spoloborota.teaching.storage.processor.Processor;
import com.spoloborota.teaching.storage.processor.type.Add;
import com.spoloborota.teaching.storage.view.Console;;

/**
 * Main class
 * @author Spoloborota
 *
 */
public class Main {
	
	public static void main(String[] args) {
		final File FILE = new File(args[0]);
		RAM ram = new RAM();
		LocalDatabase.loadingDatabase(FILE, ram);
		Processor processor = new Processor(ram);
		Console console = new Console(processor);
		console.startListen();
	}
}
