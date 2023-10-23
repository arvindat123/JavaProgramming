package org.designpattern.singleton;
//Java Program to demonstrate Thread-Safety 

//in NestedInitialization 

class SingletonClassOne {

	private static class Nested {
		static SingletonClassOne Instance = new SingletonClassOne();
	}

	// This method returns Object, does not create it
	// Object is created on initialization of Nested class
	// which happens only once.
	public static SingletonClassOne getInstance() {
		return Nested.Instance;
	}

	private SingletonClassOne() {
		System.out.println("Object made");
	}
}

public class WithoutSynchronizedKeword {
	public static void main(String[] args) {

		// Thread 1 will call getInstance
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				SingletonClassOne a = SingletonClassOne.getInstance();
			}
		});

		// Thread 2 will also call getInstance
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				SingletonClassOne b = SingletonClassOne.getInstance();
			}
		});

		// Start both the Threads
		t1.start();
		t2.start();
	}
}
