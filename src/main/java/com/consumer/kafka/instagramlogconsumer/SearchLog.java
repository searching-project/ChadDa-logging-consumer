package com.consumer.kafka.instagramlogconsumer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "search_log")
@Builder
public class SearchLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String timeRecord;

    @Column
    String thread;

    @Column
    String pattern;

    @Column
    String logger;

    @Column
    String keyword;
}
