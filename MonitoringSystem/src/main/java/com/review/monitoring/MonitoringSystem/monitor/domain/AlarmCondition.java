package com.review.monitoring.MonitoringSystem.monitor.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class AlarmCondition {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int feedback;

    private int score;

    @Enumerated(EnumType.STRING)
    private Keyword keyword;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
