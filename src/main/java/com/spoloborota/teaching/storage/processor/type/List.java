package com.spoloborota.teaching.storage.processor.type;

import java.util.ArrayList;
import com.spoloborota.teaching.storage.model.RAM;

public class List {
	public static String process(RAM ram) {

		ArrayList<String> isData = ram.list();
		String[] strArr = new String[isData.size()];
		isData.toArray(strArr);
		StringBuilder sb = new StringBuilder();
		for (int i = isData.size()-1; i >=0; i--){
			sb.append("[").append(strArr[i]).append("]").append(" ");
		}
		return sb.toString();

	}
}
