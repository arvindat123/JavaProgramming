package org.Java21;

import java.util.concurrent.Executors;

public class VirtualThreads {
    static void main() {
        //Traditional Java threads are mapped to OS threads, each thread requires significant memory and os resources
        // like 10,000 users -> 10,000 threads, this becomes expensive
        Thread t = new Thread(() -> {
            System.out.println("This is process");
        });
        t.start();

        //Solution : Virtual threads are lightweight threads managed by JVM
        Thread.startVirtualThread(() -> {
            System.out.println("This is virtual thread");
        });

        try(var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for(int i=0;i<100;i++){
                int id = 1;
                executor.submit(() -> {
                    try{
                    Thread.sleep(1000);}
                    catch(InterruptedException e){
                        System.out.println("Interrupted");
                    }
                    System.out.println("Task = "+ id +" executed by "+ Thread.currentThread());
                    return null;
                });
            }
        }
    }

}
