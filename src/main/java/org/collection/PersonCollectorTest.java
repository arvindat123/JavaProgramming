package org.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class PersonCollectorTest {
	public static void main(String[] args) {
		List<PersonCollector> people = Arrays.asList(new PersonCollector("Beth", 30),
				new PersonCollector("Eric", 31),
				new PersonCollector("Deb", 31),
				new PersonCollector("Liz", 30),
				new PersonCollector("Wendi", 34),
				new PersonCollector("Kathy", 35),
				new PersonCollector("Bert", 32),
				new PersonCollector("Bill", 34),
				new PersonCollector("Robert", 38),
				new PersonCollector("Bill", 40),
				new PersonCollector("Beth", 45),
				new PersonCollector("Bert", 38));
		people.stream().filter(p -> p.getAge() == 34).collect(Collectors.toList()).forEach(System.out::println);
		
		System.out.println("=============================================");
		List<PersonCollector> peopleAged34 = people.stream().filter(p -> p.getAge() == 34).collect(Collectors.toList());
		System.out.println(peopleAged34);
		
		System.out.println("=============================================");
		peopleAged34 = people.stream().filter(p -> p.getAge() == 34).collect(Collectors.toCollection(ArrayList::new));
		//same as without method reference
		peopleAged34 = people.stream().filter(p -> p.getAge() == 34).collect(Collectors.toCollection(() -> new ArrayList<PersonCollector>()));
		System.out.println(peopleAged34);
		
		//groupingBy()
		Map<Integer, List<PersonCollector>> peopleByAge = people.stream().collect(Collectors.groupingBy(PersonCollector ::getAge));
		System.out.println("People by age : " + peopleByAge);
		
		//groupingBy() and counting
		Map<Integer, Long> numPeopleWithAge = people.stream().collect(Collectors.groupingBy(PersonCollector::getAge, Collectors.counting()));
		System.out.println("People by age counting: " + numPeopleWithAge);
		
		//groupingBy() and display name
		Map<Integer, List<String>> namesByAge = people.stream()
				.collect(Collectors.groupingBy(PersonCollector::getAge, Collectors.mapping(PersonCollector::getName, Collectors.toList())));
		System.out.println("People by age and name " + namesByAge);
		
		//partitionBy()
		Map<Boolean, List<PersonCollector>> peopleOlderThan34 = people.stream().collect(Collectors.partitioningBy(d -> d.getAge() > 34));
		System.out.println("People aged more than 34 => " + peopleOlderThan34);
		
		//summimgInt, averagingInt
		Map<String, Integer> sumOfAge = people.stream().filter(d -> d.getName().startsWith("B"))
				.collect(Collectors.groupingBy(PersonCollector::getName,Collectors.summingInt(PersonCollector::getAge)));
		System.out.println("Name and sum of age =>" + sumOfAge);
		
		Map<String, Double> avgOfAge = people.stream().filter(d -> d.getName().startsWith("B"))
				.collect(Collectors.groupingBy(PersonCollector::getName,Collectors.averagingInt(PersonCollector::getAge)));
		System.out.println("Name and sum of age =>" + avgOfAge);
		
		System.out.println("count() => "+people.stream().count() + " Counting method=>"+ people.stream().collect(Collectors.counting()));
		
		//joining
		String older34 = people.stream().filter(d -> d.getAge() > 34).map(PersonCollector::getName).collect(Collectors.joining(", "));
		System.out.println("Joining => "+ older34);
		
		//maxBy(), minBy()
		Optional<PersonCollector> oldest = people.stream().collect(Collectors.maxBy((d1,d2) -> d1.getAge() - d2.getAge()));
		//System.out.println("Oldest person with age =>" + oldest);
		oldest.ifPresent(p -> System.out.println("oldest person => "+ p));
	}
}





















