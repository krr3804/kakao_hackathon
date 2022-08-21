package com.review.monitoring.MonitoringSystem.monitor.service;

import com.review.monitoring.MonitoringSystem.monitor.domain.User;
import com.review.monitoring.MonitoringSystem.monitor.repository.UserManagementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserManagementServiceImpl implements UserManagementService{

    private final UserManagementRepository userManagementRepository;

    @Override
    public int checkDuplicateUser(String id) {
        return userManagementRepository.existById(id);
    }

    @Override
    @Transactional
    public void register(User user) {
        userManagementRepository.insertUser(user);
    }

    @Override
    public User logIn(String id, String password) {
        User user = userManagementRepository.selectOne(id);
        if(user == null) {
            return null;
        }
        if(!user.getPassword().equals(password)) {
            return null;
        }
        return user;
    }

    @Override
    @Transactional
    public void updateUserInfo(User user) {
        userManagementRepository.updateUser(user);
    }
}
