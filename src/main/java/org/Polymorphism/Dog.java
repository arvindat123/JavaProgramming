package org.Polymorphism;

public class Dog extends Animal {
	public String shout() {
		return "BOW BOW";
	}
	public void run() {
		System.out.println("inside run method");
	}
	
	public static void main(String[] args) {
		Animal animal1 = new Animal();
		System.out.println(animal1.shout()); // output : Don't know!
		//Dog d = new Animal(); compile time error
		Animal animal2 = new Dog();
		System.out.println(animal2.shout()); // output : BOW BOW
		//animal2.run();//COMIPLE ERROR , The method run() is undefined for the type Animal
	}
}	
