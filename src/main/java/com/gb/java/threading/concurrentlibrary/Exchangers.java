package com.gb.java.threading.concurrentlibrary;

import java.util.concurrent.Exchanger;

/**
 * Created by gbalasubramanian on 21/10/17.
 */
public class Exchangers {
    public static void main(String[] args) {
        Exchanger<Integer> exchanger = new Exchanger<>();

        new Thread(new ExchangeProducer(exchanger)).start();
        new Thread(new ExchangeConsumer(exchanger)).start();

    }
}
class ExchangeProducer implements Runnable{
    Exchanger<Integer> exchanger;
    int count;
    public ExchangeProducer(Exchanger<Integer> exchanger){
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        while (true){
            count+=1;
            try {
                count = exchanger.exchange(count);
                System.out.println("Exchanging " + count + " from sender");
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
class ExchangeConsumer implements Runnable{
    Exchanger<Integer> exchanger;
    int count;
    public ExchangeConsumer(Exchanger<Integer> exchanger){
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        while (true){
            try {
                count = exchanger.exchange(count);
                System.out.println("Exchanging " + count + " from receiver");
                count--;

                Thread.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}