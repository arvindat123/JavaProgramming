package org.ThreadLearning;

public class WaitNotifySleepExample {
    public static void main(String[] args) {
        final Object object = new Object();
        Thread thread = new Thread(() ->  {
            synchronized (object){
                System.out.println("thread 1 waiting");
                try {
                    object.wait();
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println("threat 1 resumed");
            }
        });
        Thread thread2 = new Thread(() -> {
            synchronized (object){
                System.out.println("thread 2 notifying");
                object.notify();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("thread 2 notified thread 1");
            }
        });
        thread.start();
        thread2.start();
    }
}
