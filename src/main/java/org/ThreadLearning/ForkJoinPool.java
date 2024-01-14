package org.ThreadLearning;

import java.util.concurrent.RecursiveTask;

public class ForkJoinPool extends RecursiveTask<Integer> { //Must implement method compute()
    /**
     * It is enhanced ExecutorService
     * Two concepts:
     * 1) Per-Thread Queuing
     * 2) Work-stealing
     * It divide problem in sub-task and complete sub-task and join to produce final result
     *=======================Advantages of queue per thread =============================
     * Each thread will have its own Deque where all its sub-task will be stored
     * Just picking tasks from own queue
     * No blocking(unless during stealing) : because thread has its own queue
     * Easier scheduling
     *
     * ForkJoinTask class
     * (Submit tasks internally)
     *
     * ===========Idea ForkJoinTasks============
     * Avoid Synchronization
     * Do not use shared variables across tasks
     * Do not perform Blocking IO Operations
     * Are pure functions
     * Are isolated
     *
     * ============Use case===================
     * Sorting
     * Matrix Multiplication
     * Best Move Finder for a game
     * Tree Traversal
     * **/

    final int n;
    ForkJoinPool(int n) {
        this.n = n;
    }

    public Integer compute(){
        if(n <= 1){
            return n;
        }
        ForkJoinPool f1 = new ForkJoinPool(n-1);
        f1.fork();

        ForkJoinPool f2 = new ForkJoinPool(n-2);
        f2.fork();

        return f1.join()+f2.join();
    }

    public static void main(String[] args) {
        ForkJoinPool obj = new ForkJoinPool(4);
        System.out.println(obj.compute());
    }

}
