---
# Encapsulation in Java: A Detailed Explanation with Example

## What is Encapsulation?

Encapsulation is one of the four fundamental OOP concepts (along with inheritance, polymorphism, and abstraction). It refers to the bundling of data (variables) and methods (functions) that operate on the data into a single unit called a class. More importantly, encapsulation is about restricting direct access to some of an object's components, which is a way of preventing accidental modification of data.

## Key Principles of Encapsulation

1. **Data Hiding**: Making fields private to prevent direct access
2. **Controlled Access**: Providing public getter and setter methods to access and modify private fields
3. **Bundling**: Combining data and methods that operate on that data within one unit (class)

## Detailed Example

Let's look at a complete example demonstrating encapsulation:

```java
public class BankAccount {
    // Private fields (data hiding)
    private String accountNumber;
    private String accountHolder;
    private double balance;
    
    // Constructor
    public BankAccount(String accountNumber, String accountHolder, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        if(initialBalance >= 0) {  // Validation
            this.balance = initialBalance;
        } else {
            this.balance = 0;
            System.out.println("Initial balance cannot be negative. Set to 0.");
        }
    }
    
    // Public getter methods (controlled access)
    public String getAccountNumber() {
        return accountNumber;
    }
    
    public String getAccountHolder() {
        return accountHolder;
    }
    
    public double getBalance() {
        return balance;
    }
    
    // Public setter methods with validation (controlled access)
    public void setAccountHolder(String accountHolder) {
        if(accountHolder != null && !accountHolder.isEmpty()) {
            this.accountHolder = accountHolder;
        } else {
            System.out.println("Account holder name cannot be empty.");
        }
    }
    
    // Methods to perform operations (bundled with data)
    public void deposit(double amount) {
        if(amount > 0) {
            balance += amount;
            System.out.println("Deposited: " + amount);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }
    
    public void withdraw(double amount) {
        if(amount > 0) {
            if(balance >= amount) {
                balance -= amount;
                System.out.println("Withdrawn: " + amount);
            } else {
                System.out.println("Insufficient funds.");
            }
        } else {
            System.out.println("Withdrawal amount must be positive.");
        }
    }
    
    public void displayAccountInfo() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Balance: $" + balance);
    }
}

// Main class to test our encapsulated BankAccount
public class Main {
    public static void main(String[] args) {
        // Create a new bank account
        BankAccount account = new BankAccount("123456789", "John Doe", 1000.0);
        
        // Access fields through methods (not directly)
        System.out.println("Initial balance: " + account.getBalance());
        
        // Perform operations
        account.deposit(500.0);
        account.withdraw(200.0);
        
        // Try to withdraw more than balance
        account.withdraw(2000.0);
        
        // Try to set invalid account holder name
        account.setAccountHolder("");
        
        // Try to deposit negative amount
        account.deposit(-100.0);
        
        // Display account info
        account.displayAccountInfo();
    }
}
```

## Importance of Encapsulation

1. **Data Protection**: Prevents unauthorized access and modification of data
   - Example: In our BankAccount, balance can't be directly set to a negative value

2. **Increased Flexibility**: Allows you to change internal implementation without affecting other code
   - Example: You could change how balance is stored (maybe to use BigDecimal for precision) without changing how other classes interact with it

3. **Better Control**: You can add validation logic in setter methods
   - Example: We validate that deposit amounts must be positive

4. **Maintainability**: Makes code easier to maintain and modify
   - All related data and operations are in one place

5. **Reusability**: Encapsulated classes can be easily reused in other programs

6. **Hiding Implementation Details**: Users of the class don't need to know how it works internally
   - They just need to know what methods to call

7. **Reduced Complexity**: By hiding internal details, encapsulation reduces complexity for users of the class

## Best Practices for Encapsulation

1. Make fields private by default
2. Provide public getter methods only when necessary
3. Make setter methods private or protected when possible
4. Always validate data in setter methods
5. Keep methods that operate on the data in the same class as the data

