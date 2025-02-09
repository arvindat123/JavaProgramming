package org.Java8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamString {
    public static void main(String[] args) {
        //Reverse String using Stream API Java 8
        String str = "Hello World";
        String reverse = str.chars().mapToObj(c ->(char)c).reduce("", (s,c)->c+s, (s1,s2) -> s2+s1);
        System.out.println(reverse);

        //2nd way
        String reversed = Stream.of(str).map(word -> new StringBuilder(word).reverse()).collect(Collectors.joining(" "));
        System.out.println(reversed);

        //3rd way
        String reversed1 = IntStream.range(0, str.length())
                .map(i -> str.charAt(str.length() - i - 1))
                .collect(StringBuilder::new, (sb, c) -> sb.append((char) c), StringBuilder::append)
                .toString();
        System.out.println(reversed1);

        //Find the longest string in a list of strings using Java streams:
        List<String> strings = Arrays.asList("apple", "banana", "cherry", "date", "grapefruit");
        Optional<String> longestString = strings.stream().max(Comparator.comparingInt(String::length));
    }
}
