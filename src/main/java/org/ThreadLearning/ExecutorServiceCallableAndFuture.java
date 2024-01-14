package org.ThreadLearning;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorServiceCallableAndFuture {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(10);
        //Submit task for execution
        List<Future> allFutures = new ArrayList<>();
        for (int i = 0; i <100; i++) {
            Future<Integer> future = service.submit(new Task());
            allFutures.add(future);
        }
        //100 futures, with 100 placeholders

        //perform some unrelated operations

        //100 seconds
        for (int i = 0; i < 100; i++) {
            Future<Integer> future = allFutures.get(i);
            System.out.println("Result of future #"+i+"="+future);
        }

        /**
         * Other method on future
         * //cancel the task
         * future.cancel(false);
         *
         * //return true if task was cancelled
         * future.isCancelled();
         *
         * //returns true if task is completed (successfully or otherwise)
         * future.isDone();
         *
         * **/
    }
    static class Task implements Callable<Integer> {//Return type of callable is Integer
        @Override
        public Integer call() throws Exception {
            Thread.sleep(3000);
            return new Random().nextInt();
        }
    }
}


