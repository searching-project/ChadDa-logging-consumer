package com.consumer.kafka.instagramlogconsumer;

import com.consumer.kafka.instagramlogconsumer.service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@RequiredArgsConstructor
@SpringBootApplication
public class InstagramLogConsumerApplication {

    private final LogService logService;

    public static void main(String[] args) {
        SpringApplication.run(InstagramLogConsumerApplication.class, args);
    }

    @KafkaListener(id = "myId1", topics = "search_log")
    public void searchLogListen(String in) {

        logService.saveSearchLog(in);
        System.out.println(in);

    }

    @KafkaListener(id = "myId2", topics = "log")
    public void logListen(String in) {

        logService.saveLog(in);
        System.out.println(in);

    }

    @KafkaListener(id = "myId3", topics = "execution_time_log")
    public void executionTimeLogListen(String in) {

        logService.saveExecutionTimeLog(in);
        System.out.println(in);

    }
}
