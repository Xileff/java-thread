package com.pzn.java_thread;

import org.junit.jupiter.api.Test;

public class ThreadCommunicationTest {
    String message;

    @Test
    void threadCommunicationTest() throws InterruptedException {
        final var mockObject = new Object();

        final var thread1 = new Thread(() -> {
            synchronized (mockObject) {
                try {
                    mockObject.wait(); // karena ada wait, jadi ini gak menyebabkan deadlock
                    System.out.println(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        final var thread2 = new Thread(() -> {
            synchronized (mockObject) {
                message = "Message loaded";
                mockObject.notify();
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

    }
}
