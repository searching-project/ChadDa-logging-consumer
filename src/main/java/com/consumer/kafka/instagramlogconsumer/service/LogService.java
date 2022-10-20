package com.consumer.kafka.instagramlogconsumer.service;

import com.consumer.kafka.instagramlogconsumer.entity.ExecutionTimeLog;
import com.consumer.kafka.instagramlogconsumer.entity.Log;
import com.consumer.kafka.instagramlogconsumer.entity.SearchLog;
import com.consumer.kafka.instagramlogconsumer.repository.ExecutionTimeLogRepository;
import com.consumer.kafka.instagramlogconsumer.repository.LogRepository;
import com.consumer.kafka.instagramlogconsumer.repository.SearchLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class LogService {

//    private final SearchLogRepository searchLogRepository;
//    private final LogRepository logRepository;
//    private final ExecutionTimeLogRepository executionTimeLogRepository;

    @Autowired
    private SearchLogRepository searchLogRepository;

    @Autowired
    private LogRepository logRepository;

    @Autowired
    private ExecutionTimeLogRepository executionTimeLogRepository;



    @Transactional
    public void saveSearchLog(String in) {

        List<String> strs = new ArrayList<>();
        String[] splitStr = in.split(" ");
        Collections.addAll(strs, splitStr);

        if (strs.get(7).equals("name:")) {

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

            String thread = strs.get(2).replaceAll("[\\[\\]]", "");
            String keyword = strs.get(8).replace("\n", "");

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

    @Transactional
    public void saveLog(String in) {
        List<String> strs = new ArrayList<>();
        String[] splitStr = in.split(" ");
        Collections.addAll(strs, splitStr);

//        if (strs.get(7).startsWith("com")) {

        // 방법 2 : time_record를 Timestamp로 저장
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

        // String을 LocalDateTime으로 변환
        LocalDateTime datetime = LocalDateTime.parse(strs.get(0) + " " + strs.get(1), format);

        String thread = strs.get(2).replaceAll("[\\[\\]]", "");
        String className = strs.get(8);
        String methodName = strs.get(9);
//            String parameter = strs.get(10);
        String parameter = strs.get(10) + strs.get(11) + strs.get(12) + strs.get(13) + strs.get(14) + strs.get(15) + strs.get(16) + strs.get(17) + strs.get(18);

        Log log = Log.builder()
                .timeRecord(datetime)
                .thread(thread)
                .level(strs.get(3))
                .logger(strs.get(5))
                .className(className)
                .methodName(methodName)
                .parameter(parameter)
                .build();

        logRepository.save(log);
        System.out.println("저장 명령 완료");
//        }
    }

    @Transactional
    public void saveExecutionTimeLog(String in) {
        List<String> strs = new ArrayList<>();
        String[] splitStr = in.split(" ");
        Collections.addAll(strs, splitStr);

//        if (strs.get(7).startsWith("com")) {

        // 방법 2 : time_record를 Timestamp로 저장
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

        // String을 LocalDateTime으로 변환
        LocalDateTime datetime = LocalDateTime.parse(strs.get(0) + " " + strs.get(1), format);

        String thread = strs.get(2).replaceAll("[\\[\\]]", "");
        String className = strs.get(8);
        String methodName = strs.get(9);
        String parameter = strs.get(10) + strs.get(11) + strs.get(12) + strs.get(13) + strs.get(14) + strs.get(15) + strs.get(16) + strs.get(17) + strs.get(18);
        String executionTime = strs.get(22);

        ExecutionTimeLog log = ExecutionTimeLog.builder()
                .timeRecord(datetime)
                .thread(thread)
                .level(strs.get(3))
                .logger(strs.get(5))
                .className(className)
                .methodName(methodName)
                .parameter(parameter)
                .executionTime(executionTime)
                .build();

        executionTimeLogRepository.save(log);
        System.out.println("실행시간 저장 명령 완료");
//        }
    }
}
