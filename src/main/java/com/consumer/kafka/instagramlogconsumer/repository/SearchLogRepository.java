package com.consumer.kafka.instagramlogconsumer.repository;

import com.consumer.kafka.instagramlogconsumer.entity.SearchLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SearchLogRepository extends JpaRepository<SearchLog, Long> {
}
