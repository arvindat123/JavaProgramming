package org.ThreadLearning;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConsumerProducer {
    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10); //handle concurrent thread access

        //Producer
        final Runnable producer = () -> {
            Random random = new Random();
            while(true){
                int produced = random.nextInt(100);
                try {
                    Thread.sleep(2000);
                    queue.put(produced); //Thread blocks if queue is full
                    System.out.println("Produced=" + produced);
                } catch (InterruptedException e){
                    System.out.println(e.getMessage());
                }
            }
        };

        new Thread(producer).start(); //thread1
        new Thread(producer).start(); //thread2

        //Consumer
        final Runnable consumer = () -> {
           while(true){
               try {
                   Thread.sleep(2000);
                   Integer consumed = queue.take();//Thread blocks if queue is empty
                   System.out.println("Consumed=" + consumed);
               } catch (InterruptedException e){
                   System.out.println(e.getMessage());
               }
           }
        };

        new Thread(consumer).start();//thread1
        new Thread(consumer).start();//thread2

       //Thread.sleep(1000);
    }
}
