package com.gb.java.threading.concurrentlibrary;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by gbalasubramanian on 21/10/17.
 */
public class ConcurrentMaps {
    public static void main(String[] args) {
        ConcurrentMap<String, Integer> map = new ConcurrentHashMap<>();
        ConcurrentProducer concurrentProducer = new ConcurrentProducer(map);
        ConcurrentConsumer concurrentConsumer = new ConcurrentConsumer(map);

        new Thread(concurrentProducer).start();
        new Thread(concurrentConsumer).start();
    }
}

class ConcurrentProducer implements Runnable{
    ConcurrentMap<String,Integer> concurrentMap;
    public ConcurrentProducer(ConcurrentMap<String,Integer> concurrentMap){
        this.concurrentMap = concurrentMap;
    }

    @Override
    public void run() {
        for (int i=0;i<10;i++){
            try {
                concurrentMap.putIfAbsent(String.valueOf(i),i);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
class ConcurrentConsumer implements Runnable{
    ConcurrentMap<String,Integer> concurrentMap;
    public ConcurrentConsumer(ConcurrentMap<String,Integer> concurrentMap){
        this.concurrentMap = concurrentMap;
    }

    @Override
    public void run() {
        for (int i=0;i<10;i++){
            try {
                System.out.println( concurrentMap.get(String.valueOf(i)));
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
