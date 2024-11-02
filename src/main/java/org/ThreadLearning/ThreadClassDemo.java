package org.ThreadLearning;

public class ThreadClassDemo extends Thread{

    @Override //If this method is not overriden and thread is starting then it will not print anything because there is
    //no task
    public void run(){
        System.out.println("Thread started running===>"+Thread.currentThread().getName());
    }
    public static void main(String[] args) {
        ThreadClassDemo threadClassDemo = new ThreadClassDemo();
        Thread t1 = new Thread(threadClassDemo);
        t1.run(); // This way it is not running thread but main method
        t1.start(); // This start a thread execution

        /**
         * OUTPUT
         * Thread started running===>main
         * Thread started running===>Thread-1
         */
    }
}
