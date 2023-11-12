package org.Java8;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class OptionalExample {
    public static void main(String[] args) {

       //Code that will throw null pointer exception
        /*String[] words = new String[10];
        String word = words[5].toLowerCase();
        System.out.print(word);*/
        //Example 1
        String[] words = new String[10];
        Optional<String> checkNull
                = Optional.ofNullable(words[5]);
        if (checkNull.isPresent()) {
            String word = words[5].toLowerCase();
            System.out.print(word);
        }
        else
            System.out.println("word is null");


    //Example 2

    // creating a string array
    String[] str = new String[5];

    // Setting value for 2nd index
    str[2]= "Geeks Classes are coming soon";

    // It returns an empty instance of Optional class
    Optional<String> empty1 = Optional.empty();
        System.out.println(empty1);

    // It returns a non-empty Optional
    Optional<String> value11 = Optional.of(str[2]);
        System.out.println(value11);

        //Example 3

        // creating a string array
        String[] str1 = new String[5];

        // Setting value for 2nd index
        str1[2] = "Geeks Classes are coming soon";

        // It returns a non-empty Optional
        Optional<String> value112 = Optional.of(str1[2]);

        // It returns value of an Optional.
        // If value is not present, it throws
        // an NoSuchElementException
        System.out.println(value112.get());

        // It returns hashCode of the value
        System.out.println(value112.hashCode());

        // It returns true if value is present,
        // otherwise false
        System.out.println(value112.isPresent());

        String value = "Hello, Optional!";

        // Creating an Optional with a non-null value
        Optional<String> optionalWithValue = Optional.of(value);

        // Creating an Optional with a potentially null value
        Optional<String> optionalNullable = Optional.ofNullable(getNullableValue());

        // Accessing values
        String result1 = optionalWithValue.orElse("Default Value");
        String result2 = optionalNullable.orElse("Default Value");

     //   System.out.println(result1);  // Output: Hello, Optional!
     //   System.out.println(result2);  // Output: Default Value

        // Using ifPresent to perform an action if a value is present
        optionalWithValue.ifPresent(v -> System.out.println("Value is present: " + v));
        optionalNullable.ifPresent(v -> System.out.println("Value is present: " + v));

    // Method that may return null

        //How do you print ten random numbers using forEach?
        Random random = new Random();
        random.ints().limit(10).forEach(System.out::println);

        //How do you get the highest number that exists on a list?
        List<Integer> number = Arrays.asList(3,6,1,7,2,9,4,11);
        int num = number.stream().max((s1,s2) -> s1 - s2).get();
        System.out.println(num);

    }
    private static String getNullableValue() {
        return null;
    }
}
