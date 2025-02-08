```java

@PropertySource("classpath:/error_messages.properties")
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

public GlobalExceptionHandler() { super();}

@ExceptionHandler(GekkoAPIValidationException.class)
public ResponseEntity<ErrorMessageDetails> handleAPIValidationException(
		GekkoAPIValidationException gekkoAPIValidationException) {
	ErrorMessageDetails errorResponse = new ErrorMessageDetails(
			DateUtils.getCurrentDateTime(),
			gekkoAPIValidationException.getStatusCode(),
			gekkoAPIValidationException.getMessages(),
			gekkoAPIValidationException.getErrorCode()
	);
	return new ResponseEntity<>(errorResponse, gekkoAPIValidationException.getHttpStatus());
}


}

public class GekkoAPIValidationException extends RuntimeException {	
private static final long serialVersionUID = -4559697804079915339L;
	private final int statusCode;
	private final List<String> messages;
	private final String errorCode;
	private final HttpStatus httpStatus;

	public GekkoAPIValidationException(int statusCode, List<String> messages, String errorCode,
                                       HttpStatus httpStatus) {
		super();
		this.statusCode = statusCode;
		this.messages = messages;
		this.errorCode = errorCode;
		this.httpStatus = httpStatus;
	}
}

public class ErrorMessageDetails {

	public ErrorMessageDetails() { }
	
	private String timestamp;	
	private int statusCode;
	private List<String> messages;
	private String details;

	public ErrorMessageDetails(String currentDateTime, int string, List<String> messages, String details) {
		this.timestamp = currentDateTime;
		this.statusCode = string;
		this.messages = messages;
		this.details = details;
	}
}

```



# Here are some common Java exception interview questions along with brief answers:

### 1. **What is an Exception in Java?**
**Answer**: An exception in Java is an event that disrupts the normal flow of a program's execution. It is an object that is thrown at runtime to indicate the occurrence of an unexpected condition or error.

### 2. **What is the difference between Checked and Unchecked Exceptions?**
**Answer**:
- **Checked Exceptions**: Exceptions that are checked at compile-time. They must be either caught or declared in the method signature using `throws`. Examples include `IOException`, `SQLException`.
- **Unchecked Exceptions**: Exceptions that are not checked at compile-time. They are subclasses of `RuntimeException` and do not need to be explicitly handled. Examples include `NullPointerException`, `ArrayIndexOutOfBoundsException`.

### 3. **What is the base class for all exceptions in Java?**
**Answer**: The base class for all exceptions is `java.lang.Throwable`. It has two main subclasses:
- **`Exception`**: Used for conditions that a reasonable application might want to catch.
- **`Error`**: Indicates serious problems that an application should not attempt to catch (e.g., `OutOfMemoryError`).

### 4. **What are the key differences between `throw` and `throws`?**
**Answer**:
- **`throw`**: Used to explicitly throw an exception in the code. It is used within a method.
- **`throws`**: Used in the method signature to declare that a method might throw an exception, alerting the caller of the method to handle it.

### 5. **What is `finally` block in Java?**
**Answer**: A `finally` block is used to execute important code such as closing resources, regardless of whether an exception is thrown or not. It is always executed after the `try` and `catch` blocks.

### 6. **Can `finally` block be skipped?**
**Answer**: The `finally` block will execute in almost all cases. However, it can be skipped in situations such as:
- The JVM exits during `try` or `catch` execution (e.g., `System.exit()` is called).
- The thread executing the `try` block is interrupted or killed.

### 7. **What is the `try-with-resources` statement?**
**Answer**: The `try-with-resources` statement, introduced in Java 7, is used for automatic resource management. Any resource that implements the `AutoCloseable` or `Closeable` interface is closed automatically at the end of the `try` block. For example:
```java
try (BufferedReader br = new BufferedReader(new FileReader("file.txt"))) {
    // read file
} catch (IOException e) {
    e.printStackTrace();
}
```

### 8. **What is a `custom exception` in Java?**
**Answer**: A custom exception is a user-defined exception class that extends `Exception` or `RuntimeException`. It is used when a specific condition needs to be indicated within an application. For example:
```java
public class CustomException extends Exception {
    public CustomException(String message) {
        super(message);
    }
}
```

### 9. **What are some best practices for handling exceptions in Java?**
**Answer**:
- Use specific exceptions instead of a general `Exception` type.
- Log the exception details for debugging.
- Avoid swallowing exceptions (i.e., empty `catch` blocks).
- Use `finally` or `try-with-resources` to close resources.
- Do not use exceptions for control flow.

