package com.spoloborota.teaching.storage.fileloader;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.spoloborota.teaching.storage.model.RAM;
import com.spoloborota.teaching.storage.type.MapStorage;

public class FileLoader {
	
    public static String findFiles(RAM ram, String directoryPath, String expansion) {
    	
    	// ��������� ������, ���� � ������� ������������ ����� � ���������� storage, ����������
    	// �� ��������. ��� �������� �������� �������� ������� ����-��������, ���� ��������
    	// �����������, ���� ��������� �� �����.
    	String result = "";
        File file = new File(directoryPath);
        File[] listFiles = file.listFiles(new FileExtensionsFilter(expansion));
        if(listFiles.length == 0){
            return result = directoryPath + " �� �������� ������ � ����������� " + expansion;
        }else{
            for(File f : listFiles){
            	int i = f.getName().indexOf(".");
            	String fileName = f.getName().substring(0, i);
            	try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(f), StandardCharsets.UTF_8))){
            		ram.create(fileName);
            		ram.use(fileName);
            		List<String> list = new ArrayList<String>();
            		String line;
                    while ((line = reader.readLine()) != null) {
                    	list.add(line);
                    }
                    String[] arr = list.toArray(new String[list.size()]);
                    if (arr.length % 2 == 0){
                    	for(int y = 0; y < arr.length; y += 2){
                        	ram.add(new String[]{arr[y], arr[y + 1]});
                        } System.out.println("���������: " + directoryPath + File.separator + f.getName() + " ��������� � ������");
                    } else {
                    	System.out.println("�������� ���������: " + directoryPath + File.separator + f.getName() + " �� �������, ������ �����������");
                    	ram.delete(fileName);
                    }
                } catch (IOException e) {
                	e.printStackTrace();
                }
            } ram.currentStorage = null;
        } return "�������� ���������, �������� ����� ��������� ��� �������� ������������ �������� use ���_���������";
    }
 
    public static class FileExtensionsFilter implements FilenameFilter{
        private String expansion;
        public FileExtensionsFilter(String expansion){
            this.expansion = expansion;
        }
        @Override
        public boolean accept(File isdir, String name) {
            return name.toLowerCase().endsWith(expansion);
        }
    }
 
}
