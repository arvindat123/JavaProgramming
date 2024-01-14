package org.General;

public class Factorial {

	//head recursion, it needs stack
	//1, 1, 2, 3, 5, 8, 13, 21
	public static int headRecursionFactorial(int n) {
		if(n==0) {
			return 1;
		}
		return n*headRecursionFactorial(n-1);
	}
	
	//tail recursion, this doesn't need stack. Result is called accumulator which stores calculation
	public static int tailRecursionFactorial(int n, int result) {
		if(n==0) return result;
		return tailRecursionFactorial(n-1, n*result);
	}
	
	
	public static void main(String[] args) {
		System.out.println("head recursion example"+headRecursionFactorial(3));
		System.out.println("tail recursion example"+tailRecursionFactorial(3,1));
	}
}
