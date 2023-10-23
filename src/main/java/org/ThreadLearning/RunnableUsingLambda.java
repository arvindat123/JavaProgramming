package org.ThreadLearning;

public class RunnableUsingLambda {
	public static void main(String[] args) {
		Runnable myThread = () -> {
			Thread.currentThread().setName("My Thread");
			System.out.println("Name of running thread => " + Thread.currentThread().getName() + ", "
					+ Thread.currentThread().getPriority() + ", " + Thread.currentThread().getId() + ", "
					+ Thread.currentThread().getClass().getName());
		};

		Thread t = new Thread(myThread);
		t.start();
		
		Runnable r = () -> {
			Thread.currentThread().setName("Arvind Thread");
			System.out.println(Thread.currentThread().getName());
			
		};
		
		new Thread(r).start();
	}
	
	
	
}
