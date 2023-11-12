package org.Java8;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.IntStream;

public class SecondHighestElementExample {
    public static void main(String[] args) {
        int[] array = {10};
		/*
		 * int[] a = new int[] {1,2,3}; 
		 * int[] a2 = new int[3];
		 */
        
        int secondHighest = findSecondHighest(array);

        System.out.println("Second Highest Element: " + secondHighest);
    }

    private static int findSecondHighest(int[] array) {
		Optional<Integer> a =  Arrays.stream(array)
				.distinct()
				.boxed()  // Convert to Integer stream to use reverseOrder
				.sorted(Collections.reverseOrder())
				.skip(1)   // Skip the first element (highest)
				.findFirst();
			if(a.isPresent()) {
				System.out.println("Second highest  ->" + a.get());
			} else
				System.out.println("Array contains no 2nd highest element");


    
    	
        return Arrays.stream(array)
                .distinct()
                .boxed()  // Convert to Integer stream to use reverseOrder
                .sorted(Collections.reverseOrder())
                .skip(1)   // Skip the first element (highest)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Array is empty"));
    }
	/*
	 * The Arrays.stream(array) converts the array to an IntStream. distinct() is
	 * used to remove duplicate elements. boxed() is used to convert the IntStream
	 * to a Stream of Integer to enable the use of reverseOrder in the subsequent
	 * sorting operation. sorted(java.util.Collections.reverseOrder()) sorts the
	 * elements in descending order. skip(1) skips the first element (the highest
	 * element). findFirst() gets the first element of the remaining stream, which
	 * is the second-highest element.
	 */
}