package com.gb.java.threading.threads;

/**
 * Created by gbalasubramanian on 21/10/17.
 */
public class ThreadSynchronization {
    static int count = 0;
    static int synchronizedCount = 0;

    static synchronized void syncIncrement(){
        ++synchronizedCount;
    }

    public static void main(String[] args) {
        process();
    }

    static void increment() {
        ++count;
    }

    static void process() {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; ++i) {
                    increment();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; ++i) {
                    increment();
                }
            }
        });

        t1.start();
        t2.start();



        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0; i<1000;i++){
                    syncIncrement();
                }
            }
        });

        Thread t4 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0 ;i< 1000;i++) {
                    syncIncrement();
                }
            }
        });

        t3.start();
        t4.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final value: " + count);
        System.out.println("Final synchronized value: " + synchronizedCount);

    }
}
