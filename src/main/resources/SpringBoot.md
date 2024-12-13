Here are some advanced Spring Boot interview questions and answers with detailed examples, suitable for experienced professionals:

### 1. **What is Spring Boot and how does it differ from the traditional Spring Framework?**
**Answer**: 
Spring Boot is an extension of the Spring Framework that simplifies the development of Spring applications by offering default configurations and starter templates. It allows developers to create stand-alone, production-grade applications with minimal configuration. Unlike the traditional Spring Framework, which requires a significant amount of configuration, Spring Boot provides:

- **Auto-Configuration**: Automatically configures your Spring application based on the libraries available on the classpath.
- **Embedded Servers**: Comes with built-in servers (like Tomcat, Jetty), enabling running the application as a standalone JAR.
- **Spring Boot Starters**: Pre-configured starter dependencies to reduce dependency management.

**Example**:
With Spring Boot, a simple `application.properties` or `application.yml` file can replace complex XML configurations of traditional Spring.

```properties
server.port=8081
spring.datasource.url=jdbc:mysql://localhost:3306/mydb
spring.datasource.username=root
spring.datasource.password=password
```

### 2. **How does auto-configuration work in Spring Boot?**
**Answer**:
Spring Boot's auto-configuration feature scans the classpath and automatically configures beans based on the dependencies and configurations available. The `@SpringBootApplication` annotation encompasses `@Configuration`, `@EnableAutoConfiguration`, and `@ComponentScan`.

**Example**:
If `spring-boot-starter-web` is on the classpath, Spring Boot automatically configures a `DispatcherServlet` and other beans needed for a web application.

**Detailed Example**:
```java
@SpringBootApplication
public class MyApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }
}
```
- Auto-configuration can be customized using `@ConditionalOnClass`, `@ConditionalOnMissingBean`, etc., in your custom configuration.

### 3. **How do you create a custom starter in Spring Boot?**
**Answer**:
To create a custom starter:
1. **Create a module** with dependencies needed for your custom starter.
2. Include `spring-boot-autoconfigure` and `spring-boot-starter` dependencies.
3. Write an auto-configuration class annotated with `@Configuration` and use `@Conditional` annotations to ensure it loads only when certain conditions are met.
4. Register the auto-configuration in `META-INF/spring.factories`.

**Example**:
```java
@Configuration
@ConditionalOnClass(MyCustomService.class)
public class MyCustomAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public MyCustomService myCustomService() {
        return new MyCustomService();
    }
}
```

**`spring.factories`** file:
```
org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
com.example.autoconfig.MyCustomAutoConfiguration
```

### 4. **How do you manage application properties in Spring Boot?**
**Answer**:
Spring Boot provides `application.properties` and `application.yml` to manage application configurations. Properties can be organized by profiles for different environments using the `application-{profile}.properties` format.

**Example**:
```properties
# application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/mydb
spring.datasource.username=root
spring.datasource.password=password

# application-dev.properties
server.port=8082
```
**Access in code**:
```java
@Value("${spring.datasource.url}")
private String dbUrl;
```

Or using a `@ConfigurationProperties` class:
```java
@ConfigurationProperties(prefix = "app")
public class AppProperties {
    private String name;
    private int version;
    // Getters and setters
}
```

### 5. **What is Spring Boot Actuator and how is it used?**
**Answer**:
Spring Boot Actuator provides production-ready features to help monitor and manage applications. It includes endpoints for metrics, health checks, and application information.

**Example**:
To enable Actuator, add the dependency:
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

Configure specific endpoints in `application.properties`:
```properties
management.endpoints.web.exposure.include=health,info,metrics
management.endpoint.health.show-details=always
```

**Access**:
- **Health Endpoint**: `http://localhost:8080/actuator/health`
- **Metrics Endpoint**: `http://localhost:8080/actuator/metrics`

### 6. **Explain the difference between `@RestController` and `@Controller` in Spring Boot.**
**Answer**:
`@RestController` is a combination of `@Controller` and `@ResponseBody`. It is used to create RESTful web services and returns JSON or XML data directly as a response body.

- **`@RestController`**: Each method automatically serializes the return value into JSON format.
- **`@Controller`**: Primarily used to render views and requires `@ResponseBody` or `ModelAndView` to return data.

