package com.gb.java.threading.threads;

/**
 * Created by gbalasubramanian on 21/10/17.
 */
public class JoiningThread {
    public static void main(String[] args) {
        Thread t1 = new Worker1();
        Thread t2 = new Worker2();

        t1.start();
        t2.start();
//
//        try {
//            //t1.join();
//            //t2.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println("Finished all the tsks...");
    }
}


