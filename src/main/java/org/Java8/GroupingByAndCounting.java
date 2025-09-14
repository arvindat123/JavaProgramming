package org.Java8;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        return "Person{" + "name='" + name + '\'' + ", age=" + age + '}';
    }
}


public class GroupingByAndCounting {
    public static void main(String[] args) {
        List<PersonObject> people = Arrays.asList(new PersonObject("Alice", 25), new PersonObject("Bob", 30), new PersonObject("Charlie", 25), new PersonObject("David", 30));

        // Grouping people by age
        Map<Integer, List<PersonObject>> peopleByAge = people.stream().collect(Collectors.groupingBy(PersonObject::getAge));
        Map<Integer, List<PersonObject>> peopleByAgeWithoutMethodReference = people.stream().collect(Collectors.groupingBy(d -> d.getAge()));
        // Print the result
        peopleByAge.forEach((age, persons) -> {
            System.out.println("Age " + age + ": " + persons);
        });
        peopleByAgeWithoutMethodReference.forEach((age, persons) -> {
            System.out.println("Age " + age + ": " + persons);
        });

        // Grouping people by age and counting them
        Map<Integer, Long> countByAge = people.stream().collect(Collectors.groupingBy(PersonObject::getAge, Collectors.counting()));
        // Print the result
        countByAge.forEach((age, count) -> {
            System.out.println("Age " + age + ": " + count + " person(s)");
        });

        //Nested grouping:
        List<String> words = Arrays.asList("apple", "banana", "avocado", "orange", "blueberry");

        // Grouping by the length of the words and then by the first letter
        Map<Integer, Map<Character, List<String>>> nestedGrouping = words.stream().collect(Collectors.groupingBy(String::length, Collectors.groupingBy(s -> s.charAt(0))));
        System.out.println(nestedGrouping);

        Map<Integer, List<String>> collect1 = words.stream().collect(Collectors.groupingBy(String::length));
        System.out.println(collect1); // {5=[apple], 6=[banana, orange], 7=[avocado], 9=[blueberry]}

        //Counting occurrences:
        List<String> words2 = Arrays.asList("apple", "banana", "avocado", "orange", "blueberry");
        // Counting occurrences of words by their length
        Map<Integer, Long> countingOccurrences = words2.stream().collect(Collectors.groupingBy(String::length, Collectors.counting()));
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

        String input = "ilovejavaprogramming";
        String[] strArray = input.split("");
	/*	String joining from arraylist, set, list, array
	String str = String.join("",strArray); //in place of strArray, set and arraylist can also be passed
		System.out.println(str);
		OR
		set.stream().collect(Collectors.joining(""));
		OR
		set.stream().reduce("",(a,b)->a+b);
		*/
        System.out.println(Arrays.asList(strArray)); //[i, l, o, v, e, j, a, v, a, p, r, o, g, r, a, m, m, i, n, g]

        Map<String, List<String>> collect = Arrays.asList(strArray).stream().collect(Collectors.groupingBy(s -> s));
        System.out.println(collect); //{p=[p], a=[a, a, a], r=[r, r], e=[e], v=[v, v], g=[g, g], i=[i, i], j=[j], l=[l], m=[m, m], n=[n], o=[o, o]}

        //Map<String, Long> collect1 = Arrays.asList(strArray).stream().collect(Collectors.groupingBy(s->s, Collectors.counting()));
        Map<String, Long> collect11 = Arrays.asList(strArray).stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(collect11); //{p=1, a=3, r=2, e=1, v=2, g=2, i=2, j=1, l=1, m=2, n=1, o=2}

        //Get duplicate letter
        String collect2 = Arrays.asList(strArray).stream().collect(Collectors.groupingBy(s -> s, Collectors.counting())).entrySet().stream().filter(m -> m.getValue() > 1).map(n -> n.getKey()).collect(Collectors.joining(""));
        System.out.println(collect2); //arvgimo

        //get non duplicate letter
        String collect3 = Arrays.asList(strArray).stream().collect(Collectors.groupingBy(s -> s, Collectors.counting())).entrySet().stream().filter(m -> m.getValue() == 1).map(n -> n.getKey()).collect(Collectors.joining(""));
        System.out.println(collect3); //pejln

        //get distinct letter
        String collect4 = Arrays.asList(strArray).stream().distinct().collect(Collectors.joining(""));
        System.out.println(collect4);//ilovejaprgmn

        //Find first non - repeat element from a string, HashMap doesn't preserve insertion order so use LinkedHashMap to preserve insertion order
        String str = Arrays.asList(strArray).stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream().filter(m -> m.getValue() == 1).findFirst().get().getKey();
        System.out.println(str); //p

        String str1 = Arrays.asList(strArray).stream().collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting())).entrySet().stream().filter(m -> m.getValue() == 1).findFirst().get().getKey();
        System.out.println(str1); //l

        //Longest string in a Array of strings
        /*String str2 = "java spring springBoot Programme microservices";
        String[] strArray2 = str2.split(" ");*/
        String[] strArray1 = {"java", "spring", "springBoot","Programme","microservices"};
        //Stream.of(strArray1).findFirst();
        String str3 = Arrays.asList(strArray1).stream().sorted(Comparator.reverseOrder()).findFirst().get(); // springBoot lexicographical order
        String outputLongestString = Arrays.stream(strArray1).sorted((a,b) -> b.compareTo(a)).findFirst().get(); //springBoot
        String outputLongestString1 = Arrays.stream(strArray1).sorted((a,b) -> b.length()-a.length()).findFirst().get(); //microservices
        String outputLongestString2 = Arrays.stream(strArray1).reduce((word1,word2) -> word1.length()>word2.length() ? word1 : word2).get();//microservices
        System.out.println(outputLongestString2);

        //Find the number starting with 1
        int[] array = {10,2,7,4,55,1,23,45,67,43};
        List<String> num = Arrays.stream(array).boxed().map(s -> s+"").filter(s -> s.startsWith("1")).collect(Collectors.toList());
        System.out.println(num);

        //String.join method
        String[] stringArray = {"a","b","c","r"};
        String out = String.join("",stringArray); //abcr
        String out1 = String.join("/",stringArray); //   a/b/c/r
        System.out.println(out1);

        //skip and limit method
        IntStream.rangeClosed(1, 10).skip(1).limit(8).forEach(System.out::println);

    }
}
/*	In Java 8, the Collectors.groupingBy is a powerful collector provided by the Stream API
		for grouping elements of a stream based on a classification function.
		It's commonly used in conjunction with the Map data structure to create groups of elements. */
