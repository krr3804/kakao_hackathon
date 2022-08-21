package com.review.monitoring.MonitoringSystem.monitor.repository;

import com.review.monitoring.MonitoringSystem.monitor.domain.User;

import java.util.List;

public interface UserManagementRepository {
    void insertUser(User user);
    int existById(String id);
    User selectOne(String userId);
    void updateUser(User user);
}
