package org.ThreadLearning;

public class RunnableInterfaceDemo implements Runnable {

    @Override
    public void run(){
        System.out.println("Runnable interface demo for thread ==> "+ Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        RunnableInterfaceDemo runnableInterfaceDemo = new RunnableInterfaceDemo();
        Thread thread = new Thread(runnableInterfaceDemo);
        thread.run();// it doesn't invoke thread
        thread.start(); // it invoke thread
        /**
         * OUTPUT
         * Runnable interface demo for thread ==> main
         * Runnable interface demo for thread ==> Thread-0
         */
    }

}
