package org.designpattern.singleton;

// Java code to explain cloning
// issue with singleton
//To overcome this issue, override clone() method and throw an exception from clone method that is CloneNotSupportedException. 
class SuperClass implements Cloneable {

	int i = 10;

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}

// Singleton class
class SingletonClone extends SuperClass {
	// public instance initialized when loading the class
	public static SingletonClone instance = new SingletonClone();

	private SingletonClone() {
		// private constructor
	}


// To prevent cloneable - solution

//	@Override
//	protected Object clone() throws CloneNotSupportedException {
//		throw new CloneNotSupportedException();
//	}

}

public class CloneableSingleton {
	public static void main(String[] args) throws CloneNotSupportedException {
		SingletonClone instance1 = SingletonClone.instance;
		SingletonClone instance2 = (SingletonClone) instance1.clone();
		System.out.println("instance1 hashCode:- " + instance1.hashCode());
		System.out.println("instance2 hashCode:- " + instance2.hashCode());
	}
}