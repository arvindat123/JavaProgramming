---
---

### **What is a Primary Key and a Composite Key?**

1. **Primary Key**:
   - A **primary key** is a unique identifier for a record in a database table.
   - It ensures that each row in the table is uniquely identifiable.
   - A table can have only one primary key, and its value must be unique and not `NULL`.

   **Example**:
   ```sql
   CREATE TABLE users (
       id INT PRIMARY KEY,
       name VARCHAR(50),
       email VARCHAR(50)
   );
   ```

2. **Composite Key**:
   - A **composite key** is a primary key consisting of two or more columns in a table.
   - The combination of these columns uniquely identifies a record in the table.

   **Example**:
   ```sql
   CREATE TABLE orders (
       order_id INT,
       product_id INT,
       quantity INT,
       PRIMARY KEY (order_id, product_id)
   );
   ```

---

### **Composite Key Representation in JPA**

In JPA, a composite key can be represented using either:
1. **`@IdClass` Annotation**: Separate class for the composite key.
2. **`@EmbeddedId` Annotation**: Embedded primary key class.

---

### **Example 1: Using `@IdClass`**

1. **Step 1: Create a Composite Key Class**
   - This class must be `Serializable` and include fields that match the composite key columns.
   - It must override `equals()` and `hashCode()`.

   ```java
   import java.io.Serializable;
   import java.util.Objects;

   public class OrderId implements Serializable {
       private int orderId;
       private int productId;

       // Default Constructor
       public OrderId() {}

       // Parameterized Constructor
       public OrderId(int orderId, int productId) {
           this.orderId = orderId;
           this.productId = productId;
       }

       // Getters and Setters
       public int getOrderId() {
           return orderId;
       }

       public void setOrderId(int orderId) {
           this.orderId = orderId;
       }

       public int getProductId() {
           return productId;
       }

       public void setProductId(int productId) {
           this.productId = productId;
       }

       @Override
       public boolean equals(Object o) {
           if (this == o) return true;
           if (o == null || getClass() != o.getClass()) return false;
           OrderId orderId = (OrderId) o;
           return this.orderId == orderId.orderId && this.productId == orderId.productId;
       }

       @Override
       public int hashCode() {
           return Objects.hash(orderId, productId);
       }
   }
   ```

2. **Step 2: Map the Composite Key with `@IdClass`**
   - Use `@IdClass` to specify the composite key class.

   ```java
   import jakarta.persistence.Entity;
   import jakarta.persistence.Id;
   import jakarta.persistence.IdClass;

   @Entity
   @IdClass(OrderId.class)
   public class Order {
       @Id
       private int orderId;

       @Id
       private int productId;

       private int quantity;

       // Getters and Setters
       public int getOrderId() {
           return orderId;
       }

       public void setOrderId(int orderId) {
           this.orderId = orderId;
       }

       public int getProductId() {
           return productId;
       }

       public void setProductId(int productId) {
           this.productId = productId;
       }

       public int getQuantity() {
           return quantity;
       }

       public void setQuantity(int quantity) {
           this.quantity = quantity;
       }
   }
   ```

---

### **Example 2: Using `@EmbeddedId`**

1. **Step 1: Create an Embeddable Key Class**
   - Annotate the class with `@Embeddable`.

   ```java
   import jakarta.persistence.Embeddable;
   import java.io.Serializable;
   import java.util.Objects;

   @Embeddable
   public class OrderKey implements Serializable {
       private int orderId;
       private int productId;

       // Default Constructor
       public OrderKey() {}

       // Parameterized Constructor
       public OrderKey(int orderId, int productId) {
           this.orderId = orderId;
           this.productId = productId;
       }

       // Getters and Setters
       public int getOrderId() {
           return orderId;
       }

       public void setOrderId(int orderId) {
           this.orderId = orderId;
       }

       public int getProductId() {
           return productId;
       }

       public void setProductId(int productId) {
           this.productId = productId;
       }

       @Override
       public boolean equals(Object o) {
           if (this == o) return true;
           if (o == null || getClass() != o.getClass()) return false;
           OrderKey orderKey = (OrderKey) o;
           return orderId == orderKey.orderId && productId == orderKey.productId;
       }

       @Override
       public int hashCode() {
           return Objects.hash(orderId, productId);
       }
   }
   ```

2. **Step 2: Use `@EmbeddedId` in the Entity**
   - Embed the key in the entity using `@EmbeddedId`.

   ```java
   import jakarta.persistence.EmbeddedId;
   import jakarta.persistence.Entity;

   @Entity
   public class Order {
       @EmbeddedId
       private OrderKey id;

       private int quantity;

       // Getters and Setters
       public OrderKey getId() {
           return id;
       }

       public void setId(OrderKey id) {
           this.id = id;
       }

       public int getQuantity() {
           return quantity;
       }

       public void setQuantity(int quantity) {
           this.quantity = quantity;
       }
   }
   ```

---

### **Choosing Between `@IdClass` and `@EmbeddedId`**

- Use **`@IdClass`** when:
  - The composite key logic is shared across multiple entities.
  - You prefer defining the key fields in the entity.

- Use **`@EmbeddedId`** when:
  - The composite key is specific to a single entity.
  - You want a more modular and reusable key class.

---

### **Example of Query Using Composite Key**

