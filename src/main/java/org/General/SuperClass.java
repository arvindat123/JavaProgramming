package org.General;

public class SuperClass {
 public SuperClass() {
	 System.out.println("Inside SuperClass");
 }
 public SuperClass(String str) {
	 System.out.println("Inside SuperClass"+str);
 }
}


//Private constructor doesn't allow other class to create object of this class or extend this class 
//Final class cannot be extended