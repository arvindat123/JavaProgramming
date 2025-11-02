## Java version and feature
### Java 11 [https://docs.oracle.com/en/java/javase/11/language/java-language-changes-release.html]
- Local-Variable Syntax for Lambda Parameters
- Local Variable Type Inference (Java 10)

### Java 17 [https://docs.oracle.com/en/java/javase/17/language/java-language-changes-release.html]
- Sealed class or interface: A sealed class or interface restricts which classes or interfaces can extend or implement it.
- Record Classes: A record is a class that acts as transparent carrier for immutable data. (Java 16)
- Pattern Matching for instanceof (Java 16)
- Text Blocks: A text block is a multiline string literal that avoids the need for most escape sequences, automatically formats the string in a predictable way, and gives the developer control over the format when desired. (Java 15)
- Switch Expressions: This feature extends switch so it can be used as either a statement or an expression, and so that both forms can use either traditional case ... : labels (with fall through) or new case ... -> labels (with no fall through), with a further new statement for yielding a value from a switch expression. (Java 14)

### Java 21 [https://docs.oracle.com/en/java/javase/21/language/java-language-changes-release.html]
- Record Patterns: In this release, support for record patterns appearing in the header of an enhanced for statement has been removed.
- Pattern Matching for switch Expressions and Statements: Parenthesized patterns have been removed, Qualified enum constants as case constants in switch expressions and statements are allowed.

### Java 25 [https://docs.oracle.com/en/java/javase/25/language/java-language-changes-release.html]
- Module Import Statements
- Compact Source Files and Instance Main Methods
  - The new IO class for basic console I/O is now in the java.lang package rather than the java.io package. Thus, it is implicitly imported by every source file.
  -  The static methods of the IO class are no longer implicitly imported into compact source files. Thus invocations of these methods must name the class, for example, IO.println("Hello, world!"), unless the methods are explicitly imported.
  -  The implementation of the IO class is now based upon System.out and System.in rather than the java.io.Console class.
- Flexible Constructor Bodies