Encapsulation is a powerful concept that helps create more robust, maintainable, and secure Java applications. By properly encapsulating your classes, you create clear boundaries between different components of your system, making your code more modular and easier to work with.

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
## hierarchy of collection in java?

The **Java Collections Framework** is a unified architecture for representing and manipulating collections. It provides a well-designed hierarchy of interfaces and classes for storing and processing groups of objects. Below is the detailed hierarchy of the Java Collections Framework:

---

### **1. Core Interfaces**
The Java Collections Framework is built around a set of core interfaces:

1. **`Iterable<T>`**
   - Root interface for all collections.
   - Provides the `iterator()` method to iterate over elements.

2. **`Collection<E>`**
   - Extends `Iterable<E>`.
   - Represents a group of objects (elements).
   - Core methods: `add()`, `remove()`, `size()`, `isEmpty()`, `contains()`, etc.

3. **`List<E>`**
   - Extends `Collection<E>`.
   - Represents an ordered collection (sequence).
   - Allows duplicate elements.
   - Core implementations: `ArrayList`, `LinkedList`, `Vector`, `Stack`.

4. **`Set<E>`**
   - Extends `Collection<E>`.
   - Represents a collection of unique elements (no duplicates).
   - Core implementations: `HashSet`, `LinkedHashSet`, `TreeSet`.

5. **`Queue<E>`**
   - Extends `Collection<E>`.
   - Represents a collection designed for holding elements prior to processing.
   - Core implementations: `LinkedList`, `PriorityQueue`.

6. **`Deque<E>`**
   - Extends `Queue<E>`.
   - Represents a double-ended queue (supports element insertion and removal at both ends).
   - Core implementations: `ArrayDeque`, `LinkedList`.

7. **`Map<K, V>`**
   - Does **not** extend `Collection<E>`.
   - Represents a collection of key-value pairs.
   - Keys are unique, values can be duplicated.
   - Core implementations: `HashMap`, `LinkedHashMap`, `TreeMap`, `Hashtable`.

---

### **2. Class Hierarchy**
The Java Collections Framework provides concrete implementations of the core interfaces. Here's the hierarchy:

#### **`Collection<E>` Implementations**
1. **`List<E>` Implementations**
   - **`ArrayList<E>`**
     - Resizable array implementation.
     - Fast random access.
   - **`LinkedList<E>`**
     - Doubly-linked list implementation.
     - Efficient for frequent insertions/deletions.
   - **`Vector<E>`**
     - Synchronized resizable array.
     - Legacy class (use `ArrayList` instead).
   - **`Stack<E>`**
     - Extends `Vector<E>`.
     - Represents a last-in-first-out (LIFO) stack.

2. **`Set<E>` Implementations**
   - **`HashSet<E>`**
     - Uses a hash table for storage.
     - Does not maintain insertion order.
   - **`LinkedHashSet<E>`**
     - Extends `HashSet<E>`.
     - Maintains insertion order.
   - **`TreeSet<E>`**
     - Implements `SortedSet<E>`.
     - Stores elements in a sorted order (natural or custom).

3. **`Queue<E>` Implementations**
   - **`PriorityQueue<E>`**
     - Implements a priority heap.
     - Orders elements based on natural ordering or a custom comparator.
   - **`LinkedList<E>`**
     - Can also be used as a `Queue<E>`.

4. **`Deque<E>` Implementations**
   - **`ArrayDeque<E>`**
     - Resizable array implementation of a double-ended queue.
   - **`LinkedList<E>`**
     - Can also be used as a `Deque<E>`.

#### **`Map<K, V>` Implementations**
1. **`HashMap<K, V>`**
   - Uses a hash table for storage.
   - Does not maintain insertion order.
2. **`LinkedHashMap<K, V>`**
   - Extends `HashMap<K, V>`.
   - Maintains insertion order.
3. **`TreeMap<K, V>`**
   - Implements `SortedMap<K, V>`.
   - Stores key-value pairs in sorted order (natural or custom).
