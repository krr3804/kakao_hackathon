package com.review.monitoring.MonitoringSystem.monitor.user.repository;

import com.review.monitoring.MonitoringSystem.monitor.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class UserManagementRepositoryImpl implements UserManagementRepository{
    private final EntityManager em;

    @Override
    public void insert(User user) {
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
    public User update(User user) {

        return em.merge(user);
    }

    @Override
    public void delete(String userId) {
        em.remove(userId);
    }
}
