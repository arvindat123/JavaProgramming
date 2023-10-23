package org.Java8;

import java.util.*;

public class LambdaExpressionsTest {
	
		public void sumOfOddNumbers_Usual() { // changes to state
			List<Integer> numbers = Arrays.asList(1,3,4,6,2,7);
			int sum = 0;
			for(int i : numbers) {
				if(i%2 != 0) {
					sum += i;
				}
			}
			System.out.println(sum);
		}
		
		public void sumOfOddNumbers_FunctionalProgrammingExample() { //No changes to state so helpful in multithreading 
			List<Integer> numbers = Arrays.asList(1,3,4,6,2,7);
			int sum = 0;
			for(int i : numbers) {
				if(i%2 != 0) {
					sum += i;
				}
			}
			System.out.println(sum);
		}
		
		public static void main(String[] args) {
			LambdaExpressionsTest obj = new LambdaExpressionsTest();
			obj.sumOfOddNumbers_Usual();
		}
}
