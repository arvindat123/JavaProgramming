package org.Leetcode;

public class RegularExpressionMatching {

	
	 public boolean isMatch(String s, String p) {
		 	//String s = "aa";
			int i = s.length();
			int plength = p.substring(0, p.indexOf('*')).length();
			if(i == plength && s.equals(p)) {
				return true;
			}
			return false;
	    }
	
	
	public static void main(String[] args) {
		
		String pattern = "a*";
		System.out.println(pattern.indexOf('*'));
		System.out.println(pattern.substring(0, pattern.indexOf('*')));
		String s = "aa";
		int i = s.length();
		int plength = pattern.substring(0, pattern.indexOf('*')).length();
		if(i == plength && s.equals(pattern)) {
			 
		}
		
	}

}
