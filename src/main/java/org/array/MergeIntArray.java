package org.array;

import java.util.Arrays;

public class MergeIntArray {
	public static void main(String[] args) {
        // Two int arrays to be merged
        int[] array1 = {1, 2, 3};
        int[] array2 = {4, 5, 6};

        // Merge the arrays
        int[] mergedArray = mergeArrays(array1, array2);

        // Display the merged array
        System.out.println("Merged Array: " + Arrays.toString(mergedArray));
    }

    // Function to merge two int arrays without inbuilt functions
    private static int[] mergeArrays(int[] array1, int[] array2) {
        int length1 = array1.length;
        int length2 = array2.length;

        // Create a new array with the combined length
        int[] mergedArray = new int[length1 + length2];

        // Copy elements from array1 to mergedArray
        for (int i = 0; i < length1; i++) {
            mergedArray[i] = array1[i];
        }

        /* Copy elements from array2 to mergedArray */
        for (int i = 0; i < length2; i++) {
            mergedArray[length1 + i] = array2[i];
        }

        return mergedArray;
    }
}
