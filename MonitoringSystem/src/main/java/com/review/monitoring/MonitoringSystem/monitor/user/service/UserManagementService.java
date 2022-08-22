package com.review.monitoring.MonitoringSystem.monitor.user.service;

import com.review.monitoring.MonitoringSystem.monitor.domain.User;
import com.review.monitoring.MonitoringSystem.monitor.vo.UserVO;

import java.util.List;

public interface UserManagementService {
    int checkDuplicateUser(String id);
    void register(User user);

    User getUser(String id);
    User logIn(String id, String password);
    User updateUserInfo(UserVO userVO, UserVO user);

    void delete(String userId);
}
