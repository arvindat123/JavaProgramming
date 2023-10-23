package org.Java11;

import java.util.stream.Collectors;

// Java Program to demonstrate
// the working of isBlank() method

class GFG {
	public static void main(String args[])
	{
		String str1 = "";
		System.out.println(str1.isBlank());

		String str2 = "GeeksForGeeks";
		System.out.println(str2.isBlank());
		
		/*
		 * String str3 = null; System.out.println(str3.isBlank()); NullPointerException
		 */
		String str = "Geeks\nFor\nGeeks";
        System.out.println(str.lines().collect(Collectors.toList()));
        
        String s = "GeeksForGeeks-";
        System.out.println(s.repeat(4));
        
        s = " GeeksForGeeks";
        System.out.println(s.stripLeading());
        
        s = "GeeksForGeeks ";
        System.out.println(s.stripTrailing());
        
        s = " GeeksForGeeks ";
       System.out.println(s.strip());
	}
}
