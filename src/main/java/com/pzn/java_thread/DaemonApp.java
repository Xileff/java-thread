package com.pzn.java_thread;

public class DaemonApp {
    public static void main(String[] args) {
        final var thread = new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Hello Daemon");
        });

        thread.setDaemon(false);
        thread.start();

        System.out.println("App finished"); // this code will run first, because there is 3000ms delay in the other thread
    }
}