### 10. **What is `StackOverflowError` and how is it different from `OutOfMemoryError`?**
**Answer**:
- **`StackOverflowError`**: Occurs when the stack memory limit is exceeded, typically due to deep or infinite recursion.
- **`OutOfMemoryError`**: Occurs when the JVM runs out of heap memory, often due to excessive memory usage by objects.

### 11. **What is `Exception Chaining` in Java?**
**Answer**: Exception chaining is the practice of associating an exception with another (i.e., the original cause). This is done using the `Throwable` constructor that accepts a `Throwable` argument, allowing a stack trace of both exceptions to be preserved:
```java
try {
    // some code
} catch (IOException e) {
    throw new CustomException("Custom exception occurred", e);
}
```

### 12. **Can you explain the `NullPointerException` and how to avoid it?**
**Answer**: A `NullPointerException` occurs when trying to access or modify an object that is `null`. To avoid it:
- Always check for `null` before dereferencing objects.
- Use `Optional` (Java 8+) to handle potentially `null` values.
- Use `Objects.requireNonNull()` to throw an informative `NullPointerException`.

These are common questions and answers related to Java exceptions that could help during an interview.

Here are some common Java exception interview questions with detailed answers:

### 1. **What are exceptions in Java?**

**Answer:**
An exception in Java is an event that disrupts the normal flow of the program’s execution. It is an object that represents an error or an unexpected condition. Exceptions are used to handle errors in a program in a structured way and can be classified into two types:
- **Checked exceptions**: These exceptions are checked at compile-time, like `IOException`, `SQLException`.
- **Unchecked exceptions**: These exceptions occur at runtime and are subclasses of `RuntimeException`, like `NullPointerException`, `ArrayIndexOutOfBoundsException`.

### 2. **What is the difference between checked and unchecked exceptions?**

**Answer:**
- **Checked exceptions**: These must be either caught using a `try-catch` block or declared in the method signature using the `throws` keyword. Examples include `IOException` and `SQLException`.
- **Unchecked exceptions**: These are not required to be caught or declared, as they extend `RuntimeException`. Examples include `NullPointerException` and `ArrayIndexOutOfBoundsException`.

### 3. **What is the `try-catch` block in Java?**

**Answer:**
The `try-catch` block is used to handle exceptions in Java. The `try` block contains the code that might throw an exception, and the `catch` block handles the exception if it occurs.

Example:
```java
try {
    int result = 10 / 0;
} catch (ArithmeticException e) {
    System.out.println("Error: " + e.getMessage());
}
```

### 4. **What is the purpose of the `finally` block?**

**Answer:**
The `finally` block is used to execute code after the `try` block, regardless of whether an exception was thrown or not. It's typically used to release resources such as closing files or database connections.

Example:
```java
try {
    // Code that may throw an exception
} catch (Exception e) {
    // Handle exception
} finally {
    // This block will always execute
    System.out.println("This will always run.");
}
```

### 5. **What is the `throw` keyword in Java?**

**Answer:**
The `throw` keyword is used to explicitly throw an exception from a method or block of code. You can throw both checked and unchecked exceptions using the `throw` keyword.

Example:
```java
public void validateAge(int age) {
    if (age < 18) {
        throw new IllegalArgumentException("Age must be 18 or older");
    }
}
```

### 6. **What is the `throws` keyword in Java?**

**Answer:**
The `throws` keyword is used in a method signature to declare that a method might throw one or more exceptions. It does not handle the exception but passes the responsibility to the calling method.

Example:
```java
public void readFile(String filename) throws IOException {
    // Code that might throw IOException
}
```

### 7. **What is the difference between `throw` and `throws` in Java?**

**Answer:**
- **`throw`**: It is used to explicitly throw an exception from within a method or block.
- **`throws`**: It is used in the method signature to declare that the method may throw one or more exceptions.

### 8. **What is a `NullPointerException`?**

**Answer:**
A `NullPointerException` occurs when the JVM attempts to access a method or field on an object reference that is `null`. It is a common runtime exception and often indicates a programming bug.

Example:
```java
String str = null;
System.out.println(str.length()); // Throws NullPointerException
```

### 9. **What is an `ArrayIndexOutOfBoundsException`?**

**Answer:**
This exception occurs when you try to access an array element with an index that is outside its bounds, either negative or greater than the size of the array.

Example:
```java
int[] arr = new int[5];
arr[10] = 100; // Throws ArrayIndexOutOfBoundsException
```

