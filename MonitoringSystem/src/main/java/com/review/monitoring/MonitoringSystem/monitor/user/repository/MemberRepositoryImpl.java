package com.review.monitoring.MonitoringSystem.monitor.user.repository;

import com.review.monitoring.MonitoringSystem.monitor.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository {
    private final EntityManager em;

    @Override
    public void insert(Member member) {
        em.persist(member);
    }

    @Override
    public int existById(String id) {
        return em.createQuery("select m from Member m where m.id = :memberId")
                .setParameter("memberId",id)
                .setMaxResults(1)
                .getResultList().size();
    }

    @Override
    public Member selectOne(String memberId) {
        System.out.println("memberId = " + memberId);
        return em.find(Member.class, memberId);
    }

    @Override
    public Member update(Member member) {

        return em.merge(member);
    }

    @Override
    public void delete(String memberId) {
        em.remove(memberId);
    }
}
