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
