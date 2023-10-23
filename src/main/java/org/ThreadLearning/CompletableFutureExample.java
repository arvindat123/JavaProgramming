package org.ThreadLearning;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureExample {

	public static void main(String[] args) {

		// Example 1
		CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello").thenApply(s -> s + " World")
				.thenApply(String::toUpperCase);

		try {
			String result = future.get();
			System.out.println(result); // Prints "HELLO WORLD"
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

		// Example 2
		CompletableFuture<String> greetingFuture = CompletableFuture.supplyAsync(() -> {
			// some async computation
			return "Hello from CompletableFuture";
		});

		try {
			System.out.println(greetingFuture.get());
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Example 3
		CompletableFuture<String> helloFuture = CompletableFuture.supplyAsync(() -> "Hello");
		CompletableFuture<String> greetingFutureq = CompletableFuture.supplyAsync(() -> "World");

		CompletableFuture<String> combinedFuture = helloFuture.thenCombine(greetingFutureq, (m1, m2) -> m1 + " " + m2);

		try {
			System.out.println(combinedFuture.get());
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Example 4 exception handling
		CompletableFuture<Integer> resultFuture
		// java.lang.ArithmeticException: / by zero
				= CompletableFuture.supplyAsync(() -> 10 / 0).exceptionally(ex -> 9);

		// 0 - returned by exceptionally block
		try {
			System.out.println(resultFuture.get());
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
