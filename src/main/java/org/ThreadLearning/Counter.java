package org.ThreadLearning;

import java.util.concurrent.atomic.AtomicInteger;

public class Counter {
	
	//private int count;
	
	private AtomicInteger count = new AtomicInteger();
	
	public void increment() {
		count.getAndIncrement();
	}

	public int getValue() {
		return count.intValue();
		
	}
	
	
}
