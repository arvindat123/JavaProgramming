package org.Java8;

import java.util.Arrays;

public class ArraysJava8 {
	
	public static int secondHighestElement(int[] arr) {
		
		int secondHighest = Arrays.stream(arr)
				.sorted().skip(1).findFirst().orElseThrow(() -> new IllegalArgumentException("There is no 2nd smallest element"));
		
		return secondHighest;
		
	}
	public static void main(String[] args) {
		int arr[] = {5};
		System.out.println(secondHighestElement(arr));
	}
}
