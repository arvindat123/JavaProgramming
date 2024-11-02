package org.ThreadLearning;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class ExecutorServiceExample1 {
	//create the pool
	public static void main(String[] args) {
		ExecutorService service = Executors.newFixedThreadPool(10);
		ExecutorService executor = Executors.newCachedThreadPool();//Creates a thread pool that creates new threads as needed
		ExecutorService executor4 = Executors.newWorkStealingPool();
		ExecutorService executor2 = Executors.newSingleThreadExecutor();
		ScheduledExecutorService executor3 = Executors.newScheduledThreadPool(3);


		
		//submit/Execute tasks for execution
			for(int i=0; i<100; i++) {
				service.execute(new Task(i)); // Doesn't return anything
				//service.submit(new Task()); return Future
			}
			System.out.println("Thread name: "+ Thread.currentThread().getName());
			
			 // Shutdown the executor service when no more tasks need to be submitted
			service.shutdown();
	}
	
}
		
	 class Task implements Runnable {
		 private int taskId;

		    public Task(int taskId) {
		        this.taskId = taskId;
		    }
		 
			public void run() {
				System.out.println("Task ID: " + taskId + " executed by thread: " + Thread.currentThread().getName());
			}
		}



