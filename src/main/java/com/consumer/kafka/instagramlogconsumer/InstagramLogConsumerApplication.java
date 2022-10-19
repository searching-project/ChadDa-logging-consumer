package com.consumer.kafka.instagramlogconsumer;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@SpringBootApplication
public class InstagramLogConsumerApplication {

    private final SearchLogService searchLogService;


    public static void main(String[] args) {
        SpringApplication.run(InstagramLogConsumerApplication.class, args);
    }

    @KafkaListener(id = "myId", topics = "search_log")
    public void searchLogListen(String in) {
        System.out.println(in);
//        saveSearchLog(in);

        List<String> strs = new ArrayList<>();
        String[] splitStr = in.split(" ");
        Collections.addAll(strs, splitStr);

        System.out.println("time : " + strs.get(0));
        System.out.println("thread : " + strs.get(1));
        System.out.println("pattern : " + strs.get(2));
        System.out.println("logger : " + strs.get(4));
        System.out.println("keyword : " + strs.get(7));

        searchLogService.saveSearchLog(in);

//        SearchLog searchLog = SearchLog.builder()
//                .timeRecord(strs.get(0))
//                .thread(strs.get(1))
//                .pattern(strs.get(2))
//                .logger(strs.get(4))
//                .keyword(strs.get(7))
//                .build();
//
//        searchLogRepository.save(searchLog);

    }
}
