package com.gb.java.threading.threads;

/**
 * Created by gbalasubramanian on 21/10/17.
 */
public class ThreadTermination {
    public static void main(String[] args) {
        TerminableWorker worker = new TerminableWorker();
        Thread t1 = new Thread(worker);
        t1.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        worker.isTerminated = true;
        System.out.println("Done..");
    }
}

class TerminableWorker extends Thread{
    public boolean isTerminated = false;

    @Override
    public void run(){
        while (!isTerminated) {
            System.out.println(System.currentTimeMillis());
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}