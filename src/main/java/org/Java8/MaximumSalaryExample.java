package org.Java8;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
/*Assuming you have a collection of employees and each employee has properties like name, salary, and department, you can use the Stream API to find the maximum salary in a specific department. Here's an example:
*/

class Employee {
	String name;
	double salary;
	String department;

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
//The streams classes have multiple forms of general reduction operations, called reduce() and collect(), as well as multiple specialized reduction forms such as sum(), max(), or count().

public class MaximumSalaryExample {
	public static void main(String[] args) {
		// Sample list of employees
		List<Employee> employees = Arrays.asList(new Employee("Alice", 50000, "HR"), new Employee("Bob", 60000, "IT"),
				new Employee("Charlie", 75000, "Finance"), new Employee("David", 70000, "IT"));

		// Specify the department for which you want to find the maximum salary
		String targetDepartment = "IT";

		// Use Stream API to filter employees by department and find the max salary
		Optional<Double> maxSalary = employees.stream().filter(employee -> employee.department.equals(targetDepartment))
				.map(Employee::getSalary).max(Double::compare);

		// Print the result
		if (maxSalary.isPresent()) {
			System.out.println("Maximum salary in " + targetDepartment + " department: " + maxSalary.get());
		} else {
			System.out.println("No employees found in " + targetDepartment + " department.");
		}


	}
}
