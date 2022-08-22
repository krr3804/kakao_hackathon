package com.review.monitoring.MonitoringSystem.monitor.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Alarm {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long feedback;

    private Long score;

    @Enumerated(EnumType.STRING)
    private Keyword keyword;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Alarm() {

    }

    public Alarm(Long feedback, Long score, Keyword keyword, User user) {
        this.feedback = feedback;
        this.score = score;
        this.keyword = keyword;
        this.user = user;
    }
}
