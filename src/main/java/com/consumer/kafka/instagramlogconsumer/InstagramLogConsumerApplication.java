package com.consumer.kafka.instagramlogconsumer;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@RequiredArgsConstructor
@SpringBootApplication
public class InstagramLogConsumerApplication {

    private final SearchLogService searchLogService;


    public static void main(String[] args) {
        SpringApplication.run(InstagramLogConsumerApplication.class, args);
    }

    @KafkaListener(id = "myId", topics = "search_log")
    public void searchLogListen(String in) {

        searchLogService.saveSearchLog(in);
        System.out.println(in);

    }
}
