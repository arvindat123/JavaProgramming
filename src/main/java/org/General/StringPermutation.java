package org.General;

import java.util.Arrays;

public class StringPermutation {

	boolean stringPermutation(String str1, String str2) {
		if(str1.length() != str2.length()) return false;
		char[] charArrayFirst = str1.toCharArray();
		Arrays.sort(charArrayFirst);
		str1 = new String(charArrayFirst);
		System.out.println(str1);
		char[] charArraySecond = str2.toCharArray();
		Arrays.sort(charArraySecond);
		str2 = new String(charArraySecond);
		System.out.println(str2);
		if(str1.equals(str2)) return true;			
		else return false;
	}
	
	public static void main(String[] args) {

		System.out.println(new StringPermutation().stringPermutation("arvind", "airvnd"));
		

		
		
	}

}
