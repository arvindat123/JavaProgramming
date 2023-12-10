package org.ThreadLearning;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Locks {
    private Lock lock = new ReentrantLock();
    private Condition conditionMet = lock.newCondition();

    //Thread 1
    public void method1() throws InterruptedException {
        lock.lock();
        try {
            conditionMet.await();// <--suspend here
            //can now do dependent operations <--Resume here
        } finally {
            lock.unlock();
        }
    }

    //Thread 2
    public void method2(){
        lock.lock();
        try {
            //do some operations
            conditionMet.signal();
            //conditionMet.signalAll();
        } finally {
            lock.unlock();
        }
    }
 /*=======================================================================*/
    public synchronized void execute(){
        //wait for monitor notify
        Object monitor = new Object();
        try{
            monitor.wait();
        } catch (InterruptedException e){
            System.err.println("Interrupted");
        }
        //notify thread waiting on the monitor
        monitor.notify();

        //notify all threads
        monitor.notifyAll();
    }
    /*=======================================================================*/


}
