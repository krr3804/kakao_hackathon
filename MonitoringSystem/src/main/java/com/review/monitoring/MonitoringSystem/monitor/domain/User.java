package com.review.monitoring.MonitoringSystem.monitor.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
public class User {
    @Id
    @Column(name = "user_id", nullable = false)
    private String id;

    @Column(name ="password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Department department;

    @OneToMany(mappedBy = "user")
    private List<AlarmCondition> conditions = new ArrayList<>();

    public User() {}

    public User(String id, String password, String email, Department department) {
        this.id = id;
        this.password = password;
        this.email = email;
        this.department = department;
    }
}
