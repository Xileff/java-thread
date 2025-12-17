package com.pzn.java_thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {
    @Test
    void threadpoolTest() throws InterruptedException {

        final var minThread = 10;
        final var maxThread = 100;
        final var aliveTime = 1;
        final var aliveTimeUnit = TimeUnit.MINUTES;
        final var taskQueue = new ArrayBlockingQueue<Runnable>(100);

        final var threadpool = new ThreadPoolExecutor(minThread, maxThread, aliveTime, aliveTimeUnit, taskQueue);

        threadpool.execute(() -> {
            try {
                Thread.sleep(5000);
                System.out.println("Message from thread : " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread.sleep(6000);
    }

    @Test
    void threadpoolTerminationTest() throws InterruptedException {

        final var minThread = 10;
        final var maxThread = 100;
        final var aliveTime = 1;
        final var aliveTimeUnit = TimeUnit.MINUTES;
        final var taskQueue = new ArrayBlockingQueue<Runnable>(10);

        final var executor = new ThreadPoolExecutor(minThread, maxThread, aliveTime, aliveTimeUnit, taskQueue);

        for (int i = 0; i < 100; i++) {
            final var taskNumber = i;

            executor.execute(() -> {
                try {
                    Thread.sleep(1000);
                    System.out.println("Task : " + taskNumber + " from thread : " + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }


        executor.awaitTermination(1, TimeUnit.DAYS);
    }
}
