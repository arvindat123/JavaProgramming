package org.Java8;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
/*Assuming you have a collection of employees and each employee has properties like name, salary, and department, you can use the Stream API to find the maximum salary in a specific department. Here's an example:
 */

//The streams classes have multiple forms of general reduction operations, called reduce() and collect(), as well as multiple specialized reduction forms such as sum(), max(), or count().

public class MaximumSalaryExample {
    public static void main(String[] args) {
        // Sample list of employees
        List<Employee> employees = Arrays.asList(new Employee(null, 50000, "HR"), new Employee("Bob", 60000, "IT"), new Employee("Charlie", 75000, "Finance"), new Employee("David", 70000, "IT"));

        //Summing
        /*
        list.stream().collect(Collectors.groupingBy(foo -> foo.id, Collectors.summingInt(foo->foo.targetCost))).forEach((id,sumTargetCost)->System.out.println(id+"\t"+sumTargetCost));

         list.stream().collect(groupingBy(Function.identity(),
  ()->new TreeMap<>(
    // we are effectively grouping by [id, actualCost]
    Comparator.<Foo,Integer>comparing(foo->foo.id).thenComparing(foo->foo.actualCost)
  ), // and aggregating/ summing targetCost
  Collectors.summingInt(foo->foo.targetCost)))
.forEach((group,targetCostSum) ->
    // take the id and actualCost from the group and actualCost from aggregation
    System.out.println(group.id+"\t"+group.actualCost+"\t"+targetCostSum));
         */
        // Specify the department for which you want to find the maximum salary
        String targetDepartment = "IT";

        // Use Stream API to filter employees by department and find the max salary
        Optional<Double> maxSalary = employees.stream().filter(emp->emp.getName() != null && emp.getName().equals("Bob")).filter(employee -> employee.getDepartment().equals(targetDepartment)).map(Employee::getSalary).max(Double::compare);
        //Below will throw null pointer exception because there getName() will be null and equals cannot be applied on null
        // Optional<Double> maxSalary = employees.stream().filter(emp->emp.getName().equals("Bob")).filter(employee -> employee.getDepartment().equals(targetDepartment)).map(Employee::getSalary).max(Double::compare);

        // Print the result
        if (maxSalary.isPresent()) {
            System.out.println("Maximum salary in " + targetDepartment + " department: " + maxSalary.get());
        } else {
            System.out.println("No employees found in " + targetDepartment + " department.");
        }

        List<String> l = Arrays.asList("A", "B", "C", "D");
        long count = l.stream().peek(System.out::println).count();


    }
}


class Employee {
    String name;
    double salary;
    private String department;

    public Employee(String name, double salary, String department) {
        this.name = name;
        this.salary = salary;
        this.department = department;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the salary
     */
    public double getSalary() {
        return salary;
    }

    /**
     * @return the department
     */
    public String getDepartment() {
        return department;
    }

}
