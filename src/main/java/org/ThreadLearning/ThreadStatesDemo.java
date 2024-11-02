package org.ThreadLearning;

class MyThread implements Runnable {
    @Override
    public void run() {
        try {
            // The thread will move to TIMED_WAITING state
            Thread.sleep(1000); // Sleep for 1 second
            System.out.println(Thread.currentThread().getName() + " is in TIMED_WAITING state");

            synchronized (this) {
                // The thread will move to WAITING state
                wait(); // Waiting indefinitely
                System.out.println(Thread.currentThread().getName() + " is in WAITING state");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class ThreadStatesDemo {
    public static void main(String[] args) throws InterruptedException {
        MyThread myRunnable = new MyThread();
        Thread thread = new Thread(myRunnable);

        // NEW state
        System.out.println("Thread state after creation: " + thread.getState());

        // Start the thread (RUNNABLE state)
        thread.start();
        System.out.println("Thread state after start: " + thread.getState());

        // Give time for the thread to move into TIMED_WAITING state
        Thread.sleep(500);
        System.out.println("Thread state during sleep: " + thread.getState());

        // Wait for the thread to enter WAITING state (wait is called inside run method)
        synchronized (myRunnable) {
            // Notify the thread to move it out of WAITING state
            Thread.sleep(1500); // Ensure thread has moved to WAITING state
            System.out.println("Thread state after moving to WAITING: " + thread.getState());
            myRunnable.notify();
        }

        // Wait for the thread to complete (TERMINATED state)
        thread.join();
        System.out.println("Thread state after termination: " + thread.getState());
    }
}