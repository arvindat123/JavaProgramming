package org.HelperClass;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class HelpfulCode {
    public static void main(String[] args) {
        boolean isNegative = 10 < 12; //true
        System.out.println(Math.abs(-989)); //989 return positive of number
        StringBuilder sb = new StringBuilder("123456").reverse(); //print reverse number
        System.out.println(sb);//654321
        System.out.println(Integer.parseInt(sb.toString()));//print integer form of string
        //String doesn't have reverse method
        //StringBuilder has reverse method
        int intNumber = 1009;
        System.out.println(-intNumber);//just add - to convert postive to negative
        System.out.println(1 / 10); // 0

        //creation of list
        List<String> list = List.of("a", "b");
        List<String> list1 = Arrays.asList("a", "b");

        //declare and initialize array
        //[1] Declare and Initialize Separately
        int[] arr;              // Declaration
        arr = new int[5];       // Initialization with size 5
        arr[0] = 10;            // Assigning values individually
        arr[1] = 20;

        //[2] Declare and Initialize with Values
        int[] arr1 = {10, 20, 30, 40, 50};  // Declare and initialize with values

        //[3] Using new Keyword with Initial Values
        int[] arr2 = new int[]{10, 20, 30, 40, 50};  // Explicitly use new keyword

        //[4] Using Loops to Initialize
        int[] arr3 = new int[5];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i * 10;  // Initialize with multiples of 10
        }

        //[5] Using Anonymous Arrays, Anonymous arrays are arrays created without assigning to a variable.
        //printArray(new int[]{10, 20, 30, 40, 50});  // Passing directly to a method
        // Method to print array
        for (int num : arr) {
            System.out.print(num + " ");
        }

        //[6]Multi-Dimensional Arrays, Declare and Initialize 2D Array
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        //Declare and Initialize 3D Array
        int[][][] cube = {
                {{1, 2}, {3, 4}},
                {{5, 6}, {7, 8}}
        };

        //[7] Using Arrays.fill(), The Arrays.fill() method can populate an array with a specific value.
        int[] arr4 = new int[5];
        Arrays.fill(arr, 100);  // Fills the array with 100

        //[8]Using Streams (Java 8 and Later)
        // Initialize Using IntStream
        int[] arr5 = IntStream.range(1, 6).toArray();  // Generates [1, 2, 3, 4, 5]
        //Using Arrays.setAll
        int[] arr6 = new int[5];
        Arrays.setAll(arr6, i -> i * 2);  // Sets each element to twice its index

        //[9] Declaring with Variable Length,
        int n = 5;
        int[] arr7 = new int[n];  // Array size based on a variable

        //[10] Mixed Dimensions for Multi-Dimensional Arrays
        int[][] jaggedArray = new int[3][];
        jaggedArray[0] = new int[]{1, 2};
        jaggedArray[1] = new int[]{3, 4, 5};
        jaggedArray[2] = new int[]{6, 7, 8, 9};

    }
}
