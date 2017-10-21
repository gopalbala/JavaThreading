package com.gb.java.threading.concurrentlibrary;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Created by gbalasubramanian on 21/10/17.
 */
public class DelayQueues {
    public static void main(String[] args) {
        BlockingQueue<DelayedWorker> blockingQueue = new DelayQueue<DelayedWorker>();

        try {
            blockingQueue.put(new DelayedWorker("This is message #1", 1000));
            blockingQueue.put(new DelayedWorker("This is message #2", 4000));
            blockingQueue.put(new DelayedWorker("This is message #3", 2000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (!blockingQueue.isEmpty()){
            try {
                System.out.println(blockingQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class DelayedWorker implements Delayed {

    long duration;
    String message;

    public DelayedWorker(String message, long duration) {
        this.message = message;
        this.duration = duration;
    }

    @Override
    public long getDelay(TimeUnit unit) {
       //System.out.println("delay: " + (duration - System.currentTimeMillis()));
        return unit.convert(duration - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        if (this.duration < ((DelayedWorker) o).duration){
            //System.out.println(this.duration + " " + ((DelayedWorker) o).duration);
            return -1;
        }

        else if (this.duration > ((DelayedWorker) o).duration) {
            //System.out.println(this.duration + " " + ((DelayedWorker) o).duration);
            return 1;
        }
        else {
            //System.out.println(this.duration + " " + ((DelayedWorker) o).duration);
            return 0;
        }
    }
    @Override
    public String toString(){
        return this.message;
    }
}