**Example**:
```java
@RestController
public class MyController {
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, World!";
    }
}

@Controller
public class ViewController {
    @GetMapping("/page")
    public String showPage() {
        return "pageView"; // Refers to a template like pageView.html
    }
}
```

### 7. **What are the different ways to create a RESTful web service in Spring Boot?**
**Answer**:
Spring Boot provides the following ways to create RESTful web services:
- **Using `@RestController` and `@RequestMapping`**.
- **Using `@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping`** for HTTP method-specific requests.

**Example**:
```java
@RestController
@RequestMapping("/api")
public class ProductController {

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.findAll();
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product created = productService.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
}
```

These questions test both conceptual understanding and hands-on knowledge, helping to demonstrate your ability to work with Spring Boot in real-world applications.

---

Here are some advanced Spring interview questions and answers with detailed examples, suitable for experienced professionals:

### 1. **What are the main features of the Spring Framework?**
**Answer**:
The main features of the Spring Framework include:
- **Dependency Injection (DI)**: Simplifies the development by managing object dependencies.
- **Aspect-Oriented Programming (AOP)**: Enables modularizing cross-cutting concerns such as logging and transaction management.
- **Transaction Management**: Provides an abstraction layer for managing transactions.
- **Data Access Framework**: Simplifies data access using JDBC and integrates seamlessly with ORM tools like Hibernate.
- **Spring MVC**: Provides a web framework for building RESTful services and web applications.
- **Spring Boot**: Offers auto-configuration to simplify the creation of production-ready applications.
- **Integration Support**: Provides integration with popular technologies like JPA, JMS, and JMX.

### 2. **Explain Dependency Injection (DI) and how it is implemented in Spring.**
**Answer**:
Dependency Injection (DI) is a design pattern where the control of object creation and dependency management is shifted from the object itself to an external entity (the Spring container). DI promotes loose coupling between components.

**Example**:
**Constructor Injection**:
```java
@Component
public class Car {
    private Engine engine;

    @Autowired
    public Car(Engine engine) {
        this.engine = engine;
    }
}
```

**Setter Injection**:
```java
@Component
public class Car {
    private Engine engine;

    @Autowired
    public void setEngine(Engine engine) {
        this.engine = engine;
    }
}
```

**XML-based Configuration**:
```xml
<bean id="car" class="com.example.Car">
    <constructor-arg ref="engine" />
</bean>

<bean id="engine" class="com.example.Engine" />
```

