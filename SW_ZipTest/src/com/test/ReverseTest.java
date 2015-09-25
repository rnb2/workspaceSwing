package com.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ReverseTest {

	/**
	 * @param args  RNB: 30.08.2007--------------
	 */
	public static void main(String[] args) {

		Collection list = getList1();
		Collections.reverse((List<?>) list);
		
		Iterator it = list.iterator();
		while (it.hasNext()){
			String ss = (String)it.next();
			System.out.println("RNB: 30.08.2007--------------"+ss);
		}	

	}


	public static Collection getList1(){
		ArrayList listRS = new ArrayList();
		for (int i = 1; i < 10; i++) {
			listRS.add(new String(""+i));
			System.out.println(i);
		}
		return listRS;
	}


}