4. **`Hashtable<K, V>`**
   - Legacy class (use `HashMap` instead).
   - Synchronized.

---

### **3. Utility Classes**
The Java Collections Framework also provides utility classes for working with collections:

1. **`Collections`**
   - Provides static methods for operations like sorting, searching, shuffling, etc.
   - Example: `Collections.sort(list)`.

2. **`Arrays`**
   - Provides static methods for working with arrays.
   - Example: `Arrays.asList(array)`.

---

### **4. Legacy Classes**
Before the introduction of the Java Collections Framework, Java had legacy classes for collections:

1. **`Vector<E>`**
   - Synchronized resizable array.
   - Replaced by `ArrayList<E>`.

2. **`Hashtable<K, V>`**
   - Synchronized key-value storage.
   - Replaced by `HashMap<K, V>`.

3. **`Stack<E>`**
   - Extends `Vector<E>`.
   - Represents a LIFO stack.
   - Replaced by `Deque<E>` implementations.

---

### **5. Diagram of the Hierarchy**

```
Iterable<E>
    |
Collection<E>
    |
    +-- List<E>
    |     |
    |     +-- ArrayList<E>
    |     +-- LinkedList<E>
    |     +-- Vector<E>
    |           |
    |           +-- Stack<E>
    |
    +-- Set<E>
    |     |
    |     +-- HashSet<E>
    |     |      |
    |     |      +-- LinkedHashSet<E>
    |     |
    |     +-- TreeSet<E>
    |
    +-- Queue<E>
          |
          +-- PriorityQueue<E>
          +-- Deque<E>
                |
                +-- ArrayDeque<E>
                +-- LinkedList<E>

Map<K, V>
    |
    +-- HashMap<K, V>
    |      |
    |      +-- LinkedHashMap<K, V>
    |
    +-- TreeMap<K, V>
    +-- Hashtable<K, V>
```

---

### **Key Points**
1. **`Collection<E>`** is the root interface for all collections (except `Map<K, V>`).
2. **`List<E>`**, **`Set<E>`**, and **`Queue<E>`** are the main sub-interfaces of `Collection<E>`.
3. **`Map<K, V>`** is a separate interface for key-value pairs.
4. Use **`Collections`** and **`Arrays`** utility classes for common operations.
5. Prefer modern collection classes (e.g., `ArrayList`, `HashMap`) over legacy classes (e.g., `Vector`, `Hashtable`).

This hierarchy provides a flexible and powerful way to work with collections in Java.


---
## Diagram of map<K, V> hierarchy?

The **`Map<K, V>`** hierarchy in Java is part of the Java Collections Framework and represents key-value pairs. Below is the **diagram** and **explanation** of the `Map<K, V>` hierarchy:

---

### **Map<K, V> Hierarchy Diagram**

```
Map<K, V>
    |
    +-- HashMap<K, V>
    |      |
    |      +-- LinkedHashMap<K, V>
    |
    +-- TreeMap<K, V>
    |
    +-- Hashtable<K, V>
    |
    +-- SortedMap<K, V>
          |
          +-- NavigableMap<K, V>
                |
                +-- TreeMap<K, V>
```

---

### **Explanation of the Hierarchy**

#### **1. `Map<K, V>`**
- **Root interface** for all map implementations.
- Represents a collection of key-value pairs.
- Keys are unique, and each key maps to exactly one value.
- **Core methods**:
  - `put(K key, V value)`: Adds a key-value pair.
  - `get(K key)`: Retrieves the value associated with the key.
  - `remove(K key)`: Removes the key-value pair.
  - `keySet()`: Returns a set of keys.
  - `values()`: Returns a collection of values.
  - `entrySet()`: Returns a set of key-value pairs.

---

#### **2. `HashMap<K, V>`**
- **Most commonly used implementation** of `Map<K, V>`.
- Uses a **hash table** for storage.
- **Features**:
  - Does **not maintain insertion order**.
  - Allows **one null key** and **multiple null values**.
  - Provides **O(1)** time complexity for `get()` and `put()` operations (on average).
