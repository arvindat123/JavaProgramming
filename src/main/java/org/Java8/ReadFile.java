package org.Java8;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReadFile {
    public static void main(String[] args) throws IOException {
            /* This example uses the Java 8 Files.lines to read the file into a Stream,
            and print it line by line. Furthermore, the Files.lines will helps to auto-close the opened resource (file);
            we do not need to wrap the code with try-with-resources.*/
        // auto-close the resources
        //D:\LearningIntelliJ\AlgorithmsInJava\src\main\java\org\Java8\java.txt
        Path path = Paths.get("D:\\LearningIntelliJ\\AlgorithmsInJava\\src\\main\\java\\org\\Java8\\java.txt");
        Stream<String> lines = Files.lines(path);
        //Stream<String> lines = Files.lines(Paths.get("D:\\EclipseWorkspace\\JavaLearning\\JavaExamples\\src\\com\\JavaTest\\Java8\\java.txt"));

        // does not preserve order
        //lines.forEach(System.out::println);
        List<String> collect = lines.collect(Collectors.toList());
        System.out.println(collect);

        // preserve order
        // lines.forEachOrdered(System.out::println);

        // only for small text file
        //List<String> collect = lines.collect(Collectors.toList()); //IllegalStateException: stream has already been operated upon or closed
        //System.out.println(collect);

       /* For reading in a large text file, and if the order of the line does not matter,
       we can turn on the Stream’s parallel feature to further improve reading speed.*/
        Stream<String> lines1 = Files.lines(path);
        // parallel but not ordered
       /* lines1.parallel().forEach(l -> {
            System.out.println(l);
        });*/

        // parallel and ordered
        lines1.parallel().forEachOrdered(l -> {
            //System.out.println(l);
        });

        /*A common mistake is to convert a large Stream into a List directly, and
        it will throw java.lang.OutOfMemoryError: Java heap space if the Stream size is larger than the running JVM heap size.*/
        // a large text file, for example, 10G

        Stream<String> lines2 = Files.lines(path);

        // java.lang.OutOfMemoryError: Java heap space
        List<String> collect1 = lines2.collect(Collectors.toList());


      /*  This example uses Files.readAllLines to read a file into a List<String>,
        if the file size is larger than the running JVM heap size, it will throw java.lang.OutOfMemoryError: Java heap space.*/
        List<String> lines3 = Files.readAllLines(path,StandardCharsets.UTF_8);
       // lines3.forEach(System.out::println);

        //BufferedReader (Java 1.1)
       /* A classic and old friend, BufferedReader example, works well in reading small and
        large files, and the default buffer size (8k) is large enough for most purposes.*/
        // defaultCharBufferSize = 8192; or 8k
        //ordered
        try (BufferedReader br = new BufferedReader(new FileReader(String.valueOf(path)))) {
            String line;
            while ((line = br.readLine()) != null) {
               // System.out.println("Inside reader");
                System.out.println(line);
            }
        }

        //We can specify the buffer size.
        int bufferSize = 10240; //10k
        try (BufferedReader br = new BufferedReader(new FileReader(String.valueOf(path)), bufferSize)) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        }

        //In Java 8, we can use the new Files.newBufferedReader to create a BufferedReader.
        try (BufferedReader br = Files.newBufferedReader(Paths.get(String.valueOf(path)))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        }
    }

}

/*
        Files.lines, return a Stream (Java 8)
        Files.readString, returns a String (Java 11), max file size 2G.
        Files.readAllBytes, returns a byte[] (Java 7), max file size 2G.
        Files.readAllLines, returns a List<String> (Java 8)
        BufferedReader, a classic old friend (Java 1.1 -> forever)
        Scanner (Java 1.5)

        The new Java 8 Files.lines is working well in reading small or large text files, returns a Stream
         (flexible type and support parallel), auto-close the resources, and has a single line of clean code.
          Stream<String> lines = Files.lines(Paths.get(fileName));
       */
