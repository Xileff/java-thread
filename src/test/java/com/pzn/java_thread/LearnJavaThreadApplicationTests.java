package com.pzn.java_thread;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LearnJavaThreadApplicationTests {

	@Test
    void mainThread() {
        System.out.println("Current thread name : " + Thread.currentThread().getName());
    }

    @Test
    void createThread() {
        final Runnable runnable = () -> System.out.println("Thread name : " + Thread.currentThread().getName());

        runnable.run(); // will run on main thread

        final Thread thread = new Thread(runnable);

        thread.start();

        System.out.println("Method finished");
    }

    @Test
    void threadSleep() throws InterruptedException {
        final Runnable runnable = () -> {
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Thread name : " + Thread.currentThread().getName());
        };

        final Thread thread = new Thread(runnable);

        thread.start();
        thread.join(); // will wait for thread to finish (blocking)

        System.out.println("Method finished");
    }

    @Test
    void threadInterrupt() throws InterruptedException {
        final Runnable runnable = () -> {
            System.out.println("Thread name : " + Thread.currentThread().getName());

            for (int i = 0; i < 1000000; i++) {
                System.out.println("Runnable : " + i);
                if (Thread.interrupted()) {
                    return;
                }
            }
        };

        final Thread thread = new Thread(runnable);

        // both these lines will start at the same time
        thread.start(); // new thread starts
        Thread.sleep(1000L); // main thread sleeps

        thread.interrupt(); // interrupt the new thread after main thread slept for 5000ms

        System.out.println("Waiting for new thread to finish");
        thread.join();

        System.out.println("Method finished");
    }

    @Test
    void threadName() {
        final Runnable runnable = () -> System.out.println(Thread.currentThread().getName());

        final Thread thread = new Thread(runnable);
        thread.setName("test-thread");

        thread.start();
    }
}
