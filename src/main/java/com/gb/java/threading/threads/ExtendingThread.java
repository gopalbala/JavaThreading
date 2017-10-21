package com.gb.java.threading.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by gbalasubramanian on 21/10/17.
 */
public class ExtendingThread {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(new Worker1());
        executorService.execute(new Worker2());
        try {
            executorService.awaitTermination(3000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Worker1 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 100; ++i) {
            System.out.println("Worker 1: " + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Worker2 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 100; ++i) {
            System.out.println("Worker 2: " + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
