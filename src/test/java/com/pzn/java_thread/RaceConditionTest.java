package com.pzn.java_thread;

import org.junit.jupiter.api.Test;

public class RaceConditionTest {
    @Test
    void counterTest() throws InterruptedException {
        final var counter = new Counter();

        final Runnable incrementTask = () -> {
            for (int i = 0; i < 1_000_000; i++) {
                counter.increment();
            }
        };

        // will not cause racing condition, because these runnables run inside the main thread sequentially
//        incrementTask.run();
//        incrementTask.run();
//        incrementTask.run();


//        will cause racing condition, because each thread runs in paralel
        final var thread1 = new Thread(incrementTask);
        final var thread2 = new Thread(incrementTask);
        final var thread3 = new Thread(incrementTask);

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

        System.out.println(counter.getValue());
    }
}
