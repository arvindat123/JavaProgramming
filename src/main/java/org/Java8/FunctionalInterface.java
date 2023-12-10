package org.Java8;

import java.util.Comparator;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.*;

public class FunctionalInterface {
    public static void main(String[] args) {
//using the test method of Predicate
	/*	public interface Predicate<T> {
			boolean test(T t);
		}*/

        Predicate<String> stringLen = (s) -> s.length() < 10;
        System.out.println(stringLen.test("Apples") + " - Apples is less than 10");


//Consumer example uses accept method
		/*public interface Consumer<T> {
			void accept(T t);
		}*/
        Consumer<String> consumerStr = (s) -> System.out.println(s.toLowerCase());
        consumerStr.accept("ABCDefghijklmnopQRSTuvWxyZ");


//Supplier example
		/*public interface Supplier<T> {
			T get();
		}*/
		Supplier<String> s = () -> "Java is fun";
		System.out.println(s.get());

//Function example
		/*public interface Function<T, R> {
			R apply(T t);
		}*/
        Function<Integer, String> converter = (num) -> Integer.toString(num);
        System.out.println("length of 26: " + converter.apply(26).length());




//Binary Operator example

        BinaryOperator<Integer> add = (a, b) -> a + b;
        System.out.println("add 10 + 25: " + add.apply(10, 25));


//Unary Operator example
        UnaryOperator<String> str = (msg) -> msg.toUpperCase();
        System.out.println(str.apply("This is my message in upper case"));


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

		//Passing functionality as argument
		ExecutorService threadPool = Executors.newFixedThreadPool(5);

		//Functionality to execute
		Runnable calculateTax = new Runnable() { //anonymous class
			@Override
			public void run() {
			//code to calculate tax
			}
		};

		threadPool.submit(calculateTax); // passing functionality as argument
		/*
		submit method accept runnable or callable interfaces
		Functional interfaces are interfaces which represent some functionality(instead of representing data)
		Runnable interface represents functionality..thus runnable is a functional interface
		Callable(V call() throws Exception) and Comparator(int compareTo(T o1, T o2)) are also functional interfaces
		Functional interfaces have single abstract method
		Functional interface help enable Lambdas in Java 8
		*/
		Comparator<User> comparator = new Comparator<User>() { //Anonymous inner class
			@Override
			public int compare(User o1, User o2) {
				return o1.getName().compareTo(o2.getName());
			}
		};

		//Shorthand for above code also knowns as - lambda
		//Lambda are shorthand for functional interfaces
		Comparator<User> comparator1 = (u1, u2) -> u1.getName().compareTo(u2.getName());

    }


}

class User {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
