package org.AbstractClass;

public abstract class AbstractClassOne {
	public void firstMethod() {
		System.out.println("Hi");
	}
//hi
	public AbstractClassOne(){

	}
	//Abstract class can have constructor
	public abstract void print();
}
//If class is final then class cannot be extended but object of final class can be created
//If class has private constructor then class cannot be extended or object cannot be created
