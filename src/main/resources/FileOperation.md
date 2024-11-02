In Java, handling multiple files for reading and writing is straightforward, especially with file-handling classes from the `java.io` and `java.nio.file` packages. Here’s an example to demonstrate how you can read from multiple input files and write their contents into a single output file.

### Example Code: Merging Multiple Files into a Single File

Suppose we have multiple text files (`file1.txt`, `file2.txt`, etc.) and we want to combine their content into a single output file (`output.txt`).

```java
import java.io.*;
import java.nio.file.*;
import java.util.List;

public class MultiFileProcessor {

    public static void main(String[] args) {
        // List of input files to read from
        List<String> inputFiles = List.of("file1.txt", "file2.txt", "file3.txt");

        // Output file where the merged content will be written
        String outputFile = "output.txt";

        mergeFiles(inputFiles, outputFile);
    }

    public static void mergeFiles(List<String> inputFiles, String outputFile) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputFile))) {
            for (String inputFile : inputFiles) {
                // Open each file for reading
                try (BufferedReader reader = Files.newBufferedReader(Paths.get(inputFile))) {
                    String line;
                    // Read each line and write it to the output file
                    while ((line = reader.readLine()) != null) {
                        writer.write(line);
                        writer.newLine(); // Add a newline character after each line
                    }
                } catch (IOException e) {
                    System.err.println("Error reading file: " + inputFile + " - " + e.getMessage());
                }
            }
            System.out.println("Files merged successfully into " + outputFile);
        } catch (IOException e) {
            System.err.println("Error writing to output file: " + outputFile + " - " + e.getMessage());
        }
    }
}
```

### Explanation

1. **Define Input and Output Files**: We create a list of input file paths (`file1.txt`, `file2.txt`, etc.) and specify an output file (`output.txt`).

2. **BufferedWriter for Output**: We use a `BufferedWriter` to write to the output file. Using `try-with-resources` ensures the writer is closed automatically after use.

3. **Reading Each Input File**:
   - For each file in `inputFiles`, a `BufferedReader` reads each line.
   - Each line from the input file is written to the output file using the `BufferedWriter`.
   - `newLine()` ensures that each line from each file is written on a new line in the output file.

4. **Error Handling**: Both `try-catch` blocks handle potential `IOException`s, with error messages for any issues with specific files.

### Example Output (contents of `output.txt`)

If `file1.txt` contains:
```
Hello from file 1
```

And `file2.txt` contains:
```
Hello from file 2
```

Then, `output.txt` will have:
```
Hello from file 1
Hello from file 2
```

This example demonstrates reading from multiple files and merging them into a single output file. You can modify the code for other use cases, such as processing each file's content differently before writing it to the output.
