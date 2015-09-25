package com.azovstal.core;

public class TestText2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String currentValue = "s";
		String currentValue2 = "2";
		String currentValue3 = "ä";
		
		Character charAt = currentValue.charAt(0);
		Character charAt2 = currentValue2.charAt(0);
		Character charAt3 = currentValue3.charAt(0);
		
		String regex = "[A-Za-z]+";
		
		System.out.println("char: " + charAt + " type: " + Character.getType(charAt) + " is Def: " + Character.isDefined(charAt) + " is latin: " + currentValue.matches(regex));
		
		System.out.println("char: " + charAt2 + " type: " + Character.getType(charAt2) + " is Def: " + Character.isDefined(charAt2)  + " is latin: " + currentValue2.matches(regex));
		
		System.out.println("char: " + charAt3 + " type: " + Character.getType(charAt3) + " is Def: " + Character.isDefined(charAt3) + " is latin: " + currentValue3.matches(regex));
		
		
		System.out.println("char: " + currentValue + " is latin: " + isLatinCharacter(currentValue));
	
		
	}
	
	public static boolean isLatinCharacter(String value){
		if(value.isEmpty()){
			return false;
		}
		
		char charAt = value.charAt(0);
		if(Character.isLetter(charAt)){
			return value.matches("[A-Za-z]+");
		}
		
		return false;
	}

}
