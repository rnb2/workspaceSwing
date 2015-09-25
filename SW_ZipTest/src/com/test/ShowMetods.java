package com.test;


import java.lang.reflect.*;

public class ShowMetods {

	static final String usage = 
		"usage: \n" +
	    "ShowMethods qualified.class.name\n" +
	    "To show all methods in class or: \n" +
	    "ShowMethods qualified.class.name word\n" +
	    "To search for methods involving 'word'";
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		if(args.length <1){
			System.out.println(usage);
			System.exit(0);
		}
		Class c;
		try {
			c = Class.forName(args[0]);
			Method[] m = c.getMethods();
			Constructor[] cons = c.getConstructors();
			
			if(args.length == 1){
				for (int i = 0; i < cons.length; i++) {
					System.out.println("--------------constructors");
					System.out.println(cons[i]);
				}
				
				System.out.println("--------------");
				System.out.println("--------------Metods");
				for (int i = 0; i < m.length; i++) {
					System.out.println(m[i].getName());
				
				}
			}else {
				for (int i = 0; i < cons.length; i++) {
					
					if (cons[i].toString().indexOf(args[1]) !=-1 ){
						System.out.println(cons[i]);
					}
				
					if(m[i].toString().indexOf(args[1]) != -1){
						System.out.println(m[i]);
						
					}
				}
			}
			
		} catch (ClassNotFoundException e) {
			System.out.println("No such class " + e);
					}
		
	}

}
