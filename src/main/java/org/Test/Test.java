package org.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.Arrays;

public class Test {

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(2);
        es.submit(() -> System.out.println("This is task"));
        if(!es.isShutdown()){
            System.out.println("Shutting down");
            es.shutdown();
            System.out.println("Shutdown");
        }
    }
}



