# **Strategy Design Pattern**

The **Strategy Design Pattern** is a behavioral design pattern that defines a family of algorithms, encapsulates each one, and makes them interchangeable. This pattern allows the algorithm to be selected at runtime. 

It promotes **Open-Closed Principle** by enabling the addition of new algorithms without modifying the existing codebase. The strategy pattern is widely used in cases where multiple algorithms or approaches need to be dynamically chosen based on the situation.

---

### **Key Components**
1. **Context**:
   - Maintains a reference to a `Strategy` object.
   - Interacts with the `Strategy` to execute a behavior.

2. **Strategy Interface**:
   - Defines a common interface for all supported algorithms.
   - Ensures that the `Context` can use any concrete strategy interchangeably.

3. **Concrete Strategies**:
   - Implement the `Strategy` interface.
   - Define specific algorithms or behaviors.

---

### **Example Scenario**

Imagine an application for **online payment processing** where different payment methods (e.g., Credit Card, PayPal, and Google Pay) are available. The user can choose a payment method at runtime.

---

### **Implementation in Java**

#### **1. Strategy Interface**
Defines a common method for all payment strategies.
```java
interface PaymentStrategy {
    void pay(int amount);
}
```

#### **2. Concrete Strategies**
Implement specific payment methods.

**Credit Card Payment:**
```java
class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;

    public CreditCardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using Credit Card: " + cardNumber);
    }
}
```

**PayPal Payment:**
```java
class PayPalPayment implements PaymentStrategy {
    private String email;

    public PayPalPayment(String email) {
        this.email = email;
    }

    @Override
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using PayPal account: " + email);
    }
}
```

**Google Pay Payment:**
```java
class GooglePayPayment implements PaymentStrategy {
    private String phoneNumber;

    public GooglePayPayment(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using Google Pay linked to phone number: " + phoneNumber);
    }
}
```

#### **3. Context Class**
The `Context` uses a `PaymentStrategy` to execute the selected payment method.

```java
class PaymentContext {
    private PaymentStrategy paymentStrategy;

    // Allows setting the strategy at runtime
    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    // Executes the strategy
    public void pay(int amount) {
        if (paymentStrategy == null) {
            System.out.println("No payment method selected.");
        } else {
            paymentStrategy.pay(amount);
        }
    }
}
```

#### **4. Main Method (Usage)**
Demonstrates the flexibility of the strategy pattern.

```java
public class StrategyPatternExample {
    public static void main(String[] args) {
        PaymentContext context = new PaymentContext();

        // Using Credit Card Payment
        context.setPaymentStrategy(new CreditCardPayment("1234-5678-9876-5432"));
        context.pay(500);

        // Switching to PayPal Payment
        context.setPaymentStrategy(new PayPalPayment("user@example.com"));
        context.pay(300);

        // Switching to Google Pay Payment
        context.setPaymentStrategy(new GooglePayPayment("9876543210"));
        context.pay(200);
    }
}
```

---

### **Output**
```
Paid 500 using Credit Card: 1234-5678-9876-5432
Paid 300 using PayPal account: user@example.com
Paid 200 using Google Pay linked to phone number: 9876543210
```

---

### **Advantages of Strategy Pattern**
1. **Open-Closed Principle**: Adding new strategies does not affect existing code.
2. **Simplifies Maintenance**: Algorithms are encapsulated in separate classes.
3. **Runtime Flexibility**: Algorithms can be changed dynamically.

---

### **When to Use the Strategy Pattern**
- When you need multiple algorithms for a task, and these algorithms can be swapped at runtime.
- When you want to avoid using conditional statements like `if-else` or `switch` to choose between algorithms.
- When different parts of the application need different variations of a behavior. 

---

### **Real-World Examples**
1. **Sorting Algorithms**: Dynamically choosing between quicksort, mergesort, or bubblesort.
2. **Payment Gateways**: Integrating multiple payment providers.
3. **Data Compression**: Supporting various compression strategies (e.g., ZIP, GZIP).

---

Solid design patterns, particularly the **SOLID principles**, are a set of design guidelines in object-oriented programming that help developers create more maintainable, scalable, and robust software systems. The benefits of adhering to these principles are significant and include:

---

### **1. Improved Code Maintainability**
- **Benefit**: Code that follows SOLID principles is easier to understand, modify, and extend.
- **Why**: Each class and method has a single responsibility, making it easier to locate and fix bugs or add new features without affecting other parts of the system.

---

### **2. Enhanced Scalability**
- **Benefit**: SOLID principles promote modular and loosely coupled designs, making it easier to scale the system.
- **Why**: Components are independent, so you can scale or replace individual parts without disrupting the entire system.

---

### **3. Reduced Code Duplication**
- **Benefit**: SOLID principles encourage reusable and modular code.
- **Why**: By adhering to principles like the **Single Responsibility Principle (SRP)** and **Open/Closed Principle (OCP)**, you avoid duplicating code and create reusable components.

---

### **4. Easier Testing**
- **Benefit**: Code that follows SOLID principles is easier to test.
- **Why**: Smaller, single-purpose classes and methods are easier to isolate and test with unit tests, leading to more reliable and maintainable test suites.

---

### **5. Better Flexibility and Adaptability**
- **Benefit**: SOLID principles make it easier to adapt to changing requirements.
- **Why**: The **Open/Closed Principle (OCP)** ensures that systems are open for extension but closed for modification, allowing you to add new features without altering existing code.

