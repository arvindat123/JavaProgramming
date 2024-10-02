package org.designpattern.singleton;

class Singleton {

	private static Singleton uniqueInstance;

	private Singleton() {  //Cannot create object in subclass and cannot be extended as super class

	}

	public static Singleton getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new Singleton();
		}
		return uniqueInstance;
	}

	public void simpleMethod() {
		System.out.println("Inside simple method");
	}
}

public class SingletonTest {
	public static void main(String[] args) {
		//Singleton abc = new Singleton();
		Singleton object1 = Singleton.getInstance();
		Singleton object2 = Singleton.getInstance();

		System.out.println("object1 " + object1);
		System.out.println("object2 " + object2);

		if (object1 == object2 && object1.equals(object2)) {
			System.out.println("object1 == object2 : True");
		}

		object1.simpleMethod();

	}
}
