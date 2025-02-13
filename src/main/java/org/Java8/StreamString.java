package org.Java8;

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
        System.out.println(collect);
    }

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

        //Find the longest string in a list of strings using Java streams:
        List<String> strings = Arrays
                .asList("apple", "banana", "cherry", "redy","date", "grapefruit");
        Optional<String> str1 = strings.stream().min(Comparator.comparingInt(String::length));
        if(str1.isPresent()){
            System.out.println(str1.get());
        }

        StreamString test = new StreamString();
        test.avgAge();

    }

    class Person{
        public String name;
        public int age;
        public Person(String name, int age){
            this.name = name;
            this.age = age;
        }
    }
}