### 10. **How do you handle multiple exceptions in Java?**

**Answer:**
You can handle multiple exceptions either using multiple `catch` blocks or a multi-catch feature (Java 7 and above).

1. **Multiple catch blocks**:
```java
try {
    // code that may throw exceptions
} catch (IOException e) {
    // Handle IOException
} catch (SQLException e) {
    // Handle SQLException
}
```

2. **Multi-catch (Java 7 and above)**:
```java
try {
    // code that may throw exceptions
} catch (IOException | SQLException e) {
    // Handle both IOException and SQLException
}
```

### 11. **What is the `StackTrace` in Java?**

**Answer:**
The `StackTrace` is a list of method calls that the JVM was executing when an exception was thrown. It provides detailed information about the exception, such as the class, method, and line number where the exception occurred.

You can print the stack trace using `e.printStackTrace()` in a `catch` block.

Example:
```java
try {
    // Code that may throw exception
} catch (Exception e) {
    e.printStackTrace();
}
```

### 12. **What is the difference between `Exception` and `Error` in Java?**

**Answer:**
- **Exception**: These are conditions that a program can recover from. They are usually the result of problems that occur during the program's execution and can be caught and handled. Examples include `IOException`, `SQLException`.
- **Error**: These are serious problems that cannot typically be recovered from, such as `OutOfMemoryError`, `StackOverflowError`. They represent issues related to the JVM and are not meant to be caught by the application code.

### 13. **What is the `IllegalStateException` in Java?**

**Answer:**
An `IllegalStateException` is thrown when a method is called at an illegal or inappropriate time, i.e., when the method’s environment is not in the correct state.

Example:
```java
List<String> list = new ArrayList<>();
Iterator<String> iterator = list.iterator();
iterator.remove(); // Throws IllegalStateException because no element has been iterated
```

These questions are common in Java interviews related to exceptions and error handling, and understanding the different types of exceptions and how to use them effectively is crucial for a Java developer role.

Here are some tricky Java exception interview questions along with detailed answers to help you prepare:

### 1. **Can a `try` block exist without a `catch` block?**

**Answer:**
Yes, a `try` block can exist without a `catch` block if it is followed by a `finally` block. The `finally` block ensures that the code within it will always execute, regardless of whether an exception was thrown or not.

Example:
```java
try {
    // Code that may throw an exception
} finally {
    // This block will always run, even if an exception occurs
    System.out.println("Finally block executed.");
}
```

### 2. **What happens if an exception is thrown in a `finally` block?**

**Answer:**
If an exception is thrown inside a `finally` block, it will override any exception that was thrown in the `try` block. This can lead to the loss of the original exception if it was not handled.

Example:
```java
try {
    throw new RuntimeException("Exception in try block");
} catch (Exception e) {
    System.out.println("Caught exception: " + e.getMessage());
} finally {
    throw new RuntimeException("Exception in finally block");
}
// Output: Only "Exception in finally block" will be thrown, overriding the first exception.
```

### 3. **Can you catch more than one type of exception with a single `catch` block?**

**Answer:**
Yes, starting from Java 7, you can catch multiple exceptions in a single `catch` block using the pipe (`|`) operator. This is known as multi-catch.

Example:
```java
try {
    // Code that may throw IOException or SQLException
} catch (IOException | SQLException e) {
    System.out.println("Caught IOException or SQLException: " + e.getMessage());
}
```

### 4. **What happens if a `return` statement is encountered in a `try` block? Will the `finally` block execute?**

**Answer:**
Yes, the `finally` block will still execute even if a `return` statement is present in the `try` or `catch` block. The `finally` block runs before the method returns.

Example:
```java
public int testMethod() {
    try {
        return 1;
    } finally {
        System.out.println("Finally block executed");
    }
}
// Output: "Finally block executed" will print, and the method will return 1.
```

### 5. **Can you catch and rethrow an exception in Java?**

**Answer:**
Yes, you can catch an exception and rethrow it. This is useful if you want to log the exception or add more context before rethrowing it. From Java 7 onwards, you can also rethrow exceptions without declaring them explicitly if they are not more general than the caught type.

Example:
```java
try {
    // Code that may throw an exception
} catch (IOException e) {
    System.out.println("Logging exception: " + e.getMessage());
    throw e; // Rethrowing the exception
}
```

### 6. **Can a `catch` block catch multiple exceptions but handle them differently?**

**Answer:**
Not directly with a single `catch` block. To handle them differently, you need to use separate `catch` blocks for each type.

