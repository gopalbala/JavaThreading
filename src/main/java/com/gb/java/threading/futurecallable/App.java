package com.gb.java.threading.futurecallable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by gbalasubramanian on 20/10/17.
 */
public class App {
    public static void main(String[] args) {
        List<Future<String>> futureList = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            futureList.add(executorService.submit(new Processor(i)));
        }
        for (Future<String> future : futureList) {
            try {
                System.out.println(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(2000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Processor implements Callable<String> {
    int id;

    public Processor(int id) {
        this.id = id;
    }

    @Override
    public String call() throws Exception {
        Thread.sleep(200);
        return "ID:" + this.id;
    }
}
