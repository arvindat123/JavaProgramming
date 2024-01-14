package org.Java8;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.LongSummaryStatistics;

public class ParallelStreamsConcurrency {
    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        int sum = numbers.stream().mapToInt(n -> n).sum(); //serial stream
        System.out.println("sum ->" + sum);

        IntSummaryStatistics longSummaryStatistics = numbers.stream().mapToInt(n -> n).summaryStatistics();
        System.out.println(longSummaryStatistics.toString()); //IntSummaryStatistics{count=10, sum=55, min=1, average=5.500000, max=10}

        int parallelSum = numbers.stream().parallel().mapToInt(n -> n).sum();
        System.out.println("Parallel sum ->" + parallelSum);

        System.out.println(numbers.parallelStream().mapToInt(n -> n).sum());

        numbers.parallelStream().forEach(System.out::println); // Order is not maintained

        System.out.println("------------------------------------------------");

        numbers.parallelStream().forEachOrdered(System.out::println);// Order is maintained


    }

}
