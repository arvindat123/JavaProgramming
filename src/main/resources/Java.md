---
https://docs.oracle.com/en/java/javase/11/language/java-language-changes-release.html#GUID-6459681C-6881-45D8-B0DB-395D1BD6DB9B
---

Here’s a curated list of **Java 8 interview questions** for experienced professionals, focusing on key concepts, practical applications, and advanced topics:

---

### **1. Core Features of Java 8**
1. What are the new features introduced in Java 8?
2. How does the `default` method in interfaces work? Why was it introduced?
3. What is the difference between `default` methods and `abstract` methods in interfaces?
4. Can a class implement two interfaces with the same default method? How would you resolve the conflict?

---

### **2. Functional Programming and Lambda Expressions**
5. What are lambda expressions? How do they differ from anonymous classes?
6. How is the type of a lambda expression determined?
7. Write a lambda expression to sort a list of strings by their length.
8. How do you use effectively final variables inside lambda expressions?

---

### **3. Functional Interfaces**
9. What are functional interfaces in Java 8? Give examples.
10. Can you create your own functional interface? Write a small example.
11. How does the `@FunctionalInterface` annotation help in Java 8?

---

### **4. Streams API**
12. What is the purpose of the Streams API in Java 8?
13. Explain the difference between `intermediate` and `terminal` operations in streams.
14. How would you filter a list of integers to include only even numbers using streams?
15. What is the difference between `map()` and `flatMap()`?
16. Explain how parallel streams work. When would you use them?
17. How would you count the number of occurrences of each word in a list using streams?

---

### **5. Optional Class**
18. What is `Optional` in Java 8? Why is it used?
19. How would you handle a `null` value with `Optional`?
20. What is the difference between `Optional.of()`, `Optional.ofNullable()`, and `Optional.empty()`?
21. How would you use `orElse()`, `orElseGet()`, and `orElseThrow()` in Optional?

---

### **6. Date and Time API**
22. What is the new Date and Time API introduced in Java 8?
23. How do `LocalDate`, `LocalTime`, and `LocalDateTime` differ?
24. How would you add 7 days to the current date using the new API?
25. What is the difference between `Period` and `Duration`?

---

### **7. Collectors**
26. What is the role of the `Collectors` utility class?
27. How would you use `Collectors.groupingBy()` to group elements in a list?
28. Explain the difference between `Collectors.toList()`, `toSet()`, and `toMap()`.
29. How does `partitioningBy()` work in Collectors?

---

### **8. Method References**
30. What are method references in Java 8? How are they different from lambda expressions?
31. Explain the types of method references with examples.
32. When would you use a method reference instead of a lambda?

---

### **9. Concurrency Enhancements**
33. What are the new concurrency enhancements introduced in Java 8?
34. How does `CompletableFuture` work? What are its key methods?
35. Write a code snippet to demonstrate the use of `CompletableFuture.supplyAsync()`.

---

### **10. Miscellaneous**
36. How do you use the `forEach()` method in Java 8?
37. Explain the difference between `peek()` and `map()` in streams.
38. What are the key changes in Java 8 regarding garbage collection?
39. How does the `Predicate` interface work? Provide an example.
40. What is the difference between `findAny()` and `findFirst()` in streams?

---

### **Scenario-Based Questions**
41. How would you use streams to remove duplicates from a list?
42. Write a program to find the second highest number in a list using streams.
43. How would you handle exceptions inside a stream pipeline?
44. How do you sort a list of custom objects by multiple fields using streams?
45. Implement a caching mechanism using `Optional` to avoid repeated computations.

---

### **What are Lambda Expressions?**

A **lambda expression** in Java 8 is a concise way to represent a block of code (or behavior) that can be passed around and executed. It’s a feature that enables functional programming by treating functions as first-class citizens.

---

### **Syntax of a Lambda Expression**
```java
(parameters) -> { body }
```
- **Parameters:** The inputs to the lambda expression.  
- **Arrow (->):** Separates parameters from the body of the expression.  
- **Body:** The logic or implementation of the lambda.

#### **Examples:**
1. **Without Parameters:**
   ```java
   () -> System.out.println("Hello, World!");
   ```
2. **Single Parameter:**
   ```java
   name -> System.out.println("Hello, " + name);
   ```
3. **Multiple Parameters:**
   ```java
   (a, b) -> a + b;
   ```
4. **Block Body with Return Statement:**
   ```java
   (a, b) -> {
       int sum = a + b;
       return sum;
   };
   ```

---

### **How Lambda Expressions Differ from Anonymous Classes**

| **Aspect**               | **Lambda Expression**                                      | **Anonymous Class**                                      |
|--------------------------|-----------------------------------------------------------|---------------------------------------------------------|
| **Syntax**               | Concise and readable                                      | Verbose, with class definition and boilerplate code     |
| **Type Inference**       | Compiler infers the type from the context                 | Explicitly defines the type in the class               |
| **Performance**          | More efficient (no additional class file created)         | Generates an additional class file at runtime          |
| **Usage**                | Typically used with functional interfaces                 | Can implement any interface or extend a class          |
| **Access to `this`**     | Refers to the enclosing scope                             | Refers to the instance of the anonymous class          |
| **Reusability**          | Cannot have a named implementation                        | Can be reused if declared as an object                 |

---

### **Code Example: Lambda Expression vs Anonymous Class**

#### **Using Anonymous Class:**
```java
import java.util.Comparator;

public class AnonymousClassExample {
    public static void main(String[] args) {
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return a - b;
            }
        };

        int result = comparator.compare(10, 20);
        System.out.println("Result using anonymous class: " + result);
    }
}
```

---

#### **Using Lambda Expression:**
```java
import java.util.Comparator;

public class LambdaExample {
    public static void main(String[] args) {
        Comparator<Integer> comparator = (a, b) -> a - b;

        int result = comparator.compare(10, 20);
        System.out.println("Result using lambda expression: " + result);
    }
}
```

---

### **Output for Both Examples:**
```
Result: -10
```

---

### **Key Differences Highlighted in the Examples:**
1. **Verbosity:** The anonymous class requires more lines of code (explicitly overriding `compare`), while the lambda is a single line.
2. **Type Inference:** The lambda infers `Integer` from the context, whereas the anonymous class explicitly declares it.
3. **Performance:** The lambda does not generate a new `.class` file, making it more memory-efficient.

---

### **When to Use Lambda Expressions:**
- When implementing functional interfaces (interfaces with a single abstract method).
- When simplicity and readability are priorities.

---

### **When to Use Anonymous Classes:**
- When implementing multiple methods of an interface.
- When you need to access `this` as the instance of the anonymous class.
- When extending a class and overriding methods.

Would you like more examples or further clarification on functional interfaces and their relation to lambda expressions?

---
---

### **How Does the `default` Method in Interfaces Work?**

A `default` method in an interface is a method with a body, introduced in **Java 8**. It allows an interface to provide a **default implementation** for a method, enabling backward compatibility and reducing boilerplate code.

#### **Key Points:**
1. A `default` method is defined in an interface using the `default` keyword.
2. Classes implementing the interface can:
   - Use the default implementation as-is.
   - Override the `default` method with a custom implementation.

---

### **Why Was `default` Method Introduced?**

1. **Backward Compatibility:**  
   - Before Java 8, if a new method was added to an interface, all implementing classes had to implement it.  
   - With `default` methods, new methods can be added to interfaces without breaking existing implementations.

2. **Multiple Inheritance of Behavior:**  
   - Provides a way for interfaces to include functionality without needing abstract classes.

3. **Streamlining API Evolution:**  
   - Java collections and other libraries use `default` methods to introduce new functionalities (e.g., `forEach` in `Iterable`).

---

### **Example: Default Method in Interface**

```java
interface Vehicle {
    // Abstract method
    void start();

    // Default method
    default void stop() {
        System.out.println("The vehicle is stopping.");
    }
}

class Car implements Vehicle {
    @Override
    public void start() {
        System.out.println("Car is starting.");
    }

    // Uses the default stop() implementation from Vehicle
}

class Bike implements Vehicle {
    @Override
    public void start() {
        System.out.println("Bike is starting.");
    }

    // Overrides the default stop() method
    @Override
    public void stop() {
        System.out.println("Bike is stopping in its own way.");
    }
}

public class DefaultMethodDemo {
    public static void main(String[] args) {
        Vehicle car = new Car();
        car.start();
        car.stop();

        Vehicle bike = new Bike();
        bike.start();
        bike.stop();
    }
}
```

---

### **Output:**
```
Car is starting.
The vehicle is stopping.
Bike is starting.
Bike is stopping in its own way.
```

---

### **Explanation of the Example:**
1. The `Vehicle` interface has:
   - An abstract method `start()` that must be implemented by all implementing classes.
   - A `default` method `stop()` with a predefined implementation.

2. The `Car` class:
   - Implements the `start()` method.
   - Uses the default implementation of `stop()` from the `Vehicle` interface.

3. The `Bike` class:
   - Implements the `start()` method.
   - Overrides the `stop()` method to provide a custom implementation.

---

### **Conflict Resolution with Multiple Interfaces**

If a class implements multiple interfaces that provide conflicting `default` methods, the class must explicitly resolve the conflict.

```java
interface A {
    default void greet() {
        System.out.println("Hello from A");
    }
}

interface B {
    default void greet() {
        System.out.println("Hello from B");
    }
}

class C implements A, B {
    @Override
    public void greet() {
        // Resolve the conflict explicitly
        A.super.greet(); // or B.super.greet()
        System.out.println("Hello from C");
    }
}

public class ConflictResolution {
    public static void main(String[] args) {
        C obj = new C();
        obj.greet();
    }
}
```

#### **Output:**
```
Hello from A
Hello from C
```

---

Would you like to explore another example, such as using default methods in the Java Collections API?


---

### **What is an Immutable Class in Java?**

An **immutable class** is a class whose objects cannot be modified after they are created. Once an object is constructed, its state (data) cannot be changed. Immutable classes are widely used in Java, such as `String`, `Integer`, and `BigDecimal`.

---

### **Characteristics of an Immutable Class**

1. **Final Class**: The class should be declared as `final` to prevent subclassing, which could compromise immutability.
2. **Final Fields**: All fields should be declared as `private` and `final` to ensure they cannot be modified after initialization.
3. **No Setter Methods**: The class should not have any methods to modify the state of the object.
4. **Initialize Fields in Constructor**: All fields must be initialized via the constructor.
5. **Defensive Copies**: If a field is mutable (like a `Date` or `List`), return a copy instead of the original object to prevent external modification.

---

### **Steps to Create an Immutable Class in Java**

1. Declare the class as `final`.
2. Declare all instance variables as `private` and `final`.
3. Initialize all fields through a constructor.
4. Do not provide setters.
5. Provide getters that return a copy of mutable fields (if any).
6. Ensure that any mutable objects passed to the constructor are deeply copied to prevent external references.

---

### **Example of an Immutable Class**

```java
import java.util.Date;

public final class ImmutableClass {
    private final String name;
    private final int age;
    private final Date birthDate; // Mutable field

    // Constructor
    public ImmutableClass(String name, int age, Date birthDate) {
        this.name = name;
        this.age = age;

        // Defensive copy of mutable object
        this.birthDate = new Date(birthDate.getTime());
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Getter for age
    public int getAge() {
        return age;
    }

    // Getter for birthDate (return a copy)
    public Date getBirthDate() {
        return new Date(birthDate.getTime());
    }

    public static void main(String[] args) {
        Date birthDate = new Date();
        ImmutableClass person = new ImmutableClass("John", 25, birthDate);

        System.out.println("Name: " + person.getName());
        System.out.println("Age: " + person.getAge());
        System.out.println("BirthDate: " + person.getBirthDate());

        // Attempt to modify birthDate
        birthDate.setTime(0);
        System.out.println("Modified BirthDate: " + person.getBirthDate()); // Original date remains unchanged
    }
}
```

---

### **Explanation of the Example**

1. **`final`**** Keyword**:

   - The class is declared as `final` to prevent inheritance.
   - All instance variables (`name`, `age`, and `birthDate`) are marked as `final`.

2. **Defensive Copy**:

   - The `birthDate` field is mutable (`Date`), so a defensive copy is created in the constructor and in the getter.

3. **No Setters**:

   - No setter methods are provided, ensuring the object's state cannot be changed.

4. **Immutability Guaranteed**:

   - External attempts to modify the `birthDate` object do not affect the internal state of the `ImmutableClass` object.

---

### **Advantages of Immutable Classes**

1. **Thread-Safety**: Immutable objects are inherently thread-safe as their state cannot change.
2. **Cache-Friendly**: Can be safely shared and reused without copying, reducing memory usage.
3. **Simplified Testing and Debugging**: Since their state cannot change, bugs related to state modification are eliminated.

---

### **Common Mistakes to Avoid**

1. **Allowing Direct Access to Mutable Fields**:

   ```java
   public Date getBirthDate() {
       return birthDate; // Exposes internal state
   }
   ```

   **Solution**: Return a defensive copy.

2. **Not Marking the Class as ****`final`**:
   If the class is not `final`, a subclass can override its behavior and compromise immutability.

3. **Not Copying Mutable Parameters in the Constructor**:

   ```java
   this.birthDate = birthDate; // Retains reference to the mutable object
   ```

   **Solution**: Use `new Date(birthDate.getTime())` for defensive copying.

---

### **Real-Life Examples**

1. **`String`**** Class**:
   The `String` class in Java is immutable. Any operation that modifies a `String` creates a new instance rather than changing the existing one.

2. **Custom Immutable Class for Configuration**:
   Immutable objects are commonly used for configuration or settings in applications where the state should remain constant after initialization.

By following these principles, you can create robust, immutable classes in Java that are easy to use and maintain.


---
---

### **List of Java Constructor Interview Questions with Detailed Answers and Examples**

---

### **1. What is a Constructor in Java?**
**Answer:**
A constructor is a special method in Java used to initialize an object when it is created. It has the same name as the class and no return type (not even `void`).

**Example:**
```java
public class Example {
    private String message;

    // Constructor
    public Example(String message) {
        this.message = message;
    }

    public void printMessage() {
        System.out.println("Message: " + message);
    }

    public static void main(String[] args) {
        Example example = new Example("Hello, Constructor!");
        example.printMessage(); // Output: Message: Hello, Constructor!
    }
}
```

---

### **2. What are the Types of Constructors in Java?**
**Answer:**
There are two types of constructors:
1. **Default Constructor**: A no-argument constructor provided by Java if no constructors are explicitly defined.
2. **Parameterized Constructor**: A constructor with parameters to initialize objects with specific values.

**Example:**
```java
class Example {
    private int number;

    // Default Constructor
    public Example() {
        this.number = 0;
    }

    // Parameterized Constructor
    public Example(int number) {
        this.number = number;
    }

    public void display() {
        System.out.println("Number: " + number);
    }
}

public class Main {
    public static void main(String[] args) {
        Example defaultExample = new Example();
        Example parameterizedExample = new Example(10);

        defaultExample.display();         // Output: Number: 0
        parameterizedExample.display();  // Output: Number: 10
    }
}
```

---

### **3. What is the Difference Between a Constructor and a Method?**
**Answer:**

| Feature               | Constructor                         | Method                                |
|-----------------------|-------------------------------------|---------------------------------------|
| Name                  | Same as the class name             | Can have any name                     |
| Return Type           | No return type (not even `void`)   | Must have a return type               |
| Purpose               | Initialize an object               | Perform a task                        |
| Invocation            | Called automatically during object creation | Called explicitly                     |

**Example:**
```java
class Example {
    private String text;

    // Constructor
    public Example(String text) {
        this.text = text;
    }

    // Method
    public void display() {
        System.out.println("Text: " + text);
    }
}

public class Main {
    public static void main(String[] args) {
        Example example = new Example("Hello");
        example.display(); // Output: Text: Hello
    }
}
```

---

### **4. Can a Constructor be Overloaded?**
**Answer:**
Yes, constructors can be overloaded by defining multiple constructors with different parameter lists.

**Example:**
```java
class OverloadExample {
    private int number;

    // Default Constructor
    public OverloadExample() {
        this.number = 0;
    }

    // Parameterized Constructor
    public OverloadExample(int number) {
        this.number = number;
    }

    public void display() {
        System.out.println("Number: " + number);
    }
}

public class Main {
    public static void main(String[] args) {
        OverloadExample obj1 = new OverloadExample();
        OverloadExample obj2 = new OverloadExample(42);

        obj1.display(); // Output: Number: 0
        obj2.display(); // Output: Number: 42
    }
}
```

---

### **5. Can a Constructor Call Another Constructor in the Same Class?**
**Answer:**
Yes, a constructor can call another constructor using the `this()` keyword. This is known as **constructor chaining**.

**Example:**
```java
class ChainingExample {
    private int number;

    // Constructor 1
    public ChainingExample() {
        this(10); // Calls Constructor 2
    }

    // Constructor 2
    public ChainingExample(int number) {
        this.number = number;
    }

    public void display() {
        System.out.println("Number: " + number);
    }
}

public class Main {
    public static void main(String[] args) {
        ChainingExample obj = new ChainingExample();
        obj.display(); // Output: Number: 10
    }
}
```

---

### **6. Can a Constructor be Private in Java?**
**Answer:**
Yes, a constructor can be private. This is typically used in the **Singleton Design Pattern** to restrict object creation from outside the class.

**Example:**
```java
class Singleton {
    private static Singleton instance;

    // Private Constructor
    private Singleton() {}

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}

public class Main {
    public static void main(String[] args) {
        Singleton obj1 = Singleton.getInstance();
        Singleton obj2 = Singleton.getInstance();

        System.out.println(obj1 == obj2); // Output: true
    }
}
```

---

### **7. Can a Constructor Have a Return Statement?**
**Answer:**
A constructor cannot have a return type (not even `void`), but it can use the `return` keyword to exit early.

**Example:**
```java
class Example {
    public Example(String text) {
        if (text == null || text.isEmpty()) {
            System.out.println("Invalid input. Exiting constructor.");
            return;
        }
        System.out.println("Text: " + text);
    }
}

public class Main {
    public static void main(String[] args) {
        new Example("");        // Output: Invalid input. Exiting constructor.
        new Example("Hello!");  // Output: Text: Hello!
    }
}
```

---

### **8. What Happens if a Class Does Not Have a Constructor?**
**Answer:**
If a class does not define any constructor, Java provides a **default constructor**. This constructor is parameterless and initializes instance variables to their default values.

**Example:**
```java
class DefaultConstructorExample {
    int number;
    String text;
}

public class Main {
    public static void main(String[] args) {
        DefaultConstructorExample obj = new DefaultConstructorExample();
        System.out.println("Number: " + obj.number); // Output: Number: 0
        System.out.println("Text: " + obj.text);     // Output: Text: null
    }
}
```

---

### **9. Can a Constructor Throw an Exception?**
**Answer:**
Yes, a constructor can throw exceptions using the `throws` keyword.

**Example:**
```java
class ExceptionExample {
    public ExceptionExample(int number) throws Exception {
        if (number < 0) {
            throw new Exception("Number cannot be negative");
        }
        System.out.println("Number: " + number);
    }
}

public class Main {
    public static void main(String[] args) {
        try {
            new ExceptionExample(-1); // Throws Exception
        } catch (Exception e) {
            System.out.println(e.getMessage()); // Output: Number cannot be negative
        }
    }
}
```

---

### **10. Can You Use `super()` and `this()` Together in a Constructor?**
**Answer:**
No, `super()` and `this()` cannot be used together in the same constructor. They must be the first statement, and only one of them can be used.

**Example:**
```java
class Parent {
    public Parent() {
        System.out.println("Parent Constructor");
    }
}

class Child extends Parent {
    public Child() {
        super(); // Calls Parent Constructor
        System.out.println("Child Constructor");
    }
}

public class Main {
    public static void main(String[] args) {
        new Child();
        // Output:
        // Parent Constructor
        // Child Constructor
    }
}
```

---

These questions cover the most common aspects of Java constructors and provide practical examples to illustrate their usage.
---
---

A **memory leak** occurs when an application fails to release memory that is no longer needed, causing the application to consume increasing amounts of memory over time. In a monolith application, memory leaks can lead to **performance degradation**, **high CPU utilization (due to frequent garbage collection)**, and eventually **application crashes** due to `OutOfMemoryError`.

---

### **Common Causes of Memory Leaks**
1. **Unreleased Object References**:
   - Objects are held in memory due to lingering references, even though they are no longer needed.
   - **Example**: Adding objects to a collection (e.g., `List` or `Map`) but never removing them.

2. **Static Variables**:
   - Static variables have a lifecycle tied to the application's lifespan and may hold objects unnecessarily.
   - **Example**: A `static Map` storing data that grows indefinitely.

3. **Listener or Callback References**:
   - Event listeners or callbacks are registered but never unregistered, causing objects to stay in memory.
   - **Example**: Registering listeners in GUIs or event-driven frameworks without proper cleanup.

4. **Thread Local Variables**:
   - Improper use of `ThreadLocal` variables can cause memory to linger beyond the intended scope.
   - **Example**: Thread-local data not cleared after thread execution.

5. **Caching Without Eviction**:
   - Caching strategies that do not remove stale or unused entries.
   - **Example**: A cache (like `HashMap`) growing without size limits.

6. **Custom Class Loaders**:
   - Improper handling of class loaders can cause memory leaks, especially in applications that reload modules (e.g., web applications in servlet containers).
   - **Example**: Retaining references to objects from the previous class loader after redeployment.

7. **Poorly Managed Collections**:
   - Collections that grow indefinitely without cleanup.
   - **Example**: Accumulating logs or temporary data in an unbounded collection.

8. **Finalizer or Shutdown Hooks**:
   - Objects waiting for finalization or shutdown hooks can stay in memory longer than necessary.
   - **Example**: Using `finalize()` improperly instead of explicit resource cleanup.

9. **Improper Use of Weak/Soft References**:
   - Weak or soft references not managed properly, leading to unintentional retention of objects.
   - **Example**: Misusing `WeakHashMap` without understanding its behavior.

---

### **How to Detect Memory Leaks**
1. **Monitoring Tools**:
   - Use tools like **JVM monitoring tools** (VisualVM, JConsole) or APM tools (AppDynamics, Dynatrace) to track memory usage over time.

2. **Heap Dumps**:
   - Capture heap dumps using tools like `jmap` or built-in profilers and analyze them with tools like Eclipse MAT (Memory Analyzer Tool).

3. **Garbage Collection Logs**:
   - Enable and analyze GC logs to check for frequent garbage collections and excessive memory usage.

4. **Profilers**:
   - Use profilers (e.g., YourKit, JProfiler) to monitor object retention and identify the source of leaks.

---

### **How to Fix and Prevent Memory Leaks**
1. **Release References**:
   - Ensure unused objects are dereferenced.
   - **Example**: Remove entries from collections like `Map` or `List` when they are no longer needed.

2. **Use Weak or Soft References**:
   - Use `WeakReference` or `SoftReference` for objects that can be garbage collected when not strongly referenced.

3. **Unregister Listeners**:
   - Properly unregister listeners or callbacks when they are no longer needed.
   - **Example**: `eventSource.removeListener(listener)`.

4. **Use Thread Pools Properly**:
   - Avoid creating too many threads or improperly handling `ThreadLocal` variables. Clear thread-local values explicitly after use.

5. **Optimize Caching**:
   - Use cache libraries like **Guava Cache** or **Caffeine** with eviction policies.
   - Set size limits and expiration times for cache entries.

6. **Static Variables**:
   - Be cautious with `static` variables. Use them sparingly and only when necessary.

7. **Avoid Finalize**:
   - Do not rely on `finalize()` for resource cleanup; use `try-with-resources` or explicit cleanup methods.

8. **Class Loader Management**:
   - Avoid holding references to objects loaded by previous class loaders when reloading modules.

9. **Use Tools for Analysis**:
   - Integrate heap dump analysis into the debugging process to proactively identify leaks.

---

### **Example of a Memory Leak in Java**
```java
import java.util.ArrayList;
import java.util.List;

public class MemoryLeakExample {
    private static List<Object> memoryLeakList = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            Object obj = new Object();
            memoryLeakList.add(obj); // Object added but never removed
        }
    }
}
```

**Fix**:
Use a bounded collection or properly clear the list when objects are no longer needed:
```java
import java.util.ArrayList;
import java.util.List;

public class MemoryLeakFixed {
    private static List<Object> memorySafeList = new ArrayList<>();

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) { // Limit the size of the list
            Object obj = new Object();
            memorySafeList.add(obj);
        }
    }
}
```

---

By being proactive with coding practices, profiling, and regular monitoring, you can minimize the risk of memory leaks in your monolith application.

---
---
High CPU utilization in a running monolith application can stem from various causes. Here are some common reasons and their explanations:
---

### 1. **Inefficient Code or Algorithm**
   - **Description**: Suboptimal algorithms or inefficient loops can consume excessive CPU resources.
   - **Example**: A poorly implemented sorting algorithm or an unnecessary nested loop can lead to high computational overhead.

---

### 2. **High Concurrency/Threading Issues**
   - **Description**: Excessive concurrent threads or thread contention for shared resources can lead to high CPU usage.
   - **Example**: A thread pool with an unbounded number of threads or improper synchronization leading to CPU spinning.

---

### 3. **High Request Load**
   - **Description**: If the monolith is under heavy traffic, the application server may struggle to handle the increased number of incoming requests.
   - **Example**: High API call volume during peak traffic times or unexpected spikes in user activity.

---

### 4. **Inefficient Database Queries**
   - **Description**: Poorly written or unoptimized queries can lead to excessive CPU usage on the application side if processing large datasets.
   - **Example**: Loading too much data into memory or executing complex joins without proper indexing.

---

### 5. **Excessive Logging**
   - **Description**: Verbose logging, especially at debug or trace levels, can consume significant CPU resources.
   - **Example**: Logging every incoming request and response payload during high traffic.

---

### 6. **Memory Leaks**
   - **Description**: Memory leaks can trigger frequent garbage collection, leading to high CPU utilization.
   - **Example**: Accumulating objects in memory that are never released due to bad references.

---

### 7. **Garbage Collection Overhead**
   - **Description**: Inefficient garbage collection settings or excessive object creation can lead to high CPU utilization by the JVM garbage collector.
   - **Example**: Using default GC settings without tuning them for high load scenarios.

---

### 8. **Improper Configuration**
   - **Description**: Misconfigured thread pools, caching layers, or other system resources can cause bottlenecks.
   - **Example**: A thread pool with too few threads causing tasks to pile up.

---

### 9. **CPU-Intensive Operations**
   - **Description**: Certain operations are inherently CPU-intensive, such as encryption, compression, or image/video processing.
   - **Example**: Encoding a large video file on the server.

---

### 10. **Third-Party Library Issues**
   - **Description**: Bugs or inefficiencies in third-party libraries used by the application.
   - **Example**: Using a library that has known performance issues with certain inputs.

---

### 11. **Deadlocks or Infinite Loops**
   - **Description**: Threads stuck in a deadlock or infinite loop can hog CPU resources.
   - **Example**: A misconfigured retry mechanism causing infinite retries.

---

### How to Diagnose?
1. **Monitor Metrics**:
   - Use monitoring tools (e.g., ELK, Grafana, Prometheus) to track CPU usage.
2. **Thread Dump Analysis**:
   - Use `jstack` or similar tools to analyze thread behavior.
3. **Profiling**:
   - Use profilers like VisualVM, JProfiler, or YourKit to identify hotspots.
4. **Database Query Analysis**:
   - Monitor slow queries and analyze execution plans.
5. **Log Analysis**:
   - Check logs for excessive or unnecessary entries.

---

### How to Mitigate?
- Optimize the code and algorithms.
- Tune thread pool and GC configurations.
- Optimize database queries and indexing.
- Reduce logging levels in production.
- Implement caching for frequently used data.
- Scale the application horizontally (e.g., via load balancers).

Let me know if you'd like deeper insights into any of these causes or solutions!
---
---
Java EE (Java Platform, Enterprise Edition) and Jakarta EE (Jakarta Platform, Enterprise Edition) are both platforms for building enterprise-level applications in Java. However, they differ primarily in governance, branding, and evolution. Here's a breakdown:

---

### 1. **Governance and Ownership**
   - **Java EE**:
     - Was developed and maintained by Sun Microsystems, and later Oracle Corporation after it acquired Sun.
     - Oracle controlled the platform's specifications, implementation, and branding.

   - **Jakarta EE**:
     - Jakarta EE is the successor to Java EE, now governed by the **Eclipse Foundation**.
     - The transition happened after Oracle decided to donate Java EE to the Eclipse Foundation in 2017.
     - The community-driven approach under the Eclipse Foundation fosters greater transparency and collaboration.

---

### 2. **Branding and Naming**
   - **Java EE**:
     - The "Java" name is a trademark of Oracle, and the platform was branded under the "Java" family.
     - The naming rights remained with Oracle.

   - **Jakarta EE**:
     - Oracle retained the "Java" trademark, so the platform was rebranded as "Jakarta EE."
     - The new name was chosen to reflect its fresh direction under the Eclipse Foundation.

---

### 3. **Evolution**
   - **Java EE**:
     - Java EE had a slower release cycle under Oracle.
     - Major versions were infrequent, and updates took longer due to corporate decision-making processes.

   - **Jakarta EE**:
     - Jakarta EE is designed to have a faster, more agile release cycle under the community-driven model.
     - The goal is to respond more quickly to technological advancements and developer needs.

---

### 4. **Specifications and Compatibility**
   - **Java EE**:
     - The last release was **Java EE 8** in 2017, which included technologies like Servlet 4.0, JSON-B, and improvements to CDI and JAX-RS.

   - **Jakarta EE**:
     - Jakarta EE 8 was functionally identical to Java EE 8 to ensure a seamless transition for developers and vendors.
     - Subsequent versions, such as **Jakarta EE 9**, introduced namespace changes (from `javax.*` to `jakarta.*`) as part of its evolution.
     - **Jakarta EE 10** introduced new features, modern APIs, and better support for cloud-native architectures.

---

### 5. **Namespace Changes**
   - One of the most significant differences is the change in package namespaces:
     - **Java EE**: Used `javax.*` (e.g., `javax.servlet`, `javax.persistence`).
     - **Jakarta EE**: Moved to `jakarta.*` (e.g., `jakarta.servlet`, `jakarta.persistence`) starting from Jakarta EE 9.

---

### 6. **Community and Ecosystem**
   - **Java EE**:
     - Was largely controlled by Oracle, with limited input from the broader community.

   - **Jakarta EE**:
     - Open to contributions from a wide range of individuals, organizations, and vendors.
     - Aims to modernize the platform for cloud-native and microservices-based applications.

---

### Summary Table

| Aspect            | Java EE                     | Jakarta EE                  |
|--------------------|-----------------------------|-----------------------------|
| Governance         | Oracle                     | Eclipse Foundation          |
| Branding           | "Java" (Oracle-owned)      | "Jakarta" (community-driven)|
| Namespace          | `javax.*`                  | `jakarta.*`                 |
| Evolution Speed    | Slower                     | Faster                      |
| Release Example    | Java EE 8                  | Jakarta EE 10               |
| Ecosystem          | Corporate-driven           | Community-driven            |

