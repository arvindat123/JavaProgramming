package org.collection;

import java.util.Arrays;
import java.util.List;

public class DuckComparableStreamTest {
	public static void main(String[] args) {
		List<DuckComparable> ducks = Arrays.asList(new DuckComparable("Jerry", "yellow", 3),
				new DuckComparable("George", "brown", 4),
				new DuckComparable("Kramer", "mottled", 6),
				new DuckComparable("Elaine", "white", 2));
		
		// DuckComparable By implementing comparable interface
		ducks.stream().sorted().forEach(d -> System.out.println(d));
		
		System.out.println("==========Next sorting =============");
		//By using comparator in Lambda expression in stream
		ducks.stream().sorted((s1,s2) -> s1.getName().compareTo(s2.getName())).forEach(System.out::println);
		
		
	}
}
