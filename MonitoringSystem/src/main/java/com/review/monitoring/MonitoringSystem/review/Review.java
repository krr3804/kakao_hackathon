package com.review.monitoring.MonitoringSystem.review;

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
    @Column(nullable = true)
    private int star;
    @Column(nullable = true)
    private LocalDateTime date;

    @Column(nullable = true)
    private String department;
    @Column(nullable = true)
    private int feedback;
    @Column(nullable = true)
    private int score;
    @Column(nullable = true)
    private String comment;
    @Column(nullable = true)
    private String mu_keyword;
}
