package com.consumer.kafka.instagramlogconsumer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SearchLogService {

    private final SearchLogRepository searchLogRepository;

    public void saveSearchLog(String in) {

        List<String> strs = new ArrayList<>();
        String[] splitStr = in.split(" ");
        Collections.addAll(strs, splitStr);

        // 방법 1 : time_record를 그대로 String으로 저장
        SearchLog searchLog = SearchLog.builder()
                .timeRecord(strs.get(0))
                .thread(strs.get(1))
                .pattern(strs.get(2))
                .logger(strs.get(4))
                .keyword(strs.get(7))
                .build();

        /*
        // 방법 2 : time_record를 Timestamp로 저장
        // String을 LocalDateTime으로 변환
//        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

        LocalDateTime time_record = LocalDateTime.parse(strs.get(0), format);
//        System.out.println("strToLocalDateTime1 : " + strToLocalDateTime1);

        SearchLog searchLog = SearchLog.builder()
                .timeRecord(time_record)
                .thread(strs.get(1))
                .pattern(strs.get(2))
                .logger(strs.get(4))
                .keyword(strs.get(7))
                .build();
        */

        searchLogRepository.save(searchLog);
    }
}
