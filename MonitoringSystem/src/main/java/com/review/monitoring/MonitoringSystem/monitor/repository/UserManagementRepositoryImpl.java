package com.review.monitoring.MonitoringSystem.monitor.repository;

import com.review.monitoring.MonitoringSystem.monitor.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserManagementRepositoryImpl implements UserManagementRepository{
    private final EntityManager em;

    @Override
    public void insertUser(User user) {
        em.persist(user);
    }

    @Override
    public int existById(String id) {
        return em.createQuery("select u from User u where u.id = :userId")
                .setParameter("userId",id)
                .setMaxResults(1)
                .getResultList().size();
    }

    @Override
    public User selectOne(String userId) {
        return em.find(User.class, userId);
    }

    @Override
    public void updateUser(User user) {
        em.merge(user);
    }
}
