package org.ThreadLearning;

class CounterDemo2 {
    private int count = 0;

    // Synchronized method
    public synchronized void increment() {
        count++; // Critical section
    }

    public int getCount() {
        return count;
    }
}

public class SynchronizedMethodDemo {
    public static void main(String[] args) throws InterruptedException {
        CounterDemo2 counter = new CounterDemo2();

        // Create two threads that try to increment the counter
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        });

        t1.start();
        t2.start();

        // Wait for both threads to finish
        t1.join();
        t2.join();

        // Output the counter value
        System.out.println("Final Count: " + counter.getCount());
    }
}