Example:
```java
try {
    // Code that may throw exceptions
} catch (IOException e) {
    System.out.println("Handle IOException differently");
} catch (SQLException e) {
    System.out.println("Handle SQLException differently");
}
```

### 7. **What happens if an exception is thrown during the exception handling itself (inside a `catch` block)?**

**Answer:**
If an exception is thrown within a `catch` block, it will propagate up the call stack, unless it is caught again by another `try-catch` higher up in the call stack. The `finally` block (if present) will still execute.

Example:
```java
try {
    throw new RuntimeException("Initial exception");
} catch (RuntimeException e) {
    System.out.println("Caught initial exception");
    throw new RuntimeException("Exception thrown in catch block"); // This will propagate
} finally {
    System.out.println("Finally block executed");
}
// Output: "Caught initial exception", "Finally block executed", and the new exception is thrown.
```

### 8. **Is it a good practice to catch the `Exception` class?**

**Answer:**
Catching the `Exception` class is generally not a good practice because it catches all exceptions, including those you might not want to handle, such as unchecked exceptions like `NullPointerException` or `ArrayIndexOutOfBoundsException`. This can lead to catching unexpected exceptions and potentially masking real issues.

It’s better to catch specific exceptions to handle different cases appropriately.

### 9. **Can we have `try`, `catch`, and `finally` blocks without any code in them?**

**Answer:**
Yes, syntactically you can have empty `try`, `catch`, and `finally` blocks, but it is not good practice. An empty `catch` block may lead to "swallowed" exceptions that go unnoticed, making debugging difficult.

Example (legal but discouraged):
```java
try {
    // Code that might throw an exception
} catch (Exception e) {
    // Empty block (bad practice)
} finally {
    // Empty block (bad practice)
}
```

### 10. **What is the difference between `throw e` and `throw new e` inside a `catch` block?**

**Answer:**
- **`throw e`**: This rethrows the original exception, preserving the original stack trace.
- **`throw new e`**: This creates a new exception, resetting the stack trace to the current line. This can result in losing the original stack trace and make debugging harder.

Example:
```java
try {
    throw new IOException("Original exception");
} catch (IOException e) {
    throw e; // Preserves stack trace
    // throw new IOException("Rethrown exception"); // Resets stack trace
}
```

These tricky questions test your deep understanding of how exception handling works in Java and your ability to use it effectively and correctly in complex scenarios.
The `finally` block in Java is designed to execute regardless of whether an exception is thrown or not. However, there are a few rare cases when the `finally` block will **not** execute:

### 1. **System.exit() Method**
If `System.exit()` is called in the `try` or `catch` block, the JVM will terminate immediately, and the `finally` block will not execute.

**Example:**
```java
try {
    System.out.println("In try block");
    System.exit(0); // Terminates the JVM
} catch (Exception e) {
    System.out.println("In catch block");
} finally {
    System.out.println("In finally block"); // Will not execute
}
```

### 2. **JVM Crash or Fatal Error**
If the JVM crashes or encounters a fatal error (e.g., `OutOfMemoryError`, `StackOverflowError`), the `finally` block may not execute because the JVM is no longer able to continue running.

**Example:**
```java
try {
    // Simulating a critical error that causes JVM to crash (rare)
} finally {
    System.out.println("In finally block");
}
```

### 3. **Infinite Loop or Endless Block in `try` or `catch`**
If there is an infinite loop or an operation that does not complete within the `try` or `catch` block, the `finally` block will never be reached.

**Example:**
```java
try {
    while (true) {
        // Infinite loop
    }
} finally {
    System.out.println("In finally block"); // Will not execute
}
```

### 4. **Thread Termination (e.g., `Thread.stop()`)**
If the thread executing the `try` block is forcibly terminated by calling `Thread.stop()`, the `finally` block may not execute because the thread is killed before reaching that block. This method is deprecated and considered unsafe for this reason.

**Example:**
```java
Thread t = new Thread(() -> {
    try {
        System.out.println("In try block");
        Thread.currentThread().stop(); // Unsafe and deprecated
    } finally {
        System.out.println("In finally block"); // Will not execute
    }
});
t.start();
```

### 5. **Power Failure or Hardware Shutdown**
If the system running the JVM experiences a power failure, or if the machine is shut down abruptly (e.g., a power cut or forced shutdown), the `finally` block will not be executed.

### Conclusion
While the `finally` block is a reliable way to ensure code execution in most cases, there are scenarios involving extreme conditions or JVM-level interruptions where it may not run.

---

### Exception Hierarchy in Java

