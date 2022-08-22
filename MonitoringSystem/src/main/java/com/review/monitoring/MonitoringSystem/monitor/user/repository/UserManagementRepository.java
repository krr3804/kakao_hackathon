package com.review.monitoring.MonitoringSystem.monitor.user.repository;

import com.review.monitoring.MonitoringSystem.monitor.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserManagementRepository{
    void insert(User user);
    int existById(String id);
    User selectOne(String userId);
    User update(User user);

    void delete(String userId);
}
