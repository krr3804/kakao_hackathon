package com.review.monitoring.MonitoringSystem.review;

import com.review.monitoring.MonitoringSystem.monitor.domain.Department;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Review {
    @Id @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int star;

    @Column(nullable = false)
    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    private Department department;

    @Column(nullable = true)
    private int feedback;

    @Column(nullable = true)
    private int score;

    @Column(nullable = false)
    private String comment;

    @Column(nullable = true)
    private String mu_keyword;
}
