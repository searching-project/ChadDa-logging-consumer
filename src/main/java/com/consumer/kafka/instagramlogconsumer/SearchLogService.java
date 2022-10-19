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

        /*
        // 방법 1 : time_record를 그대로 String으로 저장
        SearchLog searchLog = SearchLog.builder()
                .timeRecord(strs.get(0))
                .thread(strs.get(1))
                .pattern(strs.get(2))
                .logger(strs.get(4))
                .keyword(strs.get(7))
                .build();
        */

        // 방법 2 : time_record를 Timestamp로 저장
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

        // String을 LocalDateTime으로 변환
        LocalDateTime datetime = LocalDateTime.parse(strs.get(0) + " " + strs.get(1), format);

        String thread = strs.get(2).replaceAll("[\\[\\]]","");
        String keyword = strs.get(8).replace("\n","");

        SearchLog searchLog = SearchLog.builder()
                .timeRecord(datetime)
                .thread(thread)
                .pattern(strs.get(3))
                .logger(strs.get(5))
                .keyword(keyword)
                .build();

        searchLogRepository.save(searchLog);
    }
}
