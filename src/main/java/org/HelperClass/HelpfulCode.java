package org.HelperClass;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class HelpfulCode {
    public static void main(String[] args) {

        //To join a string array with specified space
        String ss = " the sky  is blue ";
        String str10 = ss.trim(); // remove pre and postfix space from string
        String[] strArray = str10.split("\\s+"); //split based in space (any number of space, with regular expression)
        String temp = String.join(" ", strArray); // output : "the sky is blue"

        //Count of unique character and their count without stream and HashMap
        String paragraph1 = "I am not going to market.";
        int[] arrOfAllCharacter = new int[256];
        for (int i = 0; i < paragraph1.length(); i++) {
            arrOfAllCharacter[paragraph1.charAt(i)]++;
        }
        for (int j = 0; j < 256; j++) {
            if(arrOfAllCharacter[j] != 0){
                System.out.println(arrOfAllCharacter[j]+"="+(char)j);
            }
        }


        //To break string to char array and sort
        String strr = "Arvind Gupta ";
        String[] s = strr.split("");
        Arrays.sort(s);
        System.out.println(Arrays.toString(s)); // [ ,  , A, G, a, d, i, n, p, r, t, u, v]

        char[] c = strr.toCharArray();
        Arrays.sort(c);
        System.out.println(Arrays.toString(c)); // [ ,  , A, G, a, d, i, n, p, r, t, u, v]

        String paragraph = "I am going to market.";
        System.out.println(paragraph.charAt(0)); // I

        System.out.println(paragraph.length()); // 21

        //For String use string.length();
        //For Array use array.length;

        //https://www.geeksforgeeks.org/prime-numbers/
        //HashMap
        Map<String, Integer> map = new HashMap<>();
        String str1 = "arvind";
        map.put(str1, 123);
        str1 = str1 + "abc";
        System.out.println(map.get(str1)); // null

        //to convert number to binary code
        String binaryCode = Integer.toString(5, 2); //101
        System.out.println(binaryCode); // 101

        //How to split words
        String str = "Hello World";
        String[] split = str.split("");
        char[] charArray = str.toCharArray();
        System.out.println(Arrays.toString(split)); //[H, e, l, l, o,  , W, o, r, l, d]
        System.out.println(Arrays.toString(charArray)); //[H, e, l, l, o,  , W, o, r, l, d]

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


        //reverse an array using two pointer
        int[] arr8 = {1,2,3,4,5,5};
        int i=0,j=arr8.length-1;
        while(i<j) {
            int t = arr8[i];
            arr8[i] = arr8[j];
            arr8[j] = t;
            i++;
            j--;
        }

    }
}
