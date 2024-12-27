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
