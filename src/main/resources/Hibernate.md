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
