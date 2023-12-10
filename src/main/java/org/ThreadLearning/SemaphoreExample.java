package org.ThreadLearning;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

class SemaphoreExample {
    /**Semaphore -> Restrict/Manage the use of limited resources **/
    public static Semaphore semaphore = new Semaphore(3);

    public static void main(String[] args) throws InterruptedException {

        ExecutorService service = Executors.newFixedThreadPool(50);
        IntStream.of(1000).forEach(i -> service.execute(new Task(semaphore)));


        //Initiates an orderly shutdown in which previously submitted tasks are executed,
        // but no new tasks will be accepted. Invocation has no additional effect if already shut down.
        //This method does not wait for previously submitted tasks to complete execution. Use awaitTermination to do that.
        service.shutdown();

        //Blocks until all tasks have completed execution after a shutdown request, or the timeout occurs,
        // or the current thread is interrupted, whichever happens first.
        //Params:
        //timeout – the maximum time to wait
        // unit – the time unit of the timeout argument
        service.awaitTermination(1, TimeUnit.MINUTES);
    }

    //Inner class can be declared as static but outer class can only be FAP : Final, Abstract and Public
    static class Task implements Runnable {

        public Task(Semaphore semaphores) {
            semaphore = semaphores;
        }

        @Override
        public void run() {
            System.out.println("Implementation run method inside Task class");
            //Some processing
            try {
                semaphore.acquire();  //if don't want to put in try block then call semaphore.acquireUninterruptibly(); 4th concurrent thread will be blocked here
                //IO call to the slow service
                semaphore.release(); //Release permit back, 4th thread will go here and acquire the lock and call the service
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            //rest of processing
            //This might be called 50 times concurrently
        }
    }

    /**
     * Other methods
     * tryAcquire : Try to acquire, if no permit available, do not block, continue doing something else
     * tryAcquire(timeout) : Same as above but with timeout
     * availablePermits : Returns count of permits available
     * new Semaphore(count,fairness) : FIFO, Fairness guarantee for threads waiting the longest
     *
     *
     *
     *
     *
     * **/
}


