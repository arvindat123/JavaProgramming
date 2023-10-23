package org.Java8;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class HighestOccurrenceInArrayInteger {
	public static void main(String[] args) {

		// without stream 8
		int[] numbers = { 1, 2, 2, 3, 3, 3, 4, 4, 4, 4 };
		if (numbers == null || numbers.length == 0) {
			throw new IllegalArgumentException("Array is null or empty");
		}

		Map<Integer, Integer> occurrenceMap = new HashMap<>();

		// Count occurrences of each integer in the array
		for (int num : numbers) {
			occurrenceMap.put(num, occurrenceMap.getOrDefault(num, 0) + 1);
		}

		// Find the integer with the highest occurrence
		int maxNum = 0;
		int maxOccurrence = 0;

		for (Map.Entry<Integer, Integer> entry : occurrenceMap.entrySet()) {
			if (entry.getValue() > maxOccurrence) {
				maxNum = entry.getKey();
				maxOccurrence = entry.getValue();
			}
		}

		// Using Java 8 stream
		int mostFrequentNumber = findMostFrequentNumber(numbers);
		System.out.println("Most frequent number: " + mostFrequentNumber);
	}

	private static int findMostFrequentNumber(int[] numbers) {
		Map<Integer, Long> occurrences = Arrays.stream(numbers).boxed()
				.collect(Collectors.groupingBy(i -> i, Collectors.counting()));

		return occurrences.entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey)
				.orElseThrow(() -> new RuntimeException("Array is empty"));

		/*
		 * Convert the primitive array to a stream of boxed integers. Use
		 * Collectors.groupingBy to group the integers by their values and count
		 * occurrences. Use Collectors.counting() as the downstream collector to count
		 * occurrences for each group. Use entrySet().stream() to operate on the grouped
		 * entries. Use max to find the entry with the maximum count. Map the entry to
		 * its key (the number with the maximum occurrence). This will output the most
		 * frequent number in the array.
		 */
	}
	// without stream 8

}