package org.JavaTest2;



abstract class Animal{
	void eat() {
		System.out.println("animal eating");
	}
	
}
class Dog1 extends Animal {
	void bark() {System.out.println("animal barking"); }
}
class Cat extends Animal {
	void meow() {System.out.println("animal meow"); }
}