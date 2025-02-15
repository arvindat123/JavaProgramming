package org.Java8;

import org.Test.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamString {

    //Calculate the average age of a list of Person objects using Java streams:
    public void avgAge() {
        List<Person> persons = Arrays.asList(
                new Person("Alice", 25),
                new Person("Bob", 30),
                new Person("Charlie", 35)
        );
        double collect = persons.stream().collect(Collectors.averagingLong(p -> p.age));
        //double averageAge = persons.stream().mapToInt(p->p.age).average().orElse(0);
        System.out.println(collect);
    }

    public static boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i < Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {

        //Merge two sorted lists into a single sorted list using Java streams:
        List<Integer> list1 = Arrays.asList(1, 3, 5, 7, 9, 45, 67, 89);
        List<Integer> list2 = Arrays.asList(2, 4, 6, 8, 10);
        List<Integer> finalList = Stream.concat(list1.stream(), list2.stream()).sorted().collect(Collectors.toList());
        System.out.println(finalList);

        //Check if a list of integers contains a prime number using Java streams:
        List<Integer> numbers = Arrays.asList(2, 4, 6, 8, 10, 11, 12, 13, 14, 15);
        boolean isPrimeNumber = numbers.stream().anyMatch(StreamString::isPrime);
        System.out.println(isPrimeNumber);

        //Reverse String using Stream API Java 8
        String str = "Hello World";
        String reverse = str.chars().mapToObj(c -> (char) c).reduce("", (s, c) -> c + s, (s1, s2) -> s2 + s1);
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

        //Find the longest string in a list of strings using Java streams:
        List<String> strings1 = Arrays
                .asList("apple", "banana", "cherry", "redy", "date", "grapefruit");
        Optional<String> str1 = strings1.stream().min(Comparator.comparingInt(String::length));
        if (str1.isPresent()) {
            System.out.println(str1.get());
        }

        StreamString test = new StreamString();
        test.avgAge();

    }

    class Person {
        public String name;
        public int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }
}
