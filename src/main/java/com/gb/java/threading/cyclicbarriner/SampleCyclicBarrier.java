package com.gb.java.threading.cyclicbarriner;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by gbalasubramanian on 21/10/17.
 */
public class SampleCyclicBarrier {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                System.out.println("Continuation of the thread");
            }
        });
        for (int i=0;i<5;i++) {
            executorService.execute(new CyclicWorker(cyclicBarrier));
        }
        executorService.shutdown();
    }
}
class CyclicWorker extends Thread{
    CyclicBarrier cyclicBarrier;
    public CyclicWorker(CyclicBarrier cyclicBarrier){
        this.cyclicBarrier = cyclicBarrier;
    }
    @Override
    public void run() {
        try {
            System.out.println("Thread with id " + Thread.currentThread().getId() + " starts working");
            Thread.sleep(200);
            System.out.println("Thread with id " + Thread.currentThread().getId() + " finished working");
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
