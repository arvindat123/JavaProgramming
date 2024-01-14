package org.Java8;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.Stream;
 
public class StudentStreamTest {
	public static void main(String[] args) {

		Student student1 = new Student("Jayesh", 20, new Addressaas("1234"),Arrays.asList(new MobileNumber("1233"), new MobileNumber("1234")));
		Student student2 = new Student("Khyati", 20, new Addressaas("1235"),Arrays.asList(new MobileNumber("1111"), new MobileNumber("3333"), new MobileNumber("1233")));
		Student student3 = new Student("Jason", 20, new Addressaas("1236"),Arrays.asList(new MobileNumber("3333"), new MobileNumber("4444")));

		List<Student> students = Arrays.asList(student1, student2, student3);
		Stream<String> stream = students.stream().filter(obj -> obj.getAge()>30).map(obj->obj.getName());

		/*****************************************************
		 * Get student with exact match name "jayesh"
		 *****************************************************/
		System.out.println("-----------1---------");
		Optional<Student> stud = students.stream().filter(student -> student.getName().equals("Jayesh")).findFirst();
		System.out.println(stud.isPresent() ? stud.get().getName() : "No student found");
		System.out.println("---------2-----------");

		/*****************************************************
		 * Get student with matching address "1235"
		 *****************************************************/
		Optional<Student> stud1 = students.stream().filter(student -> student.getAddress().getZipcode().equals("1235"))
				.findFirst();
		System.out.println(stud1.isPresent() ? stud1.get().getName() : "No student found");
		System.out.println("----------3----------");

		/*****************************************************
		 * Get all student having mobile numbers 3333.
		 *****************************************************/
		List<Student> stud2 = students.stream().filter(student111 -> student111.getMobileNumbers().stream()
				.anyMatch(x -> Objects.equals(x.getNumber(), "3333"))).collect(Collectors.toList());

		String result1 = stud2.stream().map(std -> std.getName()).collect(Collectors.joining(",", "[", "]"));
		System.out.println(result1);
		System.out.println("-----------4---------");

		/*****************************************************
		 * Get all student having mobile number 1233 and 1234
		 *****************************************************/
		List<Student> stud3 = students.stream()
				.filter(student -> student.getMobileNumbers().stream()
						.allMatch(x -> Objects.equals(x.getNumber(), "1233") || Objects.equals(x.getNumber(), "1234")))
				.collect(Collectors.toList());

		String result4 = stud3.stream().map(std -> std.getName()).collect(Collectors.joining(",", "[", "]"));
		System.out.println(result4);
		System.out.println("----------5----------");

		/*****************************************************
		 * Create a List<Student> from the List<TempStudent>
		 *****************************************************/
		TempStudent tmpStud1 = new TempStudent("Jayesh1", 201, new Addressaas("12341"),
				Arrays.asList(new MobileNumber("12331"), new MobileNumber("12341")));

		TempStudent tmpStud2 = new TempStudent("Khyati1", 202, new Addressaas("12351"),
				Arrays.asList(new MobileNumber("11111"), new MobileNumber("33331"), new MobileNumber("12331")));

		List<TempStudent> tmpStudents = Arrays.asList(tmpStud1, tmpStud2);

		List<Student> studentList = tmpStudents.stream()
				.map(tmpStud -> new Student(tmpStud.name, tmpStud.age, tmpStud.address, tmpStud.mobileNumbers))
				.collect(Collectors.toList());

		System.out.println(studentList);
		System.out.println("---------6-----------");

		/*****************************************************
		 * Convert List<Student> to List<String> of student name
		 *****************************************************/
		List<String> studentsName = studentList.stream().map(Student::getName).collect(Collectors.toList());

		System.out.println(studentsName.stream().collect(Collectors.joining(",")));
		System.out.println(studentsName.stream().collect(Collectors.joining(",", "[", "]")));
		System.out.println("----------7----------");

		/*****************************************************
		 * Convert List<students> to String
		 *****************************************************/
		String name = students.stream().map(Student::getName).collect(Collectors.joining(",", "[", "]"));
		System.out.println(name);
		System.out.println("---------8-----------");

		/*****************************************************
		 * Change the case of List<String>
		 *****************************************************/
		List<String> nameList = Arrays.asList("Jayesh", "Dany", "Khyati", "Hello", "Mango");

		nameList.stream().map(String::toUpperCase).forEach(System.out::println);
		System.out.println("----------9----------");

		/*****************************************************
		 * Sort List<String>
		 *****************************************************/
		List<String> namesList = Arrays.asList("Jayesh", "Dany", "Khyati", "Hello", "Mango");

		namesList.stream().sorted().forEach(System.out::println);
		System.out.println("----------10----------");

		/*****************************************************
		 * Conditionally apply Filter condition, say if flag is enabled then
		 *****************************************************/
		boolean sortConditionFlag = true;

		Stream<Student> conditionalFilterResult = students.stream().filter(std -> std.getName().startsWith("J"));

		if (sortConditionFlag) {
			conditionalFilterResult = conditionalFilterResult.sorted(Comparator.comparing(Student::getName));
		}

		System.out.println("Before sorting :");
		students.forEach(s -> System.out.println(s.getName()));

		List<Student> list = conditionalFilterResult.collect(Collectors.toList());
		System.out.println("After filter and conditional sorting :");
		list.forEach(s -> System.out.println(s.getName()));
		
		StringJoiner str1 = new StringJoiner(",");
		str1.add("A").add("B").add("C");
		System.out.println(str1); //A,B,C
		StringJoiner str2 = new StringJoiner(":");
		str2.add("P").add("Q");
		System.out.println(str2); //P:Q
		str1.merge(str2);
		System.out.println(str1); //A,B,C,P:Q
		StringJoiner str3 = new StringJoiner(",","[","]");
		str3.add("A").add("B").add("C");
		System.out.println(str3); //[A,B,C]
		
		int array1[] = {1,3,5,7,9};
		int array2[] = {2,4,5,7,9};
		
		Set<Integer> s = new HashSet<>();
		//Common element between two array, intersection
		for(int i=0;i<array1.length;i++) {
			s.add(array1[i]);
		}
		for(int i=0;i<array2.length;i++) {
			if(s.contains(array2[i])) {
				System.out.print(array2[i]+","); //5,7,9,
			}
		}
		
		//Union of two array
		Set<Integer> s2 = new HashSet<>();
		for(int i=0;i<array1.length;i++) {
			s2.add(array1[i]);
		}
		for(int i=0;i<array2.length;i++) {
			s2.add(array2[i]);
		}
		System.out.println(s2);//[1, 2, 3, 4, 5, 7, 9] Set contains only unique elements, duplicate elements will be removed
		

	}
}