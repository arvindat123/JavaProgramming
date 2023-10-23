package org.Java8;

import java.util.Arrays;
import java.util.List;

public class ParallelStreamsConcurrency {
	public static void main(String[] args) {

		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		int sum = numbers.stream().mapToInt(n -> n).sum(); //serial stream
		System.out.println("sum ->" + sum);
		
		int parallelSum = numbers.stream().parallel().mapToInt(n -> n).sum();
		System.out.println("Parallel sum ->" + parallelSum);
	}

}