In Java, exceptions are objects of classes that inherit from the `Throwable` class. The `Throwable` class is the root of the exception hierarchy and has two main subclasses:

1. **`Error`**: Represents serious problems that an application should not try to handle (e.g., `OutOfMemoryError`, `StackOverflowError`).
2. **`Exception`**: Represents conditions that an application might want to handle.

#### Exception Hierarchy Diagram

```
Throwable
   ├── Error
   │     ├── OutOfMemoryError
   │     ├── StackOverflowError
   │     └── ...
   └── Exception
         ├── RuntimeException
         │       ├── NullPointerException
         │       ├── ArrayIndexOutOfBoundsException
         │       ├── ArithmeticException
         │       └── ...
         └── IOException
                 ├── FileNotFoundException
                 ├── EOFException
                 └── ...
```

### Key Points About Exception Hierarchy

1. **`Error`**:
   - Indicates serious issues that the JVM cannot recover from.
   - Examples: `OutOfMemoryError`, `StackOverflowError`.

2. **`Exception`**:
   - Represents errors that are expected and can be handled by the application.

3. **Checked Exceptions**:
   - Exceptions that must be declared in the `throws` clause or handled using a `try-catch` block.
   - Examples: `IOException`, `SQLException`.

4. **Unchecked Exceptions**:
   - Subclasses of `RuntimeException` that do not need to be declared in the `throws` clause.
   - Examples: `NullPointerException`, `ArithmeticException`.

---

### Example with Detailed Explanation

#### 1. Custom Exception Hierarchy
Let's create a custom exception hierarchy to understand how to extend the exception classes.

```java
// Base Custom Exception
class ApplicationException extends Exception {
    public ApplicationException(String message) {
        super(message);
    }
}

// Derived Custom Exception (Checked)
class InvalidInputException extends ApplicationException {
    public InvalidInputException(String message) {
        super(message);
    }
}

// Derived Custom Exception (Unchecked)
class DataProcessingException extends RuntimeException {
    public DataProcessingException(String message) {
        super(message);
    }
}
```

---

#### 2. Handling Checked Exception
Checked exceptions must be handled or declared in the `throws` clause.

```java
public class CheckedExceptionExample {
    public static void main(String[] args) {
        try {
            validateInput(-1); // Call method that throws a checked exception
        } catch (InvalidInputException e) {
            System.out.println("Caught checked exception: " + e.getMessage());
        }
    }

    public static void validateInput(int input) throws InvalidInputException {
        if (input < 0) {
            throw new InvalidInputException("Input must be non-negative.");
        }
    }
}
```

**Output:**
```
Caught checked exception: Input must be non-negative.
```

---

#### 3. Handling Unchecked Exception
Unchecked exceptions are typically programming bugs or unexpected situations.

```java
public class UncheckedExceptionExample {
    public static void main(String[] args) {
        try {
            processData(null); // Call method that throws an unchecked exception
        } catch (DataProcessingException e) {
            System.out.println("Caught unchecked exception: " + e.getMessage());
        }
    }

    public static void processData(String data) {
        if (data == null) {
            throw new DataProcessingException("Data cannot be null.");
        }
    }
}
```

**Output:**
```
Caught unchecked exception: Data cannot be null.
```

---

### Comparison Between Checked and Unchecked Exceptions

| Aspect              | Checked Exceptions                      | Unchecked Exceptions                   |
|---------------------|------------------------------------------|----------------------------------------|
| Class               | Subclasses of `Exception` (except `RuntimeException`) | Subclasses of `RuntimeException`       |
| Declaration         | Must be declared in the `throws` clause | No need to declare in the `throws` clause |
| Compile-time Check  | Enforced by the compiler                | Not enforced by the compiler           |
| Examples            | `IOException`, `SQLException`          | `NullPointerException`, `ArithmeticException` |

---

### Exception Propagation
- **Checked Exceptions**: Propagate up the call stack and must be handled or declared at each level.
- **Unchecked Exceptions**: Automatically propagate without requiring explicit handling.

---

### Best Practices

1. **Use Checked Exceptions** for recoverable conditions (e.g., invalid input, file not found).
2. **Use Unchecked Exceptions** for programming errors (e.g., null references).
3. **Wrap Exceptions**: Use custom exceptions to abstract third-party exceptions.
4. **Log Exceptions**: Always log exceptions for debugging.
5. **Avoid Catch-All**: Avoid `catch (Exception e)` unless you rethrow or handle appropriately.

Let me know if you'd like further examples or have any specific questions!