### 3. **What is Spring Bean Lifecycle and what are the different stages involved?**
**Answer**:
The lifecycle of a Spring bean involves several stages:
1. **Instantiation**: The bean is created by the Spring container.
2. **Populating Properties**: Dependencies are injected.
3. **`setBeanName()`**: The `BeanNameAware` interface method is called, passing the bean's name.
4. **`setBeanFactory()`**: The `BeanFactoryAware` interface method is called.
5. **`setApplicationContext()`**: The `ApplicationContextAware` interface method is called.
6. **Pre-Initialization (`postProcessBeforeInitialization()`)**: Called by `BeanPostProcessor` before initialization methods.
7. **Initialization**: `@PostConstruct` or `afterPropertiesSet()` (from `InitializingBean` interface) or a custom `init` method.
8. **Post-Initialization (`postProcessAfterInitialization()`)**: Called by `BeanPostProcessor` after initialization.
9. **Bean Ready to Use**: The bean is fully initialized.
10. **Destruction**: `@PreDestroy` or `destroy()` (from `DisposableBean` interface) or a custom `destroy` method.
![image](https://github.com/user-attachments/assets/f38c289d-94aa-4463-aea6-57ac16894fca)

**Example**:
```java
@Component
public class MyBean implements InitializingBean, DisposableBean {
    @PostConstruct
    public void postConstruct() {
        System.out.println("PostConstruct method called");
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("InitializingBean's afterPropertiesSet method called");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("PreDestroy method called");
    }

    @Override
    public void destroy() {
        System.out.println("DisposableBean's destroy method called");
    }
}
```

### 4. **What is the difference between `@Component`, `@Repository`, `@Service`, and `@Controller` in Spring?**
**Answer**:
All these annotations are specialized forms of `@Component` and are used for different types of classes:
- **`@Component`**: A generic stereotype for any Spring-managed component.
- **`@Repository`**: Indicates that a class is a Data Access Object (DAO). It also provides exception translation, converting database-related exceptions into Spring's `DataAccessException`.
- **`@Service`**: Used to indicate that a class holds business logic.
- **`@Controller`**: Used for web controllers in the Spring MVC framework. It defines a class as a controller capable of handling HTTP requests.

**Example**:
```java
@Repository
public class UserRepository {
    // Database access logic
}

@Service
public class UserService {
    // Business logic
}

@Controller
public class UserController {
    @GetMapping("/users")
    public String getUsers() {
        return "userList";
    }
}
```

### 5. **What are Spring Profiles and how do you use them?**
**Answer**:
Spring Profiles allow you to define separate configurations for different environments (e.g., development, testing, production). You can activate or switch between profiles using the `spring.profiles.active` property.

**Example**:
**Profile Configuration in `application.properties`**:
```properties
# application-dev.properties
spring.datasource.url=jdbc:mysql://localhost:3306/dev_db
spring.datasource.username=dev_user

# application-prod.properties
spring.datasource.url=jdbc:mysql://localhost:3306/prod_db
spring.datasource.username=prod_user
```

**Using `@Profile` Annotation**:
```java
@Configuration
@Profile("dev")
public class DevConfig {
    @Bean
    public DataSource devDataSource() {
        // return data source for development
    }
}

@Configuration
@Profile("prod")
public class ProdConfig {
    @Bean
    public DataSource prodDataSource() {
        // return data source for production
    }
}
```

**Activating a Profile**:
- Via command line: `java -jar myapp.jar --spring.profiles.active=dev`
- In `application.properties`: `spring.profiles.active=dev`

### 6. **What is Spring AOP and how is it used?**
**Answer**:
Spring AOP (Aspect-Oriented Programming) allows you to separate cross-cutting concerns (like logging, transaction management) from the main business logic. It is used to implement aspects that can be applied to various join points (e.g., method executions).

**Example**:
**Aspect Class**:
```java
@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.example.service.*.*(..))")
    public void logBeforeMethod(JoinPoint joinPoint) {
        System.out.println("Executing method: " + joinPoint.getSignature().getName());
    }
}
```

**Enable AOP**:
```java
@Configuration
@EnableAspectJAutoProxy
public class AppConfig {
}
```

### 7. **Explain the `@Transactional` annotation and its usage.**
**Answer**:
The `@Transactional` annotation in Spring is used to define the transactional behavior of methods or classes. It ensures that a method runs within a transaction, providing rollback capabilities and managing commits.

**Example**:
```java
@Service
public class PaymentService {

    @Transactional
    public void processPayment(Order order) {
        // Logic for processing payment
        // Any RuntimeException will trigger a rollback
    }
}
```

**Key Attributes**:
- **`propagation`**: Defines the transaction propagation type (e.g., `REQUIRED`, `REQUIRES_NEW`).
- **`isolation`**: Defines the transaction isolation level (e.g., `READ_COMMITTED`).
- **`timeout`**: Specifies the time limit for a transaction.
- **`readOnly`**: Marks the transaction as read-only.

**Example with attributes**:
```java
@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE, timeout = 5, readOnly = false)
public void complexTransactionMethod() {
    // Critical transactional logic
}
```

These questions and detailed examples cover a variety of critical concepts in Spring, demonstrating the expertise needed for experienced professionals in a Spring-based environment.
---

Here are some advanced interview questions about Spring and Spring Boot annotations, along with detailed examples to help experienced professionals prepare:

### 1. **What is the difference between `@Component`, `@Service`, `@Repository`, and `@Controller` in Spring?**
**Answer**:
- **`@Component`**: A generic stereotype annotation for any Spring-managed component. It tells Spring to create a bean for this class and manage it in the application context.
- **`@Service`**: A specialization of `@Component`, used for classes that contain business logic. It makes the code more readable and indicates that the class performs service tasks.
- **`@Repository`**: A specialization of `@Component` used to indicate that a class is a Data Access Object (DAO). It provides additional support for exception translation to convert database exceptions into Spring’s `DataAccessException`.
- **`@Controller`**: A specialization of `@Component` for web controllers in Spring MVC. It is used to create REST endpoints or handle web requests.

**Example**:
```java
@Component
public class GeneralComponent {
    // Generic functionality
}

@Service
public class UserService {
    // Business logic methods
}

@Repository
public class UserRepository {
    // Data access methods
}

@Controller
public class UserController {
    @GetMapping("/users")
    public String getUsers() {
        return "userList";
    }
}
```

### 2. **Explain the use of `@Autowired` and its different modes.**
**Answer**:
`@Autowired` is used for dependency injection in Spring. It automatically wires dependencies by type. It can be used on constructors, fields, or setter methods.

**Modes**:
- **Default (required=true)**: If Spring cannot find a bean to inject, it throws an exception.
- **`@Autowired(required=false)`**: If a bean is not found, Spring will not inject and will not throw an exception.

**Example**:
```java
@Component
public class Car {
    private Engine engine;

    // Constructor Injection
    @Autowired
    public Car(Engine engine) {
        this.engine = engine;
    }

    // Field Injection
    @Autowired
    private Transmission transmission;

    // Setter Injection
    @Autowired(required = false)
    public void setWheels(Wheels wheels) {
        this.wheels = wheels;
    }
}
```

### 3. **What is `@Qualifier` and when is it used?**
**Answer**:
`@Qualifier` is used with `@Autowired` to resolve the ambiguity when multiple beans of the same type are available in the application context. It specifies the exact bean to be injected.

**Example**:
```java
@Component("v8Engine")
public class V8Engine implements Engine {
    // V8 engine specifics
}

@Component("v6Engine")
public class V6Engine implements Engine {
    // V6 engine specifics
}

@Component
public class Car {
    private Engine engine;

    @Autowired
    @Qualifier("v8Engine")
    public Car(Engine engine) {
        this.engine = engine;
    }
}
```

### 4. **Explain the `@Primary` annotation and its use case.**
**Answer**:
`@Primary` is used to indicate that a bean should be given preference when multiple candidates are qualified to be autowired. It is helpful in scenarios where a default bean is needed without using `@Qualifier`.

**Example**:
```java
@Component
@Primary
public class V8Engine implements Engine {
    // V8 engine specifics
}

@Component
public class V6Engine implements Engine {
    // V6 engine specifics
}

@Component
public class Car {
    @Autowired
    private Engine engine;  // V8Engine will be autowired due to @Primary
}
```

### 5. **What is `@Configuration` and how does it work?**
**Answer**:
`@Configuration` is used to indicate that a class declares one or more `@Bean` methods and may be processed by the Spring container to generate bean definitions and service requests.

**Example**:
```java
@Configuration
public class AppConfig {
    @Bean
    public Car car() {
        return new Car(engine());
    }

    @Bean
    public Engine engine() {
        return new V8Engine();
    }
}
```
In this example, `AppConfig` acts as a configuration class that defines beans for the Spring container.

### 6. **What is `@Bean` and how is it different from `@Component`?**
**Answer**:
`@Bean` is used to declare a method that returns a bean to be managed by the Spring container. Unlike `@Component`, which is used at the class level, `@Bean` is used within `@Configuration` classes.

**Example**:
```java
@Configuration
public class AppConfig {
    @Bean
    public Engine engine() {
        return new V6Engine();
    }
}
```
**Difference**:
- `@Component` is used for class-level bean registration, while `@Bean` is for method-level bean definition.
- `@Bean` allows more control over bean initialization logic.

### 7. **What is `@RestController` in Spring Boot and how does it differ from `@Controller`?**
**Answer**:
`@RestController` is a specialized version of `@Controller` used to create RESTful web services. It combines `@Controller` and `@ResponseBody`, meaning that all methods return JSON or XML data directly, without needing `@ResponseBody` on each method.

**Example**:
```java
@RestController
@RequestMapping("/api")
public class UserRestController {
    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }
}
```
**Difference**:
- **`@Controller`**: Typically used to render views.
- **`@RestController`**: Used for RESTful web services, returning data directly as JSON or XML.

### 8. **What is `@SpringBootApplication` and what does it do?**
**Answer**:
`@SpringBootApplication` is a convenience annotation that combines:
- `@Configuration`: Denotes the class as a source of bean definitions.
- `@EnableAutoConfiguration`: Enables Spring Boot’s auto-configuration mechanism.
- `@ComponentScan`: Scans for `@Component`, `@Controller`, `@Service`, and other annotated classes within the package.

**Example**:
```java
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```
This annotation makes it easier to set up and run Spring Boot applications with minimal configuration.

### 9. **What is `@PathVariable` and `@RequestParam`?**
**Answer**:
- **`@PathVariable`**: Used to extract values from the URI template.
- **`@RequestParam`**: Used to extract query parameters from the request URL.

**Example**:
```java
@RestController
@RequestMapping("/api")
public class ProductController {

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable("id") Long productId) {
        return productService.getProductById(productId);
    }

    @GetMapping("/products")
    public List<Product> getProductsByCategory(@RequestParam("category") String category) {
        return productService.getProductsByCategory(category);
    }
}
```

### 10. **What is `@Value` and how do you use it in Spring?**
**Answer**:
`@Value` is used to inject values from properties files or environment variables into fields, methods, or constructor parameters.

**Example**:
```java
@Component
public class AppConfig {
    @Value("${app.name}")
    private String appName;

    @Value("${app.version:1.0}")  // Default value if not found
    private String appVersion;

    // Getter methods
}
```
In this example, the values for `appName` and `appVersion` are read from `application.properties` or set with default values if not found.

These questions and detailed examples cover a variety of key Spring and Spring Boot annotations, showcasing expertise for experienced professionals in real-world application development.

---
In Spring Framework, **bean scope** defines the lifecycle and visibility of a bean within the container. It determines how many instances of a bean are created and how they are shared. Spring provides several built-in scopes for beans:

---

### **1. Singleton (Default Scope)**
- **Description**: A single shared instance of the bean is created and used across the entire Spring container.
- **Scope**: Application-wide.
- **Lifecycle**:
  - Bean is created during container initialization (eager initialization by default).
  - Shared across all requests and references.
- **Usage**: For stateless beans, such as service and DAO layers.

#### Example:
```java
@Component
@Scope("singleton") // Default; can be omitted.
public class MySingletonBean {
}
```

---

### **2. Prototype**
- **Description**: A new instance of the bean is created every time it is requested from the container.
- **Scope**: Limited to the object requesting it.
- **Lifecycle**:
  - Bean is created on demand, not during container startup.
  - Container doesn’t manage the complete lifecycle of the bean (e.g., destruction).
- **Usage**: For stateful beans, or beans that are expensive to create and used only for short periods.

#### Example:
```java
@Component
@Scope("prototype")
public class MyPrototypeBean {
}
```

---

### **3. Request (Web Application Scope)**
- **Description**: A single bean instance is created for each HTTP request.
- **Scope**: HTTP request.
- **Lifecycle**:
  - Bean is created at the start of a request and destroyed when the request completes.
- **Usage**: For web applications, such as handling request-specific data.

#### Example:
```java
@Component
@Scope("request")
public class MyRequestScopedBean {
}
```

---

### **4. Session (Web Application Scope)**
- **Description**: A single bean instance is created for each HTTP session.
- **Scope**: HTTP session.
- **Lifecycle**:
  - Bean is created when a session starts and destroyed when the session ends.
- **Usage**: For storing session-specific data in web applications.

#### Example:
```java
@Component
@Scope("session")
public class MySessionScopedBean {
}
```

---

### **5. Application (Web Application Scope)**
- **Description**: A single bean instance is shared across the entire ServletContext.
- **Scope**: ServletContext-wide.
- **Lifecycle**:
  - Bean is created when the ServletContext is initialized and destroyed when it is shut down.
- **Usage**: For application-wide shared resources.

#### Example:
```java
@Component
@Scope("application")
public class MyApplicationScopedBean {
}
```

---

### **6. WebSocket (Web Application Scope)**
- **Description**: A single bean instance is created for each WebSocket session.
- **Scope**: WebSocket session.
- **Lifecycle**:
  - Bean is created when a WebSocket session is established and destroyed when the session ends.
- **Usage**: For WebSocket-based applications.

#### Example:
```java
@Component
@Scope("websocket")
public class MyWebSocketScopedBean {
}
```

---

### **Custom Scopes**
- Spring allows defining custom scopes if the predefined scopes don't meet requirements.
- Implement the `org.springframework.beans.factory.config.Scope` interface to create a custom scope.

---

### **Comparison Table**

| Scope         | Instance Per       | Lifecycle Trigger           | Usage                              |
|---------------|--------------------|-----------------------------|------------------------------------|
| **Singleton** | Spring container   | Application context creation| Stateless, shared resources.      |
| **Prototype** | Each request       | Bean request                | Stateful or short-lived beans.    |
| **Request**   | HTTP request       | HTTP request lifecycle      | Request-specific data.            |
| **Session**   | HTTP session       | HTTP session lifecycle      | Session-specific data.            |
| **Application**| Servlet context   | Servlet context lifecycle   | Application-wide resources.       |
| **WebSocket** | WebSocket session  | WebSocket lifecycle         | WebSocket-specific data.          |

---

### **Important Notes**:
1. **Default Scope**: If no scope is explicitly defined, Spring assumes `singleton` scope.
2. **Web Scopes**: `request`, `session`, `application`, and `websocket` scopes require a web-aware Spring application context (e.g., `Spring MVC` or `Spring Boot` web applications).

---

The `DispatcherServlet` and a regular `Servlet` in Java serve different purposes and operate at different levels in a Java web application. Here's how they differ:

### 1. **Purpose**
   - **`Servlet`:**
     - A `Servlet` is a core Java class that processes HTTP requests and generates HTTP responses. It operates at a low level and can handle tasks such as reading request parameters, writing responses, managing sessions, and interacting with backend resources.
     - Example: A `HttpServlet` subclass might handle a specific URL pattern and directly produce the HTML or JSON response.
   - **`DispatcherServlet`:**
     - A `DispatcherServlet` is a specialized servlet provided by the Spring Framework. It acts as the **front controller** in the Spring MVC framework, coordinating and dispatching HTTP requests to various Spring MVC components such as controllers, views, and exception handlers.

---

### 2. **Role in Application**
   - **`Servlet`:**
     - Handles HTTP requests and responses directly, and you need to write a lot of code for handling application logic, mapping URLs, and interacting with backend components.
   - **`DispatcherServlet`:**
     - Delegates request processing to Spring MVC controllers (annotated with `@Controller` or `@RestController`) and uses other components like `HandlerMapping`, `HandlerAdapter`, and `ViewResolver`.
     - It abstracts away much of the boilerplate code, making application development simpler and more structured.

---

### 3. **Configuration**
   - **`Servlet`:**
     - Defined in `web.xml` or annotated with `@WebServlet`.
     - Example of mapping:
       ```xml
       <servlet>
           <servlet-name>ExampleServlet</servlet-name>
           <servlet-class>com.example.ExampleServlet</servlet-class>
       </servlet>
       <servlet-mapping>
           <servlet-name>ExampleServlet</servlet-name>
           <url-pattern>/example/*</url-pattern>
       </servlet-mapping>
       ```
   - **`DispatcherServlet`:**
     - Defined either in `web.xml` or programmatically via `WebApplicationInitializer` or a Spring Boot application class.
     - Example in Spring Boot:
       ```java
       @SpringBootApplication
       public class Application {
           public static void main(String[] args) {
               SpringApplication.run(Application.class, args);
           }
       }
       ```
     - Automatically configured by Spring Boot when using `spring-boot-starter-web`.

---

### 4. **Extensibility**
   - **`Servlet`:**
     - Developers need to handle all functionality, including routing, validation, and rendering, manually.
   - **`DispatcherServlet`:**
     - Extends `HttpServlet` and provides integration with the Spring ecosystem, such as dependency injection, interceptors, exception handling, and validation.

---

### 5. **Lifecycle**
   - **`Servlet`:**
     - Lifecycle methods like `init()`, `service()`, and `destroy()` must be implemented or overridden for custom behavior.
   - **`DispatcherServlet`:**
     - Inherits lifecycle methods from `HttpServlet`, but its behavior is heavily managed by the Spring container. Customization is typically done by configuring beans in the Spring application context.

---

### 6. **Usage**
   - **`Servlet`:**
     - Suitable for small applications or custom low-level HTTP request handling.
     - Example: A custom servlet for streaming large files.
   - **`DispatcherServlet`:**
     - Ideal for building large-scale, maintainable, and component-based web applications using the Spring MVC framework.

---

### Key Analogy
If a `Servlet` is a low-level worker directly handling HTTP traffic, the `DispatcherServlet` is a traffic controller that directs requests to specialized workers (controllers, services, etc.) based on predefined rules and configurations.
