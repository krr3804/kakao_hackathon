package com.review.monitoring.MonitoringSystem.monitor.service;

import com.review.monitoring.MonitoringSystem.monitor.domain.User;

import java.util.List;

public interface UserManagementService {
    int checkDuplicateUser(String id);
    void register(User user);
    User logIn(String id, String password);
    void updateUserInfo(User user);
}