Here’s a comprehensive list of **preview features in Java 25**, based on the latest official and community sources[1](https://developer.ibm.com/articles/java-whats-new-java25/)[2](https://javatechonline.com/java-25-new-features-with-examples/)[3](https://www.happycoders.eu/java/java-25-features/):

---

### 🔍 **Preview Features in Java 25**

| **JEP** | **Feature Name** | **Description** |
|--------|------------------|------------------|
| **JEP 507** | **Primitive Types in Patterns (Third Preview)** | Enables pattern matching with primitive types (`int`, `double`, etc.) in `instanceof` and `switch`. |
| **JEP 511** | **Module Import Declarations** | Allows importing entire modules directly in source files, improving modular readability. |
| **JEP 512** | **Compact Source Files and Instance Main Methods** | Lets you write Java programs without class declarations and simplifies the `main()` method. |
| **JEP 513** | **Flexible Constructor Bodies** | Removes the restriction that `super()` or `this()` must be the first statement in a constructor. |
| **JEP 502** | **Stable Values** | Introduces a way to define values that are initialized once and treated as constants, improving performance and thread safety. |
| **JEP 470** | **PEM Encodings for Cryptographic Objects** | Adds support for encoding/decoding cryptographic keys and certificates in PEM format. |
| **JEP 505** | **Structured Concurrency (Fifth Preview)** | Simplifies concurrent programming by treating multiple tasks running in parallel as a single unit of work. |

---

### 🧪 How to Use Preview Features

To compile and run code using preview features:

```bash
javac --enable-preview --release 25 MyApp.java
java --enable-preview MyApp
```

---

Would you like example code for any of these preview features or help deciding which ones are most relevant for your projects?

---

In Java, a **preview feature** is a new language or JVM feature that is **fully specified and implemented**, but **not yet permanent**. It’s included in a JDK release to gather feedback from developers before finalizing it in a future release.

---

### 🔍 **Key Characteristics of Preview Features**

- **Opt-in**: You must explicitly enable them using compiler and runtime flags.
- **Stable but not final**: They are tested and usable, but may change or be removed in future versions.
- **Feedback-driven**: Oracle uses developer feedback to refine or finalize the feature.

---

### ✅ **How to Use Preview Features**

To **compile** and **run** Java code using preview features:

```bash
javac --enable-preview --release <version> MyClass.java
java --enable-preview MyClass
```

For example, with Java 25:

```bash
javac --enable-preview --release 25 Tier.java
java --enable-preview Tier
```

---

### 🧪 Examples of Preview Features in Recent Java Versions

- **Pattern Matching for `switch`** (JEP 406)
- **Record Patterns** (JEP 405)
- **String Templates** (JEP 430)
- **Primitive Types in Patterns** (JEP 507)

---

Would you like a list of all preview features in Java 25 or help setting up a project that uses them?

---
To approach performance tuning for a **legacy Spring Boot service** that takes **20 seconds to respond under load**, here’s a structured and practical strategy:

---

## 🔍 Step-by-Step Performance Tuning Approach

### 1. **Understand the Baseline**
- Measure current performance using tools like:
  - **Spring Boot Actuator**
  - **Prometheus + Grafana**
  - **New Relic / Datadog / AppDynamics**
- Identify:
  - Which endpoints are slow?
  - What kind of load causes the delay?
  - Is it CPU-bound, memory-bound, or I/O-bound?

---

### 2. **Profile the Application**
Use profiling tools to find bottlenecks:
- **JProfiler**, **VisualVM**, or **YourKit**
- Look for:
  - Long-running methods
  - High GC activity
  - Thread contention
  - Database query latency

---

### 3. **Check Database Performance**
- Use **Spring Boot DevTools** or **Hibernate Statistics** to log SQL queries.
- Look for:
  - N+1 query problems
  - Missing indexes
  - Slow joins or subqueries
- Consider:
  - Connection pool tuning (e.g., HikariCP settings)
  - Caching frequently accessed data

---

### 4. **Analyze Threading and Concurrency**
- Check if the service is blocking (e.g., waiting on I/O).
- Use **asynchronous processing** (`@Async`, `CompletableFuture`) where possible.
- Tune thread pools:
  ```yaml
  spring.task.execution.pool.core-size: 10
  spring.task.execution.pool.max-size: 50
  ```

---

### 5. **Optimize Memory Usage**
- Check heap size and GC logs.
- Use JVM options:
  ```bash
  -Xms512m -Xmx2048m -XX:+UseG1GC
  ```
- Avoid loading large objects into memory unnecessarily.

---

### 6. **Enable Caching**
- Use Spring’s caching abstraction:
  ```java
  @Cacheable("users")
  public User getUserById(Long id) { ... }
  ```
- Backed by Redis, Caffeine, or Ehcache.

---

### 7. **Use Connection Pooling**
- Ensure efficient DB connections via **HikariCP** (default in Spring Boot).
- Tune pool size:
  ```yaml
  spring.datasource.hikari.maximum-pool-size=20
  ```

---

### 8. **Tune Web Server Settings**
- If using embedded Tomcat:
  ```yaml
  server.tomcat.max-threads=200
  server.tomcat.accept-count=100
  ```

---

### 9. **Use Load Testing Tools**
- Simulate load using:
  - **Apache JMeter**
  - **Gatling**
  - **Locust**
- Identify how performance degrades with increasing users.

---

### 10. **Consider Architectural Improvements**
- Introduce **circuit breakers** (e.g., Resilience4j)
- Use **rate limiting** and **bulkheads**
- Break monolith into microservices if needed

---

## 🧠 Summary

| Area                  | Tool/Technique                          |
|-----------------------|-----------------------------------------|
| Profiling             | VisualVM, JProfiler                     |
| DB Optimization       | Hibernate logs, indexes, connection pool |
| Caching               | Spring Cache, Redis                     |
| Threading             | Async methods, thread pool tuning       |
| Load Testing          | JMeter, Gatling                         |
| Monitoring            | Actuator, Prometheus, Grafana           |

---

Would you like help creating a **checklist**, a **dashboard setup**, or a **sample profiling report template** for your team?

---

Improving query response time in a Spring Boot application using **native queries** (i.e., raw SQL via `@Query` or `EntityManager`) involves a combination of **database optimization**, **query tuning**, and **application-level improvements**.

Here are some effective strategies:

---

### 🔍 1. **Optimize the Native SQL Query**
- **Use indexes**: Ensure that columns used in `WHERE`, `JOIN`, `ORDER BY`, and `GROUP BY` clauses are indexed.
- **Avoid SELECT ***: Only fetch the columns you need.
- **Limit result set**: Use `LIMIT` or pagination to reduce the amount of data returned.
- **Avoid unnecessary joins**: Simplify joins or use subqueries if they perform better.
- **Use query profiling tools**: Use `EXPLAIN` or `ANALYZE` to understand query execution plans.

---

### ⚙️ 2. **Tune Spring Boot Configuration**
- **Connection Pooling**: Use a high-performance pool like **HikariCP** (default in Spring Boot).
  ```properties
  spring.datasource.hikari.maximum-pool-size=20
  spring.datasource.hikari.connection-timeout=30000
  ```
- **Enable query caching** (if applicable): Use Hibernate's second-level cache or database-level caching.

---

### 🧠 3. **Use Projections or DTOs**
Instead of returning full entities, use **interface-based projections** or **DTOs** to fetch only required fields:
```java
@Query(value = "SELECT name, age FROM users WHERE age > :age", nativeQuery = true)
List<UserDTO> findUsersByAge(@Param("age") int age);
```

---

### 🧪 4. **Batch Processing and Streaming**
- For large datasets, use **streaming** to avoid loading everything into memory:
  ```java
  @Query(value = "SELECT * FROM large_table", nativeQuery = true)
  Stream<LargeEntity> streamAll();
  ```
- Use **batch inserts/updates** for bulk operations.

---

### 🛠️ 5. **Database-Level Improvements**
- **Partitioning**: Split large tables into smaller partitions.
- **Materialized views**: Precompute and store complex query results.
- **Stored procedures**: Offload complex logic to the database.

---

### 📈 6. **Monitoring and Profiling**
Use tools like:
- **Spring Boot Actuator**
- **New Relic**, **Datadog**, or **Prometheus + Grafana**
- **Database logs and slow query logs**

---

Here’s a curated list of **advanced practical questions related to concurrency in Java**, ideal for interviews or deep learning:

---

### 🔁 **Advanced Concurrency Questions in Java**

#### 1. **Thread Safety in Collections**
**Q:** How would you make a non-thread-safe collection like `ArrayList` thread-safe? What are the trade-offs between using `Collections.synchronizedList()` vs `CopyOnWriteArrayList`?

#### 2. **Deadlock Detection and Prevention**
**Q:** Write a Java program that simulates a deadlock scenario. Then, modify it to prevent deadlock using lock ordering or `tryLock()`.

#### 3. **Executor Framework**
**Q:** Design a task scheduler using `ScheduledExecutorService` that runs periodic tasks and handles exceptions gracefully.

#### 4. **Producer-Consumer Problem**
**Q:** Implement a thread-safe producer-consumer system using `BlockingQueue`. How would you handle backpressure?

#### 5. **Custom Thread Pool**
**Q:** Create a custom thread pool using `ThreadPoolExecutor`. How would you tune its parameters for high throughput and low latency?

#### 6. **Atomic Variables vs Synchronization**
**Q:** Compare `AtomicInteger` with `synchronized` blocks for incrementing a shared counter. Which is better and why?

#### 7. **CompletableFuture**
**Q:** Use `CompletableFuture` to run multiple asynchronous tasks and combine their results. How would you handle exceptions in the pipeline?

#### 8. **Fork/Join Framework**
**Q:** Implement a parallel sum of a large array using the `ForkJoinPool`. How does it differ from traditional thread pools?

#### 9. **ThreadLocal Usage**
**Q:** Demonstrate a use case for `ThreadLocal` in a multi-threaded web application. What are the risks of memory leaks?

#### 10. **Concurrency with Streams**
**Q:** How can you parallelize stream operations in Java? What are the caveats of using `parallelStream()`?

---

Would you like code examples for any of these, or a mock interview-style walkthrough for one?
