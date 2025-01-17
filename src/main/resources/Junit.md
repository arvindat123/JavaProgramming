Here’s a list of interview questions specifically focused on **JUnit 5**. These questions cover basic, intermediate, and advanced topics to assess knowledge of JUnit 5's features and its practical use in testing Java applications:

---

### **Basic Questions**
1. What is JUnit 5, and how does it differ from JUnit 4?
2. How do you set up JUnit 5 in a project? Which dependencies are required?
3. What are the main components/modules of JUnit 5?
   - **Answer**: JUnit Platform, JUnit Jupiter, and JUnit Vintage.
4. How do you write a simple test case using JUnit 5?
5. What are the most commonly used JUnit 5 annotations? Briefly explain their purpose.
6. What is the use of `@Test` in JUnit 5?
7. How can you disable a test in JUnit 5?
8. What is the difference between `@BeforeEach` and `@BeforeAll`?
9. How do `@AfterEach` and `@AfterAll` differ from `@BeforeEach` and `@BeforeAll`?
10. What is the role of `@DisplayName` in JUnit 5?

---

### **Intermediate Questions**
1. How do you handle exceptions in JUnit 5 tests?
   - **Hint**: Use `assertThrows` or `assertDoesNotThrow`.
2. Explain the difference between `@Nested` and regular test classes.
3. What are parameterized tests in JUnit 5, and how do you implement them?
4. Explain the role of `@ParameterizedTest` and the associated sources like `@ValueSource`, `@CsvSource`, `@CsvFileSource`, and `@MethodSource`.
5. How do you run JUnit 4 tests using JUnit 5?
   - **Hint**: Use the JUnit Vintage Engine.
6. How do you group tests in JUnit 5?
   - **Answer**: Using `@Tag` annotations.
7. How can you configure test execution order in JUnit 5?
8. Explain how to use conditional test execution with annotations like `@EnabledOnOs`, `@EnabledIf`, and `@EnabledOnJre`.
9. What is the purpose of assertions in JUnit 5? Mention some common assertion methods.
10. How do you integrate JUnit 5 with a build tool like Maven or Gradle?

---

### **Advanced Questions**
1. What is the difference between `Assertions` and `Assumptions` in JUnit 5?
2. Explain the use of `Dynamic Tests` in JUnit 5.
   - **Hint**: `@TestFactory` is used for creating dynamic tests.
3. How do you handle test lifecycle extensions in JUnit 5?
   - **Hint**: Use `@ExtendWith` and implement the `BeforeTestExecutionCallback` or `AfterTestExecutionCallback` interfaces.
4. What are the key differences between JUnit 5’s `Assertions.assertAll()` and multiple `assertEquals()` calls?
5. How do you implement custom extensions in JUnit 5?
6. How do you use JUnit 5's `TestInstance.Lifecycle.PER_CLASS` feature? What are its advantages?
7. What are the differences between `@RepeatedTest` and regular test methods?
8. Can you explain how to mock dependencies in JUnit 5 tests?
   - **Hint**: Combine JUnit 5 with mocking frameworks like Mockito or MockK.
9. How can you capture logs in JUnit 5 tests?
10. Explain how JUnit 5 supports parallel test execution and how you can configure it.

---

### **Practical/Scenario-Based Questions**
1. Write a JUnit 5 test case to verify a service method that throws a custom exception.
2. How would you test a method that interacts with a database using JUnit 5?
3. Create a parameterized test in JUnit 5 to test multiple sets of data for a calculation method.
4. Implement a custom extension in JUnit 5 to measure the execution time of all test methods in a class.
5. How would you integrate JUnit 5 with CI/CD pipelines?

---

Let me know if you need detailed answers or sample code for any of these questions!