---

### Conclusion
Jakarta EE is the modern evolution of Java EE, focused on agility, innovation, and community collaboration. If you're starting a new enterprise project or maintaining an existing one, transitioning to Jakarta EE is recommended for its up-to-date features and community support.
---

### What is a Marker Interface?

A **Marker Interface** in Java is an interface that does not contain any methods or fields and serves as a *tag* or *marker* to indicate that a class possesses some special property or behavior. Marker interfaces provide metadata to the Java runtime or framework, signaling that classes implementing these interfaces should be treated differently.

#### Examples of Marker Interfaces in Java
1. **Serializable**: Marks a class as capable of being serialized.
2. **Cloneable**: Marks a class as capable of being cloned.
3. **Remote**: Marks a class for use in Java RMI (Remote Method Invocation).

### Use Case of Marker Interfaces
Marker interfaces are used to indicate or tag that a class qualifies for a certain behavior or capability. The decision of whether a class should exhibit the marked behavior is usually made by:
1. JVM or frameworks during runtime.
2. Custom logic checking for the presence of the interface using `instanceof` or reflection.

---

### How to Write a Custom Marker Interface
A custom marker interface can be created by defining an interface without any methods or fields.

#### Steps to Create a Custom Marker Interface

1. Define an empty interface.
2. Use it to mark classes.
3. Add logic to check if a class implements the marker interface.

#### Example: Custom Marker Interface for Auditing

1. **Define the Marker Interface**
   ```java
   public interface Auditable {
   }
   ```

2. **Use the Marker Interface in Classes**
   ```java
   public class User implements Auditable {
       private String name;
       private String email;

       // Constructor, Getters, and Setters
       public User(String name, String email) {
           this.name = name;
           this.email = email;
       }

       // Getters and Setters
       public String getName() {
           return name;
       }

       public void setName(String name) {
           this.name = name;
       }

       public String getEmail() {
           return email;
       }

       public void setEmail(String email) {
           this.email = email;
       }
   }
   ```

3. **Add Logic to Check for Marker Interface**
   ```java
   public class MarkerInterfaceExample {

       public static void main(String[] args) {
           User user = new User("Alice", "alice@example.com");

           if (user instanceof Auditable) {
               System.out.println("This class is auditable. Perform auditing logic here.");
           } else {
               System.out.println("This class is not auditable.");
           }
       }
   }
   ```

---

### Key Points
- Marker interfaces are typically used for classification and metadata.
- They are checked during runtime, either using `instanceof` or reflection.
- In modern Java, **annotations** are often preferred over marker interfaces because they offer more flexibility and readability. 

#### Example Using Annotation (Preferred Approach)
```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Auditable {
}
```
You can then use the `@Auditable` annotation and check it using reflection.
---
### In Java, both the `Comparable` and `Comparator` interfaces are used to compare objects, but they serve different purposes and are implemented in different ways. Here's a detailed comparison:

### 1. **Comparable Interface:**
   - **Purpose:** Used to define the natural ordering of objects. It provides a single method `compareTo()` that compares the current object with the specified object.
   - **Usage:** A class implements `Comparable` to impose a natural order on its objects. This ordering is typically the "default" or "natural" way that objects of this class should be compared.
   - **Method:** 
     - `public int compareTo(T o);`
     - It returns:
       - A negative integer if the current object is less than the specified object.
       - Zero if the current object is equal to the specified object.
       - A positive integer if the current object is greater than the specified object.

   - **Example:**
     ```java
     class Student implements Comparable<Student> {
         int rollNo;
         String name;

         public Student(int rollNo, String name) {
             this.rollNo = rollNo;
             this.name = name;
         }

         @Override
         public int compareTo(Student other) {
             return this.rollNo - other.rollNo;  // Compare based on roll number
         }

         @Override
         public String toString() {
             return "Student{" + "rollNo=" + rollNo + ", name='" + name + '\'' + '}';
         }
     }

     public class Main {
         public static void main(String[] args) {
             List<Student> students = new ArrayList<>();
             students.add(new Student(3, "John"));
             students.add(new Student(1, "Alice"));
             students.add(new Student(2, "Bob"));

             Collections.sort(students);  // Uses compareTo to sort by roll number
             System.out.println(students);
         }
     }
     ```
   - **Output:**
     ```
     [Student{rollNo=1, name='Alice'}, Student{rollNo=2, name='Bob'}, Student{rollNo=3, name='John'}]
     ```

### 2. **Comparator Interface:**
   - **Purpose:** Used to define an external, custom ordering of objects. It allows you to create multiple ways to compare objects, unlike `Comparable` which provides a single comparison method.
   - **Usage:** You create a separate class or an anonymous inner class to implement `Comparator`. It’s particularly useful when you cannot modify the class whose objects you want to sort, or when you need multiple ways of comparing the same type of object.
   - **Method:** 
     - `public int compare(T o1, T o2);`
     - It returns:
       - A negative integer if `o1` is less than `o2`.
       - Zero if `o1` is equal to `o2`.
       - A positive integer if `o1` is greater than `o2`.

   - **Example:**
     ```java
     import java.util.*;

     class Student {
         int rollNo;
         String name;

         public Student(int rollNo, String name) {
             this.rollNo = rollNo;
             this.name = name;
         }

         @Override
         public String toString() {
             return "Student{" + "rollNo=" + rollNo + ", name='" + name + '\'' + '}';
         }
     }

     class RollNoComparator implements Comparator<Student> {
         @Override
         public int compare(Student s1, Student s2) {
             return s1.rollNo - s2.rollNo;  // Compare by roll number
         }
     }

     class NameComparator implements Comparator<Student> {
         @Override
         public int compare(Student s1, Student s2) {
             return s1.name.compareTo(s2.name);  // Compare by name
         }
     }

     public class Main {
         public static void main(String[] args) {
             List<Student> students = new ArrayList<>();
             students.add(new Student(3, "John"));
             students.add(new Student(1, "Alice"));
             students.add(new Student(2, "Bob"));

             // Sort by roll number using RollNoComparator
             Collections.sort(students, new RollNoComparator());
             System.out.println("Sorted by roll number: " + students);

             // Sort by name using NameComparator
             Collections.sort(students, new NameComparator());
             System.out.println("Sorted by name: " + students);
         }
     }
     ```
   - **Output:**
     ```
     Sorted by roll number: [Student{rollNo=1, name='Alice'}, Student{rollNo=2, name='Bob'}, Student{rollNo=3, name='John'}]
     Sorted by name: [Student{rollNo=1, name='Alice'}, Student{rollNo=2, name='Bob'}, Student{rollNo=3, name='John'}]
     ```

### Key Differences:

| Aspect               | Comparable                           | Comparator                          |
|----------------------|--------------------------------------|-------------------------------------|
| **Method**           | `compareTo()`                        | `compare()`                         |
| **Location of Logic**| Inside the class itself              | Outside the class (in a separate class) |
| **Modification**     | Must modify the class to implement it| No need to modify the class         |
| **Number of Comparison Strategies** | Single natural order          | Multiple custom orders              |
| **Used in**          | `Collections.sort()` or `Arrays.sort()` | `Collections.sort()` or `Arrays.sort()` with Comparator |

### When to Use:
- Use `Comparable` when you have a natural ordering that should be part of the class itself (e.g., sorting by an ID or name in a consistent way).
- Use `Comparator` when you need to provide multiple sorting strategies or when you can't modify the class you’re sorting (e.g., sorting by multiple attributes like age or salary).

---
Why cloneable is called marker interface when we must override clone method if implementing cloneable  marker interface but the same is not applicable for serializable
The difference between **`Cloneable`** and **`Serializable`** as marker interfaces, despite both being marker interfaces, lies in their **purpose** and **implementation**. Let's break this down:

---

### 1. **Why `Cloneable` is a Marker Interface?**
A **marker interface** is used to signal to the JVM or a library that a class possesses certain characteristics or behaviors. 

- `Cloneable` doesn't provide any methods to implement. Its sole purpose is to indicate that an object's **`clone()`** method can be safely invoked to perform a shallow copy. 
- The actual cloning logic resides in the `Object` class's **`clone()`** method, which is protected and must be overridden in the class implementing `Cloneable`.

#### Why `clone()` Must Be Overridden:
- Even though `Cloneable` is implemented, the `Object` class doesn't directly provide a public `clone()` method for the subclass.
- To make cloning accessible, **`clone()` must be explicitly overridden** in the class implementing `Cloneable`. Failing to do so results in a `CloneNotSupportedException`.

```java
class MyClass implements Cloneable {
    int value;

    public MyClass(int value) {
        this.value = value;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone(); // Cloning is performed here
    }
}
```

Without overriding, cloning cannot be performed because `Object`'s `clone()` method is **protected** and requires explicit permission.

---

### 2. **Why `Serializable` Doesn't Require Method Overriding?**
- `Serializable` is also a marker interface but is used to indicate that a class can be serialized and deserialized.
- Unlike `Cloneable`, the JVM or libraries like Java's `ObjectInputStream` and `ObjectOutputStream` **handle serialization automatically** for classes implementing `Serializable`.
- Serialization logic is built into the Java runtime, so no additional method needs to be overridden.

#### How Serialization Works:
- When an object is serialized, the JVM automatically examines whether the class implements `Serializable`. If it does, it serializes the fields of the object.
- The process doesn't require any specific method to be implemented, making `Serializable` truly a "marker."

---

### 3. **Key Difference: Behavior Enforcement**
- **`Cloneable`**: Requires manual intervention (method overriding) to enforce the behavior of cloning, as the base implementation in `Object` is incomplete without subclass involvement.
- **`Serializable`**: Behavior is fully handled by the Java runtime and does not need intervention from the programmer.

---

### Conclusion:
`Cloneable` is a **marker interface** because it signals the **intent** to allow cloning, but the responsibility for providing a working `clone()` implementation lies with the developer. 

In contrast, `Serializable` is a marker interface whose functionality is completely automated by the JVM, hence no methods need to be implemented. This distinction highlights why the two interfaces behave differently despite both being marker interfaces.

---
Without implementing serializable interface, can we serialize or deserialize object in java?

Yes, it is **possible to serialize and deserialize an object in Java without implementing the `Serializable` interface**, but it requires some additional effort. Normally, the Java Serialization API (`ObjectOutputStream` and `ObjectInputStream`) works only with classes that implement the `Serializable` interface. However, you can bypass this restriction using custom serialization logic.

---

### 1. **Using `Externalizable` Interface**
The **`Externalizable`** interface is an alternative to `Serializable` that provides complete control over the serialization and deserialization process. Unlike `Serializable`, `Externalizable` does not rely on marker behavior; instead, it requires you to implement the methods explicitly.

#### Example:
```java
import java.io.*;

class Person implements Externalizable {
    private String name;
    private int age;

    // Default constructor is required for Externalizable
    public Person() {}

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(name);
        out.writeInt(age);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        name = in.readUTF();
        age = in.readInt();
    }

    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + '}';
    }
}

public class ExternalizableExample {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Person person = new Person("Alice", 30);

        // Serialize the object
        FileOutputStream fileOut = new FileOutputStream("person.dat");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(person);
        out.close();

        // Deserialize the object
        FileInputStream fileIn = new FileInputStream("person.dat");
        ObjectInputStream in = new ObjectInputStream(fileIn);
        Person deserializedPerson = (Person) in.readObject();
        in.close();

        System.out.println("Deserialized Person: " + deserializedPerson);
    }
}
```
- **Advantages**: You get full control over which fields are serialized.
- **Disadvantages**: You must implement the `writeExternal()` and `readExternal()` methods.

---

### 2. **Using Custom Serialization Logic**
You can manually write and read object data using streams without relying on `Serializable` or `Externalizable`. This method is not tied to the standard Java Serialization API.

#### Example:
```java
import java.io.*;

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void saveToFile(String fileName) throws IOException {
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(fileName))) {
            out.writeUTF(name);
            out.writeInt(age);
        }
    }

    public static Person loadFromFile(String fileName) throws IOException {
        try (DataInputStream in = new DataInputStream(new FileInputStream(fileName))) {
            String name = in.readUTF();
            int age = in.readInt();
            return new Person(name, age);
        }
    }

    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + '}';
    }
}

public class CustomSerializationExample {
    public static void main(String[] args) throws IOException {
        Person person = new Person("Bob", 25);
        person.saveToFile("person.dat");

        Person deserializedPerson = Person.loadFromFile("person.dat");
        System.out.println("Deserialized Person: " + deserializedPerson);
    }
}
```
- **Advantages**: Full control over serialization and deserialization logic.
- **Disadvantages**: Requires more effort compared to standard serialization.

---

### 3. **Why is `Serializable` Used?**
While it is possible to serialize objects without implementing `Serializable`, the standard serialization mechanism provides significant convenience:
- Automatic handling of the object's fields.
- Built-in support for object graphs and cyclic references.
- Consistency with the default Java Serialization API.

If you bypass `Serializable`, you lose these features and must handle them manually.

---

### Summary
- **Without `Serializable`**, you can use the `Externalizable` interface for explicit control or write custom logic to serialize and deserialize objects manually.
- However, using `Serializable` simplifies the process and integrates seamlessly with the Java Serialization API.

---

In Java, an `int` array can be declared and initialized in various ways. Below are examples of different approaches:

---

### **1. Declare and Initialize Separately**
```java
int[] arr;              // Declaration
arr = new int[5];       // Initialization with size 5
arr[0] = 10;            // Assigning values individually
arr[1] = 20;
```

---

### **2. Declare and Initialize with Values**
```java
int[] arr = {10, 20, 30, 40, 50};  // Declare and initialize with values
```

---

### **3. Using `new` Keyword with Initial Values**
```java
int[] arr = new int[]{10, 20, 30, 40, 50};  // Explicitly use new keyword
```

---

### **4. Using Loops to Initialize**
#### Example: Initialize with a for loop
```java
int[] arr = new int[5];
for (int i = 0; i < arr.length; i++) {
    arr[i] = i * 10;  // Initialize with multiples of 10
}
```

---

### **5. Using Anonymous Arrays**
Anonymous arrays are arrays created without assigning to a variable.
```java
printArray(new int[]{10, 20, 30, 40, 50});  // Passing directly to a method

// Method to print array
public static void printArray(int[] arr) {
    for (int num : arr) {
        System.out.print(num + " ");
    }
}
```

---

### **6. Multi-Dimensional Arrays**
#### Example: Declare and Initialize 2D Array
```java
int[][] matrix = {
    {1, 2, 3},
    {4, 5, 6},
    {7, 8, 9}
};
```

#### Example: Declare and Initialize 3D Array
```java
int[][][] cube = {
    {{1, 2}, {3, 4}},
    {{5, 6}, {7, 8}}
};
```

---

### **7. Using `Arrays.fill()`**
The `Arrays.fill()` method can populate an array with a specific value.
```java
import java.util.Arrays;

int[] arr = new int[5];
Arrays.fill(arr, 100);  // Fills the array with 100
```

---

### **8. Using Streams (Java 8 and Later)**
#### Example: Initialize Using `IntStream`
```java
import java.util.stream.IntStream;

int[] arr = IntStream.range(1, 6).toArray();  // Generates [1, 2, 3, 4, 5]
```

#### Example: Using `Arrays.setAll`
```java
import java.util.Arrays;

int[] arr = new int[5];
Arrays.setAll(arr, i -> i * 2);  // Sets each element to twice its index
```

---

### **9. Declaring with Variable Length**
You can declare an array with different sizes dynamically:
```java
int n = 5;
int[] arr = new int[n];  // Array size based on a variable
```

---

### **10. Mixed Dimensions for Multi-Dimensional Arrays**
```java
int[][] jaggedArray = new int[3][];
jaggedArray[0] = new int[]{1, 2};
jaggedArray[1] = new int[]{3, 4, 5};
jaggedArray[2] = new int[]{6, 7, 8, 9};
```

---

### Summary
These methods provide flexibility depending on the use case:
- Use **static initialization** for fixed values.
- Use **dynamic loops** or `Arrays.fill` for repetitive initialization.
- Use **streams** for functional-style initialization.
- Use **multi-dimensional arrays** for structured data.

---

Java 8 introduced several significant features that have reshaped Java programming. Here’s an overview of the major ones, along with examples:

---

### 1. **Lambda Expressions**
Lambda expressions enable functional programming by allowing you to pass functions as arguments and simplify the syntax for anonymous classes.

**Example:**
```java
// Without Lambda
List<String> names = Arrays.asList("John", "Jane", "Jack");
Collections.sort(names, new Comparator<String>() {
    @Override
    public int compare(String s1, String s2) {
        return s1.compareTo(s2);
    }
});

// With Lambda
Collections.sort(names, (s1, s2) -> s1.compareTo(s2));
```

---

### 2. **Functional Interfaces**
A functional interface is an interface with a single abstract method. Examples include `Runnable`, `Callable`, `Comparator`, and `Function`.

**Example:**
```java
@FunctionalInterface
interface Greeting {
    void sayHello(String name);
}

public class Main {
    public static void main(String[] args) {
        Greeting greet = name -> System.out.println("Hello, " + name);
        greet.sayHello("Alice");
    }
}
```

---

### 3. **Streams API**
The Streams API simplifies operations on collections like filtering, mapping, and reducing data.

**Example:**
```java
List<String> names = Arrays.asList("John", "Jane", "Jack", "Jill");
names.stream()
     .filter(name -> name.startsWith("J"))
     .map(String::toUpperCase)
     .forEach(System.out::println);
```

---

### 4. **Default and Static Methods in Interfaces**
Interfaces can now have methods with default implementations or static utility methods.

**Example:**
```java
interface Vehicle {
    default void start() {
        System.out.println("Starting vehicle...");
    }
    static void service() {
        System.out.println("Servicing vehicle...");
    }
}

public class Car implements Vehicle {
    public static void main(String[] args) {
        Car car = new Car();
        car.start();
        Vehicle.service();
    }
}
```

---

### 5. **Optional**
`Optional` is a container to handle null values gracefully and avoid `NullPointerException`.

**Example:**
```java
Optional<String> optionalName = Optional.ofNullable(null);
System.out.println(optionalName.orElse("Default Name"));

optionalName = Optional.of("Alice");
optionalName.ifPresent(name -> System.out.println("Name is " + name));
```

---

### 6. **Date and Time API (java.time package)**
The new `java.time` package replaces the old `Date` and `Calendar` APIs with more powerful and intuitive classes.

**Example:**
```java
LocalDate today = LocalDate.now();
LocalDate birthday = LocalDate.of(1995, Month.MAY, 23);

Period age = Period.between(birthday, today);
System.out.println("You are " + age.getYears() + " years old.");
```

---

### 7. **Nashorn JavaScript Engine**
Java 8 introduced Nashorn, a JavaScript engine that allows you to execute JavaScript code within Java applications.

**Example:**
```java
import javax.script.*;

public class Main {
    public static void main(String[] args) throws ScriptException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        engine.eval("print('Hello from JavaScript');");
    }
}
```

---

### 8. **Method References**
Method references simplify lambda expressions by referring to existing methods by name.

**Example:**
```java
List<String> names = Arrays.asList("John", "Jane", "Jack");
names.forEach(System.out::println); // Equivalent to names.forEach(name -> System.out.println(name));
```

---

### 9. **New Collectors in Streams**
The `Collectors` utility provides methods like `toList()`, `toSet()`, and `joining()` for stream data aggregation.

**Example:**
```java
List<String> names = Arrays.asList("John", "Jane", "Jack");
String joinedNames = names.stream().collect(Collectors.joining(", "));
System.out.println(joinedNames); // Output: John, Jane, Jack
```

---

### 10. **Parallel Streams**
Parallel streams leverage multiple threads to perform operations in parallel.

**Example:**
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
numbers.parallelStream().forEach(System.out::println); // Output may vary due to parallel execution
```
```
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
int sum = numbers.parallelStream().mapToInt(Integer::intValue).sum();
System.out.println(sum);  // Output: 15
```
### 11. **CompletableFuture API**
The `CompletableFuture` class provides a way to handle asynchronous programming, enabling you to write non-blocking code.

**Example:**
```java
CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
    return 10;
});

future.thenApplyAsync(value -> value * 2)
      .thenAccept(result -> System.out.println("Result: " + result));  // Output: Result: 20

example
CompletableFuture.runAsync(this::processPurgeLogicCronMetaDeta);
public void processPurgeLogicCronMetaDeta(){
//logic implementation
}

```
Java 8 : Why Java 8
Java 8 improves overall application performance 
Lambda expressions, the Streams API, and new methods added to existing classes improve productivity.
Java 8 Optional type gives developers flexibility when dealing with null values, reducing the likelihood of NullPointerExceptions
Easy to Parallelize: JVM splits any operation into separate jobs and uses fork/join to run them on multiple cores with simple API additions.
Code is more concise and readable
Code is more reusable
Code is more testable and maintainable
Code is now both highly concurrent and scalable
Users can write parallel code
Users can write database-like operations
Applications now perform better
Code is far more productive
Java 8 : What are feature of Java 8
Key features introduced in Java 8 include:
1. Lambdas and Functional Interfaces: Java 8 introduced lambda expressions, which are anonymous functions that allow you to treat functions as method arguments. This made it easier to write more concise and expressive code. Alongside lambdas, functional interfaces were introduced to define a single abstract method, which can be implemented using lambda expressions.
2. Stream API: The Stream API introduced functional-style operations to process sequences of elements, such as collections, arrays, or I/O channels. Streams provide a more declarative way to work with data and allow for parallel processing without the need for explicit multithreading.
3. Default Methods: Interfaces in Java 8 can have method implementations. This feature, known as default methods, allows adding new methods to interfaces without breaking the existing implementations of classes that implement those interfaces.
4. Static Methods in Interfaces: Interfaces can now include static methods as well. These methods are associated with the interface itself, not with implementing classes.
5. Method References: Method references provide a shorthand syntax for referring to methods or constructors without invoking them. They can make code more readable when using lambda expressions.
6. Functional Interfaces: Java 8 introduced a few new built-in functional interfaces in the `java.util.function` package, such as `Predicate`, `Function`, `Consumer`, and `Supplier`, which are used to work with functional programming concepts.
7. Date and Time API: The new `java.time` package provides an improved date and time API that addresses the shortcomings of the older `java.util.Date` and `java.util.Calendar` classes. It offers better date and time manipulation, formatting, and parsing capabilities.
8. Optional: The `Optional` class is used to represent an optional value that can be either present or absent. It helps to avoid null pointer exceptions and encourages more robust handling of nullable values.
9. Nashorn JavaScript Engine: Java 8 included the Nashorn JavaScript engine, which allows developers to embed JavaScript code within Java applications and perform seamless interoperation between the two languages. Nashorn, a high-performance Java-based engine integrated to JDK used to evaluate and execute JavaScript code
Type Annotations: Type annotations can be applied to various elements in Java, such as classes, methods, and variables, to provide additional type information. This is particularly useful for tools that perform static analysis, like finding bugs or performing code analysis.
PermGen Removal: In previous versions of Java, the PermGen space was used to store metadata about classes and class loaders. Java 8 introduced the removal of PermGen and replaced it with the more flexible Metaspace, which is allocated from the native memory.
PermGen removal
10. New APIs and Libraries: Java 8 introduced various new APIs and libraries, including the `CompletableFuture` class for asynchronous programming, the `java.util.concurrent` package for concurrent programming, and improvements to the Collections framework.
These features collectively aimed to make Java code more expressive, efficient, and modern, while also enabling better support for functional programming paradigms.

https://javatechonline.com/java-features-after-java-8/

---

These features make Java 8 a major step forward for functional programming, concurrency, and developer productivity.

---

A **functional interface** in Java is an interface that contains exactly **one abstract method**. However, it **can have** any number of:

### 1. **Static Methods**:
- There is **no restriction** on the number of static methods in a functional interface.
- Static methods belong to the interface itself and do not affect its functional interface nature.

### 2. **Default Methods**:
- A functional interface **can have multiple default methods**.
- Default methods have a body and are not abstract, so they don't conflict with the "single abstract method" requirement of functional interfaces.

### Key Rule:
The functional interface remains valid as long as it has only one **abstract method**. The presence of static or default methods does not violate its functional interface property.

### Example:
```java
@FunctionalInterface
interface MyFunctionalInterface {
    // Single abstract method
    void execute();

    // Multiple static methods
    static void staticMethod1() {
        System.out.println("Static Method 1");
    }

    static void staticMethod2() {
        System.out.println("Static Method 2");
    }

    // Multiple default methods
    default void defaultMethod1() {
        System.out.println("Default Method 1");
    }

    default void defaultMethod2() {
        System.out.println("Default Method 2");
    }
}

public class TestFunctionalInterface {
    public static void main(String[] args) {
        MyFunctionalInterface instance = () -> System.out.println("Executing abstract method!");
        instance.execute();
        instance.defaultMethod1();
        instance.defaultMethod2();
        MyFunctionalInterface.staticMethod1();
        MyFunctionalInterface.staticMethod2();
    }
}
```

### Output:
```
Executing abstract method!
Default Method 1
Default Method 2
Static Method 1
Static Method 2
```

### Summary:
- **Static Methods**: Unlimited
- **Default Methods**: Unlimited
- **Abstract Methods**: Exactly **one** for a functional interface.

---------------------------------------------------------
Yes, **`StringBuilder`** can technically be used as a key in a **`HashMap`**, but it is not recommended because **`StringBuilder`** is mutable. Using mutable objects as keys in a **`HashMap`** can lead to unpredictable behavior and bugs.

### Why StringBuilder Can Be a Problematic Key

1. **Mutable Nature of StringBuilder**:
   - **`StringBuilder`** objects can be modified after they are used as keys in the **`HashMap`**. 
   - The **`hashCode()`** and **`equals()`** of the key are used by the **`HashMap`** to locate entries. If the content of a **`StringBuilder`** changes, the **`hashCode()`** changes, and the **`HashMap`** can no longer locate the key or its corresponding value correctly.

2. **Impact on HashMap Behavior**:
   - After a key's **`StringBuilder`** value is modified, the **`HashMap`** may not find the value for the updated key.
   - This leads to inconsistencies, such as `null` being returned for a valid key that was just modified.

---

### Example of the Problem:

```java
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        HashMap<StringBuilder, String> map = new HashMap<>();
        
        StringBuilder key = new StringBuilder("key1");
        map.put(key, "value1");

        System.out.println("Before modifying key: " + map.get(key)); // Output: value1

        // Modify the key
        key.append("Modified");

        // Attempt to retrieve the value
        System.out.println("After modifying key: " + map.get(key)); // Output: null
    }
}
```

### Output:
```
Before modifying key: value1
After modifying key: null
```

---

### Why String is Preferred as a Key:
- **`String`** is immutable, meaning its state cannot change after it is created.
- **`HashCode()`** and **`equals()`** remain consistent for the lifetime of the object, ensuring reliable behavior in a **`HashMap`**.

---

### Conclusion:
While **`StringBuilder`** can technically be used as a key in a **`HashMap`**, doing so is risky because of its mutable nature. It is strongly recommended to use immutable objects like **`String`** as keys to ensure consistent and reliable behavior of the **`HashMap`**. If you must use a **`StringBuilder`**, ensure it is not modified after being used as a key.

---

The **Diamond Problem** in Java is a common issue that arises with **multiple inheritance**. In languages that allow multiple inheritance (e.g., C++), this problem occurs when a class inherits from two classes that have a common ancestor, leading to ambiguity in method resolution. 

Java avoids this problem at the class level by not supporting multiple inheritance of classes. However, the issue can still arise with **interfaces**, particularly after Java 8 introduced **default methods**.

---

### **Explanation**

1. **The Problem:**
   Suppose there are two interfaces, `A` and `B`, both defining or inheriting a default method with the same name. A third class, `C`, implements both `A` and `B`. Java now has to decide which version of the default method to use, leading to ambiguity.

2. **How Java Handles It:**
   Java forces the implementing class to **resolve the conflict** by overriding the ambiguous method and explicitly specifying which default method to use.

---

### **Example: Diamond Problem in Java**

```java
interface A {
    default void display() {
        System.out.println("Display method in Interface A");
    }
}

interface B {
    default void display() {
        System.out.println("Display method in Interface B");
    }
}

class C implements A, B {
    // Must resolve the conflict explicitly
    @Override
    public void display() {
        // Choose one implementation or provide a custom one
        A.super.display(); // Calling the display method of Interface A
        // B.super.display(); // Uncomment to call Interface B's method
        System.out.println("Display method in Class C");
    }
}

public class DiamondProblemExample {
    public static void main(String[] args) {
        C obj = new C();
        obj.display();
    }
}
```

---

### **Output**

```
Display method in Interface A
Display method in Class C
```

---

### **Explanation of Resolution**

1. The compiler detects that both `A` and `B` have a `display()` method with the same signature.
2. To resolve this, the class `C` is forced to override the `display()` method.
3. Inside the overridden method in `C`, `A.super.display()` or `B.super.display()` can be explicitly called to refer to the respective interface's method.

---

### **Why Java Avoids the Diamond Problem at Class Level**
Java doesn't allow multiple inheritance of classes. This eliminates ambiguity because a class can only inherit methods from a single superclass. 

For example:

```java
class A {
    void display() {
        System.out.println("Display in Class A");
    }
}

class B extends A {
    void display() {
        System.out.println("Display in Class B");
    }
}

