package org.ThreadLearning;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierVSCountDownLatchExample {

    public static void main(String[] args) throws InterruptedException {
        // CyclicBarrier example
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, () -> System.out.println("CyclicBarrier action"));
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                try {
                    System.out.println("Thread waiting at barrier");
                    cyclicBarrier.await();
                    System.out.println("Thread passed barrier");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }

        Thread.sleep(1000); // Sleep to separate examples

        // CountDownLatch example
        CountDownLatch countDownLatch = new CountDownLatch(3);
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                try {
                    System.out.println("Thread performing task");
                    Thread.sleep(1000);
                    countDownLatch.countDown();
                    System.out.println("Task completed");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }

        countDownLatch.await();
        System.out.println("All tasks completed");
    }
}
