package org.Java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

class EmployeeClass {
    private int id;
    private String name;

    EmployeeClass(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeClass employee = (EmployeeClass) o;
        return id == employee.id && Objects.equals(name, employee.name);
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

   public String toString() {
        return id + "---" + name;
    }

}

public class RemoveDuplicateObject {
    public static void main(String[] args) {
        List<EmployeeClass> employeesWithDuplicates = new ArrayList<>();
        employeesWithDuplicates.add(new EmployeeClass(1, "John"));
        employeesWithDuplicates.add(new EmployeeClass(2, "Jane"));
        employeesWithDuplicates.add(new EmployeeClass(1, "John")); // Duplicate
        employeesWithDuplicates.add(new EmployeeClass(3, "Bob"));
        employeesWithDuplicates.add(new EmployeeClass(2, "Jane")); // Duplicate

        employeesWithDuplicates.stream().distinct().toList().forEach(System.out::println);
        System.out.println("--------------------------------------------------------");
        employeesWithDuplicates.stream().distinct().collect(Collectors.toList()).forEach(System.out::println);
    }
}
