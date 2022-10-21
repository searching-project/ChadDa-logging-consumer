package com.consumer.kafka.instagramlogconsumer.service;

import com.consumer.kafka.instagramlogconsumer.entity.Log;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LogServiceTest {

    @Autowired
    private LogService logService;

    @Test
    @DisplayName("ExecutionTimeLog 가공 테스트")
    void saveExecutionTimeLog() {

        // given
        String executionTimeLog = "2022-10-21 17:29:30.455 [http-nio-8080-exec-2] INFO  kafkaAppender-et - com.search.instagramsearching.service.UsersService searchUsers running time = 1137509300 ns teepz Page request [number: 0, size 20, sort: UNSORTED]";

        // when
        Log result = logService.saveLog(executionTimeLog);

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