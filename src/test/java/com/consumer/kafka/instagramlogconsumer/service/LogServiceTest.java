package com.consumer.kafka.instagramlogconsumer.service;

import com.consumer.kafka.instagramlogconsumer.entity.ExecutionTimeLog;
import com.consumer.kafka.instagramlogconsumer.entity.Log;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LogServiceTest {

    @Autowired
    private LogService logService;

    @Test
    void saveSearchLog() {
    }

    @Test
    @DisplayName("Log 가공 테스트")
    void saveLog() {
        // given
        String log = "2022-10-21 17:29:28.749 [http-nio-8080-exec-2] INFO  kafkaAppender-l - com.search.instagramsearching.controller.UsersController searchUsers  hry Page request [number: 0, size 20, sort: UNSORTED]";

        // when
        Log result = logService.saveLog(log);

        // then
        Assertions.assertThat(result.getTimeRecord()).isNotNull();
        Assertions.assertThat(result.getThread()).isNotNull();
        Assertions.assertThat(result.getLevel()).isNotNull();
        Assertions.assertThat(result.getLogger()).isNotNull();
        Assertions.assertThat(result.getClassName()).isNotNull();
        Assertions.assertThat(result.getMethodName()).isNotNull();
        Assertions.assertThat(result.getParameterKeyword()).isNotNull();

        System.out.println(result);
    }

    @Test
    @DisplayName("ExecutionTimeLog 가공 테스트")
    void saveExecutionTimeLog() {

        // given
        String executionTimeLog = "2022-10-21 17:29:30.455 [http-nio-8080-exec-2] INFO  kafkaAppender-et - com.search.instagramsearching.service.UsersService searchUsers running time = 1137509300 ns teepz Page request [number: 0, size 20, sort: UNSORTED]";

        // when
        ExecutionTimeLog result = logService.saveExecutionTimeLog(executionTimeLog);

        // then
        Assertions.assertThat(result.getTimeRecord()).isNotNull();
        Assertions.assertThat(result.getThread()).isNotNull();
        Assertions.assertThat(result.getLevel()).isNotNull();
        Assertions.assertThat(result.getLogger()).isNotNull();
        Assertions.assertThat(result.getClassName()).isNotNull();
        Assertions.assertThat(result.getMethodName()).isNotNull();
        Assertions.assertThat(result.getExecutionTime()).isNotNull();
        Assertions.assertThat(result.getParameterKeyword()).isNotNull();

        System.out.println(result);
    }
}