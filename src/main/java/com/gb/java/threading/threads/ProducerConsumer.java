package com.gb.java.threading.threads;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gbalasubramanian on 21/10/17.
 */
public class ProducerConsumer {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> produce());
        Thread t2 = new Thread(() -> consume());

        t1.start();
        t2.start();
    }

    static Object lock = new Object();
    static int count;
    static List<Integer> produced = new ArrayList<>();

    static void produce() {
        synchronized (lock) {
            while (true) {
                if (count <= 5) {
                    count++;
                    produced.add(count);
                    System.out.println("added item: " + count + " to the list.");
                    lock.notify();
                } else {
                    try {
                        lock.wait();
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
    }

    static void consume() {
        synchronized (lock) {
            while (true) {
                if (count >= 1) {
                    count--;
                    produced.remove(count);
                    System.out.println("removed item: " + count + " to the list.");
                    lock.notify();
                } else {
                    try {
                        lock.wait();
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
    }
}
