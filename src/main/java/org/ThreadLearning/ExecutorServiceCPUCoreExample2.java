package org.ThreadLearning;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceCPUCoreExample2 {
	public static void main(String[] args) {
		//get the available cores
		int coreCount = Runtime.getRuntime().availableProcessors();
		System.out.println("Number of core in CPU = "+ coreCount);
		
		ExecutorService service = Executors.newFixedThreadPool(coreCount);
		
		//submit the tasks for execution
		for(int i=0;i<100;i++) {
			service.execute(new CpuIntensiveTask());
		}
	}
}
	
	 class CpuIntensiveTask implements Runnable {
		public void run() {
			System.out.println(" Thread name : "+ Thread.currentThread().getName());
		}
	}
