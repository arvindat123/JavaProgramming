package org.Java8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class FlatMap {
    public static void main(String[] args) {
        try {

            //https://stackoverflow.com/questions/64927047/how-to-find-second-highest-salary-in-below-array-list-using-java8-streams
            Stream<String> input = Files.lines(Paths.get("D:\\EclipseWorkspace\\JavaLearning\\JavaExamples\\src\\com\\JavaTest\\Java8\\java.txt"));

            Stream<String[]> inputStream = input.map(line -> line.split(" "));
            //System.out.println(inputStream);

            //Stream<Stream<String>> ss = inputStream.map(array -> Arrays.stream(array)); //Stream<Stream<String>>
            //System.out.println(ss);

            Stream<String> streamOfString = inputStream.flatMap(array -> Arrays.stream(array));

            //System.out.println(streamOfString);

            //Complete code to count the java word
            // long n = Files.lines(Paths.get("D:\\\\EclipseWorkspace\\\\JavaLearning\\\\JavaExamples\\\\src\\\\com\\\\JavaTest\\\\Java8\\\\java.txt")).map(line -> line.split(" ")).flatMap(array -> Arrays.stream(array)).filter(d -> d.equals("java")).count();

            //System.out.println("Number of java words-> " + n);

            Files.lines(Paths.get("D:\\\\EclipseWorkspace\\\\JavaLearning\\\\JavaExamples\\\\src\\\\com\\\\JavaTest\\\\Java8\\\\java.txt")).map(line -> line.split(" ")).flatMap(array -> Arrays.stream(array)).filter(d -> d.equals("java")).collect(Collectors.groupingBy(a -> a.toString(), Collectors.counting())).forEach((s, p) -> System.out.println(s + " " + p));

            Stream.iterate(0, s -> s + 1).limit(10).forEach(System.out::println);
            LongStream.iterate(0, t -> t + 1).limit(10).forEach(System.out::println);
            System.out.println("------------");

            IntStream numStream = IntStream.range(10, 15); //Exclusive end boundary like 15 will be excluded
            numStream.forEach(System.out::println);


            System.out.println("------------");

            IntStream numStreamInclusive = IntStream.rangeClosed(10, 15);
            numStreamInclusive.forEach(System.out::println); //Inclusive both boundary value

            String listOfName = "Arvinda Prabha Aarabhdf"; //return the largest string and then count the number of character in that string
            Arrays.stream(listOfName.split(" ")).sorted((a,b) -> b.length()-a.length()).forEach(System.out::println);
            Stream.of(listOfName).map(m -> m.split(" ")).flatMap(Arrays::stream).sorted((a, b) -> b.compareTo(a)).forEach(System.out::println);
            Optional<Integer> largestString = Stream.of(listOfName).map(m -> m.split(" ")).flatMap(Arrays::stream).map(i -> i.length()).sorted((i, j) -> j - i).findFirst();
            if (largestString.isPresent()) {
                System.out.println(largestString.get());
            }
            List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
            numbers.parallelStream().forEach(System.out::println);


            //The flatMap() function is an extension of the map function.
            // Apart from transferring one object into another, it also flattens it.
            //Example: Consider you have a list of list data and you want to combine all elements
            // of lists into just one list. In this case, we can use flatMap()
            // The function you pass to the map() operation returns a single value.
            //The function you pass to flatMap() operation returns a Stream of value.
            //The flatMap() is a combination of map and flat operation.
            //The map() is used for transformation only, but flatMap() is used for both transformation and flattening.

            List<Integer> evens = Arrays.asList(2, 4, 6);
            List<Integer> odds = Arrays.asList(1, 3, 5);
            List<Integer> primes = Arrays.asList(2, 3, 5, 7);

            Stream.of(evens, odds, primes).flatMap(list -> list.stream()).collect(Collectors.toList()).forEach(System.out::println);

            List<List<String>> names = Arrays.asList(Arrays.asList("Saket", "Trevor"), Arrays.asList("John", "Michael"), Arrays.asList("Shawn", "Franklin"));
            List<String> listOfNames = names.stream().flatMap(list -> list.stream()).collect(Collectors.toList());

            System.out.println(listOfNames);

        } catch (IOException e) {
            System.out.println("Error reading java.txt");
            e.printStackTrace();
        }


    }
}
