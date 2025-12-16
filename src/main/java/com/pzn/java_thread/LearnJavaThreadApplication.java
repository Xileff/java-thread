package com.pzn.java_thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LearnJavaThreadApplication {

    private static final Logger log = LoggerFactory.getLogger(LearnJavaThreadApplication.class);

    public static void main(String[] args) {
		SpringApplication.run(LearnJavaThreadApplication.class, args);

        log.info("Current thread name : {}", Thread.currentThread().getName());
	}

}
