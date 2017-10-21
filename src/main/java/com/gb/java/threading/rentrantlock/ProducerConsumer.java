package com.gb.java.threading.rentrantlock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by gbalasubramanian on 21/10/17.
 */
public class ProducerConsumer {
    static Lock lock = new ReentrantLock(true);
    static Condition condition = lock.newCondition();
    static int count = 0;
    static void produce(){
        while (true){
            if (count <=5){
                lock.lock();
                count++;
                condition.signal();
                System.out.println("Producing item " + count);
            }
            else {
                try {
                    condition.await();
                    lock.unlock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static void consume(){
        while (true){
            if (count >= 1){
                lock.lock();
                count--;
                condition.signal();
                System.out.println("Consuming item " + count);
            }
            else {
                try {
                    condition.await();
                    lock.unlock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(()->produce());
        Thread t2 = new Thread(()->consume());
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Complete...");
    }
}
