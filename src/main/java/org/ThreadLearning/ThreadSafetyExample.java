package org.ThreadLearning;

import java.util.ArrayList;
import java.util.List;

public class ThreadSafetyExample implements Runnable{	
	//shared by all the threads
	private List<Integer> list = new ArrayList<>();
	
	public ThreadSafetyExample() {
		for(int i = 0;i < 2; i++) {
			list.add(i);
			System.out.println("Hi "+i);
		}
	}
	
	public void run() {
		String tName = Thread.currentThread().getName(); 
		while(!list.isEmpty()) {
			System.out.println(tName + " is removed "+ list.remove(0));
		}
	}
	
	public static void main(String[] args) {
		ThreadSafetyExample alr = new ThreadSafetyExample();
		Thread t1 = new Thread(alr);
		Thread t2 = new Thread(alr);
		t1.start();
		t2.start();
	}
}
