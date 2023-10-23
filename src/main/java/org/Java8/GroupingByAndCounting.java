package org.Java8;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class PersonObject {
	private String name;
	private int age;

	public PersonObject(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	@Override
	public String toString() {
		return "Person{" +
				"name='" + name + '\'' +
				", age=" + age +
				'}';
	}
}



public class GroupingByAndCounting {
	public static void main(String[] args) {
		List<PersonObject> people = Arrays.asList(
				new PersonObject("Alice", 25),
				new PersonObject("Bob", 30),
				new PersonObject("Charlie", 25),
				new PersonObject("David", 30)
				);

		// Grouping people by age
		Map<Integer, List<PersonObject>> peopleByAge = people.stream().collect(Collectors.groupingBy(PersonObject::getAge));
		Map<Integer, List<PersonObject>> peopleByAgeWithoutMethodReference = people.stream()
				.collect(Collectors.groupingBy(d->d.getAge()));
		// Print the result
		peopleByAge.forEach((age, persons) -> { System.out.println("Age " + age + ": " + persons);});
		peopleByAgeWithoutMethodReference.forEach((age, persons) -> { System.out.println("Age " + age + ": " + persons);});
		
		// Grouping people by age and counting them
        Map<Integer, Long> countByAge = people.stream().collect(Collectors.groupingBy(PersonObject::getAge, Collectors.counting()));
        // Print the result
        countByAge.forEach((age, count) -> {System.out.println("Age " + age + ": " + count + " person(s)");});
		
		
	}
}