---

### **6. Reduced Risk of Bugs**
- **Benefit**: SOLID principles minimize the risk of introducing bugs when making changes.
- **Why**: By isolating responsibilities and reducing dependencies, changes in one part of the system are less likely to affect other parts.

---

### **7. Improved Collaboration**
- **Benefit**: SOLID principles make it easier for teams to collaborate on a codebase.
- **Why**: Clear responsibilities and modular design allow multiple developers to work on different parts of the system simultaneously without conflicts.

---

### **8. Long-Term Cost Savings**
- **Benefit**: Following SOLID principles reduces the cost of maintaining and extending software over time.
- **Why**: Clean, modular, and well-structured code requires less effort to maintain, debug, and enhance, saving time and resources in the long run.

---

### **9. Better Code Reusability**
- **Benefit**: SOLID principles promote reusable components.
- **Why**: By designing classes and methods with a single responsibility and avoiding tight coupling, you can reuse code across different parts of the system or even in other projects.

---

### **10. Enhanced Readability**
- **Benefit**: Code that adheres to SOLID principles is easier to read and understand.
- **Why**: Clear responsibilities, meaningful abstractions, and reduced complexity make the codebase more accessible to new developers and team members.

---

### **Summary of SOLID Principles**
1. **Single Responsibility Principle (SRP)**: A class should have only one reason to change.
2. **Open/Closed Principle (OCP)**: Software entities should be open for extension but closed for modification.
3. **Liskov Substitution Principle (LSP)**: Subtypes must be substitutable for their base types.
4. **Interface Segregation Principle (ISP)**: Clients should not be forced to depend on interfaces they do not use.
5. **Dependency Inversion Principle (DIP)**: High-level modules should not depend on low-level modules; both should depend on abstractions.

By following these principles, developers can create systems that are easier to maintain, extend, and adapt, ultimately leading to higher-quality software and more efficient development processes.

---

The Adapter Design Pattern is a structural design pattern that allows two incompatible interfaces to work together. It acts as a bridge between two incompatible interfaces by converting the interface of a class into another interface that a client expects. This pattern is particularly useful when you want to reuse existing classes but their interfaces do not match the requirements of the new system.

### Key Components of the Adapter Pattern:
1. **Target Interface**: This is the interface that the client expects to interact with.
2. **Adaptee**: This is the existing class that has the functionality that the client needs but has an incompatible interface.
3. **Adapter**: This is the class that implements the Target Interface and wraps the Adaptee, translating the calls from the client to the Adaptee.

### Example in Java:

Let's consider a scenario where we have an existing `LegacyPrinter` class that has a method `printDocument(String text)` but the client expects to use a `ModernPrinter` interface with a method `print(String content)`.

#### Step 1: Define the Target Interface (`ModernPrinter`)
```java
public interface ModernPrinter {
    void print(String content);
}
```

#### Step 2: Define the Adaptee (`LegacyPrinter`)
```java
public class LegacyPrinter {
    public void printDocument(String text) {
        System.out.println("Legacy Printer: " + text);
    }
}
```

#### Step 3: Create the Adapter (`PrinterAdapter`)
The `PrinterAdapter` class implements the `ModernPrinter` interface and wraps the `LegacyPrinter` to adapt its interface.

```java
public class PrinterAdapter implements ModernPrinter {
    private LegacyPrinter legacyPrinter;

    public PrinterAdapter(LegacyPrinter legacyPrinter) {
        this.legacyPrinter = legacyPrinter;
    }

    @Override
    public void print(String content) {
        // Convert the modern print call to the legacy print call
        legacyPrinter.printDocument(content);
    }
}
```

#### Step 4: Client Code
The client code interacts with the `ModernPrinter` interface, unaware of the `LegacyPrinter` class.

```java
public class Client {
    public static void main(String[] args) {
        // Create an instance of the LegacyPrinter
        LegacyPrinter legacyPrinter = new LegacyPrinter();

        // Wrap the LegacyPrinter in a PrinterAdapter
        ModernPrinter printer = new PrinterAdapter(legacyPrinter);

        // Use the ModernPrinter interface to print
        printer.print("Hello, World!");
    }
}
```

### Output:
```
Legacy Printer: Hello, World!
```

### Explanation:
- **Target Interface (`ModernPrinter`)**: The client expects to use this interface to print documents.
- **Adaptee (`LegacyPrinter`)**: This is the existing class that has the functionality to print but with a different method signature.
- **Adapter (`PrinterAdapter`)**: This class implements the `ModernPrinter` interface and internally uses the `LegacyPrinter` to perform the actual printing. It translates the `print(String content)` method call to the `printDocument(String text)` method call.

### Benefits of the Adapter Pattern:
1. **Reusability**: Allows existing classes to be reused without modifying their code.
2. **Flexibility**: Makes it easier to introduce new adapters for different adaptees without changing the client code.
3. **Separation of Concerns**: Keeps the client code decoupled from the implementation details of the adaptee.

### When to Use the Adapter Pattern:
- When you want to use an existing class, but its interface does not match the one you need.
- When you want to create a reusable class that cooperates with classes that don't necessarily have compatible interfaces.
- When you need to integrate multiple third-party libraries with different interfaces.

This pattern is widely used in real-world applications, especially when dealing with legacy code or integrating with third-party libraries.
