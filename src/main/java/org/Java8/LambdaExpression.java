package org.Java8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LambdaExpression {

    public static void main(String[] args) {

        List<String> strings = List.of("I", "am", "a", "list", "of", "Strings");

        List<String> result = strings.stream().limit(4).collect(Collectors.toList());

        System.out.println(result);
        //System.out.println(strings);


        //Natural order of sorting
        List<String> resultSorted = strings.stream().sorted().limit(4).collect(Collectors.toList());
        System.out.println(resultSorted);

        //Customised sorting
        List<String> customSortedList = strings.stream().sorted((s1, s2) -> s1.compareToIgnoreCase(s2)).limit(4).collect(Collectors.toList());
        System.out.println(customSortedList);

        List<String> customSkipSortedList = strings.stream().sorted((s1, s2) -> s1.compareToIgnoreCase(s2)).skip(2).limit(4).collect(Collectors.toList());
        System.out.println(customSkipSortedList);

        Stream<String> limit = strings.stream().limit(4);
        List<String> resultt = limit.collect(Collectors.toList());
        //List<String> result2 = limit.collect(Collectors.toList()); //runtime error because terminal operation has been used and stream has been closed
				
		
		/*
		
		List<String> allColors = List.of("Red","Blue","Yellow"); //Convenience Factory method for creating new list
		
		for(String color : allColors) {
			System.out.println(color);
		}
		
		allColors.forEach(color -> System.out.println(color));
		
		List<Integer> nums = List.of(1,2,3,4,5);
		String output = "";
		
		for(int i = 1; i<nums.size();i++) {
			output += nums.get(i)+ " ";
		}
		System.out.println(output);
		output = "";
		for(int i = 1; i < nums.size();i++) {   // a += b => a = a+b, a=+b => a = (+)b
			output =+ nums.get(i)+ " ";  //https://stackoverflow.com/questions/6958401/the-difference-between-and
		}
		System.out.println(output);
		
		output = "";
		for(Integer num : nums) {
			output += num + " ";
		}
		System.out.println(output);
		
		output = "";
		for(Integer num : nums) {
			output += nums + " ";
		}
		System.out.println(output);
		
		/*
		 * output = ""; for(int i = 0; i <= nums.size(); i++) { // Exception for index
		 * out of bound output += nums.get(i) + " "; } System.out.println(output);
		 */


        //HashMap<int,int> mapp = new HashMap<>();
//		HashMap<StringBuilder, StringBuilder> map = new HashMap<>();
//		map.put(new StringBuilder("kumar"), new StringBuilder("arvind"));


        /*
         * int i = 9; int ii = 09;
         */
//		int[] number = {1,2,3};
//		int k = 3;
//		k %= number.length;
//		System.out.println(k);

        List<Integer> number = Arrays.asList(3, 6, 1, 7, 2, 9, 4, 11);
        int num = number.stream().max((s1, s2) -> s1 - s2).get();
        System.out.println(num);


    }
}


//public static void main(String[] args) {
//System.out.println("Hi in main");
//main(8);
//}
//static public void main(int m) {
//System.out.println("Hi in second main");
//}

//String initialization
//List<String> allColors = Arrays.asList("Arvind","Aarabh");
//for(String color : allColors) {
//	System.out.println(color);
//}