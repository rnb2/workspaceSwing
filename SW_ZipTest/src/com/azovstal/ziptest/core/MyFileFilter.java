package com.azovstal.ziptest.core;

import java.io.File;
import java.util.ArrayList;

import javax.swing.filechooser.FileFilter;

public class MyFileFilter extends FileFilter {
	
	private ArrayList list = new ArrayList();
	private String descrip = "";
	
	
//	Задает расщирения распознаваемые фильтром и добавляет их в список исполь расширений
//	@param newVal - ".txt"	
	public void addExtentions(String newVal){
		if(!newVal.startsWith("."))	newVal = "." + newVal;
		 list.add(newVal.toLowerCase());	
		
	}
	
	
//	Распознаем расширения файлов
//	@PARAM	f файл 
	
	@Override
	public boolean accept(File f) {
		if(f.isDirectory()) return true;
		String string = f.getName().toLowerCase();
		
		//Проверка, имеет ли файл заданное расширение
		for (int i = 0; i < list.size(); i++) {
			if(string.endsWith((String)list.get(i)))
				return true;
		}
		
		return false;
	}


	@Override
	public String getDescription() {
		return descrip;
	}

//	Описания файлов распознаваемые фильтром
	public String setDescription(String newval) {
		return descrip= newval;
	}

}