```java
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("example-unit");
        EntityManager em = emf.createEntityManager();

        // Create a new order
        em.getTransaction().begin();
        OrderKey orderKey = new OrderKey(1, 101);
        Order order = new Order();
        order.setId(orderKey);
        order.setQuantity(5);
        em.persist(order);
        em.getTransaction().commit();

        // Fetch the order
        Order fetchedOrder = em.find(Order.class, orderKey);
        System.out.println("Order Quantity: " + fetchedOrder.getQuantity());
    }
}
```

---

### **Key Points**
1. Composite keys require careful handling of `equals()` and `hashCode()`.
2. Defensive coding ensures no accidental misuse of composite key logic.
3. Use `@IdClass` for a flat structure and `@EmbeddedId` for modular design.

Both approaches are valid, and the choice depends on your application's requirements and design preferences.

---
Here are some advanced Hibernate mapping interview questions and answers, with examples for experienced professionals:

### 1. **What is Hibernate mapping and why is it needed?**
**Answer**:
Hibernate mapping defines the way Java classes are mapped to database tables and the relationships between them. It tells Hibernate how to map class fields to columns in the database. This mapping helps in achieving Object-Relational Mapping (ORM), which simplifies database operations by abstracting them as Java objects.

**Example**:
In Hibernate XML mapping:
```xml
<class name="com.example.model.Employee" table="EMPLOYEE">
    <id name="id" column="EMP_ID">
        <generator class="increment"/>
    </id>
    <property name="name" column="EMP_NAME"/>
    <property name="department" column="DEPARTMENT"/>
</class>
```

Using annotations:
```java
@Entity
@Table(name = "EMPLOYEE")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMP_ID")
    private Long id;

    @Column(name = "EMP_NAME")
    private String name;

    @Column(name = "DEPARTMENT")
    private String department;

    // Getters and setters
}
```

### 2. **Explain the different types of mappings in Hibernate.**
**Answer**:
Hibernate supports various types of mappings, such as:

1. **One-to-One Mapping**:
   - Maps a single entity to another entity in a one-to-one relationship.
   **Example**:
   ```java
   @Entity
   public class User {
       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       private Long id;

       @OneToOne(cascade = CascadeType.ALL)
       @JoinColumn(name = "profile_id")
       private Profile profile;
   }
   ```

2. **One-to-Many Mapping**:
   - Maps a single entity to multiple related entities.
   **Example**:
   ```java
   @Entity
   public class Department {
       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       private Long id;

       @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
       private List<Employee> employees;
   }
   ```

3. **Many-to-One Mapping**:
   - Many entities relate to a single entity.
   **Example**:
   ```java
   @Entity
   public class Employee {
       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       private Long id;

       @ManyToOne
       @JoinColumn(name = "department_id")
       private Department department;
   }
   ```

4. **Many-to-Many Mapping**:
   - Maps multiple entities to multiple related entities.
   **Example**:
   ```java
   @Entity
   public class Student {
       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       private Long id;

       @ManyToMany
       @JoinTable(
           name = "student_course",
           joinColumns = @JoinColumn(name = "student_id"),
           inverseJoinColumns = @JoinColumn(name = "course_id")
       )
       private List<Course> courses;
   }
   ```

### 3. **What is the difference between `@OneToOne` and `@ManyToOne` mapping?**
**Answer**:
- **`@OneToOne`**: Maps a single entity to another single entity. Each row in the source table can relate to at most one row in the target table.
- **`@ManyToOne`**: Many rows in the source table can relate to a single row in the target table. It is used when many entities share a common relationship with a single entity.

**Example**:
```java
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "address")
    private User user;
}

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
```

### 4. **What is `@JoinColumn` and how is it used in Hibernate?**
**Answer**:
`@JoinColumn` is used to specify the foreign key column for an association in Hibernate. It indicates that a column in the entity table will act as a foreign key referencing another table.

**Example**:
```java
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;
}
```
In this example, `department_id` in the `Employee` table acts as a foreign key pointing to the primary key in the `Department` table.

### 5. **Explain the difference between `@JoinTable` and `@JoinColumn`.**
**Answer**:
- **`@JoinColumn`**: Used for single-valued associations (e.g., `@OneToOne`, `@ManyToOne`). It defines the foreign key column for the relationship.
- **`@JoinTable`**: Used for many-to-many relationships and specifies the join table that associates two tables.

**Example with `@JoinTable`**:
```java
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(
        name = "student_course",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courses;
}
```

### 6. **What is the role of `@Embeddable` and `@Embedded` in Hibernate?**
**Answer**:
`@Embeddable` is used to define a class whose instances are stored as part of an owning entity's table. The `@Embedded` annotation is used in the entity to specify an embeddable class.

**Example**:
```java
@Embeddable
public class Address {
    private String street;
    private String city;
    private String state;
    private String zipCode;
}

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Address address;
}
```
In this example, the `Address` fields are stored in the `Employee` table as columns.

### 7. **How do you handle bidirectional relationships in Hibernate?**
**Answer**:
Bidirectional relationships involve maintaining references in both entities. To avoid infinite loops during serialization, you can use the `mappedBy` attribute and choose `@JsonIgnore` (from Jackson) or `@JsonManagedReference`/`@JsonBackReference`.

**Example**:
```java
@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<Employee> employees;
}

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
}
```
In this example, the `mappedBy` attribute tells Hibernate that the `employees` collection in `Department` is mapped by the `department` field in `Employee`.

These questions cover a range of concepts that demonstrate a deep understanding of Hibernate mappings.
