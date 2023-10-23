package org.Java8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FlatMap {
	public static void main(String[] args) {
		try {
			
			//https://stackoverflow.com/questions/64927047/how-to-find-second-highest-salary-in-below-array-list-using-java8-streams
			Stream<String> input = Files.lines(
					Paths.get("D:\\EclipseWorkspace\\JavaLearning\\JavaExamples\\src\\com\\JavaTest\\Java8\\java.txt"));
			
			Stream<String[]> inputStream = input.map(line -> line.split(" "));
			//System.out.println(inputStream);
			
			//Stream<Stream<String>> ss = inputStream.map(array -> Arrays.stream(array)); //Stream<Stream<String>>
			//System.out.println(ss);
			
			Stream<String> streamOfString = inputStream.flatMap(array -> Arrays.stream(array));
			//System.out.println(streamOfString);
			
			//Complete code to count the java word
			long n = Files.lines(Paths.get("D:\\\\EclipseWorkspace\\\\JavaLearning\\\\JavaExamples\\\\src\\\\com\\\\JavaTest\\\\Java8\\\\java.txt")).map(line -> line.split(" ")).flatMap(array -> Arrays.stream(array)).filter(d -> d.equals("java")).count();
			
			System.out.println(n);
			
			Stream.iterate(0, s->s+1).limit(10).forEach(System.out::println);
			
			System.out.println("------------");
			
			IntStream numStream = IntStream.range(10, 15); //Exclusive end boundary
					numStream.forEach(System.out::println);
					
					System.out.println("------------");
			
			IntStream numStreamInclusive = IntStream.rangeClosed(10, 15);
					numStreamInclusive.forEach(System.out::println); //Inclusive both boundary value
					
			
		}

		catch (IOException e) {
			System.out.println("Error reading java.txt");
			e.printStackTrace();
		}
		
		
	}
}
