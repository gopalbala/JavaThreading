package com.gb.java.threading.concurrentlibrary;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by gbalasubramanian on 21/10/17.
 */
public class PriorityBlockingQueues {
    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new PriorityBlockingQueue<>();

        PriorityProducer producer = new PriorityProducer(blockingQueue);
        PriorityConsumer consumer = new PriorityConsumer(blockingQueue);


        new Thread(producer).start();
        new Thread(consumer).start();

    }
}
class PriorityProducer implements Runnable{

    BlockingQueue<String> blockingQueue;
    public PriorityProducer(BlockingQueue<String> blockingQueue){
        this.blockingQueue = blockingQueue;
    }
    @Override
    public void run() {
        for (int i=0;i<10;i++){
            try {
                blockingQueue.put(String.valueOf(i));
                System.out.println("adding item " + i);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
class PriorityConsumer implements Runnable{

    BlockingQueue<String> blockingQueue;
    public PriorityConsumer(BlockingQueue<String> blockingQueue){
        this.blockingQueue = blockingQueue;
    }
    @Override
    public void run() {
        for (int i=0;i<10;i++){
            try {
                //Thread.sleep(50);
                System.out.println("Taking item " + blockingQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}