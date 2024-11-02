package org.ThreadLearning;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class SharedResource {
    private int counter = 0;
    private final Lock lock = new ReentrantLock();  // Create a ReentrantLock

    public void increment() {
        lock.lock();  // Acquire the lock
        try {
            counter++;  // Critical section
            System.out.println(Thread.currentThread().getName() + " incremented counter to: " + counter);
        } finally {
            lock.unlock();  // Release the lock in the finally block to ensure it is always released
        }
    }

    public int getCounter() {
        return counter;
    }
}

public class LockDemo {
    public static void main(String[] args) throws InterruptedException {
        SharedResource sharedResource = new SharedResource();

        // Create two threads that access the shared resource
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                sharedResource.increment();
            }
        }, "Thread-1");

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                sharedResource.increment();
            }
        }, "Thread-2");

        // Start both threads
        t1.start();
        t2.start();

        // Wait for both threads to finish
        t1.join();
        t2.join();

        // Output the final counter value
        System.out.println("Final Counter: " + sharedResource.getCounter());
    }
}
