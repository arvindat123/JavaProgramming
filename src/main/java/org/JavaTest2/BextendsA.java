package org.JavaTest2;

import java.util.*;

public class BextendsA extends A{
	
	private void aMethod() {
		System.out.println("Overridden superclass private method");
	}
	
	/*static void aStaticMethod() {
		System.out.println("overridden superclass static method");
	}*/
	
	public static void main(String[] args) {
		A obj = new A();
		//obj.aMethod();
		aStaticMethod();
		aStaticMethod();
		//obj.aMethod();
		
		System.out.println(1.0/0.0);
		//System.out.println(1/0); Exception
		
		String temp = "A"+"B"+"C";
		
		
	}
}
