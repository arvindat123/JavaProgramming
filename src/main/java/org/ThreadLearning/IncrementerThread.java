package org.ThreadLearning;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class IncrementerThread extends Thread {
	
	private Counter counter;
	
	public IncrementerThread(Counter counter) {
		this.counter =counter;
	}
	
	public void run() {
		for(int i = 0; i < 1000000000; i++) {
			this.counter.increment();
		}
	}
	
	public static void main(String[] args) {
		Counter counter = new Counter();
		IncrementerThread it1 = new IncrementerThread(counter);
		IncrementerThread it2 = new IncrementerThread(counter);
		it1.start();
		it2.start();
		try {
			it1.join();
			it2.join();
			System.out.println(counter.getValue());
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
		Object obj = new Object();
		synchronized(obj) {
			
		}
		
		Lock lock = new ReentrantLock();
		lock.lock();
		try {
			//do work here
		}
		finally {
			lock.unlock();//to ensure we manually unlock 
		}
		
		
	}
}
