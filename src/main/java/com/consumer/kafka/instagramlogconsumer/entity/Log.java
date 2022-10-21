package com.consumer.kafka.instagramlogconsumer.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "log")
@Builder
@Getter
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDateTime timeRecord;

    @Column
    private String thread;

    @Column
    private String level;

    @Column
    private String logger;

    @Column
    private String className;

    @Column
    private String methodName;

    @Column
    private String parameterKeyword;

    @Column
    private String pageRequestNumber;

    @Column
    private String pageRequestSize;

    @Column
    private String sort;
}