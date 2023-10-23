package org.Leetcode;

public class ReverseInteger {
	
	public static int reverse(int integer) {
		long reverseInt = 0;
		int intermediate = 0;

		while(integer != 0) {
			intermediate = integer%10;
			reverseInt = reverseInt + intermediate;
			reverseInt = reverseInt*10;
			integer = integer / 10;
		}
		reverseInt = reverseInt/10;
		 if(reverseInt > Integer.MAX_VALUE || reverseInt<Integer.MIN_VALUE){
	            return 0;
	        }
		//System.out.println(reverseInt);
		if(integer < 0) {
			reverseInt = -1*reverseInt;
		}
		return (int)reverseInt;
	}
	
	
	public static void main(String[] args) {
		System.out.println(reverse(1534236469));
		//System.out.println(0-(-100));
	}
}
