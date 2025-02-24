package org.ThreadLearning;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TimeOutThread {
    public static void main(String[] args) throws InterruptedException {
        //There is no method to stop a thread in Thread class
        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        //Start the task
        threadPool.submit(() -> System.out.println("Task has been submitted and completed by " + Thread.currentThread().getName()));
        Thread.sleep(1000);
        if (!threadPool.isShutdown()) {
            System.out.println("Shutting down threadPool");
            //Stop the thread
            //No new Tasks accepted, Previously submitted tasks(waiting or in progress) are executed
            threadPool.shutdown();
            System.out.println("Shut down threadPool");
        }
        //No new task accepted, returned previously submitted tasks waiting in the queue,
        // tasks being run by thread(s) are attempted to stop(but not guarantees)
        threadPool.shutdownNow();
    }
}
