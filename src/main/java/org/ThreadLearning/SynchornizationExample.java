package org.ThreadLearning;

public class SynchornizationExample {
	public static void main(String[] args) {
		SynchronizedExchanger exchanger = new SynchronizedExchanger();
		
		Thread thread1 = new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i=0; i<100; i++) {
					exchanger.setObject(""+ i);
				}
			}
		});
		
		Thread thread2 = new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i=0; i<100; i++) {
					System.out.println(exchanger.getObject());
				}
			}
		});
		
		thread1.start();
		thread2.start();
	}
}
