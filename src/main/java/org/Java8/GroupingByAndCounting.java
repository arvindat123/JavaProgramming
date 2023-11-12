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

		//Nested grouping:
		List<String> words = Arrays.asList("apple", "banana", "avocado", "orange", "blueberry");

		// Grouping by the length of the words and then by the first letter
		Map<Integer, Map<Character, List<String>>> nestedGrouping = words.stream()
				.collect(Collectors.groupingBy(String::length,
						Collectors.groupingBy(s -> s.charAt(0))));
		System.out.println(nestedGrouping);

		//Counting occurrences:
		List<String> words2 = Arrays.asList("apple", "banana", "avocado", "orange", "blueberry");
		// Counting occurrences of words by their length
		Map<Integer, Long> countingOccurrences = words2.stream()
				.collect(Collectors.groupingBy(String::length, Collectors.counting()));
		System.out.println(countingOccurrences);

		//Another aggregation that we can perform is to get the blog post with the maximum number of likes:
		/*Map<BlogPostType, Optional<BlogPost>> maxLikesPerPostType = posts.stream()
				.collect(groupingBy(BlogPost::getType,
						maxBy(comparingInt(BlogPost::getLikes))));
		Similarly, we can apply the minBy downstream collector to get the blog post with the minimum number of likes.
		Note that the maxBy and minBy collectors take into account the possibility that the collection to
		which they are applied could be empty.
		This is why the value type in the map is Optional<BlogPost>.

Similar to groupingBy is the groupingByConcurrent collector, which leverages multi-core architectures.
This collector has three overloaded methods that take exactly the same arguments as the respective overloaded
methods of the groupingBy collector. The return type of the groupingByConcurrent collector, however,
must be an instance of the ConcurrentHashMap class or a subclass of it.

To do a grouping operation concurrently, the stream needs to be parallel:
ConcurrentMap<BlogPostType, List<BlogPost>> postsPerType = posts.parallelStream()
  .collect(groupingByConcurrent(BlogPost::getType));
If we choose to pass a Map supplier function to the groupingByConcurrent collector, then we need to make sure
that the function returns either a ConcurrentHashMap or a subclass of it
		*/
	}
}
/*	In Java 8, the Collectors.groupingBy is a powerful collector provided by the Stream API
		for grouping elements of a stream based on a classification function.
		It's commonly used in conjunction with the Map data structure to create groups of elements. */
