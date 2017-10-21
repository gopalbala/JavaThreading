package com.gb.java.threading.rentrantlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by gbalasubramanian on 21/10/17.
 */
public class SampleReentrantLock {
    /**
     * Rentrant lock is an implementation of lock. It has a fairness parameter
     * which will hand over the processor time for the longest waiting thread
     * if set to false the execution is random
     * reentrant lock locking does not require try catch in case of exception
     * we have to unlock the object else it will be in deadlocked sate
     * it is best practice to surround the lock with try finally in case of reenrtrant lock
     * @param args
     */
    static int count =0;
    static Lock lock = new ReentrantLock(true);
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> increment());
        t1.start();
        Thread t2 = new Thread(() -> increment());
        t2.start();


        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final count: "+count);
    }
    static void increment(){
        lock.lock();
        try {
            for (int i=0;i<10;i++)
                count++;
        }finally {
            lock.unlock();
        }
    }
}
