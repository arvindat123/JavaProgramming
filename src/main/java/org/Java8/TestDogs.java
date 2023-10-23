package org.Java8;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TestDogs {
	public static void main(String[] args) {
		List<Dog> dogs = new ArrayList<>();
		Dog boi = new Dog("boi", 30, 6); 
		Dog clover = new Dog("clover", 35, 12);
		Dog aiko = new Dog("aiko", 50, 10);
		Dog zooey = new Dog("zooey", 45, 8);
		Dog charis = new Dog("charis", 120, 7);
		dogs.add(boi);
		dogs.add(clover);
		dogs.add(aiko);
		dogs.add(zooey);
		dogs.add(charis);
		
		boolean cNames = dogs.stream().anyMatch(d -> d.getWeight() > 50 && d.getName().startsWith("c"));
		System.out.println(cNames);
		
		cNames = dogs.stream().allMatch(d -> d.getWeight() > 5);
		System.out.println("allMatch match any dog with weight > 5 -> "+cNames);
		
		cNames = dogs.stream().noneMatch(d -> d.getName().equals("red"));
		System.out.println("nonMatch match any name with 'red' -> "+cNames);
		
		dogs.stream().map(d -> d.getWeight()).forEach(d -> System.out.println(d));
		
		System.out.println("mapToInt");
		dogs.stream().mapToInt(d -> d.getWeight()).forEach(d -> System.out.println(d));
		
		System.out.println("mapToDouble");
		dogs.stream().mapToDouble(d -> d.getWeight()).forEach(d -> System.out.println(d));
		
		System.out.println("mapToLong");
		dogs.stream().mapToLong(d -> d.getWeight()).forEach(d -> System.out.println(d));
		
		Optional<Dog> c50 = dogs.stream().filter(d -> d.getWeight() > 5 && d.getName().startsWith("c")).findAny();//Return one result if multiple passed the predicate
		System.out.println("Get data => "+ c50.get().getName());
		System.out.println("Get data => "+ c50.get());
		System.out.println("Get data => "+ c50);
		c50.ifPresent(System.out::println);
		System.out.println(c50.isPresent());
		
		
		
		
		
		/*
		 * Dog boi = new Dog("boi", 30, 6); Dog clover = new Dog("clover", 35, 12);
		 * 
		 * DogQuerier dq = d -> d.getAge() > 9;
		 * 
		 * System.out.println("is Boi older than 9 ? " + dq.test(boi));
		 * System.out.println("is Clover older than 9 ? " + dq.test(clover));
		 */
	}
}
