package org.ThreadLearning;

public class SynchronizedBlockDemo {

    public static void main(String[] args) throws InterruptedException {
        CounterDemo counter = new CounterDemo();
        // Create two threads that try to increment the counter
        Thread t1 = new Thread( () -> {
            for (int i = 0; i < 100; i++) {
                counter.increment();
                System.out.println(i +" value for thread "+ Thread.currentThread().getName());
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                counter.increment();
                System.out.println(i +" value for thread "+ Thread.currentThread().getName());
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        // Output the counter value
        System.out.println("Final Count: " + counter.getCount());

    }
}

class CounterDemo {
    private final Object lock = new Object(); // Lock object
    private int count = 0;

    public void increment() {
       synchronized (lock) { //Synchronizing on a specific object
            count++; // critical section
       }
    }

    public int getCount() {
        return count;
    }
}
