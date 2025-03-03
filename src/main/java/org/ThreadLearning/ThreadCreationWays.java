package org.ThreadLearning;

//1 way by implementing Runnable interface
/*public class ThreadCreationWays implements Runnable{
    @Override
    public void run(){
        System.out.println("Way 1 for T1 : By implementing Runnable interface");
    }
    public static void main(String[] args) {
        ThreadCreationWays object = new ThreadCreationWays();
        Thread T1 = new Thread(object);
        T1.start();
    }
}*/

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//2 Way by extending Thread class, thread class implements Runnable interface and override run method
public class ThreadCreationWays extends Thread {
    @Override
    public void run(){
        System.out.println("Way 2 for T2 : By extending Thread class");
    }
    public static void main(String[] args) {
        ThreadCreationWays object = new ThreadCreationWays();
        Thread T2 = new Thread(object);
        T2.start();

        //Way 3
        Runnable r = () -> {
            System.out.println("Way 3 for T3 : By Lambda expression with Runnable interface");
        };
        Thread T3 = new Thread(r);
        T3.start();

        //Way 4
        Thread T4 = new Thread(() -> {
            System.out.println("Way 4 for T4 : By Lambda expression");
        });
        T4.start();

        //Way 5
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Way 5 for T5 : By Lambda expression with Runnable interface");
            }
        };
        Thread T5 = new Thread(runnable);
        T5.start();

        //Way 6
        Thread T6 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Way 6 for T6 : By Lambda expression with Runnable interface");
            }
        });
        T6.start();

        //Way 7
        ExecutorService es = Executors.newFixedThreadPool(2); //Threadpool of 2 threads is created
        es.submit(() -> System.out.println("This is task"));// single task is submitted to 2 threads
    }
}
