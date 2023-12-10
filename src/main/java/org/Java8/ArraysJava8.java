package org.Java8;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.IntStream;

public class ArraysJava8 {

    public static int secondHighestElement(int[] arr) {

        int secondHighest = Arrays.stream(arr).boxed()
                .sorted((a,b) -> b.compareTo(a)).skip(1).findFirst().orElseThrow(() -> new IllegalArgumentException("There is no 2nd smallest element"));
        return secondHighest;

    }

    public static void main(String[] args) {
        int arr[] = {1, 2, 3, 4, 5, 6};
        System.out.println(secondHighestElement(arr));

        int a[] = {1, 2, 3, 4, 5, 6};

    }
}
