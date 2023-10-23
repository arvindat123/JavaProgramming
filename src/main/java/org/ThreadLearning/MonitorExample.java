package org.ThreadLearning;

public class MonitorExample {
	public static void main(String[] args) {
		SharedDataPrinter printer = new SharedDataPrinter();
		Thread2 t1 = new Thread2(printer);
		Thread2 t2 = new Thread2(printer);
		t1.start();
		t2.start();
		
	}
}
//In Java, monitors are implemented using synchronized keyword (synchronized blocks, synchronized methods or classes).
//Monitor in Java Concurrency is a synchronization mechanism that provides the fundamental requirements of multithreading namely mutual exclusion between various threads and cooperation among threads working at common tasks. Monitors basically ‘monitor’ the access control of shared resources and objects among threads.

class SharedDataPrinter {
	synchronized public void display(String str) {
		for(int i=0;i<str.length();i++) {
			System.out.println(str.charAt(i) + " Executing Thread Name = "+ Thread.currentThread().getName());
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				
			}
		}
	}
}

class Thread2 extends Thread {
	SharedDataPrinter p;
	public Thread2(SharedDataPrinter p) {
		this.p = p;
	}
	public void run() {
		p.display("LearningThread");
	}
}