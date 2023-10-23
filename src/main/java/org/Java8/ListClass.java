package org.Java8;

import java.util.List;

public class ListClass {

	
	public static void main(String[] args) {
		List<String> allColors = List.of("red","green","black");
		for(String color : allColors) {
			System.out.println("Normal For "+ color);
		}
		
		allColors.forEach(color -> System.out.println("forEach " +color));
		

	}

}
