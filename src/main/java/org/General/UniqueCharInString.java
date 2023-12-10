package org.General;

public class UniqueCharInString {

	boolean isUniqueChar(String str) {
		if(str.length() > 128) return false; //Considering ASCII String
		boolean[] bol_charSet = new boolean[128];
		for(int i = 0;i<str.length(); i++) {
			int val = str.charAt(i);
			if(bol_charSet[val]) {
				return false;
			}
			bol_charSet[val] = true;
		}	
		return true;
	}
	
	public static void main(String[] args) {
		UniqueCharInString obj = new UniqueCharInString();
		System.out.println(obj.isUniqueChar("arvindd"));
		
	}

}