class C extends A, B { // Compilation error: Multiple inheritance not allowed in Java
    // Not possible
}
```

This design decision simplifies the inheritance model and avoids potential conflicts.

---

### **Key Points**

1. **In Classes:** Java avoids the Diamond Problem by disallowing multiple inheritance of classes.
2. **In Interfaces:** The problem arises with default methods but can be resolved by overriding the method in the implementing class.
3. **Resolution:** Use `InterfaceName.super.methodName()` to explicitly specify which interface's default method to use.

---

The concept of whether a stream has ordered or unordered data depends on the specific type of stream and its implementation. Here’s how it breaks down:

### **1. Ordered Streams**
- **Definition**: The data in the stream has a defined sequence or order.
- **Examples**:
  - **Java 8 Streams**: If the source of the stream is ordered (like a `List` or a `LinkedHashSet`), the stream will preserve that order unless explicitly altered (e.g., by calling `.unordered()`).
  - **Event Streams**: Some systems, like Kafka or RabbitMQ, preserve the order of messages within a partition or queue.
  - **Use Cases**: Scenarios where order is critical, such as processing a series of transactions in sequence.

### **2. Unordered Streams**
- **Definition**: The data in the stream has no defined sequence or order.
- **Examples**:
  - **Java 8 Streams**: If the source is unordered (like a `HashSet`) or the `.unordered()` method is applied, the stream operates without considering order.
  - **Parallel Streams**: Java Streams can become unordered in parallel processing to improve performance, depending on the operations applied.
  - **Real-Time Event Streams**: Some streaming systems prioritize speed over maintaining order.
- **Use Cases**: Situations where order doesn't matter, such as calculating aggregate statistics or parallel processing for faster execution.

### **Key Considerations**:
- In **Java Streams**, the order is tied to the source of the stream and any intermediate operations.
- In **Data Streams** (like Apache Flink or Spark Streaming), order depends on the source and whether mechanisms like event-time watermarking are used.
- **Trade-offs**: Maintaining order can add complexity and performance overhead, whereas unordered processing can enhance speed and scalability. 

If you’re referring to a specific kind of stream (e.g., Java Streams, data streams in a specific framework), please clarify for a more targeted explanation!

---

In Java, collections can be categorized as **ordered** or **unordered** based on whether they preserve the order of elements. Here's an overview:

---

### **Ordered Collections**
These collections preserve a specific order for their elements, which could be **insertion order** or **a custom-defined order (sorted)**.

#### **1. Ordered by Insertion**
- Elements are maintained in the order they were inserted.
- Examples:
  - **`List` Interface**:
    - `ArrayList`
    - `LinkedList`
  - **`LinkedHashSet`**: Maintains insertion order for sets.
  - **`LinkedHashMap`**: Maintains insertion order for keys in a map.

#### **2. Ordered by a Natural or Custom Sort Order**
- Elements are maintained in a sorted order (natural ordering or using a comparator).
- Examples:
  - **`TreeSet`**: A sorted set implementation based on a red-black tree.
  - **`TreeMap`**: A map implementation that sorts keys.
  - **`PriorityQueue`**: Sorts elements based on natural ordering or a provided comparator.

---

### **Unordered Collections**
These collections do not guarantee any specific order for their elements.

#### **1. Unordered Collections**
- **`HashSet`**: No guarantees about the order of elements.
- **`HashMap`**: No guarantees about the order of keys.
- **`Hashtable`**: Similar to `HashMap`, but synchronized and does not guarantee order.

#### **2. Arbitrary Order (Could Be Implementation-Dependent)**
- **`ConcurrentHashMap`**: Provides thread-safe access but does not guarantee order.
- **`EnumSet`**: Maintains a natural ordering of elements but is specific to enumerated types.
- **`EnumMap`**: Maintains keys in their natural order (the order in which the enum constants are declared).

---

### **Summary Table**

| **Collection Type**      | **Ordered**                                  | **Unordered**      |
|---------------------------|---------------------------------------------|-------------------|
| **List**                 | `ArrayList`, `LinkedList`                  | -                 |
| **Set**                  | `LinkedHashSet`                            | `HashSet`         |
| **Sorted Set**           | `TreeSet`                                  | -                 |
| **Map**                  | `LinkedHashMap`                            | `HashMap`         |
| **Sorted Map**           | `TreeMap`                                  | -                 |
| **Queue**                | `PriorityQueue`                            | `ArrayDeque`      |

---

### **Key Points**
1. **Insertion Order**:
   - `ArrayList`, `LinkedList`, `LinkedHashSet`, and `LinkedHashMap` preserve insertion order.
2. **Sorted Order**:
   - `TreeSet`, `TreeMap`, and `PriorityQueue` sort elements.
3. **Unordered**:
   - Collections like `HashSet` and `HashMap` are designed for fast lookups without maintaining any order.

Let me know if you'd like detailed examples for any of these!

---

The **`wait()`** and **`sleep()`** methods in Java are often confused but serve distinct purposes and have different characteristics. Here's a detailed comparison:

---
@Value("${threadpool.max.thread.count:100}")
private int maxThreadCount;

@Value("${threadpool.max.queue.size:200}")
private int maxQueueSize;

@Value("${threadpool.thread.timeout:60}")
private long threadTimeout;

@Value("${threadpool.max.thread.cpu:10}")
private int maxThreadPerCPU;

### **1. Purpose**
- **`wait()`**:
  - Used for **inter-thread communication**.
  - Makes the current thread release the lock it holds and enter the waiting state.
  - Allows other threads to acquire the lock and perform their tasks.
  - Typically used in synchronization blocks or methods.

- **`sleep()`**:
  - Used to **pause the execution** of the current thread for a specified duration.
  - The thread does not release the lock during the sleep period.
  - Helps to introduce a delay in the execution flow.

---

### **2. Location**
- **`wait()`**:
  - Defined in the `java.lang.Object` class.
  - Can only be called on an object, not on a thread directly.

- **`sleep()`**:
  - Defined in the `java.lang.Thread` class.
  - Called on the `Thread` class directly.

---

### **3. Synchronization**
- **`wait()`**:
  - Must be called within a synchronized block or method.
  - Throws `IllegalMonitorStateException` if not called while holding the object's lock.
  - Releases the lock on the object when it enters the waiting state.

- **`sleep()`**:
  - Can be called without synchronization.
  - Does not release any locks during its execution.

---

### **4. Behavior**
- **`wait()`**:
  - The thread remains in the waiting state until it is notified (using `notify()` or `notifyAll()`).
  - The waiting thread can also be interrupted by another thread.

- **`sleep()`**:
  - The thread remains in the sleeping state for the specified duration (in milliseconds or nanoseconds).
  - The sleeping thread can also be interrupted by another thread.

---

### **5. Resumption**
- **`wait()`**:
  - Resumes execution when the thread is explicitly notified or when the specified timeout (if any) elapses.

- **`sleep()`**:
  - Automatically resumes execution after the sleep duration expires.

---

### **6. Common Exceptions**
- **`wait()`**:
  - Throws `IllegalMonitorStateException` if not called from within a synchronized block.
  - Throws `InterruptedException` if the thread is interrupted while waiting.

- **`sleep()`**:
  - Throws `InterruptedException` if the thread is interrupted during the sleep period.

---

### **Example Code**

#### **`wait()` Example**
```java
class WaitExample {
    public static void main(String[] args) {
        final Object lock = new Object();

        Thread thread1 = new Thread(() -> {
            synchronized (lock) {
                System.out.println("Thread 1: Waiting...");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread 1: Resumed.");
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (lock) {
                System.out.println("Thread 2: Notifying...");
                lock.notify();
            }
        });

        thread1.start();
        thread2.start();
    }
}
```

#### **`sleep()` Example**
```java
class SleepExample {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println("Thread: Sleeping for 2 seconds...");
            try {
                Thread.sleep(2000); // Sleep for 2 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread: Resumed.");
        });

        thread.start();
    }
}
```

---

### **Key Differences Summary**

| **Aspect**               | **`wait()`**                            | **`sleep()`**                         |
|--------------------------|-----------------------------------------|---------------------------------------|
| **Defined In**            | `java.lang.Object`                     | `java.lang.Thread`                   |
| **Purpose**               | Inter-thread communication             | Pause thread execution               |
| **Lock Release**          | Releases lock                         | Does not release lock                |
| **Requires Synchronization** | Yes                                   | No                                    |
| **Resumes By**            | `notify()` / `notifyAll()` / timeout   | Sleep duration expiration            |

---

Let’s explore **`wait()`** and **`sleep()`** in detail with extended examples and explanations of their use cases.  

---

### **Use Case for `wait()`**
`wait()` is commonly used in **inter-thread communication**, where one thread needs to wait for a condition to be satisfied by another thread. Here's an example:

#### **Example: Producer-Consumer Problem**
```java
class SharedResource {
    private int value = 0;
    private boolean hasValue = false;

    public synchronized void produce(int newValue) throws InterruptedException {
        while (hasValue) {
            wait(); // Wait until the consumer consumes the current value
        }
        value = newValue;
        hasValue = true;
        System.out.println("Produced: " + value);
        notify(); // Notify the consumer that the value is available
    }

    public synchronized int consume() throws InterruptedException {
        while (!hasValue) {
            wait(); // Wait until the producer produces a value
        }
        hasValue = false;
        System.out.println("Consumed: " + value);
        notify(); // Notify the producer to produce a new value
        return value;
    }
}

public class WaitExample {
    public static void main(String[] args) {
        SharedResource resource = new SharedResource();

        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    resource.produce(i);
                    Thread.sleep(500); // Simulate time to produce
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    resource.consume();
                    Thread.sleep(1000); // Simulate time to consume
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        producer.start();
        consumer.start();
    }
}
```

#### **Output**:
```
Produced: 1
Consumed: 1
Produced: 2
Consumed: 2
Produced: 3
Consumed: 3
Produced: 4
Consumed: 4
Produced: 5
Consumed: 5
```

### **Key Points in This Example**:
1. The producer waits (`wait()`) if the resource is already full and notifies (`notify()`) the consumer after producing.
2. The consumer waits (`wait()`) if the resource is empty and notifies (`notify()`) the producer after consuming.

---

### **Use Case for `sleep()`**
`sleep()` is useful when you need to introduce a delay in a thread's execution without involving inter-thread communication. 

#### **Example: Scheduled Task Simulation**
```java
public class SleepExample {
    public static void main(String[] args) {
        Thread task = new Thread(() -> {
            System.out.println("Task started...");
            try {
                for (int i = 5; i >= 1; i--) {
                    System.out.println("Countdown: " + i);
                    Thread.sleep(1000); // Pause for 1 second between prints
                }
                System.out.println("Task complete!");
            } catch (InterruptedException e) {
                System.out.println("Task interrupted.");
            }
        });

        task.start();
    }
}
```

#### **Output**:
```
Task started...
Countdown: 5
Countdown: 4
Countdown: 3
Countdown: 2
Countdown: 1
Task complete!
```

---

### **Key Differences in Use Cases**

| **Scenario**                   | **Use `wait()`**                                             | **Use `sleep()`**                                            |
|--------------------------------|-------------------------------------------------------------|-------------------------------------------------------------|
| **Inter-thread communication**  | Yes. E.g., Producer-Consumer problem.                       | No. It doesn't involve interaction between threads.         |
| **Delay execution**             | No. `wait()` delays only until another thread notifies.     | Yes. Useful for introducing timed pauses.                  |
| **Releasing lock**              | Yes. Releases the lock for other threads.                  | No. Retains the lock even during the sleep period.          |
| **Example Context**             | Resource coordination (e.g., shared buffer).               | Timed tasks, polling, or animation.                        |

---

### **Common Pitfalls and Tips**
1. **Using `wait()` Without Synchronization**:
   - Always use `wait()` inside a `synchronized` block to avoid `IllegalMonitorStateException`.

2. **Misusing `sleep()` for Inter-thread Communication**:
   - Avoid using `sleep()` when you need proper coordination between threads. Use `wait()` with `notify()`/`notifyAll()` instead.

3. **Handling InterruptedException**:
   - Both `wait()` and `sleep()` throw `InterruptedException`. Ensure your code handles it gracefully to avoid abrupt termination.

---

Let’s dive deeper into **advanced threading topics** like **locks**, **semaphores**, and **executors**. These concepts offer more flexibility and control than basic synchronization mechanisms like `wait()`, `notify()`, and `synchronized`. 

---

### **1. Locks**
Locks are part of the `java.util.concurrent.locks` package and provide a more advanced mechanism for thread synchronization compared to the `synchronized` keyword.

#### **Key Classes and Interfaces**
- **`Lock` Interface**:
  - Allows explicit lock and unlock.
  - Supports features like fairness and interruptible locks.
- **`ReentrantLock`**:
  - A common implementation of `Lock`.
  - Allows a thread to re-acquire the same lock it already holds.
- **`ReadWriteLock`**:
  - Allows multiple readers or one writer at a time.

#### **Example: ReentrantLock**
```java
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class SharedCounter {
    private int counter = 0;
    private final Lock lock = new ReentrantLock();

    public void increment() {
        lock.lock(); // Acquire the lock
        try {
            counter++;
            System.out.println(Thread.currentThread().getName() + " incremented to: " + counter);
        } finally {
            lock.unlock(); // Release the lock
        }
    }

    public int getCounter() {
        return counter;
    }
}

public class LockExample {
    public static void main(String[] args) {
        SharedCounter counter = new SharedCounter();

        Runnable task = () -> {
            for (int i = 0; i < 5; i++) {
                counter.increment();
            }
        };

        Thread t1 = new Thread(task, "Thread-1");
        Thread t2 = new Thread(task, "Thread-2");

        t1.start();
        t2.start();
    }
}
```

#### **Output**:
```
Thread-1 incremented to: 1
Thread-2 incremented to: 2
Thread-1 incremented to: 3
Thread-2 incremented to: 4
...
```

---

### **2. Semaphores**
Semaphores limit the number of threads that can access a resource at the same time.

#### **Key Class**
- **`Semaphore`** (from `java.util.concurrent`):
  - `Semaphore(int permits)`: Specifies the number of permits available.

#### **Example: Controlling Access**
```java
import java.util.concurrent.Semaphore;

class ResourceAccess {
    private final Semaphore semaphore = new Semaphore(2); // Allow 2 threads at a time

    public void accessResource() {
        try {
            semaphore.acquire(); // Acquire a permit
            System.out.println(Thread.currentThread().getName() + " is accessing the resource.");
            Thread.sleep(1000); // Simulate work
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + " is releasing the resource.");
            semaphore.release(); // Release the permit
        }
    }
}

public class SemaphoreExample {
    public static void main(String[] args) {
        ResourceAccess resource = new ResourceAccess();

        for (int i = 1; i <= 5; i++) {
            new Thread(resource::accessResource, "Thread-" + i).start();
        }
    }
}
```

#### **Output**:
```
Thread-1 is accessing the resource.
Thread-2 is accessing the resource.
Thread-1 is releasing the resource.
Thread-3 is accessing the resource.
...
```

#### **Use Case**:
Semaphores are ideal for controlling access to limited resources like database connections, file systems, or APIs.

---

### **3. Executors**
Executors manage thread pools and provide an easy way to create and control threads.

#### **Key Classes**
- **`ExecutorService`**:
  - Provides thread pool management.
  - Methods like `submit()`, `shutdown()`.
- **`ScheduledExecutorService`**:
  - Schedules tasks to run after a delay or periodically.

#### **Example: Fixed Thread Pool**
```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3); // Create a pool of 3 threads

        for (int i = 1; i <= 5; i++) {
            int taskId = i;
            executor.submit(() -> {
                System.out.println("Task " + taskId + " is running on " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000); // Simulate work
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        executor.shutdown(); // Shutdown the executor after tasks complete
    }
}
```

#### **Output**:
```
Task 1 is running on pool-1-thread-1
Task 2 is running on pool-1-thread-2
Task 3 is running on pool-1-thread-3
Task 4 is running on pool-1-thread-1
Task 5 is running on pool-1-thread-2
```

#### **Use Case**:
Executors are perfect for managing tasks with large numbers of threads, especially when you want to control the number of active threads.

---

### **Key Differences: Locks, Semaphores, and Executors**

| **Feature**               | **Locks**                     | **Semaphores**                 | **Executors**               |
|---------------------------|-------------------------------|--------------------------------|-----------------------------|
| **Purpose**               | Synchronize thread access     | Control thread count           | Manage thread pools         |
| **Key Class**             | `ReentrantLock`, `ReadWriteLock` | `Semaphore`                   | `ExecutorService`           |
| **Control**               | Thread reentry, fairness      | Limits concurrent threads      | Task submission and execution |
| **Common Use Cases**      | Critical section management   | Resource pool access           | Task scheduling             |

---

Let’s explore **advanced concurrency utilities** like **CyclicBarrier**, **CountDownLatch**, and other synchronization tools in Java. These tools provide powerful mechanisms for coordinating tasks among multiple threads.

---

## **1. CountDownLatch**
A **CountDownLatch** is used to delay the progress of one or more threads until other threads have completed specific tasks. It operates as a counter; threads call `countDown()` to decrement the count, and one or more threads call `await()` to wait until the count reaches zero.

### **Use Case**
- Waiting for multiple threads to complete initialization before proceeding.
- Implementing multi-threaded testing where a main thread waits for worker threads to finish.

### **Example: Waiting for Threads**
```java
import java.util.concurrent.CountDownLatch;

class Worker extends Thread {
    private final CountDownLatch latch;
    private final int workerId;

    public Worker(CountDownLatch latch, int workerId) {
        this.latch = latch;
        this.workerId = workerId;
    }

    @Override
    public void run() {
        System.out.println("Worker " + workerId + " is starting...");
        try {
            Thread.sleep(workerId * 1000); // Simulate work
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Worker " + workerId + " has finished.");
        latch.countDown(); // Decrease the count
    }
}

public class CountDownLatchExample {
    public static void main(String[] args) {
        int numWorkers = 3;
        CountDownLatch latch = new CountDownLatch(numWorkers);

        for (int i = 1; i <= numWorkers; i++) {
            new Worker(latch, i).start();
        }

        try {
            latch.await(); // Wait for all workers to finish
            System.out.println("All workers are done. Proceeding with the main task...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
```

#### **Output**:
```
Worker 1 is starting...
Worker 2 is starting...
Worker 3 is starting...
Worker 1 has finished.
Worker 2 has finished.
Worker 3 has finished.
All workers are done. Proceeding with the main task...
```

---

## **2. CyclicBarrier**
A **CyclicBarrier** allows a set of threads to wait at a barrier point until all threads in the group reach it. Once all threads reach the barrier, the barrier is broken, and all threads proceed.

### **Use Case**
- Synchronizing threads to start a task simultaneously (e.g., starting a race).
- Breaking down a large task into phases, where each phase requires all threads to synchronize before moving forward.

### **Example: Synchronizing Threads**
```java
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

class Task implements Runnable {
    private final CyclicBarrier barrier;

    public Task(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " is preparing...");
            Thread.sleep((int) (Math.random() * 3000)); // Simulate preparation
            System.out.println(Thread.currentThread().getName() + " is ready.");
            barrier.await(); // Wait for others
            System.out.println(Thread.currentThread().getName() + " is proceeding.");
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}

public class CyclicBarrierExample {
    public static void main(String[] args) {
        int numThreads = 3;
        CyclicBarrier barrier = new CyclicBarrier(numThreads, () -> {
            System.out.println("All threads are ready. Let's proceed together!");
        });

        for (int i = 0; i < numThreads; i++) {
            new Thread(new Task(barrier)).start();
        }
    }
}
```

#### **Output**:
```
Thread-0 is preparing...
Thread-1 is preparing...
Thread-2 is preparing...
Thread-2 is ready.
Thread-0 is ready.
Thread-1 is ready.
All threads are ready. Let's proceed together!
Thread-2 is proceeding.
Thread-0 is proceeding.
Thread-1 is proceeding.
```

---

## **3. Difference Between CountDownLatch and CyclicBarrier**

| **Aspect**              | **CountDownLatch**                           | **CyclicBarrier**                             |
|-------------------------|----------------------------------------------|----------------------------------------------|
| **Resets Automatically** | No. Once the count reaches zero, it cannot be reused. | Yes. Can be reused after the barrier is broken. |
| **Purpose**              | Used for threads waiting for other threads to complete tasks. | Used to synchronize threads at a common barrier point. |
| **Trigger Action**       | No action triggered when count reaches zero. | Can execute a `Runnable` task after all threads reach the barrier. |
| **Thread Coordination**  | Main thread waits for other threads.         | Threads wait for each other.                 |

---

## **4. Phaser**
A **Phaser** is an advanced synchronization tool similar to `CyclicBarrier` but more flexible. It supports dynamic registration and deregistration of parties (threads).

### **Use Case**
- Coordinating multiple threads across multiple phases of execution.
- Dynamic thread management, where threads can join or leave at runtime.

---

## **5. Other Concurrency Utilities**
- **`Exchanger`**:
  - Allows two threads to exchange data.
  - Use case: Thread-to-thread communication.

- **`ForkJoinPool`**:
  - Optimized for recursive task splitting and joining.
  - Use case: Implementing divide-and-conquer algorithms.

---

Let’s explore **Phaser**, **Exchanger**, and **ForkJoinPool** in detail, along with examples. These are advanced concurrency utilities that cater to specific use cases.

---

## **1. Phaser**

A **Phaser** is a flexible tool for synchronizing threads over multiple phases. Unlike `CyclicBarrier`, it allows dynamic registration of threads (parties) and works across multiple phases, making it ideal for workflows with changing thread counts.

### **Use Case**
- Tasks that go through multiple phases of execution.
- Dynamic thread addition/removal during execution.

### **Example: Multi-phase Synchronization**
```java
import java.util.concurrent.Phaser;

class Task implements Runnable {
    private final Phaser phaser;

    public Task(Phaser phaser) {
        this.phaser = phaser;
    }

    @Override
    public void run() {
        for (int phase = 1; phase <= 3; phase++) {
            System.out.println(Thread.currentThread().getName() + " is in phase " + phase);
            phaser.arriveAndAwaitAdvance(); // Wait for all threads to complete the phase
        }
        System.out.println(Thread.currentThread().getName() + " has finished all phases.");
    }
}

public class PhaserExample {
    public static void main(String[] args) {
        Phaser phaser = new Phaser(1); // Register the main thread

        for (int i = 0; i < 3; i++) {
            phaser.register(); // Register a new party
            new Thread(new Task(phaser)).start();
        }

        for (int i = 1; i <= 3; i++) {
            System.out.println("Main thread is in phase " + i);
            phaser.arriveAndAwaitAdvance(); // Main thread participates in phases
        }

        phaser.arriveAndDeregister(); // Deregister the main thread
        System.out.println("Main thread has finished all phases.");
    }
}
```

#### **Output**:
```
Main thread is in phase 1
Thread-0 is in phase 1
Thread-1 is in phase 1
Thread-2 is in phase 1
Main thread is in phase 2
Thread-0 is in phase 2
Thread-1 is in phase 2
Thread-2 is in phase 2
Main thread is in phase 3
Thread-0 is in phase 3
Thread-1 is in phase 3
Thread-2 is in phase 3
Thread-0 has finished all phases.
Thread-1 has finished all phases.
Thread-2 has finished all phases.
Main thread has finished all phases.
```

---

## **2. Exchanger**

An **Exchanger** is used for thread-to-thread communication, allowing two threads to exchange data. It's especially useful in pipelines where one thread produces data, and another consumes it.

### **Use Case**
- Data handoff between producer and consumer threads.

### **Example: Data Exchange Between Threads**
```java
import java.util.concurrent.Exchanger;

class Producer extends Thread {
    private final Exchanger<String> exchanger;

    public Producer(Exchanger<String> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        try {
            String data = "Data from Producer";
            System.out.println("Producer produced: " + data);
            String response = exchanger.exchange(data); // Exchange data
            System.out.println("Producer received: " + response);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Consumer extends Thread {
    private final Exchanger<String> exchanger;

    public Consumer(Exchanger<String> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        try {
            String data = exchanger.exchange(null); // Receive data
            System.out.println("Consumer received: " + data);
            exchanger.exchange("Acknowledgment from Consumer"); // Send acknowledgment
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class ExchangerExample {
    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();

        new Producer(exchanger).start();
        new Consumer(exchanger).start();
    }
}
```

#### **Output**:
```
Producer produced: Data from Producer
Consumer received: Data from Producer
Producer received: Acknowledgment from Consumer
```

---

## **3. ForkJoinPool**

A **ForkJoinPool** is used to efficiently execute **divide-and-conquer** algorithms. It splits tasks into smaller subtasks, executes them in parallel, and combines the results.

### **Key Class**
- **`RecursiveTask<V>`**: Represents a task that returns a result.
- **`RecursiveAction`**: Represents a task that doesn't return a result.

### **Example: Calculating Sum Using RecursiveTask**
```java
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ForkJoinPool;

class SumTask extends RecursiveTask<Long> {
    private final int[] numbers;
    private final int start, end;
    private static final int THRESHOLD = 5;

    public SumTask(int[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        if (end - start <= THRESHOLD) {
            long sum = 0;
            for (int i = start; i < end; i++) {
                sum += numbers[i];
            }
            return sum;
        } else {
            int mid = (start + end) / 2;
            SumTask leftTask = new SumTask(numbers, start, mid);
            SumTask rightTask = new SumTask(numbers, mid, end);

            leftTask.fork(); // Execute left task asynchronously
            long rightResult = rightTask.compute(); // Execute right task in current thread
            long leftResult = leftTask.join(); // Wait for left task to finish

            return leftResult + rightResult;
        }
    }
}

public class ForkJoinPoolExample {
    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        ForkJoinPool pool = new ForkJoinPool();

        SumTask task = new SumTask(numbers, 0, numbers.length);
        long result = pool.invoke(task);

        System.out.println("Sum: " + result);
    }
}
```

#### **Output**:
```
Sum: 55
```

---

### **When to Use These Tools**
| **Tool**         | **Use Case**                                                                               |
|-------------------|-------------------------------------------------------------------------------------------|
| **Phaser**        | Multi-phase tasks with dynamic thread addition/removal.                                   |
| **Exchanger**     | Direct data exchange between two threads.                                                 |
| **ForkJoinPool**  | Large computational tasks that can be broken into smaller, independent subtasks.          |

---

Let's dive into **deadlock prevention**, **thread-safe collections**, and additional concurrency mechanisms in Java.

---

## **1. Deadlock Prevention**

### **What is Deadlock?**
Deadlock occurs when two or more threads are waiting for each other to release locks, leading to a situation where none can proceed.

### **Deadlock Example**
```java
class Resource {
    private final String name;

    public Resource(String name) {
        this.name = name;
    }

    public synchronized void lock(Resource other) {
        System.out.println(Thread.currentThread().getName() + " locked " + name);
        try {
            Thread.sleep(100); // Simulate some work
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " waiting to lock " + other.name);
        synchronized (other) {
            System.out.println(Thread.currentThread().getName() + " locked " + other.name);
        }
    }
}

public class DeadlockExample {
    public static void main(String[] args) {
        Resource resource1 = new Resource("Resource1");
        Resource resource2 = new Resource("Resource2");

        Thread t1 = new Thread(() -> resource1.lock(resource2), "Thread-1");
        Thread t2 = new Thread(() -> resource2.lock(resource1), "Thread-2");

        t1.start();
        t2.start();
    }
}
```

#### **Output** (Deadlock Situation):
```
Thread-1 locked Resource1
Thread-2 locked Resource2
Thread-1 waiting to lock Resource2
Thread-2 waiting to lock Resource1
```

### **Strategies to Prevent Deadlock**
1. **Lock Ordering**: Always acquire locks in a consistent order.
   ```java
   synchronized (resource1) {
       synchronized (resource2) {
           // Perform operations
       }
   }
   ```
2. **Try-Lock with Timeout**: Use `ReentrantLock` with `tryLock()` to acquire locks with a timeout.
   ```java
   ReentrantLock lock1 = new ReentrantLock();
   ReentrantLock lock2 = new ReentrantLock();

   try {
       if (lock1.tryLock(100, TimeUnit.MILLISECONDS)) {
           try {
               if (lock2.tryLock(100, TimeUnit.MILLISECONDS)) {
                   try {
                       // Perform operations
                   } finally {
                       lock2.unlock();
                   }
               }
           } finally {
               lock1.unlock();
           }
       }
   } catch (InterruptedException e) {
       e.printStackTrace();
   }
   ```
3. **Avoid Nested Locks**: Reduce complexity by avoiding multiple locks if possible.
4. **Deadlock Detection**: Use tools like **jstack** to detect deadlocks in a running application.

---

## **2. Thread-Safe Collections**

Java provides various **thread-safe collections** to handle concurrency without explicit synchronization.

### **Common Thread-Safe Collections**
| **Class**                    | **Description**                                                                 |
|-------------------------------|---------------------------------------------------------------------------------|
| `Vector`                     | Legacy synchronized list.                                                      |
| `Hashtable`                  | Legacy synchronized map.                                                       |
| `Collections.synchronizedXXX`| Wrappers to synchronize existing collections.                                   |
| `CopyOnWriteArrayList`       | Allows concurrent reads with safe writes by copying the array during modification.|
| `ConcurrentHashMap`          | A highly efficient, thread-safe map with segment-based locking.                |
| `ConcurrentLinkedQueue`      | A non-blocking, thread-safe queue for high performance.                         |

### **Example: ConcurrentHashMap**
```java
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapExample {
    public static void main(String[] args) {
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

        Thread t1 = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                map.put("Key" + i, i);
                System.out.println("Thread-1 added Key" + i);
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                System.out.println("Thread-2 reading Key" + i + ": " + map.get("Key" + i));
            }
        });

        t1.start();
        t2.start();
    }
}
```

---

## **3. Advanced Synchronization Mechanisms**

### **a. Semaphore**
A **Semaphore** controls access to a resource by allowing a fixed number of permits.

#### **Example: Limiting Access**
```java
import java.util.concurrent.Semaphore;

class Worker implements Runnable {
    private final Semaphore semaphore;

    public Worker(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();
            System.out.println(Thread.currentThread().getName() + " is accessing the resource.");
            Thread.sleep(2000); // Simulate resource usage
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + " is releasing the resource.");
            semaphore.release();
        }
    }
}

public class SemaphoreExample {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2); // Limit to 2 threads

        for (int i = 0; i < 5; i++) {
            new Thread(new Worker(semaphore)).start();
        }
    }
}
```

#### **Output**:
```
Thread-0 is accessing the resource.
Thread-1 is accessing the resource.
Thread-0 is releasing the resource.
Thread-2 is accessing the resource.
Thread-1 is releasing the resource.
Thread-3 is accessing the resource.
```

---

### **b. ReadWriteLock**
A **ReadWriteLock** allows multiple threads to read but only one thread to write, ensuring better performance in read-heavy scenarios.

#### **Example: Read-Write Access**
```java
import java.util.concurrent.locks.ReentrantReadWriteLock;

class SharedResource {
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private int data = 0;

    public void write(int value) {
        lock.writeLock().lock();
        try {
            data = value;
            System.out.println(Thread.currentThread().getName() + " wrote: " + data);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void read() {
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " read: " + data);
        } finally {
            lock.readLock().unlock();
        }
    }
}

public class ReadWriteLockExample {
    public static void main(String[] args) {
        SharedResource resource = new SharedResource();

        Thread writer = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                resource.write(i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread reader1 = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                resource.read();
            }
        });

        Thread reader2 = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                resource.read();
            }
        });

        writer.start();
        reader1.start();
        reader2.start();
    }
}
```

---

Let's dive into **thread pools**, **parallel streams**, and some practical applications of concurrency concepts.

---

## **1. Thread Pools**

Thread pools manage a pool of worker threads to execute tasks efficiently. Using a pool reduces the overhead of creating and destroying threads for each task.

### **Key Class**
`java.util.concurrent.ExecutorService`

### **Common Thread Pool Types**
- **FixedThreadPool**: A pool with a fixed number of threads.
- **CachedThreadPool**: A pool with a dynamically changing number of threads.
- **SingleThreadExecutor**: A single-threaded executor.
- **ScheduledThreadPool**: For delayed and periodic task execution.

### **Example: FixedThreadPool**
```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class WorkerThread implements Runnable {
    private final String task;

    public WorkerThread(String task) {
        this.task = task;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " started task: " + task);
        try {
            Thread.sleep(2000); // Simulate work
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " finished task: " + task);
    }
}

