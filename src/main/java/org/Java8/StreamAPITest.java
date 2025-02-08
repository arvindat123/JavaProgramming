package org.Java8;

import java.util.*;
import java.util.stream.Collectors;

public class StreamAPITest {
    public static void main(String[] args) {
        List<EmployeeObj> employeeList = new ArrayList<EmployeeObj>();

        //(int id, String name, int age, String gender, String department, int yearOfJoining, double salary)
        employeeList.add(new EmployeeObj(122, "Paul Niksui", 25, "Male", "Sales And Marketing", 2015, 13500.0));
        employeeList.add(new EmployeeObj(111, "Jiya Brein", 32, "Female", "HR", 2011, 25000.0));
        employeeList.add(new EmployeeObj(133, "Martin Theron", 29, "Male", "Infrastructure", 2012, 18000.0));
        employeeList.add(new EmployeeObj(144, "Murali Gowda", 28, "Male", "Product Development", 2014, 32500.0));
        employeeList.add(new EmployeeObj(155, "Nima Roy", 43, "Female", "HR", 2013, 22700.0));
        employeeList.add(new EmployeeObj(166, "Iqbal Hussain", 43, "Male", "Security And Transport", 2016, 10500.0));
        employeeList.add(new EmployeeObj(177, "Manu Sharma", 35, "Male", "Account And Finance", 2010, 27000.0));
        employeeList.add(new EmployeeObj(188, "Wang Liu", 31, "Male", "Product Development", 2015, 34500.0));
        employeeList.add(new EmployeeObj(199, "Amelia Zoe", 24, "Female", "Sales And Marketing", 2016, 11500.0));
        employeeList.add(new EmployeeObj(200, "Jaden Dough", 38, "Male", "Security And Transport", 2015, 11000.5));
        employeeList.add(new EmployeeObj(211, "Jasna Kaur", 27, "Female", "Infrastructure", 2014, 15700.0));
        employeeList.add(new EmployeeObj(222, "Nitin Joshi", 25, "Male", "Product Development", 2016, 28200.0));
        employeeList.add(new EmployeeObj(233, "Jyothi Reddy", 27, "Female", "Account And Finance", 2013, 21300.0));
        employeeList.add(new EmployeeObj(244, "Nicolus Den", 24, "Male", "Sales And Marketing", 2017, 10700.5));
        employeeList.add(new EmployeeObj(255, "Ali Baig", 23, "Male", "Infrastructure", 2018, 12700.0));
        employeeList.add(new EmployeeObj(266, "Sanvi Pandey", 26, "Female", "Product Development", 2015, 28900.0));
        employeeList.add(new EmployeeObj(277, "Anuj Chettiar", 31, "Male", "Product Development", 2012, 35700.0));

        // Double sorting
        //employeeList.stream().sorted(Comparator.comparing(EmployeeObj::getAge).reversed().thenComparing(EmployeeObj::getDepartment)).collect(Collectors.toList()).forEach(System.out::println);

        List<EmployeeObj> list = employeeList.stream().sorted(Comparator.comparing(EmployeeObj::getAge).reversed().thenComparing(EmployeeObj::getDepartment)).collect(Collectors.toList());
        //System.out.println(list);

        //Find out the count of male and female employees present in the organization?

        Map<String, Long> map = employeeList.stream().collect(Collectors.groupingBy(EmployeeObj::getGender, Collectors.counting()));
        //System.out.println(map);

        //Write a program to print the names of all departments in the organization.
        List<String> nameOfDepartment = employeeList.stream().map(obj -> obj.getDepartment()).distinct().collect(Collectors.toList());
        // System.out.println(nameOfDepartment);

        //Find the average age of Male and Female Employees.
        Map<String, Double> avgAge = employeeList.stream().collect(Collectors.groupingBy(obj -> obj.gender, Collectors.averagingDouble(EmployeeObj::getAge)));
        // System.out.println(avgAge);

        //Get the Names of employees who joined after 2015.
        String nameOfEmpAfter2015 = employeeList.stream().filter(obj -> obj.yearOfJoining > 2015).map(EmployeeObj::getName).collect(Collectors.joining(",", "[", "]"));
        //System.out.println(nameOfEmpAfter2015);

        //Count the number of employees in each department.
        Map<String, Long> noOfEmpInDept = employeeList.stream().collect(Collectors.groupingBy(EmployeeObj::getDepartment, Collectors.counting()));
        Set<Map.Entry<String, Long>> set = noOfEmpInDept.entrySet();
        for (Map.Entry<String, Long> mapEmp : set) {
            //System.out.println(mapEmp.getKey()+":"+mapEmp.getValue());
        }
        //Find out the average salary of each department.

        Map<String, Double> avgSalDept = employeeList.stream().collect(Collectors.groupingBy(EmployeeObj::getDepartment, Collectors.averagingDouble(EmployeeObj::getSalary)));
        Set<Map.Entry<String, Double>> avgSal = avgSalDept.entrySet();
        for (Map.Entry<String, Double> mapEmp : avgSal) {
            // System.out.println(mapEmp.getKey()+":"+Math.round(mapEmp.getValue()));
        }
        //Find out the oldest employee, his/her age and department?
        //Optional<EmployeeObj> oldestEmp = employeeList.stream().sorted((a,b) -> b.age - a.age).findFirst();
        Optional<EmployeeObj> oldestEmp = employeeList.stream().min(Comparator.comparingInt(EmployeeObj::getAge)); // alternate
        // Optional<EmployeeObj> oldestEmp = employeeList.stream().min(Comparator.comparingInt(EmployeeObj::getYearOfJoining));
        // Optional<EmployeeObj> oldestEmp = employeeList.stream().sorted(Comparator.comparingInt(EmployeeObj::getYearOfJoining)).findFirst();
        if (oldestEmp.isPresent()) {
            //System.out.println(oldestEmp.get().name+"-> "+oldestEmp.get().age+"->"+oldestEmp.get().department);
        }
/*
		 //Find out the average and total salary of the organization.
		 DoubleSummaryStatistics empSalary = employeeList.stream().collect(Collectors.summarizingDouble(EmployeeObj::getSalary));
		 System.out.println(empSalary.getMax()+","+empSalary.getAverage()+","+empSalary.getSum()+","+empSalary.getCount()+","+empSalary.getMin());

		 //List down the employees of each department.
		 Map<String, List<EmployeeObj>> empDept = employeeList.stream().collect(Collectors.groupingBy(EmployeeObj::getDepartment));
		 Set<Map.Entry<String,List<EmployeeObj>>> setMap = empDept.entrySet();
		 for(Map.Entry<String, List<EmployeeObj>> mapped : setMap){
			 System.out.println("Dept================================= : "+mapped.getKey());
			 for(EmployeeObj ob : mapped.getValue()){
				 System.out.println(ob.name);
			 }
		 }
		*/

        //Find out the highest experienced employees in the organization


        Map<String, Integer> myMap = new HashMap<>();
        myMap.put("Boi", 6);
        myMap.put("Zooey", 3);
        myMap.put("CHaris", 8);

        System.out.println("Number of items in the map with the value : 4 => " + myMap.entrySet().stream().filter(d -> d.getValue() > 4).count());

        Map<String, Integer> mpp = new HashMap<>();
        mpp.put("Boi", 6);
        mpp.put("Zooey", 3);
        mpp.put("CHaris", 8);
        //IntStream.iterate(0,i->i+1).forEach(System.out::println);


        mpp.entrySet().stream().filter(m -> m.getValue() > 4).count();
		 /*try
		 {
			 Stream<String> stream = Files.lines(Paths.get("dvdinfo.txt"));
		 }
		 catch (IOException e)
		 {
			 e.printStackTrace();
		 }*/
		/*List<String> coffees = List.of("Cappuccino",
			"Americano", "Espresso","Cortado", "Mocha",
			"Cappuccino", "Flat White", "Latte");



	//List<String> coffeesEndingInO = coffees.stream().distinct().sorted().filter(s -> s.endsWith("o")).collect(Collectors.toList());
	List<String> coffeesEndingInO = coffees.stream().distinct().sorted()
													.filter(s -> s.endsWith("o"))
													.collect(Collectors.toUnmodifiableList());
	System.out.println(coffeesEndingInO);
	coffeesEndingInO.add("arvind");
	*/

        //Comparator<String> comparator = (s1, s2) -> s1.compareTo(s2);

        //Runnable runnable = () -> System.out.println("Hello");

        //Consumer<String> consumer = str -> System.out.println("Hello");
        /*
         * Integer[] myNums = {1,2,3}; Stream<Integer> myStream = Arrays.stream(myNums);
         *
         *
         * List<Double> tempsInPhoenix =
         * Arrays.asList(123.6,118.0,113.0,112.5,115.8,117.0,110.2,110.1,106.0,106.4);
         * System.out.println("Number of days over 110 in 10 day period " +
         * tempsInPhoenix.stream().filter(i -> i > 110).count());
         */

        /*Map<Boolean, List<Integer>> collect = Stream.of(1, 2, 3, 4, 5, 6).collect(Collectors.partitioningBy(n -> n % 2 == 0));
        System.out.println(collect);*/

        /*double a = Stream.of(1,2,3).mapToInt(b->b.intValue()).average().orElse(1);
        System.out.println(a);*/

       /* boolean allEven = Stream.of(2,4,6,8).allMatch(a -> a % 2 == 0);
        System.out.println(allEven);*/

        /*Stream.of("Alice", "Bob", "Charlie").sorted(Comparator.comparingInt(String::length).reversed()).forEach(System.out::println);*/

        /*Stream.of("Alice", "Bob", "Charlie").map(a -> a.toUpperCase()).forEach(System.out::println);*/

        /*int sum = Stream.of(1,2,3).mapToInt(a ->a.intValue()).sum();
        System.out.println(sum);*/

        /* Stream.of("Alice", "Bob", "Charlie").map(a -> a.length()).forEach(System.out::println);*/

        /*List<List<String>> listOfList = Arrays.asList(Arrays.asList("arvind","arid"), Arrays.asList("aridy","arrid"));
        List<String> collect = listOfList.stream().flatMap(List::stream).collect(Collectors.toList());*/


        /*Optional<Integer> a = Stream.of(1,3,4,5,6,9,4,3).sorted(Comparator.reverseOrder()).skip(1).findFirst();
        System.out.println(a.isPresent() ? a.get() : "there is no second highest");*/

        //"arvind".chars().mapToObj(c -> (char)c).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
    }
}

class EmployeeObj {

    int id;
    String name;
    int age;
    String gender;
    String department;
    int yearOfJoining;
    double salary;

    public EmployeeObj(int id, String name, int age, String gender, String department, int yearOfJoining, double salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.department = department;
        this.yearOfJoining = yearOfJoining;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getDepartment() {
        return department;
    }

    public int getYearOfJoining() {
        return yearOfJoining;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Id : " + id + ", Name : " + name + ", age : " + age + ", Gender : " + gender + ", Department : " + department + ", Year Of Joining : " + yearOfJoining + ", Salary : " + salary;
    }


}

