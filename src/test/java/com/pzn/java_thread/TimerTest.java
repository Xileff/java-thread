package com.pzn.java_thread;

import org.junit.jupiter.api.Test;

import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {
    @Test
    void timerTest() throws InterruptedException {
        final var timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Delayed job");
            }
        };

        final var timer = new Timer();

        timer.schedule(timerTask, 2000);

        Thread.sleep(3000);
    }
}
