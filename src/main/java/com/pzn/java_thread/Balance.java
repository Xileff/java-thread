package com.pzn.java_thread;

public class Balance {
    private Long value;

    public Balance(Long value) {
        this.value = value;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public static void transferDeadlock(Balance from, Balance to, Long value) throws InterruptedException {
        // thread 1 lock balance1 & nunggu buat nge-lock balance2
        // thread 2 lock balance2 & nunggu buat nge-lock balance1
        // deadlock

        synchronized (from) {
            Thread.sleep(1000);
            synchronized (to) {
                Thread.sleep(1000);
                from.setValue(from.getValue() - value);
                to.setValue(to.getValue() + value);
            }
        }
    }

    public static void transfer(Balance from, Balance to, Long value) throws InterruptedException {
        synchronized (from) {
            Thread.sleep(1000);
            from.setValue(from.getValue() - value);
        }

        synchronized (to) {
            Thread.sleep(1000);
            to.setValue(to.getValue() + value);
        }
    }
}
