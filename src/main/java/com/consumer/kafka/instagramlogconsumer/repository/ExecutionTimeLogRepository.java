package com.consumer.kafka.instagramlogconsumer.repository;

import com.consumer.kafka.instagramlogconsumer.entity.ExecutionTimeLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExecutionTimeLogRepository extends JpaRepository<ExecutionTimeLog, Long> {
}
