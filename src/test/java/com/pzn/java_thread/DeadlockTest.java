package com.pzn.java_thread;

import org.junit.jupiter.api.Test;

public class DeadlockTest {
    @Test
    void transferDeadlock() throws InterruptedException {
        final var balance1 = new Balance(1_000_000L);
        final var balance2 = new Balance(1_000_000L);

        final var thread1 = new Thread(() -> {
            try {
                Balance.transferDeadlock(balance1, balance2, 500_000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        final var thread2 = new Thread(() -> {
            try {
                Balance.transferDeadlock(balance2, balance1, 500_000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("Balance 1 : " + balance1.getValue());
        System.out.println("Balance 2 : " + balance2.getValue());
    }

    @Test
    void transferSafe() throws InterruptedException {
        final var balance1 = new Balance(1_000_000L);
        final var balance2 = new Balance(1_000_000L);

        final var thread1 = new Thread(() -> {
            try {
                Balance.transfer(balance1, balance2, 500_000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        final var thread2 = new Thread(() -> {
            try {
                Balance.transfer(balance2, balance1, 500_000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("Balance 1 : " + balance1.getValue());
        System.out.println("Balance 2 : " + balance2.getValue());
    }
}
