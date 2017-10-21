package com.gb.java.threading.latches;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by gbalasubramanian on 21/10/17.
 */
public class SampleCountdownLatch {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            executorService.execute(new LatchWorker(countDownLatch, i));
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
    }
}

class LatchWorker extends Thread {
    CountDownLatch countDownLatch;
    long threadId;

    public LatchWorker(CountDownLatch countDownLatch, long threadId) {
        this.countDownLatch = countDownLatch;
        this.threadId = threadId;
    }

    @Override
    public void run() {
        System.out.println("called by thread " + threadId + " Latch count " + countDownLatch.getCount());
        countDownLatch.countDown();

    }
}
