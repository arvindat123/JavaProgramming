package org.Java8;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DuplicateInArray {

	public static void main(String[] args) {
		Integer[] numbers = new Integer[] { 1, 2, 1, 3, 4, 4 };
		Set<Integer> allItems = new HashSet<>();
		Set<Integer> duplicates = Arrays.stream(numbers)
		        .filter(n -> !allItems.add(n)) //Set.add() returns false if the item was already in the set.
		        .collect(Collectors.toSet());
		System.out.println(duplicates); // [1, 4]
		
		List<Integer> numbersList = Arrays.asList(new Integer[]{1,2,1,3,4,4,});    
		numbersList.stream().filter(i -> Collections.frequency(numbersList, i) >1 )
        .collect(Collectors.toSet()).forEach(System.out::println);
	}

}