public class ThreadPoolExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3); // Pool with 3 threads

        for (int i = 1; i <= 6; i++) {
            executor.execute(new WorkerThread("Task-" + i));
        }

        executor.shutdown(); // Shut down after task completion
    }
}
```

#### **Output**:
```
pool-1-thread-1 started task: Task-1
pool-1-thread-2 started task: Task-2
pool-1-thread-3 started task: Task-3
pool-1-thread-1 finished task: Task-1
pool-1-thread-2 finished task: Task-2
pool-1-thread-3 finished task: Task-3
...
```

---

## **2. Parallel Streams**

Parallel streams enable data processing in parallel, leveraging the **ForkJoinPool** under the hood. They're ideal for splitting tasks across multiple cores.

### **Use Case**
Processing large collections or performing operations that can run independently.

### **Example: Parallel Stream for Sum**
```java
import java.util.stream.IntStream;

public class ParallelStreamExample {
    public static void main(String[] args) {
        int sum = IntStream.range(1, 10)
                           .parallel() // Enable parallelism
                           .peek(num -> System.out.println(Thread.currentThread().getName() + " processing: " + num))
                           .sum();

        System.out.println("Sum: " + sum);
    }
}
```

#### **Output**:
```
ForkJoinPool.commonPool-worker-3 processing: 1
ForkJoinPool.commonPool-worker-5 processing: 2
...
Sum: 45
```

### **When to Use Parallel Streams**
- For CPU-intensive tasks on large data sets.
- Avoid for I/O-bound tasks, as it can lead to thread contention.

---

## **3. Practical Applications**

### **a. Web Server with Thread Pool**
Simulate a web server where each request is handled by a thread in a pool.

```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class RequestHandler implements Runnable {
    private final int requestId;

    public RequestHandler(int requestId) {
        this.requestId = requestId;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " processing request: " + requestId);
        try {
            Thread.sleep(3000); // Simulate request processing
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " completed request: " + requestId);
    }
}

public class WebServerSimulation {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(4);

        for (int i = 1; i <= 10; i++) {
            threadPool.execute(new RequestHandler(i));
        }

        threadPool.shutdown();
    }
}
```

---

### **b. Data Aggregation with ForkJoinPool**
Aggregate data from multiple sources using **ForkJoinPool** for parallel computation.

```java
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ForkJoinPool;

class DataAggregator extends RecursiveTask<Integer> {
    private final int[] data;
    private final int start, end;
    private static final int THRESHOLD = 2;

    public DataAggregator(int[] data, int start, int end) {
        this.data = data;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if (end - start <= THRESHOLD) {
            int sum = 0;
            for (int i = start; i < end; i++) {
                sum += data[i];
            }
            return sum;
        } else {
            int mid = (start + end) / 2;
            DataAggregator leftTask = new DataAggregator(data, start, mid);
            DataAggregator rightTask = new DataAggregator(data, mid, end);

            leftTask.fork(); // Compute left in parallel
            int rightResult = rightTask.compute(); // Compute right in the current thread
            int leftResult = leftTask.join(); // Wait for left to complete

            return leftResult + rightResult;
        }
    }
}

public class ForkJoinAggregation {
    public static void main(String[] args) {
        int[] data = {1, 2, 3, 4, 5, 6, 7, 8};
        ForkJoinPool pool = new ForkJoinPool();

        DataAggregator task = new DataAggregator(data, 0, data.length);
        int result = pool.invoke(task);

        System.out.println("Total sum: " + result);
    }
}
```

---

### **c. Producer-Consumer Using BlockingQueue**
Efficiently handle a producer-consumer scenario using `BlockingQueue`.

```java
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class Producer implements Runnable {
    private final BlockingQueue<Integer> queue;

    public Producer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 5; i++) {
                System.out.println("Produced: " + i);
                queue.put(i); // Add item to the queue
                Thread.sleep(500); // Simulate delay
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Consumer implements Runnable {
    private final BlockingQueue<Integer> queue;

    public Consumer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int item = queue.take(); // Take item from the queue
                System.out.println("Consumed: " + item);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class ProducerConsumerExample {
    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(3); // Capacity of 3

        Thread producer = new Thread(new Producer(queue));
        Thread consumer = new Thread(new Consumer(queue));

        producer.start();
        consumer.start();
    }
}
```

---

Let’s dive into **CompletableFuture**, **asynchronous programming**, and some advanced concurrency patterns.

---

## **1. CompletableFuture**

`CompletableFuture` is a powerful tool in Java for asynchronous programming. It allows you to chain tasks, handle exceptions, and combine multiple tasks without blocking.

### **Key Features**
1. Non-blocking asynchronous execution.
2. Functional-style API for chaining tasks (`thenApply`, `thenAccept`, `thenCompose`, etc.).
3. Combining multiple futures (`allOf`, `anyOf`).
4. Exception handling (`exceptionally`, `handle`).

---

### **Basic Example: Async Task**
```java
import java.util.concurrent.CompletableFuture;

public class CompletableFutureBasicExample {
    public static void main(String[] args) {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            System.out.println("Running asynchronously in: " + Thread.currentThread().getName());
        });

        future.join(); // Block and wait for completion
    }
}
```

---

### **Example: Chaining Tasks**
```java
import java.util.concurrent.CompletableFuture;

public class CompletableFutureChaining {
    public static void main(String[] args) {
        CompletableFuture.supplyAsync(() -> {
            System.out.println("Fetching data...");
            return 42; // Simulated data
        }).thenApply(data -> {
            System.out.println("Processing data: " + data);
            return data * 2;
        }).thenAccept(result -> {
            System.out.println("Final result: " + result);
        }).join(); // Wait for the chain to complete
    }
}
```

---

### **Example: Combining Multiple Futures**
```java
import java.util.concurrent.CompletableFuture;

public class CompletableFutureCombining {
    public static void main(String[] args) {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> 20);
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> 22);

        CompletableFuture<Integer> combinedFuture = future1.thenCombine(future2, Integer::sum);

        System.out.println("Combined result: " + combinedFuture.join()); // Output: 42
    }
}
```

---

### **Exception Handling**
Handle errors gracefully during asynchronous execution.

```java
import java.util.concurrent.CompletableFuture;

public class CompletableFutureExceptionHandling {
    public static void main(String[] args) {
        CompletableFuture.supplyAsync(() -> {
            if (true) {
                throw new RuntimeException("Simulated error");
            }
            return "Success";
        }).exceptionally(ex -> {
            System.out.println("Error: " + ex.getMessage());
            return "Fallback value";
        }).thenAccept(result -> {
            System.out.println("Result: " + result);
        }).join();
    }
}
```

---

## **2. Asynchronous Programming with CompletableFuture**

### **Simulating an API Call**
```java
import java.util.concurrent.CompletableFuture;

public class AsyncApiCall {
    public static void main(String[] args) {
        CompletableFuture<Void> apiCall = CompletableFuture.supplyAsync(() -> {
            System.out.println("Calling API...");
            try {
                Thread.sleep(2000); // Simulate API call delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "API Response";
        }).thenAccept(response -> {
            System.out.println("Received response: " + response);
        });

        System.out.println("Main thread continues...");
        apiCall.join(); // Wait for API call to complete
    }
}
```

---

### **Combining Independent Tasks**
```java
import java.util.concurrent.CompletableFuture;

public class CombiningIndependentTasks {
    public static void main(String[] args) {
        CompletableFuture<String> task1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Task 1 result";
        });

        CompletableFuture<String> task2 = CompletableFuture.supplyAsync(() -> "Task 2 result");

        CompletableFuture<Void> combined = CompletableFuture.allOf(task1, task2);

        combined.thenRun(() -> {
            try {
                System.out.println("Task 1: " + task1.get());
                System.out.println("Task 2: " + task2.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).join();
    }
}
```

---

## **3. Advanced Concurrency Patterns**

### **a. Custom Thread Pool with CompletableFuture**
Use a custom thread pool for controlling task execution.

```java
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CustomThreadPoolExample {
    public static void main(String[] args) {
        ExecutorService customExecutor = Executors.newFixedThreadPool(3);

        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            System.out.println("Task running on: " + Thread.currentThread().getName());
        }, customExecutor);

        future.join();
        customExecutor.shutdown();
    }
}
```

---

### **b. Async Pipeline with CompletableFuture**
Simulate a pipeline of dependent tasks (e.g., data fetch, processing, and saving).

```java
import java.util.concurrent.CompletableFuture;

public class AsyncPipeline {
    public static void main(String[] args) {
        CompletableFuture.supplyAsync(() -> {
            System.out.println("Fetching data...");
            return "Raw Data";
        }).thenApply(data -> {
            System.out.println("Processing data: " + data);
            return "Processed Data";
        }).thenAccept(processedData -> {
            System.out.println("Saving data: " + processedData);
        }).join();
    }
}
```

---

### **c. Timeout Handling with CompletableFuture**
Use `orTimeout` and `completeOnTimeout` for timeout management.

```java
import java.util.concurrent.CompletableFuture;

