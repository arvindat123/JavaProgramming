package org.Java8;

import java.util.Random;
import java.util.function.IntSupplier;

public class FunctionalInterfaceTesting {
	public static void main(String[] args) {
		
		Random random = new Random();
		
		IntSupplier randomIntSupplier = () -> random.nextInt();
		
		int asInt = randomIntSupplier.getAsInt();
		System.out.println("Random number" + asInt);
		
		
		/*
		 * //Lambda Expression of Functional Interface Supplier<Integer> answerSupplier
		 * = () -> 40;
		 * 
		 * System.out.println("Supplier Functional Testing = "+ answerSupplier.get());
		 * 
		 * //Inner class implementation of Functional Interface Supplier<Integer>
		 * answerSupplierInnerClass = new Supplier<Integer>() {
		 * 
		 * @Override public Integer get() { return 42; } };
		 * System.out.println("Inner class implementation = "+
		 * answerSupplierInnerClass.get());
		 * 
		 * //Lambda expression with more detail Supplier<String> userSupplier = () -> {
		 * Map<String, String> env = System.getenv(); //System.out.println(env); return
		 * env.get("USERNAME"); }; System.out.println("Environment Details supplier = "
		 * + userSupplier.get());
		 */
	}
}
