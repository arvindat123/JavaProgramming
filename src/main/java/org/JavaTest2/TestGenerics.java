package org.JavaTest2;

import java.util.List;

public class TestGenerics {
	
	 public static void main(String[] args) {
		List<Animal> animals = List.of(new Dog1(), new Cat(), new Dog1());
		takeAnimals(animals);
		
		List<Dog1> dogs = List.of(new Dog1(), new Dog1());	
		//takeAnimals(dogs);  // compile time error for takeAnimals(List<Animal> animals)
		takeAnimals(dogs);  // works fine for wildcar takeAnimals(List<? extends Animal> animals)
	}
	
	public static void takeAnimals(List<? extends Animal> animals) {
		for(Animal a : animals) {
			a.eat();
			//animals.add(new Dog1()); Runtime unsupported exception immutable collection
		}
	}
}
