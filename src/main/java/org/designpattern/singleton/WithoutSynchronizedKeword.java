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
				System.out.println(a.hashCode());
				//System.out.println(123);
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

		Thread t3 = new Thread(() -> {
			for (int i = 0; i<5;i++){
				System.out.println("Value of i==>"+i);
				Thread.yield();
			}
		});
		t3.start();
		try {

			t3.join();


		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
		Thread t4 = new Thread( () -> {
			for (int i = 0; i <100; i++) {
				System.out.println("Thread t4 is executing");
			}
		});

		Thread t5 = new Thread( () -> {
			for (int i = 0; i <100; i++) {
				System.out.println("Thread t5 is executing");
			}
		});


		try {

			t5.start();
			t5.join();
			t4.start();
			t4.join();


		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}

	}
}
