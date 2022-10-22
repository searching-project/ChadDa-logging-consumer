package com.consumer.kafka.instagramlogconsumer.repository;

import com.consumer.kafka.instagramlogconsumer.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Log, Long> {
}
