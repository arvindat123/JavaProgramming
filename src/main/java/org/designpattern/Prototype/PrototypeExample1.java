package org.designpattern.Prototype;


//Prototype interface
interface Prototype extends Cloneable {
	Prototype clone();
}

//Concrete prototype
class ConcretePrototype implements Prototype {
	@Override
	public Prototype clone() {
		try {
			return (Prototype) super.clone();
		} catch(CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}
}

//Client code
public class PrototypeExample1 {
	public static void main(String[] args) {
		ConcretePrototype original = new ConcretePrototype();
		ConcretePrototype copy = (ConcretePrototype) original.clone();
		
		System.out.println("Original = "+original);
		System.out.println("Copy = "+copy);
		
	}
}
