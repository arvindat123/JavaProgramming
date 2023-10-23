package org.ThreadLearning;

public class ManyNames {

	public static void main(String[] args) {
		Runnable nr = () -> {
			for(int x = 1; x <=4; x++) {
				System.out.println("Run by " + Thread.currentThread().getName() + ", x is "+ x);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		};
		
		Thread one = new Thread(nr);
		Thread two = new Thread(nr);
		Thread three = new Thread(nr);
		
		one.setName("Fred");
		two.setName("Lucy");
		three.setName("Ricky");
		
		one.start();
		two.start();
		//System.out.println("After starting thread in between");
		three.start();
		//int i = 1, j=2;
		//int f = (i+j)/2;
		//System.out.println(f);
		//System.out.println("After starting thread");
	}

}
