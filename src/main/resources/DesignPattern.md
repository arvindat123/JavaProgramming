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
