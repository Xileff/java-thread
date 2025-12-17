package com.pzn.java_thread;

import org.junit.jupiter.api.Test;

public class SynchronizationTest {
    @Test
    void synchronizedCounterTest() throws InterruptedException {
        final var syncCounter = new SynchronizedCounter();

        final Runnable incrementTask = () -> {
            for (int i = 0; i < 1_000_000; i++) {
                syncCounter.increment();
            }
        };

        final var thread1 = new Thread(incrementTask);
        final var thread2 = new Thread(incrementTask);
        final var thread3 = new Thread(incrementTask);

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

        System.out.println(syncCounter.getValue());
    }
}
