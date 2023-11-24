package org.General;

public class SuperClass {

    final int print(){ return 1;}

 public SuperClass() {
	 System.out.println("Inside SuperClass");
     this.print("again arvind");
 }
 public SuperClass(String str) {
	 System.out.println("Inside SuperClass arg const"+str);
 }
    public void print(String str) {
        System.out.println("Inside SuperClass"+str);
    }
}


//Private constructor doesn't allow other class to create object of this class or extend this class 
//Final class cannot be extended