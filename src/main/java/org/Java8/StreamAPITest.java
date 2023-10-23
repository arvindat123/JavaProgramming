package org.Java8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class StreamAPITest {
	void print() {
		System.out.println("Hi");
	}
	
public static void main(String[] args) {
	
	/*
	 * Map<String, Integer> myMap = new HashMap<>(); myMap.put("Boi", 6);
	 * myMap.put("Zooey", 3); myMap.put("CHaris", 8);
	 * 
	 * System.out.println("Number of items in the map with the value : 4 => "+
	 * myMap.entrySet() .stream() .filter(d -> d.getValue() > 4) .count());
	 */

	try 
	{
		Stream<String> stream = Files.lines(Paths.get("dvdinfo.txt"));
	} 
	catch (IOException e) 
	{
		e.printStackTrace();
	}
	
	
	
	
	
	
	
	/*List<String> coffees = List.of("Cappuccino",
			"Americano", "Espresso","Cortado", "Mocha",
			"Cappuccino", "Flat White", "Latte");
	


	//List<String> coffeesEndingInO = coffees.stream().distinct().sorted().filter(s -> s.endsWith("o")).collect(Collectors.toList());
	List<String> coffeesEndingInO = coffees.stream().distinct().sorted()
													.filter(s -> s.endsWith("o"))
													.collect(Collectors.toUnmodifiableList());
	System.out.println(coffeesEndingInO);
	coffeesEndingInO.add("arvind");
	*/
	
	//Comparator<String> comparator = (s1, s2) -> s1.compareTo(s2);
	
	//Runnable runnable = () -> System.out.println("Hello");
	
	//Consumer<String> consumer = str -> System.out.println("Hello");
	/*
	 * Integer[] myNums = {1,2,3}; Stream<Integer> myStream = Arrays.stream(myNums);
	 * 
	 * 
	 * List<Double> tempsInPhoenix =
	 * Arrays.asList(123.6,118.0,113.0,112.5,115.8,117.0,110.2,110.1,106.0,106.4);
	 * System.out.println("Number of days over 110 in 10 day period " +
	 * tempsInPhoenix.stream().filter(i -> i > 110).count());
	 */
	
	
	
	
}
	
}
