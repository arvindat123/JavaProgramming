package org.Java8;

import java.util.Arrays;
import java.util.stream.Collectors;

public class RemoveEmptyStrings {
    public static void main(String[] args) {
        String[] array = {"apple", "", "banana", "", "orange"};

        // Using Stream API to remove empty strings
        String[] result = Arrays.stream(array)
                .filter(str -> !str.isEmpty())
                .toArray(String[]::new);

        // Print the result
        System.out.println(Arrays.toString(result));
    }
}

/*
Using the Stream API in Java, you can remove empty strings from an array of strings like this
In this example, Arrays.stream(array) converts the array to a stream of strings.
 The filter method is then used to exclude empty strings, and toArray(String[]::new)
 collects the elements into a new array.

        This results in a new array without empty strings.*/