public class TimeoutHandling {
    public static void main(String[] args) {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000); // Simulate delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Completed Task";
        }).orTimeout(2, java.util.concurrent.TimeUnit.SECONDS)
          .exceptionally(ex -> "Timeout occurred");

        System.out.println(future.join());
    }
}
```

---

## **4. Practical Applications**

### **a. Microservices with Asynchronous APIs**
In a microservices architecture, use `CompletableFuture` for non-blocking calls to multiple services.

```java
public class MicroservicesExample {
    public static void main(String[] args) {
        CompletableFuture<String> userService = CompletableFuture.supplyAsync(() -> "User Details");
        CompletableFuture<String> paymentService = CompletableFuture.supplyAsync(() -> "Payment Details");

        CompletableFuture<Void> combined = CompletableFuture.allOf(userService, paymentService);

        combined.thenRun(() -> {
            try {
                System.out.println("User Service Response: " + userService.get());
                System.out.println("Payment Service Response: " + paymentService.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).join();
    }
}
```

---

### **b. Parallel Batch Processing**
Process multiple batches of data in parallel using `CompletableFuture`.

```java
import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;

public class ParallelBatchProcessing {
    public static void main(String[] args) {
        IntStream.range(1, 6).forEach(batch -> {
            CompletableFuture.runAsync(() -> {
                System.out.println("Processing batch " + batch + " on " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000); // Simulate batch processing
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        });

        try {
            Thread.sleep(6000); // Wait for all batches to complete
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
```

---

Let's dive deeper into more **practical use cases** and advanced patterns for **asynchronous programming** with `CompletableFuture` and concurrency in Java.

---

## **1. Asynchronous File Processing**

One common scenario where asynchronous programming can significantly improve performance is **file processing**, especially for large files or multiple files. You can process chunks of a file or multiple files concurrently.

### **Example: Parallel File Processing with CompletableFuture**

```java
import java.io.*;
import java.nio.file.*;
import java.util.concurrent.CompletableFuture;

public class AsyncFileProcessing {
    public static void main(String[] args) throws IOException {
        // Simulate reading multiple files asynchronously
        String[] files = {"file1.txt", "file2.txt", "file3.txt"};

        CompletableFuture[] futures = new CompletableFuture[files.length];

        for (int i = 0; i < files.length; i++) {
            final int index = i;
            futures[i] = CompletableFuture.runAsync(() -> {
                try {
                    System.out.println("Processing file: " + files[index]);
                    String content = new String(Files.readAllBytes(Paths.get(files[index])));
                    // Simulate some processing
                    System.out.println("File content of " + files[index] + ": " + content.substring(0, 20));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

        // Wait for all tasks to finish
        CompletableFuture.allOf(futures).join();
    }
}
```

In this example, multiple files are processed concurrently without blocking the main thread. The `allOf` method waits for all `CompletableFuture` instances to complete before continuing.

---

## **2. Asynchronous HTTP Requests**

In real-world applications, performing multiple **HTTP requests** concurrently can help reduce latency and improve performance, especially when interacting with remote APIs.

### **Example: Asynchronous HTTP Requests Using CompletableFuture**

```java
import java.util.concurrent.CompletableFuture;

public class AsyncHttpRequest {
    public static void main(String[] args) {
        CompletableFuture<String> request1 = CompletableFuture.supplyAsync(() -> {
            // Simulate HTTP request (you can use HttpClient for real-world usage)
            return simulateHttpRequest("https://api.service1.com");
        });

        CompletableFuture<String> request2 = CompletableFuture.supplyAsync(() -> {
            // Simulate HTTP request
            return simulateHttpRequest("https://api.service2.com");
        });

        CompletableFuture<Void> allRequests = CompletableFuture.allOf(request1, request2);

        allRequests.thenRun(() -> {
            try {
                // Wait for both HTTP requests to complete
                System.out.println("Response from service 1: " + request1.get());
                System.out.println("Response from service 2: " + request2.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).join();
    }

    private static String simulateHttpRequest(String url) {
        try {
            // Simulate network delay
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Response from " + url;
    }
}
```

In this example, both HTTP requests are made concurrently, reducing the total waiting time.

---

## **3. Asynchronous Database Queries**

When interacting with a database, **asynchronous execution** can improve performance by avoiding thread blocking during query execution. 

### **Example: Asynchronous Database Queries Using CompletableFuture**

Let's simulate fetching user information and processing it asynchronously.

```java
import java.util.concurrent.CompletableFuture;

public class AsyncDatabaseQuery {
    public static void main(String[] args) {
        CompletableFuture<String> query1 = CompletableFuture.supplyAsync(() -> {
            // Simulate a database query (replace with actual DB code)
            return simulateDatabaseQuery("SELECT * FROM users WHERE id = 1");
        });

        CompletableFuture<String> query2 = CompletableFuture.supplyAsync(() -> {
            // Simulate another database query
            return simulateDatabaseQuery("SELECT * FROM users WHERE id = 2");
        });

        CompletableFuture<Void> allQueries = CompletableFuture.allOf(query1, query2);

        allQueries.thenRun(() -> {
            try {
                System.out.println("User 1 data: " + query1.get());
                System.out.println("User 2 data: " + query2.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).join();
    }

    private static String simulateDatabaseQuery(String query) {
        try {
            // Simulate database delay
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Result of query: " + query;
    }
}
```

This example demonstrates how you can perform multiple database queries in parallel, improving the overall response time.

---

## **4. Managing Complex Async Flows with CompletableFuture**

### **a. Sequential and Parallel Execution**

You may need to perform tasks sequentially or in parallel based on some conditions.

### **Example: Sequential vs. Parallel Execution Using CompletableFuture**

```java
import java.util.concurrent.CompletableFuture;

public class SequentialParallelExecution {
    public static void main(String[] args) {
        // Sequential Execution
        CompletableFuture<Integer> sequentialResult = CompletableFuture.supplyAsync(() -> 5)
            .thenApplyAsync(x -> x * 2)
            .thenApplyAsync(x -> x + 3);

        sequentialResult.thenAccept(result -> System.out.println("Sequential Result: " + result));

        // Parallel Execution (non-blocking)
        CompletableFuture<Integer> parallelResult1 = CompletableFuture.supplyAsync(() -> 5);
        CompletableFuture<Integer> parallelResult2 = CompletableFuture.supplyAsync(() -> 10);

        CompletableFuture<Void> parallelResults = CompletableFuture.allOf(parallelResult1, parallelResult2);
        parallelResults.thenRun(() -> {
            try {
                System.out.println("Parallel Results: " + (parallelResult1.get() + parallelResult2.get()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Block main thread until all tasks complete
        parallelResults.join();
    }
}
```

In this example, we compare **sequential** execution (where each task waits for the previous one to complete) and **parallel** execution (where tasks run concurrently).

---

## **5. Asynchronous Event Processing with CompletableFuture**

In event-driven systems, **asynchronous event processing** allows you to process events in parallel without blocking the main thread.

### **Example: Event Processing with CompletableFuture**

```java
import java.util.concurrent.CompletableFuture;

public class EventProcessing {
    public static void main(String[] args) {
        // Simulate an event stream (asynchronously handle events)
        CompletableFuture<Void> event1 = CompletableFuture.runAsync(() -> {
            System.out.println("Processing event 1...");
            try {
                Thread.sleep(1000); // Simulate event processing
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        CompletableFuture<Void> event2 = CompletableFuture.runAsync(() -> {
            System.out.println("Processing event 2...");
            try {
                Thread.sleep(2000); // Simulate event processing
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Wait for all events to be processed
        CompletableFuture.allOf(event1, event2).join();
        System.out.println("All events processed!");
    }
}
```

This example processes events asynchronously, ensuring that no event blocks the main thread.

---

## **6. Using `CompletableFuture` with Real-Time Data**

In real-time applications (e.g., stock market updates, social media feeds), **asynchronous programming** ensures that data is processed and displayed as quickly as possible without blocking the user interface.

### **Example: Real-Time Data Processing**

```java
import java.util.concurrent.CompletableFuture;

public class RealTimeDataProcessing {
    public static void main(String[] args) {
        CompletableFuture<Void> stockUpdate = CompletableFuture.runAsync(() -> {
            System.out.println("Fetching latest stock price...");
            try {
                Thread.sleep(1500); // Simulate stock update delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Stock price updated: $100");
        });

        CompletableFuture<Void> feedUpdate = CompletableFuture.runAsync(() -> {
            System.out.println("Fetching social media feed...");
            try {
                Thread.sleep(2000); // Simulate feed update delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("New post on social media feed.");
        });

        // Wait for both tasks to finish
        CompletableFuture.allOf(stockUpdate, feedUpdate).join();
    }
}
```

In this example, two real-time updates (stock prices and social media feed) are processed asynchronously.

---

Great! Let's explore more advanced topics, such as **asynchronous programming** with **web frameworks** and **high-performance multi-threading**.

---

## **1. Asynchronous Programming in Web Frameworks**

### **a. Spring WebFlux (Reactive Programming)**

In modern web applications, especially for handling large numbers of concurrent users or real-time data, **Reactive Programming** is a popular choice. **Spring WebFlux** is an asynchronous, non-blocking framework that can handle high-concurrency with fewer threads.

Spring WebFlux uses **Project Reactor** for asynchronous and event-driven programming.

#### **Example: Simple Asynchronous REST API with Spring WebFlux**

1. **Dependencies** (Add to `pom.xml` for Spring Boot application):
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-webflux</artifactId>
</dependency>
```

2. **Controller Class** (Creating a simple asynchronous API endpoint):
```java
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class AsyncController {

    @GetMapping("/async-data")
    public Mono<String> getAsyncData() {
        return Mono.fromCallable(() -> {
            // Simulate some delay (e.g., database call)
            Thread.sleep(2000);
            return "Asynchronous Data Response";
        });
    }
}
```

In this example:
- The `Mono` class represents a **single asynchronous value** (similar to `CompletableFuture`).
- The `Mono.fromCallable` method is used to simulate a task that takes time (e.g., fetching data from a database).

When you hit the `/async-data` endpoint, the request will be processed asynchronously, and the server won't be blocked while waiting for the data.

#### **Advantages of Spring WebFlux:**
- Scalable to handle **thousands of concurrent users** with fewer threads.
- Built-in support for asynchronous processing using **reactive streams**.
- Good for **real-time applications**, such as chat apps, live updates, etc.

---

### **b. Asynchronous Programming with Vert.x**

**Vert.x** is another framework designed for building reactive and non-blocking applications. It's a toolkit that provides a lot of flexibility for creating high-performance, scalable applications.

#### **Example: Simple Asynchronous HTTP Server with Vert.x**

1. **Dependencies** (For Maven):
```xml
<dependency>
    <groupId>io.vertx</groupId>
    <artifactId>vertx-web</artifactId>
    <version>4.4.0</version>
</dependency>
```

2. **Creating an Asynchronous HTTP Server**:
```java
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;

public class AsyncServer {

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        HttpServer server = vertx.createHttpServer();

        Router router = Router.router(vertx);

        // Define an asynchronous route
        router.get("/async").handler(ctx -> {
            vertx.setTimer(2000, id -> {
                // Simulate some async operation (e.g., fetching from DB)
                ctx.response().end("Async Response after 2 seconds");
            });
        });

        // Start the server
        server.requestHandler(router).listen(8080);
    }
}
```

In this example, the Vert.x server handles requests asynchronously without blocking threads. When the `/async` endpoint is accessed, a timer simulates a 2-second delay, and the response is sent back once the delay is over.

---

## **2. High-Performance Multi-threading and Concurrency**

High-performance applications often require efficient **multi-threading** and **concurrency** management to maximize hardware utilization while ensuring that threads are not wasted. Java provides multiple approaches to this, such as using **Executors**, **Thread Pools**, and **ForkJoinPool**.

### **a. Using Executors and Thread Pools**

A **Thread Pool** is a pool of worker threads that can execute submitted tasks. Using a thread pool avoids the overhead of creating a new thread for each task.

#### **Example: Using ExecutorService for Parallel Tasks**

```java
import java.util.concurrent.*;

public class ThreadPoolExample {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(4);

        // Submit 10 tasks to the thread pool
        List<Callable<String>> tasks = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            final int taskId = i;
            tasks.add(() -> {
                Thread.sleep(1000); // Simulate work
                return "Task " + taskId + " completed";
            });
        }

        // Invoke all tasks and collect results
        List<Future<String>> results = executor.invokeAll(tasks);

        // Print results
        for (Future<String> result : results) {
            System.out.println(result.get());
        }

        executor.shutdown();
    }
}
```

In this example:
- **ExecutorService** manages a pool of 4 threads.
- **invokeAll** submits multiple tasks for parallel execution and returns the results.

#### **Advantages of Thread Pools:**
- **Reduces thread creation overhead** (tasks reuse threads).
- Helps manage system resources effectively.
- Can limit the number of concurrent threads (e.g., to avoid overloading the system).

---

### **b. ForkJoinPool for Parallel Divide-and-Conquer**

The **ForkJoinPool** is designed for tasks that can be split into smaller tasks, processed in parallel, and then combined into a final result. It's highly optimized for parallelism.

#### **Example: ForkJoinPool for Parallel Task Execution**

```java
import java.util.concurrent.*;

public class ForkJoinPoolExample {

    static class SumTask extends RecursiveTask<Integer> {
        private final int[] arr;
        private final int start;
        private final int end;

        SumTask(int[] arr, int start, int end) {
            this.arr = arr;
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            if (end - start <= 2) {
                // Base case: Sum the small array portion
                int sum = 0;
                for (int i = start; i < end; i++) {
                    sum += arr[i];
                }
                return sum;
            } else {
                // Divide and conquer: Split the task into smaller tasks
                int mid = (start + end) / 2;
                SumTask task1 = new SumTask(arr, start, mid);
                SumTask task2 = new SumTask(arr, mid, end);

                task1.fork(); // Asynchronously execute the first task
                int result2 = task2.fork().join(); // Execute the second task and wait for its result
                int result1 = task1.join(); // Wait for the first task result

                return result1 + result2; // Combine results
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        ForkJoinPool pool = new ForkJoinPool();
        SumTask task = new SumTask(arr, 0, arr.length);
        int result = pool.invoke(task);
        System.out.println("Sum of array elements: " + result);
    }
}
```

This example demonstrates a **divide-and-conquer** approach where the array is split into smaller subarrays, processed in parallel, and then combined.

#### **Advantages of ForkJoinPool:**
- Efficient for **recursive, divide-and-conquer** problems.
- Optimized for **parallel processing** of large tasks.
- Better for **computationally intensive tasks** than traditional thread pools.

---

### **c. Managing Concurrent Access with `Atomic` Classes**

In multi-threaded applications, you often need to manage concurrent access to shared data. The `java.util.concurrent.atomic` package provides classes like `AtomicInteger`, `AtomicLong`, and `AtomicReference` to safely modify variables in a concurrent environment.

#### **Example: Using AtomicInteger for Thread-Safe Increment**

```java
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerExample {
    private static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        // Create 100 threads that increment the counter
        Thread[] threads = new Thread[100];
        for (int i = 0; i < 100; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    count.incrementAndGet();
                }
            });
            threads[i].start();
        }

        // Wait for all threads to finish
        for (Thread thread : threads) {
            thread.join();
        }

        // Final count value
        System.out.println("Final count: " + count.get());
    }
}
```

In this example:
- `AtomicInteger` ensures **thread-safe** increment operations without using `synchronized` blocks.

#### **Advantages of `Atomic` Classes:**
- **Lock-free** synchronization for simple data types.
- Improved performance compared to using synchronized blocks or `ReentrantLocks`.

---

## **Conclusion**

We’ve covered a range of advanced topics in **asynchronous programming** and **high-performance multi-threading**, including how to implement concurrency in **web frameworks** like Spring WebFlux and Vert.x, how to manage parallel tasks with **ExecutorService** and **ForkJoinPool**, and how to efficiently handle concurrent data access using **

---

Let's dive deeper into **advanced concurrency patterns** and **use cases** in Java. These patterns are often employed to solve complex multi-threading challenges, improve performance, and ensure thread-safety when dealing with concurrent tasks.

---

## **1. The Producer-Consumer Pattern**

This is a classic problem in concurrent programming where multiple producers generate data, and multiple consumers process that data. The challenge is to manage the shared resource (the queue) and ensure thread safety while avoiding excessive blocking.

### **a. Using `BlockingQueue` for Producer-Consumer Pattern**

In Java, the **`BlockingQueue`** interface (part of the `java.util.concurrent` package) provides a safe and efficient way to implement the producer-consumer pattern.

#### **Example: Producer-Consumer with BlockingQueue**

```java
import java.util.concurrent.*;

public class ProducerConsumer {
    static class Producer implements Runnable {
        private final BlockingQueue<Integer> queue;

        Producer(BlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < 10; i++) {
                    queue.put(i); // Produces data
                    System.out.println("Produced: " + i);
                    Thread.sleep(100); // Simulate work
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    static class Consumer implements Runnable {
        private final BlockingQueue<Integer> queue;

        Consumer(BlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    Integer data = queue.take(); // Consumes data
                    System.out.println("Consumed: " + data);
                    Thread.sleep(150); // Simulate work
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(10);

        // Create and start producer and consumer threads
        Thread producerThread = new Thread(new Producer(queue));
        Thread consumerThread = new Thread(new Consumer(queue));

        producerThread.start();
        consumerThread.start();
    }
}
```

In this example:
- **`BlockingQueue`** handles the synchronization between the producer and consumer, ensuring that the producer doesn't add elements if the queue is full, and the consumer doesn't take elements if the queue is empty.
- `put()` and `take()` are blocking operations, meaning they will wait if the queue is full (for producers) or empty (for consumers).

#### **Advantages of this Approach:**
- Efficient handling of **producer-consumer** problems with **non-blocking** operations for the threads.
- **Scalability**: Multiple producers and consumers can be added without changing much of the logic.

---

## **2. The Future-Callback Pattern**

The **Future-Callback Pattern** is useful when you need to execute a task asynchronously and handle its result or errors once the task is completed. This pattern can be combined with **CompletableFuture** to allow non-blocking callbacks on the result of a task.

### **a. Using `CompletableFuture` with Callbacks**

#### **Example: Handling Results with Callbacks**

```java
import java.util.concurrent.*;

public class FutureCallbackPattern {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(4);

        // Start an asynchronous task
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("Task started");
            try {
                Thread.sleep(2000); // Simulate task delay
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return 42; // Return result
        }, executor);

        // Attach a callback to be executed when the task completes
        future.thenAccept(result -> {
            System.out.println("Task completed with result: " + result);
        });

        // Optionally handle exceptions
        future.exceptionally(ex -> {
            System.out.println("An error occurred: " + ex.getMessage());
            return null;
        });

        // Wait for the task to complete
        future.join();

        executor.shutdown();
    }
}
```

In this example:
- We start an asynchronous task with `CompletableFuture.supplyAsync()`, which will execute the task in a separate thread.
- **Callbacks**: The `thenAccept` method attaches a callback that will be executed once the task completes successfully.
- **Error Handling**: `exceptionally` allows handling errors in case the task fails.

#### **Advantages:**
- **Non-blocking**: You don't need to block the main thread; the callback will be executed when the result is available.
- **Flexible Error Handling**: The `exceptionally` method allows graceful error recovery or logging.

---

## **3. The Read-Write Lock Pattern**

When you have a shared resource that needs frequent reading and occasional writing, a **read-write lock** can improve performance by allowing multiple threads to read concurrently while ensuring exclusive access when writing.

### **a. Using `ReentrantReadWriteLock` for Concurrent Reads and Exclusive Writes**

#### **Example: Using Read-Write Lock**

```java
import java.util.concurrent.locks.*;

public class ReadWriteLockExample {
    private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private static int sharedData = 0;

    public static void main(String[] args) throws InterruptedException {
        // Reader Thread (Multiple readers can access concurrently)
        Runnable reader = () -> {
            lock.readLock().lock();
            try {
                System.out.println("Reading data: " + sharedData);
            } finally {
                lock.readLock().unlock();
            }
        };

        // Writer Thread (Only one writer can access at a time)
        Runnable writer = () -> {
            lock.writeLock().lock();
            try {
                sharedData++;
                System.out.println("Writing data: " + sharedData);
            } finally {
                lock.writeLock().unlock();
            }
        };

        // Create and start threads
        Thread readerThread1 = new Thread(reader);
        Thread readerThread2 = new Thread(reader);
        Thread writerThread = new Thread(writer);

        readerThread1.start();
        readerThread2.start();
        writerThread.start();

        readerThread1.join();
        readerThread2.join();
        writerThread.join();
    }
}
```

In this example:
- **Read Lock**: Allows multiple threads to read the shared data concurrently.
- **Write Lock**: Ensures that only one thread can write at a time, and while writing, no other thread can read or write.

#### **Advantages:**
- **Optimized for Read-Heavy Workloads**: If the number of readers greatly exceeds writers, this pattern can significantly improve performance by allowing concurrent reads.
- **Flexibility**: Writers have exclusive access to the resource when necessary, but reading is still optimized.

---

## **4. The Thread-Local Storage Pattern**

Sometimes, you need to store data that is specific to a **thread**. This can be useful in scenarios where you need to keep track of user sessions, database connections, or thread-specific configurations. **ThreadLocal** provides thread-specific storage.

### **a. Using `ThreadLocal` to Store Thread-Specific Data**

#### **Example: Using `ThreadLocal` for Thread-Specific Variables**

```java
public class ThreadLocalExample {

    private static ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 1);

    public static void main(String[] args) throws InterruptedException {
        // Thread 1
        Thread thread1 = new Thread(() -> {
            threadLocal.set(100);
            System.out.println("Thread 1 value: " + threadLocal.get());
        });

        // Thread 2
        Thread thread2 = new Thread(() -> {
            threadLocal.set(200);
            System.out.println("Thread 2 value: " + threadLocal.get());
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }
}
```

In this example:
- Each thread has its own instance of the `ThreadLocal` variable. This ensures that data is isolated and thread-safe without synchronization.

#### **Advantages:**
- **Thread-Safety**: The value of `ThreadLocal` is isolated to the current thread, ensuring no race conditions.
- **Performance**: You don't need to synchronize access to the data, making this pattern very efficient in certain use cases.

---

## **5. The Executor Completion Service Pattern**

In many cases, you might want to submit multiple tasks to an executor and get their results in the order in which they finish, rather than the order in which they were submitted. This is where the **ExecutorCompletionService** comes in handy.

### **a. Using `ExecutorCompletionService` to Handle Task Results**

#### **Example: ExecutorCompletionService for Task Ordering**

```java
import java.util.concurrent.*;

public class ExecutorCompletionServiceExample {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        ExecutorCompletionService<Integer> completionService = new ExecutorCompletionService<>(executor);

        // Submit 5 tasks
        for (int i = 0; i < 5; i++) {
            final int taskId = i;
            completionService.submit(() -> {
                Thread.sleep(1000); // Simulate task
                return taskId * 10; // Return result
            });
        }

        // Retrieve the results as tasks complete
        for (int i = 0; i < 5; i++) {
            Future<Integer> resultFuture = completionService.take(); // Blocks until a task

---


Java 8 introduced several significant enhancements and features over Java 7, making it a landmark release in the evolution of the Java programming language. Here’s a detailed comparison:

---

### 1. **Functional Programming Features**
#### **Java 7:**
- Java 7 did not support functional programming. Developers had to rely on imperative programming styles with verbose syntax.

#### **Java 8:**
- Introduced **Lambda Expressions**, **Functional Interfaces**, and the **Stream API**, which support functional programming paradigms.

#### **Example:**
- **Java 7: Filtering a List of Strings**
  ```java
  List<String> names = Arrays.asList("John", "Jane", "Jack", "Jill");
  List<String> filteredNames = new ArrayList<>();
  for (String name : names) {
      if (name.startsWith("J")) {
          filteredNames.add(name);
      }
  }
  System.out.println(filteredNames);
  ```
- **Java 8: Using Streams and Lambda Expressions**
  ```java
  List<String> names = Arrays.asList("John", "Jane", "Jack", "Jill");
  List<String> filteredNames = names.stream()
                                    .filter(name -> name.startsWith("J"))
                                    .collect(Collectors.toList());
  System.out.println(filteredNames);
  ```

---

### 2. **Default Methods in Interfaces**
#### **Java 7:**
- Interfaces could only contain abstract methods, requiring implementing classes to define all methods.

#### **Java 8:**
- Introduced **default methods**, allowing interfaces to provide a default implementation for methods, enabling backward compatibility.

#### **Example:**
- **Java 7:**
  ```java
  interface Vehicle {
      void start();
  }
  class Car implements Vehicle {
      @Override
      public void start() {
          System.out.println("Car starts");
      }
  }
  ```
- **Java 8:**
  ```java
  interface Vehicle {
      void start();
      default void stop() {
          System.out.println("Vehicle stops");
      }
  }
  class Car implements Vehicle {
      @Override
      public void start() {
          System.out.println("Car starts");
      }
  }
  Vehicle car = new Car();
  car.start();
  car.stop(); // Default method from the interface
  ```

---

### 3. **Stream API**
#### **Java 7:**
- No Stream API. Developers had to rely on loops and collections for operations on data.

#### **Java 8:**
- Introduced the **Stream API** for processing sequences of elements in a functional style.

#### **Example:**
- **Java 8: Summing Even Numbers**
  ```java
  List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
  int sum = numbers.stream()
                   .filter(n -> n % 2 == 0)
                   .mapToInt(Integer::intValue)
                   .sum();
  System.out.println(sum); // Output: 12
  ```

---

### 4. **Date and Time API**
#### **Java 7:**
- Relied on the `java.util.Date` and `java.util.Calendar` classes, which were cumbersome and mutable.

#### **Java 8:**
- Introduced a new **Date and Time API** (`java.time` package), which is immutable and thread-safe.

#### **Example:**
- **Java 7:**
  ```java
  Calendar calendar = Calendar.getInstance();
  calendar.set(2023, Calendar.DECEMBER, 13);
  Date date = calendar.getTime();
  System.out.println(date);
  ```
- **Java 8:**
  ```java
  LocalDate date = LocalDate.of(2023, Month.DECEMBER, 13);
  System.out.println(date);
  ```

---

### 5. **Optional Class**
#### **Java 7:**
- No dedicated support for handling null values. Developers used `null` checks and faced `NullPointerException`.

#### **Java 8:**
- Introduced `Optional` to handle nullable values more elegantly.

#### **Example:**
- **Java 7:**
  ```java
  String name = null;
  if (name != null) {
      System.out.println(name.toUpperCase());
  } else {
      System.out.println("Name is null");
  }
  ```
- **Java 8:**
  ```java
  Optional<String> name = Optional.ofNullable(null);
  System.out.println(name.map(String::toUpperCase).orElse("Name is null"));
  ```

---

### 6. **Nashorn JavaScript Engine**
#### **Java 7:**
- Did not include the Nashorn JavaScript engine.

#### **Java 8:**
- Introduced the Nashorn engine to execute JavaScript code within Java applications.

#### **Example:**
```java
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class Java8NashornExample {
    public static void main(String[] args) throws Exception {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        engine.eval("print('Hello from Nashorn');");
    }
}
```

---

### 7. **Other Enhancements**
| Feature                  | **Java 7**                           | **Java 8**                                                                 |
|--------------------------|---------------------------------------|-----------------------------------------------------------------------------|
| **Type Inference**       | Limited with diamond operator.       | Extended to Lambda expressions and method references.                      |
| **Concurrency API**      | Fork/Join framework enhancements.    | Introduced `CompletableFuture` for asynchronous programming.               |
| **PermGen Removal**      | Still uses PermGen for class metadata.| Removed PermGen and introduced Metaspace.                                  |

---

### Conclusion:
Java 8 introduced groundbreaking features that promote a more functional and modern approach to programming, while Java 7 focused on improving the platform's stability and performance.

---

Reading millions of records from a file using Java 8 Streams can be efficient, but performance and resource usage need to be carefully managed. Here’s how to do it and some considerations to mitigate potential performance impacts.

---

### **1. Reading a Large File Using Java 8 Stream**

Java 8’s `Files.lines` method can be used to read lines from a file lazily as a stream, which is memory-efficient because it avoids loading the entire file into memory at once.

#### **Example:**
```java
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class LargeFileReader {
    public static void main(String[] args) {
        Path filePath = Paths.get("path/to/largefile.txt");
        
        try (Stream<String> lines = Files.lines(filePath)) {
            lines.forEach(line -> {
                // Process each line
                System.out.println(line);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

- **Key Points:**
  - `Files.lines`: Returns a lazily-loaded stream of lines.
  - `try-with-resources`: Ensures the stream and file resources are closed after use.

---

### **2. Managing Performance Impact**

#### **A. Memory Usage**
- `Files.lines` reads lines lazily but keeps file handles open.
- If the processing of each line is slow or involves buffering multiple lines, memory usage can grow.

**Mitigation:**
- Process each line immediately to avoid retaining data unnecessarily.
- Use parallel streams if the processing is CPU-bound and can be parallelized.

#### **B. File I/O Bottlenecks**
- Disk I/O can be a bottleneck, especially for slow storage or remote files.

**Mitigation:**
- Tune the file buffer size using `BufferedReader` if needed:
  ```java
  Files.newBufferedReader(filePath).lines();
  ```

#### **C. Parallel Processing**
- Java 8 streams can be converted to parallel streams for improved performance on multicore processors.

**Example:**
```java
try (Stream<String> lines = Files.lines(filePath)) {
    lines.parallel().forEach(line -> {
        // Process each line in parallel
        System.out.println(line);
    });
}
```

**Note:** Parallel streams work best when:
- The processing of each line is independent.
- The data source is sufficiently large to benefit from parallelism.

#### **D. Error Handling**
- Reading large files might encounter corrupt or unreadable lines.
- Handle exceptions for specific lines without terminating the whole process:
  ```java
  lines.forEach(line -> {
      try {
          // Process the line
      } catch (Exception e) {
          System.err.println("Error processing line: " + line);
      }
  });
  ```

---

### **3. Performance Considerations**

#### **Advantages:**
- **Memory Efficiency:** Lazily loads lines into memory.
- **Readability:** Stream API provides concise and declarative syntax.
- **Scalability:** Supports parallel processing for large datasets.

#### **Potential Issues:**
1. **File Handle Exhaustion:** Streams keep file handles open. Ensure they are closed properly.
2. **Garbage Collection:** Millions of objects created for each line can increase GC pressure.
3. **I/O Throughput:** Disk speed can limit performance.

---

### **4. Optimizing for Large Files**

#### **A. Batch Processing**
If processing involves external systems (e.g., database operations), process the file in batches:
```java
int batchSize = 1000;
try (Stream<String> lines = Files.lines(filePath)) {
    List<String> batch = new ArrayList<>();
    lines.forEach(line -> {
        batch.add(line);
        if (batch.size() == batchSize) {
            processBatch(batch);
            batch.clear();
        }
    });
    if (!batch.isEmpty()) {
        processBatch(batch);
    }
}
```

#### **B. Chunked Reading**
Split large files into smaller chunks and process them separately:
- Use external tools like `split` (Linux) or write a Java program to split files.

#### **C. Custom Thread Pool for Parallel Streams**
Default `ForkJoinPool` used by parallel streams may not fit your application's needs. Configure a custom thread pool if necessary:
```java
ForkJoinPool customThreadPool = new ForkJoinPool(4);
customThreadPool.submit(() -> {
    try (Stream<String> lines = Files.lines(filePath)) {
        lines.parallel().forEach(line -> {
            // Process each line
        });
    } catch (IOException e) {
        e.printStackTrace();
    }
}).join();
```

---

### **5. Conclusion**

- **Stream API** is well-suited for processing large files due to its lazy nature and ability to leverage parallelism.
- **Performance Considerations:** Be mindful of memory, file I/O bottlenecks, and parallelism overhead.
- **Optimizations:** Use batching, parallel processing, and custom thread pools when appropriate.

This approach ensures efficient handling of large files with minimal performance impact.

---

This is a common question when working with **multithreading** in Java. To understand why we **call `start()` instead of directly calling `run()`**, we need to clarify the behavior of each method in the `Thread` class.

### Key Points:  
1. **`run()` method**:
   - The `run()` method in the `Thread` class **contains the code to be executed** in the separate thread.
   - If you directly call the `run()` method, it will **not start a new thread**. Instead, it will execute the code **synchronously** within the current thread.

2. **`start()` method**:
   - The `start()` method is responsible for creating a **new thread** and then calling the `run()` method **asynchronously** in that newly created thread.
   - This method does not directly execute the `run()` method; instead, it **initiates the thread lifecycle** and delegates execution to the JVM.

---

### Why Not Call `run()` Directly?  

When you directly call the `run()` method:
- The `run()` code executes in the **current thread** instead of starting a new thread.
- The thread does not enter the **Runnable state** or transition through the typical thread lifecycle (`NEW` → `RUNNABLE` → `TERMINATED`).
- You lose the core benefit of multithreading, as the code will run sequentially in the calling thread.

---

### Example  

```java
class MyThread extends Thread {
    public void run() {
        System.out.println("Running in thread: " + Thread.currentThread().getName());
    }
}

public class ThreadExample {
    public static void main(String[] args) {
        MyThread t1 = new MyThread();

        // Case 1: Using start()
        t1.start(); // Starts a new thread and executes run() asynchronously
        System.out.println("Main thread: " + Thread.currentThread().getName());

        // Case 2: Calling run() directly
        MyThread t2 = new MyThread();
        t2.run(); // Executes run() in the main thread, NOT in a new thread
    }
}
```

#### Output (Example):
```
Running in thread: Thread-0    // Executed by t1's new thread
Main thread: main             // Printed by the main thread
Running in thread: main       // Executed by t2 in the main thread
```

---

### Explanation of Output:
1. **`t1.start()`**: Creates a **new thread** that executes the `run()` method, resulting in "Thread-0" printing.
2. **`t2.run()`**: Executes the `run()` method **synchronously in the main thread**, as no new thread is created.

---

### Lifecycle of a Thread (`start()` Behavior):
When you call `start()`, the following happens:
1. The thread enters the `NEW` state.
2. The JVM allocates resources for the new thread.
3. The thread is scheduled by the **Thread Scheduler** and moves to the `RUNNABLE` state.
4. The `run()` method is executed in the **new thread**.

If you directly call `run()`:
- Steps 1-3 are skipped.
- The code executes immediately in the current thread.

---

### Summary  
We call `start()` because it:
- **Creates a new thread** and executes the `run()` method in that thread.
- Allows the thread to go through its **lifecycle states** (`NEW`, `RUNNABLE`, etc.).
- Achieves **true multithreading**.

Calling `run()` directly defeats the purpose of multithreading, as it runs synchronously in the current thread.

---

Here is a comprehensive list of **multithreading interview questions** in Java, ranging from beginner to advanced levels. These questions are designed to evaluate a candidate's understanding of multithreading concepts, concurrency issues, thread safety, and Java-specific features.

---

## **1. Basic Multithreading Concepts**
1. **What is a thread in Java?**
   - Explain how threads work in Java.
2. **What is the difference between a process and a thread?**
3. **How do you create a thread in Java?**
   - Discuss `Thread` class vs `Runnable` interface.
4. **What is the lifecycle of a thread in Java?**
   - Explain the thread states: New, Runnable, Blocked, Waiting, Timed Waiting, and Terminated.
5. **What is the difference between `start()` and `run()` methods in threads?**
6. **What happens if we call `run()` directly instead of `start()`?**
7. **Can we restart a thread in Java once it has been started?**
8. **What is the default priority of a thread, and how can we change it?**
   - Discuss `Thread.NORM_PRIORITY`, `Thread.MIN_PRIORITY`, and `Thread.MAX_PRIORITY`.
9. **What is the difference between a user thread and a daemon thread?**
   - How do you create a daemon thread?
10. **How do you stop a thread in Java?**
    - Explain why `stop()` is deprecated and the alternatives like `interrupt()`.

---

## **2. Thread Synchronization**
11. **What is thread synchronization, and why is it important?**
12. **How do you achieve thread safety in Java?**
    - Discuss `synchronized` keyword.
13. **What is the difference between class-level and object-level locking?**
14. **What are the different ways to use the `synchronized` keyword?**
    - Methods, code blocks, and static synchronization.
15. **What is a monitor in Java?**
16. **What are deadlocks?**
    - Explain a deadlock scenario with an example.
17. **How do you avoid deadlocks in Java?**
18. **What is reentrant locking?**
    - Explain how `synchronized` in Java is reentrant.
19. **What are race conditions, and how do you prevent them?**
20. **What is a `volatile` keyword in Java?**
    - How is it different from `synchronized`?

---

## **3. Concurrency Utilities**
21. **What is the `java.util.concurrent` package? Why was it introduced?**
22. **What is `ReentrantLock` in Java? How is it different from `synchronized`?**
23. **What are the advantages of `ReentrantLock` over the `synchronized` keyword?**
24. **What is the difference between `tryLock()` and `lock()`?**
25. **What is a `Semaphore`, and how is it used?**
26. **What is a `CountDownLatch`? Provide a use case.**
27. **What is a `CyclicBarrier`, and how does it differ from `CountDownLatch`?**
28. **What is `ThreadLocal` in Java?**
    - How does it provide thread safety?
29. **What is an `ExecutorService`?**
    - Discuss the purpose of `Executors` and thread pools.
30. **What are the types of thread pools available in Java?**
    - FixedThreadPool, CachedThreadPool, SingleThreadExecutor, and ScheduledThreadPool.
31. **What is the difference between `submit()` and `execute()` methods of `ExecutorService`?**
32. **What is a `Future` in Java?**
    - How do you get results from a thread?
33. **What is the difference between `Callable` and `Runnable`?**
34. **What is a `BlockingQueue` in Java?**
    - Discuss the different implementations like `ArrayBlockingQueue` and `LinkedBlockingQueue`.
35. **What are `ConcurrentHashMap` and `CopyOnWriteArrayList`? How do they differ from their non-thread-safe counterparts?**

---

## **4. Thread Communication**
36. **How do threads communicate with each other in Java?**
    - Explain `wait()`, `notify()`, and `notifyAll()` methods.
37. **What is the significance of calling `wait()` and `notify()` inside a synchronized block?**
38. **What happens if `wait()` is called without acquiring a lock?**
39. **What is spurious wakeup, and how do you handle it?**
40. **What is the difference between `sleep()` and `wait()`?**
41. **What is the difference between `notify()` and `notifyAll()`?**
42. **How does thread communication work with condition variables?**
    - Explain using `Condition` with `ReentrantLock`.

---

## **5. Advanced Multithreading Concepts**
43. **What is a deadlock, livelock, and starvation in multithreading?**
44. **What is thread starvation, and how can you prevent it?**
45. **What is the **ForkJoin framework** in Java?**
    - Explain `ForkJoinPool` and its use case.
46. **What is the `CompletableFuture` class?**
    - Discuss its advantages over `Future`.
47. **What are `Phaser` and `Exchanger` in Java?**
48. **What are immutable objects, and how do they help in multithreading?**
49. **What is the **double-checked locking** pattern?**
    - Where is it used, and what problem does it solve?
50. **What is the `java.util.concurrent.atomic` package?**
    - Discuss `AtomicInteger`, `AtomicLong`, and `compareAndSet()` operations.
51. **How does the `CAS (Compare-And-Swap)` algorithm work?**
52. **What are the differences between `synchronized` and `Lock` interfaces?**
53. **How does the JVM handle thread scheduling?**
    - Explain **preemptive scheduling** and **time slicing**.
54. **What is the difference between `Thread.yield()` and `Thread.sleep()`?**

---

## **6. Best Practices and Real-World Scenarios**
55. **What are some best practices for writing multithreaded code in Java?**
56. **How do you handle thread interruptions?**
    - Discuss the `interrupt()` method and the `InterruptedException`.
57. **How do you debug and monitor threads in a Java application?**
    - Tools: VisualVM, JConsole, and thread dumps.
58. **How do you design a thread-safe singleton class?**
    - Discuss the **Bill Pugh Singleton** and **lazy initialization** approaches.
59. **How do you handle exceptions in threads?**
60. **What tools or approaches do you use for testing multithreaded code?**
    - Discuss the use of testing tools like **FindBugs**, **JUnit**, or **TestNG**.

---

## **7. Common Code Scenarios**
61. Write a program to create two threads:  
   - One thread prints **even numbers**, and the other thread prints **odd numbers**.  
   - The output should be in sequential order.
---
Here is a Java program that creates two threads to print even and odd numbers in **sequential order**. The program uses a **shared lock object** and `wait()`/`notify()` to synchronize the two threads. This ensures that the numbers are printed in the correct order.

### Code
```java
class PrintNumbers {
    private int limit;
    private int number = 1; // Start with 1
    
    public PrintNumbers(int limit) {
        this.limit = limit;
    }
    
    public void printOdd() {
        synchronized (this) {
            while (number <= limit) {
                if (number % 2 == 0) { // Check if it's even
                    try {
                        wait(); // Wait for the even thread
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Odd Thread: " + number);
                    number++;
                    notify(); // Notify the even thread
                }
            }
        }
    }
    
    public void printEven() {
        synchronized (this) {
            while (number <= limit) {
                if (number % 2 != 0) { // Check if it's odd
                    try {
                        wait(); // Wait for the odd thread
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Even Thread: " + number);
                    number++;
                    notify(); // Notify the odd thread
                }
            }
        }
    }
}

public class EvenOddThreads {
    public static void main(String[] args) {
        int limit = 20; // The maximum number to print
        PrintNumbers pn = new PrintNumbers(limit);
        
        // Thread for printing odd numbers
        Thread oddThread = new Thread(() -> pn.printOdd());
        
        // Thread for printing even numbers
        Thread evenThread = new Thread(() -> pn.printEven());
        
        // Start both threads
        oddThread.start();
        evenThread.start();
    }
}
```

---

### Explanation:
1. **Shared Resource**:
   - The `PrintNumbers` class is shared between both threads.
   - It has a `number` that keeps track of the current number to print.

2. **Synchronization**:
   - The `synchronized` block ensures only one thread prints at a time.
   - `wait()` makes a thread wait when it’s not its turn to print.
   - `notify()` wakes up the waiting thread to resume printing.

3. **Threads**:
   - One thread (`oddThread`) handles odd numbers.
   - The other thread (`evenThread`) handles even numbers.

4. **Sequential Output**:
   - The threads coordinate using `wait()` and `notify()` so they alternate and print numbers in order.

---

### Sample Output:
```
Odd Thread: 1
Even Thread: 2
Odd Thread: 3
Even Thread: 4
Odd Thread: 5
Even Thread: 6
Odd Thread: 7
Even Thread: 8
...
Even Thread: 20
```

The output will always be sequential, as the two threads communicate and wait for their turn to print.
---
62. Write a program that demonstrates deadlock between two threads.
A **deadlock** occurs in Java multithreading when two or more threads are blocked forever, each waiting for the other to release a lock. This situation typically arises when multiple threads need the same locks but acquire them in different orders. 

Here's an example demonstrating a deadlock between two threads:

```java
public class DeadlockDemo {

    public static void main(String[] args) {
        final Object resource1 = "Resource 1";
        final Object resource2 = "Resource 2";

        // Thread 1 tries to lock resource1 then resource2
        Thread thread1 = new Thread(() -> {
            synchronized (resource1) {
                System.out.println("Thread 1: locked resource 1");

                // Adding delay to simulate real-world scenario and ensure deadlock
                try { Thread.sleep(100); } catch (InterruptedException e) {}

                synchronized (resource2) {
                    System.out.println("Thread 1: locked resource 2");
                }
            }
        });

        // Thread 2 tries to lock resource2 then resource1
        Thread thread2 = new Thread(() -> {
            synchronized (resource2) {
                System.out.println("Thread 2: locked resource 2");

                // Adding delay to simulate real-world scenario and ensure deadlock
                try { Thread.sleep(100); } catch (InterruptedException e) {}

                synchronized (resource1) {
                    System.out.println("Thread 2: locked resource 1");
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}
```

**Explanation:**

- **Resources:** Two objects, `resource1` and `resource2`, represent the resources that the threads will attempt to lock.

- **Thread 1:** Locks `resource1` first, then attempts to lock `resource2`.

- **Thread 2:** Locks `resource2` first, then attempts to lock `resource1`.

**Deadlock Scenario:**

1. **Thread 1** locks `resource1` and sleeps for 100 milliseconds.

2. **Thread 2** locks `resource2` and sleeps for 100 milliseconds.

3. After waking up, **Thread 1** attempts to lock `resource2` but can't because **Thread 2** holds it.

4. After waking up, **Thread 2** attempts to lock `resource1` but can't because **Thread 1** holds it.

Both threads are now waiting indefinitely for each other to release the locks, resulting in a deadlock.

**Avoiding Deadlock:**

To prevent deadlocks, ensure that all threads acquire locks in the same order. In this example, if both threads attempted to lock `resource1` first and then `resource2`, the deadlock would not occur.

For a more in-depth understanding of deadlocks in Java multithreading, you can refer to this resource: 

Additionally, here's a video tutorial that explains deadlocks in Java multithreading:

 

---
64. Write a program that uses a `CountDownLatch` to ensure a task starts only after multiple threads complete their execution.  
65. Write a program to demonstrate the use of `BlockingQueue` for producer-consumer problems.  
66. Write a program to implement a thread-safe **singleton class**.

---

These questions are commonly asked in **multithreading interviews** to assess a developer's understanding of threads, synchronization, concurrency utilities, and how they can design thread-safe applications in Java. For senior roles, interviewers will often ask questions involving real-world problem-solving and advanced concurrency concepts.

---

Here is a list of **common multithreading code scenario questions** in Java. These are practical coding problems often asked in interviews to assess hands-on understanding of threads, synchronization, and concurrency.

---

## **1. Basic Thread Creation**
**Question:**  
Write a program to create two threads in Java:  
- One thread should print numbers from 1 to 5.  
- The other thread should print the square of those numbers.

---

## **2. Printing Even and Odd Numbers Sequentially**  
**Question:**  
Write a program to print even and odd numbers sequentially using two threads.  
- Thread 1 prints odd numbers.  
- Thread 2 prints even numbers.  
- The output should be in proper order: `1, 2, 3, 4, 5, ...`.

**Hint:** Use `synchronized` and `wait()/notify()` for thread communication.

---

## **3. Producer-Consumer Problem (BlockingQueue)**  
**Question:**  
Implement a producer-consumer problem in Java using `BlockingQueue`.  
- The producer thread generates numbers from 1 to 10.  
- The consumer thread consumes those numbers and prints them.

**Follow-Up:**  
- Solve it using wait/notify without using `BlockingQueue`.

---

## **4. Thread Deadlock Scenario**  
**Question:**  
Write a program to demonstrate a deadlock between two threads.  
- Use two shared resources (`Resource1` and `Resource2`).  
- Thread 1 locks Resource1 and then tries to lock Resource2.  
- Thread 2 locks Resource2 and then tries to lock Resource1.

**Follow-Up:**  
How can you resolve or prevent the deadlock?

---

## **5. Thread Synchronization Using Synchronized Methods**  
**Question:**  
Write a program where multiple threads try to increment a shared counter.  
- Use a synchronized method to ensure thread safety.  
- Print the final value of the counter after all threads have finished execution.

**Follow-Up:**  
- Solve the same problem using `ReentrantLock` instead of `synchronized`.

---

## **6. Print “Ping” and “Pong” Alternately**  
**Question:**  
Write a program with two threads:  
- One thread prints **"Ping"**.  
- The other thread prints **"Pong"**.  
- The output should alternate like: `Ping Pong Ping Pong...`.

**Hint:** Use `wait()` and `notify()` for proper thread coordination.

---

## **7. Thread-Safe Singleton Implementation**  
**Question:**  
Write a thread-safe Singleton class in Java.  
- Use lazy initialization.  
- Ensure the singleton instance is created only once in a multi-threaded environment.

**Follow-Up:**  
What are the pros and cons of using the **Double-Checked Locking** pattern?

---

## **8. Print Numbers in Sequential Order Using N Threads**  
**Question:**  
Write a program where `N` threads print numbers in sequential order.  
- For example, if `N=3`, Thread 1 prints 1, Thread 2 prints 2, Thread 3 prints 3, and then Thread 1 prints 4, and so on.

**Hint:** Use `wait()/notify()` for coordination between threads.

---

## **9. Implement a Custom Thread Pool**  
**Question:**  
Write a program to implement a simple thread pool in Java.  
- The thread pool should manage a fixed number of worker threads.  
- Submit multiple tasks to the thread pool for execution.

---

## **10. Dining Philosophers Problem**  
**Question:**  
Implement the **Dining Philosophers Problem** in Java using threads.  
- Five philosophers sit at a table with forks between them.  
- Each philosopher alternates between thinking and eating.  
- To eat, a philosopher needs to pick up two forks (shared resources).

**Hint:** Use **synchronization** or `ReentrantLock` to avoid deadlock.

---

## **11. Implement a CountdownLatch Example**  
**Question:**  
Write a program where a main thread waits for other worker threads to complete their tasks before proceeding further.  
- Use `CountDownLatch` to implement this.

**Example Scenario:**  
- Main thread waits for three worker threads to complete before printing "All tasks completed!".

---

## **12. Reader-Writer Problem**  
**Question:**  
Write a program to implement the **Reader-Writer problem** using threads.  
- Allow multiple reader threads to read the data simultaneously.  
- Ensure that writer threads have exclusive access to the data.

**Hint:** Use `ReentrantReadWriteLock`.

---

## **13. ThreadLocal Example**  
**Question:**  
Write a program to demonstrate the use of `ThreadLocal` in Java.  
- Each thread should have its own copy of a counter variable.  
- Increment and print the counter value from different threads.

---

## **14. Implement a Barrier Using CyclicBarrier**  
**Question:**  
Write a program where multiple threads perform partial computations and then wait at a barrier before proceeding to the next phase.  
- Use `CyclicBarrier` to synchronize the threads.

---

## **15. Simulate Bank Transactions with Deadlock-Free Solution**  
**Question:**  
Write a program where multiple threads perform bank transactions (transferring money between accounts).  
- Ensure thread safety.  
- Avoid deadlocks when accessing two accounts simultaneously.

---

## **16. ScheduledExecutorService Example**  
**Question:**  
Write a program to schedule a task to run at a fixed interval using `ScheduledExecutorService`.

**Example Use Case:**  
Print a message every 2 seconds.

---

## **17. Merge Two Sorted Arrays Using Threads**  
**Question:**  
Write a program where two threads merge two sorted arrays into a single sorted array.  
- Thread 1 merges the first half.  
- Thread 2 merges the second half.  
- Main thread combines the results.

---

## **18. Thread Interruption Example**  
**Question:**  
Write a program to demonstrate how to properly interrupt a thread in Java.  
- Use the `interrupt()` method.  
- Ensure the thread checks for interruptions and handles it gracefully.

---

## **19. Implement a Custom Blocking Queue**  
**Question:**  
Write a program to implement a custom thread-safe blocking queue using `wait()` and `notify()`.  
- The queue should support `enqueue()` and `dequeue()` operations.  
- Block producer threads if the queue is full.  
- Block consumer threads if the queue is empty.

---

## **20. Simulate Multi-Threaded File Processing**  
**Question:**  
Write a program where multiple threads process a large file concurrently.  
- Each thread processes a specific part of the file.  
- Combine the results after all threads finish processing.

---

## **21. Simple Thread Sleep Example**  
**Question:**  
Write a program where two threads print numbers 1 to 5, but each thread sleeps for a different duration.

---

## **22. Thread Join Example**  
**Question:**  
Write a program where the main thread waits for multiple worker threads to finish using `join()`.

---

## **23. Implement a Thread-Safe Counter**  
**Question:**  
Write a program to increment a counter from multiple threads.  
- Ensure thread safety using:  
   - `synchronized`  
   - `ReentrantLock`  
   - `AtomicInteger`  

---

## **24. Parallel Array Sum Calculation**  
**Question:**  
Write a program to calculate the sum of a large array using multiple threads.  
- Divide the array into equal parts.  
- Each thread computes the sum of its part.  
- Combine the partial results.

---

## **25. Multithreaded Matrix Multiplication**  
**Question:**  
Write a program to perform matrix multiplication using multiple threads.  
- Each thread calculates one row or block of the resultant matrix.

---

These **common coding scenarios** help you demonstrate your understanding of **multithreading** concepts such as thread creation, synchronization, deadlocks, concurrent utilities, and problem-solving. Being able to write and debug these programs is critical for acing Java multithreading interviews.

---

The lifecycle of a thread in Java consists of several states that a thread goes through during its lifetime. These states are defined in the `java.lang.Thread.State` enumeration. The thread states are as follows:

### 1. **New**  
- **Description:** A thread is created but not yet started.
- **How it happens:** When a thread object is instantiated using the `Thread` class or implementing the `Runnable` interface, it is in the `NEW` state.
- **Transition:** The thread moves to the `Runnable` state when the `start()` method is called.

**Code Example:**
```java
Thread thread = new Thread(() -> System.out.println("Thread is running"));
System.out.println("Thread state: " + thread.getState()); // Output: NEW
```

---

### 2. **Runnable**  
- **Description:** The thread is ready to run and waiting for CPU time.
- **How it happens:** After calling `start()`, the thread is in the `Runnable` state. Note that it might not run immediately because the operating system's thread scheduler decides when it will run.
- **Transition:** It can move to the `Running` state when the thread gets CPU time.

**Code Example:**
```java
thread.start(); // Now the thread is in the Runnable state.
System.out.println("Thread state: " + thread.getState()); // Output: RUNNABLE
```

---

### 3. **Running**  
- **Description:** The thread is executing its `run()` method.
- **How it happens:** The thread scheduler selects the thread and allocates CPU time to it.
- **Transition:** It can move to the `Blocked`, `Waiting`, `Timed_Waiting`, or `Terminated` state based on thread operations.

**Note:** The `Running` state is not explicitly represented in `Thread.State`.

---

### 4. **Waiting**  
- **Description:** The thread is waiting indefinitely for another thread to perform a specific action.
- **How it happens:** The thread enters the `Waiting` state when methods like `Object.wait()` or `Thread.join()` are called without a timeout.
- **Transition:** It moves back to the `Runnable` state when it is notified or the waiting action is complete.

**Code Example:**
```java
Thread t1 = new Thread(() -> {
    try {
        t2.join(); // t1 waits for t2 to finish.
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
});
Thread t2 = new Thread(() -> System.out.println("Thread t2 is running"));

t1.start();
t2.start();
```

---

### 5. **Timed_Waiting**  
- **Description:** The thread is waiting for another thread to perform a specific action for a limited amount of time.
- **How it happens:** Methods like `Thread.sleep()`, `Object.wait(timeout)`, or `Thread.join(timeout)` are called.
- **Transition:** The thread moves to the `Runnable` state when the specified time elapses or the waiting action is completed.

**Code Example:**
```java
Thread thread = new Thread(() -> {
    try {
        Thread.sleep(1000); // The thread enters the Timed_Waiting state.
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
});
thread.start();
```

---

### 6. **Blocked**  
- **Description:** The thread is waiting to acquire a lock to enter a synchronized block or method.
- **How it happens:** A thread enters the `Blocked` state when it tries to access a synchronized block or method that is already locked by another thread.
- **Transition:** It moves to the `Runnable` state once it acquires the lock.

**Code Example:**
```java
class SharedResource {
    synchronized void sharedMethod() {
        try {
            Thread.sleep(1000); // Simulate work.
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

SharedResource resource = new SharedResource();

Thread t1 = new Thread(() -> resource.sharedMethod());
Thread t2 = new Thread(() -> resource.sharedMethod());

t1.start();
t2.start(); // t2 will enter the Blocked state while t1 holds the lock.
```

---

### 7. **Terminated (Dead)**  
- **Description:** The thread has finished its execution.
- **How it happens:** A thread enters this state when the `run()` method completes or an unhandled exception terminates it.
- **Transition:** A terminated thread cannot be restarted.

**Code Example:**
```java
Thread thread = new Thread(() -> System.out.println("Thread is running"));
thread.start();

try {
    thread.join(); // Wait for the thread to finish.
} catch (InterruptedException e) {
    e.printStackTrace();
}
System.out.println("Thread state: " + thread.getState()); // Output: TERMINATED
```

---

### Summary of Transitions
The following diagram summarizes the lifecycle:

```
NEW --> RUNNABLE --> RUNNING --> (WAITING | TIMED_WAITING | BLOCKED) --> TERMINATED
```

By understanding these states and transitions, you can write multithreaded applications that handle concurrency more effectively.

---
Java Stream API provides a powerful way to process collections of data. Operations on streams can be classified into two types: **intermediate** and **terminal** operations.

---

### **Intermediate Operations**
Intermediate operations transform a stream into another stream. They are **lazy**, meaning they don’t execute until a terminal operation is invoked.

#### Common Intermediate Operations

1. **`filter`**
   - Filters elements based on a predicate.
   ```java
   List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
   List<Integer> evens = numbers.stream()
                                .filter(n -> n % 2 == 0)
                                .collect(Collectors.toList());
   System.out.println(evens); // [2, 4]
   ```

2. **`map`**
   - Transforms each element in the stream.
   ```java
   List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
   List<Integer> nameLengths = names.stream()
                                    .map(String::length)
                                    .collect(Collectors.toList());
   System.out.println(nameLengths); // [5, 3, 7]
   ```

3. **`flatMap`**
   - Flattens nested collections or streams.
   ```java
   List<List<Integer>> listOfLists = Arrays.asList(Arrays.asList(1, 2), Arrays.asList(3, 4));
   List<Integer> flatList = listOfLists.stream()
                                       .flatMap(List::stream)
                                       .collect(Collectors.toList());
   System.out.println(flatList); // [1, 2, 3, 4]
   ```

4. **`distinct`**
   - Removes duplicate elements.
   ```java
   List<Integer> numbers = Arrays.asList(1, 2, 2, 3, 3, 3, 4);
   List<Integer> distinctNumbers = numbers.stream()
                                          .distinct()
                                          .collect(Collectors.toList());
   System.out.println(distinctNumbers); // [1, 2, 3, 4]
   ```

5. **`sorted`**
   - Sorts elements in natural or custom order.
   ```java
   List<String> names = Arrays.asList("Charlie", "Alice", "Bob");
   List<String> sortedNames = names.stream()
                                   .sorted()
                                   .collect(Collectors.toList());
   System.out.println(sortedNames); // [Alice, Bob, Charlie]
   ```

6. **`limit`**
   - Limits the number of elements.
   ```java
   List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
   List<Integer> limited = numbers.stream()
                                  .limit(3)
                                  .collect(Collectors.toList());
   System.out.println(limited); // [1, 2, 3]
   ```

7. **`skip`**
   - Skips a given number of elements.
   ```java
   List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
   List<Integer> skipped = numbers.stream()
                                  .skip(2)
                                  .collect(Collectors.toList());
   System.out.println(skipped); // [3, 4, 5]
   ```

---

### **Terminal Operations**
Terminal operations produce a result or side effect. They are **eager**, meaning they trigger the pipeline execution.

#### Common Terminal Operations

1. **`collect`**
   - Collects elements into a collection or other forms.
   ```java
   List<Integer> numbers = Arrays.asList(1, 2, 3);
   List<Integer> collected = numbers.stream()
                                    .collect(Collectors.toList());
   System.out.println(collected); // [1, 2, 3]
   ```

2. **`forEach`**
   - Performs an action on each element.
   ```java
   List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
   names.stream().forEach(System.out::println);
   // Alice
   // Bob
   // Charlie
   ```

3. **`reduce`**
   - Reduces elements to a single value.
   ```java
   List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
   int sum = numbers.stream()
                    .reduce(0, Integer::sum);
   System.out.println(sum); // 10
   ```

4. **`toArray`**
   - Converts elements to an array.
   ```java
   List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
   String[] array = names.stream().toArray(String[]::new);
   System.out.println(Arrays.toString(array)); // [Alice, Bob, Charlie]
   ```

5. **`anyMatch`**
   - Checks if any element matches a predicate.
   ```java
   List<Integer> numbers = Arrays.asList(1, 2, 3);
   boolean hasEven = numbers.stream().anyMatch(n -> n % 2 == 0);
   System.out.println(hasEven); // true
   ```

6. **`allMatch`**
   - Checks if all elements match a predicate.
   ```java
   List<Integer> numbers = Arrays.asList(2, 4, 6);
   boolean allEven = numbers.stream().allMatch(n -> n % 2 == 0);
   System.out.println(allEven); // true
   ```

7. **`noneMatch`**
   - Checks if no elements match a predicate.
   ```java
   List<Integer> numbers = Arrays.asList(1, 3, 5);
   boolean noneEven = numbers.stream().noneMatch(n -> n % 2 == 0);
   System.out.println(noneEven); // true
   ```

8. **`count`**
   - Counts the number of elements.
   ```java
   List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
   long count = numbers.stream().count();
   System.out.println(count); // 4
   ```

9. **`findFirst`**
   - Finds the first element, if available.
   ```java
   List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
   Optional<String> first = names.stream().findFirst();
   first.ifPresent(System.out::println); // Alice
   ```

10. **`findAny`**
    - Finds any element (useful in parallel streams).
    ```java
    List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
    Optional<String> any = names.stream().findAny();
    any.ifPresent(System.out::println); // Could print any element
    ```

---

### Summary Table

| **Operation**   | **Type**      | **Description**                                           |
|------------------|---------------|-----------------------------------------------------------|
| `filter`         | Intermediate  | Filters elements based on a predicate.                   |
| `map`            | Intermediate  | Transforms elements.                                      |
| `flatMap`        | Intermediate  | Flattens nested streams.                                  |
| `distinct`       | Intermediate  | Removes duplicates.                                       |
| `sorted`         | Intermediate  | Sorts elements.                                           |
| `limit`          | Intermediate  | Limits the number of elements.                           |
| `skip`           | Intermediate  | Skips the first n elements.                              |
| `collect`        | Terminal      | Collects elements into a collection.                     |
| `forEach`        | Terminal      | Performs an action on each element.                      |
| `reduce`         | Terminal      | Reduces elements to a single value.                      |
| `toArray`        | Terminal      | Converts elements to an array.                           |
| `anyMatch`       | Terminal      | Checks if any element matches a predicate.               |
| `allMatch`       | Terminal      | Checks if all elements match a predicate.                |
| `noneMatch`      | Terminal      | Checks if no elements match a predicate.                 |
| `count`          | Terminal      | Counts the elements.                                      |
| `findFirst`      | Terminal      | Finds the first element.                                  |
| `findAny`        | Terminal      | Finds any element (useful in parallel streams).          |

By combining intermediate and terminal operations, you can create powerful and concise data processing pipelines.


---

### **Difference Between Multithreading and Concurrency**

| **Aspect**              | **Multithreading**                                                                                      | **Concurrency**                                                                                   |
|-------------------------|--------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------|
| **Definition**           | A programming technique where multiple threads run in the same process to perform tasks in parallel.  | The ability of a system to handle multiple tasks at the same time by managing their execution.    |
| **Execution Context**    | Focuses on running multiple threads simultaneously within a single process.                           | Focuses on managing multiple tasks, which can be threads, processes, or even distributed systems. |
| **Parallelism**          | Can lead to parallel execution of threads if running on a multi-core processor.                       | Does not necessarily mean parallel execution; tasks may run sequentially but appear simultaneous. |
| **Resource Sharing**     | Threads share the same memory space and resources of the process.                                     | Can involve shared or separate resources, depending on whether it uses threads or processes.      |
| **Scope**                | Limited to threads within a process.                                                                 | Broader, encompassing multitasking, multithreading, and distributed systems.                     |
| **Primary Goal**         | Increase application performance through parallelism.                                                 | Efficiently manage multiple tasks or workflows, with or without parallelism.                     |
| **Complexity**           | Typically involves managing shared resources and synchronization between threads.                     | Involves coordination of tasks, which can be threads, processes, or I/O operations.              |

---

### **Key Differences Explained**

1. **Multithreading:**
   - Refers specifically to using multiple threads in a single process.
   - Threads within a process share the same memory, making communication efficient but requiring careful synchronization to avoid issues like race conditions or deadlocks.
   - Example:
     - A web server using multithreading to handle multiple client requests, where each thread processes one client.

2. **Concurrency:**
   - Refers to the general concept of managing multiple tasks simultaneously, whether they are threads, processes, or asynchronous operations.
   - Focuses on task interleaving and resource management, which can involve multithreading or multiprocessing.
   - Example:
     - An operating system running multiple applications simultaneously, where each application is a separate process.

---

### **Analogy to Understand:**
- **Multithreading:** Imagine a restaurant with a single kitchen where multiple chefs (threads) work simultaneously to prepare dishes.
- **Concurrency:** Imagine a restaurant where waiters, chefs, and cleaning staff (different tasks) operate concurrently to ensure smooth operations. Not all activities are simultaneous, but they are managed effectively to avoid bottlenecks.

---

### **Examples in Code:**

#### **Multithreading Example:**
```java
public class MultithreadingExample extends Thread {
    public void run() {
        System.out.println(Thread.currentThread().getName() + " is running.");
    }

    public static void main(String[] args) {
        MultithreadingExample thread1 = new MultithreadingExample();
        MultithreadingExample thread2 = new MultithreadingExample();
        thread1.start();
        thread2.start();
    }
}
```

Output:
- Threads may execute in parallel or interleave depending on the CPU.

#### **Concurrency Example with Asynchronous Tasks:**
```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrencyExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        Runnable task1 = () -> System.out.println("Task 1 running");
        Runnable task2 = () -> System.out.println("Task 2 running");

        executor.submit(task1);
        executor.submit(task2);

        executor.shutdown();
    }
}
```

Output:
- Tasks are managed concurrently, which may involve thread-pooling or scheduling.

---

### **Conclusion:**
- **Multithreading** is a specific implementation of **concurrency**.  
- Concurrency is a broader concept encompassing multiple paradigms to handle multiple tasks effectively, with or without parallelism.

---
### In Java, threads can be created and run in several ways. Here are the primary methods along with detailed examples:


### **1. Extending the `Thread` Class**
- Create a class that extends `Thread`.
- Override the `run()` method to define the thread's task.
- Start the thread using the `start()` method.

**Example:**
```java
class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " is running.");
    }
}

public class ThreadExample1 {
    public static void main(String[] args) {
        MyThread thread1 = new MyThread();
        MyThread thread2 = new MyThread();
        
        thread1.start(); // Starts a new thread
        thread2.start();
    }
}
```

---

### **2. Implementing the `Runnable` Interface**
- Create a class that implements `Runnable`.
- Define the thread's task in the `run()` method.
- Pass the `Runnable` object to a `Thread` object and call `start()`.

**Example:**
```java
class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " is running.");
    }
}

public class ThreadExample2 {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new MyRunnable());
        Thread thread2 = new Thread(new MyRunnable());
        
        thread1.start();
        thread2.start();
    }
}
```

---

### **3. Using Lambda Expressions (Java 8+)**
- Use a lambda expression to define the `Runnable` task and pass it to a `Thread` object.

**Example:**
```java
public class ThreadExample3 {
    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " is running.");
        });

        thread1.start();
    }
}
```

---

### **4. Using `ExecutorService` (Thread Pool)**
- Use the `ExecutorService` framework to manage threads efficiently.
- Submit tasks using the `submit()` or `execute()` method.

**Example:**
```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadExample4 {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        Runnable task = () -> {
            System.out.println(Thread.currentThread().getName() + " is running.");
        };

        executor.submit(task);
        executor.submit(task);
        executor.shutdown(); // Graceful shutdown
    }
}
```

---

### **5. Using the `Callable` Interface with `Future`**
- `Callable` allows returning a value and throwing exceptions.
- Submit `Callable` tasks to an `ExecutorService` and use `Future` to retrieve results.

**Example:**
```java
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadExample5 {
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        Callable<String> task = () -> {
            return Thread.currentThread().getName() + " is running.";
        };

        Future<String> result = executor.submit(task);

        System.out.println(result.get()); // Get the result of the Callable
        executor.shutdown();
    }
}
```

---

### **6. Using `Timer` for Scheduled Threads**
- Use the `Timer` class to schedule tasks at fixed intervals or delays.

**Example:**
```java
import java.util.Timer;
import java.util.TimerTask;

public class ThreadExample6 {
    public static void main(String[] args) {
        Timer timer = new Timer();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " is running.");
            }
        };

        timer.schedule(task, 1000, 2000); // Delay of 1 second, repeat every 2 seconds
    }
}
```

---

### **7. Using `ForkJoinPool` (Java 7+)**
- Use `ForkJoinPool` for parallelism with divide-and-conquer algorithms.

**Example:**
```java
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ForkJoinPool;

class SumTask extends RecursiveTask<Integer> {
    private int[] array;
    private int start, end;

    public SumTask(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if (end - start <= 5) {
            int sum = 0;
            for (int i = start; i < end; i++) {
                sum += array[i];
            }
            return sum;
        } else {
            int mid = (start + end) / 2;
            SumTask leftTask = new SumTask(array, start, mid);
            SumTask rightTask = new SumTask(array, mid, end);
            leftTask.fork();
            return rightTask.compute() + leftTask.join();
        }
    }
}

public class ThreadExample7 {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        ForkJoinPool pool = new ForkJoinPool();
        SumTask task = new SumTask(array, 0, array.length);
        System.out.println("Sum: " + pool.invoke(task));
    }
}
```

---

### **8. Using `CompletableFuture` (Java 8+)**
- Use `CompletableFuture` for asynchronous programming and thread management.

**Example:**
```java
import java.util.concurrent.CompletableFuture;

public class ThreadExample8 {
    public static void main(String[] args) {
        CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " is running asynchronously.");
        });
    }
}
```

---

### **Summary**
Each method serves different use cases:
- Use **Thread** or **Runnable** for simple tasks.
- Use **ExecutorService** for thread pool management.
- Use **ForkJoinPool** for parallel computation.
- Use **CompletableFuture** for asynchronous tasks.
- Use **Callable** if you need results or exception handling.

---

Both `SynchronizedHashMap` and `ConcurrentHashMap` are used for thread-safe operations on maps in Java, but they have key differences in their performance and behavior. Choosing between them depends on your use case.

---

## **1. SynchronizedHashMap**
A `SynchronizedHashMap` is simply a `HashMap` that is wrapped with synchronization using `Collections.synchronizedMap()`. 

```java
Map<String, String> syncMap = Collections.synchronizedMap(new HashMap<>());
```

### **When to Use SynchronizedHashMap**
- **Low concurrency needs** – If multiple threads only occasionally access the map, this approach might be sufficient.
- **External synchronization possible** – Iterating over a `SynchronizedHashMap` requires external synchronization to avoid `ConcurrentModificationException`.
- **Small-scale applications** – If performance is not a concern, but you still need thread safety, this is a simple option.

### **Drawbacks**
- Synchronizes the entire map for every read/write operation, leading to poor performance under high concurrency.
- Requires explicit synchronization during iteration.

Example of required external synchronization:
```java
synchronized(syncMap) {
    for (Map.Entry<String, String> entry : syncMap.entrySet()) {
        System.out.println(entry.getKey() + " => " + entry.getValue());
    }
}
```

---

## **2. ConcurrentHashMap**
`ConcurrentHashMap` is a more sophisticated thread-safe alternative designed for high-concurrency scenarios.

```java
Map<String, String> concurrentMap = new ConcurrentHashMap<>();
```

### **When to Use ConcurrentHashMap**
- **High concurrency needs** – Multiple threads frequently read/write to the map.
- **Better performance** – Uses a segmented locking mechanism (instead of locking the entire map).
- **Iteration without explicit synchronization** – Uses weakly consistent iterators, which do not throw `ConcurrentModificationException`.

### **Advantages**
- **Lock-free read operations** – Read operations do not block.
- **Fine-grained locking** – Updates lock only specific segments rather than the entire map.
- **Higher throughput under multi-threading** – Significantly faster than `SynchronizedHashMap` in concurrent environments.

### **Limitations**
- Does **not** allow `null` keys or values.
- Is **not** a direct replacement for `HashMap` if full consistency is required (since iterators are weakly consistent).

---

## **Comparison Table**

| Feature               | SynchronizedHashMap                              | ConcurrentHashMap                             |
|----------------------|--------------------------------|--------------------------------|
| **Thread Safety**   | Yes (full synchronization)    | Yes (fine-grained locks)      |
| **Performance**     | Slower due to full locking    | Faster due to segment-level locking |
| **Concurrency**     | Low to moderate               | High                           |
| **Iteration**       | Needs external synchronization | No explicit synchronization needed (Weakly Consistent Iterator) |
| **Null Keys/Values** | Allowed                        | Not allowed                    |
| **Use Case**        | Simple multi-threaded apps    | High-performance concurrent applications |

---

### **Final Recommendation**
- Use **`SynchronizedHashMap`** when you have low concurrency and need simple synchronization.
- Use **`ConcurrentHashMap`** for high-concurrency environments where multiple threads frequently modify the map.

---

### Difference Between `HashMap` and `TreeMap` in Java

Both `HashMap` and `TreeMap` are implementations of the `Map` interface in Java, but they have significant differences in terms of performance, ordering, and underlying data structures.

| Feature          | `HashMap` | `TreeMap` |
|-----------------|----------|----------|
| **Ordering**    | No ordering (unsorted) | Sorted in natural order (or custom `Comparator`) |
| **Implementation** | Uses a **hash table** | Uses a **red-black tree** (self-balancing binary search tree) |
| **Performance (Time Complexity)** | `O(1)` for `put()`, `get()`, `remove()` (on average) | `O(log n)` for `put()`, `get()`, `remove()` |
| **Null Keys** | Allows one `null` key | Does **not** allow `null` key |
| **Null Values** | Allows multiple `null` values | Allows multiple `null` values |
| **Usage** | Best for **fast lookups** when ordering is not needed | Best when **sorted order** is required |

---

## Example Demonstrating `HashMap` and `TreeMap`
```java
import java.util.*;

public class MapExample {
    public static void main(String[] args) {
        // HashMap Example (No ordering)
        Map<Integer, String> hashMap = new HashMap<>();
        hashMap.put(3, "Apple");
        hashMap.put(1, "Banana");
        hashMap.put(2, "Cherry");
        hashMap.put(5, "Mango");
        hashMap.put(4, "Grapes");

        System.out.println("HashMap (Unordered): " + hashMap);

        // TreeMap Example (Sorted by keys)
        Map<Integer, String> treeMap = new TreeMap<>();
        treeMap.putAll(hashMap);

        System.out.println("TreeMap (Sorted by keys): " + treeMap);
    }
}
```

### **Output Example**
```
HashMap (Unordered): {3=Apple, 1=Banana, 2=Cherry, 5=Mango, 4=Grapes}
TreeMap (Sorted by keys): {1=Banana, 2=Cherry, 3=Apple, 4=Grapes, 5=Mango}
```

### **Key Takeaways**
1. **HashMap does not guarantee order**, so elements appear in an arbitrary sequence.
2. **TreeMap maintains elements in sorted order** based on their keys.
3. **HashMap is faster (`O(1)`)** for insertion and lookup compared to **TreeMap (`O(log n)`)** due to the difference in underlying data structures.
4. **TreeMap is useful when ordered traversal is needed** (e.g., in applications where data must be accessed in a sorted manner).

---

Here are some advanced Java collections interview questions along with answers that would be helpful for experienced professionals:

In Java, `HashMap`, `SynchronizedMap`, and `ConcurrentHashMap` are part of the Java Collections Framework, and they serve different purposes depending on the need for thread safety and performance. Here's how you can create and use each:

### 1. **HashMap**
`HashMap` is not thread-safe and should be used only in single-threaded environments or where thread safety is handled externally.

```java
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        HashMap<Integer, String> hashMap = new HashMap<>();
        hashMap.put(1, "Value1");
        hashMap.put(2, "Value2");
        System.out.println(hashMap);
    }
}
```

### 2. **SynchronizedMap**
`SynchronizedMap` wraps a `HashMap` to make it thread-safe. Use it when you need thread-safe operations but can tolerate lower performance due to synchronized methods.

```java
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<Integer, String> synchronizedMap = Collections.synchronizedMap(new HashMap<>());
        synchronizedMap.put(1, "Value1");
        synchronizedMap.put(2, "Value2");

        // Synchronize block when iterating
        synchronized (synchronizedMap) {
            for (Map.Entry<Integer, String> entry : synchronizedMap.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        }
    }
}
```

### 3. **ConcurrentHashMap**
`ConcurrentHashMap` is thread-safe and designed for high concurrency. It uses a fine-grained locking mechanism to allow concurrent reads and writes.

```java
import java.util.concurrent.ConcurrentHashMap;

public class Main {
    public static void main(String[] args) {
        ConcurrentHashMap<Integer, String> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put(1, "Value1");
        concurrentHashMap.put(2, "Value2");

        concurrentHashMap.forEach((key, value) -> {
            System.out.println(key + ": " + value);
        });
    }
}
```

### Key Differences:
| Feature                  | HashMap         | SynchronizedMap                     | ConcurrentHashMap               |
|--------------------------|-----------------|-------------------------------------|---------------------------------|
| **Thread Safety**        | No              | Yes (single lock for all operations) | Yes (fine-grained locks)       |
| **Performance**          | High            | Low (due to global lock)            | High (due to concurrent locks) |
| **Null Keys/Values**     | Allows 1 null key and multiple null values | Same as `HashMap`                | No null keys or values allowed |
| **Concurrency Level**    | N/A             | Single-threaded operations          | Multiple threads concurrently  |

Choose the appropriate map type based on your use case:
- Use `HashMap` for single-threaded applications.
- Use `SynchronizedMap` for simple thread-safe needs.
- Use `ConcurrentHashMap` for high-performance, thread-safe applications.

### 1. **What is the difference between `HashMap` and `ConcurrentHashMap`?**
   - **Answer**: 
      - `HashMap` is not thread-safe, meaning it is not designed for concurrent access. If multiple threads modify it simultaneously, it can lead to unpredictable behavior, like data corruption.
      - `ConcurrentHashMap` is a thread-safe version of `HashMap`. It uses segment-based locking (in Java 7 and below) or internal granularity locking with `compute()` methods (in Java 8+), which allows for more efficient concurrent access.
      - `HashMap` allows `null` values for keys and values, but `ConcurrentHashMap` does not allow `null` for either.

### 2. **How does `HashMap` handle collisions, and what are hash collisions?**
   - **Answer**: 
      - A hash collision occurs when two distinct keys produce the same hash code. `HashMap` handles collisions using chaining, which means it stores multiple elements in a single bucket as a linked list. Starting from Java 8, when a bucket's size exceeds a threshold (typically 8), the linked list is replaced by a balanced tree, improving search performance to O(log n) from O(n) in cases with a large number of collisions.
---    
### **Difference Between HashMap and SynchronizedHashMap**

| **Aspect**                  | **HashMap**                                                                                                     | **SynchronizedHashMap**                                                                                   |
|-----------------------------|-----------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------|
| **Thread-Safety**            | Not thread-safe. Concurrent modifications can cause `ConcurrentModificationException`.                         | Thread-safe. Operations are synchronized to allow safe usage in multithreaded environments.               |
| **Performance**              | Faster in single-threaded environments because it doesn’t incur synchronization overhead.                      | Slower due to synchronization overhead in multithreaded environments.                                     |
| **Synchronization**          | No synchronization. Multiple threads can access it simultaneously, leading to potential data inconsistency.    | Synchronization ensures only one thread can access the map at a time, preventing data inconsistency.       |
| **Usage Scenario**           | Suitable for single-threaded applications or scenarios where thread-safety is managed externally.              | Suitable for multithreaded applications where the map is accessed by multiple threads concurrently.        |
| **Implementation**           | Part of `java.util` package.                                                                                   | Can be created using `Collections.synchronizedMap()` wrapper on a `HashMap`.                              |

---

### **Detailed Example: HashMap**

In a **HashMap**, concurrent modifications by multiple threads may lead to inconsistent state or exceptions.

```java
import java.util.HashMap;

public class HashMapExample {
    public static void main(String[] args) {
        HashMap<Integer, String> hashMap = new HashMap<>();

        // Adding elements to HashMap
        hashMap.put(1, "One");
        hashMap.put(2, "Two");
        hashMap.put(3, "Three");

        // Accessing HashMap in multiple threads
        Runnable task = () -> {
            for (int i = 1; i <= 3; i++) {
                System.out.println(Thread.currentThread().getName() + " - " + hashMap.get(i));
            }
        };

        Thread thread1 = new Thread(task, "Thread 1");
        Thread thread2 = new Thread(task, "Thread 2");

        thread1.start();
        thread2.start();
    }
}
```

**Output:**
The above code may result in inconsistent outputs or a `ConcurrentModificationException` if threads modify the map simultaneously.

---

### **Detailed Example: SynchronizedHashMap**

In a **SynchronizedHashMap**, all operations are synchronized, ensuring thread safety.

```java
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SynchronizedHashMapExample {
    public static void main(String[] args) {
        Map<Integer, String> synchronizedMap = Collections.synchronizedMap(new HashMap<>());

        // Adding elements to SynchronizedHashMap
        synchronizedMap.put(1, "One");
        synchronizedMap.put(2, "Two");
        synchronizedMap.put(3, "Three");

        // Accessing SynchronizedHashMap in multiple threads
        Runnable task = () -> {
            synchronized (synchronizedMap) { // Required for iteration to avoid ConcurrentModificationException
                for (Map.Entry<Integer, String> entry : synchronizedMap.entrySet()) {
                    System.out.println(Thread.currentThread().getName() + " - Key: " + entry.getKey() + ", Value: " + entry.getValue());
                }
            }
        };

        Thread thread1 = new Thread(task, "Thread 1");
        Thread thread2 = new Thread(task, "Thread 2");

        thread1.start();
        thread2.start();
    }
}
```

**Output:**
The synchronized map ensures consistent and safe outputs even with multiple threads accessing it.

---
The **load factor** in a `HashMap` is a measure of how full the hash table can get before it needs to be resized. It plays a crucial role in the performance and efficiency of the `HashMap`.

### Key Points About Load Factor:
1. **Definition**:
   The load factor is a fraction that determines the threshold for resizing. It is calculated as:
   \[
   \text{Load Factor} = \frac{\text{Number of Entries in the Map}}{\text{Capacity of the Hash Table}}
   \]

2. **Default Load Factor**:
   - The default load factor in Java's `HashMap` is **0.75**.
   - This means the hash table will resize (i.e., double its capacity) when the map is 75% full.

3. **Resizing**:
   - When the number of entries exceeds the product of the current capacity and the load factor, the hash table is resized.
   - Resizing involves creating a new hash table with double the capacity and rehashing all existing entries into the new table.

4. **Effect on Performance**:
   - **Lower Load Factor**: 
     - Reduces the likelihood of collisions (entries hashing to the same bucket), improving lookup time.
     - Increases memory usage because the table needs more space.
   - **Higher Load Factor**: 
     - Increases the likelihood of collisions, potentially reducing performance.
     - Decreases memory usage as fewer resizes are required.

### How It Works Internally:
1. When you create a `HashMap`, you can specify the **initial capacity** and **load factor**:
   ```java
   HashMap<Integer, String> map = new HashMap<>(initialCapacity, loadFactor);
   ```

2. During put operations, the map checks if the current size exceeds the threshold:
   \[
   \text{Threshold} = \text{Capacity} \times \text{Load Factor}
   \]
   If exceeded, it triggers a resize.

3. **Example**:
   - Suppose you create a `HashMap` with an initial capacity of 16 and a default load factor of 0.75.
   - The threshold is \( 16 \times 0.75 = 12 \).
   - After adding the 13th element, the `HashMap` resizes, doubling its capacity to 32.

### Example in Code:
```java
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        HashMap<Integer, String> map = new HashMap<>(4, 0.75f); // Initial capacity 4, load factor 0.75

        // Adding entries
        map.put(1, "One");
        map.put(2, "Two");
        map.put(3, "Three");
        map.put(4, "Four");

        // The next entry will exceed the threshold (4 * 0.75 = 3) and trigger resizing
        map.put(5, "Five");

        System.out.println("Map Size: " + map.size());
        System.out.println("Map Contents: " + map);
    }
}
```

### Output:
```plaintext
Map Size: 5
Map Contents: {1=One, 2=Two, 3=Three, 4=Four, 5=Five}
```

Here, you can observe that resizing happens once the load factor threshold is exceeded.
---

### **Key Notes**
- **Iteration:** Even with `Collections.synchronizedMap`, external synchronization (e.g., using `synchronized` block) is required for iterating over the map.
- **Alternative:** Use **ConcurrentHashMap** for better performance in multithreaded scenarios as it uses a more granular locking mechanism compared to `SynchronizedHashMap`.
---  
### 3. **Explain the differences between `ArrayList` and `LinkedList` in Java.**
   - **Answer**:
      - **Storage**: `ArrayList` is backed by a resizable array, while `LinkedList` uses a doubly-linked list structure.
      - **Performance**: `ArrayList` offers O(1) access time for random access but slower O(n) insertion/removal (unless at the end). `LinkedList` provides faster O(1) insertions/deletions at the beginning and end but slower O(n) access time.
      - **Memory**: `LinkedList` consumes more memory due to additional pointers for each element, whereas `ArrayList` has a lower memory footprint.

### 4. **What is the `fail-fast` property in Java Collections?**
   - **Answer**:
      - The `fail-fast` behavior occurs in certain collection iterators, such as those in `ArrayList`, `HashSet`, or `HashMap`. When a collection is structurally modified (adding/removing elements) after an iterator is created, the iterator throws a `ConcurrentModificationException`. This mechanism helps identify unexpected concurrent modifications. Collections in the `java.util.concurrent` package, such as `ConcurrentHashMap`, are `fail-safe`, which means they do not throw exceptions when modified during iteration.

### 5. **What is the difference between `Set` and `List` in Java?**
   - **Answer**:
      - **Order**: `List` is an ordered collection that maintains the insertion order of elements, while `Set` does not guarantee any order (unless `LinkedHashSet` or `TreeSet` is used).
      - **Duplicates**: `List` allows duplicate elements, whereas `Set` does not allow duplicates.
      - **Implementations**: Common `List` implementations are `ArrayList` and `LinkedList`. `Set` implementations include `HashSet`, `LinkedHashSet`, and `TreeSet`.

### 6. **How does `TreeMap` maintain order, and what type of order does it use?**
   - **Answer**:
      - `TreeMap` maintains its keys in ascending sorted order. It is implemented using a Red-Black Tree, a type of self-balancing binary search tree. Elements can also be sorted by a custom `Comparator` if specified. Access times in `TreeMap` are O(log n) due to tree operations, unlike the constant-time access of `HashMap`.

### 7. **What is `Comparable` and `Comparator` in Java, and how do they differ?**
   - **Answer**:
      - **Comparable**: The `Comparable` interface defines a natural ordering for objects of the same type. It has a single method `compareTo()`, which compares "this" object with the specified object. Only one ordering can be defined with `Comparable`, and it is usually implemented directly within the class.
      - **Comparator**: `Comparator` is used to define an external or custom ordering. It has the `compare()` method, which takes two objects to compare. Multiple `Comparator`s can be created for different sorting needs without modifying the original class.

### 8. **Explain the working and performance implications of `HashMap`’s `get()` and `put()` operations.**
   - **Answer**:
      - `HashMap` uses hashing to store entries and relies on the `hashCode()` and `equals()` methods of keys for `get()` and `put()` operations.
      - In the best case, both `get()` and `put()` have O(1) time complexity if there are no hash collisions. However, in the worst case (extensive collisions), the time complexity can degrade to O(n). Starting from Java 8, the collision chains are replaced by balanced trees after a threshold, improving the worst-case to O(log n).

### 9. **What is the purpose of the `WeakHashMap` class in Java?**
   - **Answer**:
      - `WeakHashMap` uses weak references for its keys, meaning entries are removed automatically by the garbage collector when a key is no longer referenced elsewhere in the application. This is useful for implementing memory-sensitive caches where you want objects to be garbage-collected when they are no longer in use.

### 10. **What are `IdentityHashMap` and its use cases?**
   - **Answer**:
      - `IdentityHashMap` is a Map that compares keys using `==` (reference equality) rather than `equals()`. It is used in situations where object identity, not value equality, is important, such as maintaining unique instances or tracking object states during serialization.

### 11. **How does `CopyOnWriteArrayList` work, and where is it used?**
   - **Answer**:
      - `CopyOnWriteArrayList` is a thread-safe variant of `ArrayList`. It creates a new copy of the list for each modification operation (`add`, `remove`, etc.), ensuring that reads can occur concurrently without locking. This class is ideal for lists that are read frequently and modified infrequently, as it incurs the overhead of creating a new array on every modification but provides fast and consistent read performance.

### 12. **Explain the internal structure of `LinkedHashMap` and its access order feature.**
   - **Answer**:
      - `LinkedHashMap` maintains insertion order or access order, depending on its configuration. Internally, it uses a doubly linked list alongside a hash table to keep track of insertion order. The access order can be enabled with a special constructor flag, which makes the map reorder entries when accessed, useful for implementing caches (such as LRU caches) by removing the oldest accessed items first.

These questions explore how different Java Collections work internally, their performance, and usage scenarios which are essential for experienced professionals in Java.

---
Memory leakage in Java refers to a situation where memory that is no longer needed by a program is not released, causing the application to consume more and more memory over time. In languages with manual memory management, memory leaks happen when the developer forgets to free unused memory. However, Java has automatic memory management via the **Garbage Collector (GC)**, which usually helps prevent memory leaks. But, even with garbage collection, memory leaks can still occur if references to unused objects are maintained, preventing the GC from reclaiming that memory.

### Causes of Memory Leaks in Java

1. **Unintentional Object References**:
   - If a program unintentionally holds references to objects that are no longer needed, the GC cannot collect them, resulting in memory not being freed.

2. **Static References**:
   - Static fields live for the entire duration of the application, so any object referenced by a static field will not be garbage collected until the application terminates. If large objects or collections are assigned to static fields and not cleared when done, they can cause memory leaks.

3. **Listeners and Callbacks**:
   - Sometimes objects register themselves as listeners or callbacks but are not deregistered when they are no longer needed. This prevents the GC from reclaiming their memory because the listener holds a reference to the object.

4. **Incorrect Use of Collections**:
   - Keeping unused objects in collections (like `ArrayList`, `HashMap`, etc.) can cause memory leaks. For example, if you store objects in a map but never remove them after they’re no longer needed, they’ll consume memory indefinitely.

5. **Inner Classes and Anonymous Classes**:
   - Non-static inner classes and anonymous classes hold a reference to their outer class instance. If the outer instance is no longer used but is still referenced by an inner class, it prevents GC from collecting it.

### Example of Memory Leak in Java

Here's a simple example demonstrating how a memory leak might occur by keeping references to objects that are no longer needed.

```java
import java.util.ArrayList;
import java.util.List;

public class MemoryLeakExample {

    private List<String> list = new ArrayList<>();

    public void addData() {
        // Continuously add data to the list, but never remove it
        for (int i = 0; i < 1000; i++) {
            list.add("Data " + i);
        }
    }

    public static void main(String[] args) {
        MemoryLeakExample example = new MemoryLeakExample();

        // Simulate a memory leak by repeatedly adding data without removing it
        for (int i = 0; i < 1000; i++) {
            example.addData();
        }

        System.out.println("Finished adding data. The application is holding onto a lot of memory!");
    }
}
```

In this example, data is continuously added to the `list` without clearing or removing it. The `list` object holds onto many `String` objects that may no longer be needed, which could eventually lead to an **OutOfMemoryError** if this continues for a long time.

### Identifying Memory Leaks in Java

1. **Memory Profilers**: Tools like VisualVM, Eclipse MAT (Memory Analyzer Tool), and YourKit can help detect memory leaks by showing which objects are consuming memory and which references are preventing garbage collection.
  
2. **Heap Dumps**: A heap dump is a snapshot of memory at a specific point. Analyzing heap dumps can help identify objects that are retained in memory longer than expected.
  
3. **JConsole and JVisualVM**: Both tools, included with the JDK, monitor memory usage, including the number of live objects and their memory allocation. These tools can highlight increasing memory usage trends, which may indicate a memory leak.

### Preventing Memory Leaks in Java

- **Clear Unused References**: Explicitly set large objects or collections to `null` if they are no longer needed, especially in long-running applications.
- **Use Weak References**: Use `WeakReference` or `SoftReference` for objects that are large and may not need to persist. This allows the GC to collect them if memory becomes constrained.
- **Deregister Listeners**: Always remove listeners, callbacks, and event handlers when they are no longer needed.
- **Be Careful with Static Fields**: Use static fields judiciously, especially for large data structures or objects.
  
Memory leaks in Java applications can degrade performance and eventually lead to application crashes. Proper memory management practices and tools can help identify and prevent these issues.

---
To stop memory leaks in Java code, you can follow best practices that help manage memory effectively and ensure that unused objects are eligible for garbage collection. Here are practical steps and techniques to prevent memory leaks in Java applications:

### 1. **Avoid Unnecessary Object References**
   - Once an object is no longer needed, set its reference to `null`. This removes the reference and makes it eligible for garbage collection.
   - **Example**:
     ```java
     MyClass myObject = new MyClass();
     // Use myObject
     myObject = null;  // Eligible for GC now
     ```

### 2. **Use Local Variables Instead of Class-Level Variables**
   - Prefer local variables over instance or static fields where possible. Local variables are eligible for garbage collection after the method exits, whereas instance/static variables may persist and occupy memory longer than necessary.

### 3. **Be Cautious with Static Variables**
   - Static variables live for the entire lifetime of the application, which can cause a memory leak if large objects or collections are assigned to them and not cleared.
   - **Solution**: Use static variables judiciously, and avoid keeping references to large objects or collections unless necessary.

### 4. **Clear Collection Objects When Done**
   - If you’re using collections like `ArrayList`, `HashMap`, or `HashSet`, remove items from the collection when they are no longer needed. Accumulating objects in collections without removing them prevents garbage collection.
   - **Example**:
     ```java
     List<Object> list = new ArrayList<>();
     list.add(new Object());
     list.clear();  // Clears all items in the list, making them eligible for GC
     ```

### 5. **Deregister Event Listeners and Callbacks**
   - If an object registers itself as an event listener or callback but is not deregistered when no longer needed, it remains in memory.
   - **Solution**: Explicitly remove or deregister listeners and callbacks when the object is done being used.
   - **Example**:
     ```java
     button.addActionListener(myListener);
     // Later, when the listener is no longer needed
     button.removeActionListener(myListener);
     ```

### 6. **Use Weak References for Caches or Large Objects**
   - `WeakReference` and `SoftReference` allow objects to be garbage collected if they are no longer strongly referenced. This is especially useful for caches where you want objects to be available but not prevent garbage collection.
   - **Example**:
     ```java
     WeakReference<MyClass> weakReference = new WeakReference<>(new MyClass());
     MyClass myClass = weakReference.get();  // May return null if GC collected it
     ```

### 7. **Avoid Inner Classes that Hold References to Outer Classes**
   - Non-static inner classes hold an implicit reference to their outer class instance, which can prevent the outer instance from being collected if the inner class instance is retained.
   - **Solution**: Make inner classes `static` if they don’t need access to the outer class instance, or use `WeakReference` to the outer class.

### 8. **Use Try-with-Resources for Auto-Closeable Resources**
   - Use `try-with-resources` to ensure that resources like files, network connections, or database connections are closed after use, freeing associated memory.
   - **Example**:
     ```java
     try (BufferedReader reader = new BufferedReader(new FileReader("file.txt"))) {
         // Read file
     }  // Reader is automatically closed here
     ```

### 9. **Implement Proper `equals()` and `hashCode()` for Objects in Collections**
   - Objects in collections like `HashSet` or `HashMap` that don’t properly implement `equals()` and `hashCode()` can lead to “phantom” objects that cannot be removed from the collection, causing memory leaks.
   - **Solution**: Override `equals()` and `hashCode()` in classes used as keys in collections.

### 10. **Avoid Using Large Object Graphs When Possible**
   - Large object graphs (complex chains of object references) can lead to memory leaks if any part of the graph is inadvertently retained. Break down large structures and nullify references when they’re no longer needed.

### 11. **Monitor and Test Regularly for Memory Leaks**
   - Use tools like **VisualVM**, **Eclipse MAT (Memory Analyzer Tool)**, **JProfiler**, or **YourKit** to monitor memory usage and detect memory leaks. Analyze heap dumps to identify objects that are taking up memory and verify they’re meant to persist.

### Example Code with Best Practices

Here's an example demonstrating some of these techniques:

```java
import java.util.ArrayList;
import java.util.List;
import java.lang.ref.WeakReference;

public class MemoryLeakPrevention {

    private List<Object> cache = new ArrayList<>();

    public void useWeakReferences() {
        WeakReference<Object> weakObject = new WeakReference<>(new Object());
        if (weakObject.get() != null) {
            // Use object if still in memory
        } else {
            // Object has been garbage collected
        }
    }

    public void avoidStaticCollections() {
        List<Object> tempList = new ArrayList<>();
        // Populate and use tempList
        tempList.clear();  // Clear after use to avoid memory leak
    }

    public void clearEventListeners() {
        MyButton button = new MyButton();
        MyListener listener = new MyListener();
        button.addListener(listener);

        // Later, when button is no longer used
        button.removeListener(listener);  // Avoid memory leak
    }
    
    // Inner class without reference to outer class instance
    private static class MyListener {}

    // Dummy class for demonstration
    private static class MyButton {
        private List<MyListener> listeners = new ArrayList<>();

        public void addListener(MyListener listener) {
            listeners.add(listener);
        }

        public void removeListener(MyListener listener) {
            listeners.remove(listener);
        }
    }
}
```

Following these practices will help reduce or eliminate memory leaks in Java, leading to more efficient memory usage and more reliable applications. Regular monitoring and profiling are key to ensuring that memory is managed effectively in long-running applications.

---

No, **Java Streams** do not store any values. They are designed to process data **on-the-fly** from a source (e.g., a collection, array, or I/O channel) and do not hold or store any data themselves. Instead, they provide a pipeline for performing operations (e.g., filtering, mapping, reducing) on the data as it flows through the stream.

---

### **Key Characteristics of Java Streams**
1. **No Storage**:
   - Streams do not store data. They simply provide a way to process data from a source.

2. **Lazy Evaluation**:
   - Stream operations are **lazy**, meaning they are not executed until a terminal operation (e.g., `collect`, `forEach`, `reduce`) is invoked.
   - Intermediate operations (e.g., `filter`, `map`, `sorted`) are only executed when a terminal operation is called.

3. **Functional in Nature**:
   - Streams support functional-style operations, such as `map`, `filter`, and `reduce`, which can be chained together to form a pipeline.

4. **Consumable**:
   - A stream can only be consumed once. After a terminal operation is performed, the stream is considered consumed and cannot be reused.

---

### **How Streams Work**
1. **Source**:
   - A stream is created from a source, such as a collection, array, or I/O channel.
   - Example:
     ```java
     List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
     Stream<String> stream = names.stream();
     ```

2. **Intermediate Operations**:
   - These operations transform or filter the data in the stream.
   - Example:
     ```java
     stream.filter(name -> name.startsWith("A"))
           .map(String::toUpperCase);
     ```

3. **Terminal Operation**:
   - This operation produces a result or a side effect, such as collecting the data into a list or printing it.
   - Example:
     ```java
     List<String> result = stream.collect(Collectors.toList());
     ```

---

### **Why Streams Don’t Store Data**
- **Efficiency**: Storing data would require additional memory, which goes against the goal of processing data efficiently.
- **Flexibility**: Streams are designed to work with any data source (e.g., collections, arrays, I/O), so they avoid storing data to remain generic.
- **Lazy Evaluation**: By not storing data, streams can optimize performance by only processing data when necessary.

---

### **Example**
```java
List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

// Create a stream
Stream<String> stream = names.stream();

// Intermediate operations (no data is processed yet)
stream = stream.filter(name -> name.startsWith("A"))
               .map(String::toUpperCase);

// Terminal operation (data is processed and collected)
List<String> result = stream.collect(Collectors.toList());

System.out.println(result); // Output: [ALICE]
```

---

### **Key Points**
- Streams do not store data; they process data from a source.
- Intermediate operations are lazy and only executed when a terminal operation is invoked.
- Streams are consumable and cannot be reused after a terminal operation is performed.

---

### **Common Misconceptions**
1. **Streams Store Data**:
   - No, streams do not store data. They process data on-the-fly.

2. **Streams Are Data Structures**:
   - No, streams are not data structures. They are abstractions for processing data.

3. **Streams Can Be Reused**:
   - No, once a stream is consumed (e.g., by a terminal operation), it cannot be reused.

---

### **When to Use Streams**
- When you need to process collections or sequences of data in a functional and declarative way.
- When you want to leverage parallel processing (e.g., using `parallelStream()`).
- When you need to chain multiple operations (e.g., filtering, mapping, reducing) in a clean and readable way.

By understanding that streams do not store data, you can use them effectively for efficient and functional data processing in Java.


---

The **Singleton Pattern** is a **creational design pattern** that ensures a class has only one instance and provides a global point of access to that instance. It is commonly used when exactly one object is needed to coordinate actions across a system, such as managing a shared resource or configuration settings.

---

### **Key Features of the Singleton Pattern**
1. **Single Instance**:
   - Only one instance of the class is created and shared across the application.

2. **Global Access**:
   - The instance is accessible globally, typically through a static method.

3. **Lazy Initialization**:
   - The instance is created only when it is needed (on-demand).

4. **Thread Safety**:
   - Ensures that the singleton instance is created safely in a multi-threaded environment.

---

### **Use Cases for the Singleton Pattern**
1. **Configuration Management**:
   - A single instance to manage application-wide configuration settings.

2. **Logging**:
   - A single logger instance to handle logging across the application.

3. **Database Connections**:
   - A single connection pool to manage database connections.

4. **Caching**:
   - A single cache instance to store frequently accessed data.

5. **Hardware Access**:
   - A single instance to manage access to hardware resources (e.g., printers).

---

### **Implementation of the Singleton Pattern**
Here’s how to implement the Singleton Pattern in **Java**:

#### **1. Basic Singleton (Not Thread-Safe)**
```java
public class Singleton {
    // Private static instance
    private static Singleton instance;

    // Private constructor to prevent instantiation
    private Singleton() {}

    // Public method to provide access to the instance
    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    // Example method
    public void showMessage() {
        System.out.println("Hello from Singleton!");
    }
}
```

#### **2. Thread-Safe Singleton (Using Synchronized Method)**
```java
public class Singleton {
    private static Singleton instance;

    private Singleton() {}

    // Synchronized method to ensure thread safety
    public static synchronized Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public void showMessage() {
        System.out.println("Hello from Singleton!");
    }
}
```

#### **3. Thread-Safe Singleton (Using Double-Checked Locking)**
```java
public class Singleton {
    private static volatile Singleton instance;

    private Singleton() {}

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    public void showMessage() {
        System.out.println("Hello from Singleton!");
    }
}
```

#### **4. Singleton with Enum (Best Approach for Java)**
```java
public enum Singleton {
    INSTANCE;

    public void showMessage() {
        System.out.println("Hello from Singleton!");
    }
}
```

---

### **Example Usage**
```java
public class Main {
    public static void main(String[] args) {
        // Get the singleton instance
        Singleton singleton = Singleton.getInstance();

        // Use the singleton instance
        singleton.showMessage();
    }
}
```

---

### **Advantages of the Singleton Pattern**
1. **Controlled Access**:
   - Ensures that only one instance of the class exists, preventing multiple instances from causing inconsistencies.

2. **Global Access**:
   - Provides a global point of access to the instance, making it easy to manage shared resources.

3. **Lazy Initialization**:
   - The instance is created only when needed, saving resources.

4. **Thread Safety**:
   - Properly implemented singletons are thread-safe, ensuring safe usage in multi-threaded environments.

---

### **Disadvantages of the Singleton Pattern**
1. **Global State**:
   - Singletons introduce global state, which can make the code harder to test and maintain.

2. **Tight Coupling**:
   - Classes that depend on singletons are tightly coupled to the singleton instance, making it harder to replace or mock.

3. **Concurrency Issues**:
   - If not implemented correctly, singletons can cause issues in multi-threaded environments.

4. **Violation of Single Responsibility Principle**:
   - Singletons often manage their own lifecycle, which can violate the Single Responsibility Principle.

---

### **When to Use the Singleton Pattern**
- When you need exactly one instance of a class to coordinate actions across the system.
- When you want to control access to a shared resource (e.g., database connection, configuration settings).
- When you need lazy initialization of an object.

---

### **Real-World Example: Logger**
A common use case for the Singleton Pattern is a **Logger** class that handles logging across an application.

#### **Logger Singleton**
```java
public class Logger {
    private static Logger instance;
    private StringBuilder log = new StringBuilder();

    private Logger() {}

    public static synchronized Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void log(String message) {
        log.append(message).append("\n");
    }

    public void printLog() {
        System.out.println(log.toString());
    }
}
```

#### **Usage**
```java
public class Main {
    public static void main(String[] args) {
        Logger logger = Logger.getInstance();
        logger.log("Application started.");
        logger.log("Performing some operations...");
        logger.printLog();
    }
}
```

---

### **Summary**
- The Singleton Pattern ensures that a class has only one instance and provides a global point of access to it.
- It is useful for managing shared resources, configurations, and logging.
- Proper implementation ensures thread safety and lazy initialization.
- Use the Singleton Pattern judiciously, as it can introduce global state and tight coupling.

By understanding and applying the Singleton Pattern, you can effectively manage shared resources and ensure consistency in your applications.

---

In object-oriented programming (OOP), the terms **instance** and **object** are often used interchangeably, but they have subtle differences in meaning. Here's a detailed explanation of the two concepts:

---

### **1. Class**
- A **class** is a blueprint or template for creating objects.
- It defines the properties (attributes) and behaviors (methods) that the objects created from the class will have.
- Example:
  ```java
  public class Car {
      // Attributes (properties)
      String color;
      int speed;

      // Methods (behaviors)
      void accelerate() {
          speed += 10;
      }
  }
  ```

---

### **2. Object**
- An **object** is a concrete entity created from a class.
- It is an instance of a class that occupies memory and has state (attributes) and behavior (methods).
- Example:
  ```java
  Car myCar = new Car();
  ```
  Here, `myCar` is an object of the `Car` class.

---

### **3. Instance**
- An **instance** is a specific realization of a class.
- It refers to the process of creating an object from a class and the object itself.
- Example:
  ```java
  Car myCar = new Car();
  ```
  Here, `myCar` is an instance of the `Car` class.

---

### **Key Differences Between Instance and Object**
| **Aspect**              | **Object**                                      | **Instance**                                  |
|--------------------------|------------------------------------------------|----------------------------------------------|
| **Definition**           | A concrete entity created from a class.        | A specific realization of a class.           |
| **Usage**                | Refers to the actual entity in memory.         | Refers to the relationship between the object and its class. |
| **Focus**                | Emphasizes the entity itself.                  | Emphasizes the process of creation.          |
| **Example**              | `Car myCar = new Car();` (myCar is an object). | `myCar` is an instance of the `Car` class.   |

---

### **Example to Illustrate the Difference**
```java
public class Car {
    String color;
    int speed;

    void accelerate() {
        speed += 10;
    }
}

public class Main {
    public static void main(String[] args) {
        // Creating an object (instance) of the Car class
        Car myCar = new Car();

        // Accessing attributes and methods
        myCar.color = "Red";
        myCar.accelerate();

        System.out.println("Car color: " + myCar.color);
        System.out.println("Car speed: " + myCar.speed);
    }
}
```

- **Object**: `myCar` is an object of the `Car` class.
- **Instance**: `myCar` is an instance of the `Car` class.

---

### **When to Use Each Term**
- Use **object** when referring to the concrete entity in memory.
- Use **instance** when referring to the relationship between the object and its class or the process of creating the object.

---

### **Summary**
- A **class** is a blueprint.
- An **object** is a concrete entity created from a class.
- An **instance** refers to the specific realization of a class (the object and its creation process).

In practice, the terms are often used interchangeably, but understanding the subtle difference can help clarify discussions about object-oriented programming.

---

The `ConcurrentHashMap` in Java is a thread-safe hash table designed for high concurrency. It allows multiple threads to read and write without blocking each other unnecessarily. Here's a detailed breakdown of its internal workings with an example:

---

### what is internal working of concurrentHashMap with detailed example?

Okay, I need to explain the internal workings of ConcurrentHashMap in Java with a detailed example. Let me start by recalling what I know about ConcurrentHashMap.

First, I remember that ConcurrentHashMap is a thread-safe implementation of the Map interface, designed for concurrent access without locking the entire table. Unlike Hashtable or synchronized HashMap, which use a single lock, ConcurrentHashMap allows multiple threads to read and write without blocking each other unnecessarily. But how exactly does it achieve that?

I think it's segmented. Wait, in older versions of Java, like before Java 8, ConcurrentHashMap used a segmentation approach where the map was divided into segments, each acting as a separate hash table. Each segment had its own lock, so multiple threads could modify different segments concurrently. But I've heard that in Java 8 and later, this was changed. Now, instead of segments, they use a node-based locking mechanism with synchronized blocks on the first node of the bucket. So the segmentation was replaced with a more fine-grained locking approach.

So in Java 8+, ConcurrentHashMap is an array of buckets, each bucket is a linked list or a tree (if the list becomes too long, it's converted to a tree to improve performance). When a thread modifies a bucket, it locks only the head node of that bucket. This allows other threads to access different buckets simultaneously, increasing concurrency.

The main methods like put, get, remove, etc., work by first calculating the hash code of the key to determine the bucket. Then, they handle that bucket accordingly. For insertion (put), if the bucket is empty, a CAS (Compare-And-Swap) operation is used to add the new node. If the bucket isn't empty, the code synchronizes on the first node of the bucket and proceeds to add or update the entry. CAS operations are part of the java.util.concurrent.atomic package, which allows lock-free algorithms for certain operations, improving performance.

Also, ConcurrentHashMap allows concurrent reads and writes. For instance, the get() method doesn't lock because the Node's fields are volatile, ensuring visibility of changes across threads. However, updates like put or remove require locking but only at the bucket level.

Another thing I remember is the size() method. Since counting entries in a concurrent environment is tricky, ConcurrentHashMap uses a counter that's maintained using CAS to avoid locking. It might not be exact at any given moment but is designed to be accurate enough for concurrency purposes.

Now, how to structure an example? Let's think of a scenario where multiple threads are adding entries to the map. Let's say we have a ConcurrentHashMap and several threads performing put operations. Each thread adds different keys, perhaps with some overlapping. The example should demonstrate that even when multiple threads are writing, the map remains consistent without explicit synchronization.

Wait, but to make the example detailed, maybe have a situation where two threads are modifying the same bucket. If their keys hash to the same bucket, then one would block until the other completes. But if they are different buckets, they can proceed concurrently. How to force that? Maybe by using keys with the same hash code but different equals, but that's complicated. Alternatively, use a known hash function to target specific buckets.

Alternatively, use a small map capacity so that different keys end up in the same bucket. For example, if the initial capacity is 16 (default), the hash code determines the bucket. If two keys have the same hash modulo 16, they go to the same bucket. So if we create keys that intentionally collide, we can show that modifying the same bucket is synchronized, while different buckets can proceed in parallel.

Let's say we have a ConcurrentHashMap with capacity 16. Thread 1 adds a key "key1" which hashes to bucket 5. Thread 2 adds "key2" which also hashes to bucket 5. Then, these two threads will contend for the same bucket's lock, leading to one blocking the other. But if Thread 3 adds "key3" that hashes to bucket 6, it can proceed without contention.

But how to demonstrate this in code? Maybe by creating threads that perform puts and timing their execution. If two threads are trying to put to the same bucket, the total time would be longer than if they are putting to different buckets. However, that's a bit involved and might require knowing the internal hash function, which could vary between Java versions.

Alternatively, use a custom object as a key with overridden hashCode() to control the bucket. For example:

class Key {

int id;

public Key(int id) { this.id = id; }

@Override

public int hashCode() { return id % 16; } // Force bucket based on id mod 16

}

Then, creating Key instances with id 1 and 17 would both hash to bucket 1 (since 1 mod 16 is 1, 17 mod 16 is 1). Then, two threads inserting these keys would be in the same bucket, so their puts would be synchronized. Another key with id 2 would go to bucket 2, so a different thread could insert without contention.

In the example, we can simulate this with multiple threads. Let's create two threads inserting keys into the same bucket and a third thread inserting into a different bucket. We can add some delays to simulate processing time and observe the order of execution. However, since threads are asynchronous, the exact order may vary, but the idea is to illustrate that same-bucket operations are synchronized.

Wait, but how to ensure that the hash code is computed as per the bucket? Because the actual bucket index is determined by the hash code of the key, which in Java's ConcurrentHashMap is not just the key's hashCode() but is spread using a hashing function to minimize collisions. So overriding hashCode() might not directly map to the bucket index. The internal method for determining the bucket might apply an additional transformation.

In Java 8+, the bucket index is determined by (hash & (n-1)), where n is the number of buckets (a power of two). So if we have a key with a hashCode() that is, say, 5, and the number of buckets is 16, then the bucket index is 5. So by controlling the hash code of the key, we can control the bucket.

Therefore, creating keys with specific hash codes would allow us to target specific buckets. So let's define a Key class with a fixed hashCode() that returns a specific value. For example:

class Key {

private int hash;

public Key(int hash) {

this.hash = hash;

}

@Override

public int hashCode() {

return hash;

}

@Override

public boolean equals(Object obj) {

if (this == obj) return true;

if (obj == null || getClass() != obj.getClass()) return false;

Key key = (Key) obj;

return hash == key.hash;

}

}

Then, when creating keys with hash codes 1 and 17, both would map to bucket 1 (assuming the map has 16 buckets). So two threads inserting keys with hash 1 and 17 would end up in the same bucket, hence the same lock, leading to contention. Whereas a key with hash 2 would be in a different bucket.

Now, let's write an example where two threads are adding entries to the same bucket and another thread to a different bucket. To make it visible, we can add print statements with timestamps to see when each thread acquires the lock. However, since the actual locking is internal, we can't directly observe it. Instead, we can infer through timing. For example, if a thread is putting into a bucket and sleeps while holding the lock, another thread trying to put into the same bucket would be blocked until the first releases the lock.

But how to simulate that? Because in normal operations, the lock is held only during the modification of the bucket. So if we have a thread that does a put, then sleeps, but the lock is released once the put is done. To create contention, we need to have a long operation while holding the lock. However, the put operation itself is atomic and quick. So maybe we can use a custom data structure or some reflection to extend the lock duration. But that's getting complicated.

Alternatively, we can create a situation where two threads are trying to update the same key, leading to contention. But that's more about atomicity of operations rather than bucket-level locking.

Alternatively, use computeIfAbsent with a long-running function. Because computeIfAbsent will lock the bucket while computing the value if the key is absent. So if two threads call computeIfAbsent on the same key, the second thread will block until the first completes.

So here's a possible example:

1. Create a ConcurrentHashMap.

2. Define a key that maps to the same bucket.

3. Create a thread that uses computeIfAbsent on that key, with a function that takes some time (e.g., sleeps for 2 seconds).

4. Create another thread that also tries to computeIfAbsent on the same key. The second thread should be blocked until the first completes.

5. A third thread that works on a different key (different bucket) can proceed concurrently.

This would demonstrate that operations on the same bucket are synchronized, while others are not.

Let me draft some code:

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapExample {

public static void main(String[] args) throws InterruptedException {

ConcurrentHashMap<Key, String> map = new ConcurrentHashMap<>();

Key key1 = new Key(1); // hash 1, bucket 1 (assuming 16 buckets)

Key key2 = new Key(17); // hash 17, bucket 1 (since 17 & 15 = 1)

Key key3 = new Key(2); // bucket 2

Thread t1 = new Thread(() -> {

String value = map.computeIfAbsent(key1, k -> {

try {

System.out.println("Thread 1 computing value for key1");

Thread.sleep(2000); // Simulate long computation

} catch (InterruptedException e) {

e.printStackTrace();

}

return "Value1";

});

System.out.println("Thread 1 got: " + value);

});

Thread t2 = new Thread(() -> {

String value = map.computeIfAbsent(key2, k -> {

System.out.println("Thread 2 computing value for key2");

return "Value2";

});

System.out.println("Thread 2 got: " + value);

});

Thread t3 = new Thread(() -> {

String value = map.computeIfAbsent(key3, k -> {

System.out.println("Thread 3 computing value for key3");

return "Value3";

});

System.out.println("Thread 3 got: " + value);

});

t1.start();

Thread.sleep(100); // Ensure t1 starts first

t2.start();

t3.start();

t1.join();

t2.join();

t3.join();

}

static class Key {

private int hash;

public Key(int hash) {

this.hash = hash;

}

@Override

public int hashCode() {

return hash;

}

@Override

public boolean equals(Object obj) {

if (this == obj) return true;

if (obj == null || getClass() != obj.getClass()) return false;

Key key = (Key) obj;

return hash == key.hash;

}

}

}

In this example, key1 and key2 have hash codes 1 and 17, which modulo 16 (since the default initial capacity is 16) both map to bucket 1. Thread t1 starts first and calls computeIfAbsent on key1, which triggers the function that sleeps for 2 seconds. Because the bucket is locked during the computation, any other threads trying to modify the same bucket will block. Thread t2 calls computeIfAbsent on key2, which is in the same bucket, so it should block until t1 releases the lock. Thread t3 operates on key3, which is in a different bucket, so it can proceed immediately.

When running this code, the expected output would be:

Thread 1 computing value for key1

Thread 3 computing value for key3

Thread 3 got: Value3

(after 2 seconds)

Thread 2 computing value for key2

Thread 1 got: Value1

Thread 2 got: Value2

This shows that t3 could proceed concurrently with t1, but t2 had to wait for t1's operation on the same bucket to complete.

However, there's a potential issue here: when using computeIfAbsent, if the key is already present, the function isn't called. But in this case, since t1 and t2 are inserting different keys (key1 and key2), even though they are in the same bucket, but different keys. Wait, but the computeIfAbsent is per key. So if key1 and key2 are different keys (even in the same bucket), computeIfAbsent for key2 would proceed once the bucket's lock is available. Wait, but since the bucket is locked during the computation of key1, would inserting key2 (same bucket, different key) block?

Ah, here's a confusion. The lock is on the bucket's head node. So when t1 is computing for key1, it has locked the bucket's head. Then, when t2 comes in to compute for key2, which is in the same bucket, it would try to acquire the same lock. So even though they are different keys in the same bucket, t2 has to wait until t1's operation completes. So in this example, even though key1 and key2 are different, because they are in the same bucket, their computeIfAbsent operations are serialized.

But wait, in Java's ConcurrentHashMap, during a computeIfAbsent, the entire bucket is locked. So any modification to the same bucket requires the lock. So even different keys in the same bucket would contend for the same lock. So yes, in this example, t2 would block until t1's compute completes, even though they are different keys. However, when t1 is processing key1, the code traverses the bucket (a linked list or tree) to find the key. If key1 is not present, it adds it. During this time, the bucket is locked. So t2, which is trying to process key2 in the same bucket, must wait until the lock is released. Then, after the lock is released, t2 can proceed to check if key2 exists and add it if necessary.

But in the example, key1 and key2 are different keys, but in the same bucket. So when t1 is inserting key1, it holds the lock for the bucket. Then, t2 comes in and wants to insert key2. Since they're in the same bucket, t2 has to wait for t1 to release the lock. Once t1 is done, t2 can proceed. But the computeIfAbsent for key2 will proceed, since key2 is not present. So the expected output would be:

Thread 1 computing value for key1

Thread 3 computing value for key3

Thread 3 got: Value3

(after 2 seconds)

Thread 1 got: Value1

Thread 2 computing value for key2

Thread 2 got: Value2

But the order could vary. However, Thread 3's operation is on a different bucket, so it can proceed immediately. Thread 2's operation on the same bucket as Thread 1 has to wait until Thread 1's lock is released.

This example would demonstrate the internal locking mechanism at the bucket level. Thus, even different keys in the same bucket will contend for the same lock, leading to serialized execution of their modifications. Meanwhile, operations on other buckets can proceed concurrently.

Another point to consider is that ConcurrentHashMap dynamically resizes when the number of elements exceeds a threshold. The resizing involves creating a new bucket array and transferring entries. This process is done concurrently by multiple threads to avoid long pauses. During resizing, threads can help with the transfer, and new insertions may be directed to the new table.

Additionally, the load factor determines when resizing occurs. The default is 0.75, meaning when 75% of the buckets are filled, the map is resized. But since ConcurrentHashMap uses a per-bucket approach, the actual resizing is managed more efficiently.

In terms of iterators, ConcurrentHashMap provides weakly consistent iterators, which means they reflect the state of the map at some point at or since the creation of the iterator. They do not throw ConcurrentModificationException, allowing iteration while the map is being modified, but the changes may or may not be visible.

So putting it all together, the internal working of ConcurrentHashMap in Java 8+ involves:

- An array of buckets, each a linked list or tree.

- Locking at the bucket level (synchronized on the first node) for write operations.

- CAS operations for lock-free reads and certain updates.

- Dynamic resizing with concurrent assistance.

- Weakly consistent iterators.

This design allows high concurrency for both read and write operations, making it suitable for high-performance multi-threaded applications.



### **Internal Structure**
1. **Buckets and Nodes**:
   - The map is an array of buckets (default capacity: 16).
   - Each bucket holds a linked list or tree (for collision resolution).
   - Java 8+ uses a node-based locking mechanism (synchronized on the first node of a bucket) instead of segment-level locks.

2. **Concurrency Control**:
   - **Write Operations** (e.g., `put`, `remove`): Lock the first node of the bucket to ensure thread safety.
   - **Read Operations** (e.g., `get`): No locks; uses volatile variables for visibility.
   - **CAS (Compare-and-Swap)**: Used for lock-free updates (e.g., inserting the first node in a bucket).

3. **Dynamic Resizing**:
   - When the load factor (default: 0.75) is exceeded, the map resizes.
   - Resizing is done concurrently by multiple threads to minimize blocking.

---

### **Example: Concurrent Operations**
#### **Scenario**
- **Thread 1**: Inserts a key with a slow computation into **Bucket 1**.
- **Thread 2**: Inserts a different key into **Bucket 1** (same bucket, different key).
- **Thread 3**: Inserts a key into **Bucket 2** (different bucket).

#### **Code**
```java
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapExample {
    static class Key {
        private final int hash;
        Key(int hash) { this.hash = hash; }
        @Override public int hashCode() { return hash; }
        @Override public boolean equals(Object o) { /*...*/ }
    }

    public static void main(String[] args) throws InterruptedException {
        ConcurrentHashMap<Key, String> map = new ConcurrentHashMap<>();

        Key key1 = new Key(1);   // Bucket 1 (1 & 15 = 1)
        Key key2 = new Key(17);  // Bucket 1 (17 & 15 = 1)
        Key key3 = new Key(2);   // Bucket 2

        Thread t1 = new Thread(() -> {
            map.computeIfAbsent(key1, k -> {
                try {
                    System.out.println("Thread 1 computing key1...");
                    Thread.sleep(2000); // Simulate slow computation
                } catch (InterruptedException e) { e.printStackTrace(); }
                return "Value1";
            });
            System.out.println("Thread 1 done.");
        });

        Thread t2 = new Thread(() -> {
            map.computeIfAbsent(key2, k -> {
                System.out.println("Thread 2 computing key2...");
                return "Value2";
            });
            System.out.println("Thread 2 done.");
        });

        Thread t3 = new Thread(() -> {
            map.computeIfAbsent(key3, k -> {
                System.out.println("Thread 3 computing key3...");
                return "Value3";
            });
            System.out.println("Thread 3 done.");
        });

        t1.start();
        Thread.sleep(100); // Ensure t1 starts first
        t2.start();
        t3.start();
        t1.join(); t2.join(); t3.join();
    }
}
```

---

### **Output and Explanation**
- **Thread 1** locks **Bucket 1** and starts a slow computation.
- **Thread 3** (operating on **Bucket 2**) proceeds immediately:
  ```
  Thread 1 computing key1...
  Thread 3 computing key3...
  Thread 3 done.
  ```
- After 2 seconds, **Thread 1** releases the lock, and **Thread 2** proceeds:
  ```
  Thread 1 done.
  Thread 2 computing key2...
  Thread 2 done.
  ```

---

### **Key Takeaways**
1. **Bucket-Level Locking**:
   - Threads modifying the same bucket are serialized.
   - Threads modifying different buckets run concurrently.

2. **Efficiency**:
   - Reads are lock-free and use volatile variables.
   - Writes use fine-grained locks (minimize contention).

3. **Resizing**:
   - Done concurrently to avoid blocking all operations.

This design ensures high concurrency and scalability in multi-threaded environments.

---

The **SOLID** principles are a set of five design principles in object-oriented programming that help developers design robust, maintainable, and scalable software. Let's break down each principle with detailed examples.

---

### **1. Single Responsibility Principle (SRP)**
**Definition**: A class should have only one reason to change, meaning it should have only one responsibility.

**Example**:
Imagine a class that handles both user authentication and sending emails. This violates SRP because it has multiple responsibilities.

```java
// Violates SRP
class UserService {
    public void authenticateUser(String username, String password) {
        // Authentication logic
    }

    public void sendEmail(String to, String subject, String body) {
        // Email sending logic
    }
}
```

**Refactored Code**:
Split the responsibilities into two separate classes.

```java
// Follows SRP
class AuthenticationService {
    public void authenticateUser(String username, String password) {
        // Authentication logic
    }
}

class EmailService {
    public void sendEmail(String to, String subject, String body) {
        // Email sending logic
    }
}
```

---

### **2. Open/Closed Principle (OCP)**
**Definition**: Software entities (classes, modules, functions) should be open for extension but closed for modification.

**Example**:
Suppose you have a class that calculates the area of shapes. Adding a new shape requires modifying the existing class.

```java
// Violates OCP
class AreaCalculator {
    public double calculateArea(Object shape) {
        if (shape instanceof Rectangle) {
            Rectangle rectangle = (Rectangle) shape;
            return rectangle.width * rectangle.height;
        } else if (shape instanceof Circle) {
            Circle circle = (Circle) shape;
            return Math.PI * circle.radius * circle.radius;
        }
        throw new IllegalArgumentException("Unknown shape");
    }
}
```

**Refactored Code**:
Use abstraction to allow extension without modifying the existing code.

```java
// Follows OCP
interface Shape {
    double calculateArea();
}

class Rectangle implements Shape {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double calculateArea() {
        return width * height;
    }
}

class Circle implements Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
}

class AreaCalculator {
    public double calculateArea(Shape shape) {
        return shape.calculateArea();
    }
}
```

---

### **3. Liskov Substitution Principle (LSP)**
**Definition**: Objects of a superclass should be replaceable with objects of a subclass without affecting the correctness of the program.

**Example**:
A subclass should not break the behavior of the superclass.

```java
// Violates LSP
class Bird {
    public void fly() {
        System.out.println("Flying");
    }
}

class Ostrich extends Bird {
    @Override
    public void fly() {
        throw new UnsupportedOperationException("Ostrich cannot fly");
    }
}
```

**Refactored Code**:
Ensure subclasses adhere to the behavior of the superclass.

```java
// Follows LSP
interface Bird {
    void move();
}

class Sparrow implements Bird {
    @Override
    public void move() {
        System.out.println("Flying");
    }
}

class Ostrich implements Bird {
    @Override
    public void move() {
        System.out.println("Running");
    }
}
```

---

### **4. Interface Segregation Principle (ISP)**
**Definition**: Clients should not be forced to depend on interfaces they do not use.

**Example**:
A single interface with multiple methods forces implementing classes to define unused methods.

```java
// Violates ISP
interface Worker {
    void work();
    void eat();
}

class HumanWorker implements Worker {
    @Override
    public void work() {
        System.out.println("Working");
    }

    @Override
    public void eat() {
        System.out.println("Eating");
    }
}

class RobotWorker implements Worker {
    @Override
    public void work() {
        System.out.println("Working");
    }

    @Override
    public void eat() {
        throw new UnsupportedOperationException("Robot cannot eat");
    }
}
```

**Refactored Code**:
Split the interface into smaller, more specific interfaces.

```java
// Follows ISP
interface Workable {
    void work();
}

interface Eatable {
    void eat();
}

class HumanWorker implements Workable, Eatable {
    @Override
    public void work() {
        System.out.println("Working");
    }

    @Override
    public void eat() {
        System.out.println("Eating");
    }
}

class RobotWorker implements Workable {
    @Override
    public void work() {
        System.out.println("Working");
    }
}
```

---

### **5. Dependency Inversion Principle (DIP)**
**Definition**: High-level modules should not depend on low-level modules. Both should depend on abstractions.

**Example**:
A high-level class directly depends on a low-level class.

```java
// Violates DIP
class LightBulb {
    public void turnOn() {
        System.out.println("LightBulb: On");
    }

    public void turnOff() {
        System.out.println("LightBulb: Off");
    }
}

class Switch {
    private LightBulb bulb;

    public Switch(LightBulb bulb) {
        this.bulb = bulb;
    }

    public void operate() {
        bulb.turnOn();
    }
}
```

**Refactored Code**:
Introduce an abstraction to decouple the high-level and low-level modules.

```java
// Follows DIP
interface Switchable {
    void turnOn();
    void turnOff();
}

class LightBulb implements Switchable {
    @Override
    public void turnOn() {
        System.out.println("LightBulb: On");
    }

    @Override
    public void turnOff() {
        System.out.println("LightBulb: Off");
    }
}

class Fan implements Switchable {
    @Override
    public void turnOn() {
        System.out.println("Fan: On");
    }

    @Override
    public void turnOff() {
        System.out.println("Fan: Off");
    }
}

class Switch {
    private Switchable device;

    public Switch(Switchable device) {
        this.device = device;
    }

    public void operate() {
        device.turnOn();
    }
}
```

---

### **Summary of SOLID Principles**
| Principle                        | Key Idea                                                                 |
|----------------------------------|--------------------------------------------------------------------------|
| **Single Responsibility (SRP)**  | A class should have only one responsibility.                             |
| **Open/Closed (OCP)**            | Classes should be open for extension but closed for modification.        |
| **Liskov Substitution (LSP)**    | Subclasses should be substitutable for their base classes.               |
| **Interface Segregation (ISP)**  | Clients should not be forced to depend on interfaces they do not use.    |
| **Dependency Inversion (DIP)**   | Depend on abstractions, not on concrete implementations.                 |

By adhering to these principles, you can create clean, modular, and maintainable code.

---

## What is functional interface, how to create custom functional interface and what are type of functional interface available in java 8 with detailed example?

### **Functional Interface in Java**

A **functional interface** in Java is an interface that contains **exactly one abstract method**. It can have any number of default or static methods but only one abstract method. Functional interfaces are the foundation of **lambda expressions** in Java 8.

---

### **How to Create a Custom Functional Interface**

To create a custom functional interface:
1. Define an interface with exactly one abstract method.
2. Optionally, add `@FunctionalInterface` annotation to enforce the rule at compile time.

#### Example:
```java
@FunctionalInterface
interface Greeting {
    void greet(String name); // Single abstract method

    // Default method (allowed)
    default void sayHello() {
        System.out.println("Hello!");
    }

    // Static method (allowed)
    static void sayBye() {
        System.out.println("Goodbye!");
    }
}
```

#### Usage:
```java
public class Main {
    public static void main(String[] args) {
        // Using lambda expression
        Greeting greeting = (name) -> System.out.println("Hello, " + name);
        greeting.greet("Alice"); // Output: Hello, Alice

        // Calling default and static methods
        greeting.sayHello(); // Output: Hello!
        Greeting.sayBye();   // Output: Goodbye!
    }
}
```

---

### **Types of Functional Interfaces in Java 8**

Java 8 introduced several built-in functional interfaces in the `java.util.function` package. These interfaces cover most common use cases for lambda expressions.

#### **1. `Consumer<T>`**
- **Purpose**: Accepts a single input argument and returns no result.
- **Abstract Method**: `void accept(T t)`
- **Example**:
  ```java
  Consumer<String> print = (s) -> System.out.println(s);
  print.accept("Hello, Consumer!"); // Output: Hello, Consumer!
  ```

#### **2. `Supplier<T>`**
- **Purpose**: Supplies a result without taking any input.
- **Abstract Method**: `T get()`
- **Example**:
  ```java
  Supplier<Double> randomValue = () -> Math.random();
  System.out.println(randomValue.get()); // Output: Random double value
  ```

#### **3. `Function<T, R>`**
- **Purpose**: Accepts one argument and produces a result.
- **Abstract Method**: `R apply(T t)`
- **Example**:
  ```java
  Function<String, Integer> lengthFunction = (s) -> s.length();
  System.out.println(lengthFunction.apply("Java")); // Output: 4
  ```

#### **4. `Predicate<T>`**
- **Purpose**: Tests a condition and returns a boolean.
- **Abstract Method**: `boolean test(T t)`
- **Example**:
  ```java
  Predicate<Integer> isEven = (n) -> n % 2 == 0;
  System.out.println(isEven.test(10)); // Output: true
  ```

#### **5. `UnaryOperator<T>`**
- **Purpose**: A specialization of `Function` where the input and output types are the same.
- **Abstract Method**: `T apply(T t)`
- **Example**:
  ```java
  UnaryOperator<String> toUpperCase = (s) -> s.toUpperCase();
  System.out.println(toUpperCase.apply("java")); // Output: JAVA
  ```

#### **6. `BinaryOperator<T>`**
- **Purpose**: A specialization of `BiFunction` where both input types and the output type are the same.
- **Abstract Method**: `T apply(T t1, T t2)`
- **Example**:
  ```java
  BinaryOperator<Integer> add = (a, b) -> a + b;
  System.out.println(add.apply(5, 3)); // Output: 8
  ```

#### **7. `BiConsumer<T, U>`**
- **Purpose**: Accepts two input arguments and returns no result.
- **Abstract Method**: `void accept(T t, U u)`
- **Example**:
  ```java
  BiConsumer<String, Integer> printDetails = (name, age) -> 
      System.out.println(name + " is " + age + " years old.");
  printDetails.accept("Alice", 30); // Output: Alice is 30 years old.
  ```

#### **8. `BiFunction<T, U, R>`**
- **Purpose**: Accepts two arguments and produces a result.
- **Abstract Method**: `R apply(T t, U u)`
- **Example**:
  ```java
  BiFunction<Integer, Integer, String> sumToString = (a, b) -> "Sum: " + (a + b);
  System.out.println(sumToString.apply(5, 3)); // Output: Sum: 8
  ```

#### **9. `BiPredicate<T, U>`**
- **Purpose**: Tests a condition on two arguments and returns a boolean.
- **Abstract Method**: `boolean test(T t, U u)`
- **Example**:
  ```java
  BiPredicate<String, Integer> isNameLengthValid = (name, length) -> name.length() == length;
  System.out.println(isNameLengthValid.test("Alice", 5)); // Output: true
  ```

---

### **Custom Functional Interface Example**

Let's create a custom functional interface for a mathematical operation.

#### Step 1: Define the Interface
```java
@FunctionalInterface
interface MathOperation {
    int operate(int a, int b); // Single abstract method
}
```

#### Step 2: Use the Interface
```java
public class Main {
    public static void main(String[] args) {
        // Lambda expressions for addition, subtraction, multiplication, and division
        MathOperation add = (a, b) -> a + b;
        MathOperation subtract = (a, b) -> a - b;
        MathOperation multiply = (a, b) -> a * b;
        MathOperation divide = (a, b) -> a / b;

        System.out.println("10 + 5 = " + add.operate(10, 5)); // Output: 15
        System.out.println("10 - 5 = " + subtract.operate(10, 5)); // Output: 5
        System.out.println("10 * 5 = " + multiply.operate(10, 5)); // Output: 50
        System.out.println("10 / 5 = " + divide.operate(10, 5)); // Output: 2
    }
}
```

---

### **Key Points**
1. A functional interface has **exactly one abstract method**.
2. Use the `@FunctionalInterface` annotation to enforce this rule.
3. Java 8 provides built-in functional interfaces like `Consumer`, `Supplier`, `Function`, `Predicate`, etc.
4. Custom functional interfaces can be created for specific use cases.

By leveraging functional interfaces and lambda expressions, you can write more concise and expressive code in Java.

---

In Java, primitive data types are the most basic data types provided by the language. They are predefined by the language and named by a reserved keyword. Primitive data types are not objects and represent raw values. They are stored directly in memory and are used to declare variables and methods.

Java has eight primitive data types, which can be categorized into four groups:

1. **Integer Types**: These are used to store whole numbers.
   - `byte`: 8-bit signed integer. Range: -128 to 127.
     ```java
     byte b = 100;
     ```
   - `short`: 16-bit signed integer. Range: -32,768 to 32,767.
     ```java
     short s = 20000;
     ```
   - `int`: 32-bit signed integer. Range: -2^31 to 2^31-1.
     ```java
     int i = 100000;
     ```
   - `long`: 64-bit signed integer. Range: -2^63 to 2^63-1.
     ```java
     long l = 100000L; // Note the 'L' suffix
     ```

2. **Floating-Point Types**: These are used to store numbers with fractional parts.
   - `float`: 32-bit IEEE 754 floating point. Range: approximately ±3.40282347E+38F.
     ```java
     float f = 3.14f; // Note the 'f' suffix
     ```
   - `double`: 64-bit IEEE 754 floating point. Range: approximately ±1.79769313486231570E+308.
     ```java
     double d = 3.14159;
     ```

3. **Character Type**: This is used to store a single character.
   - `char`: 16-bit Unicode character. Range: 0 to 65,535.
     ```java
     char c = 'A';
     ```

4. **Boolean Type**: This is used to store true/false values.
   - `boolean`: Represents one bit of information, but its "size" isn't precisely defined. It can only take the values `true` or `false`.
     ```java
     boolean flag = true;
     ```

### Example Program

Here is a simple Java program that demonstrates the use of primitive data types:

```java
public class PrimitiveTypesExample {
    public static void main(String[] args) {
        // Integer types
        byte b = 100;
        short s = 20000;
        int i = 100000;
        long l = 100000L;

        // Floating-point types
        float f = 3.14f;
        double d = 3.14159;

        // Character type
        char c = 'A';

        // Boolean type
        boolean flag = true;

        // Output the values
        System.out.println("byte: " + b);
        System.out.println("short: " + s);
        System.out.println("int: " + i);
        System.out.println("long: " + l);
        System.out.println("float: " + f);
        System.out.println("double: " + d);
        System.out.println("char: " + c);
        System.out.println("boolean: " + flag);
    }
}
```

### Output
```
byte: 100
short: 20000
int: 100000
long: 100000
float: 3.14
double: 3.14159
char: A
boolean: true
```

### Key Points:
- Primitive data types are the building blocks of data manipulation in Java.
- They are not objects and do not have methods.
- Each primitive type has a fixed size and range.
- They are stored directly in memory, which makes them faster to access compared to objects.

```java
public void m1(double d){
        System.out.println("double argument="+d*d);
    }
    public void m1(float d){
        System.out.println("float argument="+d*d);
    }
    public static void main(String[] args) {
        Test test = new Test();
        test.m1(4); //float argument=16.0
    }
```

---

To build **scalable** J2EE (now Jakarta EE) applications, you would leverage a combination of the following technologies and architectural patterns, focusing on **performance**, **distributed computing**, and **resilience**:

---

### **1. Core Scalability Technologies**
#### **a) Jakarta EE (formerly J2EE) Standards**
| **Technology**       | **Purpose**                                                                 |
|----------------------|-----------------------------------------------------------------------------|
| **EJB (Enterprise Java Beans)** | For distributed transaction management and scalable business logic (use **Stateless Session Beans** for horizontal scaling). |
| **JPA (Jakarta Persistence API)** | Efficient database access with caching (e.g., Hibernate, EclipseLink). |
| **JMS (Jakarta Messaging)** | Asynchronous messaging for decoupling components (e.g., ActiveMQ, IBM MQ). |
| **Jakarta REST (JAX-RS)** | Build scalable RESTful microservices (e.g., Jersey, RESTEasy). |
| **Jakarta Servlet** | Lightweight HTTP request handling (scales well with thread pools). |
| **CDI (Contexts and Dependency Injection)** | For loosely coupled, testable components. |

#### **b) Caching**
| **Technology**       | **Use Case**                                                                 |
|----------------------|-----------------------------------------------------------------------------|
| **JCache (JSR-107)** | Standard caching API (integrate with Hazelcast, Redis, or Ehcache). |
| **Hibernate L2 Cache** | Reduce database load for read-heavy apps. |

---

### **2. Scalability Patterns**
#### **a) Horizontal Scaling**
- **Stateless Services**: Use **stateless EJBs** or **JAX-RS endpoints** to enable easy scaling across nodes.
- **Load Balancing**: Distribute traffic using **Nginx**, **HAProxy**, or cloud load balancers (AWS ALB).
- **Session Replication**: Use **Jakarta EE Session Replication** or external stores (Redis) for stateful apps.

#### **b) Asynchronous Processing**
- **JMS Queues**: Offload long-running tasks to message queues (e.g., order processing).
- **Jakarta Concurrency API**: Manage thread pools for background tasks.

#### **c) Database Scaling**
- **Read Replicas**: Use JPA with **read/write splitting** (e.g., via Hibernate sharding).
- **Connection Pooling**: Use **HikariCP** or **Jakarta DataSource** to manage DB connections.

---

### **3. Cloud-Native Scalability**
| **Technology**       | **Purpose**                                                                 |
|----------------------|-----------------------------------------------------------------------------|
| **Kubernetes** | Orchestrate scalable Jakarta EE containers (e.g., Payara, Open Liberty). |
| **MicroProfile** | Lightweight alternative to Jakarta EE for microservices (fault tolerance, metrics). |
| **Serverless (Quarkus)** | Build scalable, low-latency apps with GraalVM native compilation. |

---

### **4. Performance Optimization**
| **Technology**       | **Use Case**                                                                 |
|----------------------|-----------------------------------------------------------------------------|
| **Jakarta Bean Validation** | Validate data before processing to reduce errors. |
| **JTA (Jakarta Transactions)** | Distributed transactions across microservices (use sparingly for scalability). |
| **gRPC** | High-performance RPC for inter-service communication. |

---

### **5. Example Architecture**
```java
// Scalable JAX-RS Service (Stateless)
@Path("/orders")
@Stateless // EJB for transaction management
public class OrderService {
    @PersistenceContext
    private EntityManager em;

    @POST
    @Transactional
    public Response createOrder(Order order) {
        em.persist(order);
        return Response.ok().build();
    }

    @GET
    @Cacheable // JCache annotation
    public List<Order> getOrders() {
        return em.createQuery("SELECT o FROM Order o", Order.class).getResultList();
    }
}

// Async Processing with JMS
@JMSDestinationDefinition(
    name = "java:/queue/OrderQueue",
    interfaceName = "jakarta.jms.Queue"
)
public class OrderProcessor {
    @Inject
    private JMSContext jmsContext;

    @Resource(lookup = "java:/queue/OrderQueue")
    private Queue queue;

    public void queueOrder(Order order) {
        jmsContext.createProducer().send(queue, order); // Async processing
    }
}
```

---

### **6. Key Considerations**
1. **Statelessness**: Prefer stateless components for horizontal scaling.
2. **Caching**: Cache aggressively (use Redis for distributed caching).
3. **Database**: Optimize queries (JPA + Hibernate L2 cache).
4. **Async**: Use JMS or Kafka for background processing.
5. **Monitoring**: Integrate **MicroProfile Metrics** for scalability insights.

---

### **7. Modern Alternatives**
- **Quarkus** or **Spring Boot**: Lightweight frameworks with Jakarta EE compatibility.
- **Reactive Programming** (e.g., Eclipse Vert.x): For high-throughput apps.

---

### **Summary Table**
| **Scalability Need**       | **Jakarta EE Technology**                          | **Complementary Tools**               |
|---------------------------|--------------------------------------------------|--------------------------------------|
| **Stateless Scaling**      | Stateless EJBs, JAX-RS                           | Kubernetes, Docker                   |
| **Database Scaling**       | JPA + Caching                                    | Read replicas, Sharding              |
| **Async Processing**       | JMS, Jakarta Concurrency                         | Kafka, RabbitMQ                      |
| **Resilience**             | MicroProfile (Fault Tolerance, Retry)            | Istio, Circuit Breakers              |
| **Monitoring**             | MicroProfile Metrics, OpenTelemetry              | Prometheus, Grafana                  |

By combining these technologies with cloud-native practices, you can build **highly scalable** Jakarta EE applications. For greenfield projects, consider **Quarkus** or **MicroProfile** for better startup performance.
