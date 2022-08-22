package com.review.monitoring.MonitoringSystem.monitor.user.service;

import com.review.monitoring.MonitoringSystem.monitor.domain.Department;
import com.review.monitoring.MonitoringSystem.monitor.domain.User;
import com.review.monitoring.MonitoringSystem.monitor.user.repository.UserManagementRepository;
import com.review.monitoring.MonitoringSystem.monitor.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        userManagementRepository.insert(user);
    }

    @Override
    public User getUser(String id) {
        return userManagementRepository.selectOne(id);
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
    public User updateUserInfo(UserVO updatedVO, UserVO user) {
        if(updatedVO.getEmail().equals(user.getEmail())
                && updatedVO.getDepartment().equals(user.getDepartment())) {
            return null;
        }

        return userManagementRepository.update(new User(
                user.getId(), updatedVO.getPassword(),
                updatedVO.getEmail(), Department.of(updatedVO.getDepartment())));
    }

    @Override
    public void delete(String userId) {
        userManagementRepository.delete(userId);
    }
}
