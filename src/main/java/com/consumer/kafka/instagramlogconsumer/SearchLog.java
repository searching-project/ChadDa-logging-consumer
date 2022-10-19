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
    private Long id;

    @Column
    private LocalDateTime timeRecord;

    @Column
    private String thread;

    @Column
    private String pattern;

    @Column
    private String logger;

    @Column
    private String keyword;
}