- **Example**:
  ```java
  Map<String, Integer> map = new HashMap<>();
  map.put("Alice", 25);
  map.put("Bob", 30);
  System.out.println(map.get("Alice")); // Output: 25
  ```

---

#### **3. `LinkedHashMap<K, V>`**
- Extends `HashMap<K, V>`.
- **Maintains insertion order** of keys.
- **Features**:
  - Slower than `HashMap` due to maintaining order.
  - Provides **O(1)** time complexity for `get()` and `put()` operations.
- **Example**:
  ```java
  Map<String, Integer> map = new LinkedHashMap<>();
  map.put("Alice", 25);
  map.put("Bob", 30);
  System.out.println(map); // Output: {Alice=25, Bob=30} (insertion order preserved)
  ```

---

#### **4. `TreeMap<K, V>`**
- Implements `SortedMap<K, V>` and `NavigableMap<K, V>`.
- Stores key-value pairs in **sorted order** (natural ordering or custom comparator).
- **Features**:
  - Does **not allow null keys** (but allows null values).
  - Provides **O(log n)** time complexity for `get()` and `put()` operations.
- **Example**:
  ```java
  Map<String, Integer> map = new TreeMap<>();
  map.put("Alice", 25);
  map.put("Bob", 30);
  System.out.println(map); // Output: {Alice=25, Bob=30} (sorted by keys)
  ```

---

#### **5. `Hashtable<K, V>`**
- **Legacy class** (introduced in Java 1.0).
- **Synchronized** (thread-safe).
- **Features**:
  - Does **not allow null keys or values**.
  - Slower than `HashMap` due to synchronization.
- **Example**:
  ```java
  Map<String, Integer> map = new Hashtable<>();
  map.put("Alice", 25);
  map.put("Bob", 30);
  System.out.println(map.get("Bob")); // Output: 30
  ```

---

#### **6. `SortedMap<K, V>`**
- Extends `Map<K, V>`.
- Represents a map with keys sorted in **natural order** or by a **custom comparator**.
- **Core methods**:
  - `firstKey()`: Returns the first (lowest) key.
  - `lastKey()`: Returns the last (highest) key.
  - `subMap(K fromKey, K toKey)`: Returns a view of the portion of the map whose keys range from `fromKey` to `toKey`.

---

#### **7. `NavigableMap<K, V>`**
- Extends `SortedMap<K, V>`.
- Provides additional methods for navigation:
  - `lowerKey(K key)`: Returns the greatest key less than the given key.
  - `higherKey(K key)`: Returns the smallest key greater than the given key.
  - `floorKey(K key)`: Returns the greatest key less than or equal to the given key.
  - `ceilingKey(K key)`: Returns the smallest key greater than or equal to the given key.

---

### **Key Points**
1. **`Map<K, V>`** is the root interface for all map implementations.
2. **`HashMap<K, V>`** is the most commonly used implementation (fast, unordered).
3. **`LinkedHashMap<K, V>`** maintains insertion order.
4. **`TreeMap<K, V>`** stores keys in sorted order.
5. **`Hashtable<K, V>`** is a legacy, synchronized implementation.
6. **`SortedMap<K, V>`** and **`NavigableMap<K, V>`** provide additional functionality for sorted maps.

---

### **When to Use Which Map?**
| **Map Type**         | **Use Case**                                                                 |
|-----------------------|-----------------------------------------------------------------------------|
| **`HashMap<K, V>`**   | General-purpose map with no ordering requirements.                          |
| **`LinkedHashMap<K, V>`** | When insertion order needs to be preserved.                              |
| **`TreeMap<K, V>`**   | When keys need to be sorted (natural or custom order).                     |
| **`Hashtable<K, V>`** | Legacy code or when thread safety is required (use `ConcurrentHashMap` instead). |

By understanding the `Map<K, V>` hierarchy, you can choose the appropriate implementation based on your requirements.

