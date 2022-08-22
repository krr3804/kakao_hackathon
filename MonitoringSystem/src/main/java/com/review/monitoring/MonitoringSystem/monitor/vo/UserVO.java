package com.review.monitoring.MonitoringSystem.monitor.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UserVO {
    @NotBlank
    private String id;
    @NotBlank
    private String password;
    @NotBlank
    private String email;
    @NotBlank
    private String department;

    public UserVO() {

    }

    public UserVO(String id, String email, String department) {
        this.id = id;
        this.email = email;
        this.department = department;
    }
}
