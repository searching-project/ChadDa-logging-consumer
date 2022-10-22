package com.consumer.kafka.instagramlogconsumer.service;

import com.consumer.kafka.instagramlogconsumer.entity.Log;
import com.consumer.kafka.instagramlogconsumer.repository.LogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    private final LogRepository logRepository;

    @Transactional
    // 테스트용
    public Log saveLog(String in) {
        List<String> strs = new ArrayList<>();
        String[] splitStr = in.split(" ");
        Collections.addAll(strs, splitStr);

        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

        // String을 LocalDateTime으로 변환
        LocalDateTime datetime = LocalDateTime.parse(strs.get(0) + " " + strs.get(1), format);

        if (strs.get(5).equals("kafkaAppender")) {
            String thread = strs.get(2).replaceAll("[\\[\\]]", "");
            String level = strs.get(3);
            String logger = strs.get(5);
            String className = strs.get(7);
            String methodName = strs.get(8);
            String executionTime = strs.get(12) + "ns";
            String parameterKeyword = strs.get(14).replaceAll("\n", "");
            String pageRequestNumber = "";
            String pageRequestSize = "";
            String sort = "";


            if (strs.size() >= 18) {
                pageRequestNumber = strs.get(18).replaceAll(",", "");
            }

            if (strs.size() >= 20) {
                pageRequestSize = strs.get(20).replaceAll(",", "");
            }

            if (strs.size() >= 22) {
                sort = strs.get(22).replaceAll("]", "").replaceAll("\n", "");
            }


            Log log = Log.builder()
                    .timeRecord(datetime)
                    .thread(thread)
                    .level(level)
                    .logger(logger)
                    .className(className)
                    .methodName(methodName)
                    .executionTime(executionTime)
                    .parameterKeyword(parameterKeyword)
                    .pageRequestNumber(pageRequestNumber)
                    .pageRequestSize(pageRequestSize)
                    .sort(sort)
                    .build();

            logRepository.save(log);
            System.out.println("실행시간 저장 명령 완료");

            return log;
        }
        return null;
    }
}
