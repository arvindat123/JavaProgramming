package org.ThreadLearning;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {
    private static ReentrantLock lock = new ReentrantLock(); // It is called reentrant because it allows to call lock on same
    //object multiple times
    //Synchronised is implicit and lock is explicit becuase we are writing lock.unlock
    //Locks allow locking/unlocking in any scopes and in any order, acquire lock in one method and release lock in another method
    //Ability to tryLock and tryLock(timeout)
    private static void accessResourceMethod1(){
        lock.lock();   // similar to synchronized(this) {
        System.out.println(Thread.currentThread().getName() + "== Within Lock");
        //If exception is thrown here then below line will not execute and thread will keep this lock forever that is why
        //it is advisable to put lock.unlock(); in finally block
        lock.unlock();   // similar to }
    }

    //fair lock, out of many threads, after unlock by previous thread, most waited thread will get a chance to acquire lock
    //lock = new ReentrantLock(true); fair lock is slower but equal chance for all threads
    //unfair lock-> lock = new ReentrantLock(); implicitly or explicitly lock = new ReentrantLock(false);
    //unfair(Barge-in) : There is no preference to most waited thread to acquire lock, any thread from wait queue can acquire lock
    //unfair is faster but possible thread starvation
    private static void accessResourceMethod2(){
        lock.lock();
        lock.lock();   //number of times lock called without calling unlock,

        int number = lock.getHoldCount();

        lock.unlock(); // lock.unlock(); calls must match the number of lock.lock();
        lock.unlock();
    }

    private static void accessResourceMethod3(){
        lock.lock();

    //update share resource
        if(true /*someCondition()*/) {
             accessResourceMethod3(); //it is calling same method on basis of some condition and entering lock.lock() again,
            //that is why it is called reentrant lock.
            //getHoldCount will increase based on number of recursions.
            //although it already hold the lock so internally it doesn't acquire lock again but getHoldCount increases
        }

        lock.unlock();
    }

    //tryLock(); it return boolean value which try to get lock if got return true else return false;
    private static void accessResourceMethod4(){
        boolean lockAcquired = lock.tryLock(); // one thing is weired is that if ReentrantLock is fair(true) but tryLock doesn'thonor fairness
        //to implement fairness mention timeout as 0 second like boolean lockAcquired = lock.tryLock(0, TimeUnit.SECONDS);
        //try to acquire lock or timeout after specified time example below
        //boolean lockAcquired = lock.tryLock(5, TimeUnit.SECONDS);
        if(lockAcquired){
            try {
                //access resource
            } finally {
                lock.unlock();
            }
        } else {
            //do alternate thing
        }
    }



    public static void main(String[] args) {

        Thread t1 = new Thread(() -> accessResourceMethod1()); t1.start();
        Thread t2 = new Thread(() -> accessResourceMethod1()); t2.start();
        Thread t3 = new Thread(() -> accessResourceMethod1()); t3.start();
        Thread t4 = new Thread(() -> accessResourceMethod1()); t4.start();

    }
}
