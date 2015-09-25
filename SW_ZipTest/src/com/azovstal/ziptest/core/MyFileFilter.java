package com.azovstal.ziptest.core;

import java.io.File;
import java.util.ArrayList;

import javax.swing.filechooser.FileFilter;

public class MyFileFilter extends FileFilter {
	
	private ArrayList list = new ArrayList();
	private String descrip = "";
	
	
//	������ ���������� �������������� �������� � ��������� �� � ������ ������ ����������
//	@param newVal - ".txt"	
	public void addExtentions(String newVal){
		if(!newVal.startsWith("."))	newVal = "." + newVal;
		 list.add(newVal.toLowerCase());	
		
	}
	
	
//	���������� ���������� ������
//	@PARAM	f ���� 
	
	@Override
	public boolean accept(File f) {
		if(f.isDirectory()) return true;
		String string = f.getName().toLowerCase();
		
		//��������, ����� �� ���� �������� ����������
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

//	�������� ������ �������������� ��������
	public String setDescription(String newval) {
		return descrip= newval;
	}

}
