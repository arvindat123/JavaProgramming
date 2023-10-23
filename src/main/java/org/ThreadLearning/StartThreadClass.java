package org.ThreadLearning;

public class StartThreadClass implements Runnable{
	
	public void run() {
		System.out.println("Implements Runnable interface");
	}
	

	public static void main(String[] args) {
		StartThreadClass obj = new StartThreadClass();
		Thread t = new Thread(obj);
		t.start();
		try {
			t.sleep(100);
		} catch(InterruptedException e) {
			e.getMessage();
		}
		
		String a = new String("a");
		String aa = new String("a");
		System.out.println(a == aa ); //false
		
		 // == operator compares reference or memory location of objects in a heap, whether they point to the same location or not.
		//Whenever we create an object using the operator new, it will create a new memory location for that object
		System.out.println(a.equals(aa)); //true
		
		//.equals() method for content comparison
		//If a class does not override the equals method, then by default, it uses the equals(Object o) method of the closest parent class that has overridden this method. 
		
		
		
	}
}
