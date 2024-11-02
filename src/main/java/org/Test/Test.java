package org.Test;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Test {
    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);
        //Producer
        final Runnable producer = () -> {
            Random random = new Random();
            while(true){
                int produced = random.nextInt(100);
                try {
                    Thread.sleep(2000);
                    queue.put(produced);
                    System.out.println("produced ="+produced);
                } catch (InterruptedException e){
                    System.out.println(e.getMessage());
                }

            }
        };
        new Thread(producer).start();
        new Thread(producer).start();

        //consumer
        final Runnable consumer = () ->{
          while(true){
              try {
                  Thread.sleep(2000);
                  Integer in = queue.take();
                  System.out.println("consumed"+in);
              } catch (InterruptedException e) {
                  System.out.println("exception "+e.getMessage());
              }
          }
        };
        new Thread(consumer).start();
        new Thread(consumer).start();
    }
}
