package com.gb.java.threading.semaphores;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Created by gbalasubramanian on 21/10/17.
 */
public class CountingSemaphore {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i=0;i<100;i++){
            executorService.execute(()->DbConnection.INSTANCE.openConnection());
        }
        try {
            executorService.shutdown();
            executorService.awaitTermination(2000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
 enum DbConnection{
    INSTANCE;
    private Semaphore semaphore = new Semaphore(10,true);
    int count;
    public void openConnection(){
        try {
            semaphore.acquire();
            System.out.println("opening connection: " + count++);
            Thread.sleep(1000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            System.out.println("closing connection: " + count--);
            semaphore.release();
        }
    }
}
