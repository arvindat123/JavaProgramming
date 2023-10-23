package org.ThreadLearning;
import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample {
    public static void main(String[] args) {
        // Create a CountDownLatch with a count of 3
        CountDownLatch latch = new CountDownLatch(3);

        // Create three worker threads
        Worker worker1 = new Worker("Worker 1", latch);
        Worker worker2 = new Worker("Worker 2", latch);
        Worker worker3 = new Worker("Worker 3", latch);

        // Start the worker threads
        new Thread(worker1).start();
        new Thread(worker2).start();
        new Thread(worker3).start();

        try {
            // Main thread waits for all worker threads to finish
            latch.await();
            System.out.println("All workers have completed their tasks.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Worker implements Runnable {
    private String name;
    private CountDownLatch latch;

    public Worker(String name, CountDownLatch latch) {
        this.name = name;
        this.latch = latch;
    }

    @Override
    public void run() {
        // Simulate some work
        System.out.println(name + " is working...");
        try {
            Thread.sleep(2000); // Simulate work by sleeping for 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Worker has completed its task, count down the latch
        System.out.println(name + " has completed its task.");
        latch.countDown();
    }
}
